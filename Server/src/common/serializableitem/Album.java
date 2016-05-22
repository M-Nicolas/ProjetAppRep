package common.serializableitem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nicolas on 22/05/16.
 */
public class Album implements Serializable {

    private String artist;
    private String album;
    private String year;
    private ArrayList<Title> titleList;
    private String style;

    public Album() {
        titleList = new ArrayList<>();
    }

    public Album(String artist, String album, String year, String style, ArrayList<Title> titleList) {
        this.artist = artist;
        this.album = album;
        this.style = style;
        this.year = year;
        this.titleList = titleList;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Title getTitleNo(int i) {
        if (i < titleList.size() - 1 && i >= 0) {
            return titleList.get(i);
        } else {
            return null;
        }
    }

    public boolean addTitleNo(int i, Title title) {
        if (i < titleList.size() && i >= 0) {
            titleList.add(i, title);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTitleNo(int i) {
        if (i < titleList.size() - 1 && i >= 0) {
            titleList.remove(i);
            return true;
        } else {
            return false;
        }
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String toString() {
        String tracklist = "";

        for (Title t: titleList) {
            tracklist += "    " + titleList.indexOf(t) + ". " + t;
        }

        return "#######################\n" +
                "  Artist : " + artist + "\n" +
                "  Album : " + album + "\n" +
                "    Style : " + style + "\n" +
                "    Year : " + year + "\n" +
                "    Tracklist :\n" + tracklist;
    }
}
