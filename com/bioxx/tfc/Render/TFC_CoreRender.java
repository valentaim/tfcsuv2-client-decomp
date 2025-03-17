/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockSluice;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.FloraIndex;
/*     */ import com.bioxx.tfc.Food.FloraManager;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderFlora;
/*     */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*     */ import com.bioxx.tfc.TileEntities.TEPartial;
/*     */ import com.bioxx.tfc.TileEntities.TEWaterPlant;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class TFC_CoreRender
/*     */ {
/*     */   private static RenderBlocksLightCache renderer;
/*     */   
/*     */   public static boolean renderBlockSlab(Block block, int x, int y, int z, RenderBlocks renderblocks) {
/*  34 */     TEPartial te = (TEPartial)renderblocks.field_147845_a.func_147438_o(x, y, z);
/*     */ 
/*     */ 
/*     */     
/*  38 */     if (te.typeID <= 0) {
/*  39 */       return false;
/*     */     }
/*  41 */     int type = te.typeID;
/*  42 */     int meta = te.metaID;
/*  43 */     Block b = Block.func_149729_e(type);
/*  44 */     IIcon tex = b.func_149691_a(0, meta);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     long extraX = te.extraData & 0xFL;
/*  50 */     long extraY = te.extraData >> 4L & 0xFL;
/*  51 */     long extraZ = te.extraData >> 8L & 0xFL;
/*  52 */     long extraX2 = te.extraData >> 12L & 0xFL;
/*  53 */     long extraY2 = te.extraData >> 16L & 0xFL;
/*  54 */     long extraZ2 = te.extraData >> 20L & 0xFL;
/*     */     
/*  56 */     float div = 0.125F;
/*     */     
/*  58 */     renderblocks.func_147782_a((0.0F + div * (float)extraX), (0.0F + div * (float)extraY), (0.0F + div * (float)extraZ), (1.0F - div * (float)extraX2), (1.0F - div * (float)extraY2), (1.0F - div * (float)extraZ2));
/*     */ 
/*     */     
/*  61 */     boolean breaking = (renderblocks.field_147840_d != null);
/*  62 */     IIcon over = renderblocks.field_147840_d;
/*  63 */     if (!breaking && (b == TFCBlocks.ore || b == TFCBlocks.ore2 || b == TFCBlocks.ore3)) {
/*     */ 
/*     */       
/*  66 */       renderblocks.field_147840_d = getRockTexture((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
/*  67 */       renderblocks.func_147784_q(block, x, y, z);
/*  68 */       renderblocks.field_147840_d = over;
/*     */     } 
/*     */     
/*  71 */     if (!breaking) {
/*  72 */       renderblocks.field_147840_d = tex;
/*     */     }
/*  74 */     renderblocks.func_147784_q(block, x, y, z);
/*  75 */     renderblocks.field_147840_d = over;
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean renderBlockStairs(Block block, int x, int y, int z, RenderBlocks renderblocks) {
/*  83 */     if (renderer == null) {
/*  84 */       renderer = new RenderBlocksLightCache(renderblocks);
/*     */     } else {
/*  86 */       renderer.update(renderblocks);
/*     */     } 
/*     */     
/*  89 */     renderer.disableRender();
/*  90 */     renderer.func_147753_b(true);
/*  91 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  92 */     renderer.func_147784_q(block, x, y, z);
/*  93 */     renderer.func_147753_b(false);
/*  94 */     renderer.enableRender();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     TEPartial te = (TEPartial)renderblocks.field_147845_a.func_147438_o(x, y, z);
/* 112 */     if (te.typeID <= 0) {
/* 113 */       return false;
/*     */     }
/* 115 */     long rvmeta = te.extraData;
/* 116 */     int type = te.typeID;
/* 117 */     int temeta = te.metaID;
/* 118 */     IIcon myTexture = (renderblocks.field_147840_d == null) ? Block.func_149729_e(type).func_149691_a(0, temeta) : renderblocks.field_147840_d;
/*     */     
/* 120 */     if ((rvmeta & 0x1L) == 0L) {
/*     */       
/* 122 */       renderer.func_147782_a(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D);
/* 123 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 125 */     if ((rvmeta & 0x2L) == 0L) {
/*     */       
/* 127 */       renderer.func_147782_a(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
/* 128 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 130 */     if ((rvmeta & 0x4L) == 0L) {
/*     */       
/* 132 */       renderer.func_147782_a(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D);
/* 133 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 135 */     if ((rvmeta & 0x8L) == 0L) {
/*     */       
/* 137 */       renderer.func_147782_a(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
/* 138 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/*     */     
/* 141 */     if ((rvmeta & 0x10L) == 0L) {
/*     */       
/* 143 */       renderer.func_147782_a(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D);
/* 144 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 146 */     if ((rvmeta & 0x20L) == 0L) {
/*     */       
/* 148 */       renderer.func_147782_a(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
/* 149 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 151 */     if ((rvmeta & 0x40L) == 0L) {
/*     */       
/* 153 */       renderer.func_147782_a(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);
/* 154 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/* 156 */     if ((rvmeta & 0x80L) == 0L) {
/*     */       
/* 158 */       renderer.func_147782_a(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
/* 159 */       renderer.renderCachedBlock(block, x, y, z, myTexture);
/*     */     } 
/*     */     
/* 162 */     renderer.func_147771_a();
/* 163 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderSulfur(Block block, int x, int y, int z, RenderBlocks renderblocks) {
/* 169 */     IBlockAccess world = renderblocks.field_147845_a;
/* 170 */     if (world.func_147439_a(x, y, z + 1).isSideSolid(world, x, y, z + 1, ForgeDirection.NORTH)) {
/*     */       
/* 172 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.9900000095367432D, 1.0D, 1.0D, 1.0D);
/* 173 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/* 175 */     if (world.func_147439_a(x, y, z - 1).isSideSolid(world, x, y, z - 1, ForgeDirection.SOUTH)) {
/*     */       
/* 177 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.009999999776482582D);
/* 178 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/* 180 */     if (world.func_147439_a(x + 1, y, z).isSideSolid(world, x + 1, y, z, ForgeDirection.EAST)) {
/*     */       
/* 182 */       renderblocks.func_147782_a(0.9900000095367432D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 183 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/* 185 */     if (world.func_147439_a(x - 1, y, z).isSideSolid(world, x - 1, y, z, ForgeDirection.WEST)) {
/*     */       
/* 187 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 0.009999999776482582D, 1.0D, 1.0D);
/* 188 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/* 190 */     if (world.func_147439_a(x, y + 1, z).isSideSolid(world, x, y + 1, z, ForgeDirection.DOWN)) {
/*     */       
/* 192 */       renderblocks.func_147782_a(0.0D, 0.9900000095367432D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 193 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/* 195 */     if (world.func_147439_a(x, y - 1, z).isSideSolid(world, x, y - 1, z, ForgeDirection.UP)) {
/*     */       
/* 197 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.009999999776482582D, 1.0D);
/* 198 */       renderblocks.func_147784_q(block, x, y, z);
/*     */     } 
/*     */     
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderSnow(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 206 */     int meta = renderblocks.field_147845_a.func_72805_g(i, j, k);
/* 207 */     float drift = 0.04F + meta * 0.06F;
/* 208 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, drift, 1.0D);
/* 209 */     renderblocks.func_147784_q(block, i, j, k);
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderWoodTrunk(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 215 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (blockAccess.func_147438_o(i, j, k) != null && (blockAccess.func_147439_a(i, j - 1, k) == TFCBlocks.fruitTreeWood || blockAccess.func_147439_a(i, j - 1, k).func_149662_c())) {
/*     */       
/* 221 */       renderblocks.func_147782_a(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);
/* 222 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/* 224 */     if (blockAccess.func_147439_a(i - 1, j, k).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i - 1, j, k) == TFCBlocks.fruitTreeWood) {
/*     */       
/* 226 */       renderblocks.func_147782_a(0.0D, 0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D);
/* 227 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/* 229 */     if (blockAccess.func_147439_a(i + 1, j, k).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i + 1, j, k) == TFCBlocks.fruitTreeWood) {
/*     */       
/* 231 */       renderblocks.func_147782_a(0.5D, 0.4000000059604645D, 0.4000000059604645D, 1.0D, 0.6000000238418579D, 0.6000000238418579D);
/* 232 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/* 234 */     if (blockAccess.func_147439_a(i, j, k - 1).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i, j, k - 1) == TFCBlocks.fruitTreeWood) {
/*     */       
/* 236 */       renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.0D, 0.6000000238418579D, 0.6000000238418579D, 0.5D);
/* 237 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/* 239 */     if (blockAccess.func_147439_a(i, j, k + 1).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i, j, k + 1) == TFCBlocks.fruitTreeWood) {
/*     */       
/* 241 */       renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D, 1.0D);
/* 242 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/*     */     
/* 245 */     if (!((TEFruitTreeWood)blockAccess.func_147438_o(i, j, k)).isTrunk && blockAccess.func_147439_a(i, j - 1, k) != TFCBlocks.fruitTreeWood && !blockAccess.func_147439_a(i, j - 1, k).func_149662_c()) {
/*     */       
/* 247 */       renderblocks.func_147782_a(0.0D, 0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D);
/* 248 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/* 250 */       renderblocks.func_147782_a(0.5D, 0.4000000059604645D, 0.4000000059604645D, 1.0D, 0.6000000238418579D, 0.6000000238418579D);
/* 251 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/* 253 */       renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.0D, 0.6000000238418579D, 0.6000000238418579D, 0.5D);
/* 254 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/* 256 */       renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D, 1.0D);
/* 257 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/*     */ 
/*     */     
/* 261 */     return true;
/*     */   }
/*     */   
/* 264 */   public static Random renderRandom = new Random();
/*     */ 
/*     */   
/*     */   public static boolean renderLooseRock(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 268 */     boolean breaking = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/*     */     
/* 277 */     renderblocks.field_147837_f = true;
/*     */     
/* 279 */     DataLayer rockLayer1 = TFC_Climate.getCacheManager((World)worldClient).getRockLayerAt(i, k, 0);
/* 280 */     if (rockLayer1 != null && rockLayer1.block != null && !breaking) {
/* 281 */       renderblocks.field_147840_d = rockLayer1.block.func_149691_a(0, rockLayer1.data2);
/*     */     }
/* 283 */     int seed = i * k + j;
/* 284 */     renderRandom.setSeed(seed);
/*     */     
/* 286 */     float xOffset = (renderRandom.nextInt(5) - 2) * 0.05F;
/* 287 */     float zOffset = (renderRandom.nextInt(5) - 2) * 0.05F;
/*     */     
/* 289 */     float xOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;
/* 290 */     float yOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;
/* 291 */     float zOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;
/*     */     
/* 293 */     renderblocks.func_147782_a((0.35F + xOffset), 0.0D, (0.35F + zOffset), (0.65F + xOffset2), (0.15F + yOffset2), (0.65F + zOffset2));
/* 294 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 296 */     renderblocks.func_147771_a();
/*     */     
/* 298 */     return true;
/*     */   }
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
/*     */   
/*     */   public static boolean renderOre(Block block, int xCoord, int yCoord, int zCoord, float par5, float par6, float par7, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
/* 322 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IIcon getRockTexture(World world, int xCoord, int yCoord, int zCoord) {
/*     */     IIcon var27;
/* 328 */     DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 0);
/* 329 */     DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 1);
/* 330 */     DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 2);
/*     */     
/* 332 */     if (yCoord <= TFCOptions.rockLayer3Height) {
/* 333 */       var27 = rockLayer3.block.func_149691_a(5, rockLayer3.data2);
/* 334 */     } else if (yCoord <= TFCOptions.rockLayer2Height) {
/* 335 */       var27 = rockLayer2.block.func_149691_a(5, rockLayer2.data2);
/*     */     } else {
/* 337 */       var27 = rockLayer1.block.func_149691_a(5, rockLayer1.data2);
/* 338 */     }  return var27;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderMolten(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 343 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 344 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 346 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean renderFirepit(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 352 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.019999999552965164D, 1.0D);
/* 353 */     renderblocks.func_147784_q(block, i, j, k);
/* 354 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.019999999552965164D, 1.0D);
/* 355 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean renderForge(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 361 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
/* 362 */     renderblocks.func_147784_q(block, i, j, k);
/* 363 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderSluice(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 369 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */     
/* 371 */     int meta = blockAccess.func_72805_g(i, j, k);
/* 372 */     int dir = BlockSluice.getDirectionFromMetadata(meta);
/*     */ 
/*     */     
/* 375 */     if (!BlockSluice.isBlockFootOfBed(meta)) {
/*     */       
/* 377 */       if (dir == 0) {
/*     */         
/* 379 */         for (int count = 0; count < 4; count++)
/*     */         {
/*     */           
/* 382 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.0D + 0.25D * count, 1.0D, 1.0D - 0.125D * count, 0.05000000074505806D + 0.25D * count);
/* 383 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 385 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D, 0.8125D - 0.125D * count, 0.25D + 0.25D * count);
/* 386 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */       
/* 389 */       } else if (dir == 1) {
/*     */         
/* 391 */         if ((meta & 0x4) != 0)
/* 392 */           renderblocks.field_147867_u = 1; 
/* 393 */         for (int count = 0; count < 4; count++)
/*     */         {
/*     */           
/* 396 */           renderblocks.func_147782_a(0.949999988079071D - 0.25D * count, 0.0D, 0.0D, 1.0D - 0.25D * count, 1.0D - 0.125D * count, 1.0D);
/* 397 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 399 */           renderblocks.func_147782_a(0.75D - 0.25D * count, 0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 0.8125D - 0.125D * count, 1.0D);
/* 400 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */       
/* 403 */       } else if (dir == 2) {
/*     */         
/* 405 */         if ((meta & 0x4) != 0)
/* 406 */           renderblocks.field_147867_u = 3; 
/* 407 */         for (int count = 0; count < 4; count++)
/*     */         {
/*     */           
/* 410 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 1.0D, 1.0D - 0.125D * count, 1.0D - 0.25D * count);
/* 411 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 413 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.75D - 0.25D * count, 1.0D, 0.8125D - 0.125D * count, 0.949999988079071D - 0.25D * count);
/* 414 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */       
/* 417 */       } else if (dir == 3) {
/*     */         
/* 419 */         if ((meta & 0x4) != 0)
/* 420 */           renderblocks.field_147867_u = 2; 
/* 421 */         for (int count = 0; count < 4; count++)
/*     */         {
/*     */           
/* 424 */           renderblocks.func_147782_a(0.0D + 0.25D * count, 0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D - 0.125D * count, 1.0D);
/* 425 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 427 */           renderblocks.func_147782_a(0.05000000074505806D + 0.25D * count, 0.0D, 0.0D, 0.25D + 0.25D * count, 0.8125D - 0.125D * count, 1.0D);
/* 428 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 434 */       if (dir == 0)
/*     */       {
/* 436 */         for (int count = 0; count < 4; count++) {
/*     */ 
/*     */           
/* 439 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.0D + 0.25D * count, 1.0D, 0.5D - 0.125D * count, 0.05000000074505806D + 0.25D * count);
/* 440 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 442 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D, Math.max(0.3125D - 0.125D * count, 0.01D), 0.25D + 0.25D * count);
/* 443 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*     */       }
/* 446 */       if (dir == 1) {
/*     */         
/* 448 */         if ((meta & 0x4) != 0)
/* 449 */           renderblocks.field_147867_u = 1; 
/* 450 */         for (int count = 0; count < 4; count++) {
/*     */ 
/*     */           
/* 453 */           renderblocks.func_147782_a(0.949999988079071D - 0.25D * count, 0.0D, 0.0D, 1.0D - 0.25D * count, 0.5D - 0.125D * count, 1.0D);
/* 454 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 456 */           renderblocks.func_147782_a(0.75D - 0.25D * count, 0.0D, 0.0D, 0.949999988079071D - 0.25D * count, Math.max(0.3125D - 0.125D * count, 0.01D), 1.0D);
/* 457 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*     */       } 
/* 460 */       if (dir == 2) {
/*     */         
/* 462 */         if ((meta & 0x4) != 0)
/* 463 */           renderblocks.field_147867_u = 3; 
/* 464 */         for (int count = 0; count < 4; count++) {
/*     */ 
/*     */           
/* 467 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 1.0D, 0.5D - 0.125D * count, 1.0D - 0.25D * count);
/* 468 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 470 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.75D - 0.25D * count, 1.0D, Math.max(0.3125D - 0.125D * count, 0.01D), 0.949999988079071D - 0.25D * count);
/* 471 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*     */       } 
/*     */       
/* 475 */       if (dir == 3) {
/*     */         
/* 477 */         if ((meta & 0x4) != 0)
/* 478 */           renderblocks.field_147867_u = 2; 
/* 479 */         for (int count = 0; count < 4; count++) {
/*     */ 
/*     */           
/* 482 */           renderblocks.func_147782_a(0.0D + 0.25D * count, 0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 0.5D - 0.125D * count, 1.0D);
/* 483 */           renderblocks.func_147784_q(block, i, j, k);
/*     */           
/* 485 */           renderblocks.func_147782_a(0.05000000074505806D + 0.25D * count, 0.0D, 0.0D, 0.25D + 0.25D * count, Math.max(0.3125D - 0.125D * count, 0.01D), 1.0D);
/* 486 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*     */       } 
/*     */     } 
/* 490 */     renderblocks.field_147867_u = 0;
/*     */     
/* 492 */     renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     
/* 494 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderBlockWithCustomColorMultiplier(Block block, RenderBlocks renderBlocks, int xCoord, int yCoord, int zCoord, int colorMultiplier) {
/* 499 */     int l = colorMultiplier;
/* 500 */     float f = (l >> 16 & 0xFF) / 255.0F;
/* 501 */     float f1 = (l >> 8 & 0xFF) / 255.0F;
/* 502 */     float f2 = (l & 0xFF) / 255.0F;
/*     */     
/* 504 */     if (EntityRenderer.field_78517_a) {
/*     */       
/* 506 */       float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
/* 507 */       float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
/* 508 */       float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
/* 509 */       f = f3;
/* 510 */       f1 = f4;
/* 511 */       f2 = f5;
/*     */     } 
/*     */     
/* 514 */     return (Minecraft.func_71379_u() && block.func_149750_m() == 0) ? (renderBlocks.field_147849_o ? renderBlocks
/*     */       
/* 516 */       .func_147751_a(block, xCoord, yCoord, zCoord, f, f1, f2) : renderBlocks
/* 517 */       .func_147751_a(block, xCoord, yCoord, zCoord, f, f1, f2)) : renderBlocks
/* 518 */       .func_147736_d(block, xCoord, yCoord, zCoord, f, f1, f2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderFruitLeaves(Block block, int xCoord, int yCoord, int zCoord, RenderBlocks renderblocks) {
/* 523 */     int meta = renderblocks.field_147845_a.func_72805_g(xCoord, yCoord, zCoord);
/* 524 */     if (meta >= 8) {
/* 525 */       meta -= 8;
/*     */     }
/* 527 */     FloraManager manager = FloraManager.getInstance();
/* 528 */     FloraIndex index = manager.findMatchingIndex(BlockFruitLeaves.getType(block, meta));
/*     */     
/* 530 */     renderblocks.func_147784_q(block, xCoord, yCoord, zCoord);
/* 531 */     if (index != null && (index.inBloom(TFC_Time.getSeasonAdjustedMonth(zCoord)) || index.inHarvest(TFC_Time.getSeasonAdjustedMonth(zCoord)))) {
/*     */       
/* 533 */       renderblocks.field_147840_d = getFruitTreeOverlay(renderblocks.field_147845_a, xCoord, yCoord, zCoord);
/* 534 */       if (renderblocks.field_147840_d != null)
/* 535 */         renderBlockWithCustomColorMultiplier(block, renderblocks, xCoord, yCoord, zCoord, 16777215); 
/* 536 */       renderblocks.func_147771_a();
/*     */     } 
/* 538 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderSeaPlant(Block par1Block, int par2, int par3, int par4, RenderBlocks renderblocks) {
/* 543 */     boolean substrateRender = false;
/* 544 */     boolean plantRender = false;
/* 545 */     TileEntity te = renderblocks.field_147845_a.func_147438_o(par2, par3, par4);
/* 546 */     if (te instanceof TEWaterPlant) {
/* 547 */       TEWaterPlant wp = (TEWaterPlant)te;
/* 548 */       if (wp.getBlockFromType() != null) {
/* 549 */         substrateRender = renderblocks.func_147736_d(wp.getBlockFromType(), par2, par3, par4, 1.0F, 1.0F, 1.0F);
/* 550 */         plantRender = RenderFlora.render(par1Block, par2, par3, par4, renderblocks);
/*     */       } 
/*     */     } 
/* 553 */     return (substrateRender && plantRender);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IIcon getFruitTreeOverlay(IBlockAccess world, int x, int y, int z) {
/* 558 */     IIcon out = null;
/* 559 */     int meta = world.func_72805_g(x, y, z);
/* 560 */     Block id = world.func_147439_a(x, y, z);
/* 561 */     int offset = (id == TFCBlocks.fruitTreeLeaves) ? 0 : 8;
/*     */     
/* 563 */     FloraManager manager = FloraManager.getInstance();
/* 564 */     FloraIndex index = manager.findMatchingIndex(BlockFruitLeaves.getType(id, meta & 0x7));
/* 565 */     if (index != null)
/*     */     {
/* 567 */       if (index.inBloom(TFC_Time.getSeasonAdjustedMonth(z))) {
/* 568 */         out = BlockFruitLeaves.iconsFlowers[(meta & 0x7) + offset];
/* 569 */       } else if (meta >= 8) {
/* 570 */         out = BlockFruitLeaves.iconsFruit[(meta & 0x7) + offset];
/*     */       }  } 
/* 572 */     return out;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TFC_CoreRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */