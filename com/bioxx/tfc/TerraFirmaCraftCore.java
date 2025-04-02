package com.bioxx.tfc;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import java.util.Arrays;








@MCVersion("1.7.10")
public class TerraFirmaCraftCore
  extends DummyModContainer
{
  @Instance("tfc_coremod")
  public static TerraFirmaCraftCore instance;
  @SidedProxy(clientSide = "com.bioxx.tfc.ClientProxy", serverSide = "com.bioxx.tfc.CommonProxy")
  public static CommonProxy proxy;

  public TerraFirmaCraftCore() {
    super(new ModMetadata());
    ModMetadata meta = getMetadata();
    meta.modId = "tfc_coremod";
    meta.name = "TFC[coremod]";
    meta.version = "0.79.29";
    meta.credits = "";
    meta.authorList = Arrays.asList(new String[] { "Bioxx", "Dunkleosteus" });
    meta.description = "";
    meta.url = "www.terrafirmacraft.com";
    meta.updateUrl = "";
    meta.screenshots = new String[0];
    meta.logoFile = "";
  }


  public boolean registerBus(EventBus bus, LoadController controller) {
    bus.register(this);
    return true;
  }


  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    instance = this;
  }

  @EventHandler
  public void initialize(FMLInitializationEvent evt) {}

  @EventHandler
  public void modsLoaded(FMLPostInitializationEvent evt) {}

  @EventHandler
  public void serverStarting(FMLServerStartingEvent evt) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TerraFirmaCraftCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */