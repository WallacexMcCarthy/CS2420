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
                definition.setDescription(newDef);
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
    public String toStringRegix(){
        String out = "";
        int count = 0;
        for (Definition definition : definitions) {
            if (count > 0){
                out += "\n";
            }
            out += this.name;
            out += "::";
            out += definition.toStringRegix();
            count ++;
        }
        return out;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Word o) {
        return this.name.compareTo(o.name);
    }

    private void removeFromDefinitions(Definition definition) {
        definitions.remove(definition);
    }
}
