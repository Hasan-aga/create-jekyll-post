# Jekyll Post Creator

## Description
This Java application is designed to create and manage posts for a Jekyll website. It includes functionalities to create a new post file with metadata and to set up a corresponding assets directory.

## Prerequisites
- Java JDK 17 or higher
- Maven (for building the project)

## Using a pre-built jar file
You can either build the jar or download a pre-built one from https://github.com/Hasan-aga/create-jekyll-post/releases/download/v1.1/create-jekyll-post-1.1-jar-with-dependencies.jar

## Building the Project
To build the project, run the following command in the root directory of the project:

```bash
mvn clean package
```

This will generate a JAR file in the `target` directory.

## Running the Application
Run the application using the following command:

```bash
java -jar target/create-jekyll-post-1.0.jar [options]
```

### Options
- `-t, --title <arg>`   Title of the post (required)
- `-c, --category <arg>` Category of the post (required)
- `-i, --image <arg>`   Header image for the post
- `-p, --path <arg>`    Path to the blog's directory
- `-h, --help`          Print usage information

### Example
```bash
java -jar target/create-jekyll-post-1.1-jar-with-dependencies.jar --title "My New Post" --category "Tech" --image "header.jpg" --path "./my-blog"
```

## Features
- Create a markdown file for a new blog post with specified title and category.
- Generate an assets directory for the post.

---

### Additional Notes
- Customize the README according to the actual features and usage of your application.
- Consider adding more sections like "Configuration", "Contributing", or "License" as needed.
- For open-source projects, it's often helpful to include sections like "How to Contribute", "Code of Conduct", and "License Information".

### Credits
Created by Hasan Aga on a rainy saturday in Istanbul. May it benefit more people.
