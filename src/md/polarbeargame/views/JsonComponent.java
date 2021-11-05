package md.polarbeargame.views;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import md.json.JsonFile;
import md.json.components.Button;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.MdFiles;
import md.shared.Utility;

import javax.swing.JComponent;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class JsonComponent extends JComponent {
    private static final long serialVersionUID = 1L;

    protected JsonComponent() {
        super();
    }

    public JsonObject getJsonfileJsonObject(String className) {
        return JsonFile.get(Path.of(MdFiles.getRootFolder().toString(), "src", "ec", "polarbeargame", "views", className+".json"));
    }

    public Rectangle getBounds(String bounds) throws NumberFormatException {
        bounds = bounds.trim();
        String[] values = bounds.split(",");

        if (values.length != 4)
            return null;
        
        for(int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
        }

        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        int w = Integer.parseInt(values[2]);
        int h = Integer.parseInt(values[3]);

        return new Rectangle(x, y, w, h);

    }

    public boolean colorIsNullOrDefault(String color) {
        return color == null || color.equals("default");
    }

    public Color getColor(String sColor) {
        Color color;

        if(sColor.contains(",")) {
            //
            color = getColorFromRGB(sColor);
        }
        else {
            color = getColorFromName(sColor);
        }

        if(color == null) {
            color = Color.black;
        }
        
        return color;
    }

    public Color getColorFromRGB(String rgb) {
        String[] values = rgb.split(",");

        if(values.length == 3) {
            try {
                int r = Integer.parseInt(values[0]);
                int g = Integer.parseInt(values[1]);
                int b = Integer.parseInt(values[2]);
                return new Color(r, g, b);
            } catch(NumberFormatException ex) {
                Utility.handleUnexpectedException(ExceptionCodes.BCT2, ex);                                      
            }
        }
        
        return null;
    }

    public Color getColorFromName(String name) {
        try {
            if (!colorIsNullOrDefault(name)) {
                Field field = Class.forName(Color.class.getName()).getField(name);
                if(field != null) {
                    return (Color)field.get(null); // because a color is a static field, the argument of Field.get() will be ignored.
                }
            }
        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.BCT3, ex);
        }

        return null;
    }

    @SuppressWarnings (value="unchecked")
    public <T> T getListener(String name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException  {
        Class<?> classs = Class.forName("md.polarbeargame.listeners." + name);
        Constructor<?> constructor = classs.getConstructor();
        return (T)constructor.newInstance();
	}

	public void setListeners(Component c, JsonArray jsonArray) {
        final String klName = KeyListener.class.getSimpleName();
        final String alName = ActionListener.class.getSimpleName();

        for (JsonElement el : jsonArray) {
            if(el.isJsonObject()) {
                JsonObject obj = el.getAsJsonObject();
                String type = obj.get("Type").getAsString();
                String name = obj.get("Name").getAsString();

                try {
                    if(c instanceof Button && type.equals(alName)) {
                        Button b = (Button)c;
                        ActionListener al = getListener(name);
                        b.addActionListener(al);
                    } else if(type.equals(klName)) {
                        KeyListener kl = getListener(name);
                        c.addKeyListener(kl);
                    }                    
                } catch(Exception ex) {
                    Utility.handleUnexpectedException(ExceptionCodes.BCT4, ex);
                }
            }
        }
	}

    public ArrayList<String> getAsArray(JsonArray jsonArray) {
        ArrayList<String> list = new ArrayList<>();  
        if (jsonArray != null) { 
            int len = jsonArray.size();
            for (int i=0;i<len;i++){ 
                list.add(jsonArray.get(i).getAsString());
            } 
        }   
        
        return list;
    }

    public abstract void addActionListener(ActionListener al);
}
