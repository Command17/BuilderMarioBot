package com.github.command17.BuilderMarioBot.util;

import org.jetbrains.annotations.Nullable;
import org.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileUtil {
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    // File reading.

    @Nullable
    public static String fastReadFile(String path, boolean create) throws Exception {
        String source = null;

        try {
            File file = Paths.get(path).toFile();

            if (!file.exists()) {
                logger.error("File " + path + " does not exist!");

                if (create) {
                    logger.debug("Creating " + path + "...");

                    if (!file.createNewFile()) {
                        logger.error("Could not create " + path + "!");

                        return null;
                    }

                    logger.debug("Created " + path + ".");

                    Files.write(file.toPath(), source.getBytes());
                }
            } else {
                source = new String(Files.readAllBytes(file.toPath()));
            }
        } catch (Exception e) {
            logger.error("File not found!");
            logger.debug(Arrays.toString(e.getStackTrace()));

            throw new Exception(Arrays.toString(e.getStackTrace()));
        }

        return source;
    }

    // Json file reading.

    @Nullable
    public static JSONObject fastReadJson(String path, boolean create) throws Exception {
        JSONObject json = null;

        String source = null;

        try {
            source = fastReadFile(path, create);
        } catch (Exception ignored) {}

        if (source != null && path.endsWith(".json")) {
            try {
                json = new JSONObject(source);
            } catch (JSONException e) {
                logger.error("Json not found!");
                logger.debug(Arrays.toString(e.getStackTrace()));

                throw new Exception(Arrays.toString(e.getStackTrace()));
            }
        }

        return json;
    }
}
