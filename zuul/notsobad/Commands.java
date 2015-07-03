package zuul.notsobad;


/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This enum contains a constant for every valid command the user may enter.
 * Also contains an EMPTY constant for handling invalid commandlines.
 */
enum Commands {
    EMPTY, HELP, QUIT, SETTINGS, GO, SEARCH, USE, TALK, STATS, INVENTORY, ATTACK;

    /**
     * This method returns a String containing an explanation of the allowed parameters of a specified command
     *
     * @param command the command for which you wish the explanation
     * @return a String containing the allowed parameters
     */
    public static String allowedParams(Commands command) {
        switch (command) {
            case EMPTY:
                return "no parameters";
            case GO:
                return "<" + Directions.directionList(Directions.EMPTY) + ">";
            case HELP:
                return "<" + commandList(Commands.EMPTY) + ">";
            case QUIT:
                return "no parameters";
            case SEARCH:
                return "no parameters";
            case SETTINGS:
                return "<add/remove> <" + commandList(Commands.EMPTY) + "> <synonym>";
            case TALK:
            case USE:
                return "<gate x/itemname>";
            default:
                return "no parameters";
        }
    }

    /**
     * Rerurns a String with all commands separated by an '/' (extremly awkwardly implemented, needs refactoring)
     *
     * @param without specify a command you don't want to appear in the List (for example Commands.EMPTY)
     * @return a String list of the Commands
     */
    private static String commandList(final Commands without) {
        StringBuilder result = new StringBuilder();
        for (Commands command : Commands.values()) {
            if (without != command) {
                result.append(command.toString().toLowerCase());
                result.append('/');
            }
        }
        return result.toString();
    }
}