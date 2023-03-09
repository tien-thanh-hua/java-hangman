/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import entities.Hangman;
import entities.Player;
import entities.Question;

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

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        setDifficultyFactor(difficulty);
    }

    public int getDifficultyFactor() {
        return difficultyFactor;
    }

    public void setDifficultyFactor(int difficultyFactor) {
        this.difficultyFactor = difficultyFactor;
    }

    public void setDifficultyFactor(String difficulty) {
        switch (difficulty) {
            case "easy":
                setDifficultyFactor(1);
                break;
            case "normal":
                setDifficultyFactor(4);
                break;
            case "hard":
                setDifficultyFactor(8);
                break;
            case "asian":
                setDifficultyFactor(32);
                break;
            default:
                setDifficultyFactor(0);
                break;
        }
    }

    public void updateUserString() {
        question.updateString(playerChoice);
    }

    public void increaseScore() {
        this.setScore(this.getScore() + 100 * difficultyFactor);
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
        //newScoreDialog();

    }

    public GameInstance(String difficulty) {
        mainGame = this;
        this.score = 0;
        this.level = 0;
        this.question = new Question("ONTARIO");
        this.hangMan = new Hangman(difficulty);
        setDifficultyFactor(difficulty);
    }

    public void reset() {
        score = 0;
        level = 0;
        // gets random string from file
        question.resetQuestion("HIKIKOMORI");
    }

}
