package net.racialgamer.totemandshieldswitcher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TotemAndShieldSwitcher implements ModInitializer {

	private static KeyBinding keyBinding;
	private static boolean keyReleased = true;

	@Override
	public void onInitialize() {

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.totemandshieldswitcher.switch", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_G, // The keycode of the key
				"category.totemandshieldswitcher" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keyBinding.isPressed()) {
				if (keyReleased) {
					SwitchTotemAndShield.switchTotemAndShield();
					keyReleased = false;
				}
			} else {
				keyReleased = true;
			}
		});
	}
}

