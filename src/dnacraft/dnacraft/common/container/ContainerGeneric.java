package dnacraft.common.container;

import dnacraft.common.tileentity.BaseInventoryTileEntity;
import dnacraft.common.tileentity.TileEntityElectroporator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerGeneric extends BaseContainer {

	protected IInventory playerInventory;
	protected BaseInventoryTileEntity tileentity;
	
	public static final int[] SLOTS_SEQUENCER = new int[] { 98, 25, 62, 25, 80, 52};
	public static final int[] SLOTS_SPLICER = new int[] { 44, 36,  80, 36,  116, 36 };
	public static final int[] SLOTS_SYNTHESIZER = new int[] { 62, 30, 98, 30 };
	public static final int[] SLOTS_ELECTROPORATOR = new int[] { 53, 27, 80, 27, 107, 27, 80, 53 };
	
	public ContainerGeneric(IInventory playerInventory, BaseInventoryTileEntity tileentity, int[] slots) {
		super(tileentity.getSizeInventory());
		this.playerInventory = playerInventory;
		this.tileentity = tileentity;
		
		for (int i = 0, slotId = 0; i < slots.length; i += 2, slotId++) {
			addSlotToContainer(new Slot(tileentity, slotId, slots[i], slots[i+1]));
		}

		for (int l = 0; l < 3; l++) {
			for (int k1 = 0; k1 < 9; k1++) {
				addSlotToContainer(new Slot(playerInventory, k1 + l * 9 + 9, 8 + k1 * 18, 84 + l * 18));
			}
		}

		for (int i1 = 0; i1 < 9; i1++) {
			addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tileentity.isUseableByPlayer(entityplayer);
	}
	
	public BaseInventoryTileEntity getTileEntity() {
		return this.tileentity;
	}

}
