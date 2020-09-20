package com.whocraft.ntmspawnerfix;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class NTMSConfig {

    public static Common COMMON;
    public static ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedMobs;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Spawner Settings").push("common");
            whitelistedMobs = builder.translation("config.ntms.whitelisted_mobs").comment("List of entities in a namespaced format of mobs to not be whitelisted to spawn in the Tardis Dimension").defineList("whitelisted_mobs", Lists.newArrayList(""), String.class::isInstance);
            builder.pop();
        }
    }

}
