package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class AdobeLabelUI extends BasicLabelUI {

    private JLabel label;
    public static final Font font = new Font("Calibri", 0, 12);

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        label = (JLabel) c;
        label.setFont(font);
        label.setForeground(AdobeLookAndFeel.colorText);
        //label.setForeground(AdobeLookAndFeel.colorText);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
       // label.setForeground(AdobeLookAndFeel.colorTextShadow);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      /*  g2d.translate(0, 1);
        super.paint(g, c);
        g2d.translate(0, -1); */

        super.paint(g, c);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeLabelUI();
    }
}
