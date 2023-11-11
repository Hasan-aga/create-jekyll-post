package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
@Slf4j
public class Main {
    // 1. get post info [from cli] createJekyllPost <title> <tag> <image name>
    // title
    // tag
    // header image name
    // 2. create files
    // create post file and fill its header [tags, image]
    // create assets folder
    public static void main(String[] args) {

        try {
            var arguments = DataGetter.fromCMD(args);
            String name = null;
            if (!arguments.isEmpty()) {
                name = FileCreator.createPostFileAndReturnName(arguments.get(Arguments.TITLE), arguments.get(Arguments.CATEGORY), arguments.getOrDefault(Arguments.IMAGE, "placeholder"),arguments.getOrDefault(Arguments.PATH, "."));
            }
            if (name != null) {
                FileCreator.createAssetsDirectory(name, arguments.getOrDefault(Arguments.PATH, "."));
            }
        } catch (Exception e) {
            log.error("Failed in one or more operation, {}", e);
        }
    }
}