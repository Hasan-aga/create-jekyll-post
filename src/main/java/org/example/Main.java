package org.example;

public class Main {
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
            System.out.println("Failed in one or more operation, " + e);
        }
    }
}