package dnacraft.common.item.metas;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dnacraft.api.IMeta;

public class MetaTestTube implements IMeta {

	private int id;
	
	public MetaTestTube(int id) {
		this.id = id;
	}
	
	@Override
	public int getIconIndex() {
		return this.id;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, EntityPlayer player,
			World world) {
		return itemStack;
	}

	@Override
	public String getItemNameIS(ItemStack stack) {
		return "dnacraft.testtube";
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLiving target,
			EntityLiving player) {
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float par8, float par9,
			float par10) {
		return false;
	}

	@Override
	public boolean displayInCreative() {
		return true;
	}
}
