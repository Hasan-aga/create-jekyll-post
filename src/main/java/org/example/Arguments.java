package org.example;

import lombok.Getter;

@Getter
public enum Arguments {
    TITLE("t", "title", true, "Title of the post", true),
    CATEGORY("c", "category", true, "Category of the post", true),
    IMAGE("i", "image", true, "Header of the post", false),
    HELP("h", "help", false, "Print this message", false);

    private final String opt;
    private final String longOpt;
    private final boolean hasArg;
    private final String description;
    private final boolean required;

    Arguments(String opt, String longOpt, boolean hasArg, String description, Boolean required) {
        this.opt = opt;
        this.longOpt = longOpt;
        this.hasArg = hasArg;
        this.description = description;
        this.required = required;
    }

}
