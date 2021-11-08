package md.polarbeargame.views;

import md.json.components.JsonFrame;
import md.shared.appsettings.AppSettings;

public class Frame extends JsonFrame {
    private static final long serialVersionUID = 1L;

    public Frame(AppSettings appSettings) {
        super(Frame.class);

        String title;
        if (appSettings != null) {
            title = appSettings.getAppName();
        } else {
            title = getJsonData().get("Title").getAsString();
        }

        setTitle(title);
    }
}
