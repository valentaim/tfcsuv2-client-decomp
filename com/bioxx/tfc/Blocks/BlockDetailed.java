/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.CollisionRayTraceDetailed;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.TileEntities.TEDetailed;
/*     */ import com.bioxx.tfc.TileEntities.TEWoodConstruct;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockDetailed extends BlockPartial {
/*     */   public static int lockX;
/*     */   public static int lockY;
/*     */   public static int lockZ;
/*     */   public int xSelected;
/*     */   public int ySelected;
/*     */   public int zSelected;
/*     */   public int side;
/*     */   
/*     */   public BlockDetailed() {
/*  44 */     super(Material.field_151576_e);
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
/* 298 */     this.xSelected = -10; this.ySelected = -10; this.zSelected = -10; this.side = -1;
/*     */   } public int func_149645_b() { return TFCBlocks.detailedRenderId; } @SideOnly(Side.CLIENT) public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) { return true; } @SideOnly(Side.CLIENT) public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) { return true; } public boolean func_149655_b(IBlockAccess bAccess, int i, int j, int k) { return true; }
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {}
/*     */   public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) { TEDetailed te = (TEDetailed)bAccess.func_147438_o(x, y, z); return te.func_145838_q().func_149691_a(side, te.metaID); }
/*     */   public TileEntity func_149915_a(World var1, int var2) { return (TileEntity)new TEDetailed(); }
/* 303 */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) { TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
/*     */     
/* 305 */     player = player.func_72441_c(-x, -y, -z);
/* 306 */     view = view.func_72441_c(-x, -y, -z);
/* 307 */     if (te == null) {
/* 308 */       return null;
/*     */     }
/* 310 */     List<Object[]> returns = new ArrayList();
/*     */ 
/*     */     
/* 313 */     returns = CollisionRayTraceDetailed.rayTraceSubBlocks(this, player, view, x, y, z, returns, te.data, te);
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
/* 325 */     if (!returns.isEmpty()) {
/* 326 */       Object[] min = null;
/* 327 */       double distMin = 0.0D;
/* 328 */       for (Object[] ret : returns) {
/*     */         
/* 330 */         double dist = ((Double)ret[2]).doubleValue();
/* 331 */         if (min == null || dist < distMin) {
/*     */           
/* 333 */           distMin = dist;
/* 334 */           min = ret;
/*     */         } 
/*     */       } 
/* 337 */       if (min != null) {
/*     */         
/* 339 */         this.side = ((Byte)min[1]).byteValue();
/* 340 */         this.xSelected = ((Integer)min[3]).intValue();
/* 341 */         this.ySelected = ((Integer)min[4]).intValue();
/* 342 */         this.zSelected = ((Integer)min[5]).intValue();
/*     */         
/* 344 */         int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected;
/*     */         
/* 346 */         if (index >= 0 && te.data.get(index)) {
/*     */           
/* 348 */           int d = TEWoodConstruct.plankDetailLevel;
/*     */           
/* 350 */           float div = 1.0F / d;
/*     */           
/* 352 */           float minX = x + this.xSelected * div;
/* 353 */           float maxX = minX + div;
/* 354 */           float minY = y + this.ySelected * div;
/* 355 */           float maxY = minY + div;
/* 356 */           float minZ = z + this.zSelected * div;
/* 357 */           float maxZ = minZ + div;
/*     */           
/* 359 */           func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
/* 360 */           rayTraceBound(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ), x, y, z, player, view);
/*     */         } 
/* 362 */         setBlockBoundsBasedOnSelection((IBlockAccess)world, x, y, z);
/*     */         
/* 364 */         lockX = x; lockY = y; lockZ = z;
/*     */         
/* 366 */         return new MovingObjectPosition(x, y, z, ((Byte)min[1])
/* 367 */             .byteValue(), ((Vec3)min[0])
/* 368 */             .func_72441_c(x, y, z));
/*     */       } 
/*     */     } 
/* 371 */     this.xSelected = -10;
/* 372 */     this.ySelected = -10;
/* 373 */     this.zSelected = -10;
/* 374 */     this.side = -1;
/* 375 */     setBlockBoundsBasedOnSelection((IBlockAccess)world, x, y, z);
/*     */     
/* 377 */     return null; } public boolean func_149662_c() { return false; }
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) { return true; }
/*     */   public boolean func_149686_d() { return false; }
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) { return true; }
/* 382 */   public void setBlockBoundsBasedOnSelection(IBlockAccess access, int x, int y, int z) { if (this.xSelected == -10)
/*     */     
/* 384 */     { func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F); }
/*     */     
/*     */     else
/*     */     
/* 388 */     { TEDetailed te = (TEDetailed)access.func_147438_o(x, y, z);
/* 389 */       int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected;
/*     */       
/* 391 */       if (index >= 0 && te.data.get(index))
/*     */       
/* 393 */       { int d = 8;
/*     */ 
/*     */ 
/*     */         
/* 397 */         float div = 1.0F / d;
/*     */         
/* 399 */         float minX = this.xSelected * div;
/* 400 */         float maxX = minX + div;
/* 401 */         float minY = this.ySelected * div;
/* 402 */         float maxY = minY + div;
/* 403 */         float minZ = this.zSelected * div;
/* 404 */         float maxZ = minZ + div;
/*     */         
/* 406 */         AxisAlignedBB bound = AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ);
/* 407 */         func_149676_a((float)bound.field_72340_a, (float)bound.field_72338_b, (float)bound.field_72339_c, (float)bound.field_72336_d, (float)bound.field_72337_e, (float)bound.field_72334_f); }  }  }
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) { boolean hasHammer = false; PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer); for (int i = 0; i < 9; i++) { if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) hasHammer = true;  }  if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel && hasHammer && world.field_72995_K && pi.lockMatches(x, y, z)) { TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z); lockX = x; lockY = y; lockZ = z; NBTTagCompound nbt = new NBTTagCompound(); nbt.func_74774_a("packetType", (byte)1); nbt.func_74768_a("xSelected", this.xSelected); nbt.func_74768_a("ySelected", this.ySelected); nbt.func_74768_a("zSelected", this.zSelected); te.createDataNBT(nbt); te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt)); }  return false; }
/*     */   public boolean onBlockActivatedServer(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) { int mode = 0; PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player); if (pi != null) mode = pi.chiselMode;  TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z); int hasChisel = -1; int hasHammer = -1; for (int i = 0; i < 9; i++) { if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) hasHammer = i;  if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel)
/*     */         hasChisel = i;  }  if (mode == 1) { int index = -10; if (this.xSelected < 4 && this.ySelected < 4 && this.zSelected < 4)
/*     */         for (int subX = 0; subX < 4; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected < 4 && this.zSelected < 4)
/*     */         for (int subX = 4; subX < 8; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected < 4 && this.zSelected > 3)
/*     */         for (int subX = 4; subX < 8; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected < 4 && this.zSelected > 3)
/*     */         for (int subX = 0; subX < 4; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected > 3 && this.zSelected < 4)
/*     */         for (int subX = 0; subX < 4; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected > 3 && this.zSelected < 4)
/*     */         for (int subX = 4; subX < 8; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected > 3 && this.zSelected > 3)
/*     */         for (int subX = 4; subX < 8; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected > 3 && this.zSelected > 3)
/*     */         for (int subX = 0; subX < 4; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   return true; }  if (mode == 3 && this.xSelected != -10) { int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected; if (index >= 0)
/* 419 */         deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer);  return true; }  return false; } public Object[] rayTraceBound(AxisAlignedBB bound, int i, int j, int k, Vec3 player, Vec3 view) { Vec3 minX = player.func_72429_b(view, bound.field_72340_a);
/* 420 */     Vec3 maxX = player.func_72429_b(view, bound.field_72336_d);
/* 421 */     Vec3 minY = player.func_72435_c(view, bound.field_72338_b);
/* 422 */     Vec3 maxY = player.func_72435_c(view, bound.field_72337_e);
/* 423 */     Vec3 minZ = player.func_72434_d(view, bound.field_72339_c);
/* 424 */     Vec3 maxZ = player.func_72434_d(view, bound.field_72334_f);
/* 425 */     if (!isVecInsideYZBounds(bound, minX))
/* 426 */       minX = null; 
/* 427 */     if (!isVecInsideYZBounds(bound, maxX))
/* 428 */       maxX = null; 
/* 429 */     if (!isVecInsideXZBounds(bound, minY))
/* 430 */       minY = null; 
/* 431 */     if (!isVecInsideXZBounds(bound, maxY))
/* 432 */       maxY = null; 
/* 433 */     if (!isVecInsideXYBounds(bound, minZ))
/* 434 */       minZ = null; 
/* 435 */     if (!isVecInsideXYBounds(bound, maxZ)) {
/* 436 */       maxZ = null;
/*     */     }
/* 438 */     Vec3 tracedBound = null;
/* 439 */     if (minX != null && (tracedBound == null || player.func_72438_d(minX) < player.func_72438_d(tracedBound)))
/* 440 */       tracedBound = minX; 
/* 441 */     if (maxX != null && (tracedBound == null || player.func_72438_d(maxX) < player.func_72438_d(tracedBound)))
/* 442 */       tracedBound = maxX; 
/* 443 */     if (minY != null && (tracedBound == null || player.func_72438_d(minY) < player.func_72438_d(tracedBound)))
/* 444 */       tracedBound = minY; 
/* 445 */     if (maxY != null && (tracedBound == null || player.func_72438_d(maxY) < player.func_72438_d(tracedBound)))
/* 446 */       tracedBound = maxY; 
/* 447 */     if (minZ != null && (tracedBound == null || player.func_72438_d(minZ) < player.func_72438_d(tracedBound)))
/* 448 */       tracedBound = minZ; 
/* 449 */     if (maxZ != null && (tracedBound == null || player.func_72438_d(maxZ) < player.func_72438_d(tracedBound)))
/* 450 */       tracedBound = maxZ; 
/* 451 */     if (tracedBound == null) {
/* 452 */       return null;
/*     */     }
/* 454 */     byte side = -1;
/* 455 */     if (tracedBound == minX)
/* 456 */       side = 4; 
/* 457 */     if (tracedBound == maxX)
/* 458 */       side = 5; 
/* 459 */     if (tracedBound == minY)
/* 460 */       side = 0; 
/* 461 */     if (tracedBound == maxY)
/* 462 */       side = 1; 
/* 463 */     if (tracedBound == minZ)
/* 464 */       side = 2; 
/* 465 */     if (tracedBound == maxZ) {
/* 466 */       side = 3;
/*     */     }
/* 468 */     return new Object[] { tracedBound, Byte.valueOf(side), Double.valueOf(player.func_72438_d(tracedBound)) }; }
/*     */   public void deleteBox(World world, int x, int y, int z, EntityPlayer player, TEDetailed te, int index, int hasChisel, int hasHammer) { te.data.clear(index); te.clearQuad(this.xSelected, this.ySelected, this.zSelected); if (te.isBlockEmpty())
/*     */       world.func_147468_f(x, y, z);  if (player.field_71071_by.field_70462_a[hasChisel] != null)
/*     */       player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);  NBTTagCompound nbt = new NBTTagCompound(); nbt.func_74774_a("packetType", (byte)0); nbt.func_74768_a("index", index); te.createDataNBT(nbt); te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt)); }
/* 472 */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List list, Entity entity) { TEDetailed te = (TEDetailed)world.func_147438_o(i, j, k); float div = 0.125F; for (int subX = 0; subX < 8; subX++) { for (int subZ = 0; subZ < 8; subZ++) { for (int subY = 0; subY < 8; subY++) { if (te.data.get((subX * 8 + subZ) * 8 + subY)) { float minX = subX * div; float maxX = minX + div; float minY = subY * div; float maxY = minY + div; float minZ = subZ * div; float maxZ = minZ + div; func_149676_a(minX, minY, minZ, maxX, maxY, maxZ); super.func_149743_a(world, i, j, k, aabb, list, entity); }  }  }  }  setBlockBoundsBasedOnSelection((IBlockAccess)world, i, j, k); } private boolean isVecInsideYZBounds(AxisAlignedBB bound, Vec3 vec3) { if (vec3 == null) {
/* 473 */       return false;
/*     */     }
/* 475 */     return (vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f); }
/*     */ 
/*     */   
/*     */   private boolean isVecInsideXZBounds(AxisAlignedBB bound, Vec3 vec3) {
/* 479 */     if (vec3 == null) {
/* 480 */       return false;
/*     */     }
/* 482 */     return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
/*     */   }
/*     */   
/*     */   private boolean isVecInsideXYBounds(AxisAlignedBB bound, Vec3 vec3) {
/* 486 */     if (vec3 == null) {
/* 487 */       return false;
/*     */     }
/* 489 */     return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
/* 495 */     TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
/* 496 */     if (te.typeID >= 0)
/* 497 */       return Blocks.field_150480_ab.getFlammability(Block.func_149729_e(te.typeID)); 
/* 498 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
/* 504 */     TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
/* 505 */     if (te.typeID >= 0)
/* 506 */       return Blocks.field_150480_ab.getEncouragement(Block.func_149729_e(te.typeID)); 
/* 507 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 513 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */