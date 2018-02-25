package support;

import com.google.gson.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonSupport {

    private static final String CAPTURE_INDEX_REGEX = "^\\[(\\d+)\\]$";

    public JsonElement getJsonElementFromPath(String json, String path) throws JsonSyntaxException {
        JsonObject obj = new GsonBuilder().create().fromJson(json, JsonObject.class);
        String[] pathSegments = getPathAsSegments(path);

        for (int index = 0; index < pathSegments.length; index++) {
            if (obj != null) {
                JsonElement element = obj.get(pathSegments[index]);
                if (!element.isJsonObject()) {
                    if (element.isJsonArray()) {
                        return getJsonElementFromJsonArray(element.getAsJsonArray(), path, index);
                    }
                    return element;
                } else {
                    obj = element.getAsJsonObject();
                }
            } else {
                return null;
            }
        }

        return obj;
    }

    private JsonElement getJsonElementFromJsonArray(JsonElement element, String path, int currentIndex) {
        JsonArray jsonArray = element.getAsJsonArray();
        String[] pathSegments = getPathAsSegments(path);
        // JsonArray will have a path with an index e.g. colourArray/[0]/
        // or no index e.g. colourArray
        if (pathSegments.length > 1) {
            return getJsonElementFromJsonArrayUsingIndexFromPath(jsonArray, path, currentIndex);
        } else {
            return jsonArray;
        }
    }

    private String[] getPathAsSegments(String path) {
        return path.split("/");
    }

    private JsonElement getJsonElementFromJsonArrayUsingIndexFromPath(JsonArray jsonArray, String path,
                                                                      int currentIndex) {
        String pathUpToArrayIndex = getPathEndingWithArrayIndex(path, currentIndex);
        String[] pathSegments = getPathAsSegments(path);
        int arrayIndex = getArrayIndexFromSegments(pathSegments[currentIndex + 1]);
        JsonElement arrayElement = jsonArray.get(arrayIndex);

        if (pathUpToArrayIndex.isEmpty()) {
            return arrayElement;
        }

        return getJsonElementFromRemainingPath(arrayElement, path, pathUpToArrayIndex);
    }

    private String getPathEndingWithArrayIndex(String path, int currentIndex) {
        String[] pathSegments = getPathAsSegments(path);
        StringBuilder pathEndingWithArrayIndex = new StringBuilder();
        for (int i = 0; i <= currentIndex + 1; i++) {
            pathEndingWithArrayIndex.append(pathSegments[i]);
            pathEndingWithArrayIndex.append("/");
        }

        return pathEndingWithArrayIndex.toString();
    }

    private JsonElement getJsonElementFromRemainingPath(JsonElement arrayElement, String path,
                                                        String pathUpToArrayIndex) {
        String arrayPath = path.replace(pathUpToArrayIndex, "");
        if (arrayElement.isJsonPrimitive()) {
            return arrayElement;
        }

        return getJsonElementFromPath(arrayElement.toString(), arrayPath);
    }

    private int getArrayIndexFromSegments(String seg) {
        Pattern pattern = Pattern.compile(CAPTURE_INDEX_REGEX);
        Matcher matcher = pattern.matcher(seg);
        matcher.find();

        return Integer.parseInt(matcher.group(1));
    }
}

