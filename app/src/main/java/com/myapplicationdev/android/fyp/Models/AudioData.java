package com.myapplicationdev.android.fyp.Models;

public class AudioData {
    // static variable single_instance of type Singleton
    private static AudioData single_instance = null;

    // variable of type String
    private boolean isEnabledMusic;
    private boolean isEnabledSound;

    // private constructor restricted to this class itself
    private AudioData() {
    }

    // static method to create instance of Singleton class
    public static AudioData getInstance() {
        if (single_instance == null) {
            single_instance = new AudioData();
        }
        return single_instance;
    }

    public boolean isEnabledMusic() {
        return isEnabledMusic;
    }

    public void setEnabledMusic(boolean enabledMusic) {
        isEnabledMusic = enabledMusic;
    }

    public boolean isEnabledSound() {
        return isEnabledSound;
    }

    public void setEnabledSound(boolean enabledSound) {
        isEnabledSound = enabledSound;
    }
}
