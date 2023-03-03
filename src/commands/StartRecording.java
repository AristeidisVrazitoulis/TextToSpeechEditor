package commands;

public class StartRecording extends CommandDocument {
	private static StartRecording instance;
	
	public StartRecording() {
		instance = this;

	}
	
	public static StartRecording getInstance() {
		if(instance==null) {
			System.out.println("instance is null");
		}
		return instance;
	}
	
	@Override 
	public void actionPerformed() {

		// enables replay button
		replayManager.startRecording();

		
	}
	

	
	
}
