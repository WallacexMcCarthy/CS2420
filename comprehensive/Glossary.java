package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Glossary {

    private TreeSet<String> words;
    private HashMap<String, Word> dataMap;
    private int definitions;
    private HashSet<String> partsOfSpeach;

    public Glossary (String filename) throws FileNotFoundException {
        words = new TreeSet<>();
        this.dataMap = new HashMap<>();
        this.definitions = 0;
        this.partsOfSpeach = new HashSet<>();
        try {
            Scanner scanner = new Scanner (new File(filename));
            while (scanner.hasNextLine()) {
                this.definitions++;
                String line = scanner.nextLine();
                String[] lineData = line.split("::");
                words.add(lineData[0]);
                if(dataMap.containsKey(lineData[0])) {
                    dataMap.get(lineData[0]).addDefinition(lineData[1], lineData[2]);
                } else {
                    dataMap.put(lineData[0], new Word(lineData[0], new Definition(lineData[1], lineData[2])));
                }
                partsOfSpeach.add(lineData[1]);
            }
            scanner.close();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    public String getMetaData(){
        String out = "";
        out += "words: " + dataMap.size() + "\n";
        out += "definitions: " + definitions + "\n";
        out += "definitions per word: " + (float)definitions / dataMap.size() + "\n";
        out += "parts of speech: " + partsOfSpeach.size() + "\n";
        out += "first word: " + words.first() + "\n";
        out += "last word: " + words.last() + "\n";
        return out;
    }
    public Set<String> getWordsInRange(String word1, String word2){
        return words.subSet(words.ceiling(word1), words.ceiling(word2));
    }

    public String getWord(String word){
        if (!dataMap.containsKey(word)) {
            return word + " not found in glossary";
        }
        return dataMap.get(word).toString();
    }

    public String getWordsDefinitions(String word){
        if (!dataMap.containsKey(word)) {
            return word + " not found in glossary";
        }
        return "Definitions for " + word + ": \n" + dataMap.get(word).getDefinitions();
    }
    public int getWordsNumberOfDefinitions(String word){
       return dataMap.get(word).numberOfDefinitions;
    }


    public String getFirstWord(){
        try{
            return dataMap.get(words.first()).toString();
        } catch (Exception e) {
        }
        return null;
    }
    public String getLastWord(){
        try{
            return dataMap.get(words.last()).toString();
        } catch (Exception e) {
        }
        return null;
    }

    public String getPartsOfSpeech(String word){
        return dataMap.get(words.floor(word)).getPartsOfSpeech();
    }

    public void updateWordDefinition(String name, int i, String def) {
        dataMap.get(name).updateDefinition(i, def);
    }


}
