import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    private static String sourcePath = "src/aufgaben/";
    private static Path projectPath = Paths.get("").toAbsolutePath();  // Get project directory (inside "Main");
    private static Path bausteinPath = projectPath.resolve("../../").normalize(); // Use "../" to go up one cirectory

    public static void main(String[] args)
    {

        //region Read from file
        /*
        Path p = Paths.get("Resources","Test1.txt"); // Benötigen wir als Verweis zu unserer Datei; dynamisch, BS unabhängig

        try (InputStream inputWithFile = Files.newInputStream(p))
        {
            String dataForFile2 = new String(inputWithFile.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(dataForFile2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         */
        //endregion

    }
}