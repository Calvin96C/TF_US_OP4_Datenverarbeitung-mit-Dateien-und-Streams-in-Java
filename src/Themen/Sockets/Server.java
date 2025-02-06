package Themen.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void start() throws IOException
    {
        try (ServerSocket serverSocket = new ServerSocket(8080))
        {
            System.out.println("Warte auf Verbindung...");
            // Wichtig! AutoFlush nicht vergessen!
            try (Socket socket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true))
            {

                System.out.println("Verbunden...");

                // Solange der Socket nicht geschlossen ist...
                while (!socket.isClosed())
                {
                    // lese einen Befehl aus dem Stream
                    String command = reader.readLine(); // Hier wird pausiert, bis wir vom Client eine Antwort erhalten.

                    // Befehl ins Protokoll schreiben
                    String log = String.valueOf(LocalDateTime.now()).concat(" : ").concat(command).concat(System.lineSeparator());
                    Files.writeString(Paths.get("resources/protokoll.txt"), log, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

                    switch (command.toLowerCase())
                    {
                        case "time":
                            System.out.println("time");
                            writer.println(LocalTime.now());
                            break;
                        case "directory":
                            System.out.println("directory");
                            writer.println(System.getProperty("user.dir"));
                            break;
                        case "list":
                            System.out.println("list");
                            try (Stream<Path> s = Files.list(Path.of(System.getProperty("user.dir"))))
                            {
                                List<Path> list = s.toList();
                                StringBuilder sb = new StringBuilder();
                                list.forEach(p -> sb.append(p.toString()).append("\n"));
                                writer.println(sb.toString());
                            }
                            break;
                        case "exit":
                            System.out.println("exit");
                            writer.println("bye!");
                            socket.close();
                            break;
                        default:
                            System.out.println("default");
                            writer.println("Unbekannter Befehl!");
                    }
                }
            }
        }
    }
}
