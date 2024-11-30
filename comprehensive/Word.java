package comprehensive;

import java.util.HashSet;
import java.util.TreeSet;

public class Word implements Comparable<Word>{
    private TreeSet<Definition> definitions;
    public int numberOfDefinitions;
    private String name;
    private HashSet<String> partsOfSpeech;

    public Word(String name, Definition definition) {
        this.name = name;
        partsOfSpeech = new HashSet<>();
        partsOfSpeech.add(definition.getWordType());
        definitions = new TreeSet<>();
        definitions.add(definition);
        numberOfDefinitions++;
    }

    public void addDefinition(String type, String def) {
        definitions.add(new Definition(type, def));
        partsOfSpeech.add(type);
        numberOfDefinitions++;
    }

    public void deleteDefinition(int i) {
        int counter = 1;
        for (Definition definition : definitions) {
            if (counter == i) {
                removeFromDefinitions(definition);
                break;
            }
        }
        numberOfDefinitions--;
    }

    public void updateDefinition(int i, String newDef) {
        int counter = 1;
        for (Definition definition : definitions) {
            if (counter == i) {
                String type = definition.getWordType();
                removeFromDefinitions(definition);
                definitions.add(new Definition(type, newDef));
            }
            counter++;
        }
    }

    public String getDefinitions() {
        String result = "";
        int counter = 1;
        for (Definition definition : definitions) {
            result += counter + ". " + definition;
            if (counter != definitions.size()) {
                result += "\n";
            }
            counter++;
        }
        return result;
    }

    public String getPartsOfSpeech() {
        String result = "";
        for (String s : partsOfSpeech) {
            result += s + "\n";
        }
        return result;
    }

    public String toString() {
        String result = name + "\n";
        result += this.getDefinitions();
        return result;
    }

    @Override
    public int compareTo(Word o) {
        return this.name.compareTo(o.name);
    }

    private void removeFromDefinitions(Definition definition) {
        definitions.remove(definition);
    }
}
