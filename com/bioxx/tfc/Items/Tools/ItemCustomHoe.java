/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemHoe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.UseHoeEvent;
/*     */ 
/*     */ public class ItemCustomHoe
/*     */   extends ItemHoe
/*     */   implements ISize {
/*     */   public ItemCustomHoe(Item.ToolMaterial e) {
/*  35 */     super(e);
/*  36 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*  37 */     setNoRepair();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  43 */     String name = func_77658_a().replace("item.", "");
/*  44 */     name = name.replace("IgIn ", "");
/*  45 */     name = name.replace("IgEx ", "");
/*  46 */     name = name.replace("Sed ", "");
/*  47 */     name = name.replace("MM ", "");
/*  48 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/*  54 */     NBTTagCompound nbt = stack.func_77978_p();
/*  55 */     if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
/*  56 */       return TFC_Textures.brokenItem;
/*     */     }
/*  58 */     return func_77618_c(stack.func_77960_j(), pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  64 */     if (world.field_72995_K || world.func_147439_a(x, y, z) == TFCBlocks.toolRack) {
/*  65 */       return false;
/*     */     }
/*     */     
/*  68 */     UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
/*  69 */     if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*  70 */       return false;
/*     */     }
/*  72 */     if (event.getResult() == Event.Result.ALLOW) {
/*     */       
/*  74 */       stack.func_77972_a(1, (EntityLivingBase)player);
/*  75 */       return true;
/*     */     } 
/*     */     
/*  78 */     Block var8 = world.func_147439_a(x, y, z);
/*  79 */     Block var9 = world.func_147439_a(x, y + 1, z);
/*     */     
/*  81 */     boolean isDirt = TFC_Core.isDirt(var8);
/*     */     
/*  83 */     if (side != 1 || !var9.isAir((IBlockAccess)world, x, y + 1, z) || (!TFC_Core.isGrass(var8) && !isDirt)) {
/*  84 */       return false;
/*     */     }
/*     */     
/*  87 */     Block var10 = (var8 == TFCBlocks.dirt || var8 == TFCBlocks.grass || var8 == TFCBlocks.dryGrass) ? TFCBlocks.dirt : ((var8 == TFCBlocks.dirt2 || var8 == TFCBlocks.grass2 || var8 == TFCBlocks.dryGrass2) ? TFCBlocks.dirt2 : null);
/*     */     
/*  89 */     if (var10 != null) {
/*     */       
/*  91 */       int meta = world.func_72805_g(x, y, z);
/*  92 */       if (var10 == TFCBlocks.dirt) {
/*     */         
/*  94 */         world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), var10.field_149762_H.func_150498_e(), (var10.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var10.field_149762_H.func_150494_d() * 0.8F);
/*     */         
/*  96 */         if (world.field_72995_K) {
/*  97 */           return true;
/*     */         }
/*     */         
/* 100 */         world.func_147465_d(x, y, z, TFCBlocks.tilledSoil, meta, 2);
/* 101 */         world.func_147471_g(x, y, z);
/* 102 */         stack.func_77972_a(1, (EntityLivingBase)player);
/*     */         
/* 104 */         if (isDirt) {
/*     */           
/* 106 */           TEFarmland te = (TEFarmland)world.func_147438_o(x, y, z);
/* 107 */           te.nutrients[0] = 100;
/* 108 */           te.nutrients[1] = 100;
/* 109 */           te.nutrients[2] = 100;
/*     */         } 
/* 111 */         return true;
/*     */       } 
/*     */       
/* 114 */       if (var10 == TFCBlocks.dirt2) {
/*     */         
/* 116 */         world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), var10.field_149762_H.func_150498_e(), (var10.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var10.field_149762_H.func_150494_d() * 0.8F);
/*     */         
/* 118 */         if (world.field_72995_K) {
/* 119 */           return true;
/*     */         }
/*     */         
/* 122 */         world.func_147465_d(x, y, z, TFCBlocks.tilledSoil2, meta, 2);
/* 123 */         world.func_147471_g(x, y, z);
/* 124 */         stack.func_77972_a(1, (EntityLivingBase)player);
/*     */         
/* 126 */         if (isDirt) {
/*     */           
/* 128 */           TEFarmland te = (TEFarmland)world.func_147438_o(x, y, z);
/* 129 */           te.nutrients[0] = 100;
/* 130 */           te.nutrients[1] = 100;
/* 131 */           te.nutrients[2] = 100;
/*     */         } 
/* 133 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 145 */     ItemTerra.addSizeInformation(is, arraylist);
/* 146 */     ItemTerraTool.addSmithingBonusInformation(is, arraylist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/* 152 */     if (canStack()) {
/* 153 */       return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
/*     */     }
/* 155 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 161 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 173 */     return EnumWeight.LIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack stack) {
/* 179 */     return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDigSpeed(ItemStack stack, Block block, int meta) {
/* 185 */     float digSpeed = super.getDigSpeed(stack, block, meta);
/*     */     
/* 187 */     if (ForgeHooks.isToolEffective(stack, block, meta))
/*     */     {
/* 189 */       return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
/*     */     }
/* 191 */     return digSpeed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 197 */     return EnumItemReach.FAR;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */