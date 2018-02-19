/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soundcloudextractor;

import com.mycompany.soundcloudextractor.entity.Track;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author micky
 */
public abstract class DownLoad {

    protected void prepareDownload(Track mi, File destFolder) throws IOException {
        String res = Constantes.getLinkDownload(mi);
        File dest;
        if (mi.isHaveAlbumName()) {
            dest = new File(destFolder, mi.getAlbumName());
            if (!dest.exists()) {
                dest.mkdirs();
            }
            destFolder = dest;
            File cover = new File(dest, mi.getCoverStringName());
            downloadFile(cover, mi.getCoverURL());
        }
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        dest = new File(destFolder, mi.getFullFileNameFormated());
        downloadFile(dest, res);
    }

    public static void downloadFile(File dest, String urlStr) throws IOException {
        URL url = new URL(urlStr);
        System.out.println("Dl en cours: " + urlStr);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(dest);
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(os);
    }

}
