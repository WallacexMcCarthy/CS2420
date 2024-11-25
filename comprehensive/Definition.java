package comprehensive;

public class Definition {
    private String wordType;
    private String description;

    public Definition(String wordType, String description) {
        this.wordType = wordType;
        this.description = description;
    }

    public String getWordType() {
        return wordType;
    }
    public String toString() {
        return wordType + "." + "\t" + description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
