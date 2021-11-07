package md.polarbeargame.models;

public class DiceProps {

    private int wakes;
    private int polarBears;
    private int pinguins;

    public DiceProps() {

    }

    public DiceProps(int wakes, int pinguins, int polarBears) {
        this.wakes = wakes;
        this.polarBears = polarBears;
        this.pinguins = pinguins;
    }

    public int getWakes() {
        return wakes;
    }

    public int getPolarBears() {
        return polarBears;
    }

    public int getPinguins() {
        return pinguins;
    }
}
