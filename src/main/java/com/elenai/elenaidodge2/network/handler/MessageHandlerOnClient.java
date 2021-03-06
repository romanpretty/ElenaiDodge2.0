package com.elenai.elenaidodge2.network.handler;

import java.util.Optional;
import java.util.function.Supplier;

import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.config.ConfigHandler;
import com.elenai.elenaidodge2.event.ArmorTickEventListener;
import com.elenai.elenaidodge2.event.ClientTickEventListener;
import com.elenai.elenaidodge2.gui.DodgeStep;
import com.elenai.elenaidodge2.network.NetworkHandler;
import com.elenai.elenaidodge2.network.message.client.AbsorptionMessageToClient;
import com.elenai.elenaidodge2.network.message.client.CancelledFeathersMessageToClient;
import com.elenai.elenaidodge2.network.message.client.ConfigMessageToClient;
import com.elenai.elenaidodge2.network.message.client.DodgeEffectsMessageToClient;
import com.elenai.elenaidodge2.network.message.client.DodgeMessageToClient;
import com.elenai.elenaidodge2.network.message.client.InitPlayerMessageToClient;
import com.elenai.elenaidodge2.network.message.client.ParticleMessageToClient;
import com.elenai.elenaidodge2.network.message.client.PatronMessageToClient;
import com.elenai.elenaidodge2.network.message.client.RegenModifierMessageToClient;
import com.elenai.elenaidodge2.network.message.client.VelocityMessageToClient;
import com.elenai.elenaidodge2.network.message.client.WeightMessageToClient;
import com.elenai.elenaidodge2.util.ClientStorage;
import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.elenai.elenaidodge2.util.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageHandlerOnClient {

	/**
	 * Called when a message is received of the appropriate type. CALLED BY THE
	 * NETWORK THREAD, NOT THE CLIENT THREAD
	 */
	public static void onMessageReceived(final WeightMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("WeightMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("WeightMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("WeightMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final RegenModifierMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER.warn(
					"RegenModifierMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("RegenModifierMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("RegenModifierMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final CancelledFeathersMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER.warn(
					"CancelledFeathersMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("CancelledFeathersMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("CancelledFeathersMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final DodgeEffectsMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER.warn(
					"DodgeEffectsMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("DodgeEffectsMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("DodgeEffectsMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final InitPlayerMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("InitPlayerMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("InitPlayerMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("InitPlayerMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final ParticleMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("ParticleMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("ParticleMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("ParticleMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final AbsorptionMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("AbsorptionMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("AbsorptionMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("AbsorptionMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final DodgeMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("DodgeMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("DodgeMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("DodgeMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final VelocityMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("VelocityMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("VelocityMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("VelocityMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final ConfigMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("ConfigMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("ConfigMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("ConfigMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	public static void onMessageReceived(final PatronMessageToClient message,
			Supplier<NetworkEvent.Context> ctxSupplier) {
		NetworkEvent.Context ctx = ctxSupplier.get();
		LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
		ctx.setPacketHandled(true);

		if (sideReceived != LogicalSide.CLIENT) {
			ElenaiDodge2.LOGGER
					.warn("PatronMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
			return;
		}
		if (!message.isMessageValid()) {
			ElenaiDodge2.LOGGER.warn("PatronMessageToClient was invalid" + message.toString());
			return;
		}

		Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
		if (!clientWorld.isPresent()) {
			ElenaiDodge2.LOGGER.warn("PatronMessageToClient context could not provide a ClientWorld.");
			return;
		}

		ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
	}

	/*
	 * PROCESS MESSAGES
	 */

	private static void processMessage(ClientWorld worldClient, WeightMessageToClient message) {
		if (message.getWeight() > 0) {
			ClientStorage.weight = message.getWeight();
		} else {
			ArmorTickEventListener.previousArmor.clear();
		}
		return;
	}

	private static void processMessage(ClientWorld worldClient, DodgeEffectsMessageToClient message) {
		ClientStorage.absorption = message.getAbsorption();
		ClientStorage.dodges = message.getDodges();
		if (ClientStorage.tutorialDodges < 1) {
			ClientStorage.tutorialDodges += 0.25;
			DodgeStep.moveToast.setProgress((float) ClientStorage.tutorialDodges);
		}
		Utils.showDodgeBar();
		return;
	}

	private static void processMessage(ClientWorld worldClient, InitPlayerMessageToClient message) {
		ClientStorage.dodges = message.getDodges();
		if (ConfigHandler.tutorial) {
			ClientStorage.shownTutorial = false;
			ClientStorage.tutorialDodges = 0;
		}
		return;
	}

	@SuppressWarnings("resource")
	private static void processMessage(ClientWorld worldClient, ParticleMessageToClient message) {

		IParticleData particle = null;
		switch (message.getLevel()) {
		case 0:
			particle = ParticleTypes.CLOUD;
			break;
		case 1:
			particle = ParticleTypes.HEART;
			break;
		case 2:
			particle = ParticleTypes.FLAME;
			break;
		case 3:
			particle = ParticleTypes.SOUL_FIRE_FLAME;
			break;
		case 4:
			particle = ParticleTypes.SOUL;
			break;
		case 5:
			particle = ParticleTypes.END_ROD;
		default:
			break;
		}

		if (message.getLevel() > 0) {
			for (int i = 0; i < 8; ++i) {
				double d0 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d1 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d2 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				Minecraft.getInstance().player.world.addParticle(particle,
						message.getX() + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F)
								- (double) 0.6f - d0 * 12.0D,
						message.getY() + 0.1,
						message.getZ() + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F)
								- (double) 0.6f - d2 * 12.0D,
						d0, d1, d2);
			}
		} else {
			for (int i = 0; i < 8; ++i) {
				double d0 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d1 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				double d2 = Minecraft.getInstance().player.world.rand.nextGaussian() * 0.02D;
				Minecraft.getInstance().player.world.addParticle(particle,
						message.getX() + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F)
								- (double) 0.6f - d0 * 10.0D,
						message.getY() + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 1.8f)
								- d1 * 10.0D,
						message.getZ() + (double) (Minecraft.getInstance().player.world.rand.nextFloat() * 0.6f * 2.0F)
								- (double) 0.6f - d2 * 10.0D,
						d0, d1, d2);
			}
		}
		return;
	}

	private static void processMessage(ClientWorld worldClient, AbsorptionMessageToClient message) {
		ClientStorage.absorption = message.getAbsorption();
		return;
	}

	private static void processMessage(ClientWorld worldClient, DodgeMessageToClient message) {
		ClientStorage.dodges = message.getDodges();
		return;
	}

	@SuppressWarnings("resource")
	private static void processMessage(ClientWorld worldClient, VelocityMessageToClient message) {
		Minecraft.getInstance().player.setVelocity(message.getX(), message.getY(), message.getZ());
		return;
	}

	private static void processMessage(ClientWorld worldClient, ConfigMessageToClient message) {
		ClientStorage.regenSpeed = message.getRegenRate();

		if (message.getDodges() != 9999) {
			ClientStorage.dodges = message.getDodges();
			ClientStorage.absorption = message.getAbsorption();
		}

		ClientStorage.weightValues = message.getWeightValues();
		ClientStorage.halfFeathers = message.getHalfFeathers();

		// Forces Armor Refresh
		ArmorTickEventListener.previousArmor.clear();
		return;
	}

	private static void processMessage(ClientWorld worldClient, PatronMessageToClient message) {
		PatronRewardHandler.localPatronTier = message.getLevel();
		return;
	}

	private static void processMessage(ClientWorld worldClient, CancelledFeathersMessageToClient message) {
		ClientTickEventListener.failedFlashes = 0;
		Utils.showDodgeBar();
		return;
	}

	private static void processMessage(ClientWorld worldClient, RegenModifierMessageToClient message) {
		ClientStorage.regenModifier = message.getModifier();
		return;
	}

	public static boolean isThisProtocolAcceptedByClient(String protocolVersion) {
		return NetworkHandler.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}

}
