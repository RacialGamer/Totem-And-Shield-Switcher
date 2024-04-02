// SwitchTotemAndShield.java
package net.racialgamer.totemandshieldswitcher;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class SwitchTotemAndShield {

    public static void switchTotemAndShield() {
        // Get the itemstack in the offhand slot
        assert MinecraftClient.getInstance().player != null;
        ItemStack offhandItemStack = MinecraftClient.getInstance().player.getInventory().getStack(40);

        if (InventoryHelper.isTotem(offhandItemStack)) {
            // If the offhand slot contains a totem, find a shield in the inventory and swap it with the totem
            int shieldSlot = InventoryHelper.findItemInInventory(Items.SHIELD);
            if (shieldSlot >= 0) {
                sendSwapPackets(shieldSlot);
            }
        } else if (InventoryHelper.isShield(offhandItemStack)) {
            // If the offhand slot contains a shield, find a totem in the inventory and swap it with the shield
            int totemSlot = InventoryHelper.findItemInInventory(Items.TOTEM_OF_UNDYING);
            if (totemSlot >= 0) {
                sendSwapPackets(totemSlot);
            }
        }
    }

    private static void sendSwapPackets(int slot) {
        int sentSlot = slot;
        if (sentSlot == PlayerInventory.OFF_HAND_SLOT) sentSlot += 5; // Off Hand offset
        if(sentSlot < PlayerInventory.getHotbarSize()) sentSlot += PlayerInventory.MAIN_SIZE;   // Toolbar offset

        // Take the item to swap to
        assert MinecraftClient.getInstance().interactionManager != null;
        MinecraftClient.getInstance().interactionManager.clickSlot(0, sentSlot, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);

        // Put it in the offhand slot
        MinecraftClient.getInstance().interactionManager.clickSlot(0, 45, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);

        // Put back what was in the offhand slot (can be air)
        MinecraftClient.getInstance().interactionManager.clickSlot(0, sentSlot, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);
    }
}