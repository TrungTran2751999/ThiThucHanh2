package ultis;

import java.io.FileWriter;
import java.io.IOException;

public class CSVultils {
    private String path;
    public CSVultils(String path){
        this.path = path;
    }
    public void fileWrite(){
        try {
            FileWriter fr = new FileWriter(this.path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
