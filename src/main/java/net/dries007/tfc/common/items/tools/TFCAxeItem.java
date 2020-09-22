/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.common.items.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

import net.minecraft.item.Item.Properties;

/**
 * This is needed so we override the damage math done by vanilla
 *
 * For comparison:
 * Vanilla: Tool value (ie: 3.0 for swords) + material damage value (ie: 2.0 for iron) + 1.0 (hand) = 6.0
 * TFC: Tool value (ie: 1.3 for maces) * material damage value (ie: 5.75 for steel) + 1.0 (hand) ~= 7.5
 */
public class TFCAxeItem extends AxeItem
{
    protected final float attackDamage;
    protected final float attackSpeed;

    public TFCAxeItem(IItemTier tier, float attackDamageMultiplier, float attackSpeed, Properties builder)
    {
        super(tier, 0, attackSpeed, builder);
        this.attackDamage = attackDamageMultiplier * tier.getAttackDamageBonus();
        this.attackSpeed = attackSpeed;
    }

    public float getAttackDamage()
    {
        return attackDamage;
    }

    public float getAttackSpeed()
    {
        return attackSpeed;
    }

    @Override
    public Multimap<String, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }
}