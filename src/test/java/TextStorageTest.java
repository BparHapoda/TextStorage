import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextStorageTest {
    private String current;

    @Test
    public void getFileName (){

    TextStorage textStorage = new TextStorage();
    String rootPath= "d:";
    TextDoc textDoc =new TextDoc();
    textDoc.setName("file");
    String expected = "d:file";
            current = textStorage.getFileName(rootPath,textDoc);
    assertEquals(expected,current);
}

}