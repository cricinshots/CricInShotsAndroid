package com.indevinfinity.cricinshots;

public class Team{
    boolean isBatting;
    String logoUrl;
    String backgroundImageUrl;
    int id;
    String name;
    String shortName;
    String teamColour;

    public Team(){

    }

    public Team(String backgroundImageUrl, int id,boolean isBatting, String logoUrl, String name, String shortName, String teamColour){
        this.backgroundImageUrl = backgroundImageUrl;
        this.id = id;
        this.isBatting = isBatting;
        this.logoUrl = logoUrl;
        this.name = name;
        this.shortName = shortName;
        this.teamColour = teamColour;
    }

    public boolean getIsBatting() {
        return isBatting;
    }
    public void setIsBatting(boolean isBatting) {
        this.isBatting = isBatting;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }
    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamColour(){
        return teamColour;
    }

    public void setTeamColour(String teamColour){
        this.teamColour = teamColour;
    }
}
