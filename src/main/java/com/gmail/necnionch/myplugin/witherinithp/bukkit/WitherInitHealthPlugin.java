package com.gmail.necnionch.myplugin.witherinithp.bukkit;

import com.gmail.necnionch.myplugin.witherinithp.bukkit.events.WitherFullHealEvent;
import com.gmail.necnionch.myplugin.witherinithp.bukkit.events.WitherFullHealMarkEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class WitherInitHealthPlugin extends JavaPlugin implements Listener {

    public static final String MARK_TAG = "witherInitFullHeal";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onSummon(CreatureSpawnEvent event) {
        if (!CreatureSpawnEvent.SpawnReason.BUILD_WITHER.equals(event.getSpawnReason()))
            return;

        if (!(event.getEntity() instanceof Wither))
            return;

        Wither wither = (Wither) event.getEntity();

        WitherFullHealMarkEvent newEvent = new WitherFullHealMarkEvent(wither);
        getServer().getPluginManager().callEvent(newEvent);
        if (newEvent.isCancelled())
            return;

        wither.addScoreboardTag(MARK_TAG);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Wither))
            return;
        Wither wither = (Wither) event.getEntity();

        if (!wither.getScoreboardTags().contains(MARK_TAG))
            return;
        wither.removeScoreboardTag(MARK_TAG);

        WitherFullHealEvent newEvent = new WitherFullHealEvent(wither);
        getServer().getPluginManager().callEvent(newEvent);
        if (newEvent.isCancelled())
            return;

        AttributeInstance attr = wither.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attr != null) {
            wither.setHealth(attr.getValue());
        }
    }

}
