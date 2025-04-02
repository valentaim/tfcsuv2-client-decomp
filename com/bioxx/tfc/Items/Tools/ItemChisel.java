package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Tools.ChiselManager;
import com.bioxx.tfc.api.Tools.IToolChisel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChisel
  extends ItemTerraTool
  implements IToolChisel {
  private static final Set<Block> BLOCKS = Sets.newHashSet((Object[])new Block[0]);


  public ItemChisel(Item.ToolMaterial e) {
    super(0.0F, e, BLOCKS);
    func_77656_e(e.func_77997_a() / 2);
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.VERYSMALL;
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.Chisel.Inst0"));
    }
    else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }



  public boolean onUsed(World world, EntityPlayer player, int x, int y, int z, Block block, int meta, int side, float hitX, float hitY, float hitZ) {
    return ChiselManager.getInstance().onUsedHandler(world, player, x, y, z, block, meta, side, hitX, hitY, hitZ);
  }



  public boolean canChisel(EntityPlayer player, int x, int y, int z) {
    return true;
  }




  public Multimap func_111205_h() {
    return (Multimap)HashMultimap.create();
  }


  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemChisel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */