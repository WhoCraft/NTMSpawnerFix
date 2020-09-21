package com.whocraft.ntmspawnerfix;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.tardis.mod.dimensions.TardisDimension;

@Mod("ntmspawnerfix")
public class NTMSpawnerFix {

    public NTMSpawnerFix() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NTMSConfig.COMMON_SPEC);
    }

    @SubscribeEvent
    public void on(LivingSpawnEvent.CheckSpawn event) {
        SpawnReason spawnReason = event.getSpawnReason();
        IWorld world = event.getWorld();
        if (world.getDimension() instanceof TardisDimension) {
            if (spawnReason == SpawnReason.NATURAL) {
                event.setResult(isWhitelisted(event.getEntityLiving()) ? Event.Result.DEFAULT : Event.Result.DENY);
            }
        }
    }

    private boolean isWhitelisted(LivingEntity entityLiving) {
        ResourceLocation location = EntityType.getKey(entityLiving.getType());
        for (String name : NTMSConfig.COMMON.whitelistedMobs.get()) {
            if(name.toLowerCase().matches(location.toString())){
                return true;
            }
        }
        return false;
    }

}
