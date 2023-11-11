package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCreator {
    private FileCreator() {
        System.out.println("This class needs not be instantiated.");
    }

    private static final String CURRENT_DIR = ".";

    public static String createPostFileAndReturnName(String title, String category, String imageName, String path) throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        var name = date + "-" + title.replaceAll("\\s+", "-");
        var mdFileName = name + ".md";

        Path homeDirectory = getHomeDirectory(path);
        Path postsDirectory = homeDirectory.resolve("_posts");
        var filePath = postsDirectory.resolve(mdFileName);


        if (!Files.exists(postsDirectory)) {
            throw new IOException("posts directory could not be found in " + homeDirectory.toAbsolutePath());
        }
        // Create the markdown file
        var file = new File(filePath.toString());
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
            return name;
        }

        // Write some initial content to the file
        try (var writer = new FileWriter(file)) {
            writer.write("---\n");
            writer.write("layout: post" + "\n");
            writer.write("title: " + title.substring(0, 1).toUpperCase() + title.substring(1).replace("-", " ") + "\n");
            writer.write("date: " + date + "\n");
            writer.write("category: [" + category.toLowerCase() + "]\n");
            writer.write("---\n\n");
            writer.write("<img src=\"/assets/" + name + "/" + imageName + "\" alt=\"" + imageName + "\"/>");
            return name;
        }
    }

    private static Path getHomeDirectory(String path) {
        return Paths.get(path.isEmpty() ? CURRENT_DIR : path);
    }

    public static void createAssetsDirectory(String name, String pathToHome) {
        // Construct the path for the new directory inside the assets directory
        String assetsDirPath = getHomeDirectory(pathToHome) + File.separator + "assets" + File.separator + name;

        File directory = new File(assetsDirPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Assets directory created: " + directory.getPath());
            } else {
                System.out.println("Failed to create assets directory: " + directory.getPath());
            }
        } else {
            System.out.println("Assets directory already exists: " + directory.getPath());
        }
    }

}

