package svenhjol.charm_core.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

public interface PlayerLoginCallback {
    Event<PlayerLoginCallback> EVENT = EventFactory.createArrayBacked(PlayerLoginCallback.class, listeners -> player -> {
        for (PlayerLoginCallback listener : listeners) {
            listener.interact(player);
        }
    });

    void interact(Player player);
}
