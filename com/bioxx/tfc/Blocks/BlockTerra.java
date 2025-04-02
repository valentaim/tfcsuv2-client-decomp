package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;




public abstract class BlockTerra
  extends Block
{
  protected BlockTerra() {
    super(Material.field_151576_e);
  }


  protected BlockTerra(Material material) {
    super(material);
  }




  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
    }
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
    }
    return false;
  }


  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
    func_149689_a(world, x, y, z, (EntityLivingBase)entityliving, (ItemStack)null);
  }



  public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
    super.func_149636_a(world, player, x, y, z, meta);
    TFC_Core.addPlayerExhaustion(player, 0.001F);
  }


  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    boolean isBeach, hasWater;
    Block plant = plantable.getPlant(world, x, y + 1, z);
    if (plant == Blocks.field_150434_aF && this == Blocks.field_150434_aF)
    {
      return true;
    }

    if (plant == Blocks.field_150436_aH && this == Blocks.field_150436_aH)
    {
      return true;
    }

    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
    switch (plantType) {
      case Cave:
        return isSideSolid(world, x, y, z, ForgeDirection.UP);
      case Plains: return (TFC_Core.isSoil(this) || TFC_Core.isFarmland(this));
      case Water: return (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0);
      case Beach:
        isBeach = (TFC_Core.isSand(this) || TFC_Core.isGravel(this));



        hasWater = (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h);
        return (isBeach && hasWater);
    }  return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockTerra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */