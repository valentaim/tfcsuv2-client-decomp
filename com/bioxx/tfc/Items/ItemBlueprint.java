package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEDetailed;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.Helper;
import java.util.BitSet;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;





public class ItemBlueprint
  extends ItemTerra
{
  public static final String SUFFIX = "BR:";
  public static final String TAG_COMPLETED = "Completed";
  public static final String TAG_ITEM_NAME = "ItemName";
  public static final String TAG_DATA = "Data";
  public static final String TAG_X_ANGLE = "xAngle";
  public static final String TAG_Y_ANGLE = "yAngle";
  public static final String TAG_Z_ANGLE = "zAngle";

  public ItemBlueprint() {
    func_77656_e(0);
    func_77627_a(true);
    func_77655_b("Blueprint");
    func_77637_a(TFCTabs.TFC_TOOLS);
    setFolder("tools/");
  }



  public boolean func_77651_p() {
    return true;
  }



  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    MovingObjectPosition mo = Helper.getMouseOverObject((EntityLivingBase)player, world);
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    if (mo != null) {
      x = mo.field_72311_b; y = mo.field_72312_c; z = mo.field_72309_d;
    }

    if (mo == null || world.func_147439_a(mo.field_72311_b, mo.field_72312_c, mo.field_72309_d) != TFCBlocks.detailed) {
      if (stack.func_77942_o() && stack.field_77990_d.func_74767_n("Completed")) {
        player.openGui(TerraFirmaCraft.instance, 34, player.field_70170_p, x, y, z);
      }
    } else if (!stack.func_77942_o() || !stack.field_77990_d.func_74767_n("Completed")) {
      player.openGui(TerraFirmaCraft.instance, 34, player.field_70170_p, mo.field_72311_b, mo.field_72312_c, mo.field_72309_d);
    }
    return stack;
  }



  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (world.func_147439_a(x, y, z) != TFCBlocks.detailed) {
      return false;
    }
    if (!stack.func_77942_o() || !stack.field_77990_d.func_74767_n("Completed")) {

      TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);

      byte[] data = TEDetailed.toByteArray(te.data);

      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74773_a("Data", data);

      stack.func_77982_d(nbt);
    }
    else if (stack.func_77942_o() && stack.field_77990_d.func_74767_n("Completed")) {

      int hasChisel = -1;
      int hasHammer = -1;

      if (!player.field_71075_bZ.field_75098_d) {
        for (int i = 0; i < 9; i++) {
          if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
            hasHammer = i;
          if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {
            hasChisel = i;
          }
        }
        if (hasChisel == -1 || hasHammer == -1) {

          if (!world.field_72995_K)
          {
            TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.Blueprint.missingTool", new Object[0]));
          }
          return false;
        }
      }

      TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
      BitSet blueprintData = TEDetailed.turnCube(stack.field_77990_d
          .func_74770_j("Data"), stack.field_77990_d
          .func_74762_e("xAngle"), stack.field_77990_d
          .func_74762_e("yAngle"), stack.field_77990_d
          .func_74762_e("zAngle"));


      for (int c = 0; c < 512; c++) {
        if (te.data.get(c) && !blueprintData.get(c)) {

          te.data.clear(c);

          if (!player.field_71075_bZ.field_75098_d)
          {
            if (player.field_71071_by.field_70462_a[hasChisel] != null) {
              player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);
            } else {
              break;
            }  }
        }
      }
      if (!world.field_72995_K) {

        world.func_147471_g(x, y, z);
        if (!player.field_71075_bZ.field_75098_d) {

          stack.field_77994_a--;

          if (stack.field_77994_a <= 0)
            stack = null;
        }
      }
    }
    return false;
  }



  public String func_77653_i(ItemStack is) {
    if (is.func_77942_o() && is.field_77990_d.func_74764_b("Completed")) {
      return is.field_77990_d.func_74779_i("ItemName");
    }
    return TFC_Core.translate(func_77658_a());
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.Blueprint.Inst0"));
      if (is.func_77942_o() && !is.field_77990_d.func_74779_i("ItemName").isEmpty()) {
        arraylist.add(TFC_Core.translate("gui.Blueprint.Inst1"));
      }
    } else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlueprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */