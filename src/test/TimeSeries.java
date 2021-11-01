package test;


import java.io.BufferedReader;
import java.io.FileReader;

public class TimeSeries {
    private String fileName;

    public TimeSeries(String csvFileName) {
        this.fileName = csvFileName;
    }

    public void readcvsFile() throws Exception {
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] b = line.split(splitBy);
            System.out.println(b[0]);
        }
        br.close();
    }

}
