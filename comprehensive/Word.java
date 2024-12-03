package comprehensive;

/**
 * this class is a word object that contains a name and 1 or more definitions in a sorted order
 * @version 12/2/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class Word implements Comparable<Word>{
    private final SortedIndexableSet<Definition> definitions;
    public int numberOfDefinitions;
    private final String name;
    private final SortedIndexableSet<String> partsOfSpeech;

    /**
     * constructor for a word
     * @param name the word
     * @param definition the word's definition
     */
    public Word(String name, Definition definition) {
        this.name = name;
        partsOfSpeech = new SortedIndexableSet<>();
        definitions = new SortedIndexableSet<>();
        partsOfSpeech.add(definition.getPartOfSpeech());
        definitions.add(definition);
        numberOfDefinitions++;
    }

    /**
     * adds a definition to the word
     * @param type part of speech of the definition
     * @param def the description
     */
    public void addDefinition(String type, String def) {
        definitions.add(new Definition(type, def));
        partsOfSpeech.add(type);
        numberOfDefinitions++;
    }

    /**
     * removes a definition from the word
     * @param i the index of the definition to be removed
     */
    public void deleteDefinition(int i) {
        definitions.remove(definitions.getByIndex(i-1));
        numberOfDefinitions--;
    }

    /**
     * updates one of the word's definitions
     * @param i the index of the definition to be updated
     * @param newDef the new description
     */
    public void updateDefinition(int i, String newDef) {
        definitions.getByIndex(i-1).setDescription(newDef);
    }

    /**
     * gets a string with all of this word's definitions in sorted order
     * @return a string with all of this word's definitions
     */
    public String getDefinitions() {
        String result = "";
        for (int i = 0; i < definitions.size(); i++) {
            result += "\t" + definitions.getByIndex(i);
            if (i+1 != definitions.size()) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * gets a string with all of this word's definitions and a number indicating each definition's sorted position
     * @return a string with all of this word's definitions
     */
    public String getNumberedDefinitions() {
        String result = "";
        for (int i = 0; i < definitions.size(); i++) {
            result += i+1 + ". " + definitions.getByIndex(i);
            if (i+1 != definitions.size()) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * gets a string containing all the parts of speech used by this word's definitions
     * @return a string containing this word's parts of speech
     */
    public String getPartsOfSpeech() {
        String result = "";
        for (int i = 0; i < partsOfSpeech.size(); i++) {
            result +=  "\t" + partsOfSpeech.getByIndex(i) + "\n";
        }
        return result;
    }

    /**
     * creates a simple string representation of this word and its definitions
     * @return a string containing this word's information
     */
    public String toString() {
        String result = name + "\n";
        result += this.getDefinitions();
        return result;
    }

    /**
     * creates a string that is in the format needed for a Glossary object to read and add
     * @return an alternate string representation
     */
    public String toStringWithRegex() {
        String out = "";
        int count = 0;
        for (int i = 0; i < definitions.size(); i++) {
            if (count > 0){
                out += "\n";
            }
            out += this.name;
            out += "::";
            out += definitions.getByIndex(i).toStringWithRegex();
            count ++;
        }
        return out;
    }

    /**
     * returns the lexicographical difference between this word's name and the other's name
     * @param o the object to be compared.
     * @return an int for the lexicographical difference
     */
    @Override
    public int compareTo(Word o) {
        return this.name.compareTo(o.name);
    }

}
