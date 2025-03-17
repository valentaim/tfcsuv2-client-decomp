/*    */ package com.bioxx.tfc.Core.Config;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import com.bioxx.tfc.api.TFCOptions;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.item.crafting.CraftingManager;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraftforge.common.config.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SyncingOption
/*    */ {
/*    */   public final String name;
/*    */   public final Field field;
/*    */   public final boolean defaultValue;
/*    */   public final Configuration cfg;
/*    */   public final String cat;
/*    */   protected boolean ourConfigValue;
/*    */   protected boolean currentValue;
/*    */   
/*    */   public SyncingOption(String name, Class<?> clazz, Configuration cfg, String cat) throws NoSuchFieldException, IllegalAccessException {
/* 34 */     if (TFC_ConfigFiles.SYNCING_OPTION_MAP.containsKey(name)) throw new IllegalArgumentException("Duplicate key: " + name); 
/* 35 */     TFC_ConfigFiles.SYNCING_OPTION_MAP.put(name, this);
/* 36 */     this.name = name;
/* 37 */     this.field = clazz.getDeclaredField(name);
/* 38 */     this.cfg = cfg;
/* 39 */     this.cat = cat;
/* 40 */     this.defaultValue = this.field.getBoolean(null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(boolean enabled) throws IllegalAccessException {
/* 46 */     if (this.currentValue != enabled) {
/*    */       
/* 48 */       boolean result = enabled ? CraftingManager.func_77594_a().func_77592_b().addAll((Collection<? extends IRecipe>)getRecipes()) : CraftingManager.func_77594_a().func_77592_b().removeAll((Collection<?>)getRecipes());
/* 49 */       if (TFCOptions.enableDebugMode) TerraFirmaCraft.LOG.info("Conversion option {} changed from {} to {}. Result: {}", new Object[] { this.name, Boolean.valueOf(this.currentValue), Boolean.valueOf(enabled), Boolean.valueOf(result) }); 
/* 50 */       this.field.setBoolean(null, enabled);
/* 51 */       this.currentValue = enabled;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean inConfig() {
/* 57 */     return this.ourConfigValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAplied() {
/* 62 */     return this.currentValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadFromConfig() throws IllegalAccessException {
/* 67 */     this.ourConfigValue = this.cfg.getBoolean(this.name, this.cat, this.defaultValue, "");
/* 68 */     apply(this.ourConfigValue);
/*    */   }
/*    */   
/*    */   public abstract ImmutableList<IRecipe> getRecipes();
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\SyncingOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */