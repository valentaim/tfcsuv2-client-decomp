package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TileEntities.TEBerryBush;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockBerryBush
  extends BlockTerraContainer
{
  public static IIcon[] icons;
  public static IIcon[] iconsBerries;
  public static String[] metaNames;
  public static final int WINTERGREEN = 0;
  public static final int BLUEBERRY = 1;
  public static final int RASPBERRY = 2;
  public static final int STRAWBERRY = 3;
  public static final int BLACKBERRY = 4;
  public static final int BUNCHBERRY = 5;
  public static final int CRANBERRY = 6;
  public static final int SNOWBERRY = 7;
  public static final int ELDERBERRY = 8;
  public static final int GOOSEBERRY = 9;
  public static final int CLOUDBERRY = 10;

  public BlockBerryBush() {
    super(Material.field_151585_k);
    metaNames = new String[] { "Wintergreen", "Blueberry", "Raspberry", "Strawberry", "Blackberry", "Bunchberry", "Cranberry", "Snowberry", "Elderberry", "Gooseberry", "Cloudberry" };
    icons = new IIcon[metaNames.length];
    iconsBerries = new IIcon[metaNames.length];
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < metaNames.length; i++) {
      list.add(new ItemStack(item, 1, i));
    }
  }


  public void func_149683_g() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }



  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    int meta = access.func_72805_g(x, y, z);

    float minX = 0.1F;
    float minZ = 0.1F;
    float maxX = 0.9F;
    float maxZ = 0.9F;
    float maxY = 1.0F;

    if (isSamePlant(access, x - 1, y, z, meta)) minX = 0.0F;
    if (isSamePlant(access, x + 1, y, z, meta)) maxX = 1.0F;
    if (isSamePlant(access, x, y, z - 1, meta)) minZ = 0.0F;
    if (isSamePlant(access, x, y, z + 1, meta)) maxZ = 1.0F;
    if (isSamePlant(access, x, y + 1, z, meta)) maxY = 1.0F;

    switch (meta) {


      case 0:
        maxY = 0.2F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 1:
        maxY = 0.85F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 2:
        maxY = 0.85F;
        if (isSamePlant(access, x, y + 1, z, meta))
          maxY = 1.0F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 3:
        maxY = 0.2F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 4:
        maxY = 0.85F;
        if (isSamePlant(access, x, y + 1, z, meta))
          maxY = 1.0F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 5:
        maxY = 0.2F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 6:
        maxY = 0.6F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 7:
        maxY = 0.2F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 8:
        maxY = 0.85F;
        if (isSamePlant(access, x, y + 1, z, meta))
          maxY = 1.0F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 9:
        maxY = 0.75F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;


      case 10:
        maxY = 0.35F;
        func_149676_a(minX, 0.0F, minZ, maxX, maxY, maxZ);
        return;
    }


    func_149676_a(minX, 0.0F, minZ, maxX, 1.0F, maxZ);
  }





  private boolean isSamePlant(IBlockAccess bAccess, int x, int y, int z, int meta) {
    return (bAccess.func_147439_a(x, y, z) == this && bAccess.func_72805_g(x, y, z) == meta);
  }




  public void func_149699_a(World world, int x, int y, int z, EntityPlayer entityplayer) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(getType(meta));

      TEBerryBush te = (TEBerryBush)world.func_147438_o(x, y, z);
      if (te != null && te.hasFruit) {

        te.hasFruit = false;
        te.dayHarvested = TFC_Time.getTotalDays();
        world.func_147471_g(x, y, z);
        func_149642_a(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(), Helper.roundNumber(3.0F + world.field_73012_v.nextFloat() * 5.0F, 10.0F)));
      }
    }
  }




  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(getType(meta));

      TEBerryBush te = (TEBerryBush)world.func_147438_o(x, y, z);
      if (te != null && te.hasFruit) {

        te.hasFruit = false;
        te.dayHarvested = TFC_Time.getTotalDays();
        world.func_147471_g(x, y, z);
        func_149642_a(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(), Helper.roundNumber(3.0F + world.field_73012_v.nextFloat() * 5.0F, 10.0F)));
        return true;
      }
    }
    return false;
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    lifeCycle(world, x, y, z);
  }


  private void lifeCycle(World world, int x, int y, int z) {
    if (!world.field_72995_K) {

      if (!func_149718_j(world, x, y, z)) {

        func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
        world.func_147468_f(x, y, z);

        return;
      }
      TileEntity te = world.func_147438_o(x, y, z);
      TEBerryBush tebb = null;
      if (te instanceof TEBerryBush)
        tebb = (TEBerryBush)world.func_147438_o(x, y, z);
      if (tebb != null) {

        FloraIndex floraIndex = FloraManager.getInstance().findMatchingIndex(getType(world.func_72805_g(x, y, z)));
        float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);

        if (temp >= floraIndex.minTemp && temp < floraIndex.maxTemp) {

          if (!tebb.hasFruit && floraIndex.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) && TFC_Time.getMonthsSinceDay(tebb.dayHarvested) > 0)
          {
            tebb.hasFruit = true;
            tebb.dayFruited = TFC_Time.getTotalDays();
            world.func_147471_g(x, y, z);
          }

        } else if (temp < floraIndex.minTemp - 5.0F || temp > floraIndex.maxTemp + 5.0F) {

          if (tebb.hasFruit) {

            tebb.hasFruit = false;
            world.func_147471_g(x, y, z);
          }
        }

        if (tebb.hasFruit && TFC_Time.getMonthsSinceDay(tebb.dayFruited) > floraIndex.fruitHangTime)
        {
          tebb.hasFruit = false;
          world.func_147471_g(x, y, z);
        }

      }
    } else {

      world.func_147438_o(x, y, z).func_145829_t();
    }
  }


  public String getType(int meta) {
    return metaNames[meta];
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    for (int i = 0; i < icons.length; i++) {

      icons[i] = register.func_94245_a("terrafirmacraft:plants/" + metaNames[i]);
      iconsBerries[i] = register.func_94245_a("terrafirmacraft:plants/" + metaNames[i] + " Berry");
    }
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
    int meta = access.func_72805_g(x, y, z);

    TEBerryBush te = (TEBerryBush)access.func_147438_o(x, y, z);
    if (te != null && te.hasFruit) {
      return iconsBerries[meta];
    }
    return icons[meta];
  }



  public IIcon func_149691_a(int i, int meta) {
    return iconsBerries[meta];
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    return ((world.func_72883_k(x, y, z) >= 8 || world.func_72937_j(x, y, z)) && (
      canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)) || (
      isSamePlant((IBlockAccess)world, x, y - 1, z, world.func_72805_g(x, y, z)) && canStack(meta))));
  }



  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
    super.func_149689_a(world, x, y, z, entityliving, is);
    if (!func_149718_j(world, x, y, z)) {

      func_149695_a(world, x, y, z, world.func_147439_a(x, y, z));
    }
    else {

      TEBerryBush te = (TEBerryBush)world.func_147438_o(x, y, z);
      te.dayHarvested = TFC_Time.getTotalDays();
    }
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    super.func_149695_a(world, x, y, z, block);
    lifeCycle(world, x, y, z);
  }


  protected boolean canThisPlantGrowOnThisBlock(Block block) {
    return TFC_Core.isGrass(block);
  }



  public Item func_149650_a(int i1, Random rand, int i2) {
    return Item.func_150898_a((Block)this);
  }



  public int func_149692_a(int i) {
    return i;
  }



  public TileEntity func_149915_a(World i, int meta) {
    return (TileEntity)new TEBerryBush();
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }



  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    int meta = world.func_72805_g(x, y, z);
    if (meta == 1 || meta == 2 || meta == 4 || meta == 8 || meta == 9) {

      entity.field_70159_w *= 0.7D;
      entity.field_70179_y *= 0.7D;
    }

    if (meta == 2 || meta == 4)
    {
      if (entity instanceof EntityLivingBase)
        entity.func_70097_a(DamageSource.field_76367_g, 5.0F);
    }
  }

  private boolean canStack(int meta) {
    return (meta == 2 || meta == 4 || meta == 8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockBerryBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */