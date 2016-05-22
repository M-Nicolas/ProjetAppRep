package common.serializableitem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nicolas on 22/05/16.
 */
public class Title implements Serializable {

    private String title;
    private ArrayList<String> performers;
    private String composer;
    private String artist;

    public Title() {
        performers = new ArrayList<>();
    }

    public Title(String title, String composer, String artist, ArrayList<String> performers) {
        this.title = title;
        this.performers = performers;
        this.composer = composer;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public String getArtist() {
        return artist;
    }

    public boolean checkPerformer(String performer) {
        if (performers.contains(performer)) {
            return true;
        } else {
            return false;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean addPerformer(String performer) {
        if (!performers.contains(performer)) {
            performers.add(performer);
            return true;
        } else {
            return false;
        }
    }

    public boolean removePerformer(String performer){
        if (performer.contains(performer)) {
            performers.remove(performer);
            return true;
        } else {
            return false;
        }
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String toString() {
        String performerlist = performers.get(0);

        for (String s: performers) {
            if (!s.equals(performers.get(0))) {
                performerlist += " - " + s;
            }
        }
        return title + "\n      composer : " + composer + ", artist : " + artist +
                "\n      performers : " + performerlist;
    }
}