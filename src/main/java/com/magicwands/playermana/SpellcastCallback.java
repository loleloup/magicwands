package com.magicwands.playermana;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface  SpellcastCallback {
	
	 Event<SpellcastCallback> EVENT = EventFactory.createArrayBacked(SpellcastCallback.class,
		        (listeners) -> (player, manaCost) -> {
		            for (SpellcastCallback listener : listeners) {
		                ActionResult result = listener.interact(player, manaCost);
		 
		                if(result != ActionResult.PASS) {
		                    return result;
		                }
		            }
		 
		        return ActionResult.PASS;
		    });
		 
	 ActionResult interact(PlayerEntity player, int manaCost);
}