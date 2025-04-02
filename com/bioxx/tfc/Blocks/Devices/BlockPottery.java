package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;









public class BlockPottery
  extends BlockTerraContainer
{
  public IIcon clay;
  public IIcon ceramic;
  public IIcon straw;

  public BlockPottery() {
    super(Material.field_151592_s);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
    func_149711_c(2.0F);
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.clay = iconRegisterer.func_94245_a("terrafirmacraft:clay/Clay");
    this.ceramic = iconRegisterer.func_94245_a("terrafirmacraft:clay/Ceramic");
    this.straw = iconRegisterer.func_94245_a("terrafirmacraft:plants/Straw");
  }



  public IIcon func_149691_a(int side, int meta) {
    return this.straw;
  }



  public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
    return func_149691_a(side, bAccess.func_72805_g(x, y, z));
  }



  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
    return (side == ForgeDirection.UP && te != null && te.wood == 8);
  }



  public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
    TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
    return (te.isLit() && side == ForgeDirection.UP);
  }


  public int idDropped(int metadata, Random rand, int fortune) {
    return 0;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      TEPottery te = (TEPottery)world.func_147438_o(x, y, z);

      if (te.isLit() || (player.field_71071_by.func_70448_g() != null && (player.field_71071_by
        .func_70448_g().func_77973_b() == TFCItems.flintSteel || player.field_71071_by.func_70448_g().func_77973_b() == TFCItems.fireStarter))) {
        return false;
      }
      if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == TFCItems.straw && !player.func_70093_af()) {

        te.addStraw(player.field_71071_by.func_70448_g(), player);
        return true;
      }
      if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Items.ItemLogs && !player.func_70093_af() && te.straw == 8) {

        if (te.addLog(player.field_71071_by.func_70448_g(), player))
        {
          world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, TFCBlocks.logNatural.field_149762_H.func_150496_b(), (TFCBlocks.logNatural.field_149762_H.func_150497_c() + 1.0F) / 2.0F, TFCBlocks.logNatural.field_149762_H.func_150494_d() * 0.8F);
          return true;
        }

      } else if ((player.field_71071_by.func_70448_g() != null && !(player.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryBase)) || (te
        .func_70301_a(0) != null && te.func_70301_a(1) != null && te.func_70301_a(2) != null && te.func_70301_a(3) != null) || player.field_71071_by
        .func_70448_g() == null) {

        if (te.wood > 0) {

          te.ejectItem(3 + te.wood);
          te.wood--;
          world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
        }
        else if (te.wood == 0 && side == 1 && player.func_70093_af()) {

          if ((te.func_70301_a(0) != null && te.func_70301_a(0).func_77973_b() instanceof net.minecraft.item.ItemBlock) || (hitX < 0.5D && hitZ < 0.5D)) {
            te.ejectItem(0);
          } else if (hitX > 0.5D && hitZ < 0.5D) {
            te.ejectItem(1);
          } else if (hitX < 0.5D && hitZ > 0.5D) {
            te.ejectItem(2);
          } else if (hitX > 0.5D && hitZ > 0.5D) {
            te.ejectItem(3);
          }  world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
        }
      }
    }
    return false;
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
    int h = 0;
    int w = 0;
    if (te != null) {

      h = (te.straw == 0) ? 1 : te.straw;
      w = ((te.wood > 0) ? 1 : 0) + ((te.wood > 4) ? 1 : 0);
    }
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 0.0625F * h + 0.25F * w), (z + 1));
  }



  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    TEPottery te = (TEPottery)access.func_147438_o(x, y, z);
    int h = 0;
    int w = 0;
    if (te != null) {

      h = (te.straw == 0) ? 1 : te.straw;
      w = ((te.wood > 0) ? 1 : 0) + ((te.wood > 4) ? 1 : 0);
    }
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F * h + 0.25F * w, 1.0F);
  }



  public int func_149645_b() {
    return TFCBlocks.potteryRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }


  public void eject(World world, int x, int y, int z) {
    if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TEPottery) {

      TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
      te.ejectContents();
      world.func_147475_p(x, y, z);
    }
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    eject(world, x, y, z);
    return world.func_147468_f(x, y, z);
  }



  public Item func_149650_a(int i, Random rand, int j) {
    return null;
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEPottery();
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K)
    {
      if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {

        ((TEPottery)world.func_147438_o(x, y, z)).ejectContents();
        world.func_147468_f(x, y, z);
        return;
      }
    }
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
    return true;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockPottery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */