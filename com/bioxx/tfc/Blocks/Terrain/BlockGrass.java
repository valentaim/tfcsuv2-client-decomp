/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.Core.ColorizerGrassTFC;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenLooseRocks;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenSaplings;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockGrass
/*     */   extends BlockTerra
/*     */ {
/*     */   protected int textureOffset;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon grassTopTexture;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconSnowSide;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconGrassSideOverlay;
/*     */   
/*     */   public BlockGrass() {
/*  50 */     super(Material.field_151577_b);
/*  51 */     func_149675_a(true);
/*  52 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockGrass(int texOff) {
/*  57 */     this();
/*  58 */     this.textureOffset = texOff;
/*  59 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  67 */     Boolean addToCreative = Boolean.valueOf(true);
/*     */     
/*  69 */     if (addToCreative.booleanValue()) {
/*     */       int count;
/*     */       
/*  72 */       if (this.textureOffset == 0) { count = 16; }
/*  73 */       else { count = Global.STONE_ALL.length - 16; }
/*     */       
/*  75 */       for (int i = 0; i < count; i++) {
/*  76 */         list.add(new ItemStack(item, 1, i));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static IIcon getIconSideOverlay() {
/*  82 */     return ((BlockGrass)TFCBlocks.grass).iconGrassSideOverlay;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  88 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/*  94 */     this.grassTopTexture = registerer.func_94245_a("terrafirmacraft:GrassTop");
/*     */     
/*  96 */     this.iconSnowSide = registerer.func_94245_a("terrafirmacraft:snow");
/*  97 */     this.iconGrassSideOverlay = registerer.func_94245_a("terrafirmacraft:GrassSide");
/*     */     
/*  99 */     TFC_Textures.invisibleTexture = registerer.func_94245_a("terrafirmacraft:Invisible");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 108 */     if (side == 1)
/* 109 */       return this.grassTopTexture; 
/* 110 */     if (side == 0) {
/* 111 */       return TFC_Textures.invisibleTexture;
/*     */     }
/* 113 */     return this.iconGrassSideOverlay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
/* 122 */     if (side == 1)
/* 123 */       return this.grassTopTexture; 
/* 124 */     if (side == 0)
/* 125 */       return TFC_Textures.invisibleTexture; 
/* 126 */     if (side == 2) {
/*     */       
/* 128 */       if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x, y - 1, z - 1))) {
/* 129 */         return isSnow(access, x, y - 1, z - 1) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
/*     */       }
/* 131 */     } else if (side == 3) {
/*     */       
/* 133 */       if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x, y - 1, z + 1))) {
/* 134 */         return isSnow(access, x, y - 1, z + 1) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
/*     */       }
/* 136 */     } else if (side == 4) {
/*     */       
/* 138 */       if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x - 1, y - 1, z))) {
/* 139 */         return isSnow(access, x - 1, y - 1, z) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
/*     */       }
/* 141 */     } else if (side == 5) {
/*     */       
/* 143 */       if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x + 1, y - 1, z))) {
/* 144 */         return isSnow(access, x + 1, y - 1, z) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
/*     */       }
/*     */     } 
/* 147 */     return this.iconGrassSideOverlay;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
/* 154 */     if (side == 0) {
/* 155 */       return false;
/*     */     }
/* 157 */     return super.func_149646_a(access, x, y, z, side);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSnow(IBlockAccess access, int x, int y, int z) {
/* 162 */     Material material = access.func_147439_a(x, y, z).func_149688_o();
/* 163 */     return (material == Material.field_151597_y || material == Material.field_151596_z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
/* 174 */     return TerraFirmaCraft.proxy.grassColorMultiplier(bAccess, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 180 */     return TFCBlocks.grassRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149635_D() {
/* 186 */     double d0 = 0.5D;
/* 187 */     double d1 = 1.0D;
/* 188 */     return ColorizerGrassTFC.getGrassColor(d0, d1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149741_i(int par1) {
/* 197 */     return func_149635_D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int i, int j, int k, Random rand) {
/* 206 */     if (!world.field_72995_K) {
/*     */       
/* 208 */       int meta = world.func_72805_g(i, j, k);
/* 209 */       if (world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE && !TFC_Core.isDryGrass((Block)this)) {
/*     */         
/* 211 */         world.func_147465_d(i, j, k, TFC_Core.getTypeForDryGrassFromSoil((Block)this), meta, 2);
/*     */       }
/* 213 */       else if (world.func_147439_a(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN)) {
/*     */         
/* 215 */         world.func_147465_d(i, j, k, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
/*     */       }
/* 217 */       else if (world.func_72937_j(i, j + 1, k)) {
/*     */         
/* 219 */         spreadGrass(world, i, j, k, rand);
/*     */         
/* 221 */         float rain = TFC_Climate.getRainfall(world, i, j + 1, k);
/* 222 */         float temp = TFC_Climate.getHeightAdjustedTemp(world, i, j + 1, k);
/*     */         
/* 224 */         if (temp > 20.0F && TFC_Core.isGrass((Block)this) && !TFC_Core.isDryGrass((Block)this) && world.func_147439_a(i, j + 1, k).func_149688_o() != Material.field_151586_h && world.func_147437_c(i, j + 1, k)) {
/*     */           
/* 226 */           if (rand.nextInt((int)((16800.0F - rain) / 4.0F)) == 0 && temp > 20.0F)
/* 227 */           { world.func_147465_d(i, j + 1, k, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(30) == 0) ? 1 : 0, 2); }
/* 228 */           else if (rand.nextInt(15000) == 0) { (new WorldGenSaplings()).generate(world, rand, i, j, k); }
/*     */         
/* 230 */         } else if (Vec3.func_72443_a(i, j, k).func_72445_d((world.func_72861_E()).field_71574_a, (world.func_72861_E()).field_71572_b, (world.func_72861_E()).field_71573_c) < 9000000.0D && 
/* 231 */           temp < 15.0F) {
/*     */           
/* 233 */           if (rand.nextInt(20000) == 0 && !WorldGenLooseRocks.rocksNearby(world, i, j, k)) (new WorldGenLooseRocks()).generateRocks(world, rand, i, j, k); 
/* 234 */           if (rand.nextInt(20000) == 0) (new WorldGenLooseRocks()).generateSticks(world, rand, i, j, k);
/*     */         
/*     */         } 
/*     */         
/* 238 */         boolean nearWater = false;
/*     */         
/* 240 */         for (int y = 0; y < 2 && !nearWater; y++) {
/*     */           
/* 242 */           for (int x = -4; x < 5 && !nearWater; x++) {
/*     */             
/* 244 */             for (int z = -4; z < 5 && !nearWater; z++) {
/*     */               
/* 246 */               if (j < 250 && j > 144 && world.func_72899_e(i + x, j - y, k + z) && world.func_147439_a(i + x, j - y, k + z).func_149688_o() == Material.field_151586_h) {
/* 247 */                 nearWater = true;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 252 */         if (TFC_Core.isGrass((Block)this) && !TFC_Core.isDryGrass((Block)this) && !nearWater && rain < 500.0F) {
/*     */           
/* 254 */           world.func_147465_d(i, j, k, TFC_Core.getTypeForDryGrassFromSoil((Block)this), meta, 2);
/*     */         }
/* 256 */         else if (TFC_Core.isGrass((Block)this) && TFC_Core.isDryGrass((Block)this) && (nearWater || rain >= 500.0F) && world.func_147439_a(i, j + 1, k) != Blocks.field_150433_aE) {
/*     */           
/* 258 */           world.func_147465_d(i, j, k, TFC_Core.getTypeForGrassFromSoil((Block)this), meta, 2);
/*     */         } 
/*     */       } 
/*     */       
/* 262 */       world.func_147471_g(i, j, k);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spreadGrass(World world, int i, int j, int k, Random rand) {
/* 268 */     for (int var6 = 0; var6 < 4; var6++) {
/*     */       
/* 270 */       int x = i + rand.nextInt(3) - 1;
/* 271 */       int z = k + rand.nextInt(3) - 1;
/* 272 */       if (world.func_72899_e(x, 144, z)) {
/*     */         
/* 274 */         int y = getTopSolidBlock(world, x, z);
/*     */         
/* 276 */         if (y > 0) {
/*     */           
/* 278 */           Block id = world.func_147439_a(x, y, z);
/* 279 */           int meta = world.func_72805_g(x, y, z);
/*     */ 
/*     */           
/* 282 */           if (TFC_Core.isDirt(id) && rand.nextInt(10) == 0) {
/* 283 */             world.func_147465_d(x, y, z, TFC_Core.getTypeForGrassWithRainByBlock(id, TFC_Climate.getRainfall(world, x, y, z)), meta, 2);
/* 284 */           } else if (TFC_Core.isClay(id) && rand.nextInt(10) == 0) {
/* 285 */             world.func_147465_d(x, y, z, TFC_Core.getTypeForClayGrass(meta), meta, 2);
/* 286 */           } else if (TFC_Core.isPeat(id) && rand.nextInt(10) == 0) {
/* 287 */             world.func_147465_d(x, y, z, TFCBlocks.peatGrass, 0, 2);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTopSolidBlock(World world, int xCoord, int zCoord) {
/* 298 */     Chunk chunk = world.func_72938_d(xCoord, zCoord);
/* 299 */     int x = xCoord;
/* 300 */     int z = zCoord;
/* 301 */     int y = chunk.func_76625_h() + 15;
/* 302 */     xCoord &= 0xF;
/*     */     
/* 304 */     for (zCoord &= 0xF; y > 0; y--) {
/*     */       
/* 306 */       Block block = chunk.func_150810_a(xCoord, y, zCoord);
/* 307 */       Material material = block.func_149688_o();
/*     */ 
/*     */       
/* 310 */       if (block.func_149662_c() && material
/* 311 */         .func_76218_k() && block
/* 312 */         .func_149686_d() && material != Material.field_151584_j && material != Material.field_151586_h && material
/*     */         
/* 314 */         .func_76230_c() && 
/* 315 */         !block.isFoliage((IBlockAccess)world, x, y, z) && (block
/* 316 */         .isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP) || block.isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.DOWN))) return y;
/*     */     
/*     */     } 
/* 319 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149724_b(World world, int x, int y, int z, Entity entity) {
/* 325 */     if (!world.field_72995_K && this != TFCBlocks.clayGrass2 && this != TFCBlocks.clayGrass && this != TFCBlocks.peatGrass) {
/*     */       
/* 327 */       Random r = new Random();
/* 328 */       if (BlockCollapsible.canFallBelow(world, x, y - 1, z) && r.nextInt(10) == 0 && !BlockCollapsible.isNearSupport(world, x, y, z, 4, 0.0F).booleanValue()) {
/*     */         
/* 330 */         int meta = world.func_72805_g(x, y, z);
/* 331 */         world.func_147465_d(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 342 */     return TFC_Core.getTypeForDirtFromGrass((Block)this).func_149650_a(metadata, rand, fortune);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 348 */     if (world.func_147437_c(x, y - 1, z) && !BlockCollapsible.isNearSupport(world, x, y, z, 4, 0.0F).booleanValue()) {
/*     */       
/* 350 */       int meta = world.func_72805_g(x, y, z);
/* 351 */       world.func_147465_d(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
/* 352 */       world.func_147464_a(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), 5);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */