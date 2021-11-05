package md.polarbeargame.models;

import md.json.components.Button;
import md.json.components.interfaces.ITextComponent;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import java.awt.Component;
import java.awt.Color;

public class ComponentCollection {
    private Map<String, Component> components;

    public ComponentCollection() {
        components = new HashMap<>();
    }

    public ComponentCollection(Map<String, Component> components) {
        this.components = components;
    }
    
    public ITextComponent getTextComponent(String name) {
        return get(name);
    }

    @SuppressWarnings (value="unchecked")
    public <T extends Component> T get(String name) throws ClassCastException, NullPointerException  {

        if(components.containsKey(name)) {
            return (T)components.get(name);
        }
        
        throw new NullPointerException(name);
    }

    public void setText(String name, String t) {
        getTextComponent(name).setText(t);
    }
    
    public void addText(String name, String t) {
        ITextComponent tc = getTextComponent(name);
        tc.setText(tc.getText() + t);
    }

    public void resetComponents() {         
        resetTextComponents("", Color.white);
        disableButtonsExcept("btnThrowDices");
    }

    private void resetTextComponents(String value, Color color) {
        components.forEach((k, c) -> {
            if(c instanceof JTextComponent) {
                JTextComponent tc = (JTextComponent)c;
                tc.setText(value); 
                if(c instanceof JTextField)               
                    tc.setBackground(color);
            }
        });
    }

    private void disableButtonsExcept(String name) {
        components.forEach((k, c) -> {
            if(c instanceof Button) 
                ((Button)c).setEnabled(k.equals(name));            
        });
    }    
}
