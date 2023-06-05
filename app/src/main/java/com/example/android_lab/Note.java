package com.example.android_lab;

public class Note {
    private String fontSize;
    private String text;

    public String getFontSize(){
        return this.fontSize;
    }
    public String getText(){
        return this.text;
    }
    public void setFontSize(String fs){
        this.fontSize=fs;
    }
    public void setText(String t){
        this.text=t;
    }

    public Note(String fs, String t){
        this.fontSize=fs;
        this.text=t;
    }
    @Override
    public String toString(){
        return fontSize+" "+text.trim();
    }
    public Note getFromString(String str){
        String []t;
        t = str.split(" ",2);
        Note note = new Note (t[0].toString().trim(),t[1].toString().trim());
        return note;
    }
}
