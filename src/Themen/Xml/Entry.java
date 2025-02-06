package Themen.Xml;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Entry
{
    public static final Map<String, Entry> entries = new HashMap<>();

    private String id;
    private String title;
    private String description;
    private LocalDate date;
    private int salary;
    private String currency;

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public int getSalary()
    {
        return salary;
    }

    public String getCurrency()
    {
        return currency;
    }

    public Entry(String id, String title, String description, LocalDate date, int salary, String currency)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.salary = salary;
        this.currency = currency;

        entries.put(id, this);
    }

    @Override
    public String toString()
    {
        return "Entry{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", salary=" + salary +
            ", currency='" + currency + '\'' +
            '}';
    }
}
