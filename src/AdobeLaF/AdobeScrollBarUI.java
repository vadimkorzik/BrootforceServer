package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * User: Vadim | Date: 03.04.12 | Time: 9:26
 */
public class AdobeScrollBarUI extends BasicScrollBarUI {

    public static int ScrollBarWidth = 10;
    private JScrollBar jScrollBar;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        this.scrollBarWidth = ScrollBarWidth;
        jScrollBar = (JScrollBar) c;
        jScrollBar.setUnitIncrement(4);
        jScrollBar.setUnitIncrement(16);
        this.thumbDarkShadowColor = new Color(200, 0, 0);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        //super.paintThumb(g, c, thumbBounds);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, new Color(73, 73, 73), 0, c.getHeight(), new Color(85, 85, 85)));
        g2d.fillRect(thumbBounds.x + 1, thumbBounds.y + 1, thumbBounds.width - 2, thumbBounds.height - 2);

        g2d.setPaint(new Color(49, 49, 49));
        g2d.fillRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 4, thumbBounds.height - 4);
    }

    @Override
    protected void paintDecreaseHighlight(Graphics g) {
        super.paintDecreaseHighlight(g);
    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {
        super.paintIncreaseHighlight(g);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        super.paintTrack(g, c, trackBounds);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, c.getHeight(), new Color(75, 75, 75)));
        g2d.fillRect(0, 0, trackBounds.width, c.getHeight());

        g2d.setPaint(AdobeLookAndFeel.colorBg);
        g2d.fillRect(1, 1, trackBounds.width - 2, c.getHeight() - 2);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new AdobeBasicArrowButton(orientation);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new AdobeBasicArrowButton(orientation);
    }

    @Override
    protected void configureScrollBarColors() {
        super.configureScrollBarColors();

    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeScrollBarUI();
    }
}
