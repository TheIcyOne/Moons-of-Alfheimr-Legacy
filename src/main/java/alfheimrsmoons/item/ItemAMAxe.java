package alfheimrsmoons.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ItemAMAxe extends ItemAxe
{
    public ItemAMAxe(Item.ToolMaterial material)
    {
        super(material, 0.0F, 0.0F);
    }

    public ItemAMAxe setAttackDamage(float damage)
    {
        damageVsEntity = damage;
        return this;
    }

    public ItemAMAxe setAttackSpeed(float speed)
    {
        attackSpeed = speed;
        return this;
    }
}
