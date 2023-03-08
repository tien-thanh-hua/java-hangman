/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame_group3_se1704;

import java.util.ArrayList;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class Player {
    private String name;
    private ArrayList<Integer> scores;

    public Player(String name, ArrayList<Integer> scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }
    
    /**
     * Gets a certain score of the player.
     * @param index (The cardinal ranking of the score) - 1
     * @return (int) The (index + 1)<sup>th</sup> score.
     */
    public Integer getScore(int index) {
        return scores.get(index);
    }
    
    public void addScore(int score) {
        scores.add(score);
        scores.sort((Integer o1, Integer o2) ->
                (o1 > o2) ? 1 : -1 // sort in descending order
        );
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
        
}
