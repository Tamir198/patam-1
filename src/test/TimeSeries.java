package test;


import java.io.BufferedReader;
import java.io.FileReader;


public class TimeSeries {

    private String fileName;
    private float[][] dataMatrix;
    private String[] criteriaTitles;


    public TimeSeries(String csvFileName) {
        fileName = csvFileName;
    }

    void readCsvFile() throws Exception {
        int numOfCriteria = 0, counter = 0;
        String[] data;
        String delimiter = ",", line;
        BufferedReader buf = new BufferedReader(new FileReader(this.fileName));

        line = buf.readLine();
        if (line != null) {
            data = line.split(delimiter);
            numOfCriteria = data.length;
            dataMatrix = new float[getNumOfLines(fileName)][numOfCriteria];
            criteriaTitles = new String[data.length];
            System.arraycopy(data, 0, criteriaTitles, 0, data.length);
        }

        while ((line = buf.readLine()) != null) {
            data = line.split(delimiter);

            for (int i = 0; i < numOfCriteria; i++) {
                dataMatrix[counter][i] = Float.parseFloat(data[i]);
            }
            counter++;
        }

        buf.close();
    }

    public int getNumOfLines(String fileName) throws Exception {
        BufferedReader buf = new BufferedReader(new FileReader((fileName)));
        int counter = 0; // the function will return one line more than reality

        while ((buf.readLine()) != null) {
            counter++;
        }
        return counter - 1;
    }

}
