package com.indevinfinity.cricinshots;

public class commentarydetails {

    String ballType;
    int batsmanId;
    String batsmanImageURL;
    String batsmanName;
    int battingTeamScore;
    int bowlerId;
    String bowlerImageURL;
    String bowlerName;
    String dateTime;
    int id;
    boolean isFallOfWicket;
    int offStrikeBatsmanId;
    String runs;
    String text;
    int wickets;

    public commentarydetails() {

    }

    public commentarydetails(String ballType,
                             int batsmanId,
                             String batsmanImageURL,
                             String batsmanName,
                             int battingTeamScore,
                             int bowlerId,
                             String bowlerImageURL,
                             String bowlerName,
                             String dateTime,
                             int id,
                             boolean isFallOfWicket,
                             int offStrikeBatsmanId,
                             String runs,
                             String text,
                             int wickets) {

        this.ballType = ballType;
        this.batsmanId = batsmanId;
        this.batsmanImageURL = batsmanImageURL;
        this.batsmanName = batsmanName;
        this.battingTeamScore = battingTeamScore;
        this.bowlerId = bowlerId;
        this.bowlerImageURL = bowlerImageURL;
        this.bowlerName = bowlerName;
        this.dateTime = dateTime;
        this.id = id;
        this.isFallOfWicket = isFallOfWicket;
        this.offStrikeBatsmanId = offStrikeBatsmanId;
        this.runs = runs;
        this.text = text;
        this.wickets = wickets;

    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
    }

    public String getBallType() {
        return ballType;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public int getBattingTeamScore() {
        return battingTeamScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatsmanImageURL() {
        return batsmanImageURL;
    }

    public String getBatsmanName() {
        return batsmanName;
    }

    public String getBowlerImageURL() {
        return bowlerImageURL;
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setBatsmanImageURL(String batsmanImageURL) {
        this.batsmanImageURL = batsmanImageURL;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public void setBattingTeamScore(int battingTeamScore) {
        this.battingTeamScore = battingTeamScore;
    }

    public void setBowlerImageURL(String bowlerImageURL) {
        this.bowlerImageURL = bowlerImageURL;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public boolean getIsFallOfWicket() {
        return isFallOfWicket;
    }

    public void setIsFallOfWicket(boolean isFallOfWicket){
        this.isFallOfWicket = isFallOfWicket;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getOffStrikeBatsmanId() {
        return offStrikeBatsmanId;
    }

    public int getWickets() {
        return wickets;
    }

    public String getRuns() {
        return runs;
    }

    public String getText() {
        return text;
    }

    public void setFallOfWicket(boolean fallOfWicket) {
        isFallOfWicket = fallOfWicket;
    }

    public void setOffStrikeBatsmanId(int offStrikeBatsmanId) {
        this.offStrikeBatsmanId = offStrikeBatsmanId;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

}
