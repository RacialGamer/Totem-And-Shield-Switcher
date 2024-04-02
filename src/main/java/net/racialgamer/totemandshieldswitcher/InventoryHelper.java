package net.racialgamer.totemandshieldswitcher;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class InventoryHelper {

    public static boolean isTotem(ItemStack stack) {
        return stack.getItem() == Items.TOTEM_OF_UNDYING;
    }

    public static boolean isShield(ItemStack stack) {
        return stack.getItem() == Items.SHIELD;
    }

    public static int findItemInInventory(Item itemToFind) {
        assert MinecraftClient.getInstance().player != null;
        PlayerInventory inventory = MinecraftClient.getInstance().player.getInventory();
        for (int slot = 9; slot <= 35; slot++) {
            ItemStack stack = inventory.getStack(slot);
            if (stack.getItem() == itemToFind) {
                return slot;
            }
        }
        return -1; // Return -1 if the item was not found in the inventory
    }
}
