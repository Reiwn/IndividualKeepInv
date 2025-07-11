package rose.individualkeepinv;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.minecraft.server.network.ServerPlayerEntity;

public class IndividualKeepInv implements ModInitializer {
	public static final String MOD_ID = "individualkeepinv";
	public static final Logger LOGGER = LoggerFactory.getLogger("individualkeepinv");

	public static void onRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
            if (!alive && oldPlayer.getName().getString().equals("NotDrop")) {
                newPlayer.copyFrom(oldPlayer, true);
                newPlayer.setHealth(20.0f);
            }
            if (!alive && !oldPlayer.getName().getString().equals("NotDrop")) {
                newPlayer.experienceLevel = 0;
                newPlayer.totalExperience = 0;
                newPlayer.experienceProgress = 0.0f;
            }
        }
	@Override
	public void onInitialize() {
		ServerPlayerEvents.AFTER_RESPAWN.register(IndividualKeepInv::onRespawn);
	}
}
