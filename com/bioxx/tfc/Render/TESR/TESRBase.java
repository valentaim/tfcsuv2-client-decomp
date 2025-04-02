package com.bioxx.tfc.Render.TESR;

import com.google.common.primitives.SignedBytes;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;




public class TESRBase
  extends TileEntitySpecialRenderer
{
  public TESRBase() {
    itemRenderer = new RenderItem()
      {

        public byte getMiniBlockCount(ItemStack stack, byte original)
        {
          return SignedBytes.saturatedCast((Math.min(stack.field_77994_a / 32, 15) + 1));
        }



        public byte getMiniItemCount(ItemStack stack, byte original) {
          return SignedBytes.saturatedCast((Math.min(stack.field_77994_a / 32, 7) + 1));
        }



        public boolean shouldBob() {
          return true;
        }



        public boolean shouldSpreadItems() {
          return false;
        }
      };
    itemRenderer.func_76976_a(RenderManager.field_78727_a);
  }

  protected static RenderBlocks renderBlocks = new RenderBlocks();
  protected static RenderItem itemRenderer;

  public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */