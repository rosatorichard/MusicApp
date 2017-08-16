package com.batchmates.android.musicplayer.model;

/**
 * Created by Android on 8/15/2017.
 */

public class MusicPOJO {

    String albumName;
    String songName;
    byte[] picture;
    String bandName;
    String path;

    public MusicPOJO(String albumName, String songName, byte[] picture, String bandName, String path) {
        this.albumName = albumName;
        this.songName = songName;
        this.picture = picture;
        this.bandName = bandName;
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongName() {
        return songName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getBandName() {
        return bandName;
    }
}
