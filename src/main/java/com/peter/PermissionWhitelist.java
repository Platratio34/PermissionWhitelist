package com.peter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.api.networking.v1.PacketSender;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionWhitelist implements ModInitializer, ServerPlayConnectionEvents.Join {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("permissionWhitelist");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loaded Permission Whitelist");
	}

	@Override
	public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
		
	}


}