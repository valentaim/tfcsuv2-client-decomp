package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import growthcraft.rice.util.RiceBlockCheck;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;










public class ItemCustomSeeds
  extends ItemTerra
{
  private int cropId;

  public ItemCustomSeeds(int cropId) {
    this.cropId = cropId;
    func_77637_a(TFCTabs.TFC_FOODS);
    setFolder("food/");
    setWeight(EnumWeight.LIGHT);
    setSize(EnumSize.TINY);
  }

  public static boolean hasBlock(World w, int x, int y, int z) {
    for (int y1 = y + 1; y1 < 255; y1++) {
      if (!(w.func_147439_a(x, y1, z) instanceof net.minecraft.block.BlockGlass) && !(w.func_147439_a(x, y1, z) instanceof net.minecraft.block.BlockAir)) return true;
    }  return false;
  }







  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (side != 1 || world.field_72995_K)
      return false;
    if (player.func_82247_a(x, y, z, side, stack) && player.func_82247_a(x, y + 1, z, side, stack)) {

      Block var8 = world.func_147439_a(x, y, z);
      if ((stack.func_77973_b() == TFCItems.seedsRice && RiceBlockCheck.isPaddyWithWater((IBlockAccess)world, x, y, z, 1)) || (stack.func_77973_b() != TFCItems.seedsRice && (var8 == TFCBlocks.tilledSoil || var8 == TFCBlocks.tilledSoil2) && world.func_147437_c(x, y + 1, z))) {

        CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
        if (hasBlock(world, x, y, z) || (crop.needsSunlight && !TECrop.hasSunlight(world, x, y + 1, z))) {

          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.seeds.failedSun", new Object[0]));
          return false;
        }

        if (TFC_Climate.getHeightAdjustedTemp(world, x, y, z) <= crop.minAliveTemp && !crop.dormantInFrost) {

          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.seeds.failedTemp", new Object[0]));
          return false;
        }

        world.func_147449_b(x, y + 1, z, TFCBlocks.crops);

        TECrop te = (TECrop)world.func_147438_o(x, y + 1, z);
        te.cropId = this.cropId;
        world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
        world.func_147471_g(x, y, z);
        stack.field_77994_a--;
        return true;
      }

      return false;
    }

    return false;
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);

    SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.agriculture");
    int nutrient = CropManager.getInstance().getCropFromId(this.cropId).getCycleType();

    if (rank == SkillStats.SkillRank.Expert || rank == SkillStats.SkillRank.Master)
    {
      switch (nutrient) {

        case 0:
          arraylist.add(EnumChatFormatting.RED + TFC_Core.translate("gui.Nutrient.A"));
          break;
        case 1:
          arraylist.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.Nutrient.B"));
          break;
        case 2:
          arraylist.add(EnumChatFormatting.YELLOW + TFC_Core.translate("gui.Nutrient.C"));
          break;
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */