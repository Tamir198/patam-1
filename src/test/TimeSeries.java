package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class TimeSeries {
    private String fileName;
    private String[] flightParams;

    public TimeSeries(String csvFileName) {
        fileName = csvFileName;
    }

    public void readCsvFile() throws Exception {
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(splitBy);
            if(flightParams == null){
                flightParams = data;
                System.out.println(Arrays.toString(flightParams));

            }
        }
        br.close();

    }

}
