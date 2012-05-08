package AdobeLaF;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;

public class AdobeLookAndFeel extends BasicLookAndFeel {

    public static final Color colorText = new Color(230, 230, 230);
    public static Color colorTextShadow = new Color(20, 20, 20);
    public static Color colorBg = new Color(61, 61, 61);

    public static final Font fontBold = new Font("Calibri", Font.BOLD, 14);

    @Override
    public String getName() {
        return "AdobeLookAndFeel";
    }

    @Override
    public String getID() {
        return getName();
    }

    @Override
    public String getDescription() {
        return "Cross-platform Java Look and Feel";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

    @Override
    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        table.put("ButtonUI", AdobeButtonUI.class.getCanonicalName());
        table.put("PanelUI", AdobePanelUI.class.getCanonicalName());
        table.put("TextFieldUI", AdobeTextFieldUI.class.getCanonicalName());
        table.put("LabelUI", AdobeLabelUI.class.getCanonicalName());
        table.put("FormattedTextFieldUI", AdobeFormattedTextFieldUI.class.getCanonicalName());
        table.put("ScrollPaneUI", AdobeScrollPaneUI.class.getCanonicalName());
        table.put("ListUI", AdobeListUI.class.getCanonicalName());
        table.put("ScrollBarUI", AdobeScrollBarUI.class.getCanonicalName());
    }

    @Override
    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);    //To change body of overridden methods use File | Settings | File Templates.
        initializeAdobeDefaults();
    }

    private void initializeAdobeDefaults() {
        // AdobeTextField actions
        UIManager.put("TextField.focusInputMap", new UIDefaults.LazyInputMap(
                new Object[]{"control C", DefaultEditorKit.copyAction, "control V",
                        DefaultEditorKit.pasteAction, "control X",
                        DefaultEditorKit.cutAction, "COPY",
                        DefaultEditorKit.copyAction, "PASTE",
                        DefaultEditorKit.pasteAction, "CUT",
                        DefaultEditorKit.cutAction, "control INSERT",
                        DefaultEditorKit.copyAction, "shift INSERT",
                        DefaultEditorKit.pasteAction, "shift DELETE",
                        DefaultEditorKit.cutAction, "control A",
                        DefaultEditorKit.selectAllAction, "control BACK_SLASH", "unselect", "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "control LEFT",
                        DefaultEditorKit.previousWordAction, "control RIGHT",
                        DefaultEditorKit.nextWordAction, "control shift LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "control shift RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "HOME",
                        DefaultEditorKit.beginLineAction, "END",
                        DefaultEditorKit.endLineAction, "shift HOME",
                        DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "ctrl DELETE",
                        DefaultEditorKit.deleteNextWordAction, "ctrl BACK_SPACE",
                        DefaultEditorKit.deletePrevWordAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT",
                        DefaultEditorKit.backwardAction, "KP_RIGHT",
                        DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "ENTER", JTextField.notifyAction,
                        "control shift O", "toggle-componentOrientation"
                }));

        // AdobeFormattedTextField actions
        UIManager.put("FormattedTextField.focusInputMap", new UIDefaults.LazyInputMap(
                new Object[]{"ctrl C", DefaultEditorKit.copyAction, "ctrl V",
                        DefaultEditorKit.pasteAction, "ctrl X",
                        DefaultEditorKit.cutAction, "COPY",
                        DefaultEditorKit.copyAction, "PASTE",
                        DefaultEditorKit.pasteAction, "CUT",
                        DefaultEditorKit.cutAction, "control INSERT",
                        DefaultEditorKit.copyAction, "shift INSERT",
                        DefaultEditorKit.pasteAction, "shift DELETE",
                        DefaultEditorKit.cutAction, "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift KP_LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "shift KP_RIGHT",
                        DefaultEditorKit.selectionForwardAction, "ctrl LEFT",
                        DefaultEditorKit.previousWordAction, "ctrl KP_LEFT",
                        DefaultEditorKit.previousWordAction, "ctrl RIGHT",
                        DefaultEditorKit.nextWordAction, "ctrl KP_RIGHT",
                        DefaultEditorKit.nextWordAction, "ctrl shift LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "ctrl shift KP_LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "ctrl shift RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "ctrl shift KP_RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "ctrl A",
                        DefaultEditorKit.selectAllAction, "HOME",
                        DefaultEditorKit.beginLineAction, "END",
                        DefaultEditorKit.endLineAction, "shift HOME",
                        DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "ctrl DELETE",
                        DefaultEditorKit.deleteNextWordAction, "ctrl BACK_SPACE",
                        DefaultEditorKit.deletePrevWordAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT",
                        DefaultEditorKit.backwardAction, "KP_RIGHT",
                        DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "ENTER", JTextField.notifyAction,
                        "ctrl BACK_SLASH", "unselect", "control shift O",
                        "toggle-componentOrientation", "ESCAPE", "reset-field-edit", "UP",
                        "increment", "KP_UP", "increment", "DOWN", "decrement", "KP_DOWN",
                        "decrement",}));
    }
}
