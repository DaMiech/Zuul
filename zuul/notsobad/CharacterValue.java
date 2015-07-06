package zuul.notsobad;

/**
 * Created by Michael on 06.07.2015.
 */
public class CharacterValue implements Comparable{
    private CharaVal key;
    private int maxValue;
    private int currentValue;

    public CharacterValue(final CharaVal key, final int maxValue) {
        this.key = key;
        this.maxValue = maxValue;
        this.currentValue = maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public CharaVal getKey() {

        return key;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    public int compareTo(final Object other) {
        CharacterValue castOther =(CharacterValue) other;
        return this.key.compareTo(castOther.getKey());
    }
}
