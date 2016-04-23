package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.*;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AMEntities {
    private static int nextEntityId = 0;

    public static void registerEntities() {
        registerEntity(EntityAMArrow.class, "Arrow", 64, 20, false);
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
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, AlfheimrsMoons.MOD_ID + ":" + entityName, nextEntityId++, AlfheimrsMoons.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
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
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        registerEntity(entityClass, entityName, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
    }
}
