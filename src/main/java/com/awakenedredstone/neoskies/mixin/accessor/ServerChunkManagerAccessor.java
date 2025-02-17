package com.awakenedredstone.neoskies.mixin.accessor;

import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerChunkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(ServerChunkManager.class)
public interface ServerChunkManagerAccessor {
    @Invoker("getChunkHolder")
    ChunkHolder invokeGetChunkHolder(long pos);
}
