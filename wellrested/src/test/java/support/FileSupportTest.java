package support;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileSupportTest {
    private static final String REPLACEMENT_TEXT = "text_replaced!";
    private static final String FILE_WITH_SINGLE_LINE_PATH =
            "TestData/FileSupportTestData/replacement_for_file_with_single_line.json";
    private static final String TEXT_TO_REPLACE1 = "%REPLACE%";
    private static final String FILE_WITH_MULTIPLE_LINES_PATH =
            "TestData/FileSupportTestData/replacement_for_file_with_multiple_lines.json";

    FileSupport fs = new FileSupport();

    @Test
    public void testGetFileAsStringWithReplacementForFileWithASingleLine() throws IOException {
        String fileWithReplacementAsString =
                fs.getFileAsStringWithReplacement(FILE_WITH_SINGLE_LINE_PATH, TEXT_TO_REPLACE1, REPLACEMENT_TEXT);
        String expectedString = String.format("{ \"id\": \"%s\" }", REPLACEMENT_TEXT);
        assertThat(fileWithReplacementAsString, is(expectedString));
    }

    @Test
    public void testGetFileAsStringWithReplacementForFileWithMultipleLines() throws IOException {
        String fileWithReplacementAsString =
                fs.getFileAsStringWithReplacement(FILE_WITH_MULTIPLE_LINES_PATH, TEXT_TO_REPLACE1, REPLACEMENT_TEXT);
        String expectedString = String.format("{\n  \"id\": \"%s\"\n}", REPLACEMENT_TEXT);
        assertThat(fileWithReplacementAsString, is(expectedString));
    }
}
