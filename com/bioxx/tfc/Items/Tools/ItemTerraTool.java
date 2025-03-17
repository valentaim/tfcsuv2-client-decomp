/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemTerraTool
/*     */   extends ItemTool
/*     */   implements ISize
/*     */ {
/*     */   public ItemTerraTool(float par2, Item.ToolMaterial par3, Set<Block> par4) {
/*  35 */     super(par2, par3, par4);
/*  36 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*  37 */     setNoRepair();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/*  45 */     ItemTerra.addSizeInformation(is, arraylist);
/*  46 */     ItemTerra.addHeatInformation(is, arraylist);
/*     */     
/*  48 */     if (is.func_77973_b() instanceof ICausesDamage) {
/*  49 */       arraylist.add(EnumChatFormatting.AQUA + TFC_Core.translate(((ICausesDamage)this).getDamageType().toString()));
/*     */     }
/*  51 */     addSmithingBonusInformation(is, arraylist);
/*  52 */     addExtraInformation(is, player, arraylist);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addSmithingBonusInformation(ItemStack is, List<String> arraylist) {
/*  57 */     if (AnvilManager.getDurabilityBuff(is) > 0.0F) {
/*  58 */       arraylist.add(TFC_Core.translate("gui.SmithingBonus") + " : +" + Helper.roundNumber(AnvilManager.getDurabilityBuff(is) * 100.0F, 10.0F) + "%");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {}
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/*  68 */     if (canStack()) {
/*  69 */       return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
/*     */     }
/*  71 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  77 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
/*  78 */     if (TFC_Textures.brokenItem == null) TFC_Textures.brokenItem = registerer.func_94245_a("terrafirmacraft:tools/Broken Item"); 
/*  79 */     if (TFC_Textures.wip == null) TFC_Textures.wip = registerer.func_94245_a("terrafirmacraft:wip");
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/*  85 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  91 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 103 */     return EnumWeight.MEDIUM;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack stack) {
/* 109 */     return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDigSpeed(ItemStack stack, Block block, int meta) {
/* 115 */     float digSpeed = super.getDigSpeed(stack, block, meta);
/*     */     
/* 117 */     if (ForgeHooks.isToolEffective(stack, block, meta))
/*     */     {
/* 119 */       return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
/*     */     }
/* 121 */     return digSpeed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77623_v() {
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 133 */     NBTTagCompound nbt = stack.func_77978_p();
/* 134 */     if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
/* 135 */       return TFC_Textures.brokenItem;
/*     */     }
/* 137 */     return func_77618_c(stack.func_77960_j(), pass);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemTerraTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */