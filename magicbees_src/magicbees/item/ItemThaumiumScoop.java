package magicbees.item;

import magicbees.main.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import forestry.api.core.IToolScoop;

public class ItemThaumiumScoop extends Item implements IRepairable, IToolScoop
{
	public ItemThaumiumScoop(int itemID)
	{
		super(itemID);
		this.setMaxStackSize(1);
		this.setMaxDamage(30);
		this.setCreativeTab(forestry.api.core.Tabs.tabApiculture);
		this.setUnlocalizedName(this.iconString = CommonProxy.DOMAIN + ":thaumiumScoop");
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block)
	{
		return 1f;
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block, int metadata)
	{
		return ForgeHooks.isToolEffective(itemStack, block, metadata) ? 4.8f : getStrVsBlock(itemStack, block);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLivingBase entityliving) {
		itemstack.damageItem(1, entityliving);
		return true;
	}
	
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return ThaumcraftApi.toolMatThaumium.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName()
    {
        return ThaumcraftApi.toolMatThaumium.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return ThaumcraftApi.toolMatThaumium.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}
