/*    */ package com.bioxx.tfc.Items.Pottery;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPotteryMold
/*    */   extends ItemPotteryBase
/*    */ {
/*    */   private IIcon copperIcon;
/*    */   private IIcon bronzeIcon;
/*    */   private IIcon bismuthBronzeIcon;
/*    */   private IIcon blackBronzeIcon;
/*    */   
/*    */   public ItemPotteryMold() {
/* 27 */     func_77656_e(401);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77645_m() {
/* 33 */     return (func_77612_l() > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 39 */     if (is.func_77960_j() > 5) {
/*    */       
/* 41 */       int units = 100 - (is.func_77960_j() - 2) / 4;
/* 42 */       arraylist.add(TFC_Core.translate("gui.units") + ": " + units + " / 100");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDamaged(ItemStack stack) {
/* 49 */     return (stack.func_77960_j() > 5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {
/* 55 */     this.clayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[0]);
/* 56 */     this.ceramicIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[1]);
/* 57 */     if (this.metaNames.length > 2) {
/*    */       
/* 59 */       this.copperIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[2]);
/* 60 */       this.bronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[3]);
/* 61 */       this.bismuthBronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[4]);
/* 62 */       this.blackBronzeIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[5]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_77667_c(ItemStack par1ItemStack) {
/* 68 */     if (par1ItemStack != null && par1ItemStack.func_77960_j() > 5) {
/*    */       
/* 70 */       int damage = (par1ItemStack.func_77960_j() - 2) % 4 + 2;
/* 71 */       return super.func_77667_c(par1ItemStack) + "." + this.metaNames[damage];
/*    */     } 
/* 73 */     return super.func_77667_c(par1ItemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon func_77617_a(int damage) {
/* 79 */     if (damage > 5)
/*    */     {
/* 81 */       damage = (damage - 2) % 4 + 2;
/*    */     }
/* 83 */     if (damage == 0) return this.clayIcon; 
/* 84 */     if (damage == 1) return this.ceramicIcon; 
/* 85 */     if (damage == 2) return this.copperIcon; 
/* 86 */     if (damage == 3) return this.bronzeIcon; 
/* 87 */     if (damage == 4) return this.bismuthBronzeIcon; 
/* 88 */     if (damage == 5) return this.blackBronzeIcon;
/*    */     
/* 90 */     return this.clayIcon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 97 */     list.add(new ItemStack(item, 1, 0));
/* 98 */     list.add(new ItemStack(item, 1, 1));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryMold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */