package com.ambmt.dropParty;

import org.bukkit.Bukkit;

public class Countdown {
    private int time;
    private Object[] messages;
    private boolean running;

    public Countdown(int time, Object[] messages) {
        this.time = time;
        this.messages = messages;
        this.running = true;
        this.startCountdown();
    }

    private void startCountdown() {
        Bukkit.getServer().broadcastMessage(this.messages[0].toString());
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("BeaconDropParty"), new Runnable() {
            Integer i;

            {
                this.i = Countdown.this.time;
            }

            public void run() {
                if (this.i >= 0 && Countdown.this.running) {
                    this.i = this.i - 1;
                    Bukkit.getServer().broadcastMessage(Countdown.this.messages[1].toString().replaceAll("%COUNTDOWN%", Integer.toString(this.i)));
                }

                if (this.i <= 0 && Countdown.this.running) {
                    Countdown.this.running = false;
                    BeaconDropParty plugin = (BeaconDropParty)Bukkit.getPluginManager().getPlugin("BeaconDropParty");
                    plugin.forcestart();
                    Bukkit.getServer().broadcastMessage(Countdown.this.messages[2].toString());
                }

            }
        }, 0L, 20L);
    }

    public void stopCountdown() {
        this.running = false;
    }

    public boolean running() {
        return this.running;
    }
}
