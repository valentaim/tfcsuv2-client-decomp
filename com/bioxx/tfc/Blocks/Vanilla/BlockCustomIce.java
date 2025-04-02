package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.TFCProvider;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;





public class BlockCustomIce
  extends BlockIce
{
  private IIcon seaIce;
  
  public BlockCustomIce() {
    func_149647_a(TFCTabs.TFC_BUILDING);
  }







  
  public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {}







  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    return world.func_147465_d(x, y, z, getBlockMelt(world, x, y, z, true), 0, 2);
  }

















  
  public void func_149749_a(World world, int i, int j, int k, Block id, int l) {
    super.func_149749_a(world, i, j, k, id, l);
  }




















  
  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    boolean isBeach, hasWater;
    Block plant = plantable.getPlant(world, x, y + 1, z);
    
    if (plant == TFCBlocks.cactus && this == TFCBlocks.cactus) {
      return true;
    }
    if (plant == TFCBlocks.reeds && this == TFCBlocks.reeds) {
      return true;
    }
    int meta = world.func_72805_g(x, y, z);
    if (plantable instanceof BlockCustomLilyPad && ((BlockCustomLilyPad)plant).canThisPlantGrowOnThisBlock((Block)this, meta)) {
      return true;
    }
    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
    switch (plantType) {
      case Desert:
        return TFC_Core.isSand((Block)this);
      case Nether: return (this == Blocks.field_150425_aM);
      case Crop: return TFC_Core.isFarmland((Block)this);
      case Cave: return isSideSolid(world, x, y, z, ForgeDirection.UP);
      case Plains: return (this == TFCBlocks.grass || this == TFCBlocks.grass2 || this == TFCBlocks.dirt || this == TFCBlocks.dirt2);
      case Water: return (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0);
      case Beach:
        isBeach = (TFC_Core.isDirt((Block)this) || TFC_Core.isSand((Block)this));



        
        hasWater = (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h);
        return (isBeach && hasWater);
    } 
    return false;
  }


  
  public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta == 0)
      return 9; 
    return func_149717_k();
  }

  
  protected Block getBlockMelt(World world, int i, int j, int k, boolean moving) {
    Block block = world.func_147439_a(i, j, k);
    
    if (block != this) {
      return block;
    }
    int meta = world.func_72805_g(i, j, k);
    switch (meta) { case 0:
        return TFCBlocks.saltWater;
      case 1: return TFCBlocks.freshWater; }
     return TFCBlocks.saltWater;
  }



  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    this.seaIce = registerer.func_94245_a("terrafirmacraft:seaIce");
    super.func_149651_a(registerer);
  }


  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    if (par2 == 0) {
      return this.seaIce;
    }
    return super.func_149691_a(par1, par2);
  }





  
  public void func_149674_a(World world, int i, int j, int k, Random rand) {
    if (world.field_73011_w instanceof TFCProvider && !world.field_72995_K && world.func_147439_a(i, j, k) == this)
    {






      
      ((TFCProvider)world.field_73011_w).canBlockFreeze(i, j, k, false);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */