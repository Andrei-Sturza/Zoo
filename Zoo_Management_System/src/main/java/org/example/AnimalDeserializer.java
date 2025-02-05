package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AnimalDeserializer implements JsonDeserializer<Animal> {
    @Override
    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.has("name") && !jsonObject.get("name").isJsonNull() ? jsonObject.get("name").getAsString() : "Unknown";
        int age = jsonObject.has("age") && !jsonObject.get("age").isJsonNull() ? jsonObject.get("age").getAsInt() : 0;
        boolean hungry = jsonObject.has("hungry") && !jsonObject.get("hungry").isJsonNull() && jsonObject.get("hungry").getAsBoolean();
        String animalType = jsonObject.has("animalType") && !jsonObject.get("animalType").isJsonNull() ? jsonObject.get("animalType").getAsString() : "";

        // Use the "animalType" field to determine the specific subclass
        try {
            switch (animalType.toLowerCase()) {
                case "lion":
                    return new Lion(name, age);
                case "parrot":
                    return new Parrot(name, age);
                case "dolphin":
                    return new Dolphin(name, age);
                default:
                    // Throw the custom exception when animal type is not recognized
                    throw new AnimalTypeNotFoundException("Unknown animal type: " + animalType);
            }
        } catch (AnimalTypeNotFoundException e) {
            // Handle the exception by printing the error message (or log it)
            System.err.println("Error: " + e.getMessage());
            return null;  // Return null or handle as appropriate for your application
        }
    }

    // Method to load animals from the JSON file
    public static List<Animal> loadAnimalsFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Animal.class, new AnimalDeserializer())
                    .create();

            // Read the list of animals from JSON
            return gson.fromJson(reader, new TypeToken<List<Animal>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Error loading animals from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
