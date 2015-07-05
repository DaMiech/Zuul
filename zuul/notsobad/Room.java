package zuul.notsobad;

import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits or gates.
 * It contains Items and NPCs, and can have Events listening to him.
 *
 * @author Michael Kolling and David J. Barnes; additions and refactoring by Michael
 * @version 1.3 (June 2015)
 */

class Room {

    private String description;
    private String name;
    private ArrayList<Exit> exits;
    private ArrayList<Gate> gates;
    private ArrayList<Item> loot;
    private ArrayList<NPC> npcs;
    private ArrayList<StoryEvent> registeredEvents;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param name        unique name of the Room,
     *                    used for better readable code while manipulating the Map
     * @param description of the Room for the console output
     */
    public Room(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.exits = new ArrayList<Exit>();
        this.gates = new ArrayList<Gate>();
        this.loot = new ArrayList<Item>();
        this.npcs = new ArrayList<NPC>();
        this.registeredEvents = new ArrayList<StoryEvent>();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    /**
     * Adds an Exit to this room, if it hasn't got already one in this direction.
     * Also adds an Exit to the other room with this room and the opposite direction.
     *
     * @param exit The exit to add to the room
     */
    public void addExit(Exit exit) {
        if (!hasExitAlready(exit)) {
            exits.add(exit);
            exit.getNeighbor().addExit(new Exit(this, Directions.getCounterpart(exit.getDirection())));

        }
    }

    /**
     * Checks if the executing room already has an Exit in the same direction as the parameter toCheck
     *
     * @param toCheck the exit that should be checked
     * @return this room's exits contains an object where exit.directions == toCheck.directions
     */
    private boolean hasExitAlready(final Exit toCheck) {
        for (Exit currentExit : exits) {
            if (currentExit.getDirection() == toCheck.getDirection()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all available Exits of this room to the console excluding hidden ones and marking blocked ones.
     */
    public void printExits() {
        if (this.hasExits()) {
            System.out.print("Exits: ");
            for (Exit currentExit : exits) {
                if (!currentExit.isHidden()) {
                    System.out.print(currentExit.getDirection().toString().toLowerCase() + " ");
                }
                if (currentExit.isBlocked() && !currentExit.isHidden()) {
                    System.out.print("(Blocked) ");
                }
            }
            System.out.println();
        }
    }

    public Exit getExit(final Directions direction) {
        Exit result = new Exit();
        for (Exit currentExit : exits) {
            if (currentExit.getDirection() == direction) {
                result = currentExit;
            }
        }
        return result;
    }

    public boolean hasExits() {
        return !exits.isEmpty();
    }

    public void printGates() {
        if (this.hasGates()) {
            for (int i = 0; i < gates.size(); i++) {
                System.out.print("Gate " + (i + 1));
            }
            System.out.println();
        }
    }

    public boolean hasGates() {
        return !gates.isEmpty();
    }

    public void openGate(final Gate gate) {
        if (gate != null) {
            gates.add(gate);
            if (gate.getStart().equals(this) && gate.isBidirectional()) {
                gate.getEnd().openGate(gate);
            }
        }
    }

    public void closeGate(final Gate gate) {
        if (gates.contains(gate)) {
            gates.remove(gate);
        }
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void notifyListeners(final EventTypes type) {
        if (type != null) {
            for (StoryEvent event : registeredEvents) {
                event.onNotification(type);
            }
        }
    }

    public void useGate(final int gate, final Player player) {
        int actualGate = gate - 1;
        if (0 <= actualGate && actualGate < gates.size()) {
            player.teleport(gates.get(actualGate).getOther(this));
            if (gates.get(actualGate).isOneUseOnly()) {
                closeGate(gates.get(actualGate));
            }
        } else {
            System.out.println("This gate does not exist");
        }
    }

    public boolean hasExactlyOneGate() {
        return gates.size() == 1;
    }

    public boolean hasExactlyOneExit() {
        return exits.size() == 1;
    }

    /**
     * Print information about the current room
     */
    public void printRoomInfo() {
        System.out.println("You are " + getDescription());
        printExits();
        printGates();
        System.out.println();
    }
}
