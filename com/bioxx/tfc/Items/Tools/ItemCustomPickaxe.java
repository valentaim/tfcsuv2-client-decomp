/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ 
/*     */ 
/*     */ public class ItemCustomPickaxe
/*     */   extends ItemPickaxe
/*     */   implements ISize
/*     */ {
/*     */   public ItemCustomPickaxe(Item.ToolMaterial e) {
/*  28 */     super(e);
/*  29 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*  30 */     setNoRepair();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  36 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/*  42 */     NBTTagCompound nbt = stack.func_77978_p();
/*  43 */     if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
/*  44 */       return TFC_Textures.brokenItem;
/*     */     }
/*  46 */     return func_77618_c(stack.func_77960_j(), pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/*  52 */     ItemTerra.addSizeInformation(is, arraylist);
/*  53 */     ItemTerraTool.addSmithingBonusInformation(is, arraylist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  59 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/*  71 */     if (canStack()) {
/*  72 */       return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
/*     */     }
/*  74 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  80 */     return EnumWeight.MEDIUM;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack stack) {
/*  86 */     return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDigSpeed(ItemStack stack, Block block, int meta) {
/*  92 */     float digSpeed = super.getDigSpeed(stack, block, meta);
/*     */     
/*  94 */     if (ForgeHooks.isToolEffective(stack, block, meta))
/*     */     {
/*  96 */       return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
/*     */     }
/*  98 */     return digSpeed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 104 */     return EnumItemReach.MEDIUM;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */