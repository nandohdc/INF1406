package Consumidor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by nando on 4/18/2017.
 */
public class Writer {
    private FileWriter writer;
    private BufferedWriter bwriter;
    private File file;
    private String path;

    public Writer(String PathToFile){
        this.path = PathToFile;
        this.file = null;
        this.writer = null;
        this.bwriter = null;
    }

    public String getPath(){
        return this.path;
    }

    public void openFile(){
        try {
            this.file = new File(this.getPath());
            this.writer = new FileWriter(this.file);
            this.bwriter = new BufferedWriter(this.writer);
        } catch (IOException e) {
            System.out.println("Could not create the file" + e.getMessage());
            System.exit(0);
        }
    }

    public void toWrite(double[][] data){
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if(j == (data[0].length -1)) {
                        bwriter.write(String.format("%.2f", data[i][j]));
                    } else{
                        bwriter.write(String.format("%.2f", data[i][j]) + " ");
                    }
                }
                bwriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Could not write on the file" + e.getMessage());
            System.exit(0);
        }
    }

    public void closeFile(){
        try {
            this.bwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
