package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
import com.bioxx.tfc.TileEntities.TEAnvil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;











public class TESRAnvil
  extends TESRBase
{
  public void renderAt(TEAnvil te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {

      int dir = BlockAnvil.getDirectionFromMetadata(te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d, te.field_145849_e));

      EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
      customitem.field_70290_d = 0.0F;
      float blockScale = 1.0F;

      drawItem(te, d, d1, d2, dir, customitem, blockScale, 0);
      drawItem(te, d, d1, d2, dir, customitem, blockScale, 1);
    }
  }



  private void drawItem(TEAnvil te, double d, double d1, double d2, int dir, EntityItem customitem, float blockScale, int i) {
    if (te.func_70301_a(i) != null) {

      float[] pos = getLocation(dir, i, (Block.func_149729_e(te.stonePair[0]) != null));
      if (Block.func_149729_e(te.stonePair[0]) != Blocks.field_150350_a)
        pos[1] = pos[1] + 0.3F;
      GL11.glPushMatrix();

      GL11.glTranslatef((float)d + pos[0], (float)d1 + pos[1], (float)d2 + pos[2]);
      if (RenderManager.field_78727_a.field_78733_k.field_74347_j)
        GL11.glRotatef(pos[3], pos[4], pos[5], pos[6]);
      GL11.glScalef(pos[7], pos[8], pos[9]);
      customitem.func_92058_a(te.func_70301_a(i));
      itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

      GL11.glPopMatrix();
    }
  }


  public float[] getLocation(int dir, int slot, boolean isStone) {
    float[] out = new float[10];
    out[7] = 1.0F;
    out[8] = 1.0F;
    out[9] = 1.0F;

    if (dir == 0) {

      out[3] = 90.0F;
      out[4] = 1.0F;
      out[5] = 0.0F;
      out[6] = 0.0F;
      if (slot == 0)
      {
        out[0] = 0.55F;
        out[1] = 0.61F;
        out[2] = 0.45F;
      }
      else if (slot == 1)
      {
        out[0] = 0.55F;
        out[1] = 0.61F;
        out[2] = 0.05F;

        out[7] = 0.8F;
        out[8] = 0.8F;
        out[9] = 0.8F;
      }

    } else if (dir == 1) {

      out[3] = 90.0F;
      out[4] = 1.0F;
      out[5] = 0.0F;
      out[6] = 0.0F;
      if (slot == 0) {

        out[0] = 0.25F;
        out[1] = 0.61F;
        out[2] = 0.25F;
      }
      else if (slot == 1) {

        out[0] = 0.75F;
        out[1] = 0.61F;
        out[2] = 0.25F;

        out[7] = 0.8F;
        out[8] = 0.8F;
        out[9] = 0.8F;
      }
    }
    return out;
  }



  public void func_147500_a(TileEntity te, double xDis, double yDis, double zDis, float f) {
    renderAt((TEAnvil)te, xDis, yDis, zDis, f);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */