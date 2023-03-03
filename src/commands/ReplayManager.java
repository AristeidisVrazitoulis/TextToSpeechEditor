package commands;

import java.util.ArrayList;

public class ReplayManager {

	private boolean recordingStatus;
	private ArrayList<CommandDocument> commands = new ArrayList<CommandDocument>();
	private static ReplayManager instance;
	
	public ReplayManager() {
		
	}
	
	public void startRecording() {
		recordingStatus = true;
	}
	
	public void endRecording() {
		recordingStatus = false;
	}
	
	public void replay() {
		for(CommandDocument command : commands) {
			command.actionPerformed();
		}
	}
	public boolean isActiveRecording() {
		return recordingStatus;
	}
	
	
	public void clearCommands() {
		commands.clear();
	}
	
	public void addCommand(CommandDocument command) {
		commands.add(command);
	}
	
	public static ReplayManager getInstance() {
		if(instance==null) {
			instance = new ReplayManager();
		}
		return instance;
	}
}
