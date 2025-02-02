package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

    public static boolean createTxtFile(Path path)
    {
        File file = new File(path.toUri());

        try
        {
            return file.createNewFile();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return false;
    }
}
