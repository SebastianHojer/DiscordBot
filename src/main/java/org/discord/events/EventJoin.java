package org.discord.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventJoin extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        System.out.println("Member joined");
        if(!event.getGuild().getId().equals("948565303393149038")){
            return;
        }
        event.getGuild().getTextChannelById("1030158462992261240").sendMessage("Velkommen til serveren " + event.getMember().getAsMention() + "!").queue();
        System.out.println("Message sent");
    }
}
