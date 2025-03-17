/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
/*     */ import com.bioxx.tfc.TileEntities.TEPartial;
/*     */ import com.bioxx.tfc.api.Enums.TFCDirection;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.ByteCoord;
/*     */ import com.bioxx.tfc.api.Util.CollapseData;
/*     */ import com.bioxx.tfc.api.Util.CollapseList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ public class BlockCollapsible
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   public Block dropBlock;
/*     */   public static boolean fallInstantly;
/*     */   
/*     */   protected BlockCollapsible(Material material, Block block) {
/*  37 */     super(material);
/*  38 */     this.dropBlock = block;
/*  39 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockCollapsible(Material material) {
/*  44 */     super(material);
/*  45 */     this.dropBlock = Blocks.field_150350_a;
/*  46 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getDropBlock(World world, int x, int y, int z) {
/*  51 */     int[] data = new int[2];
/*  52 */     data[0] = Block.func_149682_b(this.dropBlock);
/*  53 */     data[1] = world.func_72805_g(x, y, z);
/*  54 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canFallBelow(World world, int x, int y, int z) {
/*  60 */     Block block = world.func_147439_a(x, y, z);
/*  61 */     if (block.isAir((IBlockAccess)world, x, y, z)) return true;
/*     */     
/*  63 */     if (block == Blocks.field_150480_ab)
/*  64 */       return true; 
/*  65 */     if (block == TFCBlocks.tallGrass)
/*  66 */       return true; 
/*  67 */     if (block == TFCBlocks.torch)
/*  68 */       return true; 
/*  69 */     if (block == TFCBlocks.smokeRack)
/*  70 */       return false; 
/*  71 */     if (block == TFCBlocks.toolRack) {
/*  72 */       return false;
/*     */     }
/*  74 */     if (block == Blocks.field_150357_h)
/*  75 */       return false; 
/*  76 */     if (block == TFCBlocks.charcoal)
/*  77 */       return false; 
/*  78 */     if (block == TFCBlocks.molten) {
/*  79 */       return false;
/*     */     }
/*  81 */     Material material = block.func_149688_o();
/*  82 */     if (material == Material.field_151586_h || material == Material.field_151587_i) return true;
/*     */ 
/*     */     
/*  85 */     if (!block.func_149662_c() && !block.func_149686_d() && !world.isSideSolid(x, y, z, ForgeDirection.UP)) return false;
/*     */     
/*  87 */     return (material == Material.field_151586_h || material == Material.field_151587_i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropCarvedStone(World world, int x, int y, int z) {
/*  92 */     if (world.func_147439_a(x + 1, y, z).func_149662_c())
/*     */       return; 
/*  94 */     if (world.func_147439_a(x - 1, y, z).func_149662_c())
/*     */       return; 
/*  96 */     if (world.func_147439_a(x, y, z + 1).func_149662_c())
/*     */       return; 
/*  98 */     if (world.func_147439_a(x, y, z - 1).func_149662_c())
/*     */       return; 
/* 100 */     if (world.func_147439_a(x, y + 1, z).func_149662_c())
/*     */       return; 
/* 102 */     if (world.func_147439_a(x, y - 1, z).func_149662_c()) {
/*     */       return;
/*     */     }
/* 105 */     func_149642_a(world, x, y, z, new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
/* 106 */     world.func_147468_f(x, y, z);
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
/*     */   public static void updateTickCollapsible(World world, int i, int j, int k, Random random, Block us, boolean checkForSupport) {
/* 169 */     if (!world.field_72995_K) {
/* 170 */       if (canFallBelow(world, i, j - 1, k)) {
/* 171 */         if (!checkForSupport || !isNearSupport(world, i, j, k, 4, 0.0F).booleanValue()) {
/* 172 */           tryToFall(world, i, j, k, us);
/*     */         }
/*     */       } else {
/* 175 */         int airSides = 0;
/* 176 */         boolean[] sides = new boolean[4];
/* 177 */         if (world.func_147437_c(i + 1, j, k)) {
/* 178 */           airSides++;
/* 179 */           if (canFallBelow(world, i + 1, j - 1, k)) {
/* 180 */             sides[0] = true;
/*     */           }
/*     */         } 
/*     */         
/* 184 */         if (world.func_147437_c(i, j, k + 1)) {
/* 185 */           airSides++;
/* 186 */           if (canFallBelow(world, i, j - 1, k + 1)) {
/* 187 */             sides[1] = true;
/*     */           }
/*     */         } 
/*     */         
/* 191 */         if (world.func_147437_c(i - 1, j, k)) {
/* 192 */           airSides++;
/* 193 */           if (canFallBelow(world, i - 1, j - 1, k)) {
/* 194 */             sides[2] = true;
/*     */           }
/*     */         } 
/*     */         
/* 198 */         if (world.func_147437_c(i, j, k - 1)) {
/* 199 */           airSides++;
/* 200 */           if (canFallBelow(world, i, j - 1, k - 1)) {
/* 201 */             sides[3] = true;
/*     */           }
/*     */         } 
/*     */         
/* 205 */         if (airSides > 2 && (sides[0] || sides[1] || sides[2] || sides[3])) {
/* 206 */           if (checkForSupport && isNearSupport(world, i, j, k, 4, 0.0F).booleanValue()) {
/*     */             return;
/*     */           }
/*     */           
/* 210 */           int meta = world.func_72805_g(i, j, k);
/* 211 */           world.func_147468_f(i, j, k);
/*     */           
/*     */           int rng;
/* 214 */           for (rng = random.nextInt(4); !sides[rng]; rng = (rng + 1) % 4);
/*     */ 
/*     */ 
/*     */           
/* 218 */           switch (rng) {
/*     */             case 0:
/* 220 */               world.func_147465_d(i + 1, j, k, us, meta, 2);
/* 221 */               tryToFall(world, i + 1, j, k, us);
/*     */               break;
/*     */             case 1:
/* 224 */               world.func_147465_d(i, j, k + 1, us, meta, 2);
/* 225 */               tryToFall(world, i, j, k + 1, us);
/*     */               break;
/*     */             case 2:
/* 228 */               world.func_147465_d(i - 1, j, k, us, meta, 2);
/* 229 */               tryToFall(world, i - 1, j, k, us);
/*     */               break;
/*     */             case 3:
/* 232 */               world.func_147465_d(i, j, k - 1, us, meta, 2);
/* 233 */               tryToFall(world, i, j, k - 1, us);
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static Boolean isNearSupport(World world, int i, int j, int k, int range, float collapseChance) {
/* 242 */     if (world == null) return Boolean.valueOf(false); 
/* 243 */     if (!world.func_72873_a(i, j, k, range + 1)) return Boolean.valueOf(true);
/*     */     
/* 245 */     for (int y = -1; y <= 1; ) { if (TFC_Core.isVertSupport(world.func_147439_a(i, j + y, k))) return Boolean.valueOf(true);  y++; }
/* 246 */      for (int x = -range; x <= range; x++) {
/* 247 */       for (int z = -range; z <= range; z++) {
/* 248 */         for (int m = -1; m <= 1; m++) {
/* 249 */           if (TFC_Core.isHorizSupport(world.func_147439_a(i + x, j + m, k + z)))
/* 250 */             if (world.field_73012_v.nextFloat() < collapseChance / 100.0F) { world.func_147468_f(i + x, j + m, k + z); }
/* 251 */             else { return Boolean.valueOf(true); }  
/*     */         } 
/*     */       } 
/* 254 */     }  return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isUnderLoad(World world, int i, int j, int k) {
/* 260 */     for (int x = 1; x <= TFCOptions.minimumRockLoad; x++) {
/*     */       
/* 262 */       if (!world.func_147439_a(i, j + x, k).func_149662_c())
/* 263 */         return Boolean.valueOf(false); 
/*     */     } 
/* 265 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Boolean tryToCollapse(World world, int x, int y, int z, float collapseChance) {
/* 269 */     if (world.field_72995_K) return Boolean.valueOf(false);
/*     */     
/* 271 */     int[] drop = getDropBlock(world, x, y, z);
/* 272 */     Block fallingBlock = Block.func_149729_e(drop[0]);
/* 273 */     if (fallingBlock == Blocks.field_150350_a) return Boolean.valueOf(false); 
/* 274 */     if (world.func_147439_a(x, y, z) != Blocks.field_150357_h && world.func_147439_a(x, y, z) != fallingBlock) {
/*     */       
/* 276 */       int fallingBlockMeta = drop[1];
/* 277 */       if (canFallBelow(world, x, y - 1, z) && isUnderLoad(world, x, y, z).booleanValue() && !isNearSupport(world, x, y, z, 4, collapseChance).booleanValue()) {
/* 278 */         if (fallingBlock != null) {
/* 279 */           EntityFallingBlockTFC ent = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), fallingBlock, fallingBlockMeta);
/* 280 */           if (this instanceof BlockStone) ent = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), fallingBlock, fallingBlockMeta + 8); 
/* 281 */           ent.aliveTimer = -5000;
/* 282 */           world.func_72838_d((Entity)ent);
/* 283 */           Random r = new Random((x * y + z));
/* 284 */           if (r.nextInt(100) > 90) world.func_72956_a((Entity)ent, "terrafirmacraft:rock.slide.long", 1.0F, 0.8F + r.nextFloat() / 2.0F);
/*     */         
/*     */         } 
/* 287 */         if (world.func_147439_a(x, y, z) instanceof BlockOre && !TFCOptions.enableCaveInsDestroyOre) { TFC_Core.setBlockToAirWithDrops(world, x, y, z); }
/* 288 */         else { world.func_147468_f(x, y, z); }
/*     */         
/* 290 */         if (world.func_147439_a(x, y - 1, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 1, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 1, z)).metaID == fallingBlockMeta) {
/* 291 */           world.func_147468_f(x, y - 1, z);
/* 292 */           if (world.func_147439_a(x, y - 2, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 2, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 2, z)).metaID == fallingBlockMeta) {
/* 293 */             world.func_147468_f(x, y - 2, z);
/* 294 */             if (world.func_147439_a(x, y - 3, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 3, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 3, z)).metaID == fallingBlockMeta) {
/* 295 */               world.func_147468_f(x, y - 3, z);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 300 */         return Boolean.valueOf(true);
/*     */       } 
/* 302 */       return Boolean.valueOf(false);
/*     */     } 
/*     */     
/* 305 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void tryToFall(World world, int x, int y, int z, Block block) {
/* 311 */     if (!world.field_72995_K) {
/* 312 */       int meta = world.func_72805_g(x, y, z);
/* 313 */       if (canFallBelow(world, x, y - 1, z) && y >= 0 && (block instanceof BlockSand || !isNearSupport(world, x, y, z, 4, 0.0F).booleanValue())) {
/* 314 */         if (!fallInstantly && world.func_72904_c(x - 32, y - 32, z - 32, x + 32, y + 32, z + 32))
/* 315 */         { if (!world.field_72995_K) {
/* 316 */             EntityFallingBlockTFC entityfallingblock = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), block, meta);
/* 317 */             world.func_72838_d((Entity)entityfallingblock);
/* 318 */             if (block instanceof BlockCobble)
/* 319 */             { world.func_72956_a((Entity)entityfallingblock, "terrafirmacraft:rock.slide.short", 1.0F, 0.8F + world.field_73012_v.nextFloat() / 2.0F); }
/* 320 */             else { world.func_72956_a((Entity)entityfallingblock, "terrafirmacraft:dirt.slide.short", 1.0F, 0.8F + world.field_73012_v.nextFloat() / 2.0F); }
/*     */           
/*     */           }  }
/* 323 */         else { world.func_147468_f(x, y, z);
/* 324 */           for (; canFallBelow(world, x, y - 1, z) && y > 0; y--);
/* 325 */           if (y > 0) world.func_147465_d(x, y, z, block, meta, 2);
/*     */            }
/*     */       
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
/* 336 */     float softModifier = 0.1F;
/*     */     
/* 338 */     int finalCollapseRatio = (TFCOptions.initialCollapseRatio > 0) ? TFCOptions.initialCollapseRatio : 10;
/*     */ 
/*     */     
/* 341 */     if (entityplayer != null) {
/*     */       
/* 343 */       entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 344 */       entityplayer.func_71020_j(0.075F);
/*     */     } 
/*     */ 
/*     */     
/* 348 */     if (this == TFCBlocks.stoneSed) {
/* 349 */       finalCollapseRatio = (int)(finalCollapseRatio - finalCollapseRatio * softModifier);
/*     */     }
/*     */     
/* 352 */     if (TFCOptions.enableCaveIns && world.field_73012_v.nextInt(finalCollapseRatio) == 0) {
/*     */ 
/*     */       
/* 355 */       int counter = 0;
/* 356 */       while (counter < 100) {
/*     */         
/* 358 */         int scanX = -4 + world.field_73012_v.nextInt(9);
/* 359 */         int scanY = -2 + world.field_73012_v.nextInt(5);
/* 360 */         int scanZ = -4 + world.field_73012_v.nextInt(9);
/* 361 */         if (world.func_147439_a(x + scanX, y + scanY, z + scanZ) instanceof BlockCollapsible && ((BlockCollapsible)world
/* 362 */           .func_147439_a(x + scanX, y + scanY, z + scanZ)).tryToCollapse(world, x + scanX, y + scanY, z + scanZ, 0.0F).booleanValue()) {
/*     */           
/* 364 */           triggerCollapse(world, entityplayer, x + scanX, y + scanY, z + scanZ, meta);
/*     */           return;
/*     */         } 
/* 367 */         counter++;
/*     */       } 
/*     */     } 
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
/*     */   public void triggerCollapse(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
/* 384 */     int height = 4;
/* 385 */     int range = 5 + world.field_73012_v.nextInt(31);
/* 386 */     for (int y = -4; y <= 1; y++) {
/*     */       
/* 388 */       for (int x = -range; x <= range; x++) {
/*     */         
/* 390 */         for (int z = -range; z <= range; z++) {
/*     */           
/* 392 */           double distSqrd = Math.pow((i - i + x), 2.0D) + Math.pow((j - j + y), 2.0D) + Math.pow((k - k + z), 2.0D);
/*     */           
/* 394 */           if (world.field_73012_v.nextInt(100) < TFCOptions.propogateCollapseChance && distSqrd < 1225.0D)
/*     */           {
/* 396 */             if (world.func_147439_a(i + x, j + y, k + z) instanceof BlockCollapsible && ((BlockCollapsible)world.func_147439_a(i + x, j + y, k + z)).tryToCollapse(world, i + x, j + y, k + z, 1.0F).booleanValue()) {
/*     */               
/* 398 */               int done = 0;
/* 399 */               while (done < height) {
/*     */                 
/* 401 */                 done++;
/* 402 */                 if (world.func_147439_a(i + x, j + y, k + z) instanceof BlockCollapsible && world.field_73012_v.nextInt(100) < TFCOptions.propogateCollapseChance) {
/* 403 */                   ((BlockCollapsible)world.func_147439_a(i + x, j + y, k + z)).tryToCollapse(world, i + x, j + y + done, k + z, 1.0F); continue;
/* 404 */                 }  done = height;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ByteCoord> getCollapseMap(World world, int i, int j, int k) {
/* 420 */     List<ByteCoord> map = new ArrayList<>();
/* 421 */     List<ByteCoord> checkedmap = new ArrayList<>();
/* 422 */     CollapseList<CollapseData> checkQueue = new CollapseList();
/* 423 */     float incrementChance = 2.5F;
/* 424 */     float incrementChanceDiag = 3.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 433 */     map.add(new ByteCoord(0, 0, 0));
/*     */     
/* 435 */     checkQueue.add(new CollapseData(new ByteCoord(1, 0, 0), TFCOptions.propogateCollapseChance, TFCDirection.EAST));
/* 436 */     checkQueue.add(new CollapseData(new ByteCoord(-1, 0, 0), TFCOptions.propogateCollapseChance, TFCDirection.WEST));
/* 437 */     checkQueue.add(new CollapseData(new ByteCoord(1, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.NORTHEAST));
/* 438 */     checkQueue.add(new CollapseData(new ByteCoord(1, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTHEAST));
/* 439 */     checkQueue.add(new CollapseData(new ByteCoord(-1, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.NORTHWEST));
/* 440 */     checkQueue.add(new CollapseData(new ByteCoord(-1, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTHWEST));
/* 441 */     checkQueue.add(new CollapseData(new ByteCoord(0, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTH));
/* 442 */     checkQueue.add(new CollapseData(new ByteCoord(0, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.NORTH));
/*     */     
/* 444 */     while (checkQueue.peek() != null) {
/*     */       
/* 446 */       CollapseData block = (CollapseData)checkQueue.peek();
/* 447 */       if (!checkedmap.contains(block.coords) && world.field_73012_v.nextFloat() < block.collapseChance / 100.0F) {
/*     */ 
/*     */ 
/*     */         
/* 451 */         int worldX = block.coords.x + i;
/* 452 */         int worldY = block.coords.y + j;
/* 453 */         int worldZ = block.coords.z + k;
/* 454 */         int localX = block.coords.x;
/* 455 */         int localY = block.coords.y;
/* 456 */         int localZ = block.coords.z;
/* 457 */         if (world.func_147437_c(worldX, worldY, worldZ)) {
/* 458 */           checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 1, localZ + 0), block.collapseChance - 10.0F, TFCDirection.UP));
/* 459 */         } else if (world.func_147439_a(worldX, worldY, worldZ) instanceof BlockCollapsible && ((BlockCollapsible)world
/* 460 */           .func_147439_a(worldX, worldY, worldZ)).tryToCollapse(world, worldX, worldY, worldZ, block.collapseChance).booleanValue()) {
/*     */           
/* 462 */           map.add(block.coords);
/*     */           
/* 464 */           switch (block.direction) {
/*     */ 
/*     */             
/*     */             case NORTH:
/* 468 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/* 469 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/* 470 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTH:
/* 475 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 476 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/* 477 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case EAST:
/* 482 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 483 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/* 484 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case WEST:
/* 489 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 490 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/* 491 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTHEAST:
/* 496 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHEAST));
/* 497 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 498 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTHWEST:
/* 503 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHWEST));
/* 504 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 505 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case NORTHEAST:
/* 510 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHEAST));
/* 511 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/* 512 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case NORTHWEST:
/* 517 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHWEST));
/* 518 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/* 519 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 524 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
/* 525 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
/* 526 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHEAST));
/* 527 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHEAST));
/* 528 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHWEST));
/* 529 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHWEST));
/* 530 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
/* 531 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/*     */       } 
/* 537 */       checkedmap.add(block.coords);
/* 538 */       checkQueue.removeFirst();
/*     */     } 
/* 540 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
/* 546 */     func_149636_a(world, (EntityPlayer)null, x, y, z, world.func_72805_g(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
/* 552 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockCollapsible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */