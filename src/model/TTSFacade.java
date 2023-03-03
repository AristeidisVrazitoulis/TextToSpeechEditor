package model;
import com.sun.speech.freetts.*;



public class TTSFacade{
	
	
	private VoiceManager voiceManager;
	private Voice voice;
	
	private static TTSFacade instance = null;
	private float wordsPerMinute = 150.0f;
	
	public TTSFacade() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voiceManager = VoiceManager.getInstance();
		
		voice = voiceManager.getVoice("kevin");
		voice.allocate();
	}
	
	
	// Test tts library
	public void play(String text) {
				
		try {
			voice.speak(text);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void setVolume(float volumeValue) {
		voice.setVolume(volumeValue);
	}
	
	public void setPitch(float pitchValue) {
		voice.setPitch(pitchValue);
	}
	
	
	// wpm will be in the range [100,200]
	public void setRate() {
		
		if(wordsPerMinute<100) {
			wordsPerMinute = 100;
		}else if(wordsPerMinute>200) {
			wordsPerMinute = 200;
		}
		voice.setRate(wordsPerMinute);
		
	}
	
	public void setWordsPerMinute(int wpm) {
		wordsPerMinute = wpm;
	}
	
	
	public void incrementRate() {
		wordsPerMinute += 10;
	}
	
	public void decrementRate() {
		wordsPerMinute -= 10;
	}
	
	
	// ------------ GETTERS ------------
	
	public float getVolume() {
		return voice.getVolume();
	}
	
	public float getPitch() {
		return voice.getPitch();
	}
	
	public int getRate() {
		return (int) voice.getRate();
	}
	
	
	
	
	
	
	
	public static TTSFacade getInstance() {
		if(instance==null) {
			instance = new TTSFacade();
		}
		
		return instance;
	}
	
	

}
