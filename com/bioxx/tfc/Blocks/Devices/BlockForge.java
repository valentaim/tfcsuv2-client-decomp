package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockForge
  extends BlockTerraContainer
{
  private IIcon textureOn;
  private IIcon textureOff;

  public BlockForge() {
    super(Material.field_151595_p);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && world.func_147438_o(i, j, k) instanceof TEForge) {

      ItemStack equippedItem = entityplayer.func_71045_bC();
      TEForge tef = (TEForge)world.func_147438_o(i, j, k);
      if (equippedItem != null) {

        Item item = equippedItem.func_77973_b();
        if (item instanceof com.bioxx.tfc.Items.Tools.ItemFirestarter || item instanceof net.minecraft.item.ItemFlintAndSteel) {

          if (!tef.isSmokeStackValid) {

            TFC_Core.sendInfoMessage(entityplayer, (IChatComponent)new ChatComponentTranslation("gui.forge.badChimney", new Object[0]));
            return true;
          }
          if (tef.fireTemp <= 0.0F && tef.fireItemStacks[7] != null) {

            tef.fireTemp = 10.0F;
            tef.fuelBurnTemp = 20;
            tef.fuelTimeLeft = 10;
            if (item instanceof net.minecraft.item.ItemFlintAndSteel) {

              Random rand = new Random();
              world.func_72908_a(i + 0.5D, j + 0.5D, k + 0.5D, "fire.ignite", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            }
            equippedItem.func_77972_a(1, (EntityLivingBase)entityplayer);
            world.func_72921_c(i, j, k, 2, 3);
            return true;
          }
        }
      }

      if (tef.isSmokeStackValid) {

        entityplayer.openGui(TerraFirmaCraft.instance, 23, world, i, j, k);
      }
      else {

        TFC_Core.sendInfoMessage(entityplayer, (IChatComponent)new ChatComponentTranslation("gui.forge.badChimney", new Object[0]));
      }
    }

    return true;
  }



  public IIcon func_149691_a(int i, int j) {
    if (j > 0)
      return this.textureOn;
    return this.textureOff;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.textureOn = iconRegisterer.func_94245_a("terrafirmacraft:devices/Forge On");
    this.textureOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Forge Off");
  }


  public boolean getIsFireLit(int i) {
    return ((i & 0x1) != 0);
  }



  public int func_149645_b() {
    return TFCBlocks.forgeRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K)
    {
      if (!TFC_Core.isSurroundedSolid(world, x, y, z) || !TFC_Core.isSurroundedStone(world, x, y, z)) {

        ((TEForge)world.func_147438_o(x, y, z)).ejectContents();
        world.func_147468_f(x, y, z);
      }
    }
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }



  public void func_149734_b(World world, int i, int j, int k, Random random) {
    if (this == TFCBlocks.forge) {
      return;
    }



    float f = i + 0.5F;
    float f1 = j + 0.9F + random.nextFloat() * 6.0F / 16.0F;
    float f2 = k + 0.5F;

    float f4 = random.nextFloat() * 0.6F;
    float f5 = random.nextFloat() * -0.6F;
    float f6 = random.nextFloat() * -0.6F;
    world.func_72869_a("smoke", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
    world.func_72869_a("flame", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
    world.func_72869_a("smoke", (f + f5 + 0.3F), f1, (f2 + f4 - 0.3F), 0.0D, 0.0D, 0.0D);
    world.func_72869_a("flame", (f + f5 + 0.3F), f1, (f2 + f4 - 0.3F), 0.0D, 0.0D, 0.0D);

    if (((TEForge)world.func_147438_o(i, j, k)).fireTemp > 550.0F) {

      world.func_72869_a("flame", (f + f5 + 0.3F), f1, (f2 + f6 + 0.2F), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", (f + f4 - 0.3F), f1, (f2 + f6 + 0.1F), 0.0D, 0.0D, 0.0D);
    }
  }




  public boolean func_149686_d() {
    return false;
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta == 0)
      return 0;
    if (meta == 1) {
      return 15;
    }
    return 10;
  }



  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (!world.field_72995_K)
    {
      if (world.func_147438_o(x, y, z) instanceof TEForge)
      {
        ((TEForge)world.func_147438_o(x, y, z)).removeSmoke();
      }
    }

    super.func_149749_a(world, x, y, z, block, meta);
  }







  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    return AxisAlignedBB.func_72330_a(par2 + this.field_149759_B, par3 + this.field_149760_C, par4 + this.field_149754_D, par2 + this.field_149755_E, par3 + this.field_149756_F, par4 + this.field_149757_G);
  }






  public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
    return AxisAlignedBB.func_72330_a(par2 + this.field_149759_B, par3 + this.field_149760_C, par4 + this.field_149754_D, par2 + this.field_149755_E, par3 + this.field_149756_F, par4 + this.field_149757_G);
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEForge();
  }






  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "terrafirmacraft:devices/forge";
  }







  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (world.func_72805_g(x, y, z) >= 1 && !entity.func_70045_F() && entity instanceof EntityLivingBase)
    {

      entity.func_70015_d(5);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */