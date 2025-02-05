package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    private static final String USERS_FILE = "users.json";

    public static List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Gson gson = new Gson();
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());

            // Debugging: Print loaded users or detect null
            if (users == null) {
                System.out.println("Error: No users found in the users file.");
                return new ArrayList<>();
            }
            //System.out.println("Users loaded: " + users);
            return users;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Users file not found - " + USERS_FILE);
        } catch (JsonSyntaxException e) {
            System.out.println("Error: Malformed JSON in users file - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return new ArrayList<>(); // Return empty list on failure
    }
}
