import org.example.Arguments;
import org.example.DataGetter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataGetterTest {

    @Test
    void fromCMDTest() {
        String[] args = {"--title", "TestTitle", "--category", "TestCategory"};

        var result = DataGetter.fromCMD(args);

        assertEquals("TestTitle", result.get(Arguments.TITLE));
        assertEquals("TestCategory", result.get(Arguments.CATEGORY));
        assertNull(result.get(Arguments.IMAGE));
    }

    // Additional tests can be written for different argument combinations
}

