package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEToolRack;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;










public class TESRToolrack
  extends TESRBase
{
  public void renderAt(TEToolRack te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {
      
      int dir = te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
      
      EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
      customitem.field_70290_d = 0.0F;
      float blockScale = 1.0F;
      
      for (int i = 0; i < 4; i++) {
        
        if (te.func_70301_a(i) != null) {
          
          float[] loc = getLocation(dir, i);
          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + loc[0], (float)d1 + loc[1], (float)d2 + loc[2]);
          if (RenderManager.field_78727_a.field_78733_k.field_74347_j)
          {
            GL11.glRotatef(loc[3], 0.0F, 1.0F, 0.0F);
          }
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(i));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        } 
      } 
    } 
  }

  
  public float[] getLocation(int dir, int slot) {
    float[] out = new float[4];
    if (dir == 0) {
      
      out[3] = 0.0F;
      if (slot == 0)
      {
        out[0] = 0.25F;
        out[1] = 0.5F;
        out[2] = 0.94F;
      }
      else if (slot == 1)
      {
        out[0] = 0.75F;
        out[1] = 0.5F;
        out[2] = 0.94F;
      }
      else if (slot == 2)
      {
        out[0] = 0.25F;
        out[1] = 0.1F;
        out[2] = 0.94F;
      }
      else if (slot == 3)
      {
        out[0] = 0.75F;
        out[1] = 0.1F;
        out[2] = 0.94F;
      }
    
    } else if (dir == 1) {
      
      out[3] = 270.0F;
      if (slot == 0)
      {
        out[0] = 0.06F;
        out[1] = 0.5F;
        out[2] = 0.25F;
      }
      else if (slot == 1)
      {
        out[0] = 0.06F;
        out[1] = 0.5F;
        out[2] = 0.75F;
      }
      else if (slot == 2)
      {
        out[0] = 0.06F;
        out[1] = 0.1F;
        out[2] = 0.25F;
      }
      else if (slot == 3)
      {
        out[0] = 0.06F;
        out[1] = 0.1F;
        out[2] = 0.75F;
      }
    
    } else if (dir == 2) {
      
      out[3] = 180.0F;
      if (slot == 0)
      {
        out[0] = 0.25F;
        out[1] = 0.5F;
        out[2] = 0.06F;
      }
      else if (slot == 1)
      {
        out[0] = 0.75F;
        out[1] = 0.5F;
        out[2] = 0.06F;
      }
      else if (slot == 2)
      {
        out[0] = 0.25F;
        out[1] = 0.1F;
        out[2] = 0.06F;
      }
      else if (slot == 3)
      {
        out[0] = 0.75F;
        out[1] = 0.1F;
        out[2] = 0.06F;
      }
    
    } else if (dir == 3) {
      
      out[3] = 90.0F;
      if (slot == 0) {
        
        out[0] = 0.94F;
        out[1] = 0.5F;
        out[2] = 0.25F;
      }
      else if (slot == 1) {
        
        out[0] = 0.94F;
        out[1] = 0.5F;
        out[2] = 0.75F;
      }
      else if (slot == 2) {
        
        out[0] = 0.94F;
        out[1] = 0.1F;
        out[2] = 0.25F;
      }
      else if (slot == 3) {
        
        out[0] = 0.94F;
        out[1] = 0.1F;
        out[2] = 0.75F;
      } 
    } 
    return out;
  }


  
  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderAt((TEToolRack)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRToolrack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */