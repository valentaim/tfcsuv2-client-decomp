package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;




public class ItemSoil
  extends ItemTerraBlock
{
  private IIcon icon;

  public ItemSoil(Block b) {
    super(b);
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    super.func_77624_a(is, player, arraylist, flag);

    Block b = Block.func_149634_a(is.func_77973_b());
    if (TFC_Core.isPeat(b) || TFC_Core.isPeatGrass(b)) {
      return;
    }
    int dam = is.func_77960_j();
    if (b == TFCBlocks.dirt2 || b == TFCBlocks.sand2 || b == TFCBlocks.clay2 ||


      TFC_Core.isGrassType2(b) || b == TFCBlocks.tilledSoil2 || b == TFCBlocks.gravel2)
    {


      dam += 16;
    }

    if (dam < Global.STONE_ALL.length) {
      arraylist.add(EnumChatFormatting.DARK_GRAY + Global.STONE_ALL[dam]);
    } else {
      arraylist.add(EnumChatFormatting.DARK_RED + "Unknown");
    }
  }


  public void func_94581_a(IIconRegister registerer) {
    if (this.field_150939_a instanceof com.bioxx.tfc.Blocks.Terrain.BlockPeat) {

      String s = this.field_150939_a.func_149702_O();

      if (s != null)
      {
        this.icon = registerer.func_94245_a(s);
      }
    }
  }







  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage) {
    if (this.field_150939_a instanceof com.bioxx.tfc.Blocks.Terrain.BlockPeat)
    {
      return (this.icon != null) ? this.icon : this.field_150939_a.func_149733_h(1);
    }

    return super.func_77617_a(damage);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */