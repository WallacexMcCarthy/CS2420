package comprehensive;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 1) {
            System.out.println("invalid");
        }

        Glossary glossary = new Glossary(args[0]);
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
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
                Select an option: \s""");
        while(scanner.hasNext()) {
            int number = validEntry(scanner, 11);
            String word;
            switch(number) {
                case 1:
                    System.out.println(glossary.getMetaData());
                    break;
                case 2:
                    System.out.print("Starting Word:  ");
                    String word1 = scanner.next();
                    System.out.print("Ending Word:  ");
                    String word2 = scanner.next();
                    System.out.println("The words in between " + word1 + " and " + word2 + " are:");
                    for (String s : glossary.getWordsInRange(word1, word2)) {
                        System.out.println( "\t" + s);
                    }
                    break;
                    //TODO
                    // working sometimes but weird sometimes
                    // a-s works but a-z throws exception
                case 3:
                    System.out.println("Select a word: ");
                    word = scanner.next();
                    System.out.println("\n");
                    System.out.println(glossary.getWord(word));
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println(glossary.getFirstWord());
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println(glossary.getLastWord());
                    System.out.println("\n");
                    break;
                case 6:
                    System.out.println("Select a word: ");
                    word = scanner.next();
                    System.out.println("\n");
                    System.out.println(glossary.getPartsOfSpeech(word));
                    break;
                case 7:
                    System.out.println("Select a word: ");
                    word = scanner.next();
                    System.out.println("\n");
                    System.out.println(glossary.getWordsDefinitions(word));
                    //TODO
                    // make it so when a word isnt in the glossary it keeps asking until a good word is input
                    System.out.println("Select a definition: ");
                    int definitionNumber = validEntry(scanner, glossary.getWordsNumberOfDefinitions(word));
                    System.out.print("Type new definition: ");
                    String newDef = scanner.next();
                    glossary.updateWordDefinition(word, definitionNumber, newDef);
                    System.out.println("Definition Updated");
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    System.exit(1);
                    scanner.close();
                    break;
            }

            System.out.println("""
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
                    
                    Select an option:""");
        }
    }

    public static int validEntry(Scanner s, int bound) {
        if(s.hasNextInt()) {
            int number = s.nextInt();
            if (bound <= number || number < 1) {
                System.out.println("invalid input \n");
            }
            return number;
        }
        s.next();
        return 0;
    }
}
