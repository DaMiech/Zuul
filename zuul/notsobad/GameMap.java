package zuul.notsobad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * Manages the game map, I.E. all rooms. This happens in a List for now. Probably needs refactoring.
 *
 * @author Michael
 */
class GameMap {
    Map<String, Room> rooms = new HashMap<String, Room>();
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
    }

    /**
     * Adds a new room to the map, if the name of it is unique
     *
     * @param newRoom the room to add to the map
     */
    public void addRoom(Room newRoom) {
        if (!this.hasRoom(newRoom.getName())) {
            rooms.put(newRoom.getName(), newRoom);
        }
    }

    private boolean hasRoom(String name) {
        return rooms.containsKey(name);
    }
    private boolean hasRoom(Room room) {
        return rooms.containsValue(room);
    }

    /**
     * Deletes a specified room from the map, if the map contains it
     *
     * @param room deletes this room
     */
    public void deleteRoom(Room room) {
        if (this.hasRoom(room)) {
            rooms.values().remove(room);
        }
    }

    /**
     * @param name the name of the searched room
     * @return dummy room if room was not found; else the specified room
     */
    public Room getRoomByName(final String name) {
        return rooms.get(name);
    }


}