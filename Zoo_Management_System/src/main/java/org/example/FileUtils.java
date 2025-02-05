package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileUtils {

    public static void saveAnimalsToFile(List<Animal> animals, String filename) {
        try {
            // Read the existing file data first, if any
            JsonArray jsonArray = new JsonArray();

            // If file exists, load the previous data
            File file = new File(filename);
            if (file.exists()) {
                // Read the existing JSON content into a JsonArray
                try (FileReader reader = new FileReader(filename)) {
                    JsonReader jsonReader = new JsonReader(reader);
                    jsonReader.setLenient(true); // Allow lenient parsing of malformed JSON
                    JsonElement existingData = JsonParser.parseReader(jsonReader);
                    if (existingData.isJsonArray()) {
                        jsonArray = existingData.getAsJsonArray(); // If the existing data is a valid JSON array
                    }
                } catch (IOException e) {
                    System.out.println("Error reading existing file: " + e.getMessage());
                } catch (JsonSyntaxException e) {
                    System.out.println("Malformed JSON detected. Starting with an empty file.");
                }
            }

            // Add the new animals to the JSON array
            for (Animal animal : animals) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", animal.getName());
                jsonObject.addProperty("age", animal.getAge());
                jsonObject.addProperty("hungry", animal.isHungry());
                jsonObject.addProperty("animalType", animal.getType()); // Use getType to include animal type
                jsonArray.add(jsonObject);
            }

            // Write the JsonArray to file
            try (FileWriter writer = new FileWriter(filename)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(jsonArray, writer);
                System.out.println("Animals saved successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error saving animals to file: " + e.getMessage());
        }
    }

    public static void parallelSaveAnimalsToFile(List<Animal> animals, String filename) {
        ExecutorService executor = Executors.newFixedThreadPool(4);  // 4 threads
        int chunkSize = (int) Math.ceil((double) animals.size() / 4);
        List<Future<Void>> futures = new ArrayList<>();

        // Divide the list into chunks
        for (int i = 0; i < 4; i++) {
            final int start = i * chunkSize;  // Make these variables effectively final
            final int end = Math.min(start + chunkSize, animals.size());
            final List<Animal> chunk = animals.subList(start, end);

            futures.add(executor.submit(() -> {
                saveChunkToTempFile(chunk, "chunk_" + start + ".json");
                return null;  // Void return type
            }));
        }

        // Wait for all threads to complete
        try {
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (Exception e) {
            System.out.println("Error saving animals in parallel: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        // Merge temporary files into the final file
        mergeChunksToFile(filename, 4);
    }

    private static void saveChunkToTempFile(List<Animal> animals, String tempFilename) throws IOException {
        try (FileWriter writer = new FileWriter(tempFilename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(animals, writer);
        }
    }

    private static void mergeChunksToFile(String finalFilename, int numberOfChunks) {
        try (FileWriter writer = new FileWriter(finalFilename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonArray mergedArray = new JsonArray();

            for (int i = 0; i < numberOfChunks; i++) {
                String tempFilename = "chunk_" + i + ".json";
                try (FileReader reader = new FileReader(tempFilename)) {
                    JsonArray chunkArray = JsonParser.parseReader(reader).getAsJsonArray();
                    mergedArray.addAll(chunkArray);
                }
                new File(tempFilename).delete();  // Delete temp files after merging
            }

            gson.toJson(mergedArray, writer);
            System.out.println("Animals saved successfully in parallel.");
        } catch (IOException e) {
            System.out.println("Error merging chunks: " + e.getMessage());
        }
    }



    public static List<Animal> loadAnimalsFromFile(String filename) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Animal.class, new AnimalDeserializer()); // Register the custom deserializer
        Gson gson = gsonBuilder.create();

        try (Reader reader = new FileReader(filename)) {
            Animal[] animalsArray = gson.fromJson(reader, Animal[].class);
            // Return a mutable ArrayList instead of an immutable list
            return new ArrayList<>(List.of(animalsArray));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
            return new ArrayList<>();  // Return an empty list if the file is missing
        } catch (IOException e) {
            System.out.println("Error loading animals from file: " + e.getMessage());
            return new ArrayList<>();  // Return an empty list on IO errors
        }
    }

    // Search for patterns in animals' names using the Aho-Corasick algorithm
    public void searchAnimalsByPattern(String pattern) {
        // Create Aho-Corasick object
        AhoCorasick ahoCorasick = new AhoCorasick();

        // Add the pattern to search for
        ahoCorasick.addPattern(pattern);

        // Build failure links
        ahoCorasick.buildFailureLinks();

        // Load animals from the JSON file
        List<Animal> animals = FileUtils.loadAnimalsFromFile("animals.json");

        if (animals.isEmpty()) {
            System.out.println("No animals found to search.");
            return;
        }

        // Search for the pattern in each animal's name
        for (Animal animal : animals) {
            Map<String, List<Integer>> matches = ahoCorasick.search(animal.getName());
            if (!matches.isEmpty()) {
                System.out.println("Matches found for pattern '" + pattern + "' in animal: " + animal.getName());
                matches.forEach((key, value) -> {
                    System.out.println("Pattern: " + key + " found at positions: " + value);
                });
            }
        }
    }


    private static final String USERS_FILE = "users.json";

    public static List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
