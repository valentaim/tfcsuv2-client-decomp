package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;




public class ItemGoldPan
  extends ItemTerra
{
  public static String[] metaNames = new String[] { "", "Sand", "Gravel", "Clay", "Dirt" };
  public IIcon[] icons = new IIcon[metaNames.length];




  public ItemGoldPan() {
    func_77656_e(0);
    func_77627_a(true);
    func_77637_a(TFCTabs.TFC_TOOLS);
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public int func_77639_j() {
    return 1;
  }



  public boolean canStack() {
    return false;
  }



  public IIcon func_77617_a(int i) {
    i &= 0xF;
    if (i == 1)
      return this.icons[1];
    if (i == 2)
      return this.icons[2];
    if (i == 3)
      return this.icons[3];
    if (i == 4)
      return this.icons[4];
    return this.icons[0];
  }



  public void func_94581_a(IIconRegister registerer) {
    this.icons[0] = registerer.func_94245_a("terrafirmacraft:tools/GoldPan_0");
    this.icons[1] = registerer.func_94245_a("terrafirmacraft:tools/GoldPan_1");
    this.icons[2] = registerer.func_94245_a("terrafirmacraft:tools/GoldPan_2");
    this.icons[3] = registerer.func_94245_a("terrafirmacraft:tools/GoldPan_3");
    this.icons[4] = registerer.func_94245_a("terrafirmacraft:tools/GoldPan_4");
  }



  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      return is;
    }

    MovingObjectPosition mop = func_77621_a(world, player, true);

    if (mop == null)
    {
      return is;
    }


    int x = mop.field_72311_b;
    int y = mop.field_72312_c;
    int z = mop.field_72309_d;
    Block blockHit = world.func_147439_a(x, y, z);
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
    {
      if (is.func_77960_j() == 0) {


        if (world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h)
        {
          return is;
        }

        ChunkData cd = TFC_Core.getCDM(world).getData(x >> 4, z >> 4);


        if (cd == null) {

          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentText("The ChunkData returned null, please report this to the developer."));
          return is;
        }


        float toSpawn = world.func_72861_E().func_71569_e((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        if (cd.sluicedAmount < TFCOptions.goldPanLimit || !TFCOptions.enableOverworkingChunks || toSpawn < 1000000.0F) {

          if (TFC_Core.isGravel(blockHit))
          {
            is.func_77964_b(82);
            if (world.field_73012_v.nextInt(10) == 0)
              world.func_147468_f(x, y, z);
            TFC_Core.addPlayerExhaustion(player, 5.0E-4F);
            cd.sluicedAmount++;
          }
          else if (TFC_Core.isSand(blockHit))
          {
            is.func_77964_b(81);
            if (world.field_73012_v.nextInt(10) == 0)
              world.func_147468_f(x, y, z);
            TFC_Core.addPlayerExhaustion(player, 5.0E-4F);
            cd.sluicedAmount++;
          }

        } else {

          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.goldpan.overused", new Object[0]));
        }

      } else {

        int bMeta = world.func_72805_g(x, y + 1, z);
        if (world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h && bMeta > 0 && mop.field_72310_e == 1) {

          int uses = is.func_77960_j() >> 4;
          if (uses > 0) {

            int type = getMetalToDrop(world, player, x, y + 1, z);

            if (type != -1) {

              ItemStack out = new ItemStack(TFCItems.smallOreChunk, 1, type);
              world.func_72956_a((Entity)player, "random.pop", 0.7F, world.field_73012_v.nextFloat() + 1.0F);
              if (!player.field_71071_by.func_70441_a(out))
              {
                player.func_71019_a(out, false);
              }
              TFC_Core.getSkillStats(player).increaseSkill("skill.prospecting", 1);
            }
            uses--;
            if (uses > 0) {
              is.func_77964_b((is.func_77960_j() & 0xF) + (uses << 4));

            }
            else if (world.field_73012_v.nextInt(100) == 0) {

              world.func_72956_a((Entity)player, "terrafirmacraft:item.ceramicbreak", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F);
              is.field_77994_a--;
            } else {

              is.func_77964_b(0);
            }

          }
        } else {

          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.goldpan.useFlowing", new Object[0]));
        }
      }
    }

    return is;
  }


  private int getMetalToDrop(World world, EntityPlayer player, int x, int y, int z) {
    int type = -1;
    int chunkX = x >> 4 << 4;
    int chunkZ = z >> 4 << 4;
    Random rand = new Random(world.func_72905_C() + (((chunkX >> 3) - (chunkZ >> 3)) * (chunkZ >> 3)));
    int randType = rand.nextInt(100);
    SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.prospecting");
    float skillMod = 1.0F - rank.ordinal() * 0.111F;

    if (randType > 25 && world.field_73012_v.nextInt((int)Math.floor((60.0F * skillMod))) == 0) type = 0;
    if (rank.ordinal() > 0 && randType > 50 && world.field_73012_v.nextInt((int)Math.floor((120.0F * skillMod))) == 0) type = 4;
    if (rank.ordinal() > 1 && randType > 75 && world.field_73012_v.nextInt((int)Math.floor((150.0F * skillMod))) == 0) type = 1;
    if (world.field_73012_v.nextInt((int)Math.floor((500.0F * skillMod))) == 0) type = 2;

    return type;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemGoldPan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */