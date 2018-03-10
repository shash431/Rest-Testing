package support;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileSupportTest {
    private static final String REPLACEMENT_TEXT = "text_replaced!";
    private static final String REPLACE_TEXT_PATH =
            "TestData/FileSupportTestData/replacement_for_file_with_single_line.json";
    private static final String TEXT_TO_REPLACE1 = "%REPLACE%";

    FileSupport fs = new FileSupport();

    @Test
    public void testGetFileAsStringWithReplacementForFileWithASingleLine() throws IOException {
        String fileWithReplacementAsString =
                fs.getFileAsStringWithReplacement(REPLACE_TEXT_PATH, TEXT_TO_REPLACE1, REPLACEMENT_TEXT);
        String expectedString = String.format("{ \"id\": \"%s\" }", REPLACEMENT_TEXT);
        assertThat(fileWithReplacementAsString, is(expectedString));
    }
}
