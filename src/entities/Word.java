/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 * The basic object that stores words' word and topic.<br>
 * Not to be confused with Question object which is a more complex version of Word
 * that has more attributes and methods.<br>
 * This object should be used when importing lists of words from external files.
 * @author CE171454 Hua Tien Thanh
 */
public class Word {
    private String word;
    private String topic;
    
    public Word(String word, String topic) {
        this.word = word;
        this.topic = topic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
}
