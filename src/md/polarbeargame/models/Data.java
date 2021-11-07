package md.polarbeargame.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import md.json.components.interfaces.ITextComponent;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.Utility;

import java.awt.Component;

public class Data {

    private ComponentCollection components;

    private DiceCollection diceCollection;

    private JPanel panel;

    private List<String> tips;

    private int score;
    private int chances;
    private int timesThrowed;
    private int totalDices;

    private static Data data;

    private Data(JPanel panel, Map<String, Component> components) {
        this.panel = panel;
        this.components = new ComponentCollection(components);
        diceCollection = new DiceCollection();
        tips = new ArrayList<>();
    }

    public static void initialize(JPanel panel, Map<String, Component> components) {
        data = new Data(panel, components);
    }

    public static Data getInstance() {
        return data;
    }

    public List<String> getTips() {
        return tips;
    }

    public void addTip(int index) {
        components.addText("jtaTips", getTips().get(index));
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public DiceCollection getDiceCollection() {
        return diceCollection;
    }

    public int getTotalDices() {
        return totalDices;
    }

    public void setTotalDices(int totalDices) {
        components.setText("lblTotalDices", String.valueOf(totalDices));
        this.totalDices = totalDices;
    }

    public int addScore() {
        setScore(++score);
        return score;
    }

    private void setScore(int score) {
        components.setText("lblScore", String.valueOf(score));
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int addChance() {
        setChances(++chances);
        return chances;
    }

    private void setChances(int chances) {
        components.setText("lblChances", String.valueOf(chances));
        this.chances = chances;
    }

    public int addTimeThrowed() {
        setTimesThrowed(++timesThrowed);
        return timesThrowed;
    }

    private void setTimesThrowed(int timesThrowed) {
        this.timesThrowed = timesThrowed;
        components.setText("lblTimesThrowed", String.valueOf(timesThrowed));
    }

    public int getChances() {
        return chances;
    }

    public ITextComponent getTextComponent(String name) {
        return components.getTextComponent(name);
    }

    public <T extends Component> T get(String name) {
        try {
            return components.get(name);
        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.DATA, ex);
            return null;
        }
    }

    public int getTimesThrowed() {
        return timesThrowed;
    }

    public void repaint() {
        panel.repaint();
    }

    public void clear() {
        setScore(0);
        setChances(0);
        setTimesThrowed(0);
        setTotalDices(0);
        components.setText("jtaTips", "");
        diceCollection.clear();
    }

    public void resetComponents() {
        components.resetComponents();
    }
}
