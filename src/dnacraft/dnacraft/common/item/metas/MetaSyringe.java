package dnacraft.common.item.metas;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;

public class MetaSyringe implements IMeta {
	
	private int id;
	
	
	public MetaSyringe(int id) {
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
		return "dnacraft.syringe";
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float par8, float par9,
			float par10) {
		return false;
	}
	
	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLiving target,
			EntityLiving player) {
		
		if (!player.worldObj.isRemote) {
			
			DNA dna = DNA.getDNAForEntity(target);
			
			if (dna != null) {
				
				NBTTagCompound currentCompound;
				if (itemStack.hasTagCompound()) {
					currentCompound = itemStack.getTagCompound();
				}else {
					currentCompound = new NBTTagCompound();
				}
				currentCompound.setCompoundTag("traits", dna.toNBT());
				
				itemStack.setTagCompound(currentCompound);
				
				IMeta meta = DNACraft.Items.itemUnstackable.getMeta(MetaBloodSample.class);
				
				if (meta != null) {
					
					itemStack.setItemDamage(meta.getId());
					
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean displayInCreative() {
		return true;
	}
}
