package support;

import org.junit.Test;

import javax.naming.Name;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileSupportTest {

    private static final String TEST_DATA_FILE_SUPPORT_PATH = "TestData/FileSupportTestData/";
    private static final String SINGLE_REPLACEMENT_FOR_FILE =
            TEST_DATA_FILE_SUPPORT_PATH + "single_replacement_for_file.json";
    private static final String MULTIPLE_REPLACEMENTS_FILE =
            TEST_DATA_FILE_SUPPORT_PATH + "multiple_replacements_for_file.json";
    private static final String MULTIPLE_DIFFERENT_REPLACEMENTS_FILE =
            TEST_DATA_FILE_SUPPORT_PATH + "multiple_different_replacements_for_file.json";
    private static final String ID = "%ID%";
    private static final String REPLACEMENT_ID = "Replacement ID";
    private static final String NAME = "%NAME%";
    private static final String REPLACEMENT_NAME = "Replacement Name%";
    private static final String AGE = "%AGE%";
    private static final String REPLACEMENT_AGE = "Replacement Age";

    FileSupport fs = new FileSupport();

    @Test
    public void testGetFileAsStringWithReplacementForFile() throws IOException {
        Map<String, String> replacementMap = getReplacementMapWithSingleEntry();
        String fileWithReplacment = fs.getFileAsStringWithReplacement(SINGLE_REPLACEMENT_FOR_FILE, replacementMap);
        String expectedString = String.format("{\n" +
                                              "  \"id\": \"%s\"" +
                                              "\n}", REPLACEMENT_ID);
        assertThat(fileWithReplacment, is(expectedString));
    }

    @Test
    public void testGetFileAsStringWithReplacementForMultipleReplacementsOfSameTarget() throws IOException {
        Map<String, String> replacementMap = getReplacementMapWithSingleEntry();
        String fileWithReplacement = fs.getFileAsStringWithReplacement(MULTIPLE_REPLACEMENTS_FILE, replacementMap);
        String expectedString =
                String.format("{ \"id\": \"%s\", \"another_id\": \"%s\" }", REPLACEMENT_ID, REPLACEMENT_ID);
        assertThat(fileWithReplacement, is(expectedString));
    }

    @Test
    public void testGetFileAsStringWithReplacementForMultipleDifferentReplacements() throws IOException {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put(ID, REPLACEMENT_ID);
        replacementMap.put(NAME, REPLACEMENT_NAME);
        replacementMap.put(AGE, REPLACEMENT_AGE);

        String fileWithReplacement =
                fs.getFileAsStringWithReplacement(MULTIPLE_DIFFERENT_REPLACEMENTS_FILE , replacementMap);
        String expectedString =
                String.format("{\n" +
                              "  \"id\": \"%s\",\n" +
                              "  \"name\": \"%s\",\n" +
                              "  \"age\": \"%s\"\n" +
                              "}", REPLACEMENT_ID, REPLACEMENT_NAME, REPLACEMENT_AGE);
        assertThat(fileWithReplacement, is(expectedString));
    }

    private Map<String, String> getReplacementMapWithSingleEntry() {
        Map<String, String> replacements = new HashMap<>();
        replacements.put(ID, REPLACEMENT_ID);
        return replacements;
    }

}
