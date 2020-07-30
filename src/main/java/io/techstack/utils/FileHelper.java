package io.techstack.utils;

import java.io.File;

public class FileHelper {
    private static final String SCREENSHOT_FOLDER = PropertyReader.getProperty("screenshots.folder");

    public static File createUniqueScreenshotName(String stringToInclude) {
        String source = String.format("%s/%s_%s.png", SCREENSHOT_FOLDER, System.currentTimeMillis(), stringToInclude);
        return new File(source);
    }
}
