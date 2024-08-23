package com.magicwands;

import com.magicwands.spell.Spell;

import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class CustomRegistryKeys{
	
	public static final RegistryKey<Registry<Spell>> SPELL = RegistryKey.ofRegistry(Identifier.of("magicwands", "spell"));
	
	public static void initialize() {
	}

}
