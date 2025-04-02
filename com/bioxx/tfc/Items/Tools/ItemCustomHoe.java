package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEFarmland;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemCustomHoe
  extends ItemHoe
  implements ISize {
  public ItemCustomHoe(Item.ToolMaterial e) {
    super(e);
    func_77637_a(TFCTabs.TFC_TOOLS);
    setNoRepair();
  }



  public void func_94581_a(IIconRegister registerer) {
    String name = func_77658_a().replace("item.", "");
    name = name.replace("IgIn ", "");
    name = name.replace("IgEx ", "");
    name = name.replace("Sed ", "");
    name = name.replace("MM ", "");
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
  }



  public IIcon getIcon(ItemStack stack, int pass) {
    NBTTagCompound nbt = stack.func_77978_p();
    if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
      return TFC_Textures.brokenItem;
    }
    return func_77618_c(stack.func_77960_j(), pass);
  }



  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K || world.func_147439_a(x, y, z) == TFCBlocks.toolRack) {
      return false;
    }

    UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
    if (MinecraftForge.EVENT_BUS.post((Event)event)) {
      return false;
    }
    if (event.getResult() == Event.Result.ALLOW) {

      stack.func_77972_a(1, (EntityLivingBase)player);
      return true;
    }

    Block var8 = world.func_147439_a(x, y, z);
    Block var9 = world.func_147439_a(x, y + 1, z);

    boolean isDirt = TFC_Core.isDirt(var8);

    if (side != 1 || !var9.isAir((IBlockAccess)world, x, y + 1, z) || (!TFC_Core.isGrass(var8) && !isDirt)) {
      return false;
    }

    Block var10 = (var8 == TFCBlocks.dirt || var8 == TFCBlocks.grass || var8 == TFCBlocks.dryGrass) ? TFCBlocks.dirt : ((var8 == TFCBlocks.dirt2 || var8 == TFCBlocks.grass2 || var8 == TFCBlocks.dryGrass2) ? TFCBlocks.dirt2 : null);

    if (var10 != null) {

      int meta = world.func_72805_g(x, y, z);
      if (var10 == TFCBlocks.dirt) {

        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), var10.field_149762_H.func_150498_e(), (var10.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var10.field_149762_H.func_150494_d() * 0.8F);

        if (world.field_72995_K) {
          return true;
        }

        world.func_147465_d(x, y, z, TFCBlocks.tilledSoil, meta, 2);
        world.func_147471_g(x, y, z);
        stack.func_77972_a(1, (EntityLivingBase)player);

        if (isDirt) {

          TEFarmland te = (TEFarmland)world.func_147438_o(x, y, z);
          te.nutrients[0] = 100;
          te.nutrients[1] = 100;
          te.nutrients[2] = 100;
        }
        return true;
      }

      if (var10 == TFCBlocks.dirt2) {

        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), var10.field_149762_H.func_150498_e(), (var10.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var10.field_149762_H.func_150494_d() * 0.8F);

        if (world.field_72995_K) {
          return true;
        }

        world.func_147465_d(x, y, z, TFCBlocks.tilledSoil2, meta, 2);
        world.func_147471_g(x, y, z);
        stack.func_77972_a(1, (EntityLivingBase)player);

        if (isDirt) {

          TEFarmland te = (TEFarmland)world.func_147438_o(x, y, z);
          te.nutrients[0] = 100;
          te.nutrients[1] = 100;
          te.nutrients[2] = 100;
        }
        return true;
      }
    }


    return false;
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
  }



  public int func_77639_j() {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
    }
    return 1;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }



  public boolean canStack() {
    return false;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }



  public int getMaxDamage(ItemStack stack) {
    return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
  }



  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    float digSpeed = super.getDigSpeed(stack, block, meta);

    if (ForgeHooks.isToolEffective(stack, block, meta))
    {
      return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
    }
    return digSpeed;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.FAR;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */