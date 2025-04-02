package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TELeatherRack;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;






public class ItemRawHide
  extends ItemLooseRock
{
  public ItemRawHide() {
    this.field_77787_bX = true;
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    this.metaNames = new String[] { "small", "medium", "large" };
    setWeight(EnumWeight.LIGHT);
    setSize(EnumSize.MEDIUM);
  }




  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
    {
      if (itemstack.func_77973_b() == TFCItems.hide && itemstack.func_77960_j() >= 2) {
        int d = (int)((45.0F + (entityplayer.field_70177_z % 360.0F + 360.0F) % 360.0F) / 90.0F) % 4;
        int x2 = x + ((d == 1) ? -1 : ((d == 3) ? 1 : 0));
        int z2 = z + ((d == 2) ? -1 : ((d == 0) ? 1 : 0));
        if (world.func_147439_a(x, y, z) == TFCBlocks.thatch && side == 1 && world.func_147439_a(x2, y, z2) == TFCBlocks.thatch && world
          .func_147437_c(x, y + 1, z) && world.func_147437_c(x2, y + 1, z2)) {
          world.func_147480_a(x, y, z, false);
          world.func_147480_a(x2, y, z2, false);
          world.func_147465_d(x, y, z, TFCBlocks.strawHideBed, d, 2);
          world.func_147465_d(x2, y, z2, TFCBlocks.strawHideBed, d + 8, 2);
          itemstack.field_77994_a--;
        }

      } else if (itemstack.func_77973_b() == TFCItems.soakedHide && side == ForgeDirection.UP.ordinal()) {

        if (world.func_147439_a(x, y, z) instanceof com.bioxx.tfc.Blocks.Flora.BlockLogHoriz && world.func_147437_c(x, y + 1, z) && world.func_147449_b(x, y + 1, z, TFCBlocks.leatherRack)) {

          TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y + 1, z);
          te.setLeather(itemstack);
        }
      }
    }

    return true;
  }



  public ItemStack func_77659_a(ItemStack itemstack, World par2World, EntityPlayer entityplayer) {
    return itemstack;
  }




  public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}



  public IIcon func_77617_a(int meta) {
    return this.field_77791_bV;
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
  }



  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(new ItemStack(this, 1, 0));
    list.add(new ItemStack(this, 1, 1));
    list.add(new ItemStack(this, 1, 2));
  }

  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemRawHide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */