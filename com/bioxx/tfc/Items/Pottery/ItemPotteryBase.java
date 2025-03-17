/*     */ package com.bioxx.tfc.Items.Pottery;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEPottery;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPotteryBase
/*     */   extends ItemTerra
/*     */   implements ISize
/*     */ {
/*     */   public IIcon clayIcon;
/*     */   public IIcon ceramicIcon;
/*     */   
/*     */   public ItemPotteryBase() {
/*  35 */     this.field_77787_bX = true;
/*  36 */     setFolder("pottery/");
/*  37 */     func_77637_a(TFCTabs.TFC_POTTERY);
/*  38 */     this.metaNames = new String[] { "", "" };
/*  39 */     setWeight(EnumWeight.MEDIUM);
/*  40 */     setSize(EnumSize.SMALL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  46 */     this.clayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[0]);
/*  47 */     this.ceramicIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  53 */     for (int i = 0; i < this.metaNames.length; i++) {
/*  54 */       list.add(new ItemStack((Item)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damage) {
/*  61 */     if (damage == 0) {
/*  62 */       return this.clayIcon;
/*     */     }
/*  64 */     return this.ceramicIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/*  70 */     if (TFC_Core.showShiftInformation()) {
/*     */       
/*  72 */       arraylist.add(TFC_Core.translate("gui.Help"));
/*  73 */       arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));
/*     */     } else {
/*  75 */       arraylist.add(TFC_Core.translate("gui.ShowHelp"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  81 */     if (!world.field_72995_K && entityplayer.func_70093_af()) {
/*     */ 
/*     */       
/*  84 */       if (side == 1) {
/*     */         
/*  86 */         int offset = 0;
/*  87 */         if (world.func_147439_a(x, y, z) != TFCBlocks.pottery && world.func_147437_c(x, y + 1, z)) {
/*     */ 
/*     */           
/*  90 */           if (!world.isSideSolid(x, y, z, ForgeDirection.UP))
/*  91 */             return false; 
/*  92 */           world.func_147449_b(x, y + 1, z, TFCBlocks.pottery);
/*  93 */           offset = 1;
/*     */         } 
/*     */ 
/*     */         
/*  97 */         if (world.func_147438_o(x, y + offset, z) != null && world.func_147438_o(x, y + offset, z) instanceof TEPottery) {
/*     */           
/*  99 */           TEPottery te = (TEPottery)world.func_147438_o(x, y + offset, z);
/* 100 */           if (hitX < 0.5D && hitZ < 0.5D) {
/*     */             
/* 102 */             if (te.canAddItem(0))
/*     */             {
/* 104 */               te.inventory[0] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
/* 105 */               (te.inventory[0]).field_77990_d = itemstack.field_77990_d;
/* 106 */               itemstack.field_77994_a--;
/* 107 */               world.func_147471_g(x, y + offset, z);
/*     */             }
/*     */           
/* 110 */           } else if (hitX > 0.5D && hitZ < 0.5D) {
/*     */             
/* 112 */             if (te.canAddItem(1))
/*     */             {
/* 114 */               te.inventory[1] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
/* 115 */               (te.inventory[1]).field_77990_d = itemstack.field_77990_d;
/* 116 */               itemstack.field_77994_a--;
/* 117 */               world.func_147471_g(x, y + offset, z);
/*     */             }
/*     */           
/* 120 */           } else if (hitX < 0.5D && hitZ > 0.5D) {
/*     */             
/* 122 */             if (te.canAddItem(2))
/*     */             {
/* 124 */               te.inventory[2] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
/* 125 */               (te.inventory[2]).field_77990_d = itemstack.field_77990_d;
/* 126 */               itemstack.field_77994_a--;
/* 127 */               world.func_147471_g(x, y + offset, z);
/*     */             }
/*     */           
/* 130 */           } else if (hitX > 0.5D && hitZ > 0.5D && 
/* 131 */             te.canAddItem(3)) {
/*     */             
/* 133 */             te.inventory[3] = new ItemStack((Item)this, 1, itemstack.func_77960_j());
/* 134 */             (te.inventory[3]).field_77990_d = itemstack.field_77990_d;
/* 135 */             itemstack.field_77994_a--;
/* 136 */             world.func_147471_g(x, y + offset, z);
/*     */           } 
/*     */         } 
/* 139 */         return true;
/*     */       } 
/* 141 */       return false;
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   public void onDoneCooking(World world, ItemStack is, Alloy.EnumTier furnaceTier) {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */