package com.bioxx.tfc.Items.Pottery;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;




public class ItemPotteryBase
  extends ItemTerra
  implements ISize
{
  public IIcon clayIcon;
  public IIcon ceramicIcon;

  public ItemPotteryBase() {
    this.field_77787_bX = true;
    setFolder("pottery/");
    func_77637_a(TFCTabs.TFC_POTTERY);
    this.metaNames = new String[] { "", "" };
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.SMALL);
  }



  public void func_94581_a(IIconRegister registerer) {
    this.clayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[0]);
    this.ceramicIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[1]);
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < this.metaNames.length; i++) {
      list.add(new ItemStack((Item)this, 1, i));
    }
  }


  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage) {
    if (damage == 0) {
      return this.clayIcon;
    }
    return this.ceramicIcon;
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));
    } else {
      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }


  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && entityplayer.func_70093_af()) {


      if (side == 1) {

        int offset = 0;
        if (world.func_147439_a(x, y, z) != TFCBlocks.pottery && world.func_147437_c(x, y + 1, z)) {


          if (!world.isSideSolid(x, y, z, ForgeDirection.UP))
            return false;
          world.func_147449_b(x, y + 1, z, TFCBlocks.pottery);
          offset = 1;
        }


        if (world.func_147438_o(x, y + offset, z) != null && world.func_147438_o(x, y + offset, z) instanceof TEPottery) {

          TEPottery te = (TEPottery)world.func_147438_o(x, y + offset, z);
          if (hitX < 0.5D && hitZ < 0.5D) {

            if (te.canAddItem(0))
            {
              te.inventory[0] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
              (te.inventory[0]).field_77990_d = itemstack.field_77990_d;
              itemstack.field_77994_a--;
              world.func_147471_g(x, y + offset, z);
            }

          } else if (hitX > 0.5D && hitZ < 0.5D) {

            if (te.canAddItem(1))
            {
              te.inventory[1] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
              (te.inventory[1]).field_77990_d = itemstack.field_77990_d;
              itemstack.field_77994_a--;
              world.func_147471_g(x, y + offset, z);
            }

          } else if (hitX < 0.5D && hitZ > 0.5D) {

            if (te.canAddItem(2))
            {
              te.inventory[2] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
              (te.inventory[2]).field_77990_d = itemstack.field_77990_d;
              itemstack.field_77994_a--;
              world.func_147471_g(x, y + offset, z);
            }

          } else if (hitX > 0.5D && hitZ > 0.5D &&
            te.canAddItem(3)) {

            te.inventory[3] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
            (te.inventory[3]).field_77990_d = itemstack.field_77990_d;
            itemstack.field_77994_a--;
            world.func_147471_g(x, y + offset, z);
          }
        }
        return true;
      }
      return false;
    }
    return false;
  }

  public void onDoneCooking(World world, ItemStack is, Alloy.EnumTier furnaceTier) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */