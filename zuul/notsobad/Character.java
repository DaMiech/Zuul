package zuul.notsobad;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 */

import java.util.*;

abstract class Character {
    protected LinkedList<Item> inventory;
    protected CharacterValue [] values;
    private final int DEFAULT_INITIAL_CHARACTER_VALUE = 30;

    public Character(){
        this(new ArrayList<CharacterValue>());
    }

    public Character(final List<CharacterValue> initialValues){
        inventory = new LinkedList<Item>();
        ArrayList<CharacterValue> valuesList = new ArrayList<CharacterValue>();
        for(int i=0; i<CharaVal.values().length; i++){
            if(i<initialValues.size()){
                valuesList.add(initialValues.get(i));
            }
        }
        valuesList = fillValues(valuesList);
        valuesList.sort(Comparator.<CharacterValue>naturalOrder());
        values =  valuesList.toArray(new CharacterValue[valuesList.size()]);
    }

    private ArrayList<CharacterValue> fillValues(final ArrayList<CharacterValue> valuesList) {
        ArrayList<CharacterValue> result = valuesList;
        for(CharaVal currentValue : CharaVal.values()){
            if(!valuesList.contains(new CharacterValue(currentValue, 0))){
                result.add(new CharacterValue(currentValue, DEFAULT_INITIAL_CHARACTER_VALUE));
            }
        }
        return result;
    }

    public LinkedList<Item> getInventory() {
        return inventory;
    }


    public void changeCurrentValue(final CharaVal value, final int change){
        int attemptedChange = getCurrentValue(value)+change;
        if(attemptedChange<=0){
            onValueZero(value);
        }else{
            if(attemptedChange>getCurrentValue(value)){
                setCurrentValue(value, getMaxValue(value));
            }else{
                setCurrentValue(value, attemptedChange);
            }
        }
    }

    private int getMaxValue(final CharaVal value) {
        for (CharacterValue currentValue : values) {
            if(currentValue.getKey() == value){
                return currentValue.getMaxValue();
            }
        }
        return 0;
    }

    private void setCurrentValue(CharaVal value, int intValue) {
        for (CharacterValue currentValue : values) {
            if(currentValue.getKey() == value){
                currentValue.setCurrentValue(intValue);
            }
        }
    }

    public int getCurrentValue(final CharaVal value){
        for (CharacterValue currentValue : values) {
            if(currentValue.getKey() == value){
                return currentValue.getCurrentValue();
            }
        }
        return 0;
    }

    protected abstract void onValueZero(final CharaVal value);
}
