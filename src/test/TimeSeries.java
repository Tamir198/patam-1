package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

    String fileName;
    float[][] dataMatrix;
    String[] criteriaTitles;


    public TimeSeries(String csvFileName) {
        fileName = csvFileName;
    }

    public void readCsvFile() throws Exception {
        int numOfCriteria = 0;
        int counter = 1; // represents the lines in the matrix dataNames
        String[] data;
        String delimiter = ",";
        BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
        String line;

        line = buf.readLine();
        if (line!= null){
            data = line.split(delimiter);
            numOfCriteria = data.length;
            dataMatrix = new float[getNumOfLines(fileName)][numOfCriteria];
            criteriaTitles  = new String[data.length];
            for (int i = 0; i < data.length ; i++) {
                criteriaTitles[i] = data[i];
            }
        }


        while((line = buf.readLine()) != null){
            data = line.split(delimiter);

            for (int i=0; i<numOfCriteria; i++){
                dataMatrix[counter][i]  = Float.parseFloat((data[i]));
            }

            System.out.println(Arrays.toString(data));
            counter++;
        }

        buf.close();
    }

    public int getNumOfLines (String fileName) throws Exception{
        BufferedReader buf = new BufferedReader(new FileReader((fileName)));
        int counter = 0; // the function will return one line more than reality
        while((buf.readLine()) != null){
            counter++;
        }
        return counter;
    }

}
