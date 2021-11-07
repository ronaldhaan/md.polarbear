package md.polarbeargame;

import java.nio.file.Path;

import com.google.gson.JsonObject;

import md.json.JsonFile;
import md.polarbeargame.views.Frame;
import md.shared.MdFiles;
import md.shared.appsettings.AppSettings;
import md.shared.mapper.Mapper;

public class App {
    public static void main(String[] args) {
        JsonObject settingsJson = JsonFile
                .get(Path.of(MdFiles.getRootFolder().toString(), "src", "md", "polarbeargame", "appsettings.json"));
        AppSettings settings = Mapper.map(settingsJson, AppSettings.class);

        if (settings != null) {
            new Frame(settings);
        } else {
            new Frame();
        }
    }
}
