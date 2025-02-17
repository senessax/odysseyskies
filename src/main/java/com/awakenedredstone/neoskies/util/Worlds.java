package com.awakenedredstone.neoskies.util;

import com.awakenedredstone.neoskies.api.NeoSkiesAPI;
import com.awakenedredstone.neoskies.logic.Hub;
import com.awakenedredstone.neoskies.logic.Island;
import com.awakenedredstone.neoskies.logic.IslandLogic;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.registry.RegistryKey;

import java.util.Optional;

public class Worlds {

    /**
     * Teleport the player back to their island spawn (if in an island dimension),
     * or to the hub otherwise.
     *
     * @param player     The player to teleport
     * @param fallDamage If false, resets fallDistance to prevent fall damage on landing
     */
    public static void returnToIslandSpawn(ServerPlayerEntity player, boolean fallDamage) {
        // Remove mounts and riding before teleporting
        if (player.hasPassengers()) {
            player.removeAllPassengers();
        }
        player.stopRiding();

        // If the player is in an island dimension, go to that island's spawn
        if (NeoSkiesAPI.isIsland(player.getWorld())) {
            Optional<Island> islandOptional = NeoSkiesAPI.getOptionalIsland(player.getWorld());
            if (islandOptional.isPresent()) {
                Island island = islandOptional.get();
                ServerWorld targetWorld = island.getOverworld();

                // Determine if we should send them to spawnPos or visitsPos
                Vec3d targetPos = island.isMember(player) ? island.spawnPos : island.visitsPos;

                // If we're ignoring fall damage, reset fall distance and velocity
                if (!fallDamage) {
                    player.fallDistance = 0;
                }
                player.setVelocity(Vec3d.ZERO);

                // Use the built-in teleport(...) method
                player.teleport(
                  targetWorld,
                  targetPos.x,
                  targetPos.y,
                  targetPos.z,
                  player.getYaw(),  // or 0f if you always want them facing east
                  player.getPitch() // or 0f if you don't care
                );
            }
        } else {
            // Otherwise, teleport the player to the hub
            Hub hub = IslandLogic.getInstance().hub;
            if (!IslandLogic.getConfig().safeVoidFallDamage) {
                player.fallDistance = 0;
            }
            hub.visit(player, true);
        }
    }

    /**
     * Optionally remap certain dimension keys to the vanilla Overworld/Nether/End.
     * (This is your custom logic to "redirect" special dims.)
     */
    public static RegistryKey<World> redirect(RegistryKey<World> registryKey) {
        if (NeoSkiesAPI.isOverworld(registryKey)) {
            return World.OVERWORLD;
        }
        if (NeoSkiesAPI.isEnd(registryKey)) {
            return World.END;
        }
        if (NeoSkiesAPI.isNether(registryKey)) {
            return World.NETHER;
        }
        return registryKey;
    }

    public static RegistryKey<World> redirect(World world) {
        return redirect(world.getRegistryKey());
    }

    /**
     * Teleports the player to a specific location and orientation (Vec3d + yaw/pitch).
     */
    public static void teleport(ServerPlayerEntity player, ServerWorld targetWorld, Vec3d pos, float yaw, float pitch) {
        teleport(player, targetWorld, pos.x, pos.y, pos.z, yaw, pitch);
    }

    /**
     * Teleports the player to a specific location and orientation (x,y,z + yaw/pitch).
     * If the targetWorld is the same as the player's current world, it simply updates
     * their position via the player's network handler.
     * If the targetWorld is different, it calls the built-in dimension-changing method.
     */
    public static void teleport(ServerPlayerEntity player,
                                ServerWorld targetWorld,
                                double x, double y, double z,
                                float yaw, float pitch) {

        // Remove passengers and stop riding first
        if (player.hasPassengers()) {
            player.removeAllPassengers();
        }
        player.stopRiding();

        // If it's the same dimension, just do a position update
        if (targetWorld == player.getWorld()) {
            player.networkHandler.requestTeleport(x, y, z, yaw, pitch);
        } else {
            // If it's a different dimension, use the built-in .teleport(...) method
            player.teleport(targetWorld, x, y, z, yaw, pitch);
        }
    }
}
