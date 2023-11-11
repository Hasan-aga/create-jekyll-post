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
import static org.mockito.Mockito.*;

class FileCreatorTest {

    @Test
    void createPostFileAndReturnNameTest() throws IOException {
        // Mock the static methods in Files
        Mockito.mockStatic(Files.class);

        // Assume the posts directory exists
        when(Files.exists(any(Path.class))).thenReturn(true);

        // Call the method to test
        String fileName = FileCreator.createPostFileAndReturnName("TestTitle", "TestCategory", "TestImage.jpg", ".");

        // Assertions and verifications
        assertNotNull(fileName);
        // Add more assertions and verifications as needed
    }

    @Test
    void createPostFileAndReturnNameWhenPostsDirDoesNotExistTest() {
        // Mock the static methods in Files
        Mockito.mockStatic(Files.class);

        // Assume the posts directory does not exist
        when(Files.exists(any(Path.class))).thenReturn(false);

        Path expectedPath = Paths.get(".").toAbsolutePath();

        // Call the method and expect an IOException
        IOException exception = assertThrows(IOException.class, () -> {
            FileCreator.createPostFileAndReturnName("TestTitle", "TestCategory", "TestImage.jpg", ".");
        });

        // Check that the exception message is as expected
        assertEquals("posts directory could not be found in " + expectedPath, exception.getMessage());
    }

    @Test
    void createPostFileAndReturnNameWithNoPathTest() throws IOException {
        // Mock the static methods in Files
        Mockito.mockStatic(Files.class);

        // Assume the posts directory exists
        when(Files.exists(any(Path.class))).thenReturn(true);

        // Call the method without passing a path
        String fileName = FileCreator.createPostFileAndReturnName("TestTitle", "TestCategory", "TestImage.jpg", "");

        // Assertions
        assertNotNull(fileName);
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

