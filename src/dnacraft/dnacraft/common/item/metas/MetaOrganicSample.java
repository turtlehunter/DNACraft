package dnacraft.common.item.metas;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;

public class MetaOrganicSample implements IMeta {

	private DNA dna = null;
	private int id;
	private String name;
	
	public static HashMap<Object, MetaOrganicSample> sampleForItems = new HashMap<Object, MetaOrganicSample>();

	public static MetaOrganicSample getFragmentForItemStack(ItemStack stack) {
		Item item = stack.getItem();
		if (item != null) {
			if (sampleForItems.containsKey(item)) {
				return MetaOrganicSample.sampleForItems.get(item);
			}
		}
		return null;
	}
	

	public MetaOrganicSample(int id, String name, DNA dna, Object...objs) {
		this.dna = dna;
		this.id = id;
		this.name = name;
		for (Object obj : objs) {
			ItemStack stack = null;
			if (obj instanceof Item) {
				stack = new ItemStack((Item)obj);
			}else if (obj instanceof Block) {
				stack = new ItemStack((Block)obj);
			}
			if (stack != null) {
				sampleForItems.put(obj, this);
				CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(
						newItemStack(1),
						stack,
						DNACraft.Items.itemGeneric.newItemStack(MetaTestTube.class)
				));
			}
		}
	}
	
	@Override
	public int getIconIndex() {
		return this.id;
	}

	@Override
	public String getItemNameIS(ItemStack stack) {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}

	public ItemStack newItemStack(int count) {
		return new ItemStack(DNACraft.Items.itemGeneric, count, this.getId());
	}

	public ItemStack newItemStack() {
		return newItemStack(1);
	}
	
	public DNA getDNA() {
		return dna;
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
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, EntityPlayer player,
			World world) {
		return itemStack;
	}

	@Override
	public boolean displayInCreative() {
		return false;
	}
}
