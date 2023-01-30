package com.github.command17.BuilderMarioBot;

import com.github.command17.BuilderMarioBot.commands.FunctionCommands;
import com.github.command17.BuilderMarioBot.commands.InfoCommands;
import com.github.command17.BuilderMarioBot.events.MessageListener;
import com.github.command17.BuilderMarioBot.util.FileUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Bot {
    public static Logger logger = LoggerFactory.getLogger(Bot.class);

    public static JSONObject messageEventJson = null;

    public static void main(String[] args) throws Exception {
        logger.info("Reading token.txt...");

        String token = FileUtil.fastReadFile("token.txt", true);

        try {
            messageEventJson = FileUtil.fastReadJson("message_event.json", false);
        } catch (Exception e) {
            logger.info("No message_event.json found! Continuing  without...");
            logger.debug("Stack trace:");
            logger.debug(Arrays.toString(e.getStackTrace()));
        }

        if (token != null && !token.isEmpty()) {
            logger.info("Done!");

            JDA jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT).setActivity(Activity.playing("m!help")).build();

            logger.info("Registering Commands...");

            jda.addEventListener(new InfoCommands());
            jda.addEventListener(new FunctionCommands());

            logger.info("Done!");
            logger.info("Registering Events...");

            jda.addEventListener(new MessageListener());

            logger.info("Done!");
        } else {
            logger.error("No token found!");
        }
    }
}