package com.awakenedredstone.neoskies.mixin.accessor;

import net.minecraft.server.world.ChunkHolder;

import java.util.Map;

public interface IServerChunkManager {
    Map<Long, ChunkHolder> getChunkHoldersPublic();
}
