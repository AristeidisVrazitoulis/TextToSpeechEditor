package commands;




public class DocumentToSpeech extends CommandDocument{
	
	private String conversionText;
	
	
	public DocumentToSpeech(String conversionText) {
		this.conversionText = conversionText;
		replayManager = ReplayManager.getInstance();
		
		
		if(replayManager.isActiveRecording()) {
			replayManager.addCommand(commandDoc);
		}		
	}
	
	
		
	@Override
	public void actionPerformed() {
		
		document.playContents(conversionText);
	}
	
	public void setConversionText(String text) {
		conversionText = text;
	}
	
	

	
	

	
	
	
}
