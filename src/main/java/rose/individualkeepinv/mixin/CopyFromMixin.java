package rose.individualkeepinv.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(ServerPlayerEntity.class)
public abstract class CopyFromMixin {

    @Inject(method = "copyFrom", at = @At("HEAD"), cancellable = true)
    public void onCopyFrom(CallbackInfo info) {
        this.sculkShriekerWarningManager = oldPlayer.sculkShriekerWarningManager;
 		    this.session = oldPlayer.session;
		    this.interactionManager.setGameMode(oldPlayer.interactionManager.getGameMode(), oldPlayer.interactionManager.getPreviousGameMode());
		    this.sendAbilitiesUpdate();
		    if (alive) {
			      this.getAttributes().setBaseFrom(oldPlayer.getAttributes());
			      this.getAttributes().addPersistentModifiersFrom(oldPlayer.getAttributes());
			      this.setHealth(oldPlayer.getHealth());
			      this.hungerManager = oldPlayer.hungerManager;

			      for (StatusEffectInstance statusEffectInstance : oldPlayer.getStatusEffects()) {
				        this.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
			      }

			      this.getInventory().clone(oldPlayer.getInventory());
			      this.experienceLevel = oldPlayer.experienceLevel;
			      this.totalExperience = oldPlayer.totalExperience;
			      this.experienceProgress = oldPlayer.experienceProgress;
			      this.setScore(oldPlayer.getScore());
			      this.portalManager = oldPlayer.portalManager;
		    } else {
			      this.getAttributes().setBaseFrom(oldPlayer.getAttributes());
			      this.setHealth(this.getMaxHealth());
			      if (this.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || oldPlayer.isSpectator()) {
				        this.getInventory().clone(oldPlayer.getInventory());
				        this.experienceLevel = oldPlayer.experienceLevel;
			          this.totalExperience = oldPlayer.totalExperience;
				        this.experienceProgress = oldPlayer.experienceProgress;
				        this.setScore(oldPlayer.getScore());
			      }
		    }

		this.enchantingTableSeed = oldPlayer.enchantingTableSeed;
		this.enderChestInventory = oldPlayer.enderChestInventory;
		this.getDataTracker().set(PLAYER_MODEL_PARTS, oldPlayer.getDataTracker().get(PLAYER_MODEL_PARTS));
		this.syncedExperience = -1;
		this.syncedHealth = -1.0F;
		this.syncedFoodLevel = -1;
		this.recipeBook.copyFrom(oldPlayer.recipeBook);
		this.seenCredits = oldPlayer.seenCredits;
		this.enteredNetherPos = oldPlayer.enteredNetherPos;
		this.chunkFilter = oldPlayer.chunkFilter;
		this.setShoulderEntityLeft(oldPlayer.getShoulderEntityLeft());
		this.setShoulderEntityRight(oldPlayer.getShoulderEntityRight());
		this.setLastDeathPos(oldPlayer.getLastDeathPos());
    info.cancel();
    }
}
