package dnacraft.api;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IMeta {

	public int getIconIndex();
	public String getItemNameIS(ItemStack stack);
	public int getId();
    public boolean hitEntity(ItemStack itemStack, EntityLiving target, EntityLiving player);
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10);
	public ItemStack onItemRightClick(ItemStack itemStack, EntityPlayer player,
			World world);
	public boolean displayInCreative();
}
