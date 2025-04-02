package com.bioxx.tfc.Blocks.Terrain;

import net.minecraft.client.renderer.texture.IIconRegister;









public class BlockMMBrick
  extends BlockMMSmooth
{
  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < this.names.length; i++)
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Brick");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockMMBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */