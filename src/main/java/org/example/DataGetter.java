package org.example;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataGetter {
    private static final Logger logger = LoggerFactory.getLogger(DataGetter.class);

    public static Map<String, String> fromCMD(String[] args) {
        var argsMap = new HashMap<String, String>();
        // Define options
        Options options = new Options();
        options.addOption(Option.builder("t")
                .longOpt("title")
                .hasArg()
                .desc("Title of the post")
                .required() // Make title required
                .build());
        options.addOption(Option.builder("c")
                .longOpt("category")
                .hasArg()
                .desc("Category of the post")
                .required() // Make category required
                .build());
        options.addOption("i", "image", true, "Header of the post");
        options.addOption("h", "help", false, "Print this message");

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        try {
            // Parse the command line arguments
            CommandLine line = parser.parse(options, args);

            // Check for specific options
            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("createJekyllPost", options);
                return argsMap;
            }

            argsMap.put("title", line.getOptionValue("title"));
            argsMap.put("category", line.getOptionValue("category"));

            if (line.hasOption("image")) {
                argsMap.put("image", line.getOptionValue("image"));
            }

        } catch (ParseException exp) {
            logger.error("Parsing failed. Reason: {}. Example use: createJekyllPost --title web-app-example-post --category web --image welcome.jpg", exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("createJekyllPost", options);
        }
        return argsMap;
    }

}
