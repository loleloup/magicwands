package com.magicwands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magicwands.mixin.MixinPlayerEntity;
import com.magicwands.playermana.PlayerMana;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class ExampleModClient implements ClientModInitializer {
	
    public static float totalTickDelta = 0;
    public static final Logger LOGGER = LoggerFactory.getLogger("magicwands");


	private static final Identifier MANA_FULL_TEXTURE = Identifier.of("magicwands", "textures/gui/mana.png");
	private static final Identifier MANA_EMPTY_TEXTURE = Identifier.of("magicwands", "textures/gui/mana_empty.png");

    @Override
    public void onInitializeClient() {
        // Register the HUD render callback
        HudRenderCallback.EVENT.register(this::onHudRender);
    }

    private void onHudRender(DrawContext drawContext, RenderTickCounter tickDelta) {
        renderCustomStatusBar(drawContext);
    }

    private void renderCustomStatusBar(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        
        PlayerEntity player = client.getCameraEntity() instanceof PlayerEntity playerEntity ? playerEntity : null;
        
        if (player == null) {
        	return;
        }
        
        int mana = player.getMana();
        int max_mana = player.getMaxMana();
        
//        LOGGER.info("screen {} {}", screenWidth, screenHeight);

        // Example bar dimensions
        int barWidth = 100;
        int barHeight = 10;
        int x = screenWidth / 2 - barWidth / 2;
        int y = screenHeight-59;
        
        int l = mana;
        client.getTextureManager().bindTexture(MANA_FULL_TEXTURE);
        client.getTextureManager().bindTexture(MANA_EMPTY_TEXTURE);
        LOGGER.info("mana {}", mana);
		RenderSystem.enableBlend();
		int m = y;

		for (int n = 0; n < 10; n++) {
			int o = x + n * 9;
			if (n * 10 + 1 <= l) {
				drawContext.drawTexture(MANA_FULL_TEXTURE, o, m, 0, 0, 9, 9, 9, 9);
			}

			if (n * 10 + 1 > l) {
				drawContext.drawTexture(MANA_EMPTY_TEXTURE, o, m, 0, 0, 9, 9, barWidth, barHeight);
			}
		}

		RenderSystem.disableBlend();
        
//        LOGGER.info("rendering the bar at pos {} {}", x, y);
//
//        // Draw the background bar
//        drawContext.fill(x, y, x + barWidth, y + barHeight, 0xFFFF0000); // Black background
//
//        // Draw the filled portion based on status percentage
//        int filledWidth = (int) (barWidth * getStatusPercentage());
//        drawContext.fill(x, y, x + filledWidth, y + barHeight, 0xFFFF0000); // Green fill
//
//        // Optionally, draw a custom texture
//        client.getTextureManager().bindTexture(MANA_FULL_TEXTURE);
//        drawContext.drawTexture(MANA_FULL_TEXTURE, x, y, 0, 0, filledWidth, barHeight, barWidth, barHeight);
    }

    private float getStatusPercentage() {
        // Replace with actual logic for the resource percentage
        return 0.75f;
    }
}
