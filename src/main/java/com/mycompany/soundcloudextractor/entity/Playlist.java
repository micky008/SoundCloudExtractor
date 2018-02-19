package com.mycompany.soundcloudextractor.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author micky
 */
public class Playlist {
    private List<Sets> myListe;
    
    
    /**
     * @return the myListe
     */
    public List<Sets> getMyListe() {
        return myListe;
    }

    /**
     * @param myListe the myListe to set
     */
    public void setMyListe(List<Sets> myListe) {
        this.myListe = myListe;
    }
    
    
     public static Playlist convert(JSONArray array) {
         Playlist playList = new  Playlist();
         
         Iterator<JSONObject> it = array.iterator();
         List<Sets> myListe = new ArrayList<>();
         
         while (it.hasNext()){
             JSONObject json = it.next();             
             myListe.add(Sets.convertPlayList(json));             
         }     
         playList.setMyListe(myListe);
         return playList;
     }
}
