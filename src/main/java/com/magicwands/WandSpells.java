package com.magicwands;

import com.magicwands.spell.Spell;
import com.magicwands.spell.ThunderSpell;

import net.minecraft.potion.Potion;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class WandSpells {
	
	public static void initialize() {
	}	
	public static final RegistryEntry<Spell> THUNDER = register("thunder", new ThunderSpell());

	private static RegistryEntry<Spell> register(String name, Spell spell) {
		return Registry.registerReference(CustomRegistries.SPELL, Identifier.of(name), spell);
	}

	public static RegistryEntry<Spell> registerAndGetDefault(Registry<Potion> registry) {
		return THUNDER;
	}
}
