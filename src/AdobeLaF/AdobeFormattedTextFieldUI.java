package AdobeLaF;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * User: Vadim
 * Date: 01.04.12
 * Time: 20:29
 */
public class AdobeFormattedTextFieldUI extends BasicFormattedTextFieldUI {

    private JFormattedTextField textFormattedField;

    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private FocusListener focusListener;
    private PropertyChangeListener propertyChangeListener;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        textFormattedField = (JFormattedTextField) c;

        textFormattedField.setFocusable(true);
        textFormattedField.setOpaque(false);
        textFormattedField.setMargin(new Insets(0, 0, 0, 0));
        textFormattedField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        textFormattedField.setFont(AdobeLookAndFeel.fontBold);
        textFormattedField.setForeground(AdobeLookAndFeel.colorText);
        textFormattedField.setSelectionColor(new Color(100, 100, 100));
        textFormattedField.setCaretColor(AdobeLookAndFeel.colorText);

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                textFormattedField.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                textFormattedField.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                textFormattedField.repaint();
            }
        };
        textFormattedField.addMouseListener(mouseAdapter);

        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textFormattedField.repaint();
            }
        };
        textFormattedField.addKeyListener(keyAdapter);

        focusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textFormattedField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFormattedField.repaint();
            }
        };
        textFormattedField.addFocusListener(focusListener);

        propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                textFormattedField.repaint();
            }
        };
        textFormattedField.addPropertyChangeListener(AccessibleContext.ACCESSIBLE_STATE_PROPERTY, propertyChangeListener);
    }

    @Override
    protected void paintBackground(Graphics g) {
        // super.paintBackground(g);
    }

    @Override
    protected void paintSafely(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, textFormattedField.getHeight(), new Color(76, 76, 76)));
        g2d.fillRect(0, 0, textFormattedField.getWidth(), textFormattedField.getHeight());

        g2d.setPaint(new GradientPaint(0, 0, new Color(21, 21, 21), 0, textFormattedField.getHeight(), new Color(24, 24, 24)));
        g2d.fillRect(1, 1, textFormattedField.getWidth() - 2, textFormattedField.getHeight() - 2);

        g2d.setPaint(new Color(53, 53, 53));
        g2d.fillRect(2, 2, textFormattedField.getWidth() - 4, textFormattedField.getHeight() - 4);
        super.paintSafely(g);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeFormattedTextFieldUI();
    }
}
