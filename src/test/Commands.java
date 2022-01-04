package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Commands {
    private TimeSeries testTimeSeries,trainTimeSeries;
    private List<AnomalyReport> anomalyReports = new LinkedList<>();

    // Default IO interface
    public interface DefaultIO {
        public String readText();

        public void write(String text);

        public float readVal();

        public void write(float val);

        // you may add default methods here
    }

    // the default IO to be used in all commands
    DefaultIO dio;

    public Commands(DefaultIO dio) {
        this.dio = dio;
    }

    // you may add other helper classes here


    // the shared state of all commands
    private class SharedState {
        // implement here whatever you need

    }

    private SharedState sharedState = new SharedState();


    // Command abstract class
    public abstract class Command {
        protected String description;

        public Command(String description) {
            this.description = description;
        }

        public abstract void execute();
    }


    // Command class for example:
    public class UploadTimeSeriesFile extends Command {

        public UploadTimeSeriesFile() {
            super("1. upload a time series csv file");
        }

        @Override
        public void execute() {
            dio.write("Please upload your local train CSV file.\n");
            generateCsvFile("anomalyTrain.csv");
            dio.write("Upload complete.\n");

            dio.write("Please upload your local test CSV file.\n");
            generateCsvFile("anomalyTest.csv");
            dio.write("Upload complete.\n");
        }
    }

    private void generateCsvFile(String fileName) {
        String nextLine = "";
        try {
            FileWriter writer = new FileWriter(fileName);
            nextLine = dio.readText();
            while (!nextLine.equals("done")) {
                writer.write(nextLine);
                if (!nextLine.equals("")) {
                    writer.write("\n");
                }
                nextLine = dio.readText();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public class ChangeAlgoSettings extends Command {

        public ChangeAlgoSettings() {
            super("2. algorithm settings");
        }

        @Override
        public void execute() {
            dio.write("The current correlation threshold is " + 0.9 + "\n");
            dio.write("Type a new threshold\n");

            testTimeSeries = new TimeSeries("anomalyTest.csv");
            trainTimeSeries = new TimeSeries("anomalyTrain.csv");

            float newThreshHold =  dio.readVal();
            boolean hasPicked = false;
            while(!hasPicked){
                if(newThreshHold > 0 && newThreshHold <1){
                    testTimeSeries.setThreshold(newThreshHold);
                    trainTimeSeries.setThreshold(newThreshHold);
                    hasPicked = true;
                }else{
                    dio.write("“please choose a value between 0 and 1.\n");
                    newThreshHold =  dio.readVal();
                }
            }


        }
    }

    public class DetectAnomalies extends Command {

        public DetectAnomalies() {
            super("3. detect anomalies");
        }

        @Override
        public void execute() {
           SimpleAnomalyDetector simpleAnomalyDetector = new SimpleAnomalyDetector();
            simpleAnomalyDetector.learnNormal(trainTimeSeries);
            anomalyReports  = simpleAnomalyDetector.detect(testTimeSeries);
            dio.write("anomaly detection complete.\n");
        }
    }

    public class DisplayResults extends Command {

        public DisplayResults() {
            super("4. display results");
        }

        @Override
        public void execute() {
            for (AnomalyReport ar: anomalyReports) {
                dio.write(ar.timeStep + "\t" + ar.description + "\n");
            }
            dio.write("Done.\n");
        }
    }

    public class UploadAndAnalyzeResults extends Command {

        public UploadAndAnalyzeResults() {
            super("5. upload anomalies and analyze results");
        }

        @Override
        public void execute() {
            dio.write(description);
        }
    }

    public class Exit extends Command {

        public Exit() {
            super("6. exit");
        }

        @Override
        public void execute() {
            dio.write(description);
        }
    }


}
