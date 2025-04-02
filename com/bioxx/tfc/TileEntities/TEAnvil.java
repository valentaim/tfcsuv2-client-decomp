package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Enums.RuleEnum;
import com.bioxx.tfc.api.Events.AnvilCraftEvent;
import com.bioxx.tfc.api.Events.AnvilWeldEvent;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;










public class TEAnvil
  extends NetworkTileEntity
  implements IInventory
{
  public ItemStack[] anvilItemStacks;
  public ItemStack[] INPUT1_INPUT2_FLUX_SLOT;
  public int itemCraftingValue;
  public int[] itemCraftingRules;
  public int craftingValue;
  public int craftingRange;
  public int craftingReq;
  public String craftingPlan;
  public int[] stonePair;
  private byte workedRecently;
  private static final byte LAG_FIX_DELAY = 5;
  public AnvilRecipe workRecipe;
  public int anvilTier = AnvilReq.STONE.Tier;

  public EntityPlayer lastWorker;

  public static final int INPUT1_SLOT = 1;

  public static final int WELD1_SLOT = 2;

  public static final int WELD2_SLOT = 3;
  public static final int WELDOUT_SLOT = 4;
  public static final int INPUT2_SLOT = 5;
  public static final int FLUX_SLOT = 6;
  public static final int HAMMER_SLOT = 0;
  public static final String ITEM_CRAFTING_VALUE_TAG = "itemCraftingValue";
  public static final String ITEM_CRAFTING_RULE_1_TAG = "itemCraftingRule1";
  public static final String ITEM_CRAFTING_RULE_2_TAG = "itemCraftingRule2";
  public static final String ITEM_CRAFTING_RULE_3_TAG = "itemCraftingRule3";

  public TEAnvil() {
    this.anvilItemStacks = new ItemStack[19];
    this.INPUT1_INPUT2_FLUX_SLOT = new ItemStack[3];
    this.itemCraftingValue = 0;
    this.itemCraftingRules = new int[] { -1, -1, -1 };
    this.craftingValue = 0;
    this.anvilTier = AnvilReq.STONE.Tier;
    this.stonePair = new int[] { 0, 0 };
    this.craftingPlan = "";
  }



  public void func_145845_h() {
    if (this.anvilItemStacks[1] == null) {

      this.workRecipe = null;
      this.craftingValue = 0;
    }




    if (!this.field_145850_b.field_72995_K) {


      if (getItemCraftingValue() > 150) {
        func_70299_a(1, (ItemStack)null);
      }
      if (this.workedRecently > 0) {
        this.workedRecently = (byte)(this.workedRecently - 1);
      }
      TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);



      if (this.workRecipe != null && getItemCraftingValue() != this.itemCraftingValue) {

        this.itemCraftingValue = getItemCraftingValue();
        AnvilManager manager = AnvilManager.getInstance();
        Object[] r = getRecipe(manager);
        AnvilRecipe recipe = (r != null && r[0] != null) ? (AnvilRecipe)r[0] : null;
        ItemStack result = (r != null && r[1] != null) ? (ItemStack)r[1] : null;


        if (result != null) {

          AnvilCraftEvent eventCraft = new AnvilCraftEvent(this.lastWorker, this, this.anvilItemStacks[1], this.anvilItemStacks[5], result);
          MinecraftForge.EVENT_BUS.post((Event)eventCraft);
          if (!eventCraft.isCanceled()) {


            TFC_ItemHeat.setTemp(eventCraft.result, TFC_ItemHeat.getTemp(this.anvilItemStacks[1]));

            ItemStack output = eventCraft.result;

            if (output != null && this.lastWorker != null && recipe != null) {

              if (output.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemMiscToolHead) {


                AnvilManager.setDurabilityBuff(output, recipe.getSkillMult(this.lastWorker));
                AnvilManager.setDamageBuff(output, recipe.getSkillMult(this.lastWorker));
              }
              else if (output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemTFCArmor) {


                AnvilManager.setDurabilityBuff(output, recipe.getSkillMult(this.lastWorker));
              }

              if (output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemIngot) {

                Item ingot = output.func_77973_b();
                if (ingot == TFCItems.blackSteelIngot) {
                  this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlackSteel);
                } else if (ingot == TFCItems.blueSteelIngot) {
                  this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlueSteel);
                } else if (ingot == TFCItems.redSteelIngot) {
                  this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achRedSteel);
                }
              } else if (output.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemSteelBucket) {

                Item bucket = output.func_77973_b();
                if (bucket == TFCItems.blueSteelBucketEmpty) {
                  this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlueBucket);
                } else if (bucket == TFCItems.redSteelBucketEmpty) {
                  this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achRedBucket);
                }
              }

              increaseSkills(recipe);
              removeRules(1);
            }

            func_70299_a(1, output);

            if (this.anvilItemStacks[5] != null) {
              (this.anvilItemStacks[5]).field_77994_a--;
            }
          }
          this.workRecipe = null;
          this.craftingPlan = "";
          this.craftingValue = 0;
          this.lastWorker = null;
        }
      }
    }
    if (this.anvilItemStacks[1] != null && (this.anvilItemStacks[1]).field_77994_a < 1) {
      (this.anvilItemStacks[1]).field_77994_a = 1;
    }
  }

  public void increaseSkills(AnvilRecipe recipe) {
    if (this.lastWorker != null)
    {
      for (String s : recipe.skillsList)
      {
        TFC_Core.getSkillStats(this.lastWorker).increaseAnvillSkill(s, recipe);
      }
    }
  }





  public Object[] getRecipe(AnvilManager manager) {
    Object[] out = new Object[2];

    if (this.itemCraftingValue == this.workRecipe.getCraftingValue())
    {
      out = manager.findCompleteRecipe(new AnvilRecipe(this.anvilItemStacks[1], this.anvilItemStacks[5], this.craftingPlan, this.workRecipe
            .getCraftingValue(), (this.anvilItemStacks[6] != null), this.anvilTier, null),
          getItemRules());
    }
    return out;
  }


  private void fillcopy() {
    this.INPUT1_INPUT2_FLUX_SLOT[0] = this.anvilItemStacks[1];
    this.INPUT1_INPUT2_FLUX_SLOT[1] = this.anvilItemStacks[5];
    this.INPUT1_INPUT2_FLUX_SLOT[2] = this.anvilItemStacks[6];
  }


  private boolean SameSlots() {
    if (ItemStack.func_77989_b(this.anvilItemStacks[1], this.INPUT1_INPUT2_FLUX_SLOT[0]) &&
      ItemStack.func_77989_b(this.anvilItemStacks[5], this.INPUT1_INPUT2_FLUX_SLOT[1]) &&
      ItemStack.func_77989_b(this.anvilItemStacks[6], this.INPUT1_INPUT2_FLUX_SLOT[2]))
      return true;
    fillcopy();
    return false;
  }






  public void onSlotChanged(int slot) {
    if ((slot == 1 || slot == 5 || slot == 6) &&
      !SameSlots()) updateRecipe();

  }

  public void updateRecipe() {
    AnvilManager manager = AnvilManager.getInstance();
    Object[] plans = manager.getPlans().keySet().toArray();
    Map<String, AnvilRecipe> planList = new HashMap<>();

    for (Object p : plans) {

      AnvilRecipe ar = manager.findMatchingRecipe(new AnvilRecipe(this.anvilItemStacks[1], this.anvilItemStacks[5], (String)p, (this.anvilItemStacks[6] != null), this.anvilTier));


      if (ar != null) {
        planList.put((String)p, ar);
      }
    }

    if (this.anvilItemStacks[1] != null && this.anvilItemStacks[1].func_77973_b() == TFCItems.bloom)
    {
      if (this.anvilItemStacks[1].func_77960_j() <= 100 && planList.containsKey("splitbloom")) {
        planList.remove("splitbloom");
      }
    }

    if (planList.isEmpty()) {

      this.workRecipe = null;
      this.craftingPlan = "";
      this.lastWorker = null;






      return;
    }






    if ((this.workRecipe = planList.get(this.craftingPlan)) == null) {

      this.workRecipe = null;

      return;
    }

    if (this.anvilItemStacks[1] != null && this.anvilItemStacks[1].func_77973_b() == TFCItems.bloom && this.workRecipe.getCraftingResult().func_77973_b() == TFCItems.bloom)
    {
      if (this.anvilItemStacks[1].func_77960_j() < 100) {

        this.craftingPlan = "";
        this.workRecipe = null;
      }
      else if (this.anvilItemStacks[1].func_77960_j() == 100) {

        this.craftingPlan = "refinebloom";
        this.workRecipe = planList.get(this.craftingPlan);
      }
    }
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }







  public int getCraftingValue() {
    return (this.workRecipe != null) ? this.workRecipe.getCraftingValue() : 0;
  }


  public void updateRules(int rule, int slot) {
    if (this.anvilItemStacks[slot].func_77942_o()) {

      NBTTagCompound tag = this.anvilItemStacks[slot].func_77978_p();
      int rule1 = -1;
      int rule2 = -1;
      if (tag.func_74764_b("itemCraftingRule1"))
      {
        rule1 = tag.func_74771_c("itemCraftingRule1");
      }
      if (tag.func_74764_b("itemCraftingRule2"))
      {
        rule2 = tag.func_74771_c("itemCraftingRule2");
      }
      if (tag.func_74764_b("itemCraftingRule3"))
      {
        tag.func_74771_c("itemCraftingRule3");
      }

      this.itemCraftingRules[2] = rule2;
      this.itemCraftingRules[1] = rule1;
      this.itemCraftingRules[0] = rule;

      tag.func_74774_a("itemCraftingRule1", (byte)this.itemCraftingRules[0]);
      tag.func_74774_a("itemCraftingRule2", (byte)this.itemCraftingRules[1]);
      tag.func_74774_a("itemCraftingRule3", (byte)this.itemCraftingRules[2]);

      this.anvilItemStacks[slot].func_77982_d(tag);
    }
  }


  public void removeRules(int slot) {
    if (this.anvilItemStacks[slot].func_77942_o()) {

      NBTTagCompound tag = this.anvilItemStacks[slot].func_77978_p();


      tag.func_82580_o("itemCraftingRule1");
      tag.func_82580_o("itemCraftingRule2");
      tag.func_82580_o("itemCraftingRule3");
      tag.func_82580_o("itemCraftingValue");

      this.anvilItemStacks[slot].func_77982_d(tag);
    }
  }


  public int[] getItemRules() {
    int[] rules = new int[3];
    ItemStack input = this.anvilItemStacks[1];

    if (input != null && input.func_77942_o()) {

      NBTTagCompound tag = input.func_77978_p();
      if (tag.func_74764_b("itemCraftingRule1")) {

        rules[0] = tag.func_74771_c("itemCraftingRule1");
      }
      else {

        rules[0] = RuleEnum.ANY.Action;
      }

      if (tag.func_74764_b("itemCraftingRule2")) {

        rules[1] = tag.func_74771_c("itemCraftingRule2");
      }
      else {

        rules[1] = RuleEnum.ANY.Action;
      }

      if (tag.func_74764_b("itemCraftingRule3"))
      {
        rules[2] = tag.func_74771_c("itemCraftingRule3");
      }
      else
      {
        rules[2] = RuleEnum.ANY.Action;
      }

    } else {

      rules[0] = RuleEnum.ANY.Action;
      rules[1] = RuleEnum.ANY.Action;
      rules[2] = RuleEnum.ANY.Action;
    }

    return rules;
  }


  private void damageHammer() {
    this.anvilItemStacks[0].func_77964_b(this.anvilItemStacks[0].func_77960_j() + 1);
    if (this.anvilItemStacks[0].func_77960_j() == this.anvilItemStacks[0].func_77958_k()) {
      this.anvilItemStacks[0] = null;
    }
  }

  private boolean canBeWorked() {
    return (isTemperatureWorkable(1).booleanValue() && this.anvilItemStacks[0] != null && (this.anvilItemStacks[1]
      .func_77960_j() == 0 || this.anvilItemStacks[1].func_77973_b().func_77614_k()) &&
      getAnvilType() >= this.craftingReq && this.workedRecently == 0);
  }


  public void actionHeavyHammer() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(-9);
        updateRules(0, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(0);
    }
  }
  public void actionLightHammer() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(-3);
        updateRules(0, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(-1);
    }
  }

  public void actionDraw() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(-15);
        updateRules(1, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(1);
    }
  }

  public void actionHammer() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(-6);
        updateRules(0, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(2);
    }
  }

  public void actionPunch() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(2);
        updateRules(3, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(3);
    }
  }

  public void actionBend() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(7);
        updateRules(4, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(4);
    }
  }

  public void actionUpset() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(13);
        updateRules(5, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(5);
    }
  }

  public void actionShrink() {
    if (!this.field_145850_b.field_72995_K) {

      if (canBeWorked()) {

        this.workedRecently = 5;
        setItemCraftingValue(16);
        updateRules(6, 1);
        damageHammer();
      }
    } else {

      sendAnvilUsePacket(6);
    }
  }

  public void actionWeld() {
    if (!this.field_145850_b.field_72995_K) {

      if (isTemperatureWeldable(2).booleanValue() && isTemperatureWeldable(3).booleanValue() && this.anvilItemStacks[0] != null && (this.anvilItemStacks[2]
        .func_77960_j() == 0 || this.anvilItemStacks[2].func_77973_b().func_77614_k()) && (this.anvilItemStacks[3]
        .func_77960_j() == 0 || this.anvilItemStacks[3].func_77973_b().func_77614_k()) && this.workedRecently == 0 && this.anvilItemStacks[4] == null) {


        AnvilManager manager = AnvilManager.getInstance();

        AnvilRecipe recipe = new AnvilRecipe(this.anvilItemStacks[2], this.anvilItemStacks[3], "", 0, (this.anvilItemStacks[6] != null), this.anvilTier, null);



        AnvilRecipe recipe2 = new AnvilRecipe(this.anvilItemStacks[3], this.anvilItemStacks[2], "", 0, (this.anvilItemStacks[6] != null), this.anvilTier, null);



        ItemStack result = manager.findCompleteWeldRecipe(recipe);
        if (result == null) {
          result = manager.findCompleteWeldRecipe(recipe2);
        }
        AnvilWeldEvent eventCraft = new AnvilWeldEvent(this.entityplayer, this, this.anvilItemStacks[2], this.anvilItemStacks[3], result);
        MinecraftForge.EVENT_BUS.post((Event)eventCraft);
        if (!eventCraft.isCanceled()) {
          result = eventCraft.result;
          if (result != null)
          {
            TFC_ItemHeat.setTemp(result, (TFC_ItemHeat.getTemp(this.anvilItemStacks[2]) + TFC_ItemHeat.getTemp(this.anvilItemStacks[3])) / 2.0F);
            if (result.field_77994_a <= 0) result.field_77994_a = 1;
            func_70299_a(4, result);
            func_70299_a(2, (ItemStack)null);
            func_70299_a(3, (ItemStack)null);
            func_70298_a(6, 1);
            damageHammer();
          }

        }
      }
    } else {

      sendAnvilUsePacket(7);
    }
  }


  public void func_70305_f() {
    this.workRecipe = null;
    if (!this.field_145850_b.field_72995_K && this.anvilItemStacks[0] == null && this.anvilTier == AnvilReq.STONE.Tier) {

      ejectContents();
      this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149729_e(this.stonePair[0]), this.stonePair[1], 2);
    }
  }



  public ItemStack func_70298_a(int i, int j) {
    if (this.anvilItemStacks[i] != null) {

      if ((this.anvilItemStacks[i]).field_77994_a <= j) {

        ItemStack itemstack = this.anvilItemStacks[i];
        this.anvilItemStacks[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.anvilItemStacks[i].func_77979_a(j);
      if ((this.anvilItemStacks[i]).field_77994_a == 0)
        this.anvilItemStacks[i] = null;
      return itemstack1;
    }

    return null;
  }



  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.anvilItemStacks[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.anvilItemStacks[i]);

        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
    }
  }


  public int getAnvilType() {
    return this.field_145847_g & 0x7;
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Anvil";
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }


  public boolean setItemCraftingValue(int i) {
    ItemStack input = this.anvilItemStacks[1];
    if (input != null) {

      NBTTagCompound tag = null;
      if (input.func_77942_o()) {

        tag = input.func_77978_p();
        if (tag.func_74764_b("itemCraftingValue"))
        {
          short craftingValue = tag.func_74765_d("itemCraftingValue");

          tag.func_74777_a("itemCraftingValue", (short)Math.max(0, craftingValue + i));

        }
        else
        {
          tag.func_74777_a("itemCraftingValue", (short)Math.max(0, i));
        }

      } else {

        tag = new NBTTagCompound();

        tag.func_74777_a("itemCraftingValue", (short)Math.max(0, i));
        input.func_77982_d(tag);
      }

      return true;
    }

    return false;
  }


  public int getItemCraftingValue() {
    ItemStack input = this.anvilItemStacks[1];
    if (input != null && input.func_77942_o() && input.func_77978_p().func_74764_b("itemCraftingValue"))
    {
      return input.func_77978_p().func_74765_d("itemCraftingValue");
    }

    return 0;
  }


  public int getItemCraftingValueNoSet(int i) {
    ItemStack input = this.anvilItemStacks[i];
    if (input != null && input.func_77942_o() && input.func_77978_p().func_74764_b("itemCraftingValue"))
    {
      return input.func_77978_p().func_74765_d("itemCraftingValue");
    }

    return 0;
  }


  public Boolean isTemperatureWeldable(int i) {
    HeatRegistry manager = HeatRegistry.getInstance();
    ItemStack is = this.anvilItemStacks[i];
    if (TFC_ItemHeat.hasTemp(is)) {

      HeatIndex index = manager.findMatchingIndex(is);
      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        float weldTemp = index.meltTemp * 0.8F;
        if (temp < index.meltTemp && temp > weldTemp)
        {

          return Boolean.valueOf((!(is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) || is.func_77960_j() == 0));
        }
      }
    }
    return Boolean.valueOf(false);
  }


  public Boolean isTemperatureWorkable(int i) {
    HeatRegistry manager = HeatRegistry.getInstance();
    ItemStack is = this.anvilItemStacks[i];
    if (TFC_ItemHeat.hasTemp(is)) {

      HeatIndex index = manager.findMatchingIndex(is);
      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        float workTemp = index.meltTemp * 0.6F;
        if (temp < index.meltTemp && temp > workTemp)
        {

          return Boolean.valueOf((!(is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) || is.func_77960_j() == 0));
        }
      }
    }
    return Boolean.valueOf(false);
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {

      if (itemstack.field_77994_a > func_70297_j_())
        itemstack.field_77994_a = func_70297_j_();
      if (itemstack.field_77994_a <= 0)
        itemstack = null;
    }
    this.anvilItemStacks[i] = itemstack;
    onSlotChanged(i);
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public int func_70302_i_() {
    return this.anvilItemStacks.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.anvilItemStacks[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.anvilItemStacks.length; i++) {

      if (this.anvilItemStacks[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.anvilItemStacks[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
    nbt.func_74768_a("Tier", this.anvilTier);
    nbt.func_74783_a("stonePair", this.stonePair);
    nbt.func_74778_a("plan", this.craftingPlan);
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.anvilItemStacks = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.anvilItemStacks.length)
        this.anvilItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
    }
    this.anvilTier = nbttagcompound.func_74762_e("Tier");
    this.stonePair = nbttagcompound.func_74759_k("stonePair");
    this.craftingPlan = nbttagcompound.func_74779_i("plan");
  }


  public void setPlan(String s) {
    if (this.field_145850_b.field_72995_K)
      sendPlanPacket(s);
    this.craftingPlan = s;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.anvilTier = nbt.func_74762_e("AnvilTier");
    this.stonePair = nbt.func_74759_k("stonePair");
    if (nbt.func_74764_b("hammer"))
    {
      this.anvilItemStacks[0] = ItemStack.func_77949_a(nbt.func_74775_l("hammer"));
    }
    if (nbt.func_74764_b("input"))
    {
      this.anvilItemStacks[1] = ItemStack.func_77949_a(nbt.func_74775_l("input"));
    }
  }


  public void handleDataPacket(NBTTagCompound nbt) {
    boolean weldFlag, soundFlag = false;
    switch (nbt.func_74762_e("action")) {


      case -1:
        soundFlag = canBeWorked();
        actionLightHammer();
        break;


      case 0:
        soundFlag = canBeWorked();
        actionHeavyHammer();
        break;


      case 1:
        soundFlag = canBeWorked();
        actionDraw();
        break;


      case 2:
        soundFlag = canBeWorked();
        actionHammer();
        break;


      case 3:
        soundFlag = canBeWorked();
        actionPunch();
        break;


      case 4:
        soundFlag = canBeWorked();
        actionBend();
        break;


      case 5:
        soundFlag = canBeWorked();
        actionUpset();
        break;


      case 6:
        soundFlag = canBeWorked();
        actionShrink();
        break;


      case 7:
        weldFlag = (this.anvilItemStacks[4] == null);
        actionWeld();
        soundFlag = (weldFlag && this.anvilItemStacks[4] != null);
        break;


      case 8:
        if (!this.field_145850_b.field_72995_K) {

          setPlan(nbt.func_74779_i("plan"));
          this.lastWorker = this.field_145850_b.func_72924_a(nbt.func_74779_i("playername"));
          this.lastWorker.openGui(TerraFirmaCraft.instance, 21, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
          updateRecipe();
        }
        return;
    }

    if (soundFlag) {
      this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "terrafirmacraft:anvil.metalimpact", 0.1F, 0.1F + this.field_145850_b.field_73012_v.nextFloat() / 4.0F);
    }
  }


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74768_a("AnvilTier", this.anvilTier);
    nbt.func_74783_a("stonePair", this.stonePair);
    if (this.anvilItemStacks[0] != null) {

      NBTTagCompound hammerNBT = new NBTTagCompound();
      hammerNBT = this.anvilItemStacks[0].func_77955_b(hammerNBT);
      nbt.func_74782_a("hammer", (NBTBase)hammerNBT);
    }
    if (this.anvilItemStacks[1] != null) {

      NBTTagCompound inputNBT = new NBTTagCompound();
      inputNBT = this.anvilItemStacks[1].func_77955_b(inputNBT);
      nbt.func_74782_a("input", (NBTBase)inputNBT);
    }
  }


  @SideOnly(Side.CLIENT)
  private void sendAnvilUsePacket(int i) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("action", i);
    nbt.func_74778_a("playername", (PlayerManagerTFC.getInstance().getClientPlayer()).playerName);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  @SideOnly(Side.CLIENT)
  private void sendPlanPacket(String plan) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("action", 8);
    nbt.func_74778_a("plan", plan);
    nbt.func_74778_a("playername", (PlayerManagerTFC.getInstance().getClientPlayer()).playerName);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */