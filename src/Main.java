package src;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Specify the file path directly here
        String inputFilePath = "/Users/ketchup/Desktop/CSC 340/Tutorial/A3/MyParser/input.txt"; // Update this to your
                                                                                                // file path

        try {
            // Create a FileReader to read the specified file
            FileReader fileReader = new FileReader(inputFilePath);

            // Create an instance of your lexer, which takes a FileReader
            Lexical lexer = new Lexical(fileReader);
            Parser parser = new Parser(lexer);

            // Begin parsing
            System.out.println("\n");
            int result = parser.yyparse();

            if (result == 0) {
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println("\nParsing completed successfully.\n\n");
            } else {
                System.out.println("\n-----------------------------------------------------------------");
                System.err.println("\nParsing failed with errors.\n\n");
            }
            // Close the FileReader
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during parsing: " + e.getMessage());
        }
    }
}
