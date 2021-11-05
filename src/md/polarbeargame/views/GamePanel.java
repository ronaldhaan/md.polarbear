package md.polarbeargame.views;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import md.json.components.JsonPanel;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.polarbeargame.models.Data;
import md.polarbeargame.models.JColor;
import ec.shared.Utility;

public class GamePanel extends JsonPanel {
    private static final long serialVersionUID = 1L;

    private Data data;

    public GamePanel() {
        super(GamePanel.class);
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        Map<String, Component> components = new HashMap<>();
        Data.initialize(this, components);

        if (getJsonData() != null) {
            String background = getJsonData().get("Background").getAsString();
            setBackground(new JColor(background));

            JsonArray jComponents = getJsonData().get("Components").getAsJsonArray();
            for (JsonElement jsonComponent : jComponents) {
                if (jsonComponent.isJsonObject()) {
                    JsonObject object = jsonComponent.getAsJsonObject();
                    String type = object.get("Type").getAsString();
                    
                    try {
                        Class<?> c = Class.forName("md.json.components." + type);
                        Constructor<?> constr = c.getConstructor(JsonObject.class);
                        Component component = (Component)constr.newInstance(object);
                        
                        String name = object.get("Name").getAsString();
                        components.put(name, component);
                        add(component);
                    } catch (Exception ex) {

                    }
                }
            }

            data = Data.getInstance();
            data.setTips(getAsArray(getJsonData().get("Tips").getAsJsonArray()));
        }
    }

    @Override
    protected void paintComponent(Graphics pen) {
        try {
            // aanmaken van super paintcomponent
            super.paintComponent(pen);

            // de dessert-kleurige vak bovenin met de tekst: "Welkom bij het
            // polarBearsspel".
            pen.setColor(new Color(172, 166, 154));
            pen.fillRoundRect(245, 20, 515, 170, 25, 25);

            pen.setColor(Color.black);
            pen.fillRect(0, 0, 772, 10); // zwarte lijn boven
            pen.fillRect(0, 0, 10, 750); // zwarte lijn links
            pen.fillRect(772, 0, 10, 750);// zwarte lijn rechts
            pen.fillRect(0, 555, 772, 10);// zwarte lijn onder
            drawRectangleRight(pen);

            // de rood-kleurige vak met de knop "new Game"
            pen.setColor(new Color(113, 72, 76));
            pen.fillRoundRect(601, 220, 160, 325, 25, 25);
            // donkere grijze vak midden onder met de tips en het antwoord.
            pen.setColor(Color.gray);
            pen.fillRoundRect(245, 220, 330, 325, 25, 25);

            pen.setColor(Color.BLACK);
            /**
             * het tekenen van de dobbelsteen objecten tot en met het gewilde aantal
             */
            if (data != null) {
                data.getDiceCollection().draw(pen);
            }
        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.GPL1, ex, true);
        }
    }

    private void drawRectangleRight(Graphics pen) {
        pen.setColor(Color.black);
        pen.fillRect(225, 0, 550, 750);// zwarte achtergrond rechts.

        pen.setColor(Color.LIGHT_GRAY);
        // grijze randen bij de gekleurde vakken rechts.
        pen.fillRoundRect(235, 10, 535, 190, 25, 25);
        pen.fillRoundRect(235, 210, 350, 345, 25, 25);
        pen.fillRoundRect(591, 210, 180, 345, 25, 25);
        pen.fillRoundRect(235, 10, 535, 190, 25, 25);
    }
}
