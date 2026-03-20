package src;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("   TRADUCTOR INGLÉS - ESPAÑOL ");
        System.out.println("======================================");

        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        // cargar diccionario
        List<Association<String, String>> dictionary = FileManager.readDictionary("diccionario.txt");

        for (Association<String, String> assoc : dictionary) {
            tree.insert(assoc);
        }

        int option = 0;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Mostrar diccionario ordenado");
            System.out.println("2. Traducir texto");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {

                    case 1:
                        System.out.println("\n--- Diccionario (In-Order) ---\n");
                        tree.inOrder();
                        break;

                    case 2:
                        translateText(tree);
                        break;

                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
            }

        } while (option != 3);

        scanner.close();
    }

    
    private static void translateText(BinaryTree<Association<String, String>> tree) {

        List<String> text = FileManager.readText("texto.txt");

        System.out.println("\n--- Resultado ---\n");

        StringBuilder result = new StringBuilder();

        for (String word : text) {

            // separar palabra y puntuación
            String clean = word.replaceAll("[^a-zA-Z]", "");
            String punctuation = word.replaceAll("[a-zA-Z]", "");

            if (clean.isEmpty()) {
                result.append(word).append(" ");
                continue;
            }

            String lower = clean.toLowerCase();

            Association<String, String> key = new Association<>(lower, "");
            Association<String, String> found = tree.search(key);

            String translated;

            if (found != null) {
                translated = found.getValue();
            } else {
                translated = "*" + clean + "*";
            }

            result.append(translated).append(punctuation).append(" ");
        }

        // capitalizar primera letra
        if (result.length() > 0) {
            result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
        }

        System.out.println(result.toString().trim());
    }
}