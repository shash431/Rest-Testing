package support;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonSupportTest {

    private static final String NON_EXISTENT_PATH = "doesntexist";
    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String YELLOW = "yellow";
    private static final String PURPLE = "purple";
    private static final String ORANGE = "orange";
    private static final String GREEN = "green";
    private static final String COLOUR_PATH = "colour";
    private static final String PINK = "pink";
    private static final String YEAR_PATH = "year";
    private static final String TWENTY_SEVENTEEN_STRING = "2017";
    private static final String IS_VISIBLE_PATH = "isVisible";
    private static final String TRUE = "true";
    private static final String IS_ENABLED_PATH = "isEnabled";
    private static final int TWENTY_SEVENTEEN_INT = 2017;
    private static final String FALSE = "false";
    private static final String INVALID_PATH = "invalid";
    private static final String INVALID_JSON = "{ \"invalid\"";
    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String FIVE_THOUSAND_AND_ONE = "5001";
    private static final String FIVE_THOUSAND_AND_TWO = "5002";
    private static final String NONE = "None";
    private static final String GLAZED = "Glazed";
    private static final String TOPPING_PATH = "items/item/[0]/topping";
    private static final String COLOURS_PATH = "colours";
    private static final String COLOUR1_PATH = "colours/colour1";
    private static final String COLOUR2_PATH = "colours/colour2";
    private static final String COLOUR3_PATH = "colours/colour3";
    private static final String COLOUR_ARRAY_PATH = "colourArray";
    private static final String COLOUR_ARRAY_INDEX_0_PATH = "colourArray/[0]/";
    private static final String COLOUR_ARRAY_INDEX_1_PATH = "colourArray/[1]/";
    private static final String COLOUR_ARRAY_INDEX_2_PATH = "colourArray/[2]/";
    private static final String COLOUR_ARRAY_INDEX_3 = "colourArray/[3]/";
    private static final String COUNTRY_PATH = "countries/[0]/country";
    private static final String FRANCE = "france";
    private static final String TOPPING_1_ID_PATH = "topping/[1]/id";
    private static final String CHOCOLATE = "Chocolate";
    private static final String ITEMS_ITEM_0_TOPPING_1_ID_PATH = "items/item/[0]/topping/[1]/id";
    private static final String ITEMS_ITEM_0_BATTERS_BATTER_1_TYPE_PATH = "items/item/[0]/batters/batter/[1]/type";
    private String json1 = new FileSupport().getFileAsString("TestData/JsonSupportTestData/test_json1.json");
    private String json2 = new FileSupport().getFileAsString("TestData/JsonSupportTestData/test_json2.json");
    private String json3 = new FileSupport().getFileAsString("TestData/JsonSupportTestData/test_json3.json");
    private JsonSupport jsonSupport = new JsonSupport();

    @Test
    public void testGetJsonElementFromPathForSingleAttribute() {
        JsonElement colour = jsonSupport.getJsonElementFromPath(json1, COLOUR_PATH);
        assertTrue(colour.isJsonPrimitive());
        assertEquals(PINK, colour.getAsString());

        JsonElement year = jsonSupport.getJsonElementFromPath(json1, YEAR_PATH);
        assertTrue(year.isJsonPrimitive());
        assertEquals(TWENTY_SEVENTEEN_INT, year.getAsInt());
        assertEquals(TWENTY_SEVENTEEN_STRING, year.getAsString());

        JsonElement isVisible = jsonSupport.getJsonElementFromPath(json1, IS_VISIBLE_PATH);
        assertTrue(isVisible.isJsonPrimitive());
        assertEquals(true, isVisible.getAsBoolean());
        assertEquals(TRUE, isVisible.getAsString());

        JsonElement isEnabled = jsonSupport.getJsonElementFromPath(json1, IS_ENABLED_PATH);
        assertTrue(isEnabled.isJsonPrimitive());
        assertEquals(false, isEnabled.getAsBoolean());
        assertEquals(FALSE, isEnabled.getAsString());
    }

    @Test
    public void testGetJsonElementFromPathForNestedObject() {
        JsonElement colours = jsonSupport.getJsonElementFromPath(json1, COLOURS_PATH);
        assertTrue(colours.isJsonObject());

        JsonElement coloursColour1 = jsonSupport.getJsonElementFromPath(json1, COLOUR1_PATH);
        assertTrue(coloursColour1.isJsonPrimitive());
        assertEquals(RED, coloursColour1.getAsString());

        JsonElement coloursColour2 = jsonSupport.getJsonElementFromPath(json1, COLOUR2_PATH);
        assertTrue(coloursColour2.isJsonPrimitive());
        assertEquals(BLUE, coloursColour2.getAsString());

        JsonElement coloursColour3 = jsonSupport.getJsonElementFromPath(json1, COLOUR3_PATH);
        assertTrue(coloursColour3.isJsonPrimitive());
        assertEquals(YELLOW, coloursColour3.getAsString());
    }

    @Test
    public void testGetJsonElementFromPathForArray() {
        JsonElement colourArray = jsonSupport.getJsonElementFromPath(json1, COLOUR_ARRAY_PATH);
        assertTrue(colourArray.isJsonArray());
        JsonArray expArray = new JsonArray();
        expArray.add(PURPLE);
        expArray.add(ORANGE);
        expArray.add(GREEN);

        assertEquals(expArray, colourArray);
    }

    @Test
    public void testGetJsonElementFromPathForArrayElements() {
        JsonElement colour0 = jsonSupport.getJsonElementFromPath(json1, COLOUR_ARRAY_INDEX_0_PATH);
        assertEquals(PURPLE, colour0.getAsString());

        JsonElement colour1 = jsonSupport.getJsonElementFromPath(json1, COLOUR_ARRAY_INDEX_1_PATH);
        assertEquals(ORANGE, colour1.getAsString());

        JsonElement colour2 = jsonSupport.getJsonElementFromPath(json1, COLOUR_ARRAY_INDEX_2_PATH);
        assertEquals(GREEN, colour2.getAsString());

        assertGetJsonElementFromPathThrowsException(json1, COLOUR_ARRAY_INDEX_3, new IndexOutOfBoundsException());
    }

    @Test
    public void testGetJsonElementFromPathForJsonObjectsWithinJsonArray() {
        JsonElement country = jsonSupport.getJsonElementFromPath(json1, COUNTRY_PATH);
        assertEquals(FRANCE, country.getAsString());
    }

    @Test
    public void assetGetJsonElementFromPathForJsonPrimitiveInHighlyNestedJson() {
        JsonElement id1 = jsonSupport.getJsonElementFromPath(json2, TOPPING_1_ID_PATH);
        assertEquals(FIVE_THOUSAND_AND_TWO, id1.getAsString());

        JsonElement id2 = jsonSupport.getJsonElementFromPath(json3, ITEMS_ITEM_0_TOPPING_1_ID_PATH);
        assertEquals(FIVE_THOUSAND_AND_TWO, id2.getAsString());

        JsonElement type = jsonSupport.getJsonElementFromPath(json3, ITEMS_ITEM_0_BATTERS_BATTER_1_TYPE_PATH);
        assertEquals(CHOCOLATE, type.getAsString());
    }

    @Test
    public void assetGetJsonElementFromPathForJsonArrayInHighlyNestedJson() {
        JsonElement toppingArray = jsonSupport.getJsonElementFromPath(json3, TOPPING_PATH);
        assertTrue(toppingArray.isJsonArray());

        JsonArray expArray = new JsonArray();
        JsonObject expObj1 = new JsonObject();
        expObj1.addProperty(ID, FIVE_THOUSAND_AND_ONE);
        expObj1.addProperty(TYPE, NONE);

        JsonObject expObj2 = new JsonObject();
        expObj2.addProperty(ID, FIVE_THOUSAND_AND_TWO);
        expObj2.addProperty(TYPE, GLAZED);

        expArray.add(expObj1);
        expArray.add(expObj2);

        assertEquals(expArray, toppingArray);
    }

    @Test
    public void assertGetJsonElementFromPathForInvalidJsonThrowsInvalidJsonSyntaxException() {
        assertGetJsonElementFromPathThrowsException(INVALID_JSON, INVALID_PATH, new JsonSyntaxException(""));
    }

    @Test
    public void testGetJsonElementFromPathForEmptyPath() {
        String emptyPath = "";
        assertGetJsonElementFromPathThrowsException(json1, emptyPath, new NullPointerException());
    }

    @Test
    public void testGetJsonElementFromPathForNonExistentPath() {
        assertGetJsonElementFromPathThrowsException(json1, NON_EXISTENT_PATH, new NullPointerException());
    }

    private void assertGetJsonElementFromPathThrowsException(String json, String path, Exception expException) {
        Exception actException = null;

        try {
            jsonSupport.getJsonElementFromPath(json, path);
        } catch (Exception e) {
            actException = e;
        }

        assertEquals(expException.getClass(), actException.getClass());
    }

}