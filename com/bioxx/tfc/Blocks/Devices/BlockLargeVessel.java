package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TEVessel;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockLargeVessel
  extends BlockBarrel
{
  private IIcon[] clayIcons;
  private IIcon[] ceramicIcons;

  public BlockLargeVessel() {
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.7F, 0.8F);
  }





  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    par3List.add(new ItemStack((Block)this, 1, 0));
    par3List.add(new ItemStack((Block)this, 1, 1));
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.ceramicIcons = new IIcon[3];
    this.clayIcons = new IIcon[3];
    this.ceramicIcons[0] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Ceramic Vessel Top");
    this.ceramicIcons[1] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Ceramic Vessel Side");
    this.ceramicIcons[2] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Ceramic Vessel Bottom");
    this.clayIcons[0] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Clay Vessel Top");
    this.clayIcons[1] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Clay Vessel Side");
    this.clayIcons[2] = iconRegisterer.func_94245_a("terrafirmacraft:clay/Clay Vessel Bottom");
  }



  public IIcon func_149691_a(int side, int meta) {
    if (meta == 1) {

      if (side == 1)
        return this.ceramicIcons[0];
      if (side == 0) {
        return this.ceramicIcons[2];
      }
      return this.ceramicIcons[1];
    }
    if (side == 1)
      return this.clayIcons[0];
    if (side == 0) {
      return this.clayIcons[2];
    }
    return this.clayIcons[1];
  }



  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
    int meta = access.func_72805_g(x, y, z);
    if (meta == 1) {

      if (side == 1)
        return this.ceramicIcons[0];
      if (side == 0) {
        return this.ceramicIcons[2];
      }
      return this.ceramicIcons[1];
    }
    if (side == 1)
      return this.clayIcons[0];
    if (side == 0) {
      return this.clayIcons[2];
    }
    return this.clayIcons[1];
  }




  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.vesselRenderId;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K) {

      world.func_147471_g(x, y, z);
      return true;
    }


    if (player.func_70093_af())
    {
      return false;
    }

    if (world.func_147438_o(x, y, z) != null) {

      TEVessel te = (TEVessel)world.func_147438_o(x, y, z);

      if (!handleInteraction(player, (TEBarrel)te)) {

        if (te.getInvCount() == 0) {
          player.openGui(TerraFirmaCraft.instance, 46, world, x, y, z);
        } else {
          player.openGui(TerraFirmaCraft.instance, 47, world, x, y, z);
        }  return true;
      }
      return true;
    }

    return false;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEVessel();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */