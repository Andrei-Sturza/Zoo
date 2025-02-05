package org.example;

public class FileOperationException extends Exception {

    // Constructor with only the error message
    public FileOperationException(String message) {
        super(message);
    }

    // Constructor with the error message and the cause (Throwable)
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
