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
                dataMap.put(lineData[0], new Word(lineData[0], new Definition(lineData[1], lineData[2])));
                partsOfSpeach.add(lineData[1]);
            }
            scanner.close();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }

        System.out.println(words);
    }

    private String getMetaData(){
        String out = "";
        out += "words: " + dataMap.size() + "\n";
        out += "definitions: " + definitions + "\n";
        out += "definitions per word: " + definitions / dataMap.size() + "\n";
        out += "parts of speech: " + partsOfSpeach.size() + "\n";
        out += "first word: " + words.first() + "\n";
        out += "last word: " + words.last() + "\n";
        return out;
    }
    private Set<String> getWordsInRange(String word1, String word2){
        String out = "";
        Set<String> names = words.subSet(words.floor(word1), words.ceiling(word2));
        return names;
    }

    private String getWord(String word){
        return words.floor(word);
    }

    private String getFirstWord(){
        try{
            return words.first();
        } catch (Exception e) {
        }
        return null;
    }
    private String getLastWord(){
        try{
            return words.last();
        } catch (Exception e) {
        }
        return null;
    }

    private String getPartsOfSpeech(String word){
        return dataMap.get(words.floor(word)).getPartsOfSpeech();
    }


}
