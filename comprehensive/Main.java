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
                    
                    Select an option:""";
        Glossary glossary = new Glossary(args[0]);
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Set<String> validPartsOfSpeech = new HashSet<>(Arrays.asList("noun", "verb", "adj", "adv", "pron", "prep", "conj", "interj"));
        System.out.print(mainMenu);
        while(scanner.hasNext()) {
            int number = validEntry(scanner, 11);
            String word;
            int definitionNumber;
            int numberOfDefinitions;

            switch(number) {
                //get metadata
                case 1:
                    System.out.println(glossary.getMetaData());
                    break;
                // get words in range
                case 2:
                    System.out.print("Starting Word:  ");
                    String word1 = scanner.next();
                    System.out.print("Ending Word:  ");
                    String word2 = scanner.next();
                    System.out.println(" \n The words in between " + word1 + " and " + word2 + " are:");
                    for (String s : glossary.getWordsInRange(word1, word2)) {
                        System.out.println( "\t" + s);
                    }
                    break;
                // get word
                case 3:
                    System.out.println("Select a word: ");
                    word = scanner.next();
                    System.out.println( "\n" + glossary.getWord(word) + "\n");
                    break;
                // get first word
                case 4:
                    System.out.println( "\n" + glossary.getFirstWord() + "\n");
                    break;
                // get last word
                case 5:
                    System.out.println("\n" + glossary.getLastWord() + "\n");
                    break;
                // get parts of speech
                case 6:
                    System.out.print("Select a word: ");
                    word = scanner.next();
                    System.out.println("\n" + glossary.getPartsOfSpeech(word) + "\n");
                    break;
                // update definition
                case 7:
                    word = getValidWord(scanner, glossary);
                    numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
                    definitionNumber = getValidDefinition(scanner, glossary, word);

                    if (definitionNumber == numberOfDefinitions + 1) {
                        break;
                    }

                    //type new definition
                    System.out.print("Type new definition: ");
                    String newDef = scanner.next();
                    glossary.updateWordDefinition(word, definitionNumber, newDef);
                    System.out.println("\n Definition Updated \n");
                    break;
                //delete definition
                case 8:
                    word = getValidWord(scanner, glossary);
                    numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
                    definitionNumber = getValidDefinition(scanner, glossary, word);

                    if (definitionNumber == numberOfDefinitions + 1) {
                        break;
                    }

                    glossary.deleteWordDefinition(word, definitionNumber);
                    System.out.println("Definition Deleted \n");
                    break;
                //add definition
                case 9:
                    System.out.print("Type a word: ");
                    word = scanner.next();

                    System.out.println("Valid parts of speech: " + validPartsOfSpeech);
                    System.out.print("Type a valid part of speech: ");
                    String partOfSpeech = scanner.next();

                    // get valid part of speech
                    while(!validPartsOfSpeech.contains(partOfSpeech)) {
                        System.out.println(" \n Invalid Selection \n");
                        System.out.print("Type a valid part of speech: ");
                        partOfSpeech = scanner.next();
                    }

                    System.out.print("Type a definition: ");
                    String definition = scanner.next();
                    glossary.addDefinitionToWord(word, partOfSpeech, definition);
                    System.out.println("\n Definition Added \n");
                    break;
                // save to file
                case 10:
                    System.out.print("Type a filename with path: ");
                    String filePath = scanner.next();
                    glossary.saveToDirectory(filePath);
                    System.out.println(" \n Glossary Saved \n ");
                    break;
                // exit
                case 11:
                    scanner.close();
                    return;
            }
            System.out.println(mainMenu);
        }
    }

    /**
     * private helper method to determine if a positive int user input is valid
     * @param s the scanner being used
     * @param bound the limit for a user input exclusive
     * @return 0 if the input is invalid and the number if it is valid
     */
    private static int validEntry(Scanner s, int bound) {
        if(s.hasNextInt()) {
            int number = s.nextInt();
            if (bound < number || number < 1) {
                System.out.println();
                System.out.println("Invalid input \n");
                return 0;
            }
            return number;
        }
        System.out.println();
        System.out.println("Invalid input \n");
        s.next();
        return 0;
    }

    /**
     * private helper method to get a valid word from a user
     * a valid word is one that is contained in the glossary given
     * @param scanner the scanner being used
     * @param glossary the glossary being checked
     * @return a valid word
     */
    private static String getValidWord(Scanner scanner, Glossary glossary) {
        String word;
        do {
            System.out.print("Select a word: ");
            word = scanner.next();
            if (!glossary.containsWord(word)) {
                System.out.println("\nWord not in glossary\n");
            }
        } while (!glossary.containsWord(word));
        return word;
    }

    /**
     * private helper method to get a valid definition number from a user
     * a valid definition number is one that is > 0 and <= the number of definitions the given word has
     * @param scanner the scanner being used
     * @param glossary the glossary being checked
     * @param word the word being checked
     * @return a valid definition number
     */
    private static int getValidDefinition(Scanner scanner, Glossary glossary, String word) {
        int numberOfDefinitions = glossary.getWordsNumberOfDefinitions(word);
        int definitionNumber;
        do {
            System.out.println(glossary.getWordsDefinitions(word));
            System.out.println(numberOfDefinitions + 1 + ". Back to main menu\n");
            System.out.print("Select a definition: ");
            definitionNumber = validEntry(scanner, numberOfDefinitions + 1);
        } while (definitionNumber == 0);
        return definitionNumber;
    }
}
