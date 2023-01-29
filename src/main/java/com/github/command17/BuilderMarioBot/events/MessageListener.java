package com.github.command17.BuilderMarioBot.events;

import com.github.command17.BuilderMarioBot.util.FileUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            JSONObject json = FileUtil.fastReadJson("message_event.json", false);

            Message message = event.getMessage();

            MessageChannel channel = event.getChannel();

            if (json != null) {
                if (json.getJSONObject(channel.getId()) != null) {
                    JSONObject channelJson = json.getJSONObject(channel.getId());

                    if (channelJson.getString("type").equals("reaction")) {
                        if (channelJson.getJSONArray("reactions") != null) {
                            JSONArray reactions = channelJson.getJSONArray("reactions");

                            for (int i = 0; i < reactions.length(); i++) {
                                if (reactions.getString(i) != null) {
                                    message.addReaction(Emoji.fromFormatted(reactions.getString(i))).queue();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
