package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdobeButtonUI extends BasicButtonUI {

    private MouseAdapter mouseAdapter;
    private FocusAdapter focusAdapter;
    private boolean mouseEntered = false;
    private boolean focus = false;

    @Override
    public void installUI(final JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setFocusable(true);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        button.setForeground(AdobeLookAndFeel.colorText);
        button.setFont(AdobeLookAndFeel.fontBold);

        focusAdapter = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focus = true;
                c.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                focus = false;
                c.repaint();
            }
        };

        button.addFocusListener(focusAdapter);

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEntered = true;
                c.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseEntered = false;
                c.repaint();
            }
        };

        button.addMouseListener(mouseAdapter);
        button.addMouseMotionListener(mouseAdapter);

    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractButton button = (AbstractButton) c;
        ButtonModel buttonModel = button.getModel();


        if (buttonModel.isPressed()) {
            g2d.setPaint(new GradientPaint(0, 0, new Color(64, 64, 64), 0, c.getHeight(), new Color(85, 85, 85)));
            g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

            g2d.setPaint(new Color(12, 12, 12));
            g2d.fillRect(1, 1, c.getWidth() - 2, c.getHeight() - 2);

            g2d.setPaint(new Color(31, 31, 31));
            g2d.fillRect(2, 2, 1, 1);
            g2d.fillRect(c.getWidth() - 3, 2, 1, 1);

            g2d.setPaint(new Color(33, 33, 33));
            g2d.fillRect(3, 2, c.getWidth() - 6, 1);

            g2d.setPaint(new GradientPaint(0, 0, new Color(48, 48, 48), 0, c.getHeight(), new Color(38, 38, 38)));
            g2d.fillRect(2, 3, c.getWidth() - 4, c.getHeight() - 5);

            g2d.setPaint(new Color(42, 42, 42));
            g2d.fillRect(2, 3, 1, 1);
            g2d.fillRect(c.getWidth() - 3, 3, 1, 1);

            g2d.setPaint(new GradientPaint(0, 0, new Color(55, 55, 55), 0, c.getHeight(), new Color(45, 45, 45)));
            g2d.fillRect(3, 4, c.getWidth() - 6, c.getHeight() - 6);

        } else if (mouseEntered) {
            g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, c.getHeight(), new Color(76, 76, 76)));
            g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

            g2d.setPaint(new Color(24, 24, 24));
            g2d.fillRect(1, 1, c.getWidth() - 2, c.getHeight() - 2);

            g2d.setPaint(new GradientPaint(0, 0, new Color(146, 146, 146), 0, c.getHeight(), new Color(88, 88, 88)));
            g2d.fillRect(2, 2, c.getWidth() - 4, c.getHeight() - 4);

            g2d.setPaint(new GradientPaint(0, 0, new Color(131, 131, 131), 0, c.getHeight(), new Color(83, 83, 83)));
            g2d.fillRect(3, 3, c.getWidth() - 6, c.getHeight() - 6);
        } else {
            g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, c.getHeight(), new Color(76, 76, 76)));
            g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

            g2d.setPaint(new Color(24, 24, 24));
            g2d.fillRect(1, 1, c.getWidth() - 2, c.getHeight() - 2);

            g2d.setPaint(new GradientPaint(0, 0, new Color(123, 123, 123), 0, c.getHeight(), new Color(75, 75, 75)));
            g2d.fillRect(2, 2, c.getWidth() - 4, c.getHeight() - 4);

            g2d.setPaint(new GradientPaint(0, 0, new Color(104, 104, 104), 0, c.getHeight(), new Color(71, 71, 71)));
            g2d.fillRect(3, 3, c.getWidth() - 6, c.getHeight() - 6);
        }

        /* button.setForeground(AdobeLookAndFeel.colorTextShadow);
        g2d.translate(0, 1);
        super.paint(g, c);
        g2d.translate(0, -1);    */
        super.paint(g, c);
    }


    public static ComponentUI createUI(JComponent c) {
        return new AdobeButtonUI();
    }
}
