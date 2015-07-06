package zuul.notsobad;

import java.util.ArrayList;

class Player extends Character {
    private Room currentLocation;
    private ArrayList<StoryEvent> registeredEvents;

    public Player(Room currentLocation) {
        super();
        this.currentLocation = currentLocation;
        registeredEvents = new ArrayList<StoryEvent>();
    }

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public void register(final StoryEvent event) {
        if (event != null) {
            registeredEvents.add(event);
        }
    }

    public void teleport(final Room destination) {
        if (destination != null) {
            this.currentLocation = destination;
            this.notifyListeners(EventTypes.MOVE);
            currentLocation.notifyListeners(EventTypes.ENTER);
            currentLocation.printRoomInfo();
        }
    }

    private void notifyListeners(final EventTypes type) {
        if (type != null) {
            for (StoryEvent event : registeredEvents) {
                event.onNotification(type);
            }
        }
    }

    public boolean move(final Directions direction) {
        Exit attemptedExit = currentLocation.getExit(direction);
        if (direction != null && !attemptedExit.isPassable()) {
            teleport(attemptedExit.getNeighbor());
            return true;
        } else {
            System.out.println("There is no way in this direction. At least it seems so...");
            return false;
        }
    }

    public void useGate(String param) {
        if (currentLocation.hasExactlyOneGate() && param.equals("")) {
            currentLocation.useGate(1, this);
        } else {
            try {
                Integer gateNumber = new Integer(param);
                currentLocation.useGate(gateNumber.intValue(), this);
            } catch (NumberFormatException e) {
                System.out.println("Use which Gate?");
            }
        }
    }

    public void searchRoom() {
        notifyListeners(EventTypes.SEARCH);
        currentLocation.printLoot();
    }

    public void pickStuffUp(final String itemName) {
        for (Item currentItem : currentLocation.getLoot()) {
            if (currentItem.getName().equals(itemName)) {
                inventory.add(currentItem);
            }
        }
    }

    public void useItem(Object params) {

    }

    public void printRoomInfo() {
        currentLocation.printRoomInfo();
    }

    /**
     * Sets the current player location to the specified room. Without notifying listening events or printing room info.
     * Consider using teleport instead.
     *
     * @param room the location where you wish the player to be
     */
    public void setCurrentLocation(final Room room) {
        currentLocation = room;
    }


    public void printInventory() {
        System.out.print("Inventory: ");
        for (Item currentItem : inventory) {
            System.out.print(currentItem.getName() + ", ");
        }
        System.out.println();
    }


    @Override
    protected void onValueZero(final CharaVal value) {
        switch (value) {
            case HEALTH: System.out.println("You died!");
                break;
            case SANITY: System.out.println("You went insane!");
                break;
        }
    }
}