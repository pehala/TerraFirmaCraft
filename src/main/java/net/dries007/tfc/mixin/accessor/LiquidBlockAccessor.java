package net.dries007.tfc.mixin.accessor;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Fix https://github.com/MinecraftForge/MinecraftForge/issues/8002
 * Would be fixed by https://github.com/MinecraftForge/MinecraftForge/pull/7992
 */
@Mixin(LiquidBlock.class)
public interface LiquidBlockAccessor
{
    @Mutable
    @Accessor("fluid")
    void setFlowingFluid(FlowingFluid flowingFluid);
}