/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soundcloudextractor.entity;

import com.mycompany.soundcloudextractor.DownLoad;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author micky
 */
public class Sets {

    private String coverAlbumURL;
    private String titleAlbum;
    private List<Track> tracks;

    /**
     * @return the tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * @return the coverAlbum
     */
    public String getCoverAlbumURL() {
        return coverAlbumURL;
    }

    /**
     * @param coverAlbum the coverAlbum to set
     */
    public void setCoverAlbumURL(String coverAlbum) {
        this.coverAlbumURL = coverAlbum;
    }

    public static Sets convertSets(JSONArray array, String albumName) {
        List<Track> tracks = new ArrayList<>();
        Sets set = new Sets();
        Iterator<JSONObject> it = array.iterator();
        Track track;

        while (it.hasNext()) {
            JSONObject json = it.next();
            track = Track.convert(json);
            track.setAlbumName(albumName);
            tracks.add(track);
        }
        set.setTracks(tracks);
        return set;
    }

    public static Sets convertPlayList(JSONObject obj) {

        String titleAlbum = obj.get("title").toString();
        String coverAlbumURL = obj.get("artwork_url").toString();
        JSONArray array = (JSONArray) obj.get("tracks");

        Sets set = convertSets(array, titleAlbum);

        set.coverAlbumURL = coverAlbumURL;
        set.titleAlbum = titleAlbum;

        return set;
    }

    public void download(File destFolder) throws IOException {
        if (titleAlbum != null && !titleAlbum.isEmpty()) {
            destFolder = new File(destFolder, titleAlbum);
        }
        if (coverAlbumURL != null && !coverAlbumURL.isEmpty()) {
            DownLoad.downloadFile(destFolder, coverAlbumURL);
        }
        for (Track track : tracks) {
            track.download(destFolder);
        }
    }

}
