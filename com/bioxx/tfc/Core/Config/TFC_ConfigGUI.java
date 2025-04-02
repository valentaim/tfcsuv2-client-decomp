package com.bioxx.tfc.Core.Config;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;




public class TFC_ConfigGUI
  extends GuiConfig
{
  public TFC_ConfigGUI(GuiScreen parent) {
    super(parent, getConfigElements(), "terrafirmacraft", false, false, "TerraFirmaCraft");
  }



  private static List<IConfigElement> getConfigElements() {
    List<IConfigElement> root = new ArrayList<>();
    root.add(new DummyConfigElement.DummyCategoryElement("TFCOptions.cfg", "config.gui.TFCConfig", getAllFrom(TFC_ConfigFiles.getGeneralConfig())));
    root.add(new DummyConfigElement.DummyCategoryElement("TFCCrafting.cfg", "config.gui.TFCCrafting", getAllFrom(TFC_ConfigFiles.getCraftingConfig())));
    root.add(new DummyConfigElement.DummyCategoryElement("TFCOre.cfg", "config.gui.TFCOre", getAllFrom(TFC_ConfigFiles.getOresConfig())));
    return root;
  }



  private static List<IConfigElement> getAllFrom(Configuration generalConfig) {
    List<IConfigElement> root = new ArrayList<>();
    for (String catName : generalConfig.getCategoryNames()) {

      if (catName.contains("."))
        continue;  root.add(new ConfigElement(generalConfig.getCategory(catName)));
    }
    return root;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\TFC_ConfigGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */