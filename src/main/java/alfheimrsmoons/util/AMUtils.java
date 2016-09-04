package alfheimrsmoons.util;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class AMUtils
{
    public static Random getWorldRandom(IBlockAccess world, Random defaultRandom)
    {
        return world instanceof World ? ((World) world).rand : defaultRandom;
    }
}
