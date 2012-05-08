package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.*;

/**
 * User: Vadim | Date: 02.04.12 | Time: 21:50
 */
public class AdobeListUI extends BasicListUI {
    JList list;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        list = (JList) c;

        list.setOpaque(false);
        list.setFocusable(true);
        list.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
        list.setFont(new Font("Calibri", 0, 12));
        list.setForeground(AdobeLookAndFeel.colorText);
        list.setBackground(new Color(61, 61, 61));
    }

    @Override
    protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer,
                             ListModel dataModel, ListSelectionModel selModel, int leadIndex) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintCell(g, row, rowBounds, cellRenderer, dataModel, selModel, leadIndex);
//        g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, list.getHeight(), new Color(75, 75, 75)));
//        g2d.fillRect(0, 0, 1, list.getHeight());

    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(AdobeLookAndFeel.colorBg);
        g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
        g2d.setPaint(new GradientPaint(0, 0, new Color(63, 63, 63), 0, c.getHeight(), new Color(75, 75, 75)));
        g2d.fillRect(0, 0, 1, c.getHeight());
        super.paint(g, c);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AdobeListUI();
    }
}
