package md.polarbeargame.views;

import com.google.gson.JsonObject;

import md.json.components.JsonFrame;
import md.shared.appsettings.AppSettings;

public class Frame extends JsonFrame {
    private static final long serialVersionUID = 1L;
    private JsonObject jData;

    public Frame() {
        super(Frame.class);
        setTitle(getJsonData().get("Title").getAsString());
        init();
    }

    public Frame(AppSettings appSettings) {
        super(Frame.class);
        setTitle(appSettings.getAppName());
        jData = getJsonData();
        init();
    }

    private void init() {
        setSize(jData.get("Size"));
        setDefaultCloseOperation(jData.get("DefaultCloseOperation"));
        setResizable(jData.get("Resizable"));
        setAlwaysOnTop(jData.get("AlwaysOnTop"));

        /**
         * het centreren van de frame
         */
        setLocationRelativeTo(null);

        /**
         * het aanmaken van van een paneel met JPanel
         */
        setContentPane(jData.get("ContentPanel"));
        setVisible(true);
    }

}
