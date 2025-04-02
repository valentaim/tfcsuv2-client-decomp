package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TEBellows;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class BlockBellows
  extends BlockTerraContainer
{
  public static IIcon[] sides = new IIcon[4];

  public static IIcon bellowsFront;
  public static IIcon bellowsBack;

  public static int getDirectionFromMetadata(int i) {
    return i & 0x3;
  }


  public BlockBellows(Material material) {
    super(material);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    super.func_149727_a(world, i, j, k, entityplayer, par6, par7, par8, par9);
    TEBellows teb = null;
    TileEntity te = world.func_147438_o(i, j, k);
    if (!world.field_72995_K && te != null && te instanceof TEBellows) {

      teb = (TEBellows)te;
      if (!teb.shouldBlow) {

        teb.shouldBlow = true;
        world.func_147471_g(i, j, k);

        world.func_72908_a(i, j, k, "terrafirmacraft:bellows.blow.air", 0.4F, 1.0F);
      }
    }
    return true;
  }









  public IIcon func_149691_a(int i, int j) {
    if (i == 0) {

      if (j == 0) return sides[0];
      if (j == 1) return sides[1];
      if (j == 2) return sides[3];
      if (j == 3) return sides[2];

    } else if (i == 1) {

      if (j == 0) return sides[0];
      if (j == 1) return sides[1];
      if (j == 2) return sides[3];
      if (j == 3) return sides[2];

    } else if (i == 2) {

      if (j == 0) return bellowsBack;
      if (j == 1) return sides[2];
      if (j == 2) return bellowsBack;
      if (j == 3) return sides[1];

    } else if (i == 3) {

      if (j == 0) return bellowsBack;
      if (j == 1) return sides[1];
      if (j == 2) return bellowsBack;
      if (j == 3) return sides[2];

    } else if (i == 4) {

      if (j == 0) return sides[2];
      if (j == 1) return bellowsBack;
      if (j == 2) return sides[1];
      if (j == 3) return bellowsBack;

    } else if (i == 5) {

      if (j == 0) return sides[1];
      if (j == 1) return bellowsBack;
      if (j == 2) return sides[2];
      if (j == 3) return bellowsBack;

    } else {

      return sides[1];
    }
    return sides[0];
  }



  public void func_149651_a(IIconRegister registerer) {
    sides[0] = registerer.func_94245_a("terrafirmacraft:devices/Bellows82");
    sides[1] = registerer.func_94245_a("terrafirmacraft:devices/Bellows83");
    sides[2] = registerer.func_94245_a("terrafirmacraft:devices/Bellows84");
    sides[3] = registerer.func_94245_a("terrafirmacraft:devices/Bellows85");
    bellowsFront = registerer.func_94245_a("terrafirmacraft:devices/Bellows Front");
    bellowsBack = registerer.func_94245_a("terrafirmacraft:devices/Bellows Back");
  }



  public int func_149645_b() {
    return TFCBlocks.bellowsRenderId;
  }








  public boolean func_149662_c() {
    return false;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    super.func_149689_a(world, i, j, k, entityliving, is);
    int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    world.func_72921_c(i, j, k, l, 2);
  }



  public boolean func_149686_d() {
    return false;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEBellows();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */