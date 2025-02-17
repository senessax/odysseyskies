package com.awakenedredstone.neoskies.mixin.accessor;

import net.minecraft.world.storage.StorageKey;

import java.nio.file.Path;

public interface IRegionBasedStorageAccessor {
    Path getDirectory();
    StorageKey getStorageKey();
    boolean getDsync();
}
