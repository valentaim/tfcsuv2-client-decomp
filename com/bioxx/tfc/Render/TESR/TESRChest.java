package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Blocks.Devices.BlockChestTFC;
import com.bioxx.tfc.TileEntities.TEChest;
import com.bioxx.tfc.api.Constant.Global;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;





public class TESRChest
  extends TileEntitySpecialRenderer
{
  private static ResourceLocation[] texNormal;
  private static ResourceLocation[] texNormalDouble;
  private ModelChest chestModel = new ModelChest();


  private ModelChest largeChestModel = (ModelChest)new ModelLargeChest();


  public TESRChest() {
    if (texNormal == null) {

      texNormal = new ResourceLocation[Global.WOOD_ALL.length];
      texNormalDouble = new ResourceLocation[Global.WOOD_ALL.length];
      for (int i = 0; i < Global.WOOD_ALL.length; i++) {

        texNormal[i] = new ResourceLocation("terrafirmacraft:textures/models/chest/normal_" + Global.WOOD_ALL[i] + ".png");
        texNormalDouble[i] = new ResourceLocation("terrafirmacraft:textures/models/chest/normal_double_" + Global.WOOD_ALL[i] + ".png");
      }
    }
  }






  public void renderTileEntityChestAt(TEChest te, double par2, double par4, double par6, float par8) {
    int i;
    if (!te.func_145830_o()) {

      i = 0;
    }
    else {

      Block block = te.func_145838_q();
      i = te.func_145832_p();

      if (block instanceof BlockChestTFC && i == 0) {


        try {
          ((BlockChestTFC)block).unifyAdjacentChests(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
        }
        catch (ClassCastException e) {

          FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", new Object[] {
                Integer.valueOf(te.field_145851_c), Integer.valueOf(te.field_145848_d), Integer.valueOf(te.field_145849_e) });
        }
        i = te.func_145832_p();
      }

      te.func_145979_i();
    }

    if (te.field_145992_i == null && te.field_145991_k == null) {
      ModelChest modelchest;


      if (te.field_145990_j == null && te.field_145988_l == null && !te.isDoubleChest) {

        modelchest = this.chestModel;

        func_147499_a(texNormal[te.type]);
      }
      else {

        modelchest = this.largeChestModel;

        func_147499_a(texNormalDouble[te.type]);
      }

      GL11.glPushMatrix();
      GL11.glEnable(32826);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
      GL11.glScalef(1.0F, -1.0F, -1.0F);
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      short short1 = 0;

      if (i == 2)
      {
        short1 = 180;
      }

      if (i == 3)
      {
        short1 = 0;
      }

      if (i == 4)
      {
        short1 = 90;
      }

      if (i == 5)
      {
        short1 = -90;
      }

      if (i == 2 && te.field_145990_j != null)
      {
        GL11.glTranslatef(1.0F, 0.0F, 0.0F);
      }

      if (i == 5 && te.field_145988_l != null)
      {
        GL11.glTranslatef(0.0F, 0.0F, -1.0F);
      }

      GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

      if (te.isDoubleChest)
      {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
      }

      float f1 = te.field_145986_n + (te.field_145989_m - te.field_145986_n) * par8;


      if (te.field_145992_i != null) {

        float f2 = te.field_145992_i.field_145986_n + (te.field_145992_i.field_145989_m - te.field_145992_i.field_145986_n) * par8;

        if (f2 > f1)
        {
          f1 = f2;
        }
      }

      if (te.field_145991_k != null) {

        float f2 = te.field_145991_k.field_145986_n + (te.field_145991_k.field_145989_m - te.field_145991_k.field_145986_n) * par8;

        if (f2 > f1)
        {
          f1 = f2;
        }
      }

      f1 = 1.0F - f1;
      f1 = 1.0F - f1 * f1 * f1;
      modelchest.field_78234_a.field_78795_f = -(f1 * 3.1415927F / 2.0F);
      modelchest.func_78231_a();
      GL11.glDisable(32826);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
  }



  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderTileEntityChestAt((TEChest)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */