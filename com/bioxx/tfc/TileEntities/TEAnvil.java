/*      */ package com.bioxx.tfc.TileEntities;
/*      */ 
/*      */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*      */ import com.bioxx.tfc.Core.TFC_Achievements;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*      */ import com.bioxx.tfc.api.Crafting.AnvilRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.AnvilReq;
/*      */ import com.bioxx.tfc.api.Enums.RuleEnum;
/*      */ import com.bioxx.tfc.api.Events.AnvilCraftEvent;
/*      */ import com.bioxx.tfc.api.Events.AnvilWeldEvent;
/*      */ import com.bioxx.tfc.api.HeatIndex;
/*      */ import com.bioxx.tfc.api.HeatRegistry;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*      */ import cpw.mods.fml.common.eventhandler.Event;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TEAnvil
/*      */   extends NetworkTileEntity
/*      */   implements IInventory
/*      */ {
/*      */   public ItemStack[] anvilItemStacks;
/*      */   public ItemStack[] INPUT1_INPUT2_FLUX_SLOT;
/*      */   public int itemCraftingValue;
/*      */   public int[] itemCraftingRules;
/*      */   public int craftingValue;
/*      */   public int craftingRange;
/*      */   public int craftingReq;
/*      */   public String craftingPlan;
/*      */   public int[] stonePair;
/*      */   private byte workedRecently;
/*      */   private static final byte LAG_FIX_DELAY = 5;
/*      */   public AnvilRecipe workRecipe;
/*   63 */   public int anvilTier = AnvilReq.STONE.Tier;
/*      */   
/*      */   public EntityPlayer lastWorker;
/*      */   
/*      */   public static final int INPUT1_SLOT = 1;
/*      */   
/*      */   public static final int WELD1_SLOT = 2;
/*      */   
/*      */   public static final int WELD2_SLOT = 3;
/*      */   public static final int WELDOUT_SLOT = 4;
/*      */   public static final int INPUT2_SLOT = 5;
/*      */   public static final int FLUX_SLOT = 6;
/*      */   public static final int HAMMER_SLOT = 0;
/*      */   public static final String ITEM_CRAFTING_VALUE_TAG = "itemCraftingValue";
/*      */   public static final String ITEM_CRAFTING_RULE_1_TAG = "itemCraftingRule1";
/*      */   public static final String ITEM_CRAFTING_RULE_2_TAG = "itemCraftingRule2";
/*      */   public static final String ITEM_CRAFTING_RULE_3_TAG = "itemCraftingRule3";
/*      */   
/*      */   public TEAnvil() {
/*   82 */     this.anvilItemStacks = new ItemStack[19];
/*   83 */     this.INPUT1_INPUT2_FLUX_SLOT = new ItemStack[3];
/*   84 */     this.itemCraftingValue = 0;
/*   85 */     this.itemCraftingRules = new int[] { -1, -1, -1 };
/*   86 */     this.craftingValue = 0;
/*   87 */     this.anvilTier = AnvilReq.STONE.Tier;
/*   88 */     this.stonePair = new int[] { 0, 0 };
/*   89 */     this.craftingPlan = "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145845_h() {
/*   95 */     if (this.anvilItemStacks[1] == null) {
/*      */       
/*   97 */       this.workRecipe = null;
/*   98 */       this.craftingValue = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  104 */     if (!this.field_145850_b.field_72995_K) {
/*      */ 
/*      */       
/*  107 */       if (getItemCraftingValue() > 150) {
/*  108 */         func_70299_a(1, (ItemStack)null);
/*      */       }
/*  110 */       if (this.workedRecently > 0) {
/*  111 */         this.workedRecently = (byte)(this.workedRecently - 1);
/*      */       }
/*  113 */       TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */ 
/*      */ 
/*      */       
/*  117 */       if (this.workRecipe != null && getItemCraftingValue() != this.itemCraftingValue) {
/*      */         
/*  119 */         this.itemCraftingValue = getItemCraftingValue();
/*  120 */         AnvilManager manager = AnvilManager.getInstance();
/*  121 */         Object[] r = getRecipe(manager);
/*  122 */         AnvilRecipe recipe = (r != null && r[0] != null) ? (AnvilRecipe)r[0] : null;
/*  123 */         ItemStack result = (r != null && r[1] != null) ? (ItemStack)r[1] : null;
/*      */ 
/*      */         
/*  126 */         if (result != null) {
/*      */           
/*  128 */           AnvilCraftEvent eventCraft = new AnvilCraftEvent(this.lastWorker, this, this.anvilItemStacks[1], this.anvilItemStacks[5], result);
/*  129 */           MinecraftForge.EVENT_BUS.post((Event)eventCraft);
/*  130 */           if (!eventCraft.isCanceled()) {
/*      */ 
/*      */             
/*  133 */             TFC_ItemHeat.setTemp(eventCraft.result, TFC_ItemHeat.getTemp(this.anvilItemStacks[1]));
/*      */             
/*  135 */             ItemStack output = eventCraft.result;
/*      */             
/*  137 */             if (output != null && this.lastWorker != null && recipe != null) {
/*      */               
/*  139 */               if (output.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemMiscToolHead) {
/*      */ 
/*      */                 
/*  142 */                 AnvilManager.setDurabilityBuff(output, recipe.getSkillMult(this.lastWorker));
/*  143 */                 AnvilManager.setDamageBuff(output, recipe.getSkillMult(this.lastWorker));
/*      */               }
/*  145 */               else if (output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemTFCArmor) {
/*      */ 
/*      */                 
/*  148 */                 AnvilManager.setDurabilityBuff(output, recipe.getSkillMult(this.lastWorker));
/*      */               } 
/*      */               
/*  151 */               if (output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemIngot) {
/*      */                 
/*  153 */                 Item ingot = output.func_77973_b();
/*  154 */                 if (ingot == TFCItems.blackSteelIngot) {
/*  155 */                   this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlackSteel);
/*  156 */                 } else if (ingot == TFCItems.blueSteelIngot) {
/*  157 */                   this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlueSteel);
/*  158 */                 } else if (ingot == TFCItems.redSteelIngot) {
/*  159 */                   this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achRedSteel);
/*      */                 } 
/*  161 */               } else if (output.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemSteelBucket) {
/*      */                 
/*  163 */                 Item bucket = output.func_77973_b();
/*  164 */                 if (bucket == TFCItems.blueSteelBucketEmpty) {
/*  165 */                   this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achBlueBucket);
/*  166 */                 } else if (bucket == TFCItems.redSteelBucketEmpty) {
/*  167 */                   this.lastWorker.func_71029_a((StatBase)TFC_Achievements.achRedBucket);
/*      */                 } 
/*      */               } 
/*      */               
/*  171 */               increaseSkills(recipe);
/*  172 */               removeRules(1);
/*      */             } 
/*      */             
/*  175 */             func_70299_a(1, output);
/*      */             
/*  177 */             if (this.anvilItemStacks[5] != null) {
/*  178 */               (this.anvilItemStacks[5]).field_77994_a--;
/*      */             }
/*      */           } 
/*  181 */           this.workRecipe = null;
/*  182 */           this.craftingPlan = "";
/*  183 */           this.craftingValue = 0;
/*  184 */           this.lastWorker = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*  188 */     if (this.anvilItemStacks[1] != null && (this.anvilItemStacks[1]).field_77994_a < 1) {
/*  189 */       (this.anvilItemStacks[1]).field_77994_a = 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public void increaseSkills(AnvilRecipe recipe) {
/*  194 */     if (this.lastWorker != null)
/*      */     {
/*  196 */       for (String s : recipe.skillsList)
/*      */       {
/*  198 */         TFC_Core.getSkillStats(this.lastWorker).increaseAnvillSkill(s, recipe);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] getRecipe(AnvilManager manager) {
/*  208 */     Object[] out = new Object[2];
/*      */     
/*  210 */     if (this.itemCraftingValue == this.workRecipe.getCraftingValue())
/*      */     {
/*  212 */       out = manager.findCompleteRecipe(new AnvilRecipe(this.anvilItemStacks[1], this.anvilItemStacks[5], this.craftingPlan, this.workRecipe
/*  213 */             .getCraftingValue(), (this.anvilItemStacks[6] != null), this.anvilTier, null), 
/*  214 */           getItemRules());
/*      */     }
/*  216 */     return out;
/*      */   }
/*      */ 
/*      */   
/*      */   private void fillcopy() {
/*  221 */     this.INPUT1_INPUT2_FLUX_SLOT[0] = this.anvilItemStacks[1];
/*  222 */     this.INPUT1_INPUT2_FLUX_SLOT[1] = this.anvilItemStacks[5];
/*  223 */     this.INPUT1_INPUT2_FLUX_SLOT[2] = this.anvilItemStacks[6];
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean SameSlots() {
/*  228 */     if (ItemStack.func_77989_b(this.anvilItemStacks[1], this.INPUT1_INPUT2_FLUX_SLOT[0]) && 
/*  229 */       ItemStack.func_77989_b(this.anvilItemStacks[5], this.INPUT1_INPUT2_FLUX_SLOT[1]) && 
/*  230 */       ItemStack.func_77989_b(this.anvilItemStacks[6], this.INPUT1_INPUT2_FLUX_SLOT[2]))
/*  231 */       return true; 
/*  232 */     fillcopy();
/*  233 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onSlotChanged(int slot) {
/*  242 */     if ((slot == 1 || slot == 5 || slot == 6) && 
/*  243 */       !SameSlots()) updateRecipe();
/*      */   
/*      */   }
/*      */   
/*      */   public void updateRecipe() {
/*  248 */     AnvilManager manager = AnvilManager.getInstance();
/*  249 */     Object[] plans = manager.getPlans().keySet().toArray();
/*  250 */     Map<String, AnvilRecipe> planList = new HashMap<>();
/*      */     
/*  252 */     for (Object p : plans) {
/*      */       
/*  254 */       AnvilRecipe ar = manager.findMatchingRecipe(new AnvilRecipe(this.anvilItemStacks[1], this.anvilItemStacks[5], (String)p, (this.anvilItemStacks[6] != null), this.anvilTier));
/*      */ 
/*      */       
/*  257 */       if (ar != null) {
/*  258 */         planList.put((String)p, ar);
/*      */       }
/*      */     } 
/*      */     
/*  262 */     if (this.anvilItemStacks[1] != null && this.anvilItemStacks[1].func_77973_b() == TFCItems.bloom)
/*      */     {
/*  264 */       if (this.anvilItemStacks[1].func_77960_j() <= 100 && planList.containsKey("splitbloom")) {
/*  265 */         planList.remove("splitbloom");
/*      */       }
/*      */     }
/*      */     
/*  269 */     if (planList.isEmpty()) {
/*      */       
/*  271 */       this.workRecipe = null;
/*  272 */       this.craftingPlan = "";
/*  273 */       this.lastWorker = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  288 */     if ((this.workRecipe = planList.get(this.craftingPlan)) == null) {
/*      */       
/*  290 */       this.workRecipe = null;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  295 */     if (this.anvilItemStacks[1] != null && this.anvilItemStacks[1].func_77973_b() == TFCItems.bloom && this.workRecipe.getCraftingResult().func_77973_b() == TFCItems.bloom)
/*      */     {
/*  297 */       if (this.anvilItemStacks[1].func_77960_j() < 100) {
/*      */         
/*  299 */         this.craftingPlan = "";
/*  300 */         this.workRecipe = null;
/*      */       }
/*  302 */       else if (this.anvilItemStacks[1].func_77960_j() == 100) {
/*      */         
/*  304 */         this.craftingPlan = "refinebloom";
/*  305 */         this.workRecipe = planList.get(this.craftingPlan);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public AxisAlignedBB getRenderBoundingBox() {
/*  314 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCraftingValue() {
/*  324 */     return (this.workRecipe != null) ? this.workRecipe.getCraftingValue() : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateRules(int rule, int slot) {
/*  329 */     if (this.anvilItemStacks[slot].func_77942_o()) {
/*      */       
/*  331 */       NBTTagCompound tag = this.anvilItemStacks[slot].func_77978_p();
/*  332 */       int rule1 = -1;
/*  333 */       int rule2 = -1;
/*  334 */       if (tag.func_74764_b("itemCraftingRule1"))
/*      */       {
/*  336 */         rule1 = tag.func_74771_c("itemCraftingRule1");
/*      */       }
/*  338 */       if (tag.func_74764_b("itemCraftingRule2"))
/*      */       {
/*  340 */         rule2 = tag.func_74771_c("itemCraftingRule2");
/*      */       }
/*  342 */       if (tag.func_74764_b("itemCraftingRule3"))
/*      */       {
/*  344 */         tag.func_74771_c("itemCraftingRule3");
/*      */       }
/*      */       
/*  347 */       this.itemCraftingRules[2] = rule2;
/*  348 */       this.itemCraftingRules[1] = rule1;
/*  349 */       this.itemCraftingRules[0] = rule;
/*      */       
/*  351 */       tag.func_74774_a("itemCraftingRule1", (byte)this.itemCraftingRules[0]);
/*  352 */       tag.func_74774_a("itemCraftingRule2", (byte)this.itemCraftingRules[1]);
/*  353 */       tag.func_74774_a("itemCraftingRule3", (byte)this.itemCraftingRules[2]);
/*      */       
/*  355 */       this.anvilItemStacks[slot].func_77982_d(tag);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeRules(int slot) {
/*  361 */     if (this.anvilItemStacks[slot].func_77942_o()) {
/*      */       
/*  363 */       NBTTagCompound tag = this.anvilItemStacks[slot].func_77978_p();
/*      */ 
/*      */       
/*  366 */       tag.func_82580_o("itemCraftingRule1");
/*  367 */       tag.func_82580_o("itemCraftingRule2");
/*  368 */       tag.func_82580_o("itemCraftingRule3");
/*  369 */       tag.func_82580_o("itemCraftingValue");
/*      */       
/*  371 */       this.anvilItemStacks[slot].func_77982_d(tag);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] getItemRules() {
/*  377 */     int[] rules = new int[3];
/*  378 */     ItemStack input = this.anvilItemStacks[1];
/*      */     
/*  380 */     if (input != null && input.func_77942_o()) {
/*      */       
/*  382 */       NBTTagCompound tag = input.func_77978_p();
/*  383 */       if (tag.func_74764_b("itemCraftingRule1")) {
/*      */         
/*  385 */         rules[0] = tag.func_74771_c("itemCraftingRule1");
/*      */       }
/*      */       else {
/*      */         
/*  389 */         rules[0] = RuleEnum.ANY.Action;
/*      */       } 
/*      */       
/*  392 */       if (tag.func_74764_b("itemCraftingRule2")) {
/*      */         
/*  394 */         rules[1] = tag.func_74771_c("itemCraftingRule2");
/*      */       }
/*      */       else {
/*      */         
/*  398 */         rules[1] = RuleEnum.ANY.Action;
/*      */       } 
/*      */       
/*  401 */       if (tag.func_74764_b("itemCraftingRule3"))
/*      */       {
/*  403 */         rules[2] = tag.func_74771_c("itemCraftingRule3");
/*      */       }
/*      */       else
/*      */       {
/*  407 */         rules[2] = RuleEnum.ANY.Action;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  412 */       rules[0] = RuleEnum.ANY.Action;
/*  413 */       rules[1] = RuleEnum.ANY.Action;
/*  414 */       rules[2] = RuleEnum.ANY.Action;
/*      */     } 
/*      */     
/*  417 */     return rules;
/*      */   }
/*      */ 
/*      */   
/*      */   private void damageHammer() {
/*  422 */     this.anvilItemStacks[0].func_77964_b(this.anvilItemStacks[0].func_77960_j() + 1);
/*  423 */     if (this.anvilItemStacks[0].func_77960_j() == this.anvilItemStacks[0].func_77958_k()) {
/*  424 */       this.anvilItemStacks[0] = null;
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean canBeWorked() {
/*  429 */     return (isTemperatureWorkable(1).booleanValue() && this.anvilItemStacks[0] != null && (this.anvilItemStacks[1]
/*  430 */       .func_77960_j() == 0 || this.anvilItemStacks[1].func_77973_b().func_77614_k()) && 
/*  431 */       getAnvilType() >= this.craftingReq && this.workedRecently == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionHeavyHammer() {
/*  436 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  438 */       if (canBeWorked()) {
/*      */         
/*  440 */         this.workedRecently = 5;
/*  441 */         setItemCraftingValue(-9);
/*  442 */         updateRules(0, 1);
/*  443 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  447 */       sendAnvilUsePacket(0);
/*      */     } 
/*      */   }
/*      */   public void actionLightHammer() {
/*  451 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  453 */       if (canBeWorked()) {
/*      */         
/*  455 */         this.workedRecently = 5;
/*  456 */         setItemCraftingValue(-3);
/*  457 */         updateRules(0, 1);
/*  458 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  462 */       sendAnvilUsePacket(-1);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionDraw() {
/*  467 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  469 */       if (canBeWorked()) {
/*      */         
/*  471 */         this.workedRecently = 5;
/*  472 */         setItemCraftingValue(-15);
/*  473 */         updateRules(1, 1);
/*  474 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  478 */       sendAnvilUsePacket(1);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionHammer() {
/*  483 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  485 */       if (canBeWorked()) {
/*      */         
/*  487 */         this.workedRecently = 5;
/*  488 */         setItemCraftingValue(-6);
/*  489 */         updateRules(0, 1);
/*  490 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  494 */       sendAnvilUsePacket(2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionPunch() {
/*  499 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  501 */       if (canBeWorked()) {
/*      */         
/*  503 */         this.workedRecently = 5;
/*  504 */         setItemCraftingValue(2);
/*  505 */         updateRules(3, 1);
/*  506 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  510 */       sendAnvilUsePacket(3);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionBend() {
/*  515 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  517 */       if (canBeWorked()) {
/*      */         
/*  519 */         this.workedRecently = 5;
/*  520 */         setItemCraftingValue(7);
/*  521 */         updateRules(4, 1);
/*  522 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  526 */       sendAnvilUsePacket(4);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionUpset() {
/*  531 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  533 */       if (canBeWorked()) {
/*      */         
/*  535 */         this.workedRecently = 5;
/*  536 */         setItemCraftingValue(13);
/*  537 */         updateRules(5, 1);
/*  538 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  542 */       sendAnvilUsePacket(5);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionShrink() {
/*  547 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  549 */       if (canBeWorked()) {
/*      */         
/*  551 */         this.workedRecently = 5;
/*  552 */         setItemCraftingValue(16);
/*  553 */         updateRules(6, 1);
/*  554 */         damageHammer();
/*      */       } 
/*      */     } else {
/*      */       
/*  558 */       sendAnvilUsePacket(6);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void actionWeld() {
/*  563 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  565 */       if (isTemperatureWeldable(2).booleanValue() && isTemperatureWeldable(3).booleanValue() && this.anvilItemStacks[0] != null && (this.anvilItemStacks[2]
/*  566 */         .func_77960_j() == 0 || this.anvilItemStacks[2].func_77973_b().func_77614_k()) && (this.anvilItemStacks[3]
/*  567 */         .func_77960_j() == 0 || this.anvilItemStacks[3].func_77973_b().func_77614_k()) && this.workedRecently == 0 && this.anvilItemStacks[4] == null) {
/*      */ 
/*      */         
/*  570 */         AnvilManager manager = AnvilManager.getInstance();
/*      */         
/*  572 */         AnvilRecipe recipe = new AnvilRecipe(this.anvilItemStacks[2], this.anvilItemStacks[3], "", 0, (this.anvilItemStacks[6] != null), this.anvilTier, null);
/*      */ 
/*      */ 
/*      */         
/*  576 */         AnvilRecipe recipe2 = new AnvilRecipe(this.anvilItemStacks[3], this.anvilItemStacks[2], "", 0, (this.anvilItemStacks[6] != null), this.anvilTier, null);
/*      */ 
/*      */ 
/*      */         
/*  580 */         ItemStack result = manager.findCompleteWeldRecipe(recipe);
/*  581 */         if (result == null) {
/*  582 */           result = manager.findCompleteWeldRecipe(recipe2);
/*      */         }
/*  584 */         AnvilWeldEvent eventCraft = new AnvilWeldEvent(this.entityplayer, this, this.anvilItemStacks[2], this.anvilItemStacks[3], result);
/*  585 */         MinecraftForge.EVENT_BUS.post((Event)eventCraft);
/*  586 */         if (!eventCraft.isCanceled()) {
/*  587 */           result = eventCraft.result;
/*  588 */           if (result != null)
/*      */           {
/*  590 */             TFC_ItemHeat.setTemp(result, (TFC_ItemHeat.getTemp(this.anvilItemStacks[2]) + TFC_ItemHeat.getTemp(this.anvilItemStacks[3])) / 2.0F);
/*  591 */             if (result.field_77994_a <= 0) result.field_77994_a = 1; 
/*  592 */             func_70299_a(4, result);
/*  593 */             func_70299_a(2, (ItemStack)null);
/*  594 */             func_70299_a(3, (ItemStack)null);
/*  595 */             func_70298_a(6, 1);
/*  596 */             damageHammer();
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  603 */       sendAnvilUsePacket(7);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70305_f() {
/*  609 */     this.workRecipe = null;
/*  610 */     if (!this.field_145850_b.field_72995_K && this.anvilItemStacks[0] == null && this.anvilTier == AnvilReq.STONE.Tier) {
/*      */       
/*  612 */       ejectContents();
/*  613 */       this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149729_e(this.stonePair[0]), this.stonePair[1], 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70298_a(int i, int j) {
/*  620 */     if (this.anvilItemStacks[i] != null) {
/*      */       
/*  622 */       if ((this.anvilItemStacks[i]).field_77994_a <= j) {
/*      */         
/*  624 */         ItemStack itemstack = this.anvilItemStacks[i];
/*  625 */         this.anvilItemStacks[i] = null;
/*  626 */         return itemstack;
/*      */       } 
/*  628 */       ItemStack itemstack1 = this.anvilItemStacks[i].func_77979_a(j);
/*  629 */       if ((this.anvilItemStacks[i]).field_77994_a == 0)
/*  630 */         this.anvilItemStacks[i] = null; 
/*  631 */       return itemstack1;
/*      */     } 
/*      */     
/*  634 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void ejectContents() {
/*  640 */     float f3 = 0.05F;
/*      */     
/*  642 */     Random rand = new Random();
/*  643 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/*  644 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/*  645 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*      */     
/*  647 */     for (int i = 0; i < func_70302_i_(); i++) {
/*      */       
/*  649 */       if (this.anvilItemStacks[i] != null) {
/*      */         
/*  651 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.anvilItemStacks[i]);
/*      */         
/*  653 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/*  654 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/*  655 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/*  656 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAnvilType() {
/*  663 */     return this.field_145847_g & 0x7;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70297_j_() {
/*  669 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String func_145825_b() {
/*  675 */     return "Anvil";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_145818_k_() {
/*  681 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_94041_b(int i, ItemStack itemstack) {
/*  687 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setItemCraftingValue(int i) {
/*  692 */     ItemStack input = this.anvilItemStacks[1];
/*  693 */     if (input != null) {
/*      */       
/*  695 */       NBTTagCompound tag = null;
/*  696 */       if (input.func_77942_o()) {
/*      */         
/*  698 */         tag = input.func_77978_p();
/*  699 */         if (tag.func_74764_b("itemCraftingValue"))
/*      */         {
/*  701 */           short craftingValue = tag.func_74765_d("itemCraftingValue");
/*      */           
/*  703 */           tag.func_74777_a("itemCraftingValue", (short)Math.max(0, craftingValue + i));
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*  708 */           tag.func_74777_a("itemCraftingValue", (short)Math.max(0, i));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  713 */         tag = new NBTTagCompound();
/*      */         
/*  715 */         tag.func_74777_a("itemCraftingValue", (short)Math.max(0, i));
/*  716 */         input.func_77982_d(tag);
/*      */       } 
/*      */       
/*  719 */       return true;
/*      */     } 
/*      */     
/*  722 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemCraftingValue() {
/*  727 */     ItemStack input = this.anvilItemStacks[1];
/*  728 */     if (input != null && input.func_77942_o() && input.func_77978_p().func_74764_b("itemCraftingValue"))
/*      */     {
/*  730 */       return input.func_77978_p().func_74765_d("itemCraftingValue");
/*      */     }
/*      */     
/*  733 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemCraftingValueNoSet(int i) {
/*  738 */     ItemStack input = this.anvilItemStacks[i];
/*  739 */     if (input != null && input.func_77942_o() && input.func_77978_p().func_74764_b("itemCraftingValue"))
/*      */     {
/*  741 */       return input.func_77978_p().func_74765_d("itemCraftingValue");
/*      */     }
/*      */     
/*  744 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean isTemperatureWeldable(int i) {
/*  749 */     HeatRegistry manager = HeatRegistry.getInstance();
/*  750 */     ItemStack is = this.anvilItemStacks[i];
/*  751 */     if (TFC_ItemHeat.hasTemp(is)) {
/*      */       
/*  753 */       HeatIndex index = manager.findMatchingIndex(is);
/*  754 */       if (index != null) {
/*      */         
/*  756 */         float temp = TFC_ItemHeat.getTemp(is);
/*  757 */         float weldTemp = index.meltTemp * 0.8F;
/*  758 */         if (temp < index.meltTemp && temp > weldTemp)
/*      */         {
/*      */           
/*  761 */           return Boolean.valueOf((!(is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) || is.func_77960_j() == 0));
/*      */         }
/*      */       } 
/*      */     } 
/*  765 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean isTemperatureWorkable(int i) {
/*  770 */     HeatRegistry manager = HeatRegistry.getInstance();
/*  771 */     ItemStack is = this.anvilItemStacks[i];
/*  772 */     if (TFC_ItemHeat.hasTemp(is)) {
/*      */       
/*  774 */       HeatIndex index = manager.findMatchingIndex(is);
/*  775 */       if (index != null) {
/*      */         
/*  777 */         float temp = TFC_ItemHeat.getTemp(is);
/*  778 */         float workTemp = index.meltTemp * 0.6F;
/*  779 */         if (temp < index.meltTemp && temp > workTemp)
/*      */         {
/*      */           
/*  782 */           return Boolean.valueOf((!(is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) || is.func_77960_j() == 0));
/*      */         }
/*      */       } 
/*      */     } 
/*  786 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70299_a(int i, ItemStack itemstack) {
/*  792 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/*      */       
/*  794 */       if (itemstack.field_77994_a > func_70297_j_())
/*  795 */         itemstack.field_77994_a = func_70297_j_(); 
/*  796 */       if (itemstack.field_77994_a <= 0)
/*  797 */         itemstack = null; 
/*      */     } 
/*  799 */     this.anvilItemStacks[i] = itemstack;
/*  800 */     onSlotChanged(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70300_a(EntityPlayer entityplayer) {
/*  806 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70295_k_() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70302_i_() {
/*  817 */     return this.anvilItemStacks.length;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70301_a(int i) {
/*  823 */     return this.anvilItemStacks[i];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70304_b(int var1) {
/*  829 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145841_b(NBTTagCompound nbt) {
/*  835 */     super.func_145841_b(nbt);
/*  836 */     NBTTagList nbttaglist = new NBTTagList();
/*  837 */     for (int i = 0; i < this.anvilItemStacks.length; i++) {
/*      */       
/*  839 */       if (this.anvilItemStacks[i] != null) {
/*      */         
/*  841 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  842 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  843 */         this.anvilItemStacks[i].func_77955_b(nbttagcompound1);
/*  844 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */       } 
/*      */     } 
/*  847 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*  848 */     nbt.func_74768_a("Tier", this.anvilTier);
/*  849 */     nbt.func_74783_a("stonePair", this.stonePair);
/*  850 */     nbt.func_74778_a("plan", this.craftingPlan);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/*  856 */     super.func_145839_a(nbttagcompound);
/*  857 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/*  858 */     this.anvilItemStacks = new ItemStack[func_70302_i_()];
/*  859 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/*  861 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  862 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/*  863 */       if (byte0 >= 0 && byte0 < this.anvilItemStacks.length)
/*  864 */         this.anvilItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*      */     } 
/*  866 */     this.anvilTier = nbttagcompound.func_74762_e("Tier");
/*  867 */     this.stonePair = nbttagcompound.func_74759_k("stonePair");
/*  868 */     this.craftingPlan = nbttagcompound.func_74779_i("plan");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlan(String s) {
/*  873 */     if (this.field_145850_b.field_72995_K)
/*  874 */       sendPlanPacket(s); 
/*  875 */     this.craftingPlan = s;
/*  876 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleInitPacket(NBTTagCompound nbt) {
/*  881 */     this.anvilTier = nbt.func_74762_e("AnvilTier");
/*  882 */     this.stonePair = nbt.func_74759_k("stonePair");
/*  883 */     if (nbt.func_74764_b("hammer"))
/*      */     {
/*  885 */       this.anvilItemStacks[0] = ItemStack.func_77949_a(nbt.func_74775_l("hammer"));
/*      */     }
/*  887 */     if (nbt.func_74764_b("input"))
/*      */     {
/*  889 */       this.anvilItemStacks[1] = ItemStack.func_77949_a(nbt.func_74775_l("input"));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleDataPacket(NBTTagCompound nbt) {
/*  895 */     boolean weldFlag, soundFlag = false;
/*  896 */     switch (nbt.func_74762_e("action")) {
/*      */ 
/*      */       
/*      */       case -1:
/*  900 */         soundFlag = canBeWorked();
/*  901 */         actionLightHammer();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 0:
/*  906 */         soundFlag = canBeWorked();
/*  907 */         actionHeavyHammer();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1:
/*  912 */         soundFlag = canBeWorked();
/*  913 */         actionDraw();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  918 */         soundFlag = canBeWorked();
/*  919 */         actionHammer();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  924 */         soundFlag = canBeWorked();
/*  925 */         actionPunch();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  930 */         soundFlag = canBeWorked();
/*  931 */         actionBend();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 5:
/*  936 */         soundFlag = canBeWorked();
/*  937 */         actionUpset();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*  942 */         soundFlag = canBeWorked();
/*  943 */         actionShrink();
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/*  948 */         weldFlag = (this.anvilItemStacks[4] == null);
/*  949 */         actionWeld();
/*  950 */         soundFlag = (weldFlag && this.anvilItemStacks[4] != null);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 8:
/*  955 */         if (!this.field_145850_b.field_72995_K) {
/*      */           
/*  957 */           setPlan(nbt.func_74779_i("plan"));
/*  958 */           this.lastWorker = this.field_145850_b.func_72924_a(nbt.func_74779_i("playername"));
/*  959 */           this.lastWorker.openGui(TerraFirmaCraft.instance, 21, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  960 */           updateRecipe();
/*      */         } 
/*      */         return;
/*      */     } 
/*      */     
/*  965 */     if (soundFlag) {
/*  966 */       this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "terrafirmacraft:anvil.metalimpact", 0.1F, 0.1F + this.field_145850_b.field_73012_v.nextFloat() / 4.0F);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void createInitNBT(NBTTagCompound nbt) {
/*  972 */     nbt.func_74768_a("AnvilTier", this.anvilTier);
/*  973 */     nbt.func_74783_a("stonePair", this.stonePair);
/*  974 */     if (this.anvilItemStacks[0] != null) {
/*      */       
/*  976 */       NBTTagCompound hammerNBT = new NBTTagCompound();
/*  977 */       hammerNBT = this.anvilItemStacks[0].func_77955_b(hammerNBT);
/*  978 */       nbt.func_74782_a("hammer", (NBTBase)hammerNBT);
/*      */     } 
/*  980 */     if (this.anvilItemStacks[1] != null) {
/*      */       
/*  982 */       NBTTagCompound inputNBT = new NBTTagCompound();
/*  983 */       inputNBT = this.anvilItemStacks[1].func_77955_b(inputNBT);
/*  984 */       nbt.func_74782_a("input", (NBTBase)inputNBT);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   private void sendAnvilUsePacket(int i) {
/*  991 */     NBTTagCompound nbt = new NBTTagCompound();
/*  992 */     nbt.func_74768_a("action", i);
/*  993 */     nbt.func_74778_a("playername", (PlayerManagerTFC.getInstance().getClientPlayer()).playerName);
/*  994 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   private void sendPlanPacket(String plan) {
/* 1000 */     NBTTagCompound nbt = new NBTTagCompound();
/* 1001 */     nbt.func_74768_a("action", 8);
/* 1002 */     nbt.func_74778_a("plan", plan);
/* 1003 */     nbt.func_74778_a("playername", (PlayerManagerTFC.getInstance().getClientPlayer()).playerName);
/* 1004 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */