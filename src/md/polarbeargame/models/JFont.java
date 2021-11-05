package md.polarbeargame.models;

import java.awt.Font;

import com.google.gson.JsonObject;

public class JFont extends Font {
    private static final long serialVersionUID = 1L;

    public JFont(JsonObject obj) {
        this(obj.get("Name").getAsString(), getFontStyle(obj.get("Style").getAsString()), obj.get("Size").getAsInt()); 
    }

    public JFont(String name, int fontStyle, int size) {
        super(name, fontStyle, size);
    }

    private static int getFontStyle(String style) {
        int fontStyle = 0;

        switch (style) {
            case "Bold":
                fontStyle = Font.BOLD;
                break;
            case "Italic":
                fontStyle = Font.ITALIC;
                break;
            default:
                fontStyle = Font.PLAIN;
                break;
        }

        return fontStyle;
    }
}
