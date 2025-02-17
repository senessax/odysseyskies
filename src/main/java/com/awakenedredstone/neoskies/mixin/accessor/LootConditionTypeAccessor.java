package com.awakenedredstone.neoskies.mixin.accessor;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import com.mojang.serialization.MapCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootConditionType.class)
public interface LootConditionTypeAccessor {
    @Accessor("codec")
    MapCodec<? extends LootCondition> getCodec();
}
