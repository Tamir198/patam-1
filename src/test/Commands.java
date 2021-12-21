package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Commands {
	
	// Default IO interface
	public interface DefaultIO{
		public String readText();
		public void write(String text);
		public float readVal();
		public void write(float val);

		// you may add default methods here
	}
	
	// the default IO to be used in all commands
	DefaultIO dio;
	public Commands(DefaultIO dio) {
		this.dio=dio;
	}
	
	// you may add other helper classes here
	
	
	
	// the shared state of all commands
	private class SharedState{
		// implement here whatever you need
		
	}
	
	private  SharedState sharedState=new SharedState();

	
	// Command abstract class
	public abstract class Command{
		protected String description;
		
		public Command(String description) {
			this.description=description;
		}
		
		public abstract void execute();
	}
	
	// Command class for example:
	public class UploadTimeSeriesFile extends Command{

		public UploadTimeSeriesFile() {
			super("1. upload a time series csv file");
		}

		@Override
		public void execute() {
			dio.write(description);
		}		
	}
	

	public class ChangeAlgoSettings extends Command{

		public ChangeAlgoSettings() {
			super("2. algorithm settings");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}

	public class DetectAnomalies extends Command{

		public DetectAnomalies() {
			super("3. detect anomalies");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}

	public class DisplayResults extends Command{

		public DisplayResults () {
			super("4. display results");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}

	public class UploadAndAnalyzeResults extends Command{

		public UploadAndAnalyzeResults() {
			super("5. upload anomalies and analyze results");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}

	public class Exit extends Command{

		public Exit() {
			super("6. exit");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}


	
}
