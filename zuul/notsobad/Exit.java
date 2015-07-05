package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is meant to be the property of a Room.
 * It links a Room to its neighboring Rooms through saving a tuple of a Room and a Direction.
 * <p>
 * An exit can be blocked (by a guard, a pile of rocks,...),
 * or can be hidden (behind a bookshelf).
 * Blocked Exits are shown to the player, but cannot be passed, while hidden Exits are not shown (and cannot be passed).
 *
 * @author Michael
 */
class Exit {
    private Room neighbor;
    private Directions direction;
    private boolean hidden;
    private boolean blocked;

    /**
     * Constructs a new Exit-Object, that is per default not hidden and not blocked
     *
     * @param neighbor  The neighbor of the current Room
     * @param direction The direction the current Room has to be left to reach the neighbor
     */
    public Exit(Room neighbor, Directions direction) {
        this(neighbor, direction, false, false);
    }


    /**
     * Constructs a new Exit-Object, that is per default not hidden
     *
     * @param neighbor  The neighbor of the current Room
     * @param direction The direction the current Room has to be left to reach the neighbor
     * @param blocked   Is this Exit blocked (for example by a guard or a pile of rocks)
     */
    public Exit(Room neighbor, Directions direction, boolean blocked) {
        this(neighbor, direction, blocked, false);
    }


    /**
     * Constructs a new Exit-Object
     *
     * @param neighbor  The neighbor of the current Room
     * @param direction The direction the current Room has to be left to reach the neighbor
     * @param blocked   Is this Exit blocked? (for example by a guard or a pile of rocks)
     * @param hidden    Is this Exit hidden? (for example behind a bookshelf)
     */
    public Exit(Room neighbor, Directions direction, boolean blocked, boolean hidden) {
        this.neighbor = neighbor;
        this.direction = direction;
        this.blocked = blocked;
        this.hidden = hidden;
    }

    /**
     * Creates an 'empty' Exit object leading to a dummy room
     */
    public Exit() {
        this(null, Directions.EMPTY);
    }

    public boolean isEmpty() {
        return direction == Directions.EMPTY;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


    public Room getNeighbor() {
        return neighbor;
    }

    public Directions getDirection() {
        return direction;
    }

    /**
     * Returns true if this Exit is passable for the player.
     * I.E. it is not hidden, not blocked, and no dummy-object
     */
    public boolean isPassable() {
        return isBlocked() && isHidden() && isEmpty();
    }

}