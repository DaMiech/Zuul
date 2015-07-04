package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class handles commandaliases during runtime
 */

import java.util.ArrayList;

class CommandSynonym {

    private ArrayList<ArrayList<String>> synonyms;

    /**
     * Constructs a new CommandSynonyms Object.
     * The synonyms are initialized with the toString() representations of the Commands constants
     */
    public CommandSynonym() {
        synonyms = new ArrayList<ArrayList<String>>();
        Commands[] commands = Commands.values();
        for (int i = 0; i < commands.length; i++) {
            synonyms.add(new ArrayList<String>());
            synonyms.get(i).add(commands[i].toString().toLowerCase());
        }
    }

    /**
     * Tries to match a String to a Command oor Commandalias. If not possible returns EMPTY
     * @param matchSeeker the String which is tried to match to an alias
     * @return the corresponding Commands constant, Commands.EMPTY else
     */
    public Commands matchStringtoCommands(final String matchSeeker) {
        if (matchSeeker != null) {
            for (ArrayList<String> currentList : synonyms) {
                if (currentList.contains(matchSeeker)) {
                    try {
                        return Commands.valueOf(currentList.get(0).toUpperCase());
                    } catch (IllegalArgumentException e) {
                        return Commands.EMPTY;
                    }
                }
            }
        }
        return Commands.EMPTY;

    }

    /**
     * Prints all available commands (not EMPTY) to the console with their corresponding aliases
     */
    public void printAvailableCommands() {
        System.out.println("Your available Commands are:");
        for (ArrayList<String> currentSynonyms : synonyms) {
            if (!currentSynonyms.contains("empty")) {
                for (String currentString : currentSynonyms) {
                    System.out.print(currentString + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Add a new alias to a command, if this alias is not already in use and the command exists
     * @param category the command category where the alias should be added
     * @param newSynonym the alias that should be added
     */
    public void addCommand(final String category, final String newSynonym) {
        if (category != null && newSynonym != null) {
            for (ArrayList<String> currentList : synonyms) {
                if (currentList.contains(newSynonym)) {
                    System.out.println("This command is already defined!");
                    return;
                }
            }
            for (ArrayList<String> currentList : synonyms) {
                if (currentList.get(0).equalsIgnoreCase(category)) {
                    currentList.add(newSynonym);
                    System.out.println("The new synonym " + newSynonym + " was successfully defined for " + category.toLowerCase() + ".");
                    return;
                }
            }
            System.out.println("You are trying to define a synonym for a non-existent command!");
        }
    }

    /**
     * Deletes an alias through removing it from the corresponding list, if the alias and command exist.
     * This won't remove the first (basic) entry of a list of aliases
     * @param category the command category where the alias should be removed
     * @param toRemove the alias that should be removed
     */
    public void removeCommand(final String category, final String toRemove) {
        if (category != null && toRemove != null) {
            boolean found = false;
            for (ArrayList<String> currentList : synonyms) {
                if (!currentList.contains(toRemove)) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println("This command does not exist!");
                return;
            }
            for (ArrayList<String> currentList : synonyms) {
                if (currentList.get(0).equalsIgnoreCase(category) && !currentList.get(0).equals(toRemove)) {
                    currentList.remove(toRemove);
                    System.out.println("The synonym " + toRemove + " was successfully removed from " + category.toLowerCase() + ".");
                    return;
                }
            }
            System.out.println("You are either trying to remove a basic command word, or the command category does not exist!");
        }
    }

    public ArrayList<ArrayList<String>> getSynonyms() {
        return synonyms;
    }
}
