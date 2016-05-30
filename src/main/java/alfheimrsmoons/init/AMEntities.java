package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AMEntities
{
    public static final ResourceLocation NITRO_WRAITH_LOOT_TABLE = registerLootTable("entities/nitro_wraith");

    private static int nextEntityId = 0;

    public static void registerEntities()
    {
        registerEntity(EntityAMArrow.class, "Arrow", 64, 20, false);
        registerEntity(EntityNitroWraith.class, "NitroWraith", 80, 3, true, 1447446, 5060690);
        registerEntity(EntitySeedPouch.class, "SeedPouch", 64, 10, true);
    }

    /**
     * @param entityClass          The entity class
     * @param entityName           A unique name for the entity
     * @param trackingRange        The range at which MC will send tracking updates
     * @param updateFrequency      The frequency of tracking updates
     * @param sendsVelocityUpdates Whether to send velocity information packets as well
     * @see net.minecraft.entity.EntityList
     * @see net.minecraft.entity.EntityTracker
     */
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(entityClass, entityName, nextEntityId++, AlfheimrsMoons.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    /**
     * @param entityClass          The entity class
     * @param entityName           A unique name for the entity
     * @param trackingRange        The range at which MC will send tracking updates
     * @param updateFrequency      The frequency of tracking updates
     * @param sendsVelocityUpdates Whether to send velocity information packets as well
     * @see net.minecraft.entity.EntityList
     * @see net.minecraft.entity.EntityTracker
     */
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
    {
        registerEntity(entityClass, entityName, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
    }

    private static ResourceLocation registerLootTable(String id)
    {
        return LootTableList.register(new ResourceLocation(AlfheimrsMoons.MOD_ID, id));
    }
}
