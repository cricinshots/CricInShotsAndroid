package com.indevinfinity.cricinshots;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

public class MatchProvider implements Parcelable {

    ListView choose;
    String[] idarray,hlist,alist,l1_list,l2_list,hcolor,acolor,shield,hbgurl,hshortname,abgurl,ashortname,currentStat, DateTime;
    boolean[] hisbatting,aisbatting;
    String ID;
    int ln;
    String team1,team2,matchid;
    DatabaseReference myRef;
    boolean dataReady = false;
    boolean[] isLive;

    public MatchProvider(DatabaseReference Ref){
        this.myRef = Ref;
    }

    protected MatchProvider(Parcel in) {
        idarray = in.createStringArray();
        hlist = in.createStringArray();
        alist = in.createStringArray();
        l1_list = in.createStringArray();
        l2_list = in.createStringArray();
        hcolor = in.createStringArray();
        acolor = in.createStringArray();
        shield = in.createStringArray();
        hbgurl = in.createStringArray();
        hshortname = in.createStringArray();
        abgurl = in.createStringArray();
        ashortname = in.createStringArray();
        hisbatting = in.createBooleanArray();
        aisbatting = in.createBooleanArray();
        ID = in.readString();
        ln = in.readInt();
        team1 = in.readString();
        team2 = in.readString();
        matchid = in.readString();
        isLive = in.createBooleanArray();
        currentStat = in.createStringArray();
        DateTime = in.createStringArray();
    }

    public static final Creator<MatchProvider> CREATOR = new Creator<MatchProvider>() {
        @Override
        public MatchProvider createFromParcel(Parcel in) {
            return new MatchProvider(in);
        }

        @Override
        public MatchProvider[] newArray(int size) {
            return new MatchProvider[size];
        }
    };

    public void getData(){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ln = (int) dataSnapshot.getChildrenCount();
                System.out.println("Class Size: " + ln);
                isLive = new boolean[ln];
                idarray = new String[ln];
                hlist = new String[ln];
                alist = new String[ln];
                l1_list = new String[ln];
                l2_list = new String[ln];
                hcolor = new String[ln];
                acolor = new String[ln];
                shield = new String[ln];
                hbgurl = new String[ln];
                hshortname = new String[ln];
                abgurl = new String[ln];
                ashortname = new String[ln];
                hisbatting = new boolean[ln];
                aisbatting = new boolean[ln];
                currentStat = new String[ln];
                DateTime = new String[ln];
                int i=0;
                for(DataSnapshot child : dataSnapshot.getChildren() ) {
                    String sID = child.child("series").child("id").getValue().toString();
                    Team hteamobj = child.child("homeTeam").getValue(Team.class);
                    if(sID.equals("2514") && !hteamobj.getName().equals("Unknown") && !hteamobj.getName().equals("null") && !hteamobj.getName().isEmpty() && !hteamobj.getName().trim().equals("")){
                    String mID = child.child("id").getValue().toString();
                    ID = sID + "-" + mID;
                    Team ateamobj = child.child("awayTeam").getValue(Team.class);
                    isLive[i] = (boolean) child.child("isLive").getValue();
                    try {
                        shield[i] = child.child("series").child("shieldImageUrl").getValue().toString();
                    } catch (Exception j) {
                        shield[i] = "https://static.toiimg.com/photo/msid-77659752/77659752.jpg";
                    }

                    currentStat[i] = child.child("currentMatchState").getValue().toString();
                    try {
                        DateTime[i] = (String) DateUtils.getRelativeTimeSpanString((Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(Objects.requireNonNull(child.child("startDateTime").getValue()).toString()))).getTime(), System.currentTimeMillis(), MINUTE_IN_MILLIS);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //DateTime[i] =


                    hlist[i] = hteamobj.getName();
                    alist[i] = ateamobj.getName();
                    hshortname[i] = hteamobj.getShortName();
                    ashortname[i] = ateamobj.getShortName();

                    try {
                        l1_list[i] = hteamobj.getLogoUrl();
                        if (l1_list[i].equals("") || l1_list[i].equals(" "))
                            throw new NullPointerException("NULL");
                    } catch (Exception e) {
                        System.out.println("Setting 1st URL");
                        try {
                            switch (hshortname[i]) {
                                case "MI":
                                    l1_list[i] = "https://www.india.com/wp-content/uploads/2017/03/mumbai.jpg";
                                    break;
                                case "CHE":
                                    l1_list[i] = "https://i.pinimg.com/originals/85/52/f8/8552f811e95b998d9505c43a9828c6d6.jpg";
                                    break;
                                case "RAJ":
                                    l1_list[i] = "https://static.toiimg.com/photo/imgsize-234579,msid-73948862/73948862.jpg";
                                    break;
                                case "RCB":
                                    l1_list[i] = "https://i.pinimg.com/736x/11/64/a9/1164a96b085d6d60e3568ceb7be873cf.jpg";
                                    break;
                                case "KKR":
                                    l1_list[i] = "https://cdn.shopify.com/s/files/1/2658/5942/files/KKR-LOGO-WITH-BACK.jpg?height=628&pad_color=3b215d&v=1583755340&width=1200";
                                    break;
                                case "SUN":
                                    l1_list[i] = "https://timesofindia.indiatimes.com/thumb/msid-72902421,imgsize-342521,width-400,resizemode-4/72902421.jpg";
                                    break;
                                case "KXI":
                                    l1_list[i] = "https://www.insidesport.co/wp-content/uploads/2020/03/7ihiRStUn1-1.jpg";
                                    break;
                                case "DEL":
                                    l1_list[i] = "https://etimg.etb2bimg.com/photo/74074359.cms";
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception r) {
                            System.out.println("No SNAME");
                        }


                    }

                    try {
                        l2_list[i] = ateamobj.getLogoUrl();
                        if (l2_list[i].equals("") || l2_list[i].equals(" "))
                            throw new NullPointerException("NULL");
                    } catch (Exception e) {
                        System.out.println("Setting 2nd URL");
                        try {
                            switch (ashortname[i]) {
                                case "MI":
                                    l2_list[i] = "https://www.india.com/wp-content/uploads/2017/03/mumbai.jpg";
                                    break;
                                case "CHE":
                                    l2_list[i] = "https://i.pinimg.com/originals/85/52/f8/8552f811e95b998d9505c43a9828c6d6.jpg";
                                    break;
                                case "RAJ":
                                    l2_list[i] = "https://static.toiimg.com/photo/imgsize-234579,msid-73948862/73948862.jpg";
                                    break;
                                case "RCB":
                                    l2_list[i] = "https://i.pinimg.com/736x/11/64/a9/1164a96b085d6d60e3568ceb7be873cf.jpg";
                                    break;
                                case "KKR":
                                    l2_list[i] = "https://cdn.shopify.com/s/files/1/2658/5942/files/KKR-LOGO-WITH-BACK.jpg?height=628&pad_color=3b215d&v=1583755340&width=1200";
                                    break;
                                case "SUN":
                                    l2_list[i] = "https://timesofindia.indiatimes.com/thumb/msid-72902421,imgsize-342521,width-400,resizemode-4/72902421.jpg";
                                    break;
                                case "KXI":
                                    l2_list[i] = "https://www.insidesport.co/wp-content/uploads/2020/03/7ihiRStUn1-1.jpg";
                                    break;
                                case "DEL":
                                    l2_list[i] = "https://etimg.etb2bimg.com/photo/74074359.cms";
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception u) {
                            System.out.println("No SNAME");
                        }

                    }

                    try {
                        hbgurl[i] = hteamobj.getBackgroundImageUrl();
                        abgurl[i] = ateamobj.getBackgroundImageUrl();
                    } catch (Exception z) {
                        System.out.println("Data missing: No BgURL received");
                    }
                    try {
                        hcolor[i] = hteamobj.getTeamColour();
                        acolor[i] = ateamobj.getTeamColour();
                    } catch (Exception z) {
                        System.out.println("Data missing: No colour received");
                    }
                    try {
                        hisbatting[i] = hteamobj.getIsBatting();
                        aisbatting[i] = ateamobj.getIsBatting();
                    } catch (Exception z) {
                        System.out.println("Data missing: No batstat received");
                    }


                    idarray[i] = ID;
                    i++;
                }
                }
                dataReady = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(idarray);
        dest.writeStringArray(hlist);
        dest.writeStringArray(alist);
        dest.writeStringArray(l1_list);
        dest.writeStringArray(l2_list);
        dest.writeStringArray(hcolor);
        dest.writeStringArray(acolor);
        dest.writeStringArray(shield);
        dest.writeStringArray(hbgurl);
        dest.writeStringArray(hshortname);
        dest.writeStringArray(abgurl);
        dest.writeStringArray(ashortname);
        dest.writeBooleanArray(hisbatting);
        dest.writeBooleanArray(aisbatting);
        dest.writeString(ID);
        dest.writeInt(ln);
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(matchid);
        dest.writeBooleanArray(isLive);
        dest.writeStringArray(currentStat);
        dest.writeStringArray(DateTime);
    }
}
