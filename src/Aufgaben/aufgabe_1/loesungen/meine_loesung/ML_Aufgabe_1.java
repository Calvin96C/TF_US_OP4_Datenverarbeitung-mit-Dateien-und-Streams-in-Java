package Aufgaben.aufgabe_1.loesungen.meine_loesung;

import Utils.FilesAndPathsUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ML_Aufgabe_1
{
    public static void main(String[] args)
    {
        Path aufgabePath = Paths.get("src/Aufgaben/aufgabe_1");
        Path aufgabeMLPath = Paths.get(aufgabePath.toString(),"loesungen/meine_loesung");

        //region Schreiben Sie folgenden Text mittels Stream in eine Textdatei.
            //region Lösung 1 Buffered Reader copy line by line
            /*
            Path mlText = Paths.get(aufgabeMLPath.toString(),"ml_text.txt");

            // Create the file & directory in case it does not exist yet
            FilesAndPathsUtil.createFile(mlText);

            // Copy line by line from the source file into our target file
            try(BufferedReader reader = Files.newBufferedReader(Paths.get(aufgabePath.toString(),"aufgabe_1.txt"));
                BufferedWriter writer = Files.newBufferedWriter(mlText))
            {
                String line = reader.readLine();
                String nextLine;
                Boolean copy = false;

                while(line != null)
                {
                    // As soon as we encounter the word "Lorem", set the boolean copy to true
                    if(line.contains("Lorem"))
                    {
                        copy = true;
                    }

                    // Copy only, if the boolean copy is set to true
                    if(copy)
                    {
                        writer.write(line);
                    }

                    // Go to the next line within our source file
                    nextLine = reader.readLine();

                    // If we are not at the end of our source file, we can enter a new line within our target file
                    if(copy && nextLine != null)
                    {
                        writer.newLine();
                    }
                    line = nextLine;
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
             */
            //endregion
            //region Lösung 2

            //endregion
        //endregion
    }
}
