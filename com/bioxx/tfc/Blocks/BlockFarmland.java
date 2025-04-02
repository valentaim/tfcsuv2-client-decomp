package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFarmland;
import com.bioxx.tfc.api.Constant.Global;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;




public class BlockFarmland
  extends BlockContainer
{
  private Block dirtBlock;
  private IIcon[] dirtTexture;
  private int textureOffset;

  public BlockFarmland(Block block, int tex) {
    super(Material.field_151578_c);
    func_149675_a(true);
    this.dirtBlock = block;
    this.textureOffset = tex;
    func_149647_a(TFCTabs.TFC_BUILDING);
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    int count = (this.textureOffset == 0) ? 16 : (Global.STONE_ALL.length - 16);
    this.dirtTexture = new IIcon[count];
    for (int i = 0; i < count; i++) {
      this.dirtTexture[i] = registerer.func_94245_a("terrafirmacraft:farmland/Farmland " + Global.STONE_ALL[i + this.textureOffset]);
    }
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    Boolean addToCreative = Boolean.valueOf(true);

    if (addToCreative.booleanValue()) {
      int count;

      if (this.textureOffset == 0) { count = 16; }
      else { count = Global.STONE_ALL.length - 16; }

      for (int i = 0; i < count; i++) {
        list.add(new ItemStack(item, 1, i));
      }
    }
  }


  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
    int meta = access.func_72805_g(x, y, z);
    if (meta < 0 || meta >= this.dirtTexture.length)
      meta = 0;
    if (side == 1) {
      return this.dirtTexture[meta];
    }
    return this.dirtBlock.func_149691_a(side, meta);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta < 0 || meta >= this.dirtTexture.length)
      meta = 0;
    if (side == ForgeDirection.UP.ordinal()) {
      return this.dirtTexture[meta];
    }
    return this.dirtBlock.func_149691_a(0, meta);
  }



  public int func_149692_a(int dmg) {
    return dmg;
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a((x + 0), (y + 0), (z + 0), (x + 1), (y + 1), (z + 1));
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return Item.func_150899_d(0);
  }























  public static boolean isFreshWaterNearby(World world, int i, int j, int k) {
    for (int x = i - 4; x <= i + 4; x++) {

      for (int y = j; y <= j + 1; y++) {

        for (int z = k - 4; z <= k + 4; z++) {

          if (world.func_72899_e(x, y, z)) {

            Block b = world.func_147439_a(x, y, z);
            if (TFC_Core.isFreshWater(b))
              return true;
          }
        }
      }
    }
    return false;
  }


  public static boolean isSaltWaterNearby(World world, int i, int j, int k) {
    for (int x = i - 4; x <= i + 4; x++) {

      for (int y = j; y <= j + 1; y++) {

        for (int z = k - 4; z <= k + 4; z++) {

          Block b = world.func_147439_a(x, y, z);
          if (TFC_Core.isSaltWater(b))
            return true;
        }
      }
    }
    return false;
  }



  public boolean func_149662_c() {
    return true;
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEFarmland();
  }



  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    Block plant = plantable.getPlant(world, x, y + 1, z);
    if (plant == Blocks.field_150393_bb || plant == Blocks.field_150394_bc) {
      return false;
    }
    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
    if (plantType == EnumPlantType.Crop) {
      return true;
    }
    return super.canSustainPlant(world, x, y, z, direction, plantable);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */