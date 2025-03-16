package org.lessons.java.spring_hitsmoviessongs.classes;

import java.io.Serializable;

public class Song implements Serializable{   //Serilizable x conver to to java bean!
    private int id;
    private String title;
    private String author;
    private String src;

    public Song(){}   //x conversion to java bean
    public Song(int id, String title, String author,String src){
        this.id = id;
        this.title = title;
        this.author = author;
        this.src = src;
    }

    public int getId(){return id;}    //all methods public, x conversion to java bean
    public String getTitle(){return title;}
    public String getSrc(){return src;}
    public String getAuthor(){return author;}
    public void setId(int theid){id=theid;}
    public void setTitle(String thetitle){title=thetitle;}
    public void setAuthor(String theauthor){author=theauthor;}
    public void setSrc(String thesrc){src=thesrc;}

    @Override  //convert correctly in str if u add a Song instance to the model with model.addAttribute()
    public String toString() {
        return String.format("%s %s %s %s", id, title, author, src);
    }
}
