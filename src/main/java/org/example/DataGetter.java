package org.example;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.util.EnumMap;
import java.util.Map;

public class DataGetter {
    public static Map<Arguments, String> fromCMD(String[] args) {
        var argsMap = new EnumMap<Arguments, String>(Arguments.class);
        // Define options
        Options options = new Options();

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

            argsMap.put(Arguments.TITLE, line.getOptionValue(Arguments.TITLE.getLongOpt()));
            argsMap.put(Arguments.CATEGORY, line.getOptionValue("category"));

            if (line.hasOption(Arguments.IMAGE.getLongOpt())) {
                argsMap.put(Arguments.IMAGE, line.getOptionValue(Arguments.IMAGE.getLongOpt()));
            }
            if (line.hasOption(Arguments.PATH.getLongOpt())) {
                argsMap.put(Arguments.PATH, line.getOptionValue(Arguments.PATH.getLongOpt()));
            }

        } catch (ParseException exp) {
            System.out.println("Parsing failed. Reason: {}. Example use: createJekyllPost --title web-app-example-post --category web --image welcome.jpg" + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("createJekyllPost", options);
        }
        return argsMap;
    }

}
