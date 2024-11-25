package comprehensive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Glossary {

    private TreeSet<Word> data;
    private int definitions;
    private HashSet<String> partsOfSpeach;

    public Glossary (String filename) {
        this.data = new TreeSet<Word>();
        this.definitions = 0;
        this.partsOfSpeach = new HashSet<>();
        Scanner scanner = new Scanner (filename);
        while (scanner.hasNextLine()) {
            this.definitions++;
            String line = scanner.nextLine();
            String[] words = line.split("::");
            data.add(new Word(words[0], new Definition(words[1], words[2])))
            partsOfSpeach.add(words[1]);
        }
        scanner.close();
    }

    private String getMetaData(){
        String out = "";
        out += "words: " + data.size() + "\n";
        out += "definitions: " + definitions + "\n";
        out += "definitions per word: " + definitions / data.size() + "\n";
        out += "parts of speech: " + partsOfSpeach.size() + "\n";
        out += "first word: " + data.first() + "\n";
        out += "last word: " + data.last() + "\n";
        return out;
    }
    private String getWordsInRange(String word1, String word2){
        String out = "";
        TreeSet<String> words = data.subSet(data.floor(word1), data.ceiling(word2));
    }

    private String getWord(String word){
        return data.floor(word);
    }

    private String getFirstWord(){
        try{
            return data.first();
        } catch (Exception e) {
            return "This dictionary is already empoty";
        }
    }
    private String getLastWord(){
        try{
            return data.getLast();
        } catch (Exception e) {
            return "This dictionary is already empoty";
        }
    }

    private String getPartsOfSpeach(String word){
        String out = "";
        Word text = data.floor(word);

    }


}
