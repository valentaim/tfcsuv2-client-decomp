/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TELogPile;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.common.registry.GameData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLogPile
/*     */   extends BlockTerraContainer
/*     */ {
/*  32 */   private IIcon[] icons = new IIcon[3];
/*     */ 
/*     */   
/*     */   public BlockLogPile() {
/*  36 */     super(Material.field_151575_d);
/*  37 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionFromMetadata(int i) {
/*  42 */     return i & 0x3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
/*  48 */     if (world.func_147438_o(x, y, z) instanceof TELogPile && side == ForgeDirection.UP)
/*     */     {
/*  50 */       if (((TELogPile)world.func_147438_o(x, y, z)).isOnFire)
/*  51 */         return true; 
/*     */     }
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
/*  59 */     if (world.field_72995_K)
/*     */     {
/*  61 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  66 */     Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
/*  67 */     if ((TELogPile)world.func_147438_o(i, j, k) != null) {
/*     */ 
/*     */ 
/*     */       
/*  71 */       ItemStack is = entityplayer.func_71045_bC();
/*     */       
/*  73 */       if (is != null && (is.func_77973_b() == TFCItems.logs || (fromcook != null && is.func_77973_b() == fromcook)))
/*     */       {
/*  75 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  79 */       entityplayer.openGui(TerraFirmaCraft.instance, 0, world, i, j, k);
/*     */       
/*  81 */       return true;
/*     */     } 
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/*  93 */     if (j == 0 || j == 2) {
/*     */       
/*  95 */       if (i == 0)
/*  96 */         return this.icons[1]; 
/*  97 */       if (i == 1)
/*  98 */         return this.icons[1]; 
/*  99 */       if (i == 2)
/* 100 */         return this.icons[2]; 
/* 101 */       if (i == 3)
/* 102 */         return this.icons[2]; 
/* 103 */       if (i == 4)
/* 104 */         return this.icons[0]; 
/* 105 */       if (i == 5) {
/* 106 */         return this.icons[0];
/*     */       }
/*     */     }
/* 109 */     else if (j == 1 || j == 3) {
/*     */       
/* 111 */       if (i == 0)
/* 112 */         return this.icons[0]; 
/* 113 */       if (i == 1)
/* 114 */         return this.icons[0]; 
/* 115 */       if (i == 2)
/* 116 */         return this.icons[0]; 
/* 117 */       if (i == 3)
/* 118 */         return this.icons[0]; 
/* 119 */       if (i == 4)
/* 120 */         return this.icons[2]; 
/* 121 */       if (i == 5) {
/* 122 */         return this.icons[2];
/*     */       }
/*     */     } 
/*     */     
/* 126 */     return this.icons[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 133 */     this.icons[0] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile Side 0");
/* 134 */     this.icons[1] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile Side 1");
/* 135 */     this.icons[2] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile End");
/*     */   }
/*     */ 
/*     */   
/*     */   public void eject(World world, int x, int y, int z) {
/* 140 */     if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELogPile) {
/*     */       
/* 142 */       TELogPile te = (TELogPile)world.func_147438_o(x, y, z);
/* 143 */       te.ejectContents();
/* 144 */       world.func_147475_p(x, y, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int par1, Random random, int par3) {
/* 151 */     return Item.func_150899_d(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/* 157 */     eject(world, i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
/* 163 */     eject(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149664_b(World world, int x, int y, int z, int i) {
/* 169 */     eject(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/* 175 */     eject(world, x, y, z);
/* 176 */     return world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 182 */     return (TileEntity)new TELogPile();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 188 */     if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELogPile)
/*     */     {
/* 190 */       ((TELogPile)world.func_147438_o(x, y, z)).lightNeighbors();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 203 */     if (world.func_147438_o(x, y, z) instanceof TELogPile) {
/*     */       
/* 205 */       TELogPile te = (TELogPile)world.func_147438_o(x, y, z);
/*     */       
/* 207 */       if (te.isOnFire && te.fireTimer + TFCOptions.charcoalPitBurnTime < (float)TFC_Time.getTotalHours())
/*     */       {
/* 209 */         te.createCharcoal(x, y, z, true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {
/* 218 */     if (world.func_147438_o(x, y, z) instanceof TELogPile && ((TELogPile)world.func_147438_o(x, y, z)).isOnFire) {
/*     */       
/* 220 */       double centerX = (x + 0.5F);
/* 221 */       double centerY = (y + 2.0F);
/* 222 */       double centerZ = (z + 0.5F);
/*     */ 
/*     */       
/* 225 */       world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.1D, 0.0D);
/* 226 */       world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.15D, 0.0D);
/* 227 */       world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY - 1.0D, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.1D, 0.0D);
/* 228 */       world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY - 1.0D, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.15D, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockLogPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */