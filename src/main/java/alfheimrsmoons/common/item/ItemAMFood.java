package alfheimrsmoons.common.item;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemAMFood extends ItemFood {
    private String name;
    private PotionEffect[] effects;

    public ItemAMFood(String unlocalizedName, int healAmount, float saturation, boolean isWolfFood, boolean alwaysEdible, PotionEffect... effects) {
        super(healAmount, saturation, isWolfFood);
        this.name = unlocalizedName;
        this.setUnlocalizedName(name);
        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);

        this.effects = effects;

        if (alwaysEdible) {
            setAlwaysEdible();
        }

        GameRegistry.registerItem(this, name);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        for (int i = 0; i < effects.length; i++) {
            if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0)
                player.addPotionEffect(new PotionEffect(this.effects[i]));
        }
    }

    public String getName() {
        return this.name;
    }

}
