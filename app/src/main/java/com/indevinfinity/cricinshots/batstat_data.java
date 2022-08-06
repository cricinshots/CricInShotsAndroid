package com.indevinfinity.cricinshots;

public class batstat_data {

    String Balls;
    String Fours;
    int FowOrder;
    String HowOut;
    int Id;
    String ImageURL;
    String Name;
    String Runs;
    String Sixes;
    String StrikeRate;

    public batstat_data(String balls,String fours, int fowOrder, String howOut, int id, String imageURL, String name, String runs, String sixes, String strikeRate){

        this.Balls = balls;
        this.Fours = fours;
        this.FowOrder = fowOrder;
        this.HowOut = howOut;
        this.Id = id;
        this.ImageURL = imageURL;
        this.Name = name;
        this.Runs = runs;
        this.Sixes = sixes;
        this.StrikeRate = strikeRate;

    }


}
