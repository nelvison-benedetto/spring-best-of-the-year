package org.lessons.java.spring_hitsmoviessongs.classes;

import java.io.Serializable;

public class Movie implements Serializable{   //Serilizable x conver to to java bean!
    private int id;
    private String title;

    public Movie(){}   //x conversion to java bean
    public Movie(int id, String title){
        this.id = id;
        this.title = title;
    }
    public int getId(){return id;}    //all methods public, x conversion to java bean
    public String getTitle(){return title;}
    public void setId(int theid){id=theid;}
    public void setTitle(String thetitle){title=thetitle;}

    @Override  //convert correctly in str if u add a Movie instance to the model with model.addAttribute() or System.out.println(movie);
    public String toString() {
        return String.format("%s %s", id, title);
    }
}
