package org.lessons.java.spring_hitsmoviessongs.classes;

import java.io.Serializable;

public class Movie implements Serializable{   //Serilizable x conver to to java bean!
    private int id;
    private String title;
    private String image;

    public Movie(){}   //x conversion to java bean
    public Movie(int id, String title, String image){
        this.id = id;
        this.title = title;
        this.image = image;
    }
    public int getId(){return id;}    //all methods public, x conversion to java bean
    public String getTitle(){return title;}
    public String getImage(){return image;}
    public void setId(int theid){id=theid;}
    public void setTitle(String thetitle){title=thetitle;}
    public void setImage(String theimage){image=theimage;}

    @Override  //convert correctly in str if u add a Movie instance to the model with model.addAttribute() or System.out.println(movie);
    public String toString() {
        return String.format("%s %s %s", id, title, image);
    }
}
