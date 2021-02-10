package com.ambmt.dropParty;

import java.util.Iterator;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PartyDropper {
    int dropRate;
    Location location;
    Map<String, Object> dropItems;
    Map<String, Object> amount;
    boolean dropping;

    public PartyDropper(PartyBeacon partyBeacon) {
        this.dropRate = partyBeacon.getDropRate();
        this.location = partyBeacon.getDroppingLocation();
        this.dropItems = partyBeacon.getItems();
        this.amount = partyBeacon.getAmount();
        this.dropping = true;
        this.loadDropper();
    }

    public void stopDropper() {
        this.dropping = false;
    }

    private void loadDropper() {
        try {
            Iterator var4 = this.dropItems.keySet().iterator();

            while(var4.hasNext()) {
                String s = (String)var4.next();
                this.startDropper((ItemStack)this.dropItems.get(s), (Integer)this.amount.get(s));
            }
        } catch (NullPointerException var3) {
            for(int i = 0; i < 20; ++i) {
                System.out.println("Your config file is wrong!!!!");
            }
        }

    }

    private void startDropper(ItemStack item, int amount) {
        final ItemStack dropItem = new ItemStack(item);
        final Integer dropAmount = new Integer(amount);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("BeaconDropParty"), new Runnable() {
            int i = 0;

            public void run() {
                if (this.i < dropAmount && PartyDropper.this.dropping) {
                    Entity e = PartyDropper.this.location.getWorld().dropItemNaturally(PartyDropper.this.location, dropItem);
                    e.setVelocity(new Vector(Math.random() - 0.5D, 0.5D, Math.random() - 0.5D));
                    ++this.i;
                }

            }
        }, 0L, (long)this.dropRate);
    }
}
