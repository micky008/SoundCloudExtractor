/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soundcloudextractor;

import com.mycompany.soundcloudextractor.entity.Track;

/**
 *
 * @author micky
 */
public final class Constantes {

    private static final String CLIENT_ID = "";
    private static final String LINK_RESOLVE = "http://api.soundcloud.com/resolve?url=:url&client_id=" + CLIENT_ID;
    private static final String LINK_TRACK_STREAM = "https://api.soundcloud.com/tracks/:trackNumber/stream?client_id=" + CLIENT_ID;
    private static final String LINK_TRACK_DOWNLOAD = "https://api.soundcloud.com/tracks/:trackNumber/download?client_id=" + CLIENT_ID;
    private static final String LINK_TRACK_INFO = "https://api.soundcloud.com/tracks/:trackNumber?client_id=" + CLIENT_ID;

    private Constantes() {
    }
    
    public static String getLinkResolve(String url){
        return LINK_RESOLVE.replace(":url", url);
    }
       
    public static String getLinkDownload(Track track){
        if (track.isDownloadable()){
            return LINK_TRACK_DOWNLOAD.replace(":trackNumber", track.getId());
        }
        return LINK_TRACK_STREAM.replace(":trackNumber", track.getId());
    }
    public static String getLinkInfo(Track track){
        return LINK_TRACK_INFO.replace(":trackNumber", track.getId());
    }
    
    
    
}
