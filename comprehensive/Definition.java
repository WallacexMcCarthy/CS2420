package comprehensive;

/**
 * this class is a definition for a word that contains a part of speech and a description
 * @version 12/2/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class Definition implements Comparable<Definition>{
    private final String partOfSpeech;
    private String description;

    /**
     * constructor for a definition
     * @param partOfSpeech the part of speech for the definition
     * @param description the description
     */
    public Definition(String partOfSpeech, String description) {
        this.partOfSpeech = partOfSpeech;
        this.description = description;
    }

    /**
     * gets the part of speech instance variable
     * @return the part of speech associated with this definition
     */
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * makes a simple string containing the part of speech and description
     * @return a string containing this definitions information
     */
    public String toString() {
        return partOfSpeech + "." + "\t" + description;
    }

    /**
     * this method creates an alternate string representation to be used when writing files for the Glossary class
     * @return a string containing this definitions information
     */
    public String toStringWithRegex(){
        return partOfSpeech + "::" + description;
    }

    /**
     * changes this definitions description
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns 1 if this part of speech is greater
     * returns -1 if the other part of speech is greater
     * if parts of speech are equal, returns the string comparison of this and the other's description
     */
    @Override
    public int compareTo(Definition o) {
        int compare = this.partOfSpeech.compareTo(o.partOfSpeech);
        if(compare == 0) {
            return this.description.compareTo(o.description);
        }
        return compare;
    }
}
