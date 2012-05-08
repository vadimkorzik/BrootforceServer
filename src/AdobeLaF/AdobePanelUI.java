package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;

public class AdobePanelUI extends BasicPanelUI {
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(61, 61, 61));
        g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

        super.paint(g, c);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobePanelUI();
    }
}
