package com.bioxx.tfc.api.Util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.settings.KeyBinding;



public class KeyBindings
{
  public static List<KeyBinding> keyBindingsList;
  public static List<Boolean> isRepeatingList;

  public static void addKeyBinding(String name, int value, String category) {
    if (keyBindingsList == null)
      keyBindingsList = new ArrayList<>();
    keyBindingsList.add(new KeyBinding(name, value, category));
  }


  public static void addKeyBinding(KeyBinding binding) {
    if (keyBindingsList == null)
      keyBindingsList = new ArrayList<>();
    keyBindingsList.add(binding);
  }


  public static void addIsRepeating(boolean value) {
    if (isRepeatingList == null)
      isRepeatingList = new ArrayList<>();
    isRepeatingList.add(Boolean.valueOf(value));
  }


  public static KeyBinding[] gatherKeyBindings() {
    return keyBindingsList.<KeyBinding>toArray(new KeyBinding[keyBindingsList.size()]);
  }


  public static boolean[] gatherIsRepeating() {
    boolean[] isRepeating = new boolean[isRepeatingList.size()];
    for (int x = 0; x < isRepeating.length; x++)
      isRepeating[x] = ((Boolean)isRepeatingList.get(x)).booleanValue();
    return isRepeating;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\KeyBindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */