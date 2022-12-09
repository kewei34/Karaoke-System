package client;

import adt.*;
import data.*;
import java.util.Iterator;
import java.util.Scanner;

public class KaraokeDriver {

    static ListInterface<playListSong> playlist = new Doubly_LinkedList<>();
    static BinaryTreeInterface<Song> songList = new BinaryTree<>();
    static BinaryTreeInterface<Singer> singerList = new BinaryTree<>();
    static ListInterface<player> playerList = new Doubly_LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int songCounter = 10, singerCounter = 7, idAfterCheck, choiceAfterCheck;
        String identity, sChoice;
        boolean loopStaff = false, loopMenu = false;

        Singer[] preSetSinger = new Singer[7];
        preSetSinger[0] = new Singer("Taylor Swift", 'F');
        preSetSinger[1] = new Singer("Christina Perri", 'F');
        preSetSinger[2] = new Singer("Ed Sheeran", 'M');
        preSetSinger[3] = new Singer("Maroon 5", 'M');
        preSetSinger[4] = new Singer("Wiz Khalifa", 'M');
        preSetSinger[5] = new Singer("Ava Max", 'F');
        preSetSinger[6] = new Singer("Alan Walker", 'M');

        Song[] preSetSong = new Song[10];
        preSetSong[0] = new Song("Love story", preSetSinger[0]);
        preSetSong[1] = new Song("Shape of you", preSetSinger[2]);
        preSetSong[2] = new Song("Happier", preSetSinger[2]);
        preSetSong[3] = new Song("Map", preSetSinger[3]);
        preSetSong[4] = new Song("See You Again", preSetSinger[4]);
        preSetSong[5] = new Song("Sugar", preSetSinger[3]);
        preSetSong[6] = new Song("Salt", preSetSinger[5]);
        preSetSong[7] = new Song("Lily", preSetSinger[6]);
        preSetSong[8] = new Song("Faded", preSetSinger[6]);
        preSetSong[9] = new Song("A Thousand Years", preSetSinger[1]);

        //add initial Song list into binary tree
        for (int i = 0; i < songCounter; i++) {
            songList.add(preSetSong[i]);
        }
        for (int i = 0; i < singerCounter; i++) {
            singerList.add(preSetSinger[i]);
        }
        //clear array to save space
        preSetSong = null;
        preSetSinger = null;

        do {
            System.out.print("\n Welcome To Joy Karaoke \n");
            System.out.print("------------------------\n");
            System.out.print(" Choose your identity(1 or 2) :\n");
            System.out.print("-------------------------\n");
            System.out.print(" 1. Customer \n");
            System.out.print(" 2. Staff\n");
            System.out.print("-------------------------\n");
            System.out.print("Enter you choice :  ");
            identity = scanner.nextLine();
            idAfterCheck = checkNum(identity);

            //if user insert character or other than 1 and 2
            if (idAfterCheck <= 0 || idAfterCheck != 1 && idAfterCheck != 2) {
                System.out.print("\n---------------------------------\n");
                System.out.print(" You can only insert 1 or 2 only.\n");
                System.out.print("----------------------------------\n\n");
                loopMenu = true;
            } else if (idAfterCheck == 1) {
                //customer menu
                customer(songList);
            } else {
                //staff menu
                boolean autho = false;
                //loop until correct passcode or exit
                while (autho != true) {
                    System.out.print("\nEnter staff passcode :  ");
                    sChoice = scanner.nextLine();

                    if ((sChoice.toUpperCase().charAt(0) == 'X') == true) {
                        loopMenu = true;
                        autho = false;
                        break;
                    }
                    if (sChoice.equals("staff123") != true) {
                        System.out.print("\n------------------------------\n");
                        System.out.print("       Wrong Passcode! \n");
                        System.out.print("------------------------------\n");
                        System.out.print("*Enter X to back to main menu.\n\n");
                        autho = false;
                    } else { //sucessful authorise
                        autho = true;
                        break;
                    }
                }
                //loop if the user enter characters or number that <1 and >4
                if (autho == true) {
                    do {
                        System.out.print("\n------------------------\n");
                        System.out.print("       Staff Menu \n");
                        System.out.print("------------------------\n");
                        System.out.print(" 1. Add Song \n");
                        System.out.print(" 2. Remove Song\n");
                        System.out.print(" 3. Edit Song/singer\n");
                        System.out.print(" 4. Display Song list \n");
                        System.out.print(" 5. Display singer list \n");
                        System.out.print(" 6. Back\n\n");
                        System.out.print("Enter your choice(1-6) :  ");
                        sChoice = scanner.nextLine();
                        choiceAfterCheck = checkNum(sChoice);

                        if (choiceAfterCheck < 1 || choiceAfterCheck > 6) {
                            System.out.print("----------------------------------------\n");
                            System.out.print(" You can only insert number 1 to 6 only.\n");
                            System.out.print("----------------------------------------\n\n");
                            loopStaff = true;
                        } else if (choiceAfterCheck == 1) {
                            addSong();
                            loopStaff = true;
                        } else if (choiceAfterCheck == 2) {
                            removeSong();
                            loopStaff = true;
                        } else if (choiceAfterCheck == 3) {
                            edit();
                            loopStaff = true;
                        } else if (choiceAfterCheck == 4) {
                            displaySL(songList);
                            loopStaff = true;
                        } else if (choiceAfterCheck == 5) {
                            displaySL(singerList);
                            loopStaff = true;
                        } else if (choiceAfterCheck == 6) {
                            loopStaff = false;
                            loopMenu = true;
                        }
                    } while (loopStaff);
                }
            }//staff function closing bracket

        } while (loopMenu);

    }//main closing bracket

    //display the Song list
    public static void displaySL(BinaryTreeInterface tree) {
        Iterator it = tree.getInorderIterator();
        int counter = 1;
        while (it.hasNext()) {
            System.out.println(counter + ". " + it.next() + " ");
            counter++;
        }
    }

    public static void displayPL(ListInterface list) {
        Iterator it = list.getListData();
        String playing = "";
        int counter = 1;
        while (it.hasNext()) {
            playListSong pl = (playListSong) it.next();
            if (pl.isPlaying()) {
                playing = "    [ ~ Playing Now ~ ] ";
            } else {
                playing = "";
            }
            System.out.println(counter + ". " + pl.toString() + " " + playing);
            counter++;
        }
        //System.out.println();
    }

    public static player pyGetObj(int index, ListInterface list) {
        Iterator it = list.getListData();
        int counter = 1;
        while (it.hasNext()) {
            player py = new player();
            py = (player) it.next();
            if (counter == index) {
                return py;
            }
            counter++;
        }
        return null;
    }

    public static player findHighestScore(ListInterface list) {
        Iterator it = list.getListData();
        int highest = 0;
        player py = new player();
        player highest_py = new player();
        while (it.hasNext()) {
            py = (player) it.next();
            int score = py.getPlayerScore();
            if (score > highest) {
                highest_py = py;
                highest = score;
            }
        }
        return highest_py;
    }

    public static boolean compareScore(int score, ListInterface list) {
        Iterator it = list.getListData();
        player py = new player();
        while (it.hasNext()) {
            py = (player) it.next();
            int existScore = py.getPlayerScore();
            if (existScore == score) {
                return false;
            }
        }
        return true;
    }

    public static void changeSingerName(BinaryTreeInterface tree, Singer oldSinger, Singer newSinger) {
        Iterator it = tree.getInorderIterator();
        String oldName = oldSinger.getSingerName();
        String newName = newSinger.getSingerName();
        while (it.hasNext()) {
            Song item = (Song) it.next();
            if (item.getSingerName().equals(oldName)) {
                item.setSingerName(newName);
            }
        }
    }

    public static void changeSingerGender(BinaryTreeInterface tree, Singer tempSinger, char gender) {
        Iterator it = tree.getInorderIterator();
        String singerName = tempSinger.getSingerName();
        while (it.hasNext()) {
            Song item = (Song) it.next();
            if (item.getSingerName().equals(singerName)) {
                item.setSingerGender(gender);
            }
        }
    }

    public static playListSong plGetObj(int index, ListInterface list) {
        Iterator it = list.getListData();
        int counter = 1;
        while (it.hasNext()) {
            playListSong pl = new playListSong();
            pl = (playListSong) it.next();
            if (counter == index) {
                return pl;
            }
            counter++;
        }
        return null;
    }

    public static int checkNum(String sChoice) {
        if (sChoice.equals("")) {
            return -2;
        }
        try {
            int c = Integer.parseInt(sChoice);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return Integer.parseInt(sChoice);
    }

    private static void addSong() {

        String songName = "", singerName = "", choice;
        char gender = '-';
        boolean loopAdd = false, loopSinger = false, loopEnterSinger = false, backToMenu = false;

        do {
            //enter Song name, enter x will return to menu
            System.out.print("Enter the Song name (Enter X to back) : ");
            songName = scanner.nextLine();
            if (songName.equals("")) {
                System.out.print("\n----------------------------------------\n");
                System.out.print("    Song name should not be empty !\n");
                System.out.print("----------------------------------------\n\n");
                loopAdd = true;
            } else if (Character.toUpperCase(songName.charAt(0)) == 'X') {
                loopAdd = false;
                backToMenu = true;
                break;
            } //add Song
            else {
                Song tempSong = new Song(songName);
                //if the Song name already exist, show error message, else continue enter singer name
                if (songList.isRepeat(tempSong) == true) {
                    System.out.print("\n----------------------------------------\n");
                    System.out.print("          This Song already exist !\n");
                    System.out.print("----------------------------------------\n");
                    loopAdd = true;
                } else {
                    //Singer name
                    loopAdd = false;
                    do {
                        System.out.print("Do you want to choose from existing singer name?(Y/N) :  ");
                        choice = scanner.nextLine();
                        //choose from list
                        if (choice.equals("")) {
                            //go to switch default
                            choice = "A";
                        }
                        switch (Character.toUpperCase(choice.charAt(0))) {
                            case 'Y':
                                Singer tempSinger = new Singer();
                                tempSinger = chooseSinger();
                                singerName = tempSinger.getSingerName();
                                gender = tempSinger.getSingerGender();
                                tempSinger = null;
                                loopSinger = false;
                                break;
                            case 'N':
                                do {
                                    System.out.print("Enter the singer name :  ");
                                    singerName = scanner.nextLine();
                                    Singer testSinger = new Singer(singerName);
                                    if (singerName.equals("")) {
                                        System.out.print("\n----------------------------------------\n");
                                        System.out.print("    Singer name should not be empty ！\n");
                                        System.out.print("----------------------------------------\n\n");
                                        loopEnterSinger = true;
                                    } else if (singerList.isRepeat(testSinger)) {
                                        System.out.print("\n----------------------------------------\n");
                                        System.out.print("    The singer name already exist! ！\n");
                                        System.out.print("----------------------------------------\n\n");
                                        testSinger = null;
                                        loopEnterSinger = true;
                                    } else {
                                        loopEnterSinger = false;
                                    }
                                } while (loopEnterSinger);

                                gender = chooseGender();
                                loopSinger = false;
                                break;
                            default:
                                loopSinger = true;
                                System.out.print("\n----------------------------------------\n");
                                System.out.print("    You can only insert Y or N only !\n");
                                System.out.print("----------------------------------------\n\n");
                                break;
                        }
                    } while (loopSinger);
                }//if Song name is not repeated
            }

        } while (loopAdd);
        if (!backToMenu) {
            Singer singerToAdd = new Singer(singerName, gender);
            singerList.add(singerToAdd);
            Song songToAdd = new Song(songName, singerToAdd);
            songList.add(songToAdd);
            singerToAdd = null;
            songToAdd = null;
            System.out.print(songName + " added sucessfully !\n\nUpdated Song list : \n");
            System.out.print("----------------------------------------\n");
            displaySL(songList);
        }
    }

    private static void removeSong() {

        String tempSong;
        Integer songIndex = 0;
        boolean loopSong = false;
        do {
            displaySL(songList);
            System.out.print("\nEnter the number of Song to delete (Enter 0 to back) :  ");
            tempSong = scanner.nextLine();
            songIndex = checkNum(tempSong);
            if (songIndex == 0) {
                break;
            } else if (songIndex <= 0 || songIndex > songList.getNumItems()) {
                System.out.print("\n----------------------------------------\n");
                System.out.print("    You can only insert 1 to " + songList.getNumItems() + "only.\n");
                System.out.print("----------------------------------------\n\n");
                loopSong = true;
            } else {
                Song tempRemoveSong = songList.getObj(songIndex, songList);
                songList.remove(tempRemoveSong);
                System.out.print(tempRemoveSong.getSongName() + " removed sucessfully !\n\nUpdated Song list : \n");
                System.out.print("----------------------------------------\n");
                displaySL(songList);
                tempRemoveSong = null;
            }
        } while (loopSong);
    }

    public static void edit() {

        String tempSong, tempChoice, songName = "", singerName;
        int songIndex = 0, choice = 0;
        boolean loopEdit = false, loopSongName = true, loopEnterSinger = false, loopSelectSong = false;
        char gender;

        do {
            System.out.print("\n1. Song name\n");
            System.out.print("2. Singer name\n");
            System.out.print("3. Singer gender\n\n");
            System.out.print("*Enter 0 to back.\n");
            System.out.print("\nEnter the category to edit(1-3) :  ");
            tempChoice = scanner.nextLine();
            choice = checkNum(tempChoice);
            switch (choice) {
                //back
                case 0:
                    loopEdit = false;
                    break;
                //Song name
                case 1:
                    do {
                        displaySL(songList);
                        System.out.print("\nEnter the number of Song to edit (Enter 0 to back) :  ");
                        tempSong = scanner.nextLine();
                        songIndex = checkNum(tempSong);
                        //back
                        if (songIndex == 0) {
                            loopEdit = false;
                            loopSelectSong = false;
                            break;
                        } //input invalid
                        else if (songIndex <= 0 || songIndex > songList.getNumItems()) {
                            System.out.print("\n----------------------------------------\n");
                            System.out.print("    You can only insert 1 to " + songList.getNumItems() + " only.\n");
                            System.out.print("----------------------------------------\n\n");
                            loopEdit = true;
                            loopSelectSong = true;
                        } else {
                            do {
                                System.out.print("\nEnter new Song name : ");
                                songName = scanner.nextLine();
                                if (songName.equals("")) {
                                    System.out.print("----------------------------------------\n");
                                    System.out.print(" Song name cannot be empty ! \n");
                                    System.out.print("----------------------------------------\n");
                                    loopSongName = true;
                                } else {
                                    Song tempEditSong = new Song(songName);
                                    //if the Song name already exist, show error message
                                    if (songList.isRepeat(tempEditSong) == true) {
                                        System.out.print("----------------------------------------\n");
                                        System.out.print(" This Song name already exist !\n");
                                        System.out.print("----------------------------------------\n");
                                        loopSongName = true;
                                    } else {
                                        //curent Song detail
                                        Song editSongList = songList.getObj(songIndex, songList);
                                        Singer oldSinger = editSongList.getSingerDetail();
                                        Song newSong = new Song(songName, oldSinger);
                                        songList.remove(editSongList);
                                        songList.add(newSong);
                                        System.out.print("\nThe Song name had been edited to " + songName + ". \n");
                                        loopSongName = false;
                                    }
                                }
                            } while (loopSongName);
                        }
                    } while (loopSelectSong);
                    break;
                //Singer name
                case 2:
                    //choose singer
                    Singer tempSinger = chooseSinger();
                    do {
                        System.out.print("Enter the new singer name :  ");
                        singerName = scanner.nextLine();
                        Singer newSinger = new Singer(singerName);
                        if (singerName.equals("")) {
                            System.out.print("\n----------------------------------------\n");
                            System.out.print("    Singer name should not be empty ！\n");
                            System.out.print("----------------------------------------\n\n");
                            loopEnterSinger = true;
                        } else if (singerList.isRepeat(newSinger)) {
                            System.out.print("\n----------------------------------------\n");
                            System.out.print("    The singer name already exist! ！\n");
                            System.out.print("----------------------------------------\n\n");
                            loopEnterSinger = true;
                        } else {
                            loopEnterSinger = false;
                            //change the singer name in all Song list
                            changeSingerName(songList, tempSinger, newSinger);
                            //change the singer name in the Singer list
                            tempSinger.setSingerName(singerName);
                            System.out.print("The singer name had been edited sucessfully. \n");
                        }
                    } while (loopEnterSinger);

                    break;
                //gender
                case 3:
                    Singer tempSingerForGender = chooseSinger();
                    gender = tempSingerForGender.getSingerGender();
                    switch (gender) {
                        case 'M':
                            //change the singer gender in all Song list
                            changeSingerGender(songList, tempSingerForGender, 'F');
                            //change the singer gender in the Singer list
                            tempSingerForGender.setSingerGender('F');
                            break;
                        case 'F':
                            changeSingerGender(songList, tempSingerForGender, 'M');
                            tempSingerForGender.setSingerGender('M');
                            break;
                        default:
                            //if the gender is not set, let user choose
                            gender = chooseGender();
                            tempSingerForGender.setSingerGender(gender);
                            changeSingerGender(songList, tempSingerForGender, gender);
                            break;
                    }
                    System.out.print("\nThe singer gender had been edited sucessfully! \n");
                    break;

                default:
                    System.out.print("----------------------------------------\n");
                    System.out.print("    You can only insert 0 to 3 only.\n");
                    System.out.print("----------------------------------------\n\n");
                    loopEdit = true;
                    break;
            }
        } while (loopEdit);
    }

    public static Singer chooseSinger() {

        boolean loopSinger = false;
        do {
            String tempSinger;
            int singerIndex = 0;
            displaySL(singerList);
            System.out.print("Choose the number of singer (1-" + singerList.getNumItems() + " only) : ");
            tempSinger = scanner.nextLine();
            singerIndex = checkNum(tempSinger);
            if (singerIndex <= 0 || singerIndex > singerList.getNumItems()) {

                System.out.print("----------------------------------------\n");
                System.out.print("    You can only insert 1 to " + singerList.getNumItems() + " only.\n");
                System.out.print("----------------------------------------\n\n");
                loopSinger = true;
            } else {
                loopSinger = false;
                return singerList.getObj(singerIndex, singerList);
            }
        } while (loopSinger);

        return null;
    }

    public static char chooseGender() {
        String tempGender;
        boolean loopGender = true;
        do {
            System.out.print("Choose the gender of the singer(M/F) :  ");
            tempGender = scanner.nextLine();
            switch (Character.toUpperCase(tempGender.charAt(0))) {
                case 'M':
                    return 'M';
                case 'F':
                    return 'F';
                default:
                    System.out.print("----------------------------------------\n");
                    System.out.print("    You can only insert M or F only.\n");
                    System.out.print("----------------------------------------\n\n");
                    loopGender = true;
                    break;
            }
        } while (loopGender);
        return '-';
    }

    public static void playSong() {

        String pchoice = "0", rchoice="0",numberOfPlayers = "0";
        boolean shuffle = false, repeat = false, diffScore = true;
        int shuffledNo, playerScore, numberOfPlayersAfterCheck, rchoiceAfterCheck = 0,pchoiceAfterCheck = 1,playingSongNo = 1;
        String playerName,replaceChoice,replaceTarget;
        char finishSinging;
        int replaceChoiceAfterCheck,replaceTargetAfterCheck;

        OUTER://default choice is 1, so it will move it into the while loop
        while (pchoiceAfterCheck >= 1 && pchoiceAfterCheck <= 7 && !playlist.isEmpty()) {
            playListSong pl = plGetObj(1, playlist);//get the first song object
            System.out.print("````````````````````````````````````````````````````````````\n"); //print out currently playing song
            System.out.print("Currently playing [ " + pl.getSelectedSong().getSingerName() + " - " + pl.getSelectedSong().getSongName() + " ]\n");
            System.out.print("````````````````````````````````````````````````````````````\n");
            pl.setPlaying(true); //set the current first song as isplaying
            if (shuffle) {
                System.out.print("Shuffle: ON\n\n");//show shuffle on/off
            } else {
                System.out.print("Shuffle: OFF\n\n");
            }

            if (repeat) {
                System.out.print("Repeat: ON\n");//show repeat on/off
            } else {
                System.out.print("Repeat: OFF\n");
            }

            System.out.print("\nInput Option(choose one only)\n1: Next Song\n2: Move particular Song to the first"
                    + "\n3: View playlist\n4: Toogle shuffle play on/off\n5: Toogle repeat on/off\n6: Enter Song battle\n7: Exit playlist\n ");
            System.out.print("\nChoice :");
            do {
                pchoice = scanner.nextLine();
                pchoiceAfterCheck = checkNum(pchoice);
                if (pchoiceAfterCheck == -1) {
                    System.out.println("Please enter 1 to 7 only.");
                    System.out.print("\nChoice :");
                } else if (pchoiceAfterCheck == -2) {
                    pchoice = scanner.nextLine();
                    pchoiceAfterCheck = checkNum(pchoice);  //check the input whether is integer
                }
            } while (pchoiceAfterCheck < 1 || pchoiceAfterCheck > 7);
            System.out.println();
            switch (pchoiceAfterCheck) {
                case 1:
                    if (!shuffle && !repeat) {
                        pl.setPlaying(false);
                        playlist.remove(pl); //normal mode: remove the song after played
                    } else if (shuffle && !repeat) {
                        shuffledNo = (int) (Math.random() * ((playlist.getNumberOfEntries() - 2) + 1)) + 1;
                        pl.setPlaying(false); //shuffle mode: song will randomly played and removed after played
                        playlist.remove(pl);
                        pl = plGetObj((Integer) (shuffledNo), playlist);
                        playlist.move(pl, 1);  
                    } else if (!shuffle && repeat) { //repeat mode: song will not be removed after played
                        pl.setPlaying(false);
                        playlist.move(pl, playlist.getNumberOfEntries());
                    } else {
                        shuffledNo = (int) (Math.random() * ((playlist.getNumberOfEntries() - 1) + 1)) + 1;
                        pl.setPlaying(false);   //shuffle and repeat mode: song will randomly played and will not be removed after played
                        pl = plGetObj((Integer) (shuffledNo), playlist);
                        playlist.move(pl, 1);
                    }
                    break;
                case 2:
                    if (playlist.getNumberOfEntries() != 1) {
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("                               Playlist   ");
                        System.out.println("---------------------------------------------------------------------");
                        displayPL(playlist);
                        System.out.print("-----------------------------------------------------------------------\n");
                        System.out.print("\nWhich Song do you want to move to first (eg: 4): ");
                        do {
                            rchoice = scanner.nextLine();
                            rchoiceAfterCheck = checkNum(rchoice);//check user input whether it is integer
                            if (rchoiceAfterCheck <= 0 || rchoiceAfterCheck > playlist.getNumberOfEntries()) {
                                System.out.print("\nPlease enter number from 1 to " + playlist.getNumberOfEntries() + " only (eg: 2): ");
                            }
                        } while (rchoiceAfterCheck <= 0 || rchoiceAfterCheck > playlist.getNumberOfEntries());
                        pl.setPlaying(false); //set the current playing false to not playing
                        playingSongNo = rchoiceAfterCheck;
                        pl = plGetObj((Integer) (playingSongNo), playlist);
                        if (playlist.move(pl, 1)) { //move the selected song to the first location
                            pl.setPlaying(true);
                            System.out.print("\n" + pl.getSelectedSong().getSongName() + " is now your first Song\n\n");
                        } else {
                            System.out.print("\nError occured, the Song cannot moved to the top\n\n");
                        }
                    } else {
                        System.out.print("\nThere is only one song in your playlist !\n\n");
                        System.out.print("Returning to playlist menu...\n\n");
                    }
                    break;
                case 3:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("                          Playlist   ");
                    System.out.println("---------------------------------------------------------------");
                    displayPL(playlist); //display the whole playlist
                    System.out.print("-----------------------------------------------------------------\n\n");

                    System.out.print("Press any button to return to playlist\n");
                    scanner.nextLine();

                    break;
                case 4:
                    if (!shuffle) {
                        shuffle = true; //turn on/off shuffle mode based on its current status
                        System.out.println("\nThe shuffle mode has been enabled.\n");
                    } else {
                        shuffle = false;
                        System.out.println("\nThe shuffle mode has been disabled.\n");
                    }
                    break;
                case 5:
                    if (!repeat) { //turn on/off repeat mode based on its current status
                        repeat = true;
                        System.out.println("\nRepeat enabled. Your playlist will now repeat after finish playing.\n");
                    } else {
                        repeat = false;
                        System.out.println("\nRepeat disabled. Played Song will now be removed after finish playing.\n");
                    }
                    break;
                case 6:
                    player py = new player(); //initialize player object
                    System.out.println("\n-------------------------------");
                    System.out.print("        ! ! Song Battle ! !\n");
                    System.out.println("-------------------------------\n");
                    System.out.print("Please enter the number of players (2-5) :");
                    do{
                        numberOfPlayers = scanner.nextLine();
                        numberOfPlayersAfterCheck = checkNum(numberOfPlayers);

                    if(numberOfPlayersAfterCheck == -1) {
                        System.out.print("Please enter value between 2 to 5 only :");
                    }else if(numberOfPlayersAfterCheck == -2){    //check user input
                        numberOfPlayers = scanner.nextLine();
                        numberOfPlayersAfterCheck = checkNum(numberOfPlayers);
                    }
                    }while(numberOfPlayersAfterCheck <= 1 || numberOfPlayersAfterCheck > 5);
                    
                    for (int i = 1; i <= numberOfPlayersAfterCheck; i++) {
                        if (i == 1) {
                            scanner.nextLine();
                        }
                        do {
                            System.out.print("Please enter player " + i + " name :");  //let user enter his/her name
                            playerName = scanner.nextLine();
                            if(playerName.isEmpty()){
                                System.out.print("Please do not leave the answer empty!\n");
                            }
                        } while (playerName.isEmpty());
                        do {
                            playerScore = (int) (Math.random() * ((100 - 40) + 1)) + 40;  //randomly generate player score
                            diffScore = compareScore(playerScore, playerList);            
                        } while (!diffScore);
                        player tempPlayer = new player(i, playerName, playerScore); //store the data into the object
                        py = tempPlayer;
                        playerList.add(py);  //add the object in the playerlist
                        playerName = "";
                    }
                    for (int i = 1; i <= numberOfPlayersAfterCheck; i++) {
                        if (playlist.getNumberOfEntries() != 1) {
                            shuffledNo = (int) (Math.random() * ((playlist.getNumberOfEntries() - 1) + 1)) + 1; //randomly generate a song no.
                            pl.setPlaying(false);
                            pl = plGetObj((Integer) (shuffledNo), playlist);  //get the object of the song
                            playlist.move(pl, 1); //move the first song and play
                        } else {
                            shuffledNo = 1;
                        }
                        py = pyGetObj((Integer) i, playerList);
                        System.out.print("`````````````````````````````````````````````````````````````````````\n");
                        System.out.print("Player " + i + " ( " + py.getPlayerName() + " )  Song ->[ " + pl.getSelectedSong().getSingerName() + " - " + pl.getSelectedSong().getSongName() + " ]<-\n");
                        System.out.print("`````````````````````````````````````````````````````````````````````\n");
                        System.out.print("Please enter 'Y' after you have finished singing the Song: ");
                        finishSinging = scanner.next().charAt(0);
                        while (finishSinging != 'Y' && finishSinging != 'y') {
                            System.out.print("\nPlease enter 'Y' after you have finished singing the Song.  ");
                            finishSinging = scanner.next().charAt(0);  //get to know whether the player has finished singing
                        }

                        System.out.print(py.toString() + "\n");
                    }
                    py = findHighestScore(playerList); //to find the highest score among all the players
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("[!!~~~~ Congratulations, player " + py.getPlayerNo() + ": " + py.getPlayerName() + " wins the game with the score " + py.getPlayerScore() + " ~~~~~!!]");
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("!!!! The Winner has the chance to replace current playlist's Song with another Song from the songlist. !!!!\n");
                    System.out.println("-----------------------------------------------");
                    System.out.println("            Current Playlist   ");
                    System.out.println("-----------------------------------------------");
                    displayPL(playlist);
                    System.out.print("-----------------------------------------------\n");
                    System.out.print("Which Song do you want to replace (0 to cancel) : ");
                    do{            //let the user choose to overwrite one of the song into playlist, user can choose 0 to cancel
                        replaceChoice = scanner.nextLine();
                        replaceChoiceAfterCheck = checkNum(replaceChoice);
                        if(replaceChoiceAfterCheck ==-1){
                            System.out.print("Which Song do you want to replace (0 to cancel) : ");
                        }else if(replaceChoiceAfterCheck ==-2){
                            replaceChoice = scanner.nextLine(); //get the input of which song to replace
                            replaceChoiceAfterCheck = checkNum(replaceChoice);
                        }
                    } while (replaceChoiceAfterCheck < 0 || replaceChoiceAfterCheck > playlist.getNumberOfEntries());
                    System.out.println();
                    playerList.clear();
                    if (replaceChoiceAfterCheck == 0) {
                        System.out.print("Thank you for playing, returning to playlist now.\n\n");
                    } else {
                        displaySL(songList);
                        System.out.print("\nWhich Song do you want to replace with (eg:1): ");
                        do{
                        replaceTarget = scanner.nextLine();
                        replaceTargetAfterCheck = checkNum(replaceTarget);  //get the input of which song to replace with
                        
                        if(replaceTargetAfterCheck==-1){
                            System.out.print("Please enter 1 to " + songList.getNumItems() + " only: ");}
                        else if(replaceTargetAfterCheck==-2){
                            replaceTarget = scanner.nextLine();
                            replaceTargetAfterCheck = checkNum(replaceTarget);
                        }
                        }while (replaceTargetAfterCheck > songList.getNumItems() || replaceTargetAfterCheck <= 0);
                        Song selectedSong = songList.getObj(replaceTargetAfterCheck, songList);
                        playListSong ps = new playListSong(playlist.getNumberOfEntries() + 1, selectedSong);
                        if (!playlist.overwrite(ps, (Integer) replaceChoiceAfterCheck)) { //overwrite the chosen song 
                            System.out.print("  " + ps.getSelectedSong().getSongName() + "failed to overwrite song no." + replaceChoice + "\n\n");
                        } else {
                            System.out.print("  " + ps.getSelectedSong().getSongName() + "has become the song no." + replaceChoice + "\n\n");
                        }
                        System.out.print("Thank you for playing, returning to playlist now.\n\n");
                    }
                    break;
                default:
                    break OUTER;
            }

        }
        if (pchoiceAfterCheck == 7) {
            System.out.print("Exiting to customer menu....\n\n"); //exit to menu 
        } else {
            System.out.print("There is no Song left in the playlist\n");
            System.out.print("Exiting to customer menu....\n\n");
        }

    }

    public static void removeCurrentSong() {
        char continueRemove = 'Y'; //to store the user input to continue remove or not
        boolean invalidInput,charInput = false;
        String chosenSong;// to store the song that user input
        int tempSongIndex = 0; // to store the int input from int array 

        while (Character.toUpperCase(continueRemove) == 'Y') { //continue to add Song when the user input is y

            //display Song list
            System.out.println("---------------------------------------------------------------");
            System.out.println("                          Playlist   ");
            System.out.println("---------------------------------------------------------------");
            displayPL(playlist);
            System.out.print("-----------------------------------------------------------------\n");
            do {
                String[] chosenSongArray; //to store the array of chosen song 
                invalidInput = charInput = false;
                System.out.print("Which Song do you want to remove (Eg: 1,2,3 OR 0 to exit) : ");

                do {
                    chosenSong = scanner.nextLine();
                } while (chosenSong.isEmpty());

                for (int i = 0; i < chosenSong.length(); i++) { //check if the user enter char
                    char ch = chosenSong.charAt(i);
                    if (ch != ',' && !Character.isDigit(ch) || ch == ' ') {
                        charInput = true;
                        break;
                    }
                }

                if (chosenSong.equals("0")) { //check if user want to leave
                    break;
                }
                if (!charInput) {
                    chosenSongArray = chosenSong.split("\\s*,\\s*");

                    for (int i = 0; i < chosenSongArray.length; i++) {
                        tempSongIndex = Integer.parseInt(chosenSongArray[i]);
                        if (tempSongIndex > playlist.getNumberOfEntries() || tempSongIndex <= 0) {
                            invalidInput = true;
                            break;
                        }
                    }
                }
                if (chosenSong.isEmpty()) {//check if the field is empty
                    System.out.print("\nPlease dont leave the field empty!\n");
                    invalidInput = true;
                } else {
                    if (charInput) {
                        System.out.print("\nInvalid input, please enter postive digit and ',' only.\n");
                    } else if (invalidInput) {
                        System.out.print("\nInvalid input, please enter Song that is in the Song list only.\n");
                    }

                }
            } while (invalidInput == true || chosenSong.isEmpty() || charInput == true);
            if (chosenSong.equals("0")) {
                System.out.println();
                break;
            }
            String[] chosenSongArray;
            System.out.println();
            chosenSongArray = chosenSong.split("\\s*,\\s*"); //to store the correct input before ',' into the array
            for (int i = 0; i < chosenSongArray.length; i++) {
                tempSongIndex = Integer.parseInt(chosenSongArray[i]); // to get the song index
                playListSong pl = plGetObj(tempSongIndex - i, playlist); //to get the object by refering to the song index
                if (!playlist.remove(pl)) {
                    System.out.print("\nRemove failed, " + pl.getSelectedSong().toString() + " is not in the playlist.\n");
                }
            }
            if (playlist.getNumberOfEntries() == 0) { //break if the no entry remaining
                System.out.println("\nThe playlist is now empty. Returning to main screen...\n ");
                break;
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                         Current Playlist   ");
            System.out.println("--------------------------------------------------------------------");
            displayPL(playlist);
            System.out.println("--------------------------------------------------------------------\n");

            System.out.print("Do you want to remove more Song from your playlist (Y/N) : ");
            continueRemove = scanner.next().charAt(0);
            while (Character.toUpperCase(continueRemove) != 'Y' && Character.toUpperCase(continueRemove) != 'N') {
                System.out.print("\nPlease enter Y or N only: ");
                continueRemove = scanner.next().charAt(0);
                System.out.println();
            }
        }
        if(Character.toUpperCase(continueRemove) == 'N')System.out.println("\nReturning to main screen...\n ");
        //if the input is y , print out return message
    }

    public static void addToPlaylist() {

        char continueAdd = 'Y';  //variable to store user choice whether to stop adding or not.
        boolean invalidInput = false;//to check inavldi input
        boolean charInput = false; //to check if the user is entering char input type
        String chosenSong; // to store the song that user input
        int tempSongIndex = 0; // to store the int input from int array 

        while (Character.toUpperCase(continueAdd) == 'Y') { //continue to add Song when the user input is y

            //display Song list
            System.out.println("---------------------------------------------------------------");
            System.out.println("                          Song List   ");
            System.out.println("---------------------------------------------------------------");
            displaySL(songList);
            System.out.print("-----------------------------------------------------------------\n");
            do {
                String[] chosenSongArray; // to store the array of chosenSong
                invalidInput = charInput = false;
                System.out.print("Which Song do you want to add (Eg: 1,2,3 OR 0 to exit) : ");
                
                do {
                    chosenSong = scanner.nextLine();
                } while (chosenSong.isEmpty());

                for (int i = 0; i < chosenSong.length(); i++) {//validation for char input 
                    char ch = chosenSong.charAt(i);
                    if (ch != ',' && !Character.isDigit(ch) || ch == ' ') {
                        charInput = true;
                        break;
                    }
                }

                if (chosenSong.equals("0")) {
                    break;
                }
                if (!charInput) {
                    chosenSongArray = chosenSong.split("\\s*,\\s*");  //get input before ',' and put into array

                    for (int i = 0; i < chosenSongArray.length; i++) {
                        tempSongIndex = Integer.parseInt(chosenSongArray[i]);
                        if (tempSongIndex > songList.getNumItems() || tempSongIndex <= 0) {
                            invalidInput = true;
                            break;
                        }
                    }
                }
                if (chosenSong.isEmpty()) {//check if the input is empty
                    System.out.print("\nPlease dont leave the field empty!\n");
                    invalidInput = true;
                } else {
                    if (charInput) {
                        System.out.print("\nInvalid input, please enter postive digit and ',' only.\n");
                    } else if (invalidInput) {
                        System.out.print("\nInvalid input, please enter Song that is in the Song list only.\n");
                    }

                }
            } while (invalidInput == true || chosenSong.isEmpty() || charInput == true);
            if (chosenSong.equals("0")) {
                System.out.println();
                break;
            }//if the input is 0, break and go back to playlist main
            String[] chosenSongArray;
            System.out.println();
            chosenSongArray = chosenSong.split("\\s*,\\s*"); // this is the part where all the input is correct
            for (int i = 0; i < chosenSongArray.length; i++) {
                tempSongIndex = Integer.parseInt(chosenSongArray[i]);
                Song selectedSong = songList.getObj(tempSongIndex, songList);//get the song from the songlist
                playListSong pl = new playListSong(playlist.getNumberOfEntries() + 1, selectedSong);  
                if (!playlist.add(pl)) { //add into the playlist 
                    System.out.print("\nError occured, " + pl.getSelectedSong().getSongName() + " is not added into the list.\n");
                }
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                          Current Playlist   ");
            System.out.println("--------------------------------------------------------------------");
            displayPL(playlist);
            System.out.println("--------------------------------------------------------------------\n");

            System.out.print("Do you want to add more Song to your current Song list (Y/N) : ");
            continueAdd = scanner.next().charAt(0); //ask for confirmation to continue 
            while (Character.toUpperCase(continueAdd) != 'Y' && Character.toUpperCase(continueAdd) != 'N') {
                System.out.print("\nPlease enter Y or N only: ");
                continueAdd = scanner.next().charAt(0);
                System.out.println();
            }

        }// end of while loop
    }

    public static void customer(BinaryTreeInterface songList) {
        
        String choice;
        int choiceAfterCheck;
        
        System.out.print("\n-------------------------------\n");
        System.out.print("|       Customer Menu         | \n");
        System.out.print("-------------------------------\n\n");

        addToPlaylist();
        do {
            System.out.print("\n-----------------------------------------");
            System.out.print("\n       Choose the next action\n");
            System.out.print("-----------------------------------------\n");
            System.out.println("  1.Enter The Playlist\n  2.Add Song\n  3.Remove Song From Playlist");
            System.out.println("-----------------------------------------");
            System.out.print("\nChoice (1 / 2 / 3 / [4 to exit]):");
            do{
            choice = scanner.nextLine();
            choiceAfterCheck = checkNum(choice);
            if(choiceAfterCheck==-1 || choiceAfterCheck>4 || choiceAfterCheck<1  &&choiceAfterCheck!=-2){
                System.out.print("Please enter 1 to 4 only :");
            }else if(choiceAfterCheck==-2){
                choice = scanner.nextLine();
                choiceAfterCheck = checkNum(choice);
            }
            }while(choiceAfterCheck<1 || choiceAfterCheck>4);
            System.out.println();
            switch (choiceAfterCheck){
                case 1:
                    playSong();
                    break;
                case 2:
                    addToPlaylist();
                    break;
                case 3:
                    removeCurrentSong();
                    break;
                default:
                    break;
            }

        } while (choiceAfterCheck == 1 || choiceAfterCheck == 2 || choiceAfterCheck == 3);
        System.out.print("\nThank you for visiting Joy Karaoke. Have a nice day, bye!\n");
        

    }
}
