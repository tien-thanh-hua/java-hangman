/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import entities.Hangman;
import entities.Player;
import entities.Question;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class GameInstance {

    public static GameInstance mainGame;
    private int score;
    private int level;
    private String difficulty = "";
    private int difficultyFactor;

    private Hangman hangMan;
    private Question question;
    
    private ArrayList<Player> top5Players;
    private Player player;
    private char playerChoice;

    public void increaseLevel() {
        this.setLevel(level + 1);
    }

    private enum QuestionTopics {
        City,
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public char getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(char playerChoice) {
        this.playerChoice = playerChoice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Hangman getHangMan() {
        return hangMan;
    }

    public void setHangMan(Hangman hangMan) {
        this.hangMan = hangMan;
    }

    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty for the game, which sets the initial state of the Hangman object.<br>
     * If this method is called after the difficulty has been set at least once,
     * then it will only update the difficulty, not resetting it.<br>
     * This method should only be used <strong>once</strong> per play-through.<br> 
     * Currently supported difficulties include:<br>
     * <ul>
     *  <li>"easy": state = 0</li>
     *  <li>"normal": state = 3</li>
     *  <li>"hard": state = 3</li>
     *  <li>"asian": state = 3</li>
     * </ul>
     * @param difficulty (String) The difficulty to be set.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        setDifficultyFactor(difficulty);
        int hangManState;
        switch (difficulty) {
            case "normal":
            case "hard":
            case "asian":
                hangManState = 3;
                break;
            case "easy":
            default:
                hangManState = 0;
                break;
        }
        this.hangMan.setState(hangManState);
    }

    public int getDifficultyFactor() {
        return difficultyFactor;
    }
    
    public void setDifficultyFactor(String difficulty) {
        switch (difficulty) {
            case "easy":
                setDifficultyFactor(1);
                break;
            case "normal":
                setDifficultyFactor(2);
                break;
            case "hard":
                setDifficultyFactor(3);
                break;
            case "asian":
                setDifficultyFactor(8);
                break;
            default:
                setDifficultyFactor(0);
                break;
        }
    }

    public void setDifficultyFactor(int difficultyFactor) {
        this.difficultyFactor = difficultyFactor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void savePlayer(String name) {
        player.setName(name);
        player.setScore(score);
        top5Players.add(player);
        updateTop5Players(top5Players);
    }
    
    public void updateUserString() {
        question.updateString(playerChoice);
    }

    public void increaseScore() {
        this.setScore(this.getScore() + 100 * difficultyFactor);
    }
    
    public void readScoreFile() {
        ArrayList<Player> players = new ArrayList<>();
        String playerName;
        int playerScore;
        boolean isFileSuccessfullyRead = false;
        while (!isFileSuccessfullyRead) {
            try {
                File scoreFile = new File("data/score.hsc");
                Scanner fileSc = new Scanner(scoreFile);
                while (fileSc.hasNext()) {
                    playerName = fileSc.next();
                    //fileSc.next();
                    playerScore = fileSc.nextInt();
                    if (getPlayerIndex(playerName) != -1) { // player already exists
                        int oldPlayerScore = players.get(getPlayerIndex(playerName)).getScore();
                        if (playerScore > oldPlayerScore) { // score has not existed for that player yet
                            players.get(getPlayerIndex(playerName)).setScore(playerScore);
                        }
                        // there might still be issues if the score 
                        // has not existed for that player yet, but for other players
                    } else { // if not, add player to list
                        players.add(new Player(playerName, playerScore));
                    }
                }
                updateTop5Players(players);
                fileSc.close();
                isFileSuccessfullyRead = true;
            } catch (FileNotFoundException e) {
                // creates default score file
                System.out.println("data/score.hsc not found.");
                createScoreFile();
                System.out.println("New score.hsc file has been created.");
            } catch (NoSuchElementException e) {
                isFileSuccessfullyRead = true; // technically not successful but it prevents infinite loop
                System.out.println("Something bad happened: " + e.getMessage());
            }
        }
    }
    
    public void createScoreFile() {
        FileWriter fw = null;
        try {
            File scoreFile = new File("data/score.hsc");
            Scanner fileSc = new Scanner(scoreFile);
            // prints debug info to check for score.hsc's existence
            if (scoreFile.isFile()) { // score.hsc exists and is not a directory
                System.out.println("File score.hsc already exists");
            }
            
            // writes default player info to new file if file is empty
            BufferedReader br = new BufferedReader(new FileReader("data/score.hsc"));
            if (br.readLine() == null) {
                fw = new FileWriter("data/score.hsc", true);
                fw.write("Player1 9001\n");
                fw.write("Player2 5000\n");
                fw.write("Player3 4000\n");
                fw.write("Player1 2000\n");
                fw.write("Player3 100\n");
                fw.flush();
                fw.close();
                System.out.println("File score.hsc is empty. Default scores added.");
            } else {
                System.out.println("File score.hsc already has data. It has not been modified.");
            }
        } catch (IOException e) {
            System.err.println("Cannot write data to \"score.hsc\": " + e.getMessage());
        }
    }

    public ArrayList<Player> getTop5Players() {
        return top5Players;
    }

    public void setTop5Players(ArrayList<Player> top5Players) {
        this.top5Players = top5Players;
    }
    
    public void updateTop5Players(ArrayList<Player> players) {
        ArrayList<Player> top5 = players;
        Comparator<Player> playerComparator = (Player p1, Player p2) -> {
            int score1 = p1.getScore();
            int score2 = p2.getScore();
            if (score1 > score2) {
                return 1;
            } else if (score1 == score2) {
                return 0;
            } else {
                return -1;
            }
        };
        top5.sort(playerComparator); // sort ascendingly
        Collections.reverse(top5);
        
        for (int i = top5.size() - 1; i > 4; i--) {
            top5.remove(i);
        }
        setTop5Players(top5);
    }
    
    public int getPlayerIndex(String playerName) {
        for (int i = 0; i < top5Players.size(); i++) {
            if (top5Players.get(i).getName().equals(playerName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isLevelCompleted() {
        return this.question.isCompleted();
    }

    public boolean isGameOver() {
        return this.hangMan.getState() == 9;
    }

    public boolean isCorrect() {
        return question.getSecretString().contains(Character.toString(playerChoice));
    }

    public void gameOver() {
        // if player score is amongst the top 5 high scores
        // prompts to save the player name and score
    }

    public GameInstance() {
        mainGame = this;
        this.score = 0;
        this.level = 0;
        this.player = new Player("", 0);
        this.question = new Question("ONTARIO");
        this.top5Players = new ArrayList<>();
        this.hangMan = new Hangman();
        readScoreFile();
    }

    public void reset() {
        this.score = 0;
        this.level = 0;
        // gets random string from file
        question.resetQuestion("HIKIKOMORI");
        
    }
    
    public void nextLevel() {
        question.resetQuestion("SUPERIORITY");
        setDifficulty(difficulty);
    }

}
