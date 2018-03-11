package support;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileSupportTest {
    private static final String REPLACEMENT_TEXT = "text_replaced!";
    private static final String TEST_DATA_FILE_SUPPORT_PATH = "TestData/FileSupportTestData/";
    private static final String MULTIPLE_REPLACEMENTS_FILE =
            TEST_DATA_FILE_SUPPORT_PATH + "multiple_replacements_for_file.json";
    private static final String TEXT_TO_REPLACE1 = "%REPLACE%";
    private static final String SINGLE_REPLACEMENT_FOR_FILE =
            TEST_DATA_FILE_SUPPORT_PATH + "single_replacement_for_file.json";

    FileSupport fs = new FileSupport();

    @Test
    public void testGetFileAsStringWithReplacementForFile() throws IOException {
        Map replacementMap = getReplacementMapWithSingleEntry();
        String fileWithReplacment = fs.getFileAsStringWithReplacement(SINGLE_REPLACEMENT_FOR_FILE, replacementMap);
        String expectedString = String.format("{\n  \"id\": \"%s\"\n}", REPLACEMENT_TEXT);
        assertThat(fileWithReplacment, is(expectedString));
    }

    @Test
    public void testGetFileAsStringWithReplacementForMultipleReplacements() throws IOException {
        Map replacementMap = getReplacementMapWithSingleEntry();
        String fileWithReplacement = fs.getFileAsStringWithReplacement(MULTIPLE_REPLACEMENTS_FILE, replacementMap);
        String expectedString =
                String.format("{ \"id\": \"%s\", \"another_id\": \"%s\" }", REPLACEMENT_TEXT, REPLACEMENT_TEXT);
        assertThat(fileWithReplacement, is(expectedString));
    }

    @Test
    public void testGetFileAsStringWithReplacementForMultipleReplacementsInAFileWithMultipleLines() throws IOException {
        Map replacementMap = getReplacementMapWithSingleEntry();
        String fileWithReplacement = fs.getFileAsStringWithReplacement(MULTIPLE_REPLACEMENTS_FILE, replacementMap);
        String expectedString =
                String.format("{ \"id\": \"%s\", \"another_id\": \"%s\" }", REPLACEMENT_TEXT, REPLACEMENT_TEXT);
        assertThat(fileWithReplacement, is(expectedString));
    }

    private Map<String, String> getReplacementMapWithSingleEntry() {
        Map<String, String> replacements = new HashMap<>();
        replacements.put(TEXT_TO_REPLACE1, REPLACEMENT_TEXT);
        return replacements;
    }

}
