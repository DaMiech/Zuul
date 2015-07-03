package zuul.notsobad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a command. It returns the command
 * as an object of class CommandWord.
 * <p>
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands or aliases, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Michael Kolling and David J. Barnes slight changes by Michael
 * @version 1.1 (June 2015)
 */

class Parser {
    private CommandSynonym synonyms;

    public Parser(final CommandSynonym synonyms) {
        this.synonyms = synonyms;
    }

    /**
     * Reads the user command and tries to create a valid (and useful) CommandWord.
     * If this fails it returns an EMPTY command with no parameters
     *
     * @return a CommandWord which then can be used to execute the users wish
     */
    public CommandWord getCommand() {
        String inputLine = ""; // will hold the full input line
        ArrayList<String> words = new ArrayList<String>();

        System.out.print("> "); // print prompt

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        } catch (IOException exc) {
            System.out.println("There was an error during reading: " + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }

        if (!words.isEmpty()) {
            Commands firstWord = synonyms.matchStringtoCommands(words.get(0));
            ArrayList<String> params = new ArrayList<String>();
            for (int i = 1; i < words.size(); i++) {
                params.add(words.get(i));
            }
            return new CommandWord(firstWord, params);
        } else {
            return new CommandWord(Commands.EMPTY, new ArrayList<String>());
        }
    }
}