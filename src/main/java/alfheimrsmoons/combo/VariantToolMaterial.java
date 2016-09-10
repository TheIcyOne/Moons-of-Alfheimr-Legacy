package alfheimrsmoons.combo;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import zaggy1024.combo.variant.IMetadata;

public enum VariantToolMaterial implements IMetadata<VariantToolMaterial>
{
    TIMBER("timber", 0, 59, 2.0F, 0.0F, 15, /* Axe: */ 6.0F, -3.2F),
    SHALE("shale", 1, 131, 4.0F, 1.0F, 5, /* Axe: */ 8.0F, -3.2F),
    TEKTITE("tektite", 2, 250, 6.0F, 2.0F, 14, /* Axe: */ 8.0F, -3.1F),
    SYLVANITE("sylvanite", 3, 1561, 8.0F, 3.0F, 10, /* Axe: */ 8.0F, -3.0F),
    MOONSTEEL("moonsteel", 4, 2119, 10.0F, 4.0F, 8, /* Axe: */ 9.0F, -2.9F),
    SUNSTEEL("sunsteel", 4, 2119, 10.0F, 4.0F, 8, /* Axe: */ 9.0F, -2.9F);

    private final String name;
    private final ToolMaterial material;
    private final float axeDamage;
    private final float axeSpeed;

    VariantToolMaterial(String name, int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, float axeDamage, float axeSpeed)
    {
        this.name = name;
        this.material = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, attackDamage, enchantability);
        this.axeDamage = axeDamage;
        this.axeSpeed = axeSpeed;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getUnlocalizedName()
    {
        return name;
    }

    public ToolMaterial getMaterial()
    {
        return material;
    }

    public int getHarvestLevel()
    {
        return material.getHarvestLevel();
    }

    public int getMaxUses()
    {
        return material.getMaxUses();
    }

    public float getEfficiency()
    {
        return material.getEfficiencyOnProperMaterial();
    }

    public float getAttackDamage()
    {
        return material.getDamageVsEntity();
    }

    public int getEnchantability()
    {
        return material.getEnchantability();
    }

    public float getAxeDamage()
    {
        return axeDamage;
    }

    public float getAxeSpeed()
    {
        return axeSpeed;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
