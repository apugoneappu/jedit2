package com.example.speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class synthesisTest {

    private static final String VOICENAME_kevin = "kevin16";
    Voice voice;
    VoiceManager voiceManager;

    public synthesisTest() {
        voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME_kevin);
        voice.allocate();
    }

    public void read(String text) {
        voice.speak(text);
    }

}
