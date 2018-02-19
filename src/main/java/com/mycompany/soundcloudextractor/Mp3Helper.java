/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soundcloudextractor;

import com.mpatric.mp3agic.ID3v1Genres;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.mycompany.soundcloudextractor.entity.Track;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author micky
 */
public class Mp3Helper {

    /*
    public static void writeTagInfo(Track mi, File dest) throws IOException {
        try {
            File f = new File(dest, mi.getFullFileNameFormated());
            MP3File mp3file = new MP3File(f);
           // mp3file.
            ID3v2_3 tag = new ID3v2_3(mp3file.getID3v2Tag());
            tag.setSongTitle(mi.getTitle());
            tag.setLeadArtist(mi.getArtisteName());
            tag.setSongGenre(mi.getGenre());
            if (mi.isHaveAlbumName()) {
                tag.setAlbumTitle(mi.getAlbumName());
            }
            mp3file.setID3v2Tag(tag);
            TagOptionSingleton.getInstance().setOriginalSavedAfterAdjustingID3v2Padding(false);
            mp3file.save();
        } catch (TagException ex) {
            Logger.getLogger(SoundCloudExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
    public static void writeTagInfo(Track mi, File dest) throws IOException {
        try {
            File f = new File(dest, mi.getFullFileNameFormated());
            Mp3File mp3file = new Mp3File(f.getAbsolutePath());
            ID3v2 id3v1Tag;
            if (mp3file.hasId3v2Tag()) {
                id3v1Tag = mp3file.getId3v2Tag();
            } else {
                // mp3 does not have an ID3v1 tag, let's create one..
                id3v1Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v1Tag);
            }
            id3v1Tag.setArtist(mi.getArtisteName());
            id3v1Tag.setTitle(mi.getTitle());
            id3v1Tag.setAlbum(mi.getAlbumName());
            id3v1Tag.setGenre(ID3v1Genres.matchGenreDescription(mi.getGenre()));
            byte[] img = IOUtils.toByteArray(mi.getCoverURL());
            id3v1Tag.setAlbumImage(img, "mycover");            
            mp3file.save(f.getName());
        } catch (UnsupportedTagException | InvalidDataException | NotSupportedException ex) {
            Logger.getLogger(Mp3Helper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
