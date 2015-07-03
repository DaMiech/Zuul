package zuul.notsobad;

import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds information about a command that was issued by the user.
 *
 * @author Michael
 * @version 2.0 (June 2015)
 */

class CommandWord {

    private Commands command;
    private ArrayList<String> params;

    /**
     * Create a command object. The command word should be empty to
     * indicate that this was a command that is not recognised by this game.
     *
     * @param command the main command, a Commands constant
     * @param params  a List of Strings specifying the main Command
     */
    public CommandWord(final Commands command, final ArrayList<String> params) {
        if (command != null) {
            this.command = command;
        } else {
            this.command = Commands.EMPTY;
        }

        if (params != null) {
            this.params = params;
        } else {
            this.params = new ArrayList<String>();
        }

    }

    /**
     * Return the command word of this command.
     */
    public Commands getCommandWord() {
        return command;
    }

    /**
     * Return a parameter of this command. Returns empty String if there were no
     * parameters, or the requested parameter does not exist
     *
     * @param index the index of the parameter requested.
     *              Has to be >0 and <command.size()
     */
    public String getParam(int index) {
        if (index >= 0 && index < params.size()) {
            return params.get(index);
        }
        return "";
    }

    /**
     * Return true if this command was not understood.
     */
    public boolean isUnknown() {
        return command == Commands.EMPTY;
    }

    /**
     * Return true if the command has at least a second word.
     */
    public boolean hasSecondWord() {
        return (params.size() >= 1);
    }

    /**
     * Return the parameters in a List of Strings
     */
    public Object getParams() {
        return params;
    }
}
