package com.ambmt.dropParty;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class PartyBeacon {
    Block b;
    int dropRate;
    Map<String, Object> dropItems;
    Map<String, Object> amount;

    public PartyBeacon(Block b, int dropRate, Map<String, Object> dropItems, Map<String, Object> amount) {
        this.b = b;
        this.dropRate = dropRate;
        this.dropItems = dropItems;
        this.amount = amount;
    }

    public Block getPartyBeacon() {
        return this.b;
    }

    public void setPartyBlock(Block b) {
        this.b = b;
    }

    public void reload(Map<String, Object> dropItems, Map<String, Object> amount, int dropRate) {
        this.dropItems = dropItems;
        this.amount = amount;
        this.dropRate = dropRate;
    }

    public void addItem(ItemStack item, String name, int amount) {
        this.dropItems.put(name, item);
        this.amount.put(name, amount);
    }

    public int getDropRate() {
        return this.dropRate;
    }

    public void setDropRate(int i) {
        this.dropRate = i;
    }

    public Location getLocation() {
        return this.b.getLocation();
    }

    public void setdropItems(HashMap<String, Object> dropItems) {
        this.dropItems = dropItems;
    }

    public boolean isBeacon() {
        return this.b != null && this.b.getState() instanceof Beacon;
    }

    public Map<String, Object> getItems() {
        return this.dropItems;
    }

    public Map<String, Object> getAmount() {
        return this.amount;
    }

    public Location getDroppingLocation() {
        return this.b.getLocation().add(0.5D, 1.0D, 0.5D);
    }
}
