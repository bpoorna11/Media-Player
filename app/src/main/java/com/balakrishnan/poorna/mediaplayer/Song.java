package com.balakrishnan.poorna.mediaplayer;

public class Song {

    String name;
    Song(){};
    Song(String name){

        this.name=name;
    }



    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
