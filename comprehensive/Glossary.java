package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                String line = scanner.nextLine();
                String[] lineData = line.split("::");
                this.addItem(lineData[0], lineData[1], lineData[2]);
            }
            scanner.close();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    public boolean containsWord(String word) {
        return dataMap.containsKey(word);
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
        return words.subSet(words.ceiling(word1), true, words.floor(word2), true);
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

    public void deleteWordDefinition(String name, int i) {
        Word word = dataMap.get(name);
        word.deleteDefinition(i);
        if(word.numberOfDefinitions == 0) {
            dataMap.remove(name);
            words.remove(name);
        }
    }

    public void saveToDirectory(String fileName){
        try(FileWriter fileWriter = new FileWriter(fileName)){
            StringBuilder data = new StringBuilder();
            for(String word : words){
                data.append(word).append("\n");
            }
            fileWriter.write(data.toString());
        }catch(Exception e){
            System.out.println(("Bad FileName"));
        }
    }

    public void addDefinitionToWord(String name, String type, String definition) {
        this.addItem(name, type, definition);
    }

    private void addItem(String name, String type, String definition) {
        if(!dataMap.containsKey(name)) {
            dataMap.put(name, new Word(name, new Definition(type, definition)));
            words.add(name);
        } else {
            dataMap.get(name).addDefinition(type, definition);
        }
        partsOfSpeach.add(type);
        definitions++;
    }

}
