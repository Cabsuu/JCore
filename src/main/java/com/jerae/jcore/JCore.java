package com.jerae.jcore;

import com.jerae.jcore.commands.ChatColorCommand;
import com.jerae.jcore.listeners.ChatListener;
import com.jerae.jcore.listeners.GUIListener;
import com.jerae.jcore.managers.ConversationManager;
import com.jerae.jcore.managers.PlayerColorManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JCore extends JavaPlugin {

    private PlayerColorManager playerColorManager;
    private ConversationManager conversationManager;

    @Override
    public void onEnable() {
        this.playerColorManager = new PlayerColorManager(this);
        this.conversationManager = new ConversationManager();

        getLogger().info("JCore has been enabled!");
        this.getCommand("chatcolor").setExecutor(new ChatColorCommand(this));
        this.getCommand("nick").setExecutor(new com.jerae.jcore.commands.NickCommand());
        this.getCommand("rename").setExecutor(new com.jerae.jcore.commands.RenameCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new GUIListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("JCore has been disabled!");
    }

    public PlayerColorManager getPlayerColorManager() {
        return playerColorManager;
    }

    public ConversationManager getConversationManager() {
        return conversationManager;
    }
}