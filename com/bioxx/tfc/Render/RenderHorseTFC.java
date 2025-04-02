package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderHorseTFC
  extends RenderHorse
{
  private static final Map<String, ResourceLocation> TEXTURE_MAP = Maps.newHashMap();
  private static final ResourceLocation WHITE_TEXTURE = new ResourceLocation("textures/entity/horse/horse_white.png");
  private static final ResourceLocation MULE_TEXTURE = new ResourceLocation("textures/entity/horse/mule.png");
  private static final ResourceLocation DONKEY_TEXTURE = new ResourceLocation("textures/entity/horse/donkey.png");
  private static final ResourceLocation ZOMBIE_TEXTURE = new ResourceLocation("textures/entity/horse/horse_zombie.png");
  private static final ResourceLocation SKELETON_TEXTURE = new ResourceLocation("textures/entity/horse/horse_skeleton.png");


  public RenderHorseTFC(ModelBase par1ModelBase, float par2) {
    super(par1ModelBase, par2);
  }


  protected void renderHorse(EntityHorse par1EntityHorse, float par2, float par3, float par4, float par5, float par6, float par7) {
    if (par1EntityHorse.func_82150_aj()) {

      this.field_77045_g.func_78087_a(par2, par3, par4, par5, par6, par7, (Entity)par1EntityHorse);
    }
    else {

      func_110777_b((Entity)par1EntityHorse);
      this.field_77045_g.func_78088_a((Entity)par1EntityHorse, par2, par3, par4, par5, par6, par7);
    }
  }


  protected ResourceLocation getTextureLocation(EntityHorse par1EntityHorse) {
    if (!par1EntityHorse.func_110239_cn()) {

      switch (par1EntityHorse.func_110265_bP())

      {
        default:
          return WHITE_TEXTURE;
        case 1:
          return DONKEY_TEXTURE;
        case 2:
          return MULE_TEXTURE;
        case 3:
          return ZOMBIE_TEXTURE;
        case 4:
          break; }  return SKELETON_TEXTURE;
    }



    return getTexture(par1EntityHorse);
  }



  private ResourceLocation getTexture(EntityHorse par1EntityHorse) {
    String s = par1EntityHorse.func_110264_co();
    ResourceLocation resourcelocation = TEXTURE_MAP.get(s);

    if (resourcelocation == null) {

      resourcelocation = new ResourceLocation(s);
      Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, (ITextureObject)new LayeredTexture(par1EntityHorse.func_110212_cp()));
      TEXTURE_MAP.put(s, resourcelocation);
    }

    return resourcelocation;
  }







  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
    float scale = ((EntityHorseTFC)par1EntityLivingBase).getSizeMod();
    GL11.glScalef(scale, scale, scale);
    func_77041_b((EntityHorse)par1EntityLivingBase, par2);
  }






  protected void func_77036_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
    renderHorse((EntityHorse)par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
  }






  protected ResourceLocation func_110775_a(Entity par1Entity) {
    return getTextureLocation((EntityHorse)par1Entity);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */