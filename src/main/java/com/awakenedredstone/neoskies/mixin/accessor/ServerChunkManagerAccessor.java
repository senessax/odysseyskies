package com.awakenedredstone.neoskies.mixin.accessor;

import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerChunkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ServerChunkManager.class)
public interface ServerChunkManagerAccessor {
    @Accessor("chunkHolders")
    Map<Long, ChunkHolder> getChunkHolders();
}
