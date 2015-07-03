package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 */

import java.util.LinkedList;

abstract class Character {
    protected LinkedList<Item> inventory;
    protected int maxHealth;
    protected int currentHealth;
    protected int maxSanity;
    protected int currentSanity;

    public LinkedList<Item> getInventory() {
        return inventory;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxSanity() {
        return maxSanity;
    }

    public int getCurrentSanity() {
        return currentSanity;
    }

}
