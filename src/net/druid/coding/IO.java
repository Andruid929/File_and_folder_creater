package net.druid.coding;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IO {

    public IO(String name){
        showInfoLol(name);
        createElements();
        System.out.println("Finished, now check your desktop mate");
    }

    private void createElements(){
        Scanner scanner = new Scanner(System.in);

        final String FILEPATH = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Druid\\";

        System.out.print("Name of project: ");
        String projectName = scanner.nextLine() + "\\";

        File projectFolder = new File(FILEPATH + projectName);

        if (!projectFolder.exists()) {
            boolean _ = projectFolder.mkdirs();
        }

        File file = new File(projectFolder, "Title and description.txt");
        if (!file.exists()){
            try {
                boolean _ = file.createNewFile();
            } catch (IOException e) {
                System.out.println("Not created, here's what went wrong: ");
                e.printStackTrace();
            }
        }

        String[] strings = new String[]{"Audio", "BGM", "Thumbnail", "Video"};

        for (String folderName : strings){
            File projectElement = new File(projectFolder, folderName);
            if (!projectElement.exists()){
                boolean _ = projectElement.mkdir();
            }
        }
    }

    private void showInfoLol(String yourName){
        System.out.println("Your name is: " + yourName);
    }

}
