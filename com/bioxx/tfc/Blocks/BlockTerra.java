/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockTerra
/*     */   extends Block
/*     */ {
/*     */   protected BlockTerra() {
/*  26 */     super(Material.field_151576_e);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockTerra(Material material) {
/*  31 */     super(material);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
/*  38 */     if (TFCOptions.enableDebugMode && world.field_72995_K) {
/*     */       
/*  40 */       int metadata = world.func_72805_g(x, y, z);
/*  41 */       TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  54 */     if (TFCOptions.enableDebugMode && world.field_72995_K) {
/*     */       
/*  56 */       int metadata = world.func_72805_g(x, y, z);
/*  57 */       TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
/*     */     } 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
/*  64 */     func_149689_a(world, x, y, z, (EntityLivingBase)entityliving, (ItemStack)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
/*  70 */     super.func_149636_a(world, player, x, y, z, meta);
/*  71 */     TFC_Core.addPlayerExhaustion(player, 0.001F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
/*     */     boolean isBeach, hasWater;
/*  77 */     Block plant = plantable.getPlant(world, x, y + 1, z);
/*  78 */     if (plant == Blocks.field_150434_aF && this == Blocks.field_150434_aF)
/*     */     {
/*  80 */       return true;
/*     */     }
/*     */     
/*  83 */     if (plant == Blocks.field_150436_aH && this == Blocks.field_150436_aH)
/*     */     {
/*  85 */       return true;
/*     */     }
/*     */     
/*  88 */     EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
/*  89 */     switch (plantType) {
/*     */       case Cave:
/*  91 */         return isSideSolid(world, x, y, z, ForgeDirection.UP);
/*  92 */       case Plains: return (TFC_Core.isSoil(this) || TFC_Core.isFarmland(this));
/*  93 */       case Water: return (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0);
/*     */       case Beach:
/*  95 */         isBeach = (TFC_Core.isSand(this) || TFC_Core.isGravel(this));
/*     */ 
/*     */ 
/*     */         
/*  99 */         hasWater = (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h);
/* 100 */         return (isBeach && hasWater);
/* 101 */     }  return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockTerra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */