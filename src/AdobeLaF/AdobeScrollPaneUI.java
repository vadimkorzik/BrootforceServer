package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;

/**
 * User: Vadim
 * Date: 02.04.12
 * Time: 19:50
 */
public class AdobeScrollPaneUI extends BasicScrollPaneUI {

    JScrollPane jscrollPane;

    @Override
    public void installUI(JComponent x) {
        super.installUI(x);

        jscrollPane = (JScrollPane) x;

        jscrollPane.setOpaque(false);
        jscrollPane.setFocusable(false);
        jscrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jscrollPane.setBackground(new  Color(52, 0, 0));

    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeScrollPaneUI();
    }
}
