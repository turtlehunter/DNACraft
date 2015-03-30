package dnacraft.common.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dnacraft.api.IMeta;

public class ItemGeneric extends Item {

	private HashMap<Integer, IMeta> metaitems = new HashMap<Integer, IMeta>();

	
	public ItemGeneric() {
		super();
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureFile("/dnacraft/resources/gfx/items_generic.png");
	}

	@Override
	public IIcon getIconFromDamage(int i) {
		IMeta meta = getMeta(i);
		if (meta != null) {
			return meta.getIconIndex();
		}
		return 0;
	}
	
    public String getItemNameIS(ItemStack stack)
    {
		IMeta meta = getMeta(stack.getItemDamage());
		if (meta != null) {
			return meta.getItemNameIS(stack);
		}
        return "";
    }
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		IMeta meta = getMeta(itemStack.getItemDamage());
		if (meta != null) {
			return meta.onItemUse(itemStack, player, world, x, y, z, side, par8, par9, par10);
		}
        return true;
	}
	
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		IMeta meta = getMeta(itemStack.getItemDamage());
		if (meta != null) {
			return meta.onItemRightClick(itemStack, player, world);
		}
        return itemStack;
    }
	
	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLiving target,
			EntityLiving player) {
		IMeta meta = getMeta(itemStack.getItemDamage());
		if (meta != null) {
			return meta.hitEntity(itemStack, target, player);
		}
        return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List subItems) {
		for (Entry<Integer, IMeta> entry : metaitems.entrySet()) {
			if (entry.getValue().displayInCreative()) {
				subItems.add(new ItemStack(Block.getBlockById(entry.getKey()), 1));
			}
		}
	}

	public void addMeta(IMeta meta) {
		metaitems.put(meta.getId(), meta);
	}

	public IMeta getMeta(int id) {
		return metaitems.get(id);
	}
	
	public IMeta getMeta(Class klass) {
		for (Entry<Integer, IMeta> entry : this.metaitems.entrySet()) {
			if (entry.getValue().getClass().equals(klass)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public IMeta getMeta(ItemStack itemStack) {
		return getMeta(itemStack.getItemDamage());
	}
	
	public ItemStack newItemStack(Class meta) {
		return newItemStack(meta, 1);
	}
	
	public ItemStack newItemStack(Class meta, int number) {
		return new ItemStack(
				this,
				number,
				getMeta(meta).getId()
		);
	}
	
	public boolean isA(ItemStack stack, Class klazz) {
		IMeta meta = getMeta(stack);
		if (meta == null || !klazz.isAssignableFrom(meta.getClass())) { 
			return false;
		}
		return stack.getItemDamage() == meta.getId();
	}


}
