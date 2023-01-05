package org.discord.interfaces;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.List;


public class ExecuteArgs {
    private final TextChannel textChannel;
    private final Member selfMember;
    private final Member member;
    public static Guild guild;
    private final JDA jda;
    private String message = "";
    private final String[] args;
    private final GuildVoiceState selfVoiceState;
    private final GuildVoiceState memberVoiceState;
    private final SlashCommandInteractionEvent event;

    protected ExecuteArgs(SlashCommandInteractionEvent event) {
        this.textChannel = event.getTextChannel();
        this.member = event.getMember();
        this.guild = event.getGuild();
        this.jda = event.getJDA();
        this.event = event;

        List<OptionMapping> map = event.getOptionsByName("link:");
        if(map.size()>0){
            message=map.get(0).getAsString();
        }
        this.selfMember = this.guild.getSelfMember();
        this.args = this.message.split(" ");
        this.selfVoiceState = this.selfMember.getVoiceState();
        this.memberVoiceState = this.member.getVoiceState();
    }

    public TextChannel getTextChannel() {
        return this.textChannel;
    }

    public Member getSelfMember() {
        return this.selfMember;
    }

    public Member getMember() {
        return this.member;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public JDA getJda() {
        return this.jda;
    }

    public String getMessage() {
        return this.message;
    }

    public String[] getArgs() {
        return this.args;
    }

    public GuildVoiceState getSelfVoiceState() {
        return this.selfVoiceState;
    }

    public GuildVoiceState getMemberVoiceState() {
        return this.memberVoiceState;
    }

    public SlashCommandInteractionEvent getEvent(){
        return event;
    }
}