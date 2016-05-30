package alfheimrsmoons.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.HashSet;

public class ItemAMAxe extends ItemTool
{
    public ItemAMAxe(Item.ToolMaterial material)
    {
        super(material, new HashSet<Block>());
        setHarvestLevel("axe", material.getHarvestLevel());
    }

    public ItemAMAxe(Item.ToolMaterial material, float attackDamage, float attackSpeed)
    {
        this(material);
        setAttackDamage(attackDamage);
        setAttackSpeed(attackSpeed);
    }

    public ItemAMAxe setAttackDamage(float attackDamage)
    {
        damageVsEntity = attackDamage;
        return this;
    }

    public ItemAMAxe setAttackSpeed(float attackSpeed)
    {
        this.attackSpeed = attackSpeed;
        return this;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.wood && material != Material.plants && material != Material.vine ? super.getStrVsBlock(stack, state) : efficiencyOnProperMaterial;
    }
}
