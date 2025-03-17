/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Recipes;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import fof.tfcsu.Register.tfcsuItems;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onCrafting(PlayerEvent.ItemCraftedEvent e) {
/*  43 */     EntityPlayer player = e.player;
/*  44 */     ItemStack itemstack = e.crafting;
/*  45 */     Item item = itemstack.func_77973_b();
/*  46 */     int itemDamage = itemstack.func_77960_j();
/*  47 */     IInventory iinventory = e.craftMatrix;
/*     */ 
/*     */     
/*  50 */     if (iinventory != null) {
/*     */ 
/*     */       
/*  53 */       if (item == TFCItems.stoneBrick) {
/*     */         
/*  55 */         List<ItemStack> chisels = OreDictionary.getOres("itemChisel", false);
/*  56 */         handleItem(player, iinventory, chisels);
/*     */       }
/*  58 */       else if (item == TFCItems.singlePlank || item == 
/*  59 */         Item.func_150898_a(TFCBlocks.woodSupportH) || item == Item.func_150898_a(TFCBlocks.woodSupportH2) || item == 
/*  60 */         Item.func_150898_a(TFCBlocks.woodSupportV) || item == Item.func_150898_a(TFCBlocks.woodSupportV2)) {
/*     */         
/*  62 */         List<ItemStack> axes = OreDictionary.getOres("itemAxe", false);
/*  63 */         List<ItemStack> saws = OreDictionary.getOres("itemSaw", false);
/*  64 */         handleItem(player, iinventory, axes);
/*  65 */         handleItem(player, iinventory, saws);
/*     */       }
/*  67 */       else if (item == TFCItems.wool) {
/*     */         
/*  69 */         List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
/*  70 */         handleItem(player, iinventory, knives);
/*  71 */         int size = 0;
/*  72 */         for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */           
/*  74 */           if (iinventory.func_70301_a(i) != null) {
/*     */             
/*  76 */             if (iinventory.func_70301_a(i).func_77973_b() == TFCItems.sheepSkin)
/*  77 */               size = iinventory.func_70301_a(i).func_77960_j(); 
/*  78 */             if (iinventory.func_70301_a(i).func_77973_b() == tfcsuItems.pbearSkin)
/*  79 */               size = iinventory.func_70301_a(i).func_77960_j(); 
/*     */           } 
/*     */         } 
/*  82 */         TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.hide, 1, size), player);
/*     */       }
/*  84 */       else if (item == TFCItems.woolYarn) {
/*     */         
/*  86 */         handleItem(player, iinventory, Recipes.spindle);
/*     */       }
/*  88 */       else if (item == TFCItems.powder && itemDamage == 0) {
/*     */         
/*  90 */         List<ItemStack> hammers = OreDictionary.getOres("itemHammer", false);
/*  91 */         handleItem(player, iinventory, hammers);
/*     */       } 
/*     */ 
/*     */       
/*  95 */       triggerAchievements(player, itemstack, item, itemDamage);
/*     */ 
/*     */       
/*  98 */       if (item == Item.func_150898_a(TFCBlocks.workbench)) {
/*     */         
/* 100 */         if (!player.getEntityData().func_74764_b("craftingTable")) {
/* 101 */           player.field_71071_by.func_146027_a(Item.func_150898_a(TFCBlocks.workbench), -1);
/*     */         }
/* 103 */         if (!player.field_70170_p.field_72995_K)
/*     */         {
/* 105 */           if (!player.getEntityData().func_74764_b("craftingTable")) {
/*     */             
/* 107 */             player.getEntityData().func_74757_a("craftingTable", true);
/*     */             
/*     */             try {
/* 110 */               PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(player, 2);
/* 111 */               TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)player);
/* 112 */             } catch (Exception e1) {
/*     */               
/* 114 */               TerraFirmaCraft.LOG.info("--------------------------------------------------");
/* 115 */               TerraFirmaCraft.LOG.catching(e1);
/* 116 */               TerraFirmaCraft.LOG.info("--------------------------------------------------");
/*     */             } 
/* 118 */             PlayerInventory.upgradePlayerCrafting(player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 123 */       if (!player.field_70170_p.field_72995_K && item instanceof com.bioxx.tfc.Items.ItemIngot)
/*     */       {
/* 125 */         for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */           
/* 127 */           ItemStack is = iinventory.func_70301_a(i);
/* 128 */           if (is != null)
/*     */           {
/* 130 */             if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */               
/* 132 */               if (player.field_70170_p.field_73012_v.nextInt(20) == 0) {
/* 133 */                 player.field_70170_p.func_72956_a((Entity)player, "terrafirmacraft:item.ceramicbreak", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F); break;
/*     */               } 
/* 135 */               TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.ceramicMold, 1, 1), player);
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preCraft(EntityPlayer player, ItemStack itemstack, IInventory iinventory) {
/* 146 */     triggerAchievements(player, itemstack, itemstack.func_77973_b(), itemstack.func_77960_j());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void triggerAchievements(EntityPlayer player, ItemStack itemstack, Item item, int itemDamage) {
/* 151 */     if (item instanceof com.bioxx.tfc.Items.Tools.ItemCustomPickaxe) {
/*     */       
/* 153 */       player.func_71029_a((StatBase)TFC_Achievements.achPickaxe);
/*     */     }
/* 155 */     else if (item instanceof com.bioxx.tfc.Items.Tools.ItemCustomSaw) {
/*     */       
/* 157 */       player.func_71029_a((StatBase)TFC_Achievements.achSaw);
/*     */     }
/* 159 */     else if ((item instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil1 && itemDamage == 2) || (item instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil2 && (itemDamage == 1 || itemDamage == 2))) {
/*     */       
/* 161 */       player.func_71029_a((StatBase)TFC_Achievements.achBronzeAge);
/*     */     }
/* 163 */     else if (item == Item.func_150898_a(TFCBlocks.blastFurnace)) {
/* 164 */       player.func_71029_a((StatBase)TFC_Achievements.achBlastFurnace);
/* 165 */     } else if (item == TFCItems.clayBall && itemDamage == 1) {
/* 166 */       player.func_71029_a((StatBase)TFC_Achievements.achFireClay);
/* 167 */     } else if (item == TFCItems.unknownIngot) {
/* 168 */       player.func_71029_a((StatBase)TFC_Achievements.achUnknown);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void transferNBT(boolean clearContents, EntityPlayer player, ItemStack itemstack, IInventory iinventory) {
/* 176 */     Item item = itemstack.func_77973_b();
/* 177 */     int itemDamage = itemstack.func_77960_j();
/* 178 */     if (item instanceof com.bioxx.tfc.Items.ItemIngot) {
/*     */       
/* 180 */       float temperature = 0.0F;
/* 181 */       for (int j = 0; j < iinventory.func_70302_i_(); j++) {
/*     */         
/* 183 */         if (iinventory.func_70301_a(j) != null)
/*     */         {
/* 185 */           if (iinventory.func_70301_a(j).func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal)
/*     */           {
/* 187 */             temperature = TFC_ItemHeat.getTemp(iinventory.func_70301_a(j)); } 
/*     */         }
/*     */       } 
/* 190 */       TFC_ItemHeat.setTemp(itemstack, temperature);
/*     */     }
/* 192 */     else if (item instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */       
/* 194 */       float temperature = 0.0F;
/* 195 */       for (int j = 0; j < iinventory.func_70302_i_(); j++) {
/*     */         
/* 197 */         if (iinventory.func_70301_a(j) != null)
/*     */         {
/* 199 */           if (iinventory.func_70301_a(j).func_77973_b() instanceof com.bioxx.tfc.Items.ItemIngot)
/* 200 */             temperature = TFC_ItemHeat.getTemp(iinventory.func_70301_a(j));  } 
/*     */       } 
/* 202 */       TFC_ItemHeat.setTemp(itemstack, temperature);
/*     */     }
/* 204 */     else if (item == TFCItems.potterySmallVessel && itemDamage == 0) {
/*     */       
/* 206 */       int color = -1;
/* 207 */       for (int j = 0; j < iinventory.func_70302_i_(); j++) {
/*     */         
/* 209 */         if (iinventory.func_70301_a(j) != null) {
/*     */ 
/*     */           
/* 212 */           int[] ids = OreDictionary.getOreIDs(iinventory.func_70301_a(j));
/* 213 */           float[] c = null;
/* 214 */           for (int id : ids) {
/*     */             
/* 216 */             String name = OreDictionary.getOreName(id);
/* 217 */             for (int k = 0; k < Global.DYE_NAMES.length; k++) {
/*     */               
/* 219 */               if (name.equals(Global.DYE_NAMES[k])) {
/*     */                 
/* 221 */                 c = EntitySheep.field_70898_d[k];
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 227 */           if (c != null) {
/*     */             
/* 229 */             int r = (int)(c[0] * 255.0F);
/* 230 */             int g = (int)(c[1] * 255.0F);
/* 231 */             int b = (int)(c[2] * 255.0F);
/* 232 */             r <<= 16;
/* 233 */             g <<= 8;
/*     */             
/* 235 */             color += r += g += b;
/*     */           } 
/*     */         } 
/*     */       } 
/* 239 */       if (color != -1) {
/*     */         
/* 241 */         NBTTagCompound nbt = null;
/* 242 */         if (itemstack.func_77942_o()) {
/* 243 */           nbt = itemstack.func_77978_p();
/*     */         } else {
/* 245 */           nbt = new NBTTagCompound();
/*     */         } 
/* 247 */         nbt.func_74768_a("color", color);
/* 248 */         itemstack.func_77982_d(nbt);
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 254 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 259 */         if ((iinventory.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemMiscToolHead || iinventory.func_70301_a(i).func_77973_b().getClass().getSimpleName().toLowerCase().contains("horsearmorpart")) && iinventory
/* 260 */           .func_70301_a(i).func_77942_o() && iinventory.func_70301_a(i).func_77978_p().func_74764_b("craftingTag"))
/*     */         {
/* 262 */           AnvilManager.setCraftTag(itemstack, AnvilManager.getCraftTag(iinventory.func_70301_a(i)));
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean gridHasItem(IInventory iinventory, Item item) {
/* 269 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 271 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 273 */         if (iinventory.func_70301_a(i).func_77973_b() == item)
/* 274 */           return true;  } 
/*     */     } 
/* 276 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, Item[] items) {
/* 281 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 283 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 285 */         for (int j = 0; j < items.length; j++)
/* 286 */           damageItem(entityplayer, iinventory, i, items[j]); 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, List<ItemStack> items) {
/* 292 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 294 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 296 */         for (ItemStack is : items)
/* 297 */           damageItem(entityplayer, iinventory, i, is.func_77973_b()); 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void damageItem(EntityPlayer entityplayer, IInventory iinventory, int i, Item shiftedindex) {
/* 303 */     if (iinventory.func_70301_a(i).func_77973_b() == shiftedindex) {
/*     */       
/* 305 */       int index = i;
/* 306 */       ItemStack item = iinventory.func_70301_a(index).func_77946_l();
/* 307 */       if (item != null) {
/*     */         
/* 309 */         item.func_77972_a(1, (EntityLivingBase)entityplayer);
/* 310 */         if (item.func_77960_j() != 0 || entityplayer.field_71075_bZ.field_75098_d) {
/*     */           
/* 312 */           iinventory.func_70299_a(index, item);
/* 313 */           (iinventory.func_70301_a(index)).field_77994_a++;
/* 314 */           if ((iinventory.func_70301_a(index)).field_77994_a > 2)
/* 315 */             (iinventory.func_70301_a(index)).field_77994_a = 2; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\CraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */