package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEHopper;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;














public class TESRHopper
  extends TESRBase
{
  public void renderAt(TEHopper te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null)
    {
      if (te.pressBlock != null) {
        
        float sink = -0.34F + (te.pressCooldown / 20) / 800.0F * 0.25F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.33F, (float)d2 + 0.5F);
        GL11.glScalef(0.75F, 0.75F, 0.75F);
        GL11.glTranslatef(0.0F, sink, 0.0F);
        TFC_Core.bindTexture(TextureMap.field_110575_b);
        GL11.glDisable(2896);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        TESRBase.renderBlocks.func_147775_a(TFCBlocks.stoneIgInSmooth);
        renderPress(Block.func_149634_a(te.pressBlock.func_77973_b()), te.func_145831_w(), (int)d, (int)d1, (int)d2, te.pressBlock.func_77960_j());
        GL11.glEnable(2896);
        GL11.glPopMatrix();
      } 
    }
  }

  
  public void renderPress(Block block, World world, int x, int y, int z, int meta) {
    float f = 0.5F;
    float f1 = 1.0F;
    float f2 = 0.8F;
    float f3 = 0.6F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78386_a(f, f, f);
    renderBlocks.func_147768_a(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 0, meta));
    tessellator.func_78386_a(f1, f1, f1);
    renderBlocks.func_147806_b(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 1, meta));
    tessellator.func_78386_a(f2, f2, f2);
    renderBlocks.func_147761_c(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 2, meta));
    tessellator.func_78386_a(f2, f2, f2);
    renderBlocks.func_147734_d(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 3, meta));
    tessellator.func_78386_a(f3, f3, f3);
    renderBlocks.func_147798_e(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 4, meta));
    tessellator.func_78386_a(f3, f3, f3);
    renderBlocks.func_147764_f(block, -0.5D, -0.5D, -0.5D, renderBlocks.func_147787_a(block, 5, meta));
    tessellator.func_78381_a();
  }




  
  public void func_147500_a(TileEntity te, double xDis, double yDis, double zDis, float f) {
    renderAt((TEHopper)te, xDis, yDis, zDis, f);
  }

  
  private class ModelPress
    extends ModelBase
  {
    private ModelBox pressModel;
    
    private ModelRenderer renderer;
    
    public ModelPress() {
      this.renderer = new ModelRenderer(this);
      
      this.pressModel = new ModelBox(this.renderer, 0, 0, 0.5F, 0.0F, 0.5F, 15, 4, 7, 1.0F);
      this.renderer.field_78804_l.add(this.pressModel);
    }



    
    public void func_78088_a(Entity entity, float x, float y, float z, float maxX, float maxY, float maxZ) {
      this.renderer = new ModelRenderer(this);
      
      this.pressModel = new ModelBox(this.renderer, 0, 0, 2.0F, 16.0F, 2.0F, 12, 12, 12, 0.0F);
      this.renderer.field_78804_l.add(this.pressModel);

      
      this.renderer.func_78785_a(0.0625F);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */