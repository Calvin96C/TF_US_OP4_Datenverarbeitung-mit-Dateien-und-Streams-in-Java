package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FilesAndPathsUtil
{
    private static String sourcePath = "src/";

    public static boolean createTaskDirectory(String taskNumber)
    {
        String loesungPath = sourcePath + "aufgaben/aufgabe_" + taskNumber + "/loesungen/loesung_dozent";

        String completePath = "src/aufgaben/aufgabe_2/loesungen/loesung_dozent";

        File directory = new File(loesungPath);

        if (!directory.exists())
        {
            directory.mkdirs();

            directory = new File(directory.getParent() + "/meine_loesung");

            return directory.mkdirs();
        }

        return false;
    }

    public static boolean deleteTaskRecursively(String taskNumber)
    {
        //TODO funktioniert noch nicht, wenn das Verzeichnis nicht leer ist.
        String pathString = sourcePath + "aufgaben/aufgabe_" + taskNumber;

        File directory = new File(pathString);

        return directory.delete();
    }

    public static void createFile(Path filepath)
    {
        // First check, if the directory exists. If not, create it.
        Path directoryPath = filepath.getParent();
        if(Files.notExists(directoryPath))
        {
            try{Files.createDirectories(directoryPath);}
            catch (IOException ex){ex.printStackTrace();}
        }

        // Create the file within the directory, if it does not exist yet
        try
        {
            if(Files.notExists(filepath))
            {
                Files.createFile(filepath);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void copyFileToDirectory(Path sourceFile, Path targetDirectory, boolean overwriteIfExists) throws IOException
    {
        // Ensure source file exists
        if(Files.notExists(sourceFile))
        {
            throw new IOException("Source file does not exist: " + sourceFile);
        }

        // Ensure target directory exists, otherwise create it
        Files.createDirectories(targetDirectory);

        // Create the option array. This notation is based on the ternary operator. More info can be found here: https://www.baeldung.com/java-ternary-operator
        CopyOption[] options = overwriteIfExists
                ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING}
                : new CopyOption[0];

        // Create target file path
        Path targetFile = targetDirectory.resolve(sourceFile.getFileName());

        // Copy the file
        Files.copy(sourceFile,targetFile,options);
    }
}
