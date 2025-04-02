package com.bioxx.tfc.Blocks;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockMolten
  extends BlockTerra
{
  private IIcon moltenOff;
  
  public BlockMolten() {
    super(Material.field_151573_f);
  }


  
  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if ((meta & 0x8) > 0)
      return 15; 
    return 0;
  }


  
  public void func_149719_a(IBlockAccess access, int i, int j, int k) {
    int meta = access.func_72805_g(i, j, k) & 0x7;
    float f = 0.125F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, f + f * meta, 1.0F);
  }


  
  public IIcon func_149691_a(int side, int meta) {
    if ((meta & 0x8) == 0)
      return this.moltenOff; 
    return this.field_149761_L;
  }


  
  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:devices/Molten Rock");
    this.moltenOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Molten Rock Off");
  }



  
  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {}



  
  public boolean func_149662_c() {
    return false;
  }


  
  public boolean func_149686_d() {
    return false;
  }


  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }


  
  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockMolten.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */