package support;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonSupportTest {

    private String json1 = new FileSupport().getFileAsString("testData/testJson1.json");
    private String json2 = new FileSupport().getFileAsString("testData/testJson2.json");
    private String json3 = new FileSupport().getFileAsString("testData/testJson3.json");
    private JsonSupport jsonSupport = new JsonSupport();

    @Test
    public void testGetJsonElementFromPathForSingleAttribute() {
        String path1 = "colour";
        JsonElement colour = jsonSupport.getJsonElementFromPath(json1, path1);
        assertTrue(colour.isJsonPrimitive());
        assertEquals("pink", colour.getAsString());

        String path2 = "year";
        JsonElement year = jsonSupport.getJsonElementFromPath(json1, path2);
        assertTrue(year.isJsonPrimitive());
        assertEquals(2017, year.getAsInt());
        assertEquals("2017", year.getAsString());

        String path3 = "isVisible";
        JsonElement isVisible = jsonSupport.getJsonElementFromPath(json1, path3);
        assertTrue(isVisible.isJsonPrimitive());
        assertEquals(true, isVisible.getAsBoolean());
        assertEquals("true", isVisible.getAsString());

        String path4 = "isEnabled";
        JsonElement isEnabled = jsonSupport.getJsonElementFromPath(json1, path4);
        assertTrue(isEnabled.isJsonPrimitive());
        assertEquals(false, isEnabled.getAsBoolean());
        assertEquals("false", isEnabled.getAsString());
    }

    @Test
    public void testGetJsonElementFromPathForNestedObject() {
        String objPath = "colours";
        String path1 = "colours/colour1";
        String path2 = "colours/colour2";
        String path3 = "colours/colour3";

        JsonElement colours = jsonSupport.getJsonElementFromPath(json1, objPath);
        assertTrue(colours.isJsonObject());

        JsonElement coloursColour1 = jsonSupport.getJsonElementFromPath(json1, path1);
        assertTrue(coloursColour1.isJsonPrimitive());
        assertEquals("red", coloursColour1.getAsString());

        JsonElement coloursColour2 = jsonSupport.getJsonElementFromPath(json1, path2);
        assertTrue(coloursColour2.isJsonPrimitive());
        assertEquals("blue", coloursColour2.getAsString());

        JsonElement coloursColour3 = jsonSupport.getJsonElementFromPath(json1, path3);
        assertTrue(coloursColour3.isJsonPrimitive());
        assertEquals("yellow", coloursColour3.getAsString());
    }

    @Test
    public void testGetJsonElementFromPathForArray() {
        String path = "colourArray";
        JsonElement colourArray = jsonSupport.getJsonElementFromPath(json1, path);
        assertTrue(colourArray.isJsonArray());
        JsonArray expArray = new JsonArray();
        expArray.add("purple");
        expArray.add("orange");
        expArray.add("green");

        assertEquals(expArray, colourArray);
    }

    @Test
    public void testGetJsonElementFromPathForArrayElements() {
        String pathWithIndex0 = "colourArray/[0]/";
        JsonElement colour0 = jsonSupport.getJsonElementFromPath(json1, pathWithIndex0);
        assertEquals("purple", colour0.getAsString());

        String pathWIthIndex1 = "colourArray/[1]/";
        JsonElement colour1 = jsonSupport.getJsonElementFromPath(json1, pathWIthIndex1);
        assertEquals("orange", colour1.getAsString());

        String pathWithIndex2 = "colourArray/[2]/";
        JsonElement colour2 = jsonSupport.getJsonElementFromPath(json1, pathWithIndex2);
        assertEquals("green", colour2.getAsString());

        String pathWithIndex3 = "colourArray/[3]/";
        assertGetJsonElementFromPathThrowsException(json1, pathWithIndex3, new IndexOutOfBoundsException());
    }

    @Test
    public void testGetJsonElementFromPathForJsonObjectsWithinJsonArray() {
        String path1 = "countries/[0]/country";
        JsonElement country = jsonSupport.getJsonElementFromPath(json1, path1);
        assertEquals("france", country.getAsString());
    }

    @Test
    public void assetGetJsonElementFromPathForJsonPrimitiveInHighlyNestedJson() {
        String path1 = "topping/[1]/id";
        JsonElement id1 = jsonSupport.getJsonElementFromPath(json2, path1);
        assertEquals("5002", id1.getAsString());

        String path2 = "items/item/[0]/topping/[1]/id";
        JsonElement id2 = jsonSupport.getJsonElementFromPath(json3, path2);
        assertEquals("5002", id2.getAsString());

        String path3 = "items/item/[0]/batters/batter/[1]/type";
        JsonElement type = jsonSupport.getJsonElementFromPath(json3, path3);
        assertEquals("Chocolate", type.getAsString());
    }

    @Test
    public void assetGetJsonElementFromPathForJsonArrayInHighlyNestedJson() {
        String path3 = "items/item/[0]/topping";
        JsonElement toppingArray = jsonSupport.getJsonElementFromPath(json3, path3);
        assertTrue(toppingArray.isJsonArray());

        JsonArray expArray = new JsonArray();
        JsonObject expObj1 = new JsonObject();
        expObj1.addProperty("id", "5001");
        expObj1.addProperty("type", "None");

        JsonObject expObj2 = new JsonObject();
        expObj2.addProperty("id", "5002");
        expObj2.addProperty("type", "Glazed");

        expArray.add(expObj1);
        expArray.add(expObj2);

        assertEquals(expArray, toppingArray);
    }

    @Test
    public void assertGetJsonElementFromPathForInvalidJsonThrowsInvalidJsonSyntaxException() {
        String json = "{ \"invalid\"";
        String path = "invalid";
        assertGetJsonElementFromPathThrowsException(json, path, new JsonSyntaxException(""));
    }

    @Test
    public void testGetJsonElementFromPathForEmptyPath() {
        String emptyPath = "";
        assertGetJsonElementFromPathThrowsException(json1, emptyPath, new NullPointerException());
    }

    @Test
    public void testGetJsonElementFromPathForNonExistentPath() {
        String nonExistentPath = "doesntexist";
        assertGetJsonElementFromPathThrowsException(json1, nonExistentPath, new NullPointerException());
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