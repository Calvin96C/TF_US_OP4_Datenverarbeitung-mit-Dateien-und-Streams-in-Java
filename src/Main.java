import Utils.FilesAndPathsUtil;

import java.io.*;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main
{
    private static String aufgabenPath = "src/aufgaben/";
    private static Path projectPath = Paths.get("").toAbsolutePath();  // Get project directory (inside "Main");
    private static Path bausteinPath = projectPath.resolve("../../").normalize(); // Use "../" to go up one cirectory

    public static void main(String[] args)
    {

        //region Changing directories
        /*
        Path testPath = Paths.get("").toAbsolutePath();

        System.out.println(testPath);

        testPath = testPath.resolve("../../../").normalize();

        System.out.println();
        System.out.println(testPath);
         */
        //endregion

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

        //region Copy content from one file to another. Start at the word "Lorem"
        /*
        Path targetPath = Paths.get("Resources","Output_Aufgabe_1.txt");
        Path aufgabenPath = Paths.get(bausteinPath.toString(),"Download\\20_InputOutputStreams\\src\\aufgaben\\output\\Aufgabe_1.txt");

        try(BufferedReader reader = Files.newBufferedReader(aufgabenPath);
            BufferedWriter writer = Files.newBufferedWriter(targetPath))
        {
            String line = reader.readLine(); // Set the reader file to its first line and write that into the line String
            String nextLine;
            boolean copy = false;

            // Looping through the reader file
            while(line  != null)
            {
                nextLine = reader.readLine(); // Writing the next line already into this variable, so we can check if we need to set a new line later.

                if (!copy && line.contains("Lorem")) // Scanning for the keyword, so we can start copying from here
                {
                    copy = true;
                }

                if (copy)
                {
                    writer.write(line);
                    if((nextLine != null)) // If we have a next line in the reader document, we need to create a new line
                    {
                        writer.newLine();
                    }
                }

                line = nextLine; // update the current line to the next line
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
         */
        //endregion

        //region Copy file to a target file
            //region Version 1
            /*
            Path targetPath = Paths.get(projectPath.toString(),"src/Aufgaben/aufgabe_1/aufgabe_1.txt");
            Path aufgabenPath = Paths.get(bausteinPath.toString(), "Download/20_InputOutputStreams/src/aufgaben/output/Aufgabe_1.txt");

            try
            {
                Files.createDirectories(targetPath.getParent()); // Make sure this directory exists, if not, create it.

                Files.copy(aufgabenPath,targetPath, StandardCopyOption.REPLACE_EXISTING); // In case this file exists, simply replace it.
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
             */
            //endregion

            //region Version 2
        /*
            Path sourceFile = bausteinPath.resolve("Download/20_InputOutputStreams/src/aufgaben/output/Lösung1.java");
            Path targetDirectory = Paths.get(aufgabenPath, "aufgabe_1/loesungen/loesung_dozent");

            try
            {
                FilesAndPathsUtil.copyFileToDirectory(sourceFile,targetDirectory,true);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
         */
            //endregion
        //endregion

    }
}