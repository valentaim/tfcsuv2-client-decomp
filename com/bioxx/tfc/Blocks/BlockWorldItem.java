package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import growthcraft.rice.util.RiceBlockCheck;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockWorldItem
  extends BlockTerraContainer
{
  public BlockWorldItem() {
    super(Material.field_151594_q);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }








  public void func_149725_f(World world, int x, int y, int z, int meta) {
    if (!world.field_72995_K) {

      TileEntity te = world.func_147438_o(x, y, z);
      if (te instanceof IInventory) {
        IInventory inv = (IInventory)te;
        for (int i = 0; i < inv.func_70302_i_(); i++) {
          if (inv.func_70301_a(i) != null) {
            EntityItem ei = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, inv.func_70301_a(i));
            inv.func_70299_a(i, null);
            ei.field_70159_w = 0.0D;
            ei.field_70181_x = 0.0D;
            ei.field_70179_y = 0.0D;
            world.func_72838_d((Entity)ei);
          }
        }
      }
    }
    super.func_149725_f(world, x, y, z, meta);
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      return world.func_147468_f(x, y, z);
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (world.func_147437_c(x, y - 1, z)) {

      world.func_147468_f(x, y, z);
      return;
    }
    Block cblock = world.func_147439_a(x, y - 1, z);
    if (!RiceBlockCheck.isPaddy(cblock) &&
      !cblock.isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP)) {

      world.func_147468_f(x, y, z);
      return;
    }
  }



  public boolean func_149686_d() {
    return false;
  }



  public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
    return false;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }



  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    return null;
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.25D, (z + 1));
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_149761_L = TFC_Textures.invisibleTexture;
  }



  public TileEntity createTileEntity(World world, int meta) {
    return (TileEntity)new TEWorldItem();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWorldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */