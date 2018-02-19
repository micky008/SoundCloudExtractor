package com.mycompany.soundcloudextractor;

import com.mycompany.soundcloudextractor.entity.Track;
import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author mchinchole
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        File dest = new File("C:\\tmp");
        Resolve resolve = new Resolve();
        // resolve.setUrl("https://soundcloud.com/trevorsomething/sets/soulless-computer-boy-and-the");
       // resolve.setUrl("https://soundcloud.com/speed-machine/tracks");
       resolve.setUrl("https://soundcloud.com/aljoshakonstanty/discovery");
       
        //resolve.setUrl("http://soundcloud.com/dancewiththedead/we-will-rock-you-dance-with-the-dead-remix");
        Track t = resolve.getTitre();
        t.download(dest);
        //Sets set = resolve.getAllTitres();
        //set.download(dest);
        
        
        
    }

}

//       try {
//            SoundCloudExtractor sce = new SoundCloudExtractor();
//            sce.getAlltracks("https://soundcloud.com/dancewiththedead/master-of-puppets-remix-free-download",null,"all soundcloud");
//            
//	Options options = new Options();
//
//	Option lien = new Option("1", "lien", true, "mettre 1 lien");
//	Option liens = new Option("t", "listeText", true, "mettre 1 fichier texte avec comme contenue lien<retour a la ligne>lien");
//	Option liensCsv = new Option("c", "listeCSV", true, "mettre des liens separer par des #");
//	Option liensPlayList = new Option("p", "playlist", true, "mettre des liens separer par des #");
//
//	Option createFolder = new Option("a", "albumName", true, "dois-je crée un folder au nom de l'artiste ?");
//
//	Option fileName = new Option("f", "destFolder", true, "le folder de destination [ex: /home/toto/Music]");
//	fileName.setRequired(true);
//
//	Option help = new Option("h", "help", false, "Affiche cette Aide");
//
//	OptionGroup op = new OptionGroup();
//	op.addOption(lien);
//	op.addOption(liens);
//	op.addOption(liensCsv);
//	op.addOption(liensPlayList);
//	op.setRequired(true);
//
//	OptionGroup opHelp = new OptionGroup();
//	op.addOption(help);
//
//	options.addOptionGroup(opHelp);
//	options.addOptionGroup(op);
//	options.addOption(fileName);
//	options.addOption(createFolder);
//
//	if (args.length == 0) {
//	    HelpFormatter formatter = new HelpFormatter();
//	    formatter.printHelp("Sound Cloud Extractor [-a albumName], -1 link | t txtFile.txt | c lien1#lien2#..., [help], -f /home/zik", options);
//	    return;
//	}
//
//	DefaultParser dp = new DefaultParser();
//	CommandLine cl = dp.parse(options, args);
//
//	if (cl.hasOption("help")) {
//	    HelpFormatter formatter = new HelpFormatter();
//	    formatter.printHelp("Sound Cloud Extractor [-a albumName], -1 link | t txtFile.txt | c lien1#lien2#..., [help], -f /home/zik", options);
//	    return;
//	}
//	SoundCloudExtractor sce = new SoundCloudExtractor();
//	String albumName = null;
//	if (cl.hasOption("a")) {
//	    albumName = cl.getOptionValue("a");
//	}
//	String folder = cl.getOptionValue("f");
//	if (cl.hasOption("1")) {
//	    System.out.printf("Lien en cours: %s : 1/1\n", cl.getOptionValue("1"));
//	    sce.download(cl.getOptionValue("1"), folder, albumName);
//	}
//	if (cl.hasOption("t")) {
//	    String txtFile = cl.getOptionValue("t");
//	    List<String> ls = IOUtils.readLines(new FileReader(txtFile));
//	    int last = ls.size();
//	    int pos = 1;
//	    for (String lienStr : ls) {
//		if (lienStr.isEmpty()) {
//		    continue;
//		}
//		System.out.printf("Lien en cours: %s : %d/%d\n", lienStr, pos, last);
//		sce.download(lienStr, folder, albumName);
//		pos++;
//	    }
//	}
//	if (cl.hasOption("c")) {
//	    String csv = cl.getOptionValue("c");
//	    String[] liensCsvs = csv.split("#");
//	    int last = liensCsvs.length;
//	    int pos = 1;
//	    for (String lienStr : liensCsvs) {
//		System.out.printf("Lien en cours: %s : %d/%d\n", lienStr, pos, last);
//		sce.download(lienStr, folder, albumName);
//		pos++;
//	    }
//	}
//	if (cl.hasOption("p")) {
//
//	}
//        sce.download("https://soundcloud.com/carpenter_brut/escape-from-midwich-valley-ost", "e:\\temp", "");
//        } catch (org.json.simple.parser.ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//}
