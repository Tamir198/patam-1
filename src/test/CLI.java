package test;

import java.util.ArrayList;

import test.Commands.Command;
import test.Commands.DefaultIO;

public class CLI {

	ArrayList<Command> commands;
	DefaultIO dio;
	Commands c;
	
	public CLI(DefaultIO dio) {
		this.dio=dio;
		c=new Commands(dio); 
		commands=new ArrayList<>();
		commands.add(c.new UploadTimeSeriesFile());
		commands.add(c.new ChangeAlgoSettings());
		commands.add(c.new DetectAnomalies());
		commands.add(c.new DisplayResults());
		commands.add(c.new UploadAndAnalyzeResults());
		commands.add(c.new Exit());
		// example: commands.add(c.new ExampleCommand());
		// implement
	}
	
	public void start() {
		for (Command c: commands) {
			System.out.println(c.description);

		}
		// implement
	}
}
