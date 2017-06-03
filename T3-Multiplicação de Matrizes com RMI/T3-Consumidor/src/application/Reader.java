package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nando on 4/18/2017.
 */
public class Reader {
    private BufferedReader breader;
    private FileReader freader;
    private String path;
    private File file;
    private List<Double> Data;
    private int MatrixDimension;

    public String getPath(){
        return this.path;
    }

    public Reader(String PathToFile, int MatrixDimension){
        this.breader = null;
        this.freader = null;
        this.file = null;
        this.path = PathToFile;
        this.Data = new ArrayList<Double>();
        this.MatrixDimension = MatrixDimension;
    }

    public void openFile(){
        try{
            this.file = new File(this.getPath());
            this.freader = new FileReader(this.file);
            this.breader = new BufferedReader(this.freader);
        } catch(Exception e){
            System.out.println("Couldn't Find the File" + e.getMessage());
            System.exit(0);
        }
    }

    public List<Double> readFile(){
        String line;
        try {
            while(( line = this.breader.readLine()) != null){
                String[] split = line.split(" ");
                for(int i = 0; i < split.length; i++){
                    Data.add(Double.parseDouble(split[i]));
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't read from File" + e.getMessage());
            System.exit(0);
        }

        return this.Data;
    }

    public void closeFile(){
        try {
            this.freader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
