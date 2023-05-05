package com.gmail.necnionch.myplugin.witherinithp.bukkit.events;

import org.bukkit.entity.Wither;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WitherFullHealMarkEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Wither wither;
    private boolean cancelled;

    public WitherFullHealMarkEvent(Wither wither) {
        this.wither = wither;
    }

    public Wither getWither() {
        return wither;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
