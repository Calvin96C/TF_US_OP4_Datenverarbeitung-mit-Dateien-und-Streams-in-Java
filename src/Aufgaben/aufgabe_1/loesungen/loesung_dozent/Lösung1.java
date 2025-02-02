package Aufgaben.aufgabe_1.loesungen.loesung_dozent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Lösung1
{

    public static void main(String[] args)
    {
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt\r\n"
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco\r\n"
            + "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in\r\n"
            + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat\r\n"
            + "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        try
        {
            File file = new File("resources/Aufgabe1_output_Lösung.txt");
            if (!file.exists())
            {
                if (file.createNewFile())
                {
                    System.out.println("Datei erstellt => " + file.getName());
                }
            }
            //outputStreamWriter(file, data);
            fileOutputStreamByte(file, data);
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // 1
    public static void outputStreamWriter(File file, String data) throws IOException
    {
        try (OutputStream out = Files.newOutputStream(file.toPath());
             Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8))
        {
            writer.write(data);
        }
    }

    // 2
    public static void fileOutputStreamByte(File file, String data) throws IOException
    {
        // Wir wollen nur Buchstaben, also nehmen wir die Satz- und Leerzeichen aus dem String raus, indem wir sie durch "" ersetzen.
        /*byte[] bytes = data.replaceAll("[., ]", "").getBytes();
        try (OutputStream out = Files.newOutputStream(file.toPath()))
        {
            for (int i = 0; i < bytes.length; i++)
            {
                if (i % 2 != 0)
                    out.write(bytes[i]);
            }
        }*/

        // Alternative, für wenn ein Zeichen mehr als ein Byte sein könnte:
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true)))
        {
            char[] arr = data.toCharArray();
            for (int i = 0; i < data.length(); i = i + 2)
            {
                writer.write(arr[i]);
            }
        }
    }

    // 3
    public static void fileOutputStreamByteSingleAtSpecificPosition(File file, String data) throws IOException
    {
        byte[] bytes = data.getBytes();
        try (OutputStream out = Files.newOutputStream(file.toPath()))
        {
            out.write(bytes, 0, 100);
        }
    }

}
