package com.magicwands;

import com.magicwands.spell.Spell;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CustomRegistries {
	public static final Registry<Spell> SPELL = FabricRegistryBuilder.createSimple(CustomRegistryKeys.SPELL).buildAndRegister();
	
	public static void initialize() {
	}
}
