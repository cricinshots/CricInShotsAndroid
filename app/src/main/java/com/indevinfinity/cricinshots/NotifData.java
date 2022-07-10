package com.indevinfinity.cricinshots;

public class NotifData{
    String content;
    boolean send;
    String title;

    public NotifData(){

    }

    public NotifData(String content, boolean send, String title){
        this.content = content;
        this.send = send;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean getSend() {
        return send;
    }
}