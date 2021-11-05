package md.polarbeargame.listeners;

import java.lang.reflect.Constructor;
import java.util.EventListener;

import java.awt.Component;

import md.json.components.Button;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.Utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public final class JEvent {

    private JEvent() {

    }

    @SuppressWarnings (value="unchecked") 
    private static <T extends EventListener> T getListener(String name) {
        try {
            Class<?> classs = Class.forName("md.polarbeargame.listeners." + name);
            Constructor<?> constructor = classs.getConstructor();
            return (T)constructor.newInstance();
        } catch(Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.EVENT1, ex);
            return null;
        }
	}

    public static void getListeners(Component c, JsonArray jsonArray) {      
        
        for (JsonElement el : jsonArray) {
            if(el.isJsonObject()) {
                JsonObject obj = el.getAsJsonObject();
                String type = obj.get("Type").getAsString();
                String name = obj.get("Name").getAsString();

                switch(type) {
                    case "ActionListener": 
                        if (c instanceof Button) {
                            ((Button)c).addActionListener(getListener(name));
                        }
                    break;
                    case "KeyListener": 
                        c.addKeyListener(getListener(name));
                    break;
                    default: 
                        Utility.handleUnexpectedException(ExceptionCodes.EVENT2, new Exception(String.format("Unknown listener named: '%s'", name)));
                }
            }
        }
	}
}
