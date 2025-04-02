package com.bioxx.tfc;

import com.bioxx.tfc.ASM.Transform.TF_EntityLeashKnot;
import com.bioxx.tfc.ASM.Transform.TF_EntityPlayer;
import com.bioxx.tfc.ASM.Transform.TF_EntityPlayerMP;
import com.bioxx.tfc.ASM.Transform.TF_EntityRenderer;
import com.bioxx.tfc.ASM.Transform.TF_RenderGlobal;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import java.io.File;
import java.util.Map;



@TransformerExclusions({"com.bioxx.tfc.ASM"})
public class TFCASMLoadingPlugin
  implements IFMLLoadingPlugin
{
  public static boolean runtimeDeobf;
  public static File location;

  public String getAccessTransformerClass() {
    return null;
  }


  public String[] getASMTransformerClass() {
    return new String[] { TF_EntityRenderer.class
        .getName(), TF_RenderGlobal.class
        .getName(), TF_EntityLeashKnot.class
        .getName(), TF_EntityPlayerMP.class
        .getName(), TF_EntityPlayer.class
        .getName() };
  }


  public String getModContainerClass() {
    return TerraFirmaCraftCore.class.getName();
  }


  public String getSetupClass() {
    return null;
  }


  public void injectData(Map<String, Object> data) {
    runtimeDeobf = ((Boolean)data.get("runtimeDeobfuscationEnabled")).booleanValue();
    location = (File)data.get("coremodLocation");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TFCASMLoadingPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */