package support;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class FileSupport {
    public String getFileAsString(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        String filePath = getFilePath(fileName);

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Remove last \n from returned String
        return contentBuilder.toString().replaceAll("\\n$", "");
    }

    private String getFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

    public String getFileWithReplacementsAsString(String fileName, Map<String, String> replacementMap)
            throws IOException {
        String fileContents = getFileAsString(fileName);
        for (String key : replacementMap.keySet()) {
            fileContents = fileContents.replace(key, replacementMap.get(key));
        }

        return fileContents;
    }
}