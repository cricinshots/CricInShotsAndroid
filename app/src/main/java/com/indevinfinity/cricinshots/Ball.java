package com.indevinfinity.cricinshots;

public class Ball {

    String angle;
    int ballId;
    int ballLegalId;
    String ballType;
    int batsmanId;
    String battingEnd;
    String bowler;
    int bowlerId;
    String distance;
    String fielder;
    int fielderId;
    String over;
    String run;

    public Ball(){

    }

    public Ball(String angle, int ballId, int ballLegalId, String ballType, int batsmanId, String battingEnd, String bowler, int bowlerId, String distance, String fielder, int fielderId, String over, String run){

        this.angle = angle;
        this.ballId = ballId;
        this.ballLegalId = ballLegalId;
        this.ballType = ballType;
        this.batsmanId = batsmanId;
        this.battingEnd = battingEnd;
        this.bowler = bowler;
        this.bowlerId = bowlerId;
        this.distance = distance;
        this.fielder = fielder;
        this.fielderId = fielderId;
        this.over = over;
        this.run = run;

    }

    public int getBallId() {
        return ballId;
    }

    public int getBallLegalId() {
        return ballLegalId;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getBallType() {
        return ballType;
    }

    public String getBattingEnd() {
        return battingEnd;
    }

    public String getBowler() {
        return bowler;
    }

    public String getDistance() {
        return distance;
    }

    public void setBallId(int ballId) {
        this.ballId = ballId;
    }

    public void setBallLegalId(int ballLegalId) {
        this.ballLegalId = ballLegalId;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

    public void setBattingEnd(String battingEnd) {
        this.battingEnd = battingEnd;
    }

    public String getFielder() {
        return fielder;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getFielderId() {
        return fielderId;
    }

    public String getOver() {
        return over;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public void setFielderId(int fielderId) {
        this.fielderId = fielderId;
    }

    public String getRun() {
        return run;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public void setRun(String run) {
        this.run = run;
    }

}
