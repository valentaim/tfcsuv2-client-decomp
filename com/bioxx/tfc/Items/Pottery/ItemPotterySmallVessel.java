/*     */ package com.bioxx.tfc.Items.Pottery;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyMetal;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IBag;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPotterySmallVessel
/*     */   extends ItemPotteryBase
/*     */   implements IBag
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon overlayIcon;
/*     */   
/*     */   public ItemPotterySmallVessel() {
/*  46 */     this.metaNames = new String[] { "Clay Vessel", "Ceramic Vessel", "Ceramic Vessel" };
/*  47 */     func_77625_d(1);
/*  48 */     setWeight(EnumWeight.MEDIUM);
/*  49 */     setSize(EnumSize.SMALL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/*  62 */     if (pass == 1 && stack.func_77978_p() != null && stack.func_77978_p().func_74764_b("color")) {
/*  63 */       return this.overlayIcon;
/*     */     }
/*  65 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  71 */     super.func_94581_a(registerer);
/*  72 */     this.overlayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Ceramic Vessel Overlay");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  78 */     list.add(new ItemStack((Item)this, 1, 0));
/*  79 */     list.add(new ItemStack((Item)this, 1, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDoneCooking(World world, ItemStack is, Alloy.EnumTier furnaceTier) {
/*  91 */     ItemStack[] bag = loadBagInventory(is);
/*  92 */     boolean canCookAlloy = true;
/*  93 */     for (int i = 0; bag != null && i < 4; i++) {
/*     */       
/*  95 */       if (bag[i] != null)
/*     */       {
/*  97 */         if (!(bag[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemOreSmall) && !(bag[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre)) {
/*  98 */           canCookAlloy = false;
/*     */         }
/*     */       }
/*     */     } 
/* 102 */     if (is.func_77960_j() == 2) {
/*     */       
/* 104 */       NBTTagCompound tag = is.field_77990_d;
/* 105 */       long totalH = TFC_Time.getTotalHours();
/* 106 */       tag.func_74772_a("TempTimer", totalH);
/*     */     } 
/*     */     
/* 109 */     if (canCookAlloy && bag != null) {
/*     */       
/* 111 */       Metal[] types = new Metal[4];
/* 112 */       int[] metalAmounts = new int[4];
/*     */       
/* 114 */       if (bag[0] != null) {
/*     */         
/* 116 */         types[0] = ((ISmeltable)bag[0].func_77973_b()).getMetalType(bag[0]);
/* 117 */         metalAmounts[0] = ((ISmeltable)bag[0].func_77973_b()).getMetalReturnAmount(bag[0]) * (bag[0]).field_77994_a;
/*     */       } 
/*     */       
/* 120 */       if (bag[1] != null) {
/*     */         
/* 122 */         types[1] = ((ISmeltable)bag[1].func_77973_b()).getMetalType(bag[1]);
/* 123 */         metalAmounts[1] = ((ISmeltable)bag[1].func_77973_b()).getMetalReturnAmount(bag[1]) * (bag[1]).field_77994_a;
/*     */         
/* 125 */         if (mergeMetals(types[0], types[1], metalAmounts[0], metalAmounts[1]) != metalAmounts[0]) {
/*     */           
/* 127 */           metalAmounts[0] = mergeMetals(types[0], types[1], metalAmounts[0], metalAmounts[1]);
/* 128 */           types[1] = null;
/* 129 */           metalAmounts[1] = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 133 */       if (bag[2] != null) {
/*     */         
/* 135 */         types[2] = ((ISmeltable)bag[2].func_77973_b()).getMetalType(bag[2]);
/* 136 */         metalAmounts[2] = ((ISmeltable)bag[2].func_77973_b()).getMetalReturnAmount(bag[2]) * (bag[2]).field_77994_a;
/*     */         
/* 138 */         if (mergeMetals(types[0], types[2], metalAmounts[0], metalAmounts[2]) != metalAmounts[0]) {
/*     */           
/* 140 */           metalAmounts[0] = mergeMetals(types[0], types[2], metalAmounts[0], metalAmounts[2]);
/* 141 */           types[2] = null;
/* 142 */           metalAmounts[2] = 0;
/*     */         } 
/* 144 */         if (mergeMetals(types[1], types[2], metalAmounts[1], metalAmounts[2]) != metalAmounts[1]) {
/*     */           
/* 146 */           metalAmounts[1] = mergeMetals(types[1], types[2], metalAmounts[1], metalAmounts[2]);
/* 147 */           types[2] = null;
/* 148 */           metalAmounts[2] = 0;
/*     */         } 
/*     */       } 
/* 151 */       if (bag[3] != null) {
/*     */         
/* 153 */         types[3] = ((ISmeltable)bag[3].func_77973_b()).getMetalType(bag[3]);
/* 154 */         metalAmounts[3] = ((ISmeltable)bag[3].func_77973_b()).getMetalReturnAmount(bag[3]) * (bag[3]).field_77994_a;
/*     */         
/* 156 */         if (mergeMetals(types[0], types[3], metalAmounts[0], metalAmounts[3]) != metalAmounts[0]) {
/*     */           
/* 158 */           metalAmounts[0] = mergeMetals(types[0], types[3], metalAmounts[0], metalAmounts[3]);
/* 159 */           types[3] = null;
/* 160 */           metalAmounts[3] = 0;
/*     */         } 
/* 162 */         if (mergeMetals(types[1], types[3], metalAmounts[1], metalAmounts[3]) != metalAmounts[1]) {
/*     */           
/* 164 */           metalAmounts[1] = mergeMetals(types[1], types[3], metalAmounts[1], metalAmounts[3]);
/* 165 */           types[3] = null;
/* 166 */           metalAmounts[3] = 0;
/*     */         } 
/* 168 */         if (mergeMetals(types[2], types[3], metalAmounts[2], metalAmounts[3]) != metalAmounts[2]) {
/*     */           
/* 170 */           metalAmounts[2] = mergeMetals(types[2], types[3], metalAmounts[2], metalAmounts[3]);
/* 171 */           types[3] = null;
/* 172 */           metalAmounts[3] = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 176 */       int total = metalAmounts[0] + metalAmounts[1] + metalAmounts[2] + metalAmounts[3];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       if (total > 0) {
/*     */         
/* 189 */         float[] metalPercent = new float[4];
/* 190 */         metalPercent[0] = metalAmounts[0] / total * 100.0F;
/* 191 */         metalPercent[1] = metalAmounts[1] / total * 100.0F;
/* 192 */         metalPercent[2] = metalAmounts[2] / total * 100.0F;
/* 193 */         metalPercent[3] = metalAmounts[3] / total * 100.0F;
/*     */         
/* 195 */         List<AlloyMetal> a = new ArrayList<>();
/* 196 */         if (types[0] != null)
/* 197 */           a.add(new AlloyMetal(types[0], metalPercent[0])); 
/* 198 */         if (types[1] != null)
/* 199 */           a.add(new AlloyMetal(types[1], metalPercent[1])); 
/* 200 */         if (types[2] != null)
/* 201 */           a.add(new AlloyMetal(types[2], metalPercent[2])); 
/* 202 */         if (types[3] != null) {
/* 203 */           a.add(new AlloyMetal(types[3], metalPercent[3]));
/*     */         }
/* 205 */         Metal match = AlloyManager.INSTANCE.matchesAlloy(a, furnaceTier);
/* 206 */         if (match != null) {
/*     */           
/* 208 */           Alloy output = new Alloy(match, total);
/* 209 */           NBTTagCompound tag = is.field_77990_d;
/* 210 */           tag.func_74778_a("MetalType", output.outputType.name);
/* 211 */           tag.func_74768_a("MetalAmount", (int)output.outputAmount);
/* 212 */           long totalH = TFC_Time.getTotalHours();
/* 213 */           tag.func_74772_a("TempTimer", totalH);
/* 214 */           is.func_77978_p().func_82580_o("Items");
/* 215 */           is.func_77964_b(2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int mergeMetals(Metal mt0, Metal mt1, int m0, int m1) {
/* 223 */     if (mt0 != null && mt1 != null && m0 > 0)
/*     */     {
/* 225 */       if (mt0.name.equals(mt1.name))
/* 226 */         return m0 + m1; 
/*     */     }
/* 228 */     return m0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack is, int pass) {
/* 235 */     if (pass != 1)
/*     */     {
/* 237 */       return 16777215;
/*     */     }
/*     */ 
/*     */     
/* 241 */     int j = getColor(is);
/*     */     
/* 243 */     if (j < 0)
/*     */     {
/* 245 */       return 16777215;
/*     */     }
/*     */     
/* 248 */     if (is.func_77960_j() == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 255 */       int r = Math.min((j >> 16) + 96, 255);
/* 256 */       int b = Math.min((j >> 8 & 0xFF) + 96, 255);
/* 257 */       int g = Math.min((j & 0xFF) + 96, 255);
/* 258 */       return r << 16 | b << 8 | g;
/*     */     } 
/*     */     
/* 261 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColor(ItemStack is) {
/* 267 */     if (!is.func_77942_o() || !is.func_77978_p().func_74764_b("color")) {
/* 268 */       return -1;
/*     */     }
/* 270 */     return is.func_77978_p().func_74762_e("color");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack[] loadBagInventory(ItemStack is) {
/* 276 */     ItemStack[] bag = new ItemStack[4];
/* 277 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("Items")) {
/*     */       
/* 279 */       NBTTagList nbttaglist = is.func_77978_p().func_150295_c("Items", 10);
/* 280 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */         
/* 282 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 283 */         byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 284 */         if (byte0 >= 0 && byte0 < 4) {
/* 285 */           bag[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       } 
/*     */     } else {
/* 289 */       return null;
/*     */     } 
/* 291 */     return bag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
/* 297 */     ItemStack[] bag = loadBagInventory(is);
/* 298 */     if (bag != null) {
/*     */       
/* 300 */       TFC_Core.handleItemTicking(bag, world, x, y, z, 0.5F);
/* 301 */       for (ItemStack i : bag) {
/*     */         
/* 303 */         if (i != null && i.field_77994_a == 0)
/* 304 */           i = null; 
/*     */       } 
/* 306 */       saveContents(is, bag);
/*     */     } 
/* 308 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveContents(ItemStack vessel, ItemStack[] bag) {
/* 313 */     NBTTagList nbttaglist = new NBTTagList();
/* 314 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 316 */       if (bag[i] != null) {
/*     */         
/* 318 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 319 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 320 */         bag[i].func_77955_b(nbttagcompound1);
/* 321 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 324 */     if (vessel != null) {
/*     */       
/* 326 */       if (!vessel.func_77942_o())
/* 327 */         vessel.func_77982_d(new NBTTagCompound()); 
/* 328 */       vessel.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
/* 335 */     if (!entityplayer.func_70093_af())
/*     */     {
/* 337 */       if (itemstack.func_77960_j() == 2) {
/*     */         
/* 339 */         NBTTagCompound nbt = itemstack.func_77978_p();
/* 340 */         if (nbt == null) {
/*     */           
/* 342 */           itemstack.func_77964_b(1);
/* 343 */           if (!world.field_72995_K)
/*     */           {
/*     */             
/* 346 */             String error = TFC_Core.translate("error.error") + " " + itemstack.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");
/*     */ 
/*     */             
/* 349 */             TFC_Core.sendInfoMessage(entityplayer, (IChatComponent)new ChatComponentText(error));
/*     */           }
/*     */         
/* 352 */         } else if (nbt.func_74764_b("TempTimer")) {
/*     */           
/* 354 */           long temp = nbt.func_74763_f("TempTimer");
/* 355 */           if (TFC_Time.getTotalHours() - temp < 11L) {
/* 356 */             entityplayer.openGui(TerraFirmaCraft.instance, 19, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
/*     */           }
/*     */         } 
/* 359 */       } else if (itemstack.func_77960_j() == 1) {
/*     */         
/* 361 */         entityplayer.openGui(TerraFirmaCraft.instance, 39, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
/*     */       } 
/*     */     }
/* 364 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 370 */     NBTTagCompound tag = is.field_77990_d;
/* 371 */     if (tag != null) {
/*     */       
/* 373 */       if (tag.func_74764_b("MetalType")) {
/*     */         
/* 375 */         String name = tag.func_74779_i("MetalType");
/* 376 */         name = name.replace(" ", "");
/* 377 */         name = TFC_Core.translate("gui.metal." + name);
/*     */ 
/*     */         
/* 380 */         if (tag.func_74764_b("MetalAmount"))
/*     */         {
/*     */           
/* 383 */           name = name + " (" + tag.func_74762_e("MetalAmount") + " " + TFC_Core.translate("gui.units") + ")";
/*     */         }
/*     */         
/* 386 */         arraylist.add(EnumChatFormatting.DARK_GREEN + name);
/*     */       } 
/*     */       
/* 389 */       if (tag.func_74764_b("TempTimer")) {
/*     */         
/* 391 */         long total = TFC_Time.getTotalHours();
/* 392 */         long temp = tag.func_74763_f("TempTimer");
/* 393 */         if (total - temp < 11L) {
/* 394 */           arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.ItemHeat.Liquid"));
/*     */         } else {
/* 396 */           arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.ItemHeat.Solidified"));
/*     */         } 
/*     */       } 
/* 399 */       if (tag.func_74764_b("Items")) {
/*     */         
/* 401 */         NBTTagList nbttaglist = tag.func_150295_c("Items", 10);
/* 402 */         for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */           
/* 404 */           NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 405 */           byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 406 */           if (byte0 >= 0 && byte0 < 4) {
/*     */             
/* 408 */             ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound1);
/* 409 */             if (itemstack.field_77994_a > 0)
/*     */             {
/* 411 */               if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
/*     */                 
/* 413 */                 float decay = Food.getDecay(itemstack);
/* 414 */                 float weight = Helper.roundNumber(Food.getWeight(itemstack), 100.0F);
/*     */                 
/* 416 */                 String ds = " " + EnumChatFormatting.DARK_GRAY + Helper.roundNumber(decay / weight * 100.0F, 10.0F) + "%";
/* 417 */                 if (decay <= 0.0F) {
/* 418 */                   ds = "";
/*     */                 }
/* 420 */                 arraylist.add(EnumChatFormatting.GOLD.toString() + itemstack.func_77973_b().func_77653_i(itemstack) + " " + EnumChatFormatting.WHITE + weight + "oz" + ds);
/*     */               } else {
/*     */                 
/* 423 */                 arraylist.add(EnumChatFormatting.GOLD.toString() + itemstack.field_77994_a + "x " + itemstack.func_77973_b().func_77653_i(itemstack));
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 434 */     if (TFC_Core.showShiftInformation()) {
/*     */       
/* 436 */       arraylist.add(TFC_Core.translate("gui.Help"));
/* 437 */       arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));
/*     */       
/* 439 */       NBTTagCompound tag = is.field_77990_d;
/* 440 */       if (tag != null && tag.func_74764_b("TempTimer")) {
/*     */         
/* 442 */         long total = TFC_Time.getTotalHours();
/* 443 */         long temp = tag.func_74763_f("TempTimer");
/* 444 */         if (total - temp < 11L) {
/* 445 */           arraylist.add(TFC_Core.translate("gui.PotteryVesselSmall.Inst0"));
/*     */         }
/*     */       } else {
/* 448 */         arraylist.add(TFC_Core.translate("gui.PotteryVesselSmall.Inst0"));
/*     */       } 
/*     */     } else {
/*     */       
/* 452 */       arraylist.add(TFC_Core.translate("gui.ShowHelp"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotterySmallVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */