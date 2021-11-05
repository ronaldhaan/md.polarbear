package md.polarbeargame.models;

import java.awt.Color;
import java.lang.reflect.Field;

import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.Utility;

public class JColor extends Color {
    private static final long serialVersionUID = 1L;

    public JColor(String name) {
        super(getColor(name).getRGB());
    }

    public static boolean isNullOrDefault(String color) {
        return color == null || color.equals("default");
    }

    public static Color getColor(String sColor) {
        Color color = Color.black;

        if(sColor.contains(",")) {
            //
            String[] values = sColor.split(",");

            if(values.length == 3) {
                try {
                    int r = Integer.parseInt(values[0].trim());
                    int g = Integer.parseInt(values[1].trim());
                    int b = Integer.parseInt(values[2].trim());
                    color = new Color(r, g, b);
                } catch(NumberFormatException ex) {
                    Utility.handleUnexpectedException(ExceptionCodes.BCT2, ex);                                        
                }
            }
        }
        else {
            try {
                if (!isNullOrDefault(sColor)) {
                    Field field = Class.forName("java.awt.Color").getField(sColor);
                    if(field != null) {
                        color = (Color) field.get(null);
                    }
                }
            } catch (Exception ex) {
                Utility.handleUnexpectedException(ExceptionCodes.BCT3, ex);
            }
        }

        return color;
    }
}
