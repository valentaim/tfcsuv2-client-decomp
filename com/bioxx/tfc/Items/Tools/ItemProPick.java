package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemProPick
  extends ItemTerra
{
  private Map<String, ProspectResult> results = new HashMap<>();

  private Random random;


  public ItemProPick() {
    this.field_77777_bU = 1;
    func_77637_a(TFCTabs.TFC_TOOLS);
    setWeight(EnumWeight.LIGHT);
    setSize(EnumSize.SMALL);
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
  }



  public IIcon getIcon(ItemStack stack, int pass) {
    NBTTagCompound nbt = stack.func_77978_p();
    if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
      return TFC_Textures.brokenItem;
    }
    return func_77618_c(stack.func_77960_j(), pass);
  }



  public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    Block block = world.func_147439_a(x, y, z);
    if (!world.field_72995_K) {


      if (block == TFCBlocks.toolRack) {
        return true;
      }

      int meta = world.func_72805_g(x, y, z);

      SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.prospecting");


      if ((block == TFCBlocks.ore || block == TFCBlocks.ore2 || block == TFCBlocks.ore3) && world
        .func_147438_o(x, y, z) instanceof TEOre) {

        TEOre te = (TEOre)world.func_147438_o(x, y, z);
        if (block == TFCBlocks.ore && rank == SkillStats.SkillRank.Master)
          meta = ((BlockOre)block).getOreGrade(te, meta);
        if (block == TFCBlocks.ore2) meta += Global.ORE_METAL.length;
        if (block == TFCBlocks.ore3) meta = meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length;
        tellResult(player, new ItemStack(TFCItems.oreChunk, 1, meta));
        return true;
      }
      if (!TFC_Core.isGround(block)) {

        Iterator<OreSpawnData> iter = WorldGenOre.oreList.values().iterator();
        while (iter.hasNext()) {

          OreSpawnData osd = iter.next();
          if (osd != null && block == osd.block) {

            tellResult(player, new ItemStack(block));
            return true;
          }
        }
      }

      this.random = new Random((x * z + y));
      int chance = 60 + (rank.ordinal() + 1) * 10;

      this.results.clear();


      if (this.random.nextInt(100) >= chance && rank != SkillStats.SkillRank.Master) {

        tellNothingFound(player);
        return true;
      }

      this.results.clear();


      for (int i = -12; i < 12; i++) {

        for (int j = -12; j < 12; j++) {

          for (int k = -12; k < 12; k++) {

            int blockX = x + i;
            int blockY = y + j;
            int blockZ = z + k;

            block = world.func_147439_a(blockX, blockY, blockZ);
            meta = world.func_72805_g(blockX, blockY, blockZ);
            ItemStack ore = null;
            if (block == TFCBlocks.ore && world.func_147438_o(blockX, blockY, blockZ) instanceof TEOre) {

              TEOre te = (TEOre)world.func_147438_o(blockX, blockY, blockZ);
              if (rank == SkillStats.SkillRank.Master) {
                ore = new ItemStack(TFCItems.oreChunk, 1, ((BlockOre)block).getOreGrade(te, meta));
              } else {
                ore = new ItemStack(TFCItems.oreChunk, 1, meta);
              }
            } else if (block == TFCBlocks.ore2) {
              ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
            } else if (block == TFCBlocks.ore3) {
              ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
            } else if (!TFC_Core.isGround(block)) {

              Iterator<OreSpawnData> iter = WorldGenOre.oreList.values().iterator();
              while (iter.hasNext()) {

                OreSpawnData osd = iter.next();
                if (osd != null && block == osd.block) {

                  ore = new ItemStack(block);

                  break;
                }
              }
            } else {
              continue;
            }
            if (ore != null) {

              String oreName = ore.func_82833_r();

              if (this.results.containsKey(oreName)) {
                ((ProspectResult)this.results.get(oreName)).count++;
              } else {
                this.results.put(oreName, new ProspectResult(ore, 1));
              }
              ore = null;
              oreName = null;
            }

            continue;
          }
        }
      }
      if (this.results.isEmpty()) {
        tellNothingFound(player);
      } else {
        tellResult(player);
      }

      this.results.clear();
      this.random = null;


      itemStack.func_77972_a(1, (EntityLivingBase)player);
      if (itemStack.func_77960_j() >= itemStack.func_77958_k()) {
        player.func_71028_bD();
      }
    }
    return true;
  }





  private void tellNothingFound(EntityPlayer player) {
    TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.ProPick.FoundNothing", new Object[0]));
  }





  private void tellResult(EntityPlayer player, ItemStack ore) {
    String oreName = ore.func_77977_a() + ".name";
    TFC_Core.sendInfoMessage(player, (new ChatComponentTranslation("gui.ProPick.Found", new Object[0]))

        .func_150258_a(" ")
        .func_150257_a((IChatComponent)new ChatComponentTranslation(oreName, new Object[0])));
  }





  private void tellResult(EntityPlayer player) {
    String quantityMsg;
    TFC_Core.getSkillStats(player).increaseSkill("skill.prospecting", 1);
    int index = this.random.nextInt(this.results.size());
    ProspectResult result = ((ProspectResult[])this.results.values().toArray((T[])new ProspectResult[0]))[index];
    String oreName = result.itemStack.func_77977_a() + ".name";


    if (result.count < 10) {
      quantityMsg = "gui.ProPick.FoundTraces";
    } else if (result.count < 20) {
      quantityMsg = "gui.ProPick.FoundSmall";
    } else if (result.count < 40) {
      quantityMsg = "gui.ProPick.FoundMedium";
    } else if (result.count < 80) {
      quantityMsg = "gui.ProPick.FoundLarge";
    } else {
      quantityMsg = "gui.ProPick.FoundVeryLarge";
    }
    TFC_Core.sendInfoMessage(player, (new ChatComponentTranslation(quantityMsg, new Object[0]))

        .func_150258_a(" ")
        .func_150257_a((IChatComponent)new ChatComponentTranslation(oreName, new Object[0])));

    oreName = null;
    result = null;
  }



  public boolean canStack() {
    return false;
  }


  private class ProspectResult
  {
    public ItemStack itemStack;
    public int count;

    public ProspectResult(ItemStack itemStack, int count) {
      this.itemStack = itemStack;
      this.count = count;
    }
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
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



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    ItemTerraTool.addSmithingBonusInformation(is, arraylist);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemProPick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */