package com.epicodus.symphoniccomposers.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 9/15/17.
 */

@Parcel
public class SymphonyComposer {
//    private String mName;
//    private String mBirthDeath;
//    private String mContent;

    String mName;
    String mBirthDeath;
    String mContent;
    String mPageUrl;

    public SymphonyComposer() {}

    public SymphonyComposer(String name, String birthDeath, String content, String pageUrl) {
        this.mName = name;
        this.mBirthDeath = birthDeath;
        this.mContent = content;
        this.mPageUrl = pageUrl;
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

    public String getPageUrl() { return mPageUrl; }
}
