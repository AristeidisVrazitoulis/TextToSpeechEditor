package commands;



public class ReplayCommand extends CommandDocument {

	public ReplayCommand() {
		
	}
	

	
	public void actionPerformed() {
		replayManager.replay();
	}
}
