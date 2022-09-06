

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class TextDoc implements Serializable {

    @Serial
    private static final long serialVersionUID = 6850662765961184140L;
    private String text;
    private String name;
    private String author;
    private final LocalDate date;

    public TextDoc() {
        date = LocalDate.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDate() {
        return date;
    }


    public String showAttributes() {
        String string = "\n" + "Автор : " + author + "\n" + "Дата создания :" + date;
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextDoc textDoc = (TextDoc) o;
        return Objects.equals(text, textDoc.text) && Objects.equals(name, textDoc.name) && Objects.equals(author, textDoc.author) && Objects.equals(date, textDoc.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, name, author, date);
    }
}
