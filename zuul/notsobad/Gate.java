package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * In contrary to the Exit class which manages physical doors between rooms, this class manages magical gateways, portals, wormholes,...
 * A gate can be oneUseOnly, in which case it collapses after the player travels through it for the first time.
 * A gate can be bidirectional. If it is, it works similar to an Exit.
 * If not it is only shown in the start room and can only be used from there
 */
class Gate {
    private Room start;
    private Room end;
    private boolean bidirectional;
    private boolean oneUseOnly;

    /**
     * Creates  a new Gate object
     *
     * @param start         the room where the gate begins
     * @param end           the room where the gate leads to
     * @param bidirectional does this gate work in both directions?
     * @param oneUseOnly    does this gate collapse after the first use
     */
    public Gate(Room start, Room end, boolean bidirectional, boolean oneUseOnly) {
        this.start = start;
        this.end = end;
        this.bidirectional = bidirectional;
        this.oneUseOnly = oneUseOnly;
    }

    /**
     * Creates a new Gate Object which is per default bidirectional and with unlimited uses
     *
     * @param start the room where the gate begins
     * @param end   the room where the gate leads to
     */
    public Gate(Room start, Room end) {
        this(start, end, true, false);
    }


    public Room getStart() {
        return start;
    }


    public Room getEnd() {
        return end;
    }


    public boolean isBidirectional() {
        return bidirectional;
    }


    public boolean isOneUseOnly() {
        return oneUseOnly;
    }

    /**
     * If the start room is used as parameter this method returns the end room and vice versa.
     * If the parameter is neither it returns a dummy-room
     *
     * @param room the room whose counterpart should be returned
     * @return the counterpart if able, otherwise null
     */
    public Room getOther(final Room room) {
        if (room.equals(end)) {
            return start;
        } else {
            if (room.equals(start)) {
                return end;
            }
            return null;
        }
    }

}