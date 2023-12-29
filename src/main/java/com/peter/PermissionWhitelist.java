package com.peter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionWhitelist implements ModInitializer, ServerPlayConnectionEvents.Join {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("permissionWhitelist");
	
	public static String permissionNode = "roboBouncer.canJoin";

	public static boolean opBypass = false;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ServerPlayConnectionEvents.JOIN.register(this);



		LOGGER.info("Loaded Permission Whitelist");
		LOGGER.info(" __         ");
		LOGGER.info("|__) \\    /");
		LOGGER.info("|     \\/\\/");
		LOGGER.info("           ");
	}

	@Override
	public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
		if (opBypass && handler.player.hasPermissionLevel(2)) { // If player is op-ed, ignore whitelisting
			return;
		}

		LuckPerms api = LuckPermsProvider.get();

		UserManager userManager = api.getUserManager();
		CompletableFuture<User> userFuture = userManager.loadUser(handler.player.getUuid());

		userFuture.thenAcceptAsync(user -> {
			boolean isWhitelisted = user.getCachedData().getPermissionData().checkPermission(permissionNode)
					.asBoolean();
			if (!isWhitelisted) {
				handler.disconnect(Text.of("Not Whitelisted"));
				LOGGER.warn("Player " + handler.player.getDisplayName() + " tried to join but did not have permission");
			}
		});

		// User user = userFuture.join();

		// user.
		
		// handler.player.has

		// handler.disconnect(Text.of("Not Whitelisted"));
	}


}