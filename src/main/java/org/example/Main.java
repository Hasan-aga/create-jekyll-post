package org.example;

import java.util.Arrays;

public class Main {
    // 1. get post info [from cli] createJekyllPost <title> <tag> <image name>
    // title
    // tag
    // header image name
    // 2. create files
    // create post file and fill its header [tags, image]
    // create assets folder
    public static void main(String[] args) {
        var arguments = DataGetter.fromCMD(args);
        System.out.println(arguments);
        FileCreator.createPostFile(arguments.get("title"), arguments.get("category"));
    }
}