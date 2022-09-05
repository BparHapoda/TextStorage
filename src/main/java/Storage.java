

import java.io.IOException;
import java.util.ArrayList;


public interface Storage {

    void openCollection() throws IOException;

    boolean deleteFile(TextDoc textDoc);


    ArrayList<TextDoc> getSortedCollection(sort sort);

    void createCollection(String rootPath);

    ArrayList<TextDoc> createFileList();
}