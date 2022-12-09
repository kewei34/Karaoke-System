package data;

// Author : Chong Ke Wei

public class Song implements Comparable<Song> {

    private String songName;
    private Singer singerDetail = new Singer();

    public Song() {
        this.songName = "";
    }

    public Song(String songName) {
        this.songName = songName;
    }

    public Song(String songName, Singer singerDetail) {
        this.songName = songName;
        this.singerDetail.setSingerName(singerDetail.getSingerName());
        this.singerDetail.setSingerGender(singerDetail.getSingerGender());
    }

    public String getSongName() {
        return songName;
    }
    
    public Singer getSingerDetail() {
        return this.singerDetail;
    }

    public String getSingerName() {
        return singerDetail.getSingerName();
    }

    public char getSingerGender() {
        return singerDetail.getSingerGender();
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSingerDetail(Singer singerDetail) {
        this.singerDetail.setSingerName(singerDetail.getSingerName());
        this.singerDetail.setSingerGender(singerDetail.getSingerGender());
    }

    public void setSingerName(String singerName) {
        this.singerDetail.setSingerName(singerName);
    }

    public void setSingerGender(char singerGender) {
        this.singerDetail.setSingerGender(singerGender);
    }

    @Override
    public String toString() {
        return songName + " - " + singerDetail.getSingerName() + " (" + singerDetail.getSingerGender() + ")";
    }

    @Override
    public int compareTo(Song songToCompare) {
        String tempSong1 = this.songName.toUpperCase();
        String tempSong2 = songToCompare.getSongName().toUpperCase();
        return tempSong1.compareTo(tempSong2);
    }
}
