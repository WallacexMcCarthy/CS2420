package comprehensive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * this class is the main application for the glossary class
 * args[0] must be a .txt file containing word definitions in the format:
 * word::partOfSpeech::definition
 * the application is to be used through the command line
 * @version 12/2/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Invalid Arguments");
        }
        String mainMenu = """
                Main menu
                1.  Get metadata
                2.  Get words in range
                3.  Get word
                4.  Get first word
                5.  Get last word
                6.  Get parts of speech
                7.  Update definition
                8.  Delete definition
                9.  Add new definition
                10.  Save dictionary
                11.  Quit
               \s
                Select an option:\s""";
        Glossary glossary = new Glossary(args[0]);
        while(true) {
            int number = validEntry(getConsoleInput(mainMenu), 11);
            String word;
            int definitionNumber;
            int numberOfDefinitions;
            Set<String> validPartsOfSpeech = new HashSet<>(Arrays.asList("noun", "verb", "adj", "adv", "pron", "prep", "conj", "interj"));
            if(number == 11){
                return;
            }
            switch(number) {
                //get metadata
                case 1:
                    System.out.println(glossary.getMetaData());
                    break;
                // get words in range
                case 2:
                    String word1 = getConsoleInput("Starting Word: ");
                    String word2 = getConsoleInput("Ending Word: ");
                    System.out.println(" \n The words in between " + word1 + " and " + word2 + " are:");
                    for (String s : glossary.getWordsInRange(word1, word2)) {
                        System.out.println( "\t" + s);
                    }
                    System.out.println();
                    break;
                // get word
                case 3:
                    System.out.print("Select a word: ");
                    word = getConsoleInput("Select a word: ");
                    System.out.println( "\n" + glossary.getWord(word) + "\n");
                    break;
                // get first word
                case 4:
                    System.out.println("\n" + glossary.getFirstWord() + "\n");
                    break;
                // get last word
                case 5:
                    System.out.println("\n" + glossary.getLastWord() + "\n");
                    break;
                // get parts of speech
                case 6:
                    word = getValidWord(glossary);
                    if(word == null) {
                        System.out.println(" \n Word not found \n");
                        break;
                    }
                    System.out.println("\n" + word);
                    System.out.println(glossary.getPartsOfSpeech(word));
                    break;
                // update definition
                case 7:
                    word = getValidWord(glossary);
                    if(word == null) {
                        System.out.println(" \n Word not found \n");
                        break;
                    }
                    numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
                    definitionNumber = getValidDefinition(glossary, word);

                    if (definitionNumber == numberOfDefinitions + 1) {
                        break;
                    }

                    //type new definition
                    String newDef = getConsoleInput("Type new definition: ");
                    glossary.updateDefinition(word, definitionNumber, newDef);
                    System.out.println("\n Definition Updated \n");
                    break;
                //delete definition
                case 8:
                    word = getValidWord(glossary);
                    numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
                    definitionNumber = getValidDefinition(glossary, word);

                    if (definitionNumber == numberOfDefinitions + 1) {
                        break;
                    }
                    System.out.println("Definition removed \n");
                    if(glossary.deleteDefinition(word, definitionNumber)){
                        System.out.println(word + " removed");
                        System.out.println();
                    }
                    break;
                //add definition
                case 9:
                    word = getConsoleInput("Type a word: ");

                    System.out.println("Valid parts of speech: " + validPartsOfSpeech);
                    String partOfSpeech = getConsoleInput("Type a valid part of speech: ");

                    // get valid part of speech
                    while(!validPartsOfSpeech.contains(partOfSpeech)) {
                        partOfSpeech = getConsoleInput("Type a valid part of speech: ");
                    }

                    String definition = getConsoleInput("Type a definition: ");
                    glossary.addDefinition(word, partOfSpeech, definition);
                    System.out.println("\n Definition Added \n");
                    break;
                // save to file
                case 10:
                    String filePath = getConsoleInput("Type a filename with path: ");
                    glossary.saveToDirectory(filePath);
                    System.out.println(" \n Glossary Saved \n ");
                    break;
            }
        }
    }

    /**
     * private helper method to determine if a positive int user input is valid
     * @param s the scanner being used
     * @param bound the limit for a user input exclusive
     * @return 0 if the input is invalid and the number if it is valid
     */
    private static int validEntry(String s, int bound) {
        try {
            int input = Integer.parseInt(s);
            if (input > 0 && input <= bound) {
                return input;
            }
        } catch (NumberFormatException ignored) {}
        System.out.println("Invalid selection\n");
        return 0;
    }

    /**
     * private helper method to get a valid word from a user
     * a valid word is one that is contained in the glossary given
     *
     * @param glossary the glossary being checked
     * @return a valid word
     */
    private static String getValidWord(Glossary glossary) {
        String word;
        Set<String> validWords = glossary.getAllWords();
        word = getConsoleInput("Select a word: ");
        if(!validWords.contains(word)) {
            return null;
        }
        return word;
    }

    /**
     * private helper method to get a valid definition number from a user
     * a valid definition number is one that is > 0 and <= the number of definitions the given word has
     * @param glossary the glossary being checked
     * @param word the word being checked
     * @return a valid definition number
     */
    private static int getValidDefinition(Glossary glossary, String word) {
        int numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
        int definitionNumber;
        do {
            System.out.println(glossary.getWordsNumberedDefinitions(word));
            System.out.println(numberOfDefinitions + 1 + ". Back to main menu\n");
            definitionNumber = validEntry(getConsoleInput("Select a definition: "), numberOfDefinitions + 1);
        } while (definitionNumber == 0);
        return definitionNumber;
    }

    /**
     * private helper method to streamline getting a user input
     * @param prompt the prompt for a user to type after
     * @return the users input
     */
    private static String getConsoleInput(String prompt) {
        System.out.print(prompt);
        return new Scanner(System.in).nextLine();
    }
}
