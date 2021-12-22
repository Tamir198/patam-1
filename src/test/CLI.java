package test;

import java.util.ArrayList;
import java.util.HashMap;

import test.Commands.Command;
import test.Commands.DefaultIO;

public class CLI {

    HashMap<Integer, Command> commands;
    DefaultIO dio;
    Commands c;

    public CLI(DefaultIO dio) {
        this.dio = dio;
        c = new Commands(dio);
        commands = new HashMap<Integer, Command>();
        commands.put(1, c.new UploadTimeSeriesFile());
        commands.put(2, c.new ChangeAlgoSettings());
        commands.put(3, c.new DetectAnomalies());
        commands.put(4, c.new DisplayResults());
        commands.put(5, c.new UploadAndAnalyzeResults());
        commands.put(6, c.new Exit());

    }

    public void start() {
        //Keep interacting with the user untill he presses 6
        int counter = 3 ;
        while(counter > 0 ) {
            dio.write("Welcome to the Anomaly Detection Server.\n" +
                    "Please choose an option:\n");
            for (int i = 0; i < commands.size(); i++) {
                dio.write(commands.get(i + 1).description + "\n");
            }

            int nextFloat = (int) dio.readVal();
            commands.get(nextFloat).execute();

            counter--;
        }
    }
}
