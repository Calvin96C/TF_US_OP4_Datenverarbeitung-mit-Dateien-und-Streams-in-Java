package Vorbereitung.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ObjectToJsonUtil
{
    public static <T> void  writeObjectListToJson(List<T> objectList, Path filePath)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            if (Files.notExists(filePath))
            {
                Files.createFile(filePath);
                System.out.println("File did not exist yet and has now been created.");
            }

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath.toString()),objectList);
            System.out.println("File has been updated.");

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
