/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEOre;
/*     */ import com.bioxx.tfc.api.Enums.EnumOreGen;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.common.FMLLog;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class WorldGenMinableTFCNew
/*     */   extends WorldGenerator
/*     */ {
/*  30 */   private static final Logger logger = FMLLog.getLogger();
/*  31 */   private static List<List<Object>> oreList = new ArrayList<>();
/*     */   
/*     */   public static int mPChunkX;
/*     */   
/*     */   public static int mPChunkZ;
/*     */   
/*     */   private int xChunk;
/*     */   
/*     */   private int zChunk;
/*     */   
/*     */   public Block mPBlock;
/*     */   
/*     */   private final int minableBlockMeta;
/*     */   
/*     */   public static int mPPrevX;
/*     */   
/*     */   public static int mPPrevZ;
/*     */   
/*     */   public static Block mPPrevBlock;
/*     */   
/*     */   public static int mPPrevMeta;
/*     */   
/*     */   private static boolean genBeforeCheck;
/*     */   
/*     */   public static int mineCount;
/*     */   
/*     */   public static int mineCountM;
/*     */   private Random rand;
/*     */   private static World worldObj;
/*     */   private int oreMin;
/*     */   private int oreMax;
/*     */   private final int rarity;
/*     */   private final EnumOreGen type;
/*     */   private final int SphereXSize;
/*     */   private final int SphereYSize;
/*     */   private final int SphereZSize;
/*     */   private final int VeinWidth;
/*     */   private final int VeinBaseHeight;
/*     */   private final int VeinDownFactor;
/*     */   private final int AreaNumber;
/*     */   private final int AreaMaxDistance;
/*     */   private final int CellSize;
/*     */   private final int rnd;
/*     */   private final String oreName;
/*     */   private final Block minableBlock;
/*     */   private int numberOfBlocks;
/*     */   
/*     */   public WorldGenMinableTFCNew(EnumOreGen type, Block block, int j, Block layerBlock, int layerMeta, int rarity, int rnd, int SphereXSize, int SphereYSize, int SphereZSize, int VeinWidth, int VeinBaseHeight, int VeinDownFactor, int an, int amd, int cs, String name) {
/*  79 */     this.minableBlock = block;
/*  80 */     this.minableBlockMeta = j;
/*  81 */     this.rarity = rarity;
/*  82 */     this.rnd = rnd;
/*  83 */     this.type = type;
/*  84 */     this.SphereXSize = SphereXSize;
/*  85 */     this.SphereYSize = SphereYSize;
/*  86 */     this.SphereZSize = SphereZSize;
/*  87 */     this.VeinWidth = VeinWidth;
/*  88 */     this.VeinBaseHeight = VeinBaseHeight;
/*  89 */     this.VeinDownFactor = VeinDownFactor;
/*  90 */     this.AreaNumber = an;
/*  91 */     this.AreaMaxDistance = amd;
/*  92 */     this.CellSize = cs;
/*  93 */     this.oreName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateBeforeCheck() {
/*  98 */     genBeforeCheck = false;
/*  99 */     genBeforeCheck = oreList.contains(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) }));
/* 100 */     if (!genBeforeCheck) oreList.add(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) })); 
/* 101 */     return genBeforeCheck;
/*     */   }
/*     */ 
/*     */   
/*     */   private int percent(float t) {
/* 106 */     int rrnd = 1 + this.rand.nextInt(this.rnd - 1);
/* 107 */     float z = t / 100.0F * rrnd;
/* 108 */     return (int)z;
/*     */   }
/*     */ 
/*     */   
/*     */   private int toReal(int param) {
/* 113 */     return this.rand.nextBoolean() ? (param - percent(param)) : (param + percent(param));
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCenter(int min, int max, int size) throws Exception {
/* 118 */     int r = max - min - size;
/* 119 */     if (r < 0) throw new Exception(); 
/* 120 */     return max - size / 2 - this.rand.nextInt(max - min - size);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createMine(World worldObj, int x, int z, int miny, int maxy) {
/* 125 */     int posX = x;
/* 126 */     int posZ = z;
/*     */     
/* 128 */     this.numberOfBlocks = 0;
/* 129 */     int Y = 0; try {
/*     */       int size;
/*     */       int Ysize;
/* 132 */       switch (this.type) {
/*     */         case Area:
/* 134 */           Y = getCenter(miny, maxy, this.AreaMaxDistance);
/* 135 */           bODgenerateArea(worldObj, this.rand, posX, Y, posZ, this.AreaMaxDistance);
/*     */           break;
/*     */         case Vein:
/* 138 */           size = toReal(this.VeinBaseHeight);
/* 139 */           Y = getCenter(miny, maxy, size);
/* 140 */           bODgenerateVein(worldObj, this.rand, posX, Y, posZ, size);
/*     */           break;
/*     */         case Lens:
/* 143 */           Ysize = toReal(this.SphereYSize);
/* 144 */           Y = getCenter(miny, maxy, Ysize);
/* 145 */           bODgenerateLens(worldObj, this.rand, posX, Y, posZ, toReal(this.SphereXSize), Ysize, 
/* 146 */               toReal(this.SphereZSize));
/*     */           break;
/*     */       } 
/* 149 */       if (TFCOptions.enableDebugMode) logger.warn("Generating " + this.oreName + " at x=" + posX + ", y=" + Y + ", z=" + posZ); 
/* 150 */     } catch (Exception e) {
/* 151 */       logger.warn("Generating " + this.oreName + " at x=" + posX + ", y=" + Y + ", z=" + posZ);
/* 152 */       logger.warn("Configuration error ! ");
/*     */     } 
/*     */     
/* 155 */     if (TFCOptions.enableDebugMode) logger.warn("Generated " + this.numberOfBlocks + " blocks");
/*     */   
/*     */   }
/*     */   
/*     */   public boolean generate(World world, Random random, int x, int z, int min, int max) {
/* 160 */     mPChunkX = x;
/* 161 */     mPChunkZ = z;
/* 162 */     this.rand = random;
/* 163 */     worldObj = world;
/* 164 */     mineCount = 0;
/* 165 */     oreList.clear();
/* 166 */     this.mPBlock = this.minableBlock;
/* 167 */     this.oreMin = min;
/* 168 */     this.oreMax = max;
/*     */     
/* 170 */     if (mPChunkX != mPPrevX || mPChunkZ != mPPrevZ || mPPrevBlock != this.mPBlock || this.minableBlockMeta != mPPrevMeta)
/*     */     {
/* 172 */       if (!generateBeforeCheck()) {
/*     */         
/* 174 */         mPPrevX = mPChunkX;
/* 175 */         mPPrevZ = mPChunkZ;
/* 176 */         this.xChunk = mPChunkX;
/* 177 */         this.zChunk = mPChunkZ;
/* 178 */         mPPrevBlock = this.mPBlock;
/* 179 */         mPPrevMeta = this.minableBlockMeta;
/*     */         
/* 181 */         if (this.rarity == 1 || (this.rarity > 0 && this.rand.nextInt(this.rarity) == 0)) createMine(worldObj, this.xChunk, this.zChunk, min, max); 
/*     */       } 
/*     */     }
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bODgenerateVein(World world, Random rand, int parX, int parY, int parZ, int xyz) {
/* 190 */     int posX = parX;
/* 191 */     int posY = parY + xyz / 2;
/* 192 */     int posZ = parZ;
/*     */     
/* 194 */     int vw = toReal(this.VeinWidth);
/*     */     
/* 196 */     boolean[] array = new boolean[xyz];
/* 197 */     for (int l = 0; l < xyz; l++) {
/* 198 */       array[l] = rand.nextBoolean();
/*     */     }
/* 200 */     boolean directionxz = rand.nextBoolean();
/* 201 */     int i = 0;
/*     */     
/*     */     do {
/* 204 */       if (directionxz) { drawPlane(world, posX++, posY - i, posZ, array, directionxz, vw); }
/* 205 */       else { drawPlane(world, posX, posY - i, posZ++, array, directionxz, vw); }
/* 206 */        i += rand.nextInt(this.VeinDownFactor);
/*     */     }
/* 208 */     while (i < xyz / 2);
/*     */ 
/*     */ 
/*     */     
/* 212 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawPlane(World world, int x, int y, int z, boolean[] array, boolean directionxz, int size) {
/* 217 */     for (int i = 0; i < size; i++) {
/* 218 */       if (directionxz)
/* 219 */       { drawLine(world, x, y, z + i, array, directionxz); }
/* 220 */       else { drawLine(world, x + i, y, z, array, directionxz); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawLine(World world, int x, int y, int z, boolean[] array, boolean directionxz) {
/* 226 */     int random = 10;
/* 227 */     for (int l = 0; l < array.length; l++) {
/*     */       
/* 229 */       int r = random - l * 100 / array.length * random / 100;
/* 230 */       if (r == 0) r = 1;
/*     */ 
/*     */       
/* 233 */       boolean smes = array[l];
/*     */       
/* 235 */       if (directionxz)
/*     */       
/*     */       { 
/* 238 */         if (smes) x++;
/*     */         
/*     */         
/*     */         
/*     */          }
/*     */       
/* 244 */       else if (smes) { z++; }
/*     */ 
/*     */ 
/*     */       
/* 248 */       int rar = 1;
/* 249 */       if (l < array.length / 3) { rar = 2; }
/* 250 */       else if (l < array.length / 3 * 2) { rar = 0; }
/*     */       
/* 252 */       if (r == 1) { setBlock(world, x, y + array.length / 2 - l, z, rar); }
/* 253 */       else if (this.rand.nextInt(r) == 1) { setBlock(world, x, y + array.length / 2 - l, z, rar); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private static double lengthSq(double x, double y, double z) {
/* 259 */     return x * x + y * y + z * z;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bODgenerateArea(World world, Random rand, int par3, int par4, int par5, int amd) {
/* 264 */     amd = toReal(this.AreaMaxDistance);
/* 265 */     Vec3 start = Vec3.func_72443_a(par3, par4, par5);
/* 266 */     for (int i = 0; i < this.AreaNumber; ) {
/*     */       
/*     */       while (true) {
/*     */         
/* 270 */         int x = rand.nextInt(amd / 2);
/* 271 */         int y = rand.nextInt(amd / 2);
/* 272 */         int z = rand.nextInt(amd / 2);
/* 273 */         Vec3 to = Vec3.func_72443_a(rand.nextBoolean() ? (par3 + x) : (par3 - x), rand.nextBoolean() ? (par4 + y) : (par4 - y), rand.nextBoolean() ? (par5 + z) : (par5 - z));
/* 274 */         if (start.func_72438_d(to) <= (amd / 2))
/* 275 */         { int rx = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
/* 276 */           int ry = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
/* 277 */           int rz = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
/* 278 */           bODgenerateLens(world, rand, (int)to.field_72450_a, (int)to.field_72448_b, (int)to.field_72449_c, rx, ry, rz); break; } 
/*     */       }  i++;
/* 280 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bODgenerateLens(World world, Random rand, int par3, int par4, int par5, int dx, int dy, int dz) {
/* 286 */     int xpos = par3;
/* 287 */     int ypos = par4;
/* 288 */     int zpos = par5;
/*     */     
/* 290 */     double radiusX = (dx / 2);
/* 291 */     double radiusY = (dy / 2);
/* 292 */     double radiusZ = (dz / 2);
/*     */     
/* 294 */     radiusX += 0.5D;
/* 295 */     radiusY += 0.5D;
/* 296 */     radiusZ += 0.5D;
/*     */     
/* 298 */     double invRadiusX = 1.0D / radiusX;
/* 299 */     double invRadiusY = 1.0D / radiusY;
/* 300 */     double invRadiusZ = 1.0D / radiusZ;
/*     */     
/* 302 */     int ceilRadiusX = (int)Math.ceil(radiusX);
/* 303 */     int ceilRadiusY = (int)Math.ceil(radiusY);
/* 304 */     int ceilRadiusZ = (int)Math.ceil(radiusZ);
/*     */     
/* 306 */     double nextXn = 0.0D; int x;
/* 307 */     label35: for (x = 0; x <= ceilRadiusX; x++) {
/* 308 */       double xn = nextXn;
/* 309 */       nextXn = (x + 1) * invRadiusX;
/* 310 */       double nextYn = 0.0D; int y;
/* 311 */       label34: for (y = 0; y <= ceilRadiusY; y++) {
/* 312 */         double yn = nextYn;
/* 313 */         nextYn = (y + 1) * invRadiusY;
/* 314 */         double nextZn = 0.0D;
/* 315 */         for (int z = 0; z <= ceilRadiusZ; z++) {
/* 316 */           double zn = nextZn;
/* 317 */           nextZn = (z + 1) * invRadiusZ;
/*     */           
/* 319 */           double distanceSq = lengthSq(xn, yn, zn);
/* 320 */           if (distanceSq > 1.0D) {
/* 321 */             if (z == 0) {
/* 322 */               if (y == 0) {
/*     */                 break label35;
/*     */               }
/*     */               
/*     */               break label34;
/*     */             } 
/*     */             break;
/*     */           } 
/* 330 */           double px = lengthSq(nextXn, yn, zn);
/* 331 */           double py = lengthSq(xn, nextYn, zn);
/* 332 */           double pz = lengthSq(xn, yn, nextZn);
/*     */           
/* 334 */           if (px <= 0.15D && py <= 0.15D && pz <= 0.05D) { createEllipse(world, xpos, ypos, zpos, x, y, z, 2, 1); }
/* 335 */           else if (px <= 0.5D && py <= 0.5D && pz <= 0.43333D) { createEllipse(world, xpos, ypos, zpos, x, y, z, 8, 0); }
/* 336 */           else { createEllipse(world, xpos, ypos, zpos, x, y, z, 25, 2); }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 343 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void createEllipse(World world, int xpos, int ypos, int zpos, int x, int y, int z, int r, int g) {
/* 348 */     if (r == 0) { setBlock(world, xpos + x, ypos + y, zpos + z, g); }
/* 349 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos + y, zpos + z, g); }
/* 350 */      if (r == 0) { setBlock(world, xpos - x, ypos + y, zpos + z, g); }
/* 351 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos + y, zpos + z, g); }
/* 352 */      if (r == 0) { setBlock(world, xpos + x, ypos - y, zpos + z, g); }
/* 353 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos - y, zpos + z, g); }
/* 354 */      if (r == 0) { setBlock(world, xpos + x, ypos + y, zpos - z, g); }
/* 355 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos + y, zpos - z, g); }
/* 356 */      if (r == 0) { setBlock(world, xpos - x, ypos - y, zpos + z, g); }
/* 357 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos - y, zpos + z, g); }
/* 358 */      if (r == 0) { setBlock(world, xpos + x, ypos - y, zpos - z, g); }
/* 359 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos - y, zpos - z, g); }
/* 360 */      if (r == 0) { setBlock(world, xpos - x, ypos + y, zpos - z, g); }
/* 361 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos + y, zpos - z, g); }
/* 362 */      if (r == 0) { setBlock(world, xpos - x, ypos - y, zpos - z, g); }
/* 363 */     else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos - y, zpos - z, g); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canPlace(Block b) {
/* 369 */     return (b instanceof com.bioxx.tfc.Blocks.Terrain.BlockMM || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockIgIn || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockSed || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockIgEx);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBlock(World world, int posX, int posY, int posZ, int g) {
/* 374 */     int m = world.func_72805_g(posX, posY, posZ);
/* 375 */     Block b = world.func_147439_a(posX, posY, posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 382 */     if (canPlace(b) && posY >= this.oreMin && posY <= this.oreMax && 
/* 383 */       this.mPBlock != null && world.func_147465_d(posX, posY, posZ, this.mPBlock, this.minableBlockMeta, 2)) {
/*     */       
/* 385 */       TEOre te = (TEOre)world.func_147438_o(posX, posY, posZ);
/* 386 */       if (te != null) {
/*     */         
/* 388 */         te.baseBlockID = Block.func_149682_b(b);
/* 389 */         te.baseBlockMeta = m;
/* 390 */         te.extraData = (byte)g;
/* 391 */         this.numberOfBlocks++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int i, int j, int k) {
/* 401 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenMinableTFCNew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */