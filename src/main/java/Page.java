import java.util.ArrayList;
import java.util.Objects;


public class Page {
    private final ArrayList<String> text;


    public Page(ArrayList<String> text) {
        this.text = text;
    }

    public ArrayList<String> getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(text, page.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}