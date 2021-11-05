package md.polarbeargame.models;

import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;

public class DiceCollection implements Iterable<Dice> {
    
    private List<Dice> dices;

    public DiceCollection() {
        dices = new ArrayList<>();
    }

    public boolean add(Dice dice) {
        dice.setEyes();
        return dices.add(dice);        
    }

    public void clear() {
        dices.clear();
    }

    public void draw(Graphics g) {
        dices.forEach(d -> d.draw(g));
    }

    public int getTotalWakes() {
        return getTotal(Dice::getWakes);
    }

    public int getTotalPinguins() {
        return getTotal(Dice::getPinguins);
    }

    public int getTotalPolarbears() {
        return getTotal(Dice::getPolarBears);
    }

    private int getTotal(ToIntFunction<? super Dice> function) {
        return dices.stream().mapToInt(function).sum();
    }

    @Override
    public Iterator<Dice> iterator() {
        return dices.iterator();
    }
}

