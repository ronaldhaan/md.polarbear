package md.polarbeargame;

import java.nio.file.Path;

import com.google.gson.JsonObject;

import md.polarbeargame.views.Frame;
import ec.shared.EcFile;
import ec.shared.Utility;
import ec.shared.appsettings.AppSettings;
import ec.shared.mapper.EcMap;

public class App {
    public static void main(String[] args) {
        EcMap map = new EcMap();
        JsonObject settingsJson = Utility.getJsonFile(Path.of(EcFile.getRootFolder().toString(), "src", "ec", "polarbeargame", "appsettings.json"));
        AppSettings settings = map.map(settingsJson, AppSettings.class);

        if(settings != null){
            new Frame(settings);
        } else {
            new Frame();
        }
    }
}
