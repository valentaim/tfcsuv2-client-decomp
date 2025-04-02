package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEQuern;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;



public class TESRQuern
  extends TESRBase
  implements ISimpleBlockRenderingHandler
{
  private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Base.png");
  private static final ResourceLocation TOP1_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Top 1.png");
  private static final ResourceLocation TOP2_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Top 2.png");
  private static final ResourceLocation WOOD_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/wood/Oak Plank.png");


  
  public void func_147500_a(TileEntity te, double xDis, double yDis, double zDis, float f) {
    TEQuern teq = (TEQuern)te;
    
    Tessellator tess = Tessellator.field_78398_a;
    GL11.glPushMatrix();
    
    GL11.glTranslatef((float)xDis, (float)yDis, (float)zDis);
    renderBase(tess);
    if (teq.hasQuern) {




      
      renderRoundTop(tess, teq.rotatetimer, (teq.func_145831_w()).field_73012_v, 0.8D, Boolean.valueOf(true));

      
      renderWoodHandle(tess, teq.rotatetimer, (teq.func_145831_w()).field_73012_v, 0.8D);
      
      if (teq.storage[0] != null) renderItem(teq.storage[0]);
    
    } 
    GL11.glPopMatrix();
  }

  
  private void renderItem(ItemStack is) {
    EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
    float blockScale = 0.7F;
    float timeD = (float)-(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);
    
    func_147499_a(TextureMap.field_110576_c);
    customitem.func_92058_a(is);
    customitem.field_70290_d = 0.0F;
    
    GL11.glTranslatef(0.5F, 0.83F, 0.5F);
    GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
    GL11.glScalef(blockScale, blockScale, blockScale);
    itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
  }

  
  private void renderRoundTop(Tessellator t, int pos, Random rand, double angle, Boolean renderSides) {
    int sides = 4;
    double speed = (pos * 4);
    double i = 0.625D;
    double j = i + 0.2D;
    if (!renderSides.booleanValue()) j = i + 0.201D;
    
    double center = 0.5D;
    double rad = 0.5D;

    
    if (pos > 0) {
      center = 0.494D + rand.nextDouble() * 0.006D + 0.003D;
    }
    for (int l = 0; l < sides; l++) {
      
      double a = ((l * 360 / sides) + speed + (4 * pos)) * Math.PI / 180.0D;
      double b = (((1 + l) * 360 / sides) + speed + (4 * pos)) * Math.PI / 180.0D;
      double x1 = Math.cos(a + angle) * rad + center;
      double y1 = Math.sin(a + angle) * rad + center;
      double x2 = Math.cos(b + angle) * rad + center;
      double y2 = Math.sin(b + angle) * rad + center;

      
      a = (l * 360 / sides) * Math.PI / 180.0D;
      b = ((1 + l) * 360 / sides) * Math.PI / 180.0D;
      double xx1 = Math.cos(a + angle) * rad + center;
      double yy1 = Math.sin(a + angle) * rad + center;
      double xx2 = Math.cos(b + angle) * rad + center;
      double yy2 = Math.sin(b + angle) * rad + center;
      
      func_147499_a(TOP2_TEXTURE);
      t.func_78371_b(4);
      t.func_78374_a(x1, j, y1, xx1, yy1);
      t.func_78374_a(center, j, center, center, center);
      t.func_78374_a(x2, j, y2, xx2, yy2);
      t.func_78381_a();
      
      if (renderSides.booleanValue()) {
        
        func_147499_a(BASE_TEXTURE);
        t.func_78382_b();
        t.func_78374_a(x1, i, y1, 0.73D, 1.0D - j);
        t.func_78374_a(x1, j, y1, 0.73D, 0.0D);
        t.func_78374_a(x2, j, y2, 0.0D, 0.0D);
        t.func_78374_a(x2, i, y2, 0.0D, 1.0D - j);
        t.func_78381_a();
      } 
    } 
  }






































  
  private void renderWoodHandle(Tessellator t, int pos, Random rand, double angle) {
    double speed = (pos * 4);
    double j = 0.825D;
    double k = j + 0.175D;
    double center = 0.5D;
    double rad = 0.5D;

    
    double a = ((pos * 4 - 5) + speed) * Math.PI / 180.0D;
    double a1 = ((pos * 4) - 5.7D + speed) * Math.PI / 180.0D;
    double b = ((pos * 4 + 5) + speed) * Math.PI / 180.0D;
    double b1 = ((pos * 4) + 5.7D + speed) * Math.PI / 180.0D;
    double x1 = Math.cos(a + angle) * (rad - 0.05D) + center;
    double y1 = Math.sin(a + angle) * (rad - 0.05D) + center;
    double xx1 = Math.cos(a1 + angle) * (rad - 0.125D) + center;
    double yy1 = Math.sin(a1 + angle) * (rad - 0.125D) + center;
    double x2 = Math.cos(b + angle) * (rad - 0.05D) + center;
    double y2 = Math.sin(b + angle) * (rad - 0.05D) + center;
    double xx2 = Math.cos(b1 + angle) * (rad - 0.125D) + center;
    double yy2 = Math.sin(b1 + angle) * (rad - 0.125D) + center;
    
    func_147499_a(WOOD_TEXTURE);
    
    t.func_78382_b();
    t.func_78374_a(x1, j, y1, 0.0D, 0.0D);
    t.func_78374_a(xx1, j, yy1, 0.0D, 0.2D);
    t.func_78374_a(xx1, k, yy1, 0.2D, 0.2D);
    t.func_78374_a(x1, k, y1, 0.2D, 0.0D);
    t.func_78381_a();
    
    t.func_78382_b();
    t.func_78374_a(xx1, j, yy1, 0.0D, 0.0D);
    t.func_78374_a(xx2, j, yy2, 0.0D, 0.2D);
    t.func_78374_a(xx2, k, yy2, 0.2D, 0.2D);
    t.func_78374_a(xx1, k, yy1, 0.2D, 0.0D);
    t.func_78381_a();
    
    t.func_78382_b();
    t.func_78374_a(xx2, j, yy2, 0.0D, 0.0D);
    t.func_78374_a(x2, j, y2, 0.0D, 0.2D);
    t.func_78374_a(x2, k, y2, 0.2D, 0.2D);
    t.func_78374_a(xx2, k, yy2, 0.2D, 0.0D);
    t.func_78381_a();
    
    t.func_78382_b();
    t.func_78374_a(x2, j, y2, 0.0D, 0.0D);
    t.func_78374_a(x1, j, y1, 0.0D, 0.2D);
    t.func_78374_a(x1, k, y1, 0.2D, 0.2D);
    t.func_78374_a(x2, k, y2, 0.2D, 0.0D);
    t.func_78381_a();
    
    t.func_78382_b();
    t.func_78374_a(x1, k, y1, 0.0D, 0.0D);
    t.func_78374_a(xx1, k, yy1, 0.0D, 0.2D);
    t.func_78374_a(xx2, k, yy2, 0.2D, 0.2D);
    t.func_78374_a(x2, k, y2, 0.2D, 0.0D);
    t.func_78381_a();
  }

  
  private void renderBase(Tessellator t) {
    double i = 0.625D;
    func_147499_a(BASE_TEXTURE);
    t.func_78382_b();
    
    t.func_78374_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D);
    t.func_78374_a(0.0D, i, 0.0D, 1.0D, 1.0D - i);
    t.func_78374_a(1.0D, i, 0.0D, 0.0D, 1.0D - i);
    t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
    
    t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 1.0D);
    t.func_78374_a(1.0D, i, 0.0D, 1.0D, 1.0D - i);
    t.func_78374_a(1.0D, i, 1.0D, 0.0D, 1.0D - i);
    t.func_78374_a(1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
    
    t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    t.func_78374_a(1.0D, i, 1.0D, 1.0D, 1.0D - i);
    t.func_78374_a(0.0D, i, 1.0D, 0.0D, 1.0D - i);
    t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 1.0D);
    
    t.func_78374_a(0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    t.func_78374_a(0.0D, i, 1.0D, 1.0D, 1.0D - i);
    t.func_78374_a(0.0D, i, 0.0D, 0.0D, 1.0D - i);
    t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
    t.func_78381_a();
    
    func_147499_a(TOP2_TEXTURE);
    t.func_78382_b();
    t.func_78374_a(0.0D, i, 0.0D, 0.0D, 0.0D);
    t.func_78374_a(0.0D, i, 1.0D, 0.0D, 1.0D);
    t.func_78374_a(1.0D, i, 1.0D, 1.0D, 1.0D);
    t.func_78374_a(1.0D, i, 0.0D, 1.0D, 0.0D);
    t.func_78381_a();
    
    func_147499_a(TOP1_TEXTURE);
    t.func_78382_b();
    t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
    t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    t.func_78374_a(0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
    t.func_78381_a();
  }


  
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
    return false;
  }


  
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
    Tessellator var14 = Tessellator.field_78398_a;
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 1));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
    var14.func_78381_a();
  }


  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }


  
  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */