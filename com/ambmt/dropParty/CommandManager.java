package com.ambmt.dropParty;

import org.bukkit.inventory.meta.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class CommandManager implements CommandExecutor {
    private BeaconDropParty plugin;

    public CommandManager(BeaconDropParty plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (command.getName().equalsIgnoreCase("bdp")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") && p.hasPermission("beacondropparty.reload")) {
                    this.plugin.reload();
                    p.sendMessage("§6[BeaconDropParty]§7 Config reloaded!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("forcestart") && p.hasPermission("beacondropparty.forcestart")) {
                    if (!this.plugin.forcestart()) {
                        p.sendMessage("§6[BeaconDropParty]§7 No PartyBlock set or Partyblock is destroyed!");
                    }

                    return true;
                }

                if (args[0].equalsIgnoreCase("stop") && p.hasPermission("beacondropparty.stop")) {
                    this.plugin.stop();
                    p.sendMessage("§6[BeaconDropParty]§7 Event stopped!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("setblock") && p.hasPermission("beacondropparty.setblock")) {
                    Block b = p.getTargetBlock((Set)null, 200);
                    this.plugin.setBlock(b);
                    p.sendMessage("§6[BeaconDropParty]§7 new PartyBlock location: (X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ() + ")");
                    return true;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("setdroprate") && p.hasPermission("beacondropparty.setdroprate")) {
                    try {
                        int i = Integer.parseInt(args[1]);
                        this.plugin.setDropRate(i);
                        p.sendMessage("§6[BeaconDropParty]§7 DropRate set to: " + i);
                    } catch (NumberFormatException var7) {
                        p.sendMessage("§6[BeaconDropParty]§7 To set the Droprate use a number!");
                    }

                    return true;
                }

                if (args[0].equalsIgnoreCase("startevent") && p.hasPermission("beacondropparty.startevent")) {
                    try {
                        if (!this.plugin.startEvent(Integer.parseInt(args[1]))) {
                            p.sendMessage("§6[BeaconDropParty]§7 No PartyBlock set or Partyblock is destroyed!");
                        }
                    } catch (NumberFormatException var8) {
                        p.sendMessage("§6[BeaconDropParty]§7 This is not a correct number to start the Event");
                    }

                    return true;
                }
            } else if (args.length == 3 && args[0].equalsIgnoreCase("add") && sender instanceof Player && p.hasPermission("beacondropparty.add")) {
                try {
                    this.plugin.add(p.getInventory().getItemInHand(), args[1], Integer.parseInt(args[2]));
                    p.sendMessage("§6[BeaconDropParty]§7 New Item " + args[1] + " added " + Integer.parseInt(args[2]) + " times: " + p.getInventory().getItemInHand().toString());
                } catch (NumberFormatException var9) {
                    p.sendMessage("§6[BeaconDropParty]§7 This is not a correct amount!");
                }

                return true;
            }
        }

        this.help(p);
        return true;
    }

    private void help(Player p) {
        p.sendMessage("§6[BeaconDropParty]§7 /bdp setblock: Sets the block you are looking at as a new PartyBeacon");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp reload: Reload the Config");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp add <NAME> <AMOUNT>: Add a new Item to the DropList");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp stop: Stops the Event");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp startevent <TIME>: Start a new Event");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp forcestart: Forcestart the Event");
        p.sendMessage("§6[BeaconDropParty]§7 /bdp setdroprate <DROPRATE>: Set a new Droprate");
    }
}

