package com.github.command17.BuilderMarioBot.util;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    @Nullable
    public static String fastReadFile(String path, boolean create) {
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
            logger.debug(String.valueOf(e.getStackTrace()));
        }

        return source;
    }

    @Nullable
    public static JSONObject fastReadJson(String path, boolean create) {
        JSONObject json = null;

        String source = fastReadFile(path, create);

        if (source != null && path.endsWith(".json")) {
            json = new JSONObject(source);
        }

        return json;
    }
}
