package net.coder.io.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static net.coder.io.res.Values.DEFAULT_COLOUR;
import static net.coder.io.res.Values.DEFAULT_FONT;

public class FileCreator {

    public static JLabel infoLabel = new JLabel("Enter a project name and click \"Create\"");

    private final JFrame frame = new JFrame("File folder creator");
    JTextField projectNameField;

    JPanel panel = new JPanel(null);

    String inputText;

    public FileCreator() {
        panel.setPreferredSize(new Dimension(450, 175));
        panel.setFocusable(true);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/net/coder/io/res/icon.png")));

        frame.add(panel);
        frame.setIconImage(icon.getImage());
        frame.setTitle("File folder creator");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        JButton createButton = new JButton("Create");
        createButton.setBounds(260, 145, 80, 20);
        createButton.setFocusable(false);
        createButton.setFont(DEFAULT_FONT);
        createButton.setBackground(DEFAULT_COLOUR);

        projectNameField = new JTextField("Project name here");
        projectNameField.setFont(changeMyFontSize(13));
        projectNameField.setBounds(70, 30, 310, 30);
        projectNameField.addKeyListener(new MyKeyAdapter());

        infoLabel.setFont(changeMyFontSize(10));
        infoLabel.setBounds(70, 120, 310, 30);

        JButton pathButton = new JButton("Select folder");
        pathButton.setBounds(350, 145, 80, 20);
        pathButton.setFont(changeMyFontSize(8));
        pathButton.setFocusable(false);
        pathButton.setBackground(DEFAULT_COLOUR);
        pathButton.addActionListener(new FolderChooser() {
            @Override
            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
            }
        });

        createButton.addActionListener(_ -> {
            inputText = projectNameField.getText();
            if (inputText.isEmpty() || inputText.isBlank()) {
                System.out.println("Empty");
                infoLabel.setText("Type something or I won't create anything");
            } else {
                createElements(inputText, FolderChooser.getPath().getLast());
                frame.setTitle("I created project \"" + inputText + "\"");
                infoLabel.setVisible(false);
            }
        });

        panel.add(createButton);
        panel.add(projectNameField);
        panel.add(infoLabel);
        panel.add(pathButton);
        panel.addKeyListener(new MyKeyAdapter());

        frame.setVisible(true);
    }

    public static Font changeMyFontSize(int size) {
        return new Font("Trebuchet MS", Font.PLAIN, size);
    }

    private void createElements(String projectFolderName, String folderPath) {

        String projectName = projectFolderName + "\\";

        File projectFolder = new File(folderPath + "\\" + projectName);

        if (!projectFolder.exists()) {
            boolean _ = projectFolder.mkdirs();
        }

        File file = new File(projectFolder, "Title and description.txt");
        if (!file.exists()) {
            try {
                boolean _ = file.createNewFile();
            } catch (IOException e) {
                System.out.println("Not created, here's what went wrong: ");
                e.printStackTrace(System.err);
                this.close();
            }
        }

        String[] strings = new String[]{"Audio", "BGM", "Thumbnail", "Video", "Pictures", "Clip"};

        for (String folderName : strings) {
            File projectElement = new File(projectFolder, folderName);
            if (!projectElement.exists()) {
                boolean _ = projectElement.mkdir();
            }
        }
    }

    public void close() {
        frame.dispose();
    }

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                return;
            }

            inputText = projectNameField.getText();

            if (inputText.isEmpty() || inputText.isBlank()) {
                System.out.println("Empty");
                infoLabel.setText("Type something or I won't create anything");
            } else if (inputText.endsWith(".")) {
                System.out.println("Folder name ends with a full stop");
                infoLabel.setText("Don't end folder name with a symbol please!");
            } else {
                createElements(inputText, FolderChooser.getPath().getLast());
                frame.setTitle("I created project \"" + inputText + "\"");
                infoLabel.setVisible(false);
                panel.revalidate();
                panel.repaint();
            }
        }
    }
}
