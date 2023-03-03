package model;

public class FakeTTSFacade extends TTSFacade{

	private String playedContents = "";
	private float volume;
	private float pitch;
	private int wpm;
	
	public FakeTTSFacade() {
		super();
	}
	
	
	// Test tts library
	public void play(String text) {
				
		playedContents += text;
		super.play(text);
		
		
	}
	
	
	public void setVolume(float volumeValue) {
		super.setVolume(volumeValue);
		volume = volumeValue;
	}
	
	public void setPitch(float pitchValue) {
		super.setPitch(pitchValue);
		pitch = pitchValue;
	}
	
	
	// wpm will be in the range [100,200]
	public void setWordsPerMinute(int wpm) {
		super.setWordsPerMinute(wpm);
		this.wpm = wpm;
		
	}
	
	
	
	public float getVolume() {
		return volume;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getWordsPerMinute() {
		return wpm;
	}
	
	
	public float getParentVolume() {
		return super.getVolume();
	}
	
	public float getParentPitch() {
		return super.getPitch();
	}
	
	public float getParentWordsPerMinute() {
		return super.getRate();
	}
	
	
	
	public String getPlayedContents() {
		return playedContents;
	}
	
	
}
