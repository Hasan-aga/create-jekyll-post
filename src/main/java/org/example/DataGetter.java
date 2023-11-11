package org.example;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DataGetter {
    private static final Logger logger = LoggerFactory.getLogger(DataGetter.class);

    public static Map<Arguments, String> fromCMD(String[] args) {
        var argsMap = new EnumMap<Arguments, String>(Arguments.class);
        // Define options
        Options options = new Options();
        options.addOption(Option.builder("t")
                .longOpt(Arguments.TITLE.toString())
                .hasArg()
                .desc("Title of the post")
                .required() // Make title required
                .build());
        options.addOption(Option.builder("c")
                .longOpt(Arguments.CATEGORY.toString())
                .hasArg()
                .desc("Category of the post")
                .required() // Make category required
                .build());
        options.addOption("i", Arguments.IMAGE.toString(), true, "Header of the post");
        options.addOption("h", Arguments.HELP.toString(), false, "Print this message");
        for (Arguments arg : Arguments.values()) {
            options.addOption(Option.builder(arg.getOpt())
                    .longOpt(arg.getLongOpt())
                    .hasArg(arg.isHasArg())
                    .required(arg.isRequired())
                    .desc(arg.getDescription())
                    .build());
        }

        // Create a parser
        CommandLineParser parser = new DefaultParser();

        try {
            // Parse the command line arguments
            CommandLine line = parser.parse(options, args);

            // Check for specific options
            if (line.hasOption(Arguments.HELP.toString())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("createJekyllPost", options);
                return argsMap;
            }

            argsMap.put(Arguments.TITLE, line.getOptionValue("title"));
            argsMap.put(Arguments.CATEGORY, line.getOptionValue("category"));

            if (line.hasOption(Arguments.IMAGE.toString())) {
                argsMap.put(Arguments.IMAGE, line.getOptionValue("image"));
            }

        } catch (ParseException exp) {
            logger.error("Parsing failed. Reason: {}. Example use: createJekyllPost --title web-app-example-post --category web --image welcome.jpg", exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("createJekyllPost", options);
        }
        return argsMap;
    }

}
