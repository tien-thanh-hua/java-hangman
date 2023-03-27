/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Hangman;
import entities.Player;
import entities.Question;
import entities.Word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class GameInstance {
    private int score;
    private int level;
    private String difficulty = "";
    private int difficultyFactor;

    private Hangman hangMan;
    private Question question;
    
    HashMap<String, Word> normalWords;
    HashMap<String, Word> hardWords;
    private boolean isListEmpty;
    
    private ArrayList<Player> top5Players;
    private Player player;
    private char playerChoice;

    /**
     * Creates a new GameInstance.
     */
    public GameInstance() {
        
        this.hangMan = new Hangman();
        this.score = 0;
        this.level = 0;
        
        this.normalWords = new HashMap<>();
        this.hardWords = new HashMap<>();
        this.top5Players = new ArrayList<>();
        this.player = new Player("", 0); // current player data is empty
        
        isListEmpty = true;
        readScoreFile();
        readWordFiles();
        // Uncomment the line below to use the default word list (for testing).
        // If so, comment the line above this to prevent loading both normal and
        // default word lists.
        //readDefaultWordFile();
    }

    /**
     * Increases the current game level by 1.
     */
    public void increaseLevel() {
        this.setLevel(level + 1);
    }

    // getters and setters

    /**
     * Returns the current player's score.
     * @return the current player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Directly updates the current player's score. Should be used within
     * increaseScore().
     * @param score the new player's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the current game's level.
     * @return the current game's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Updates the current game's level.
     * @param level the new level of the current game
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the player's latest chosen character.
     * @return the player's latest chosen character
     */
    public char getPlayerChoice() {
        return playerChoice;
    }

    /**
     * Updates the player's latest chosen character.
     * @param playerChoice the new character to be updated
     */
    public void setPlayerChoice(char playerChoice) {
        this.playerChoice = playerChoice;
    }

    /**
     * Returns the Question object of this GameInstance.
     * There can only be one Question instance running per game.
     * @return the Question instance
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Updates the Question instance of this GameInstance.
     * Note that this method is called only once (when the initial Question object
     * is null). Its primary goal is to initialize the Question instance with
     * actual data, all subsequent levels will only update its data without calling
     * this method.
     * @param question the initialized Question object to be set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Returns the Hangman instance of this GameInstance.
     * Mostly used as an argument to the HangmanCanvas constructor.
     * @return the Hangman instance
     */
    public Hangman getHangMan() {
        return hangMan;
    }

    /**
     * Replaces the current Hangman of this GameInstance.
     * @param hangMan the new Hangman instance
     */
    public void setHangMan(Hangman hangMan) {
        this.hangMan = hangMan;
    }

    /**
     * Returns the current game's difficulty.
     * @return the current game's difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty for the game, which sets the initial state of the
     * Hangman object.<br>
     * If this method is called after the difficulty has been set at least once,
     * then it will only update the difficulty, not resetting it.<br>
     * This method should only be used <strong>once</strong> per
     * play-through.<br>
     * Currently supported difficulties include:<br>
     * <ul>
     * <li>"easy": state = 0</li>
     * <li>"normal": state = 3</li>
     * <li>"hard": state = 3</li>
     * <li>"asian": state = 3</li>
     * </ul>
     * This method also initialize the current Hangman's state.
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

    /**
     * Returns the current game's difficulty factor. It is used in calculating
     * the user score (more specifically, how many points to increase per correct
     * guess).<br>
     * Difficulty factor depends on the game difficulty, as such this method
     * should only be called after the difficulty has been updated.
     * <ul>
     * <li>"easy": difficultyFactor = 1</li>
     * <li>"normal": difficultyFactor = 2</li>
     * <li>"hard": difficultyFactor = 3</li>
     * <li>"asian": difficultyFactor = 8</li>
     * </ul>
     * @return the current difficulty factor
     */
    public int getDifficultyFactor() {
        return difficultyFactor;
    }

    /**
     * Updates the current game's difficulty factor. It is used in calculating
     * the user score (more specifically, how many points to increase per correct
     * guess).<br>
     * Difficulty factor depends on the game difficulty, as such this method
     * should only be called after the difficulty has been updated.
     * <ul>
     * <li>"easy": difficultyFactor = 1</li>
     * <li>"normal": difficultyFactor = 2</li>
     * <li>"hard": difficultyFactor = 3</li>
     * <li>"asian": difficultyFactor = 8</li>
     * <li>Other values: difficultyFactor = 0</li>
     * </ul>
     * 
     * @param difficulty the updated difficulty used to determine the new
     * difficulty factor
     */
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

    /**
     * Directly updates the game's difficulty factor. Should be used within
     * setDifficultyFactor(String difficulty).
     * @param difficultyFactor the new integer value (factor)
     */
    public void setDifficultyFactor(int difficultyFactor) {
        this.difficultyFactor = difficultyFactor;
    }
    
    /**
     * Returns the list of 5 players with the highest score.
     * @return the top 5 list of Players
     */
    public ArrayList<Player> getTop5Players() {
        return top5Players;
    }

    /**
     * Checks whether the current level is completed by checking the level's
     * Question instance.
     * @return true if the question is completed (all hidden words have been
     * revealed), false otherwise
     */
    public boolean isLevelCompleted() {
        return this.question.isCompleted();
    }

    /**
     * Checks whether the current game is over by checking the Hangman instance.
     * @return true if the Hangman instance has state == 9 (the painting is
     * completed), false otherwise
     */
    public boolean isGameOver() {
        return this.hangMan.getState() == 9;
    }

    /**
     * Checks whether the player has chosen the correct letter.
     * @return true if the player's chosen letter matches one of the letters of
     * the Question's hidden word, false otherwise
     */
    public boolean isCorrect() {
        return question.getSecretString().contains(Character.toString(playerChoice));
    }    
    // end of getters and setters

    /**
     * Updates the current Player's name and score, then updates the top 5 list
     * with the new Player.
     * @param name the name of the player (read from user input)
     */
    public void savePlayer(String name) {
        player.setName(name);
        player.setScore(score);
        top5Players.add(player);
        updateTop5Players(top5Players);
    }

    /**
     * Updates the Question's userString property, replacing at least one of its
     * underscore characters with the player's (correctly) chosen character.
     */
    public void updateUserString() {
        question.updateString(playerChoice);
    }

    /**
     * Updates the current Player's score with difficulty factor taken into
     * account.
     */
    public void increaseScore() {
        this.setScore(this.getScore() + 100 * difficultyFactor);
    }
    
    /**
     * Extracts the topic from a file's name. Inputted file name must match the
     * correct format, otherwise the return value might be incorrect.
     * @param fileName the file's name that follows the format: topic_difficulty.hwd
     * @return the topic of the inputted word set
     */
    public String getTopicFromFileName(String fileName) {
        int stopIndex = fileName.indexOf('_');
        return fileName.substring(0, stopIndex);
    }

    /**
     * Checks whether normalWords or hardWords list is empty.
     * @return true if either list is empty, false if there is at least an
     * element in both lists
     */
    public boolean isListEmpty() {
        return isListEmpty;
    }

    /**
     * Reads all word files that has a topic. Since the default words file does
     * not have a topic in its name, it will not be read.
     */
    public void readWordFiles() {
        HashMap<String, Word> tmpNormalWords = new HashMap<>();
        HashMap<String, Word> tmpHardWords = new HashMap<>();
        
        String wordStr;
        String wordTopic;
        boolean isFileSuccessfullyRead = false;
        while (!isFileSuccessfullyRead) {
            try {
                File wordDir = new File("data/"); // sets the directory
                // gets all .hwd files inside folder 'data' EXCEPT words.hwd
                File[] wordFiles = wordDir.listFiles((d, name) -> (name.endsWith(".hwd") 
                        && !name.equals("words.hwd")));
                Scanner fileSc = null;
                
                for (File wordFile : wordFiles) {
                    fileSc = new Scanner(wordFile);
                    String wordDifficulty = wordFile.getName().contains("normal") ? "normal" : "hard";
                    while (fileSc.hasNext()) {
                        wordStr = fileSc.next().trim().toUpperCase();
                        wordTopic = getTopicFromFileName(wordFile.getName());

                        if (!(tmpNormalWords.containsKey(wordStr) 
                                && tmpHardWords.containsKey(wordStr))) { // word not existed yet
                            if (wordDifficulty.equals("normal")) {
                                tmpNormalWords.put(wordStr, new Word(wordStr, wordTopic));
                            } else {
                                tmpHardWords.put(wordStr, new Word(wordStr, wordTopic));
                            }
                        } else { // word already exists, log to console
                            System.out.println("Word \"" + wordStr + "\" in " 
                                    + wordFile.getName() + " has already existed.");
                        }
                    }
                }
                
                normalWords.putAll(tmpNormalWords);
                hardWords.putAll(tmpHardWords);
                isListEmpty = false;
                if (fileSc != null) {
                    fileSc.close();
                }
                isFileSuccessfullyRead = true;
            } catch (FileNotFoundException e) {
                // creates default score file
                System.out.println("File not found: " + e.getMessage());
                System.out.println("Default words.hwd file will be read instead.");
                readDefaultWordFile();
            } catch (NoSuchElementException e) {
                isFileSuccessfullyRead = true; // technically not successful but it prevents infinite loop
                System.out.println("Something bad happened: " + e.getMessage());
            }
        }
    }
    
    /**
     * Reads the default word file, in case readWordFiles() did not succesfully
     * read all word files.
     */
    public void readDefaultWordFile() {
        HashMap<String, Word> tmpNormalWords = new HashMap<>();
        HashMap<String, Word> tmpHardWords = new HashMap<>();

        String wordStr;
        String wordTopic;
        String wordDifficulty;
        boolean isFileSuccessfullyRead = false;
        while (!isFileSuccessfullyRead) {
            try {
                File wordFile = new File("data/words.hwd");
                Scanner fileSc = new Scanner(wordFile);
                while (fileSc.hasNext()) {
                    wordStr = fileSc.next().trim().toUpperCase();
                    wordTopic = fileSc.next();
                    wordDifficulty = fileSc.next();

                    if (!(tmpNormalWords.containsKey(wordStr) 
                                && tmpHardWords.containsKey(wordStr))) { // word not existed yet
                            if (wordDifficulty.equals("normal")) {
                                tmpNormalWords.put(wordStr, new Word(wordStr, wordTopic));
                            } else {
                                tmpHardWords.put(wordStr, new Word(wordStr, wordTopic));
                            }
                        } else { // word already exists, log to console
                            System.out.println("Word \"" + wordStr + "\" has already existed.");
                        }
                }
                
                normalWords.putAll(tmpNormalWords);
                hardWords.putAll(tmpHardWords);
                isListEmpty = false;
                fileSc.close();
                isFileSuccessfullyRead = true;
            } catch (FileNotFoundException e) {
                // creates default score file
                System.out.println("data/words.hwd not found.");
                createDefaultWordFile();
                System.out.println("Default words.hwd file has been created.");
            } catch (NoSuchElementException e) {
                isFileSuccessfullyRead = true; // technically not successful but it prevents infinite loop
                System.out.println("Something bad happened: " + e.getMessage());
            }
        }
    }

    /**
     * Creates a default word file, in case there are no default word file or
     * the existing one is corrupted.
     */
    public void createDefaultWordFile() {
        FileWriter fw = null;
        try {
            File wordsFile = new File("data/words.hwd");
            // prints debug info to check for words.hwd's existence
            if (wordsFile.isFile()) { // words.hwd exists and is not a directory
                System.out.println("File words.hwd already exists.");
            } else {
                if (wordsFile.createNewFile()) {
                    System.out.println("Empyt file words.hwd successfully created.");
                } else {
                    System.out.println("Failed to create words.hwd.");
                }
            }

            // writes default player info to new file if file is empty
            BufferedReader br = new BufferedReader(new FileReader("data/words.hwd"));
            if (br.readLine() == null) {
                fw = new FileWriter("data/words.hwd", true);
                fw.write("ontario cities\n");
                fw.write("university places\n");
                fw.write("flamingo animals\n");
                fw.write("bluewhale animals\n");
                fw.write("arachnophobia fears\n");
                fw.flush();
                fw.close();
                System.out.println("File words.hwd is empty. Default words added.");
            } else {
                System.out.println("File words.hwd already has data. It has not been modified.");
            }
        } catch (IOException e) {
            System.err.println("Cannot write data to \"words.hwd\": " + e.getMessage());
        }
    }

    /**
     * Reads the players' name and score data from a preexisting file. This
     * method will call createDefaultScoreFile() if there is no score file or the
     * existing one is corrupted.
     */
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
                createDefaultScoreFile();
                System.out.println("New score.hsc file has been created.");
            } catch (NoSuchElementException e) {
                isFileSuccessfullyRead = true; // technically not successful but it prevents infinite loop
                System.out.println("Something bad happened: " + e.getMessage());
            }
        }
    }

    /**
     * Creates a default score file, in case there are no score file or
     * the existing one is corrupted.
     */
    public void createDefaultScoreFile() {
        FileWriter fw = null;
        try {
            File scoreFile = new File("data/score.hsc");
            // prints debug info to check for score.hsc's existence
            if (scoreFile.isFile()) { // score.hsc exists and is not a directory
                System.out.println("File score.hsc already exists.");
            } else {
                if (scoreFile.createNewFile()) {
                    System.out.println("Empty file score.hsc successfully created.");
                } else {
                    System.out.println("Failed to create score.hsc.");
                }
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

    /**
     * Called each time the top 5 score list is updated. This method will save a
     * snapshot of the list to the score file. If there is no score file, it will
     * call createDefaultScoreFile().
     */
    public void updateScoreFile() {
        FileWriter fw = null;
        try {
            File scoreFile = new File("data/score.hsc");
            // prints debug info to check for score.hsc's existence
            if (scoreFile.isFile()) { // score.hsc exists and is not a directory
                System.out.println("File score.hsc already exists.");
            } else {
                if (scoreFile.createNewFile()) {
                    System.out.println("Empty file score.hsc successfully created.");
                } else {
                    System.out.println("Failed to create score.hsc.");
                }
            }
            
            fw = new FileWriter("data/score.hsc", true);
            PrintWriter pw = new PrintWriter("data/score.hsc");
            pw.close();
            for (Player player : top5Players) {
                fw.write(player.getName() + " "  + player.getScore() + "\n");
            }
            fw.flush();
            fw.close();
            System.out.println("New high scores saved to score.hsc");
        } catch (IOException e) {
            System.err.println("Cannot write data to \"score.hsc\": " + e.getMessage());
        }
    }
    
    /**
     * Updates the current top 5 players list with a newer one. Should be called
     * within updateTop5Players(ArrayList&lt;Player&gt; players).
     * @param top5Players the new List of Players
     */
    public void setTop5Players(ArrayList<Player> top5Players) {
        this.top5Players = top5Players;
    }

    /**
     * Sorts the top 5 players list in descending score order, and then removes
     * all excessive entries (if any) so that the final list consists of no more
     * than 5 players.
     * @param players the unsorted top 5 player list taken after saving
     * a new Player's score
     */
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

    /**
     * Returns the index of a player in the top 5 player list.
     * @param playerName the name of the Player to be searched
     * @return the index of the matching Player name, -1 if not found.
     */
    public int getPlayerIndex(String playerName) {
        for (int i = 0; i < top5Players.size(); i++) {
            if (top5Players.get(i).getName().equals(playerName)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Resets the current game's score and level. This should only be used when
     * starting a new game.
     */
    public void reset() {
        this.score = 0;
        this.level = 0;
        normalWords.clear();
        hardWords.clear();
        readWordFiles();
    }

    /**
     * Resets the game's Hangman and changes the Question to a random one.
     */
    public void nextLevel() {
        removeWordFromList();
        setDifficulty(difficulty);
        setRandomQuestion(difficulty);
    }

    /**
     * Generates a random Word from a HashMap&lt;String, Word&gt; based on input
     * difficulty, then creates/updates the Question.<br>
     * For example, if the game difficulty is "normal", it will only get questions
     * from a HashMap containing only words of "normal" difficulty.<br>
     * This method should only be called after a difficulty has been chosen.
     * @param difficulty The difficulty of the random question.
     */
    public void setRandomQuestion(String difficulty) {
        Random randomIndexGenerator = new Random(); // initializes random class
        ArrayList<String> wordList = new ArrayList<>(); // stores keys of HashMaps
        int index;
        
        Word randomWord;
        String wordStr = "";
        String wordTopic = "";
        if (difficulty.equals("easy") || difficulty.equals("normal")) {
            wordList.addAll(normalWords.keySet()); // stores keys from normalWords
            System.out.println("Current list size: " + wordList.size());
            index = randomIndexGenerator.nextInt(wordList.size());
            randomWord = normalWords.get(wordList.get(index));
            wordStr = randomWord.getWord();
            wordTopic = randomWord.getTopic();
        } else {
            wordList.addAll(hardWords.keySet()); // stores keys from normalWords
            index = randomIndexGenerator.nextInt(wordList.size());
            System.out.println("Current list size: " + wordList.size());
            randomWord = hardWords.get(wordList.get(index));
            wordStr = randomWord.getWord();
            wordTopic = randomWord.getTopic();
        }
        
        if (this.question == null) { // no Question instance yet (when starting game)
            setQuestion(new Question(wordStr, wordTopic));
        } else { // after first game
            this.question.resetQuestion(wordStr, wordTopic);
        }
        // Uncomment next line to enable cheating. Shame on you!
        System.out.println("Hidden word: " + wordStr);
    }
    
    /**
     * Removes the current word from the list of words (chosen word list depends
     * on the current difficulty).
     * Does nothing if difficulty is "Asian".<br>
     * This method should be called after the level is completed, but before the
     * "Next Level" button is unlocked.
     */
    public void removeWordFromList() {
        if (!isListEmpty) // either list is not empty yet
            switch (difficulty) {
                case "easy":
                case "normal":
                    normalWords.remove(this.question.getSecretString());
                    break;
                case "hard":
                    hardWords.remove(this.question.getSecretString());
                    break;
            }
        // When this code block is run, the next removeWordFromList 
        // will not be run.
        // Under most circumstances this is redundant thanks to isLastLevel().
        // However, this works as a safety measure in case isLastLevel() does not
        // run properly.
        if (normalWords.isEmpty() || hardWords.isEmpty()) {
            isListEmpty = true;
        }
    }

    /**
     * Checks if the current game level is the final level, based on the number
     * of remaining words in either normalWords or hardWords.
     * 
     * @return true if either lists have a size of 1, false otherwise
     */
    public boolean isLastLevel() {
        return (normalWords.size() == 1 || hardWords.size() == 1);
    }
}
