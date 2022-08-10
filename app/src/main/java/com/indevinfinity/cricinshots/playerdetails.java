package com.indevinfinity.cricinshots;

public class playerdetails {

    String battingStyle;
    String bio;
    String bowlingStyle;
    String didYouKnow;
    String dob;
    String firstName;
    String fullName;
    String height;
    String imageURL;
    String lastName;
    String odiDebutDate;
    long playerId;
    String playerType;
    String t20DebutDate;
    String testDebutDate;

    public  playerdetails(){

    }

    public playerdetails(String battingStyle,String bio, String bowlingStyle,String didYouKnow,String dob,String firstName,String fullName,String height,String imageURL, String lastName, String odiDebutDate, long playerId, String playerType, String t20DebutDate, String testDebutDate){

        this.battingStyle = battingStyle;
        this.bio = bio;
        this.bowlingStyle = bowlingStyle;
        this.didYouKnow = didYouKnow;
        this.dob = dob;
        this.firstName = firstName;
        this.fullName = fullName;
        this.height = height;
        this.imageURL = imageURL;
        this.lastName = lastName;
        this.odiDebutDate = odiDebutDate;
        this.playerId = playerId;
        this.playerType = playerType;
        this.t20DebutDate = t20DebutDate;
        this.testDebutDate = testDebutDate;

    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public String getBio() {
        return bio;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public String getDidYouKnow() {
        return didYouKnow;
    }

    public String getDob() {
        return dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHeight() {
        return height;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setDidYouKnow(String didYouKnow) {
        this.didYouKnow = didYouKnow;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOdiDebutDate() {
        return odiDebutDate;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getT20DebutDate() {
        return t20DebutDate;
    }

    public String getTestDebutDate() {
        return testDebutDate;
    }

    public void setOdiDebutDate(String odiDebutDate) {
        this.odiDebutDate = odiDebutDate;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setT20DebutDate(String t20DebutDate) {
        this.t20DebutDate = t20DebutDate;
    }

    public void setTestDebutDate(String testDebutDate) {
        this.testDebutDate = testDebutDate;
    }

}

