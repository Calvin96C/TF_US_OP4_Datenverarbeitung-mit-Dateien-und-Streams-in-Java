package Aufgaben.aufgabe_1.loesungen.meine_loesung;

import Utils.FilesAndPathsUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ML_Aufgabe_1
{
    public static void main(String[] args)
    {
        Path aufgabePath = Paths.get("src/Aufgaben/aufgabe_1");
        Path aufgabeFilePath = aufgabePath.resolve("aufgabe_1.txt");
        Path aufgabeMLPath = Paths.get(aufgabePath.toString(),"loesungen/meine_loesung");
        Path aufgabeMLFilePath = aufgabeMLPath.resolve("ml_text.txt");

        //region Schreiben Sie folgenden Text mittels Stream in eine Textdatei.
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

        //region Schreiben Sie jeden 2. Buchstaben dieses Texts mittels Stream in eine Textdatei.
        /*
        String entireContent;
        String content;

        try
        {
            // Get the entire content
            entireContent = Files.readString(aufgabeFilePath);

            // Find the index position within the string, which contains the keyword "Lorem"
            int startIndex = entireContent.indexOf("Lorem");

            // Exit this method, if the keyword could not be found
            if(startIndex == -1)
            {
                System.out.println("The keyword ''Lorem'' could not be found");
                return;
            }

            // Split the content at the index position
            content = entireContent.substring(startIndex);

            // Cleanup the content to remove everything except letters
            content = content.replaceAll("[^A-Za-z]","");

            // Build a string with every second letter
            StringBuilder everySecondLetter = new StringBuilder();

            for(int i = 0; i < content.length(); i += 2)
            {
                everySecondLetter.append(content.charAt(i));
            }

            // Write the result into the result file
            Files.writeString(aufgabeMLFilePath,everySecondLetter);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
         */
        //endregion

        //region Schreiben Sie die ersten 100 Zeichen des folgenden Texts mittels Stream in eine Textdatei.
        String entireContent;
        StringBuilder firstOneHundred = new StringBuilder();

        try
        {
            entireContent = Files.readString(aufgabeFilePath);

            entireContent = entireContent.substring(entireContent.indexOf("Lorem"));

            //region Version 1
            for (int i = 0; i < 100; i++)
            {
                firstOneHundred.append(entireContent.charAt(i));
            }
            //endregion

            //region Version 2
            String firstOneHundredV2 = entireContent.chars() // Create an IntStream of characters
                    .limit(100) // Limit to the first 100 characters
                    .mapToObj(c -> String.valueOf((char) c)) // Convert each character to a String
                    .collect(Collectors.joining()); // Join the characters into a single String
            //endregion

            //System.out.println(firstOneHundredV2.contentEquals(firstOneHundred)); //Checking if both versions are identical

            Files.writeString(aufgabeMLFilePath,firstOneHundredV2);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        //endregion
    }
}
