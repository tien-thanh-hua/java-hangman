/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author CE171454 Hua Tien Thanh
 */
public class Question {

    private String secretString = "";
    private String userString = "";
    private String topic = "";
    private HashMap<Character, ArrayList<Integer>> charactersCount;

    public Question(String secretString, String topic) {
        this.secretString = secretString;
        this.topic = topic;
        this.resetUserString();

        this.charactersCount = new HashMap<>();
        for (int i = 0; i < getLength(); i++) {
            Character currentChar = secretString.charAt(i);
            ArrayList<Integer> newArr = new ArrayList<>();
            newArr.add(i);
            charactersCount.put(currentChar, newArr);
        }
    }

    public String getSecretString() {
        return secretString;
    }

    public void setSecretString(String secretString) {
        this.secretString = secretString;
    }

    public String getUserString() {
        return userString;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public int getLength() {
        return this.secretString.length();
    }

    public HashMap<Character, ArrayList<Integer>> getCharactersCount() {
        return charactersCount;
    }

    public void setCharactersCount(HashMap<Character, ArrayList<Integer>> charactersCount) {
        this.charactersCount = charactersCount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isCompleted() {
        return secretString.equalsIgnoreCase(userString);
    }
    
    public void updateString(char c) {
        String newStr = "";
        for (int i = 0; i < getLength(); i++) {
            if (secretString.charAt(i) == c) { // has matching character
                if (userString.charAt(i) == '_') { // character is not revealed yet
                    newStr += c;
                } else { // character is already revealed
                    newStr += userString.charAt(i); // copies character of previous string
                }
            } else { // has no matching character
                newStr += userString.charAt(i); // copies character of previous string
            }
        }
        this.setUserString(newStr);
    }
    
    public void resetUserString() {
        this.setUserString(String.join("", Collections.nCopies(secretString.length(), "_")));
    }
    
    public void resetQuestion(String secretString) {
        this.setSecretString(secretString);
        this.resetUserString();
        this.charactersCount.clear();
        for (int i = 0; i < getLength(); i++) {
            Character currentChar = secretString.charAt(i);
            ArrayList<Integer> newArr = new ArrayList<>();
            newArr.add(i);
            charactersCount.put(currentChar, newArr);
        }
    }
}
