package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;


public class BlockCrop
  extends BlockContainer
{
  private IIcon[] iconsCarrots = new IIcon[5];
  private IIcon[] iconsGarlic = new IIcon[5];
  private IIcon[] iconsCorn = new IIcon[6];
  private IIcon[] iconsCabbage = new IIcon[6];
  private IIcon[] iconsTomato = new IIcon[8];
  private IIcon[] iconsPepperRed = new IIcon[7];
  private IIcon[] iconsPepperYellow = new IIcon[7];
  private IIcon[] iconsWheat = new IIcon[8];
  private IIcon[] iconsRye = new IIcon[8];
  private IIcon[] iconsBarley = new IIcon[8];
  private IIcon[] iconsOat = new IIcon[8];
  private IIcon[] iconsRice = new IIcon[8];
  private IIcon[] iconsGreenbean = new IIcon[7];
  private IIcon[] iconsOnion = new IIcon[7];
  private IIcon[] iconsPotato = new IIcon[7];
  private IIcon[] iconsSoybean = new IIcon[7];
  private IIcon[] iconsSquash = new IIcon[7];
  private IIcon[] iconsJute = new IIcon[6];
  private IIcon[] iconsSugarcane = new IIcon[8];

  public IIcon iconInfest;


  public BlockCrop() {
    super(Material.field_151585_k);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
  }



  public int func_149645_b() {
    return TFCBlocks.cropRenderId;
  }


  public void func_149651_a(IIconRegister register) {
    int i;
    for (i = 1; i < 6; i++) {

      this.iconsCarrots[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Carrots (" + i + ")");
      this.iconsGarlic[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Garlic (" + i + ")");
    }
    for (i = 1; i < 7; i++) {

      this.iconsCorn[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Corn (" + i + ")");
      this.iconsCabbage[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Cabbage (" + i + ")");
      this.iconsJute[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Jute (" + i + ")");
    }
    for (i = 1; i < 8; i++) {

      this.iconsPepperRed[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/PepperRed (" + i + ")");
      this.iconsPepperYellow[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/PepperYellow (" + i + ")");
      this.iconsGreenbean[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Greenbean (" + i + ")");
      this.iconsOnion[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Onion (" + i + ")");
      this.iconsPotato[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Potato (" + i + ")");
      this.iconsSquash[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Squash (" + i + ")");
      this.iconsSoybean[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Soybean (" + i + ")");
    }
    for (i = 1; i < 9; i++) {

      this.iconsTomato[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Tomato (" + i + ")");
      this.iconsWheat[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Wheat (" + i + ")");
      this.iconsRye[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Rye (" + i + ")");
      this.iconsBarley[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Barley (" + i + ")");
      this.iconsOat[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Oat (" + i + ")");
      this.iconsRice[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Rice (" + i + ")");
      this.iconsSugarcane[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Sugarcane (" + i + ")");
    }

    this.iconInfest = register.func_94245_a("terrafirmacraft:bugs");
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int meta) {
    TECrop te = (TECrop)access.func_147438_o(x, y, z);
    CropIndex crop = CropManager.getInstance().getCropFromId(te.cropId);

    int stage = (int)Math.floor(te.growth);
    if (stage > crop.numGrowthStages) {
      stage = crop.numGrowthStages;
    }
    switch (te.cropId) {

      case 0:
        return this.iconsWheat[stage];
      case 1:
        return this.iconsCorn[stage];
      case 2:
        return this.iconsTomato[stage];
      case 3:
        return this.iconsBarley[stage];
      case 4:
        return this.iconsRye[stage];
      case 5:
        return this.iconsOat[stage];
      case 6:
        return this.iconsRice[stage];
      case 7:
        return this.iconsPotato[stage];
      case 8:
        return this.iconsOnion[stage];
      case 9:
        return this.iconsCabbage[stage];
      case 10:
        return this.iconsGarlic[stage];
      case 11:
        return this.iconsCarrots[stage];
      case 12:
        return this.iconsPepperYellow[stage];
      case 13:
        return this.iconsPepperRed[stage];
      case 14:
        return this.iconsSoybean[stage];
      case 15:
        return this.iconsGreenbean[stage];
      case 16:
        return this.iconsSquash[stage];
      case 17:
        return this.iconsJute[stage];
      case 18:
        return this.iconsSugarcane[stage];
    }
    return this.iconsCorn[6];
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    TECrop te = (TECrop)world.func_147438_o(x, y, z);
    CropIndex crop = CropManager.getInstance().getCropFromId(te.cropId);

    if (TFCOptions.enableDebugMode) {

      TerraFirmaCraft.LOG.info("Crop ID: " + te.cropId);
      TerraFirmaCraft.LOG.info("Growth: " + te.growth);
      TerraFirmaCraft.LOG.info("Est Growth: " + te.getEstimatedGrowth(crop));
    }

    return false;
  }



  public void func_149681_a(World world, int i, int j, int k, int l, EntityPlayer player) {
    TECrop te = (TECrop)world.func_147438_o(i, j, k);
    if (!world.field_72995_K) {

      ItemStack itemstack = player.field_71071_by.func_70448_g();
      int[] equipIDs = OreDictionary.getOreIDs(itemstack);

      for (int id : equipIDs) {

        String name = OreDictionary.getOreName(id);
        if (name.startsWith("itemScythe")) {

          for (int x = -1; x < 2; x++) {

            for (int z = -1; z < 2; z++) {

              if (world.func_147439_a(i + x, j, k + z) == this && player.field_71071_by.func_70301_a(player.field_71071_by.field_70461_c) != null) {

                player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
                TECrop teX = (TECrop)world.func_147438_o(i + x, j, k + z);
                teX.onHarvest(world, player, true);

                world.func_147468_f(i + x, j, k + z);

                itemstack.func_77972_a(1, (EntityLivingBase)player);
                if (itemstack.field_77994_a == 0) {
                  player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
              }
            }
          }

          return;
        }
      }
    }

    te.onHarvest(world, player, true);
  }







  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.3D, (z + 1));
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149695_a(World world, int x, int y, int z, Block b) {
    super.func_149695_a(world, x, y, z, b);

    if (!func_149718_j(world, x, y, z)) {
      world.func_147468_f(x, y, z);
    }
  }


  public boolean func_149686_d() {
    return false;
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    return (TFC_Core.isFarmland(world.func_147439_a(x, y - 1, z)) || TFC_Core.isSoil(world.func_147439_a(x, y - 1, z)));
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TECrop();
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockCrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */