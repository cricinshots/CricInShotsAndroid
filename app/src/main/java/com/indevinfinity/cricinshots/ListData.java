package com.indevinfinity.cricinshots;

public class ListData {
    String Hname;
    String Aname;
    String Link1;
    String Link2;
    String Matchid;
    String Shield;
    String Hcol;
    String Acol;
    int Counter;
    String Hbgurl;
    String Abgurl;
    String Hsname;
    String Asname;
    boolean Hisbat;
    boolean Aisbat;
    boolean IsLive;
    String CurrentState;
    String StartDate;
    public ListData(String hname, String aname, String link1, String link2, String matchid, String shield, String hcol, String acol,int counter,String hbgurl,String abgurl, String hsname, String asname, boolean hisbat, boolean aisbat, boolean islive,String currentstate, String startdate) {
        this.Hname = hname;
        this.Aname = aname;
        this.Link1 = link1;
        this.Link2 = link2;
        this.Matchid = matchid;
        this.Shield = shield;
        this.Hcol = hcol;
        this.Acol = acol;
        this.Counter = counter;
        this.Hbgurl = hbgurl;
        this.Abgurl = abgurl;
        this.Hsname = hsname;
        this.Asname = asname;
        this.Hisbat = hisbat;
        this.Aisbat = aisbat;
        this.IsLive = islive;
        this.CurrentState = currentstate;
        this.StartDate = startdate;
    }
}