package net.coder.io.window;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public abstract class FolderChooser implements ActionListener {

    private final JLabel label;

    private static final ArrayList<String> paths = new ArrayList<>();

    private final File savedFilePath = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Documents\\File creator path.dru");

    public FolderChooser(){
        label = FileCreator.infoLabel;
        paths.add(FileCreator.FILE_PATH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        File folderPath;
        if (savedFilePath.exists() && !getSavedPath().equals(FileCreator.FILE_PATH) && !getSavedPath().isEmpty()) {
            folderPath = new File(getSavedPath());
        } else {
            savePath(FileCreator.FILE_PATH);
            folderPath = new File(FileCreator.FILE_PATH);
        }
        if (!e.getActionCommand().equals("Select folder")) {
            return;
        }
        JFileChooser folder = new JFileChooser(folderPath,FileSystemView.getFileSystemView());

        folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (folder.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        label.setText("Output directory set: " + folder.getSelectedFile().getAbsoluteFile());
        paths.add(folder.getSelectedFile().getAbsolutePath());
        folderPath = new File(paths.getLast());
        savePath();
    }

    public static ArrayList<String> getPath(){
        return paths;
    }

    private void savePath(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedFilePath));

            if (!savedFilePath.exists()){
                boolean _ = savedFilePath.createNewFile();
            } else {
                writer.write(getPath().getLast());
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void savePath(String path){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedFilePath));

            if (!savedFilePath.exists()){
                boolean _ = savedFilePath.createNewFile();
            } else {
                writer.write(path);
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSavedPath(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(savedFilePath));
            if (savedFilePath.exists()) {
                return reader.readLine();
            } else
                return FileCreator.FILE_PATH;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
