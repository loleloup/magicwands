package com.magicwands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.*;

import net.minecraft.item.ItemGroups;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magicwands.item.ModItems;


public class MagicWands implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("magicwands");
    

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		ModItems.initialize();
		CustomRegistryKeys.initialize();
		CustomRegistries.initialize();
		WandSpells.initialize();
		
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.add(ModItems.AMETHYST_POWDER));

		LOGGER.info("Hello Fabric world!");
		
		
		
	}
}