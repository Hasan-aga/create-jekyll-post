package org.example;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class FileCreator {
    // gets info
    // creates post file and fills it
    // creates assets directory
    public static void createPostFile(String title, String category) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        String fileName = date + "-" + title.replaceAll("\\s+", "-") + ".md";

        var postsDirectory = Paths.get(System.getProperty("user.dir"), "_posts");
        var filePath = postsDirectory.resolve(fileName);


        try {
            if (!Files.exists(postsDirectory)) {
                throw new IOException("posts directory could not be found in " + System.getProperty("user.dir"));
            }
            // Create the markdown file
            var file = new File(filePath.toString());
            if (file.createNewFile()) {
                log.info("File created: " + file.getName());
            } else {
                log.info("File already exists.");
                return;
            }

            // Write some initial content to the file
            var writer = new FileWriter(file);
            writer.write("---\n");
            writer.write("title: " + title + "\n");
            writer.write("date: " + date + "\n");
            writer.write("category: " + category + "\n");
            writer.write("---\n\n");
            writer.write("Content goes here.");
            writer.close();

        } catch (IOException e) {
            log.error("An error occurred: " + e.getMessage());
        }
    }
}

