package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.Metal.MetalPair;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TECrucible;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockCrucible
  extends BlockTerraContainer
{
  private IIcon[] icons;

  public BlockCrucible() {
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149676_a(0.0625F, 0.25F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    if (!world.field_72995_K && (TECrucible)world.func_147438_o(i, j, k) != null)
    {



      entityplayer.openGui(TerraFirmaCraft.instance, 37, world, i, j, k);
    }
    return true;
  }



  public void func_149749_a(World world, int i, int j, int k, Block block, int par6) {
    if (world.func_147438_o(i, j, k) instanceof TECrucible) {

      TECrucible te = (TECrucible)world.func_147438_o(i, j, k);
      if (te instanceof net.minecraft.inventory.IInventory)
      {
        for (int i1 = 0; i1 < te.func_70302_i_(); i1++) {

          if (te.func_70301_a(i1) != null) {

            EntityItem entityItem = new EntityItem(world, i + 0.5D, j + 0.5D, k + 0.5D, te.func_70301_a(i1));
            entityItem.field_70159_w = 0.0D;
            entityItem.field_70181_x = 0.0D;
            entityItem.field_70179_y = 0.0D;
            world.func_72838_d((Entity)entityItem);
            te.func_70299_a(i1, null);
          }
        }
      }
      ItemStack is = new ItemStack(Item.func_150898_a(block), 1);
      NBTTagCompound nbt = writeCrucibleToNBT(te);
      is.func_77982_d(nbt);
      EntityItem ei = new EntityItem(world, i, j, k, is);
      world.func_72838_d((Entity)ei);
    }
    super.func_149749_a(world, i, j, k, block, par6);
  }












  protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {}











  public NBTTagCompound writeCrucibleToNBT(TECrucible te) {
    NBTTagCompound nbt = new NBTTagCompound();

    nbt.func_74768_a("temp", te.temperature);

    NBTTagList nbttaglist = new NBTTagList();
    Iterator<MetalPair> iter = te.metals.values().iterator();
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74768_a("ID", Item.func_150891_b(m.type.ingot));
        nbttagcompound1.func_74776_a("AmountF", m.amount);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Metals", (NBTBase)nbttaglist);

    nbttaglist = new NBTTagList();
    for (int i = 0; i < te.storage.length; i++) {

      if (te.storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        te.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }

    nbt.func_74782_a("Items", (NBTBase)nbttaglist);

    return nbt;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack is) {
    super.func_149689_a(world, i, j, k, player, is);
    TECrucible te = (TECrucible)world.func_147438_o(i, j, k);

    if (te != null && is.func_77942_o())
    {
      te.readFromItemNBT(is.func_77978_p());
    }
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.icons = new IIcon[2];
    this.icons[0] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Crucible Top");
    this.icons[1] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Crucible Side");
  }



  public IIcon func_149691_a(int i, int j) {
    if (i < 2)
    {
      return this.icons[0];
    }

    return this.icons[1];
  }



  public int func_149645_b() {
    return TFCBlocks.crucibleRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TECrucible();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */