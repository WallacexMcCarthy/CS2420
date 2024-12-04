package comprehensive;

import java.io.*;
import java.util.*;

/**
 * this class represents a glossary that can be updated changed as needed
 * it is created through a .txt file. the format of the file is assumed to be correct for how it is read
 * uses a hashmap for some operations and a TreeSet for others
 * @version 12/2/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class Glossary {

    private final TreeSet<String> words;
    private final HashMap<String, Word> dataMap;
    private int definitions;
    private final HashSet<String> partsOfSpeech;

    /**
     * constructor for a glossary using a filename as the path to a file
     * @param filename the file to be read
     */
    public Glossary (String filename) {
        words = new TreeSet<>();
        this.dataMap = new HashMap<>();
        this.definitions = 0;
        this.partsOfSpeech = new HashSet<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineData = line.split("::");
                this.addItem(lineData[0], lineData[1], lineData[2]);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    /**
     * checks to see if a word is contained in the glossary
     * @param word the word being checked for
     * @return boolean for whether it exists in the glossary
     */
    public boolean containsWord(String word) {
        return dataMap.containsKey(word);
    }

    /**
     * similar to a toString method, this method returns a string with various information about the glossary
     * @return a string containing information about the glossary
     */
    public String getMetaData(){
        String out = "";
        out += "words: " + dataMap.size() + "\n";
        out += "definitions: " + definitions + "\n";
        if (dataMap.isEmpty()) {
            out += "definitions per word: 0.00";
        } else {
            out += "definitions per word: " +
                    String.format("%.2f", Math.floor((float)definitions / dataMap.size() * 100) / 100) + "\n";
        }
        out += "parts of speech: " + partsOfSpeech.size() + "\n";
        if (dataMap.isEmpty()) {
            out += "first word: \n";
            out += "last word: \n";
            return out;
        }
        out += "first word: " + words.first() + "\n";
        out += "last word: " + words.last() + "\n";
        return out;
    }

    /**
     * gets a set of words in a range defined by the inputs inclusive
     * @param word1 the starting word
     * @param word2 the ending word
     * @return a set of strings
     */
    public Set<String> getWordsInRange(String word1, String word2){
        if(word1.compareTo(word2) > 0) {
            return Collections.singleton("");
        }
        if (word2.compareTo(words.first()) < 0 || word1.compareTo(words.last()) > 0) {
            return Collections.singleton("");
        }
        return words.subSet(words.ceiling(word1), true, words.floor(word2), true);
    }

    /**
     * gets a word's toString representation from the glossary if it exists in the glossary
     * @param word the word to get
     * @return a string containing the word's information
     */
    public String getWord(String word){
        if (!dataMap.containsKey(word)) {
            return word + " not found in glossary";
        }
        return dataMap.get(word).toString();
    }

    /**
     * gets a word's definitions in order lexicographically and a number indicating each definition's sorted position
     * @param word the word to get from
     * @return a string with all the word's definitions
     */
    public String getWordsNumberedDefinitions(String word){
        if (!dataMap.containsKey(word)) {
            return word + " not found in glossary";
        }
        return "Definitions for " + word + ": \n" + dataMap.get(word).getNumberedDefinitions();
    }

    /**
     * gets the number of definitions a word contains
     * @param word the word to get from
     * @return the number of definitions the word has
     */
    public int getWordsNumberOfDefinitions(String word){
       return dataMap.get(word).numberOfDefinitions;
    }

    /**
     * gets the first word's string representation lexicographically in the glossary
     * @return the first word in the treeset
     */
    public String getFirstWord(){
        try{
            return dataMap.get(words.first()).toString();
        } catch (Exception e) {
        }
        return "";
    }
    /**
     * gets the last word's string representation lexicographically in the glossary
     * @return the last word in the treeset
     */
    public String getLastWord(){
        try{
            return dataMap.get(words.last()).toString();
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * gets a word's parts of speech as a string
     * @param word the word toString representation get from
     * @return the word's parts of speech
     */
    public String getPartsOfSpeech(String word){
        return dataMap.get(word).getPartsOfSpeech();
    }

    /**
     * updates one of a word's definition
     * @param name the word to update
     * @param i the definition to update
     * @param def the new definition
     */
    public void updateWordDefinition(String name, int i, String def) {
        dataMap.get(name).updateDefinition(i, def);
    }

    /**
     * deletes a definition from a word
     * @param name the word to delete from
     * @param i the definition to delete
     */
    public boolean deleteWordDefinition(String name, int i) {
        Word word = dataMap.get(name);
        word.deleteDefinition(i);
        if(word.numberOfDefinitions == 0) {

            dataMap.remove(name);
            words.remove(name);
            return true;
        }
        return false;
    }

    /**
     * saves the glossaries current state into a .txt file
     * saves in the format that allows the glossary to be reused
     * @param fileName the file to save to
     */
    public void saveToDirectory(String fileName){
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName))){
            StringBuilder data = new StringBuilder();
            int count = 0;
            for(Word word : dataMap.values()){
                if (count > 0){
                    data.append("\n").append(word.toStringWithRegex());
                }else{
                    data.append(word.toStringWithRegex());
                }
                count ++;
            }
            fileWriter.write(data.toString());
        }catch(Exception e){
            System.out.println(("Bad FileName"));
        }
    }

    /**
     * gets a set of all the words in the glossary without order
     * @return a set containing all words in the glossary
     */
    public Set<String> getAllWords(){
        return dataMap.keySet();
    }

    /**
     * adds a new definition to a word
     * @param word the word to add to
     * @param type the part of speech for the new definition
     * @param definition the new definition
     */
    public void addDefinitionToWord(String word, String type, String definition) {
        this.addItem(word, type, definition);
    }

    /**
     * private helper method that adds a definition to a word if it exists in the glossary
     * if the word does not exist already, it is added with the definition
     * @param word the word to add to
     * @param type the part of speech for the definition
     * @param definition the new definition
     */
    private void addItem(String word, String type, String definition) {
        if(!containsWord(word)) {
            words.add(word);
            dataMap.put(word, new Word(word, new Definition(type, definition)));
        } else {
            dataMap.get(word).addDefinition(type, definition);
        }
        partsOfSpeech.add(type);
        definitions++;
    }

}
