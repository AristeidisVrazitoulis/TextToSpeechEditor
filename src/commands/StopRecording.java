package commands;

public class StopRecording extends CommandDocument{
	
	private static StopRecording instance;
	
	public StopRecording() {
		instance = this;

	}
	
	public static StopRecording getInstance() {
		if(instance==null) {
			System.out.println("instance is null");
		}
		return instance;
	}
	
	@Override 
	public void actionPerformed() {
		replayManager.endRecording();
		replayManager.clearCommands();

		
	}
	
	
	
	
}
