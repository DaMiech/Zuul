package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * In this Enum there are the Directions in which a Room-Object can be left
 *
 * @author Michael
 */
enum Directions {
    EMPTY, NORTH, SOUTH, EAST, WEST, UP, DOWN;

    /**
     * Returns the counterpart of the specified direction (for example getCounterpart(NORTH) will return SOUTH)
     *
     * @param direction the direction whose counterpart should be returned
     * @return counterpart of the specified direction
     */
    public static Directions getCounterpart(Directions direction) {
        switch (direction) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case EMPTY:
                return EMPTY;
            default:
                return null;
        }
    }

    /**
     * Rerurns a String with all directions separated by an '/' (extremly awkwardly implemented, needs refactoring)
     *
     * @param without specify a direction you don't want to appear in the List (for example Directions.EMPTY)
     * @return a String list of the directions
     */
    public static String directionList(final Directions without) {
        StringBuilder result = new StringBuilder();
        for (Directions direction : Directions.values()) {
            if (without != direction) {
                result.append(direction.toString().toLowerCase());
                result.append('/');
            }
        }
        return result.toString();
    }
}
