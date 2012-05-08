package com.vadim.Bruteforce.GUI;

import AdobeLaF.AdobeLookAndFeel;
import com.vadim.Bruteforce.Main;
import com.vadim.Bruteforce.core.BruteforceManager;
import com.vadim.Bruteforce.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:54
 */
public class MainGUI extends JFrame {

    private final Dimension mainWindowDimension = new Dimension(445, 300);
    private JPanel mainPanel;

    private JTextField hashTextField;
    private JTextField countIntervalsTextField;
    private JTextField portTextField;
    private JTextField lastPassTextField;

    private JButton startButton;
    private JButton stopButton;

    private JLabel hashLabel;
    private JLabel countIntervalsLabel;
    private JLabel portLabel;
    private JLabel lastPassLabel;

    private JLabel passwordLabel;

    private BruteforceManager bruteforceManager;
    private NetworkManager networkManager;

    MainGUI mainGUI = this;

    public MainGUI() {
        super("Server");
        initWindow();
    }

    private void initWindow() {
        try {
            UIManager.setLookAndFeel(AdobeLookAndFeel.class.getCanonicalName());
        } catch (Exception ignored) {
        }
        this.setSize(mainWindowDimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, mainWindowDimension.width, mainWindowDimension.height);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        this.add(mainPanel);
        this.setResizable(false);
        initTextFields();
        initLabels();
        initLog();
        initButtons();
    }

    private void initLog() {
        final DefaultListModel<String> model = new DefaultListModel<String>();
        final JList<String> list = new JList<String>(model);

        final JScrollPane pane = new JScrollPane(list);
        pane.setBounds(10, 150, 410, 100);
        pane.setVisible(true);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setAutoscrolls(true);
        mainPanel.add(pane);
        Main.logger.setListModel(model);
    }

    private void initLabels() {
        hashLabel = new JLabel("sha-1 hash");
        hashLabel.setBounds(130, 5, 70, 20);
        hashLabel.setVisible(true);
        mainPanel.add(hashLabel);

        countIntervalsLabel = new JLabel("intervals");
        countIntervalsLabel.setBounds(30, 55, 70, 20);
        countIntervalsLabel.setVisible(true);
        mainPanel.add(countIntervalsLabel);

        portLabel = new JLabel("port");
        portLabel.setBounds(260, 55, 70, 20);
        portLabel.setVisible(true);
        mainPanel.add(portLabel);

        lastPassLabel = new JLabel("last pass");
        lastPassLabel.setBounds(140, 55, 70, 20);
        lastPassLabel.setVisible(true);
        mainPanel.add(lastPassLabel);

        passwordLabel = new JLabel();
        passwordLabel.setBounds(140, 115, 70, 20);
        passwordLabel.setVisible(true);
        mainPanel.add(passwordLabel);
    }

    private void initTextFields() {
        hashTextField = new JTextField();
        hashTextField.setBounds(15, 25, 300, 25);
        hashTextField.setVisible(true);
        mainPanel.add(hashTextField);

        countIntervalsTextField = new JTextField();
        countIntervalsTextField.setBounds(15, 75, 90, 25);
        countIntervalsTextField.setVisible(true);
        mainPanel.add(countIntervalsTextField);

        portTextField = new JTextField();
        portTextField.setBounds(225, 75, 90, 25);
        portTextField.setVisible(true);
        mainPanel.add(portTextField);

        lastPassTextField = new JTextField();
        lastPassTextField.setBounds(120, 75, 90, 25);
        lastPassTextField.setVisible(true);
        mainPanel.add(lastPassTextField);
    }

    private void initButtons() {
        startButton = new JButton("start");
        startButton.setBounds(15, 110, 90, 25);
        startButton.setVisible(true);
        mainPanel.add(startButton);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String sha1Hash = hashTextField.getText();
                if (sha1Hash.equals("")) {
                    Main.logger.warning("Enter Sha-1 hash");
                    return;
                }

                int countIntervals;
                try {
                    countIntervals = Integer.parseInt(countIntervalsTextField.getText());
                } catch (NumberFormatException ex) {
                    Main.logger.warning("Enter count of intervals");
                    return;
                }

                String lastPass = lastPassTextField.getText();
                if (lastPass.equals("")) {
                    Main.logger.warning("Enter last password");
                    return;
                }

                int port;
                try {
                    port = Integer.parseInt(portTextField.getText());
                } catch (NumberFormatException ex) {
                    Main.logger.warning("Enter port");
                    return;
                }

                hashTextField.setEnabled(false);
                countIntervalsTextField.setEnabled(false);

                bruteforceManager = new BruteforceManager(countIntervals, lastPass, sha1Hash);
                bruteforceManager.setMainGUI(mainGUI);
                networkManager = new NetworkManager(port, bruteforceManager);
                networkManager.start();
                allTextFieldsEnable(false);
                startButton.setEnabled(false);

            }
        });

        stopButton = new JButton("stop");
        stopButton.setBounds(225, 110, 90, 25);
        stopButton.setVisible(true);
        mainPanel.add(stopButton);

        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stop();
            }
        });
    }

    private void stop() {
        try {
            networkManager.closeServer();
            networkManager.interrupt();
            allTextFieldsEnable(true);
            startButton.setEnabled(true);
        } catch (Exception ex) {

        }
    }

    private void allTextFieldsEnable(boolean bool) {
        hashTextField.setEnabled(bool);
        countIntervalsTextField.setEnabled(bool);
        lastPassTextField.setEnabled(bool);
        portTextField.setEnabled(bool);
    }

    public void success(String password) {
        this.passwordLabel.setText(password);
        stop();
    }
}
