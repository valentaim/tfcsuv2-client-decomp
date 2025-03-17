/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.TileEntities.TECrop;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import growthcraft.rice.util.RiceBlockCheck;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
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
/*     */ public class ItemCustomSeeds
/*     */   extends ItemTerra
/*     */ {
/*     */   private int cropId;
/*     */   
/*     */   public ItemCustomSeeds(int cropId) {
/*  40 */     this.cropId = cropId;
/*  41 */     func_77637_a(TFCTabs.TFC_FOODS);
/*  42 */     setFolder("food/");
/*  43 */     setWeight(EnumWeight.LIGHT);
/*  44 */     setSize(EnumSize.TINY);
/*     */   }
/*     */   
/*     */   public static boolean hasBlock(World w, int x, int y, int z) {
/*  48 */     for (int y1 = y + 1; y1 < 255; y1++) {
/*  49 */       if (!(w.func_147439_a(x, y1, z) instanceof net.minecraft.block.BlockGlass) && !(w.func_147439_a(x, y1, z) instanceof net.minecraft.block.BlockAir)) return true; 
/*  50 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  60 */     if (side != 1 || world.field_72995_K)
/*  61 */       return false; 
/*  62 */     if (player.func_82247_a(x, y, z, side, stack) && player.func_82247_a(x, y + 1, z, side, stack)) {
/*     */       
/*  64 */       Block var8 = world.func_147439_a(x, y, z);
/*  65 */       if ((stack.func_77973_b() == TFCItems.seedsRice && RiceBlockCheck.isPaddyWithWater((IBlockAccess)world, x, y, z, 1)) || (stack.func_77973_b() != TFCItems.seedsRice && (var8 == TFCBlocks.tilledSoil || var8 == TFCBlocks.tilledSoil2) && world.func_147437_c(x, y + 1, z))) {
/*     */         
/*  67 */         CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
/*  68 */         if (hasBlock(world, x, y, z) || (crop.needsSunlight && !TECrop.hasSunlight(world, x, y + 1, z))) {
/*     */           
/*  70 */           TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.seeds.failedSun", new Object[0]));
/*  71 */           return false;
/*     */         } 
/*     */         
/*  74 */         if (TFC_Climate.getHeightAdjustedTemp(world, x, y, z) <= crop.minAliveTemp && !crop.dormantInFrost) {
/*     */           
/*  76 */           TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.seeds.failedTemp", new Object[0]));
/*  77 */           return false;
/*     */         } 
/*     */         
/*  80 */         world.func_147449_b(x, y + 1, z, TFCBlocks.crops);
/*     */         
/*  82 */         TECrop te = (TECrop)world.func_147438_o(x, y + 1, z);
/*  83 */         te.cropId = this.cropId;
/*  84 */         world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*  85 */         world.func_147471_g(x, y, z);
/*  86 */         stack.field_77994_a--;
/*  87 */         return true;
/*     */       } 
/*     */       
/*  90 */       return false;
/*     */     } 
/*     */     
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/*  99 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */     
/* 101 */     SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.agriculture");
/* 102 */     int nutrient = CropManager.getInstance().getCropFromId(this.cropId).getCycleType();
/*     */     
/* 104 */     if (rank == SkillStats.SkillRank.Expert || rank == SkillStats.SkillRank.Master)
/*     */     {
/* 106 */       switch (nutrient) {
/*     */         
/*     */         case 0:
/* 109 */           arraylist.add(EnumChatFormatting.RED + TFC_Core.translate("gui.Nutrient.A"));
/*     */           break;
/*     */         case 1:
/* 112 */           arraylist.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.Nutrient.B"));
/*     */           break;
/*     */         case 2:
/* 115 */           arraylist.add(EnumChatFormatting.YELLOW + TFC_Core.translate("gui.Nutrient.C"));
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */