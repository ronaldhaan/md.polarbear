package md.polarbeargame.views;

import java.awt.Container;
import java.lang.reflect.Constructor;

import javax.swing.WindowConstants;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import md.json.components.JsonFrame;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.Utility;
import md.shared.appsettings.AppSettings;

public class Frame extends JsonFrame {
    private static final long serialVersionUID = 1L;
    private JsonObject jsonData;

    public Frame() {
        super(Frame.class);
        setTitle(getJsonData().get("Title").getAsString());
        init();
    }

    public Frame(AppSettings appSettings) {
        super(Frame.class);
        setTitle(appSettings.getAppName());
        jsonData = getJsonData();
        init();
    }

    private void init() {
        setSize(jsonData.get("Size"));
        setDefaultCloseOperation(jsonData.get("DefaultCloseOperation"));
        setResizable(jsonData.get("Resizable"));

        /**
         * het centreren van de frame
         */
        setLocationRelativeTo(null);

        /**
         * het aanmaken van van een paneel met JPanel
         */
        setContentPane(jsonData.get("ContentPanel"));
        setVisible(true);
    }

    private void setContentPane(JsonElement jsonElement) {
        if(jsonElement != null) {
            try {
                Class<?> c = Class.forName("md.polarbeargame.views." + jsonElement.getAsString());
                Constructor<?> constructor = c.getConstructor();
                super.setContentPane((Container)constructor.newInstance());
            } catch(Exception ex) {
                Utility.handleUnexpectedException(ExceptionCodes.FRAME1, ex);
            }
        }
    }

    private void setResizable(JsonElement jsonElement) {
        if(jsonElement == null) {
            setResizable(true);
        } else {
            setResizable(jsonElement.getAsBoolean());
        }
    }

    private void setDefaultCloseOperation(JsonElement jsonElement) {
        if (jsonElement == null) {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(jsonElement.getAsInt());
        }
    }

    private void setSize(JsonElement jsonElement) {
        if (jsonElement == null) {
            setSize(788, 785);
        } else {
            String[] size = jsonElement.getAsString().split(",");
            Integer x = Integer.parseInt(size[0].trim());
            Integer y = Integer.parseInt(size[1].trim());
            setSize(x, y);
        }
    }

}
