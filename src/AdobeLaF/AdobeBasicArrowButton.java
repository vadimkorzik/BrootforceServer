package AdobeLaF;

import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * User: Vadim | Date: 03.04.12 | Time: 21:03
 */
public class AdobeBasicArrowButton extends BasicArrowButton {
    public AdobeBasicArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
        super(direction, background, shadow, darkShadow, highlight);

    }

    public AdobeBasicArrowButton(int direction) {
        super(direction);

    }

    @Override
    public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {

        //super.paintTriangle(g, x, y, size, direction, isEnabled);

        int mid, i, j;
        size = Math.max(size, 2);
        mid = (size / 2);

        g.setColor(new Color(39, 39, 39));
        g.translate(x, y + 1);
        switch (direction) {
            case NORTH:
                for (i = 0; i < size; i++) {
                    g.drawLine(mid - i, i, mid + i, i);
                }
                break;
            case SOUTH:
                j = 0;
                for (i = size - 1; i >= 0; i--) {
                    g.drawLine(mid - i, j, mid + i, j);
                    j++;
                }
                break;
            case WEST:
                for (i = 0; i < size; i++) {
                    g.drawLine(i, mid - i, i, mid + i);
                }
                break;
            case EAST:
                j = 0;
                for (i = size - 1; i >= 0; i--) {
                    g.drawLine(j, mid - i, j, mid + i);
                    j++;
                }
                break;
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        //super.paintBorder(g);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        int w, h, size;

        w = getSize().width;
        h = getSize().height;
        // Draw the arrow
        size = Math.min((h - 4) / 3, (w - 4) / 3);
        size = Math.max(size, 2);
        paintTriangle(g, (w - size) / 2, (h - size) / 2,
                size, direction, true);

    }
}
