package com.awakenedredstone.neoskies.mixin;

import com.awakenedredstone.neoskies.mixin.accessor.IServerChunkManager;
import com.awakenedredstone.neoskies.mixin.accessor.ServerChunkManagerAccessor;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ChunkHolder;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin implements IServerChunkManager {
    @Override
    public Map<Long, ChunkHolder> getChunkHoldersPublic() {
        return ((ServerChunkManagerAccessor) this).getChunkHolders();
    }
}
