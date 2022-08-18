package com.indevinfinity.cricinshots;

public class batstat {

    String balls;
    String fours;
    int fowOrder;
    String howOut;
    int id;
    String imageURL;
    String name;
    String runs;
    String sixes;
    String strikeRate;

    public batstat(){

    }

    public batstat(String balls,String fours, int fowOrder, String howOut, int id, String imageURL, String name, String runs, String sixes, String strikeRate){

        this.balls = balls;
        this.fours = fours;
        this.fowOrder = fowOrder;
        this.howOut = howOut;
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.runs = runs;
        this.sixes = sixes;
        this.strikeRate = strikeRate;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getBalls() {
        return balls;
    }

    public String getFours() {
        return fours;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getRuns() {
        return runs;
    }

    public int getFowOrder() {
        return fowOrder;
    }

    public String getHowOut() {
        return howOut;
    }

    public String getName() {
        return name;
    }

    public String getSixes() {
        return sixes;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public void setFowOrder(int fowOrder) {
        this.fowOrder = fowOrder;
    }

    public void setHowOut(String howOut) {
        this.howOut = howOut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setSixes(String sixes) {
        this.sixes = sixes;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

}
