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

    String name;
    String birthDeath;
    String content;
    String pageUrl;

    public SymphonyComposer() {}

    public SymphonyComposer(String name, String birthDeath, String content, String pageUrl) {
        this.name = name;
        this.birthDeath = birthDeath;
        this.content = content;
        this.pageUrl = pageUrl;
    }

    public String getName() {
        return name;
    }

    public String getBirthDeath() {
        return birthDeath;
    }

    public String getContent() {
        return content;
    }

    public String getPageUrl() { return pageUrl; }
}
