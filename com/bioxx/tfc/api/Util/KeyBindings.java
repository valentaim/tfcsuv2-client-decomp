/*    */ package com.bioxx.tfc.api.Util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyBindings
/*    */ {
/*    */   public static List<KeyBinding> keyBindingsList;
/*    */   public static List<Boolean> isRepeatingList;
/*    */   
/*    */   public static void addKeyBinding(String name, int value, String category) {
/* 15 */     if (keyBindingsList == null)
/* 16 */       keyBindingsList = new ArrayList<>(); 
/* 17 */     keyBindingsList.add(new KeyBinding(name, value, category));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addKeyBinding(KeyBinding binding) {
/* 22 */     if (keyBindingsList == null)
/* 23 */       keyBindingsList = new ArrayList<>(); 
/* 24 */     keyBindingsList.add(binding);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addIsRepeating(boolean value) {
/* 29 */     if (isRepeatingList == null)
/* 30 */       isRepeatingList = new ArrayList<>(); 
/* 31 */     isRepeatingList.add(Boolean.valueOf(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public static KeyBinding[] gatherKeyBindings() {
/* 36 */     return keyBindingsList.<KeyBinding>toArray(new KeyBinding[keyBindingsList.size()]);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean[] gatherIsRepeating() {
/* 41 */     boolean[] isRepeating = new boolean[isRepeatingList.size()];
/* 42 */     for (int x = 0; x < isRepeating.length; x++)
/* 43 */       isRepeating[x] = ((Boolean)isRepeatingList.get(x)).booleanValue(); 
/* 44 */     return isRepeating;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\KeyBindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */