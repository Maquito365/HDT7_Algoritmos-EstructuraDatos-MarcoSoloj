package src;
import java.io.*;
import java.util.*;

public class FileManager {

    public static List<Association<String, String>> readDictionary(String path) {
        List<Association<String, String>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.replace("(", "").replace(")", "");
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String english = parts[0].trim().toLowerCase();
                    String spanish = parts[1].trim().toLowerCase();

                    list.add(new Association<>(english, spanish));
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo diccionario: " + e.getMessage());
        }

        return list;
    }

    public static List<String> readText(String path) {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\s+");

                for (String word : parts) {
                    words.add(word); 
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo texto: " + e.getMessage());
        }

        return words;
    }
}
