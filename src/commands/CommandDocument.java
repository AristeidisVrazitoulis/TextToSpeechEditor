package commands;

import model.Document;
import view.Text2SpeechEditorView;

public abstract class CommandDocument {
	
	
	protected ReplayManager replayManager;
	protected Document document;
	protected CommandDocument commandDoc;
	
	public CommandDocument() {
		document = Document.getInstance();
		replayManager = ReplayManager.getInstance();
		commandDoc = this;
	}

	
	public void setCommand(CommandDocument doc) {
		commandDoc = doc;
	}
	
	public void setReplayManager(ReplayManager manager) {
		replayManager = manager;
	}
	public void setDocument(Document doc) {
		document = doc;
	}
	
	public ReplayManager getRelayManager() {
		return replayManager;
	}
	
	
	
	public abstract void actionPerformed();
	
	
	
}
