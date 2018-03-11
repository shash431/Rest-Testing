package support;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

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
    private static final String NO_REPLACEMENTS_SPECIFIED = "No replacements specified.";

    private FileSupport fs = new FileSupport();

    @Test
    public void testGetFileWithReplacementsAsStringWithReplacementForFile() throws IOException,
            NoReplacementsSpecifiedException {
        Map<String, String> replacementMap = getReplacementMapWithSingleEntry();
        String actual = fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, replacementMap);
        String expected = String.format("{\n" +
                "  \"id\": \"%s\"" +
                "\n}", REPLACEMENT_ID);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetFileWithReplacementsAsStringForMultipleReplacementsOfSameTarget() throws IOException,
            NoReplacementsSpecifiedException {
        Map<String, String> replacementMap = getReplacementMapWithSingleEntry();
        String actual = fs.getFileWithReplacementsAsString(MULTIPLE_REPLACEMENTS_FILE, replacementMap);
        String expected = String.format("{\n" +
                "  \"id\": \"%s\",\n" +
                "  \"another_id\": \"%s\"\n" +
                "}", REPLACEMENT_ID, REPLACEMENT_ID);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetFileWithReplacementsAsStringForMultipleDifferentReplacements() throws IOException,
            NoReplacementsSpecifiedException {
        Map<String, String> replacementMap = getReplacementMapWithMultipleEntries();

        String actual = fs.getFileWithReplacementsAsString(MULTIPLE_DIFFERENT_REPLACEMENTS_FILE, replacementMap);
        String expected = String.format("{\n" +
                "  \"id\": \"%s\",\n" +
                "  \"name\": \"%s\",\n" +
                "  \"age\": \"%s\"\n" +
                "}", REPLACEMENT_ID, REPLACEMENT_NAME, REPLACEMENT_AGE);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetFileWithReplacementsAsStringWhenReplacementTargetNotInFile() throws IOException,
            NoReplacementsSpecifiedException {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put(AGE, REPLACEMENT_AGE);
        String actual = fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, replacementMap);
        String expected = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", ID);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetFileWithReplacementsAsStringWhenSomeButNotAllReplacementTargetsInFile() throws IOException,
            NoReplacementsSpecifiedException {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put(AGE, REPLACEMENT_AGE);
        replacementMap.put(ID, REPLACEMENT_ID);
        String actual = fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, replacementMap);
        String expected = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", REPLACEMENT_ID);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetFileWithReplacementsAsStringWithNoReplacementsSpecified() throws IOException {
        Map<String, String> emptyReplacementMap = new HashMap<>();
        NoReplacementsSpecifiedException expException = new NoReplacementsSpecifiedException();
        NoReplacementsSpecifiedException actException = null;

        try {
            fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, emptyReplacementMap);
        } catch (NoReplacementsSpecifiedException ex) {
            actException = ex;
        }

        assertEquals(expException.getClass(), actException.getClass());
        assertThat(actException.getMessage(), is(NO_REPLACEMENTS_SPECIFIED));
    }

    @Test
    public void testGetFileWithReplacementsAsStringWithNullReplacementsSpecified() throws IOException {
        Map<String, String> nullReplacementMap = null;
        NoReplacementsSpecifiedException expException = new NoReplacementsSpecifiedException();
        NoReplacementsSpecifiedException actException = null;

        try {
            fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, nullReplacementMap);
        } catch (NoReplacementsSpecifiedException ex) {
            actException = ex;
        }

        assertEquals(expException.getClass(), actException.getClass());
        assertThat(actException.getMessage(), is(NO_REPLACEMENTS_SPECIFIED));
    }

    @Test
    public void testGetFileWithReplacementsAsStringWithNullReplacementEntrySpecified() throws IOException {
        Map<String, String> replacementMapWithNullEntry = new HashMap<>();
        replacementMapWithNullEntry.put(ID, null);

        NoReplacementsSpecifiedException expException = new NoReplacementsSpecifiedException();
        NoReplacementsSpecifiedException actException = null;

        try {
            fs.getFileWithReplacementsAsString(SINGLE_REPLACEMENT_FOR_FILE, replacementMapWithNullEntry);
        } catch (NoReplacementsSpecifiedException ex) {
            actException = ex;
        }

        assertEquals(expException.getClass(), actException.getClass());
        assertThat(actException.getMessage(), is(NO_REPLACEMENTS_SPECIFIED));
    }

    private Map<String, String> getReplacementMapWithSingleEntry() {
        Map<String, String> replacements = new HashMap<>();
        replacements.put(ID, REPLACEMENT_ID);
        return replacements;
    }

    private Map<String, String> getReplacementMapWithMultipleEntries() {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put(ID, REPLACEMENT_ID);
        replacementMap.put(NAME, REPLACEMENT_NAME);
        replacementMap.put(AGE, REPLACEMENT_AGE);
        return replacementMap;
    }

}