package com.bioxx.tfc.Render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;


@SideOnly(Side.CLIENT)
public class EntityRendererTFC
  extends EntityRenderer
{
  private boolean allowShaderSwitching = true;
  private ResourceLocation currentShader;

  public EntityRendererTFC(Minecraft minecraft, IResourceManager irm) {
    super(minecraft, irm);
  }



  public void func_147703_b() {
    if (this.allowShaderSwitching) {
      super.func_147705_c();
    }
  }

  public void setManualShader(ResourceLocation shaderLocation) {
    deactivateManualShader();
    try {
      Minecraft mc = Minecraft.func_71410_x();
      this.field_147707_d = new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shaderLocation);
      this.field_147707_d.func_148026_a(mc.field_71443_c, mc.field_71440_d);
      this.currentShader = shaderLocation;
    }
    catch (IOException ioexception) {

      LogManager.getLogger().warn("Failed to load shader: " + shaderLocation, ioexception);
    }
    this.allowShaderSwitching = false;
  }

  public void deactivateManualShader() {
    this.allowShaderSwitching = true;
    super.func_147703_b();
  }

  public ResourceLocation getCurrentShaderLocation() {
    return this.currentShader;
  }

  public boolean getManualShaderBeingUsed() {
    return !this.allowShaderSwitching;
  }



  public void func_147705_c() {
    if (this.allowShaderSwitching)
      super.func_147705_c();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\EntityRendererTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */