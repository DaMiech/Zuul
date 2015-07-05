package zuul.notsobad;

import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * Manages the game map, I.E. all rooms. This happens in a List for now. Probably needs refactoring.
 *
 * @author Michael
 */
class GameMap {
    ArrayList<Room> rooms;
    private Room selectedRoom;
    private Room startRoom;

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    public GameMap() {
        rooms = new ArrayList<Room>();
    }

    /**
     * Adds a new room to the map, if the name of it is unique
     *
     * @param newRoom the room to add to the map
     */
    public void addRoom(Room newRoom) {
        if (this.getRoomByName(newRoom.getName()) == null) {
            rooms.add(newRoom);
        }
    }

    /**
     * Deletes a specified room from the map, if the map contains it
     *
     * @param room deletes this room
     */
    public void deleteRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
        }
    }

    /**
     * @param name the name of the searched room
     * @return dummy room if room was not found; else the specified room
     */
    public Room getRoomByName(final String name) {
        for (Room currentRoom : rooms) {
            if (currentRoom.getName().equals(name)) {
                return currentRoom;
            }
        }
        return null;
    }

    public Room getselectedRoom() {
        return selectedRoom;
    }

    public void setselectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }


}