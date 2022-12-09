
package data;

public class playListSong implements Comparable<playListSong>{
    
    public int index;
    private Song selectedSong = new Song();
    public boolean playing;
    
    public playListSong(){
        this.index= 0;
        this.selectedSong = null;
        this.playing = false;
    }
    
    public playListSong(int index,Song selectedSong) {
        this.index = index;
        this.selectedSong.setSongName(selectedSong.getSongName());
        this.selectedSong.setSingerName(selectedSong.getSingerName());
        this.selectedSong.setSingerGender(selectedSong.getSingerGender());
        this.playing = false;
    }

    public int getIndex() {
        return index;
    }

    public Song getSelectedSong() {
        return selectedSong;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return selectedSong.getSongName() + " - " + selectedSong.getSingerName() + " (" + selectedSong.getSingerGender()+ ")" ;
    }

    @Override
    public int compareTo(playListSong o) {
        if(this.index == o.index){
            return 0;
        }
        else if(this.index<=o.index){
            return 1;
        }
        else{
            return -1;
        }

    }
    
    
}
