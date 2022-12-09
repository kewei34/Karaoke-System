package data;

// Author : Chong Ke Wei

public class Singer implements Comparable<Singer> {

    private String singerName;
    private char singerGender;

    public Singer() {
        this.singerName = "";
        this.singerGender = '-';
    }

    public Singer(String singerName) {
        this.singerName = singerName;
        this.singerGender = '-';
    }

    public Singer(String singerName, char singerGender) {
        this.singerName = singerName;
        this.singerGender = singerGender;
    }

    public String getSingerName() {
        return singerName;
    }

    public char getSingerGender() {
        return singerGender;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setSingerGender(char singerGender) {
        this.singerGender = singerGender;
    }

    @Override
    public String toString() {
        return singerName + " (" + singerGender + ")";
    }

    @Override
    public int compareTo(Singer singerToCompare) {
        String tempSinger1 = this.singerName.toUpperCase();
        String tempSinger2 = singerToCompare.getSingerName().toUpperCase();
        return tempSinger1.compareTo(tempSinger2);
    }
}
