package md.polarbeargame.models;

import java.awt.Rectangle;

public class JBounds extends Rectangle  {
    private static final long serialVersionUID = 1L;

    public JBounds(String bounds) {
        super(getBounds(bounds));
    }

    private static Rectangle getBounds(String bounds) throws NumberFormatException {
        bounds = bounds.trim();
        String[] values = bounds.split(",");

        if (values.length != 4)
            return null;

        int x = Integer.parseInt(values[0].trim());
        int y = Integer.parseInt(values[1].trim());
        int w = Integer.parseInt(values[2].trim());
        int h = Integer.parseInt(values[3].trim());

        return new Rectangle(x, y, w, h);

    }
    
}
