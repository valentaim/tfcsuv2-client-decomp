package com.bioxx.tfc.Core.Config;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCOptions;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Field;
import java.util.Collection;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Configuration;












public abstract class SyncingOption
{
  public final String name;
  public final Field field;
  public final boolean defaultValue;
  public final Configuration cfg;
  public final String cat;
  protected boolean ourConfigValue;
  protected boolean currentValue;

  public SyncingOption(String name, Class<?> clazz, Configuration cfg, String cat) throws NoSuchFieldException, IllegalAccessException {
    if (TFC_ConfigFiles.SYNCING_OPTION_MAP.containsKey(name)) throw new IllegalArgumentException("Duplicate key: " + name);
    TFC_ConfigFiles.SYNCING_OPTION_MAP.put(name, this);
    this.name = name;
    this.field = clazz.getDeclaredField(name);
    this.cfg = cfg;
    this.cat = cat;
    this.defaultValue = this.field.getBoolean(null);
  }



  public void apply(boolean enabled) throws IllegalAccessException {
    if (this.currentValue != enabled) {

      boolean result = enabled ? CraftingManager.func_77594_a().func_77592_b().addAll((Collection<? extends IRecipe>)getRecipes()) : CraftingManager.func_77594_a().func_77592_b().removeAll((Collection<?>)getRecipes());
      if (TFCOptions.enableDebugMode) TerraFirmaCraft.LOG.info("Conversion option {} changed from {} to {}. Result: {}", new Object[] { this.name, Boolean.valueOf(this.currentValue), Boolean.valueOf(enabled), Boolean.valueOf(result) });
      this.field.setBoolean(null, enabled);
      this.currentValue = enabled;
    }
  }


  public boolean inConfig() {
    return this.ourConfigValue;
  }


  public boolean isAplied() {
    return this.currentValue;
  }


  public void loadFromConfig() throws IllegalAccessException {
    this.ourConfigValue = this.cfg.getBoolean(this.name, this.cat, this.defaultValue, "");
    apply(this.ourConfigValue);
  }

  public abstract ImmutableList<IRecipe> getRecipes();
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\SyncingOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */