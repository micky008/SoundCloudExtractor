/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soundcloudextractor.entity;

import com.mycompany.soundcloudextractor.DownLoad;
import com.mycompany.soundcloudextractor.Mp3Helper;
import java.io.File;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author micky
 */
public class Track extends DownLoad {

    private String id;
    private String genre;
    private String title;
    private String coverURL;
    private String coverStringName;
    private String artisteName;
    private String format;
    private boolean isDownloadable;
    private String albumName;

    public String getFullFileName() {
        return getArtisteName() + " - " + getTitle();
    }

    public String getFullFileNameFormated() {
        return getFullFileName() + "." + getFormat();
    }

    /**
     * @return the trackNumber
     */
    public String getId() {
        return id;
    }

    /**
     * @param trackNumber the trackNumber to set
     */
    public void setId(String trackNumber) {
        this.id = trackNumber;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the userName
     */
    public String getArtisteName() {
        return artisteName;
    }

    /**
     * @param userName the userName to set
     */
    public void setArtisteName(String userName) {
        this.artisteName = userName;
    }

    /**
     * @return the coverMp3URL
     */
    public String getCoverURL() {
        return coverURL;
    }

    /**
     * @param coverMp3URL the coverMp3URL to set
     */
    public void setCoverURL(String coverMp3URL) {
        this.coverURL = coverMp3URL;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return the isDownloadable
     */
    public boolean isDownloadable() {
        return isDownloadable;
    }

    /**
     * @param isDownloadable the isDownloadable to set
     */
    public void setIsDownloadable(boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    /**
     * @return the haveAlbumName
     */
    public boolean isHaveAlbumName() {
        return (albumName != null && !albumName.isEmpty());
    }

    /**
     * @return the albumName
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * @param albumName the albumName to set
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * @return the coverStringName
     */
    public String getCoverStringName() {
        return coverStringName;
    }

    /**
     * @param coverStringName the coverStringName to set
     */
    public void setCoverStringName(String coverStringName) {
        this.coverStringName = coverStringName;
    }

    public static Track convert(JSONObject track) {
        String genre = track.get("genre").toString();
        String title = track.get("title").toString().replace(":", "");
        //  Boolean isDwn = (Boolean) track.get("downloadable"); //pour le moment on enleve. on force le stream 
        JSONObject user = (JSONObject) track.get("user");
        String userName = user.get("username").toString();
        String format = "mp3";//json.get("original_format").toString().toLowerCase();
        //"artwork_url":"https://i1.sndcdn.com/artworks-000037688615-gtv5r5-large.jpg?xxxyyy..."
        String artworkUrl = track.get("artwork_url").toString();
        int pos = artworkUrl.lastIndexOf('-') + 1;
        int pos2 = artworkUrl.lastIndexOf('.') + 1;
        String temp = artworkUrl.substring(0, pos); //https://i1.sndcdn.com/artworks-000037688615-gtv5r5-
        temp += "t500x500.";
        temp += artworkUrl.substring(pos2);//jpg?xxxyyy...
        String trkNum = track.get("id").toString();

        int lastSlash = artworkUrl.substring(0, pos).lastIndexOf("/") + 1;

        Track mi = new Track();
        mi.genre = genre;
        mi.title = title;
        mi.artisteName = userName;
        mi.isDownloadable = false;
        mi.coverURL = temp;
        mi.setCoverStringName(artworkUrl.substring(lastSlash));
        mi.format = format;
        mi.id = trkNum;
        return mi;
    }

    public void download(File destFolder) throws IOException {
        super.prepareDownload(this, destFolder);
        try {
            Mp3Helper.writeTagInfo(this, destFolder);
        } catch (IOException e) {
            System.out.println("ecriture sur le mp3 " + this.title + " impossible car:");
            System.out.println(e.getMessage());
        }
    }

}
