import org.example.FileCreator;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FileCreatorTest {

    @Test
    void createPostFileAndReturnNameTest() throws IOException {
        String testTitle = "TestTitle";
        String testCategory = "TestCategory";
        String testImage = "TestImage.jpg";
        String testPath = ".";

        // Create necessary directories
        Path postsDirectory = Paths.get(testPath, "_posts");
        Files.createDirectories(postsDirectory);

        // Call the method
        String fileName = FileCreator.createPostFileAndReturnName(testTitle, testCategory, testImage, testPath);

        // Assertions
        assertNotNull(fileName);

        // Verify the file exists
        File createdFile = postsDirectory.resolve(fileName + ".md").toFile();
        assertTrue(createdFile.exists());

        // Cleanup
        createdFile.delete();
        postsDirectory.toFile().delete();
    }

    @Test
    void createPostFileAndReturnNameWhenPostsDirDoesNotExistTest() {
        String testTitle = "TestTitle";
        String testCategory = "TestCategory";
        String testImage = "TestImage.jpg";
        String testPath = "./nonExistentPath";

        IOException exception = assertThrows(IOException.class, () ->
                FileCreator.createPostFileAndReturnName(testTitle, testCategory, testImage, testPath));

        // Check that the exception message is as expected
        assertEquals("posts directory could not be found in " + Paths.get(testPath).toAbsolutePath(), exception.getMessage());
    }

    @Test
    void createPostFileAndReturnNameWithNoPathTest() throws IOException {
        String testTitle = "TestTitle";
        String testCategory = "TestCategory";
        String testImage = "TestImage.jpg";
        String testPath = "";

        // Create necessary directories
        Path postsDirectory = Paths.get(testPath, "_posts");
        Files.createDirectories(postsDirectory);

        // Call the method
        String fileName = FileCreator.createPostFileAndReturnName(testTitle, testCategory, testImage, testPath);

        // Assertions
        assertNotNull(fileName);

        // Verify the file exists
        File createdFile = postsDirectory.resolve(fileName + ".md").toFile();
        assertTrue(createdFile.exists());

        // Cleanup
        createdFile.delete();
        postsDirectory.toFile().delete();
    }


    @Test
    void createAssetsDirectoryIntegrationTest() {
        String testDirectoryName = "testDirectory";
        FileCreator.createAssetsDirectory(testDirectoryName, ".");

        File createdDir = new File("./assets/" + testDirectoryName);
        assertTrue(createdDir.exists() && createdDir.isDirectory());

        // Cleanup after test
        createdDir.delete();
    }



}

