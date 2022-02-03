package net.codersdownunder.gemmod.blocks.infusion;

import net.codersdownunder.gemmod.init.BlockInit;
import net.codersdownunder.gemmod.init.MenuInit;
import net.codersdownunder.gemmod.utils.slots.GenericSlot;
import net.codersdownunder.gemmod.utils.slots.OutputSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class InfusionTableMenu extends AbstractContainerMenu {

    private BlockEntity tileEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;
    private InfusionTableBlockEntity table;
    private BlockPos pos;

    public static final int PLAYER_INVENTORY_XPOS = -5;
    public static final int PLAYER_INVENTORY_YPOS = 113;
    
//    public static final int INPUT_SLOTS_COUNT = 6;
//    public static final int OUTPUT_SLOTS_COUNT = 1;
//    public static final int FURNACE_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;
    
    public static int id;
    private static int CONTAINER_SIZE = 6;
    
    public InfusionTableMenu() {
        super(MenuInit.INFUSION_TABLE_CONTAINER.get(), id);
    }
    
    public InfusionTableMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player)  {
        super(MenuInit.INFUSION_TABLE_CONTAINER.get(), windowId);
        InfusionTableMenu.id = windowId;
        tileEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.pos = pos;


        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            	
            	//far right and left
            	addSlot(new GenericSlot(h, 5, 130, 18));
                addSlot(new GenericSlot(h, 0, 4, 18));
                
                
                // right slots
                addSlot(new GenericSlot(h, 2, 103, 36));
                addSlot(new GenericSlot(h, 3, 103, 0));
                
                // left slots
                addSlot(new GenericSlot(h, 1, 31, 36));
                addSlot(new GenericSlot(h, 4, 31, 0));
                
                //base item slot
                addSlot(new GenericSlot(h, 6, 67, 18));
                
                //output
                addSlot(new OutputSlot(h, 7, 67, 63));
            });
        }

        
        layoutPlayerInventorySlots(PLAYER_INVENTORY_XPOS, PLAYER_INVENTORY_YPOS);
    }
    
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.slots.get(pIndex);
	      if (slot != null && slot.hasItem()) {
	         ItemStack itemstack1 = slot.getItem();
	         itemstack = itemstack1.copy();
	         if (pIndex < InfusionTableMenu.CONTAINER_SIZE) {
	            if (!this.moveItemStackTo(itemstack1, InfusionTableMenu.CONTAINER_SIZE, this.slots.size(), true)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.moveItemStackTo(itemstack1, 0, InfusionTableMenu.CONTAINER_SIZE, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.set(ItemStack.EMPTY);
	         } else {
	            slot.setChanged();
	         }
	      }

	      return itemstack;
	   }
    
    public BlockPos getPos() {
        return this.pos;
    }


    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, BlockInit.INFUSION_TABLE.get());
    }

    
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
    
    public InfusionTableBlockEntity getTable()
    {
        return table;
    }


}