package comprehensive;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 1) {
            System.out.println("invalid");
        }

        Glossary glossary = new Glossary(args[0]);
        Scanner scanner = new Scanner(System.in);

    }
}
