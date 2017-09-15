package com.epicodus.symphoniccomposers.models;

/**
 * Created by Guest on 9/15/17.
 */

public class SymphonyComposer {
    private String mName;
    private String mBirthDeath;
    private String mContent;

    public SymphonyComposer(String name, String birthDeath, String content) {
        this.mName = name;
        this.mBirthDeath = birthDeath;
        this.mContent = content;
    }

    public String getName() {
        return mName;
    }

    public String getBirthDeath() {
        return mBirthDeath;
    }

    public String getContent() {
        return mContent;
    }
}
