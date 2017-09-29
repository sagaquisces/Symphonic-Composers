package com.epicodus.symphoniccomposers.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 9/15/17.
 */

@Parcel
public class SymphonyComposer {

    String name;
    String birthDeath;
    String content;
    String pageUrl;
    String index;
    private String pushId;

    public SymphonyComposer() {}

    public SymphonyComposer(String name, String birthDeath, String content, String pageUrl) {
        this.name = name;
        this.birthDeath = birthDeath;
        this.content = content;
        this.pageUrl = pageUrl;
        this.index = "not_specified";
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
