package com.github.command17.BuilderMarioBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InfoCommands extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            Message message = event.getMessage();
            String content = message.getContentRaw();

            MessageChannel channel = event.getChannel();

            if (content.equals("m!bm")) {
                channel.sendMessage("Yes. I'm Builder Mario!").queue();
            } else if (content.equals("m!bm-bot")) {
                channel.sendMessage("It's true... I'm a bot").queue();
            } else if (content.equals("m!help")) {
                String adminHelp = null;

                if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) adminHelp = "\n **m!send <path>** - send a json file.";

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Builder Mario help me!");
                embed.setDescription("**-- Builder Mario --** \n **m!bm** | **m!bm-bot** - I am... \n **m!help** - show this." + adminHelp);
                embed.setColor(0xff9e00);

                channel.sendMessageEmbeds(embed.build()).queue();
            }
        }
    }
}
