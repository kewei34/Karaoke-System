package data;

public class player implements Comparable<player> {

    public Integer playerNo;
    public String playerName;
    public int playerScore;

    public int getPlayerNo() {
        return playerNo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public player() {
        playerNo = 0;
        playerName = "";
        playerScore = 0;
    }

    public player(int playerNo, String playerName, int playerScore) {
        this.playerNo = playerNo;
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return "Player " + playerNo + "( " + playerName + " )" + " :" + playerScore + "\n";
    }

    @Override
    public int compareTo(player o) {
        if (this.playerScore == o.playerScore) {
            return 0;
        } else if (this.playerScore <= o.playerScore) {
            return 1;
        } else {
            return -1;
        }
    }

}
