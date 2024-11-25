package comprehensive;

import java.util.ArrayList;

public class Word {
    private ArrayList<Definition> definitions;
    private int numberOfDefinitions;
    private String name;

    public Word(String name, Definition definition) {
        definitions = new ArrayList<>();
        this.name = name;
        definitions.add(definition);
        numberOfDefinitions++;
    }

    public void addDefinition(String type, String def) {
        definitions.add(new Definition(type, def));
        numberOfDefinitions++;
    }

    public void deleteDefinition(int i) {
        definitions.remove(i-1);
        numberOfDefinitions--;
    }

    public void updateDefinition(int i, String def) {
        definitions.get(i-1).setDescription(def);
    }

    public String getDefinitions() {
        String result = "";
        for (int i = 0; i < definitions.size(); i++) {
            result += i + ". ";
            result += definitions.get(i).toString();
            if (i != definitions.size()-1) {
                result += "\n";
            }
        }
        return result;
    }

    public String toString() {
        String result = name + "\n";
        for (int i = 0; i < definitions.size(); i++) {
            result += "\t\t" + definitions.get(i).toString();
            if (i != definitions.size()-1) {
                result += "\n";
            }
        }
        return result;
    }
}
