package AdobeLaF;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdobeTextFieldUI extends BasicTextFieldUI {
    private JTextField textField;

    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private FocusListener focusListener;
    private PropertyChangeListener propertyChangeListener;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        textField = (JTextField) c;
        textField.setFocusable(true);
        textField.setOpaque(false);
        textField.setMargin(new Insets(0, 0, 0, 0));
        textField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        textField.setFont(AdobeLookAndFeel.fontBold);
        textField.setForeground(AdobeLookAndFeel.colorText);
        textField.setSelectionColor(new Color(100, 100, 100));
        textField.setCaretColor(AdobeLookAndFeel.colorText);

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                textField.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                textField.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                textField.repaint();
            }
        };
        textField.addMouseListener(mouseAdapter);

        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField.repaint();
            }
        };
        textField.addKeyListener(keyAdapter);

        focusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.repaint();
            }
        };
        textField.addFocusListener(focusListener);

        propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                textField.repaint();
            }
        };
        textField.addPropertyChangeListener(AccessibleContext.ACCESSIBLE_STATE_PROPERTY, propertyChangeListener);

    }

    @Override
    protected void paintBackground(Graphics g) {

       // super.paintBackground(g);
    }

    @Override
    protected void paintSafely(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, textField.getHeight(), new Color(76, 76, 76)));
        g2d.fillRect(0, 0, textField.getWidth(), textField.getHeight());

        g2d.setPaint(new GradientPaint(0, 0, new Color(21, 21, 21), 0, textField.getHeight(), new Color(24, 24, 24)));
        g2d.fillRect(1, 1, textField.getWidth() - 2, textField.getHeight() - 2);

        g2d.setPaint(new Color(53, 53, 53));
        g2d.fillRect(2, 2, textField.getWidth() - 4, textField.getHeight() - 4);
        super.paintSafely(g);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeTextFieldUI();
    }
}
