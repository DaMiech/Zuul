package zuul.notsobad;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game {

    private Parser parser;
    private GameMap map;
    private Player player;
    private CommandSynonym commands;
    private boolean isRunning;

    /**
     * Main method. Creates a Game Object
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        player = new Player();
        map = new GameMap();
        commands = new CommandSynonym();
        parser = new Parser(commands);
        isRunning = true;
        createRooms();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms
        map.addRoom(new Room("outside", "outside the main entrance of the university"));
        map.addRoom(new Room("theatre", "in a lecture theatre"));
        map.addRoom(new Room("pub", "in the campus pub"));
        map.addRoom(new Room("lab", "in a computing lab"));
        map.addRoom(new Room("office", "in the computing admin office"));

        // initialise room exits
        //Exits for outside
        map.setselectedRoom(map.getRoomByName("outside"));
        map.getselectedRoom().addExit(new Exit(map.getRoomByName("theatre"), Directions.EAST));
        map.getselectedRoom().addExit(new Exit(map.getRoomByName("lab"), Directions.SOUTH));
        map.getselectedRoom().addExit(new Exit(map.getRoomByName("pub"), Directions.WEST));

        //Exits for theatre
        map.setselectedRoom(map.getRoomByName("theatre"));
        //no new Exits

        //Exits for pub
        map.setselectedRoom(map.getRoomByName("pub"));
        //no new Exits

        //Exits for lab
        map.setselectedRoom(map.getRoomByName("lab"));
        map.getselectedRoom().addExit(new Exit(map.getRoomByName("office"), Directions.EAST));

        //Exits for office
        map.setselectedRoom(map.getRoomByName("office"));
        //no new Exits


        map.setStartRoom(map.getRoomByName("outside"));
        player.setCurrentLocation(map.getStartRoom());// start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        onStart();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        while (isRunning) {
            CommandWord command = parser.getCommand();

            // process command
            if (command.isUnknown()) {
                System.out.println("I don't know what you mean...");
                continue;
            }

            executeCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    // implementations of user commands:

    private void executeCommand(CommandWord command) {
        switch (command.getCommandWord()) {
            case EMPTY:
                System.out.println("This is no valid command!");
                break;
            case GO:
                goRoom(command);
                break;
            case HELP:
                printHelp(command);
                break;
            case QUIT:
                quit(command);
                break;
            case SEARCH:
                break;
            case SETTINGS:
                evaluateSettings(command);
                break;
            case TALK:
                break;
            case USE:
                useStuff(command);
                break;
            default:
                System.out.println("This is no valid command!");
                break;

        }
    }

    private void useStuff(final CommandWord command) {
        if (command.hasSecondWord()) {
            if (command.getParam(0).equalsIgnoreCase("gate")) {
                player.useGate(command.getParam(1));
            } else {
                player.useItem(command.getParams());
            }
        } else {
            System.out.println("Use what?");
        }
    }

    private void evaluateSettings(final CommandWord command) {
        if (command.getParam(0).equalsIgnoreCase("add")) {
            this.commands.addCommand(command.getParam(1), command.getParam(2));
            return;
        }
        if (command.getParam(0).equalsIgnoreCase("remove")) {
            this.commands.removeCommand(command.getParam(1), command.getParam(2));
            return;
        }
        System.out.println("Set what?");
    }

    /**
     * Prints the Welcome text and the Room info of the starting location
     */
    private void onStart() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.printRoomInfo();

    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     *
     */
    private void printHelp(final CommandWord command) {
        if (!command.hasSecondWord()) {
            System.out.println("You are lost. You are alone. You wander");
            System.out.println("around at the university.");
            System.out.println();
            commands.printAvailableCommands();
            System.out.println();
        } else {
            Commands commandParam = commands.matchStringtoCommands(command.getParam(0));
            if (commandParam != Commands.EMPTY) {
                System.out.println(commandParam.toString().toLowerCase() + " " + Commands.allowedParams(commandParam));
            } else {
                System.out.println("Help you with what?");
            }
        }
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(final CommandWord command) {
        if (command.hasSecondWord()) {
            try {
                Directions direction = Directions.valueOf(command.getParam(0).toUpperCase());
                player.move(direction);
            } catch (IllegalArgumentException e) {
                System.out.println("Go where?");
            }
        } else {
            System.out.println("Go where?");
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private void quit(final CommandWord command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
        } else {
            this.terminateGame();
        }
    }

    private void terminateGame() {
        isRunning = false;
    }
}
