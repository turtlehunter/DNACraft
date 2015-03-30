package dnacraft.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.DNACraft.Items;
import dnacraft.common.block.BlockElectroporator;
import dnacraft.common.block.BlockSequencer;
import dnacraft.common.block.BlockSplicer;
import dnacraft.common.block.BlockSynthesizer;
import dnacraft.common.container.ContainerGeneric;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaBloodSample;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAProfile;
import dnacraft.common.item.metas.MetaMutantEgg;
import dnacraft.common.item.metas.MetaNeedle;
import dnacraft.common.item.metas.MetaOrganicSample;
import dnacraft.common.item.metas.MetaSyringe;
import dnacraft.common.item.metas.MetaTestTube;
import dnacraft.common.tileentity.BaseInventoryTileEntity;
import dnacraft.common.tileentity.TileEntityElectroporator;
import dnacraft.common.tileentity.TileEntitySequencer;
import dnacraft.common.tileentity.TileEntitySplicer;
import dnacraft.common.tileentity.TileEntitySynthesizer;

public class CommonProxy {

	public Object getGui(InventoryPlayer inventory, TileEntity tileentity) {
		return null;
	}
	
	public Object getDNAGui(ItemStack stack) {
		return null;
	}

	public void init() {

		Items.itemUnstackable = new ItemUnstackable();

		Items.itemUnstackable.addMeta(new MetaDNAProfile(0));
		Items.itemUnstackable.addMeta(new MetaMutantEgg(1));
		Items.itemUnstackable.addMeta(new MetaSyringe(2));
		Items.itemUnstackable.addMeta(new MetaBloodSample(3));
		Items.itemUnstackable.addMeta(new MetaDNA(4));

		Items.itemGeneric = new ItemGeneric();

		Items.itemGeneric.addMeta(new MetaTestTube(0));
		Items.itemGeneric.addMeta(new MetaNeedle(1));

		Items.itemGeneric.addMeta(new MetaOrganicSample(30, "dnacraft.samples.pig", DNA.pig, Item.porkRaw, Item.porkCooked));
		Items.itemGeneric.addMeta(new MetaOrganicSample(31, "dnacraft.samples.chicken", DNA.chicken, Item.feather, Item.chickenRaw, Item.chickenCooked));
		Items.itemGeneric.addMeta(new MetaOrganicSample(32, "dnacraft.samples.zombie", DNA.zombie, Item.rottenFlesh));
		Items.itemGeneric.addMeta(new MetaOrganicSample(33, "dnacraft.samples.enderman", DNA.enderman, Item.enderPearl, Item.eyeOfEnder));
		Items.itemGeneric.addMeta(new MetaOrganicSample(34, "dnacraft.samples.spider", DNA.spider, Item.spiderEye, Item.silk));
		Items.itemGeneric.addMeta(new MetaOrganicSample(35, "dnacraft.samples.sheep", DNA.sheep, Item.itemsList[Block.cloth.blockID]));
		Items.itemGeneric.addMeta(new MetaOrganicSample(36, "dnacraft.samples.ocelot", DNA.ocelot, Item.fishRaw, Item.fishCooked));
		Items.itemGeneric.addMeta(new MetaOrganicSample(37, "dnacraft.samples.creeper", DNA.creeper, Item.gunpowder));

		int IDs = 600;

		EntityRegistry.registerModEntity(EntityMutant.class, "Mutant", 600, DNACraft.instance, 64, 1, true);

		setupLanguages();
		addRecipes();
	}

	private void setupLanguages() {

		ArrayList arrayList = new ArrayList();

		try {
			InputStream input = CommonProxy.class.getResourceAsStream("/dnacraft/resources/languages/languages.txt");

			if (input == null) {
				return;
			}

			BufferedReader var2 = new BufferedReader(new InputStreamReader(input, "UTF-8"));

			for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine()) {
				arrayList.add(var3);
			}
		} catch (IOException var5) {
			var5.printStackTrace();
			return;
		}

		Iterator iterator = arrayList.iterator();

		while (iterator.hasNext()) {
			String langString = (String) iterator.next();
			String langPath = "/dnacraft/resources/languages/" + langString + ".lang";
			URL url = CommonProxy.class.getResource(langPath);
			if (url == null) {
				continue;
			}

			LanguageRegistry.instance().loadLocalization(url, langString, false);
		}

	}

	public void addRecipes() {

		ItemGeneric generic = DNACraft.Items.itemGeneric;
		ItemGeneric unstackable = DNACraft.Items.itemUnstackable;

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				generic.newItemStack(MetaTestTube.class, 5),
				new Object[] {
					"g g",
					"g g",
					" g ",
					Character.valueOf('g'), new ItemStack(Block.thinGlass)			
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				generic.newItemStack(MetaNeedle.class, 16),
				new Object[] {
					"i  ",
					" i ",
					"  i",
					Character.valueOf('i'), new ItemStack(Item.ingotIron)			
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				unstackable.newItemStack(MetaSyringe.class),
				new Object[] {
					"n  ",
					" t ",
					"  g",
					Character.valueOf('n'), generic.newItemStack(MetaNeedle.class),
					Character.valueOf('t'), generic.newItemStack(MetaTestTube.class),
					Character.valueOf('g'), new ItemStack(Block.glass)		
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(
				unstackable.newItemStack(MetaSyringe.class),
				unstackable.newItemStack(MetaBloodSample.class)
		));
	}

	public void registerRenderInformation()
	{

	}
}
