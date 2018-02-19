package com.mycompany.soundcloudextractor;
// a delete mais je garde pour le fun...

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Iterator;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.LineIterator;
//import org.farng.mp3.MP3File;
//import org.farng.mp3.TagException;
//import org.farng.mp3.id3.ID3v2_3;
//import org.farng.mp3.TagOptionSingleton;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//
///**
// * todo a faire une interface pour faire genre API si j'arrive a piquer un autre
// * flux. le liens et de format :
// * https://soundcloud.com/carpenter_brut/escape-from-midwich-valley-ost
// * https://soundcloud.com/metropolisrecords/kids-are-evil/s-ULwYE s-ULwYE =
// * secret key
// * https://soundcloud.com/metropolisrecords/sets/electric-six-bitch-dont-let-me-die/s-kHEKY
// *
// * @author mchinchole
// */
public class SoundCloudExtractor {
//
//    private final static String LINK_STREAM = "https://api.soundcloud.com/tracks/:trackNumber/stream?client_id=3303b58e8d5c99f9040eb8cefa204614";
//    private final static String LINK_DOWNLOAD = "https://api.soundcloud.com/tracks/:trackNumber/download?client_id=3303b58e8d5c99f9040eb8cefa204614";
//    private final static String LINK_INFO = "https://api.soundcloud.com/tracks/:trackNumber?client_id=3303b58e8d5c99f9040eb8cefa204614";
//
//    private static final String LINK_RESOLVE = "http://api.soundcloud.com/resolve?url=:url&client_id=3303b58e8d5c99f9040eb8cefa204614" ;
////    
////    private final Pattern pat_playlist = Pattern.compile("android-app://com.soundcloud.android/soundcloud/playlists:([0-9]+)");
////    private final Pattern pat = Pattern.compile("android-app://com.soundcloud.android/soundcloud/sounds:([0-9]+)");
//
//    private static boolean alreadyDownloadCover = false;
//
//    /**
//     *
//     * @param lien le lien soundcloud de la chanson
//     * @param destFolder la ou serra crée le fichier
//     * @param albumName si != de null ou vide je crée le repertoire avec ce nom.
//     * @throws MalformedURLException
//     * @throws IOException
//     */
//    public void download(String lien, String destFolder, String albumName) throws MalformedURLException, IOException {
//        URL url = new URL(lien);
//        URLConnection con = url.openConnection();
//        LineIterator li = IOUtils.lineIterator(con.getInputStream(), "UTF-8");
//        String line;
//        Matcher mat;
//        String trkNum;
//        File dest = new File(destFolder);
//        while (li.hasNext()) {
//            line = li.next();
//            mat = pat.matcher(line);
//            if (mat.find()) {
//                trkNum = mat.group(1);
//                Track mi = getTrackInfo(trkNum);
//                dest = new File(dest, mi.userName);
//                if (albumName != null && !albumName.isEmpty()) {
//                    dest = new File(dest, albumName);
//                    mi.haveAlbumName = true;
//                }
//                if (!dest.exists()) {
//                    dest.mkdirs();
//                }
//                preparDownload(mi, dest);
//                writeTagInfo(mi, dest);
//                return;
//            }
//        }
//        System.out.println("");
//    }
//
//    private void realDownload(File dest, String urlStr) throws IOException {
//        URL url = new URL(urlStr);
//        InputStream is = url.openStream();
//        OutputStream os = new FileOutputStream(dest);
//        IOUtils.copy(is, os);
//        IOUtils.closeQuietly(os);
//    }
//
//    private void preparDownload(Track mi, File destFolder) throws IOException {
//        String res;
//        Constantes.
//        if (mi.isDownloadable) {
//            res = LINK_DOWNLOAD.replace(":trackNumber", mi.trackNumber);
//        } else {
//            res = LINK_STREAM.replace(":trackNumber", mi.trackNumber);
//        }
//        File dest = new File(destFolder, mi.getFullFileNameFormated());
//        realDownload(dest, res);
//        if (mi.haveAlbumName && !alreadyDownloadCover) {
//            dest = new File(destFolder, mi.getCoverAlbumName());
//            if (!dest.exists()) {
//                realDownload(dest, mi.coverDiskURL);
//            }
//            alreadyDownloadCover = true;
//        }
//    }
//
//    private Track getTrackInfo(String trkNum) throws IOException {
//        try {
//            //todo json
//            String res = LINK_INFO.replace(":trackNumber", trkNum);
//            URL url = new URL(res);
//            String jsonStr = IOUtils.toString(url.openStream());
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(jsonStr);
//            String genre = json.get("genre").toString();
//            String title = json.get("title").toString();
//            Boolean isDwn = (Boolean) json.get("downloadable");
//            JSONObject user = (JSONObject) json.get("user");
//            String userName = user.get("username").toString();
//            String uriUser = user.get("uri").toString();
//            String format = "mp3";//json.get("original_format").toString().toLowerCase();
//            //"artwork_url":"https://i1.sndcdn.com/artworks-000037688615-gtv5r5-large.jpg?xxxyyy..."
//            String artworkUrl = json.get("artwork_url").toString();
//            int pos = artworkUrl.lastIndexOf('-') + 1;
//            int pos2 = artworkUrl.lastIndexOf('.') + 1;
//            String temp = artworkUrl.substring(0, pos); //https://i1.sndcdn.com/artworks-000037688615-gtv5r5-
//            temp += "t500x500.";
//            temp += artworkUrl.substring(pos2);//jpg?xxxyyy...
//
//            Track mi = new Track();
//            mi.genre = genre;
//            mi.title = title;
//            mi.userName = userName;
//            mi.isDownloadable = isDwn;
//            mi.urlUsername = uriUser;
//            mi.coverMp3URL = artworkUrl;
//            mi.coverDiskURL = temp;
//            mi.format = format;
//            mi.trackNumber = trkNum;
//            return mi;
//
//        } catch (ParseException ex) {
//            Logger.getLogger(SoundCloudExtractor.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    private void writeTagInfo(Track mi, File dest) throws IOException {
//        try {
//            File f = new File(dest, mi.getFullFileNameFormated());
//            MP3File mp3file = new MP3File(f);
//            ID3v2_3 tag = new ID3v2_3(mp3file.getID3v2Tag());
//            tag.setSongTitle(mi.title);
//            tag.setLeadArtist(mi.userName);
//            tag.setSongGenre(mi.genre);
//            tag.setAlbumTitle(mi.getAlbumName());
//            mp3file.setID3v2Tag(tag);
//            TagOptionSingleton.getInstance().setOriginalSavedAfterAdjustingID3v2Padding(false);
//            mp3file.save();
//        } catch (TagException ex) {
//            Logger.getLogger(SoundCloudExtractor.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void tranformPlayList(String lien, String destFolder, String albumName) {
//        //uty
//    }
//
//    public void getAlltracks(String lien, String folderDest, String albumName) throws IOException, ParseException {
//       //todo json
//             JSONParser parser = new JSONParser();
//             String res = LINK_RESOLVE.replace(":url", lien);
//            
//            URL url = new URL(res);            
//            String jsonStr = IOUtils.toString(url.openStream());           
//            JSONObject json = (JSONObject) parser.parse(jsonStr);
//            String newLien = json.get("location").toString();
//            
//            URL url2 = new URL(newLien);
//            String jsonStr2 = IOUtils.toString(url2.openStream());
//            JSONArray jsonArray = (JSONArray) parser.parse(jsonStr2);
//           Iterator it = jsonArray.iterator();
//           String trkNum;
//           File destNormal = new File( "C:\\tmp");
//           File dest;
//           JSONObject o;
//            Track mi;
//            String uriNumber ;
//            Pattern myPat = Pattern.compile("https://api.soundcloud.com/tracks/([0-9]+)");
//            
//            Matcher mat;
//           while (it.hasNext()){
//               o = (JSONObject)it.next();
//               uriNumber = o.get("uri").toString();
//              mat =  myPat.matcher(uriNumber);
//              mat.find();
//              uriNumber = mat.group(1);
//                mi = getTrackInfo(uriNumber);
//                dest = new File(destNormal, mi.userName);
//                if (albumName != null && !albumName.isEmpty()) {
//                    dest = new File(dest, albumName);
//                    mi.haveAlbumName = true;
//                }
//                if (!dest.exists()) {
//                    dest.mkdirs();
//                }
//             preparDownload(mi, dest);
//                writeTagInfo(mi, dest);
//           }
//           // Object uris = jsonArray.get("uri");
//            
//    }
//    
//    
//
//
}
