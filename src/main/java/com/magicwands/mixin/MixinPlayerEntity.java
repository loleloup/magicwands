package com.magicwands.mixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.points.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.magicwands.playermana.PlayerMana;
import com.magicwands.playermana.SpellcastCallback;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity implements PlayerMana {
	
	private int mana = 100;

	
	@Override
	public int getMaxMana() {
		return this.mana;
	}
	
	@Override
	public void setMana(int newValue) {
		this.mana = newValue;
	}
	
	@Inject(at = @At("TAIL"), method = "<init>*")
	private void onConstructed(CallbackInfo ci) {
		//for future me : This register the event twice : one for the client Player and one for the server player
		//could cause issue in the future?  Is that OK?  IDK yet
		//What about the use of attributes?
		SpellcastCallback.EVENT.register((player, manaCost) -> {
			System.out.println("player mana : ");
			System.out.println(this.mana);
			if (manaCost > this.mana) {
				return ActionResult.FAIL;
			}
		    this.mana -= manaCost;
		    return ActionResult.CONSUME;
		});
	}
}
