package com.indevinfinity.cricinshots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Azure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azure);



    }
}


    /**
      releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.3'
          debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.3'
         debugImplementation 'com.squareup.leakcanary:leakcanary-support-fragment:1.6.3'

 void getMatchDetails(String b, JSONObject c, String d) {
 String[] a = {d};
 if (d.contains("/*\\")) {
 a = d.split("/\\*\\\\");
 }
 //a = (d.contains("/\\"))?d.split("/\\\\\\"):{d};
 d = getMatchDetails(b, d);
 if(d.length()==0)
 return;
 String[] e = new String[a.length];
 try {
 e[0]=(c.has(a[0]))?c.getString(a[0]):"{}";
 int i;
 for(i=1; i<a.length; i++)
 e[i]=(((new JSONObject(e[i-1])).has(a[i]))?((new JSONObject(e[i-1])).getString(a[i])):"{}");
 e[i-1]=d;
 for(i-=2; i>=0; i--)
 e[i] = new JSONObject(e[i]).put(a[i + 1], e[i + 1]).toString();
 c.put(a[0],e[0]);
 }
 catch(JSONException er) {
 return;
 }
 }

 void getMatchData_local(){

 }

 String getContentFromServer(String targetf,String requestContent)
 {
 String total="", appVersion = "1";//version; 	// Done - Put our app's versionName or versionCode in appVersion
 try {
 URL urlobj=new URL("https://codersera.tech/"+targetf);
 HttpURLConnection urlCon=(HttpURLConnection)urlobj.openConnection();
 urlCon.setRequestMethod("POST");
 urlCon.setDoOutput(true);
 Thread.sleep(100);
 if(!urlobj.getHost().equals(urlCon.getURL().getHost()))
 throw new Exception(""); 	// Internet provider expects a login.
 urlCon.setRequestProperty("User-Agent","CiSAndroid/"+appVersion);
 OutputStream os=new BufferedOutputStream(urlCon.getOutputStream());
 os.write(requestContent.getBytes());
 os.flush();
 os.close();
 if((urlCon.getResponseCode()!=HttpURLConnection.HTTP_OK)&&(urlCon.getResponseCode()!=HttpURLConnection.HTTP_NOT_MODIFIED))
 {
 FancyToast.makeText(this,"Server error occured.\nCode: "+urlCon.getResponseCode(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
 //Toast.makeText(choose_match.this,"Server error occured.\nCode: "+urlCon.getResponseCode(),Toast.LENGTH_LONG).show();
 // Data could not be obtained due to some reason, show an error screen after the above toast message, and optionally send user to Home Activity
 return total; 	// Quit the function with an empty output (if not already quit)
 }
 BufferedReader in=new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
 String line="";
 /* String line=in.readLine();
 if(line.matches("\\.\\..*\\.\\."))
 {
 Toast.makeText(choose_match.this,"Server error occured.\nMessage: "+line.substring(3,(line.length()-3)),Toast.LENGTH_LONG).show();
 return total; 	// Quit the function with an empty output (if not already quit)
 } */ // This method is no longer used to display errors in the current scenario.

          /**  while((line=in.readLine())!=null)
                    total+=line;
                    urlCon.disconnect();
                    }
                    catch(Exception e) {
                    // Internet has now disconnected, inform the user and quit the app.
                    FancyToast.makeText(this,"We are facing trouble contacting our server.\nPlease check your internet connection.",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    //Toast.makeText(choose_match.this,"We are facing trouble contacting our server.\nPlease check your internet connection.",Toast.LENGTH_LONG).show();
                    }
                    finally {
                    return total;
                    }
                    }
                    String getMatchDetails(String b, String d) {
                    return getContentFromServer("cricinshort/forapp/mproc/index2.php","op=3&in="+ Uri.encode(b,"US-ASCII")+"&in1="+ Uri.encode(d,"US-ASCII"));
                    }

 **/

          /**    String getContentFromServer(String targetf,String requestContent)
           {
           String total="", appVersion=version; 	// Done - Put our app's versionName or versionCode in appVersion
           try {
           URL urlobj=new URL("https://codersera.tech/"+targetf);
           HttpURLConnection urlCon=(HttpURLConnection)urlobj.openConnection();
           urlCon.setRequestMethod("POST");
           urlCon.setDoOutput(true);
           Thread.sleep(100);
           if(!urlobj.getHost().equals(urlCon.getURL().getHost()))
           throw new Exception(""); 	// Internet provider expects a login.
           urlCon.setRequestProperty("User-Agent","CiSAndroid/"+appVersion);
           OutputStream os=new BufferedOutputStream(urlCon.getOutputStream());
           os.write(requestContent.getBytes());
           os.flush();
           os.close();
           if((urlCon.getResponseCode()!=HttpURLConnection.HTTP_OK)&&(urlCon.getResponseCode()!=HttpURLConnection.HTTP_NOT_MODIFIED))
           {
           FancyToast.makeText(this,"Server error occured.\nCode: "+urlCon.getResponseCode(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
           //Toast.makeText(choose_match.this,"Server error occured.\nCode: "+urlCon.getResponseCode(),Toast.LENGTH_LONG).show();
           // Data could not be obtained due to some reason, show an error screen after the above toast message, and optionally send user to Home Activity
           return total; 	// Quit the function with an empty output (if not already quit)
           }
           BufferedReader in=new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
           String line="";
           /* String line=in.readLine();
           if(line.matches("\\.\\..*\\.\\."))
           {
           Toast.makeText(choose_match.this,"Server error occured.\nMessage: "+line.substring(3,(line.length()-3)),Toast.LENGTH_LONG).show();
           return total; 	// Quit the function with an empty output (if not already quit)
           } */ // This method is no longer used to display errors in the current scenario.

           /** while((line=in.readLine())!=null)
                    total+=line;
                    urlCon.disconnect();
                    }
                    catch(Exception e) {
                    // Internet has now disconnected, inform the user and quit the app.
                    FancyToast.makeText(this,"We are facing trouble contacting our server.\nPlease check your internet connection.",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    //Toast.makeText(choose_match.this,"We are facing trouble contacting our server.\nPlease check your internet connection.",Toast.LENGTH_LONG).show();
                    }
                    finally {

                    return total;
                    }
                    }**/

                    /**String[] getCurrentMatches()
                     {
                     String[] finalArr={""};
                     final String total=getContentFromServer("cricinshort/forapp/mproc/index.php","op=0");

                     try {
                     JSONArray c_input=new JSONArray(total);
                     finalArr=new String[c_input.length()];
                     for(int i=0;i<c_input.length();i++)
                     finalArr[i]=c_input.getString(i);
                     }
                     catch(Exception e) {
                     Toast.makeText(choose_match.this,"Server output is not in expected form.",Toast.LENGTH_LONG).show();

                     // Show "Feature temporarily unavailable / Server under maintenance" and redirect user to previous activity or Home Activity.
                     //Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                     }

                     return finalArr; 	// Quit the function with an output (empty if an error occurred)
                     }**/

                   /** String[][] getCurrentMatches()
                    {
                    String finalArr[][]={{"",""}}, total=getContentFromServer("cricinshort/forapp/mproc/index.php","op=1");

                    try {
                    JSONArray c_input=new JSONArray(total);
                    finalArr=new String[c_input.length()][5];
                    //finalArr[0][0]=c_input.length()+"";
                    //finalArr[0][1]="";
                    //finalArr[0][2]="";
                    for(int i=0;i<=c_input.length();i++)
                    {
                    JSONObject j=new JSONObject(c_input.getString(i));
                    finalArr[i][0]=j.getString("id");
                    finalArr[i][1]=j.getString("sname");
                    finalArr[i][2]=j.getString("venue");
                    finalArr[i][3]=j.getString("team1");
                    finalArr[i][4]=j.getString("team2");
                    }
                    }
                    catch(Exception e) {
                    FancyToast.makeText(this,"Server output is not in expected form.",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    //Toast.makeText(choose_match.this,"Server output is not in expected form.",Toast.LENGTH_LONG).show();
                    }
                    return finalArr; 	// Quit the function with an output (empty if an error occurred)
                    }**/

                   /**
                    * new Thread(new Runnable() {
                    *                     public void run(){
                    *                     Looper.prepare();
                    *
                    *                     Looper.loop();
                    *
                    *
                    *                     }).start();
                    new Thread(new Runnable() {
                    public void run(){
                    Looper.prepare();
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    get_Cmatches = getCurrentMatches();
                    current_matches = new String[get_Cmatches.length];
                    for(int i=0;i<get_Cmatches.length;i++) {
                    current_matches[i]=get_Cmatches[i][1] + "\n" + get_Cmatches[i][3] + " vs " + get_Cmatches[i][4];
                    }
                    matches.addAll(Arrays.asList(current_matches));
                    choose_match.this.runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                   list();

                   }
                   });
                    Looper.loop();

                    // progressDialog.dismiss();
                    }
                    }).start();**/