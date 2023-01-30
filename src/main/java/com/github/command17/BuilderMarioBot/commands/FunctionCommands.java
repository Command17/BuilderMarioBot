package com.github.command17.BuilderMarioBot.commands;

import com.github.command17.BuilderMarioBot.util.FileUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class FunctionCommands extends ListenerAdapter {
    public static Logger logger = LoggerFactory.getLogger(FunctionCommands.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            Message message = event.getMessage();
            String content = message.getContentRaw();

            MessageChannel channel = event.getChannel();

            String msgContent[] = content.split(" ");

            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (msgContent[0].equals("m!send")) {
                    String path = msgContent[1];

                    if (path != null) {
                        JSONObject json = null;

                        try {
                            json = FileUtil.fastReadJson(path, false);
                        } catch (Exception e) {
                            logger.error(path + "not found!");
                            logger.debug(Arrays.toString(e.getStackTrace()));
                        }

                        if (json != null && !json.isEmpty()) {
                            if (json.getString("type") != null) {
                                String type = json.getString("type");

                                if (type.equals("embed")) {
                                    EmbedBuilder embed = new EmbedBuilder();
                                    embed.setColor(0xff9e00);

                                    if (json.getString("title") != null) {
                                        embed.setTitle(json.getString("title"));
                                    }

                                    if (json.getString("description") != null) {
                                        embed.setDescription(json.getString("description"));
                                    }

                                    channel.sendMessageEmbeds(embed.build()).queue();
                                } else if (type.equals("msg")) {
                                    if (json.getString("msg") != null) {
                                        channel.sendMessage(json.getString("msg")).queue();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}