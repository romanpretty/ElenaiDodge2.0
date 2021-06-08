package com.elenai.elenaidodge2.event;

import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.gui.DodgeGui;
import com.elenai.elenaidodge2.gui.DodgeStep;
import com.elenai.elenaidodge2.integration.ToughAsNailsClient;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.server.DodgeRegenMessageToServer;
import com.elenai.elenaidodge2.network.message.server.ThirstMessageToServer;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientTickEventListener {

	public static int regen = ClientStorage.regenSpeed;

	public static int pauseLen = 7;
	public static int animationLen = 4;
	public static int alphaLen = 60;

	public static int animation = animationLen;
	public static int pause = pauseLen;
	public static int flashes = 2;

	public static int failedAnimation = animationLen;
	public static int failedPause = pauseLen;
	public static int failedFlashes = 2;

	public static int alpha = alphaLen;
	
	public static boolean stepVisible = false;
	public static int toastCountdown = 0;

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onPlayerTickClient(TickEvent.ClientTickEvent event) {

		ClientPlayerEntity player = Minecraft.getInstance().player;
		if (player != null) {

			// CLIENT
			if (event.phase == TickEvent.Phase.END && player.world.isRemote
					&& !Minecraft.getInstance().isGamePaused()) {

				// Tutorial
				if (!ClientStorage.shownTutorial && ConfigHandler.tutorial) {
					DodgeStep.show();
					ClientStorage.shownTutorial = true;
					stepVisible = true;
				}

				if (ClientStorage.shownTutorial == true && stepVisible == true) {
					toastCountdown++;
				}
				
				if(toastCountdown == 300) {
					ClientStorage.tutorialDodges = 1;
				}
				
				if (ClientStorage.tutorialDodges == 1) {
					DodgeStep.hide();
					stepVisible = false;
				}

				// COOLDOWN
				if (ClientStorage.cooldown > 0) {
					ClientStorage.cooldown--;
				}

				// ANIMATION
				if (flashes < 2 && pause <= 0) {
					pause = pauseLen;
					animation = animationLen;
					ClientStorage.healing = true;
					flashes++;
				}

				if (pause > 0) {
					pause--;
				}

				if (animation > 0) {
					animation--;
				}

				if (ClientStorage.healing && animation == 0) {
					ClientStorage.healing = false;
				}

				// FAILED ANIMATION
				if (failedFlashes < 2 && failedPause <= 0) {
					failedPause = pauseLen;
					failedAnimation = animationLen;
					ClientStorage.failed = true;
					failedFlashes++;
				}

				if (failedPause > 0) {
					failedPause--;
				}

				if (failedAnimation > 0) {
					failedAnimation--;
				}

				if (ClientStorage.failed && failedAnimation == 0) {
					ClientStorage.failed = false;
				}

				// ALPHA
				if (ClientStorage.dodges >= 20 && alpha > 0 && DodgeGui.alpha > 0) {
					alpha--;
				}

				if (alpha == 0 && DodgeGui.alpha > 0 && ConfigHandler.fadeout) {
					DodgeGui.alpha -= 0.04f;
				}

				// REGENERATION LOGIC
				if (ClientStorage.dodges < 20) {

					if (regen > 0 && Utils.tanEnabled(player)) {
						if (ToughAsNailsClient.highThirst()) {
							regen--;
						}
					} else if (regen > 0) {
						regen--;
					} else if (regen <= 0) {

						if (Utils.tanEnabled(player)) {
							NetworkHandler.simpleChannel.sendToServer(new ThirstMessageToServer());
						}

						ClientStorage.dodges++;
						NetworkHandler.simpleChannel.sendToServer(new DodgeRegenMessageToServer(ClientStorage.dodges));
						flashes = 0;
						if (ClientStorage.regenSpeed + ClientStorage.regenModifier > 0) {
							regen = ClientStorage.regenSpeed + ClientStorage.regenModifier;
						} else {
							regen = 1;
						}

					}

				}
			}
		}
	}

}
