package com.mycompany.soundcloudextractor;

import com.mycompany.soundcloudextractor.entity.Playlist;
import com.mycompany.soundcloudextractor.entity.Track;
import com.mycompany.soundcloudextractor.entity.Sets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author micky
 */
public class Resolve {

    private String urlStr;

    private Object resolveUrl() {
        try {
            JSONParser parser = new JSONParser();
            URL url = new URL(Constantes.getLinkResolve(urlStr));
            String jsonStr = IOUtils.toString(url.openStream());
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            String newLien = json.get("location").toString();
            URL url2 = new URL(newLien);
            Object o = parser.parse(IOUtils.toString(url2.openStream()));
            return o;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Resolve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Resolve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Track getTitre() {
        JSONObject json = (JSONObject) resolveUrl();
        if (json == null) {
            return null;
        }
        return Track.convert(json);
    }

    public Sets getAlbum(String albumName) {
        JSONObject job = (JSONObject) resolveUrl();
        JSONArray json = (JSONArray) job.get("tracks");
        if (json == null) {
            return null;
        }
        return Sets.convertSets(json, albumName);
    }

    public Sets getAllTitres() {
        JSONArray json = (JSONArray) resolveUrl();
        if (json == null) {
            return null;
        }
        return Sets.convertSets(json, null);
    }

       

    public Playlist getPlayList() {
        JSONArray json = (JSONArray) resolveUrl();
        if (json == null) {
            return null;
        }
        return Playlist.convert(json);
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return urlStr;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.urlStr = url;
    }

}
