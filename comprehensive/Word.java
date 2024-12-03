package comprehensive;

import java.util.HashSet;

/**
 * this class is a word that contains definitions in a sorted order
 */
public class Word implements Comparable<Word>{
    private final SortedIndexableSet<Definition> definitions;
    public int numberOfDefinitions;
    private final String name;
    private final SortedIndexableSet<String> partsOfSpeech;

    public Word(String name, Definition definition) {
        this.name = name;
        partsOfSpeech = new SortedIndexableSet<>();
        definitions = new SortedIndexableSet<>();
        partsOfSpeech.add(definition.getPartOfSpeech());
        definitions.add(definition);
        numberOfDefinitions++;
    }

    public void addDefinition(String type, String def) {
        definitions.add(new Definition(type, def));
        partsOfSpeech.add(type);
        numberOfDefinitions++;
    }

    public void deleteDefinition(int i) {
        definitions.remove(definitions.getByIndex(i-1));
        numberOfDefinitions--;
    }

    public void updateDefinition(int i, String newDef) {
        definitions.getByIndex(i-1).setDescription(newDef);
    }

    public String getDefinitions() {
        String result = "";
        for (int i = 0; i < definitions.size(); i++) {
            result += i+1 + ". " + definitions.getByIndex(i);
            if (i+1 != definitions.size()) {
                result += "\n";
            }
        }
        return result;
    }

    public String getPartsOfSpeech() {
        String result = "";
        for (int i = 0; i < partsOfSpeech.size(); i++) {
            result += partsOfSpeech.getByIndex(i) + "\n";
        }
        return result;
    }

    public String toString() {
        String result = name + "\n";
        result += this.getDefinitions();
        return result;
    }

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

}
