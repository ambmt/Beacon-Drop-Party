package com.ambmt.dropParty;

import org.bukkit.block.*;
import org.bukkit.Location;
import org.bukkit.block.Beacon;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BeaconDropParty extends JavaPlugin {
    private PartyBeacon partyBeacon;
    private Countdown countdown;
    private PartyDropper partyDropper;
    private Object[] messages;

    public BeaconDropParty() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        this.reload();
        this.getCommand("bdp").setExecutor(new CommandManager(this));
        this.getLogger().info("Ready for the DropParty!");
    }

    public void reload() {
        this.reloadConfig();
        this.messages = this.getConfig().getList("Messages").toArray();
        this.partyBeacon = new PartyBeacon(((Location)this.getConfig().get("PartyBlockLocation")).getBlock(), this.getConfig().getInt("DropRate"), this.getConfig().getConfigurationSection("DropItems").getValues(false), this.getConfig().getConfigurationSection("Amounts").getValues(false));
    }

    public boolean forcestart() {
        if (this.partyBeacon != null && this.partyBeacon.isBeacon()) {
            this.stop();
            this.partyDropper = new PartyDropper(this.partyBeacon);
            return true;
        } else {
            return false;
        }
    }

    public boolean startEvent(int i) {
        if (this.partyBeacon != null && this.partyBeacon.isBeacon()) {
            this.stop();
            this.countdown = new Countdown(i, this.messages);
            return true;
        } else {
            return false;
        }
    }

    public void stop() {
        if (this.countdown != null) {
            this.countdown.stopCountdown();
            this.countdown = null;
        }

        if (this.partyDropper != null) {
            this.partyDropper.stopDropper();
            this.partyDropper = null;
        }

    }

    public void add(ItemStack item, String name, int amount) {
        if (this.partyBeacon != null) {
            this.partyBeacon.addItem(item, name, amount);
            this.getConfig().createSection("DropItems", this.partyBeacon.getItems());
            this.getConfig().createSection("Amounts", this.partyBeacon.getAmount());
            this.saveConfig();
        }

    }

    public boolean setBlock(Block b) {
        if (b.getState() instanceof Beacon) {
            this.partyBeacon.setPartyBlock(b);
            this.getConfig().set("PartyBlockLocation", b.getLocation());
            this.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    public void setDropRate(int i) {
        this.partyBeacon.setDropRate(i);
        this.getConfig().set("DropRate", 5);
        this.saveConfig();
    }

    public void onDisable() {
        this.getLogger().info("Party Beacon Disabling");
    }
}
