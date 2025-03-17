/*     */ package com.bioxx.tfc.Core;
/*     */ 
/*     */ import com.bioxx.tfc.api.Interfaces.ICustomCollision;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CollisionRayTraceStandard
/*     */ {
/*     */   public static MovingObjectPosition collisionRayTrace(ICustomCollision b, World world, int x, int y, int z, Vec3 player, Vec3 view) {
/*  19 */     player = player.func_72441_c(-x, -y, -z);
/*  20 */     view = view.func_72441_c(-x, -y, -z);
/*     */     
/*  22 */     List<Object[]> returns = new ArrayList();
/*     */ 
/*     */     
/*  25 */     returns = rayTraceSubBlocks(b, world, player, view, x, y, z, returns);
/*     */     
/*  27 */     if (!returns.isEmpty()) {
/*     */       
/*  29 */       Object[] min = null;
/*  30 */       double distMin = 0.0D;
/*  31 */       for (Object[] ret : returns) {
/*     */         
/*  33 */         double dist = ((Double)ret[2]).doubleValue();
/*  34 */         if (min == null || dist < distMin) {
/*     */           
/*  36 */           distMin = dist;
/*  37 */           min = ret;
/*     */         } 
/*     */       } 
/*  40 */       if (min != null) {
/*     */         
/*  42 */         AxisAlignedBB aabb = (AxisAlignedBB)((Object[])min[3])[0];
/*  43 */         ((Block)b).func_149676_a((float)aabb.field_72340_a, (float)aabb.field_72338_b, (float)aabb.field_72339_c, (float)aabb.field_72336_d, (float)aabb.field_72337_e, (float)aabb.field_72334_f);
/*  44 */         rayTraceBound(aabb, x, y, z, player, view);
/*  45 */         MovingObjectPosition mop = new MovingObjectPosition(x, y, z, ((Byte)min[1]).byteValue(), ((Vec3)min[0]).func_72441_c(x, y, z));
/*  46 */         mop.hitInfo = min[3];
/*  47 */         return mop;
/*     */       } 
/*     */     } 
/*  50 */     ((Block)b).func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object[] rayTraceBound(AxisAlignedBB bound, int i, int j, int k, Vec3 player, Vec3 view) {
/*  57 */     Vec3 minX = player.func_72429_b(view, bound.field_72340_a);
/*  58 */     Vec3 maxX = player.func_72429_b(view, bound.field_72336_d);
/*  59 */     Vec3 minY = player.func_72435_c(view, bound.field_72338_b);
/*  60 */     Vec3 maxY = player.func_72435_c(view, bound.field_72337_e);
/*  61 */     Vec3 minZ = player.func_72434_d(view, bound.field_72339_c);
/*  62 */     Vec3 maxZ = player.func_72434_d(view, bound.field_72334_f);
/*  63 */     if (!isVecInsideYZBounds(bound, minX))
/*     */     {
/*  65 */       minX = null;
/*     */     }
/*  67 */     if (!isVecInsideYZBounds(bound, maxX))
/*     */     {
/*  69 */       maxX = null;
/*     */     }
/*  71 */     if (!isVecInsideXZBounds(bound, minY))
/*     */     {
/*  73 */       minY = null;
/*     */     }
/*  75 */     if (!isVecInsideXZBounds(bound, maxY))
/*     */     {
/*  77 */       maxY = null;
/*     */     }
/*  79 */     if (!isVecInsideXYBounds(bound, minZ))
/*     */     {
/*  81 */       minZ = null;
/*     */     }
/*  83 */     if (!isVecInsideXYBounds(bound, maxZ))
/*     */     {
/*  85 */       maxZ = null;
/*     */     }
/*  87 */     Vec3 tracedBound = null;
/*  88 */     if (minX != null && (tracedBound == null || player.func_72438_d(minX) < player.func_72438_d(tracedBound)))
/*     */     {
/*  90 */       tracedBound = minX;
/*     */     }
/*  92 */     if (maxX != null && (tracedBound == null || player.func_72438_d(maxX) < player.func_72438_d(tracedBound)))
/*     */     {
/*  94 */       tracedBound = maxX;
/*     */     }
/*  96 */     if (minY != null && (tracedBound == null || player.func_72438_d(minY) < player.func_72438_d(tracedBound)))
/*     */     {
/*  98 */       tracedBound = minY;
/*     */     }
/* 100 */     if (maxY != null && (tracedBound == null || player.func_72438_d(maxY) < player.func_72438_d(tracedBound)))
/*     */     {
/* 102 */       tracedBound = maxY;
/*     */     }
/* 104 */     if (minZ != null && (tracedBound == null || player.func_72438_d(minZ) < player.func_72438_d(tracedBound)))
/*     */     {
/* 106 */       tracedBound = minZ;
/*     */     }
/* 108 */     if (maxZ != null && (tracedBound == null || player.func_72438_d(maxZ) < player.func_72438_d(tracedBound)))
/*     */     {
/* 110 */       tracedBound = maxZ;
/*     */     }
/* 112 */     if (tracedBound == null) return null; 
/* 113 */     byte side = -1;
/* 114 */     if (tracedBound == minX)
/*     */     {
/* 116 */       side = 4;
/*     */     }
/* 118 */     if (tracedBound == maxX)
/*     */     {
/* 120 */       side = 5;
/*     */     }
/* 122 */     if (tracedBound == minY)
/*     */     {
/* 124 */       side = 0;
/*     */     }
/* 126 */     if (tracedBound == maxY)
/*     */     {
/* 128 */       side = 1;
/*     */     }
/* 130 */     if (tracedBound == minZ)
/*     */     {
/* 132 */       side = 2;
/*     */     }
/* 134 */     if (tracedBound == maxZ)
/*     */     {
/* 136 */       side = 3;
/*     */     }
/* 138 */     return new Object[] { tracedBound, Byte.valueOf(side), Double.valueOf(player.func_72438_d(tracedBound)), bound };
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Object[]> rayTraceSubBlocks(ICustomCollision b, World world, Vec3 player, Vec3 view, int i, int j, int k, List<Object[]> returns) {
/* 143 */     List<Object[]> bblist = new ArrayList();
/* 144 */     b.addCollisionBoxesToList(world, i, j, k, bblist);
/* 145 */     for (Object[] o : bblist) {
/*     */       
/* 147 */       AxisAlignedBB aabb = (AxisAlignedBB)o[0];
/* 148 */       Object[] ret = rayTraceBound(aabb, i, j, k, player, view);
/*     */       
/* 150 */       if (ret != null)
/*     */       {
/* 152 */         returns.add(new Object[] { ret[0], ret[1], ret[2], o });
/*     */       }
/*     */     } 
/*     */     
/* 156 */     return returns;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isVecInsideYZBounds(AxisAlignedBB bound, Vec3 vec3) {
/* 161 */     if (vec3 == null)
/*     */     {
/* 163 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 167 */     return (vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isVecInsideXZBounds(AxisAlignedBB bound, Vec3 vec3) {
/* 173 */     if (vec3 == null)
/*     */     {
/* 175 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 179 */     return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isVecInsideXYBounds(AxisAlignedBB bound, Vec3 vec3) {
/* 185 */     if (vec3 == null)
/*     */     {
/* 187 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 191 */     return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\CollisionRayTraceStandard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */