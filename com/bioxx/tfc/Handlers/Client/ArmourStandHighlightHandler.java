/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEStand;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArmourStandHighlightHandler
/*     */ {
/*     */   private AxisAlignedBB boxToRender;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
/*  22 */     World world = evt.player.field_70170_p;
/*  23 */     if (evt.currentItem == null && TFCBlocks.isArmourStand(world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d)) && 
/*  24 */       Math.sqrt(evt.target.field_72307_f.func_72445_d(evt.player.field_70165_t, evt.player.field_70163_u, evt.player.field_70161_v)) < 2.5D) {
/*     */ 
/*     */       
/*  27 */       EntityPlayer player = evt.player;
/*  28 */       Vec3 vectorTerm = player.func_70040_Z();
/*  29 */       vectorTerm.field_72450_a *= 0.5D;
/*  30 */       vectorTerm.field_72448_b *= 0.5D;
/*  31 */       vectorTerm.field_72449_c *= 0.5D;
/*  32 */       vectorTerm = vectorTerm.func_72441_c(evt.target.field_72307_f.field_72450_a, evt.target.field_72307_f.field_72448_b, evt.target.field_72307_f.field_72449_c);
/*  33 */       TEStand stand = (TEStand)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
/*  34 */       boolean isTop = stand.isTop;
/*     */       
/*  36 */       if (isTop)
/*  37 */         stand = (TEStand)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d); 
/*  38 */       boolean ns = (stand.yaw % 360.0F == 0.0F || stand.yaw % 360.0F == 180.0F);
/*  39 */       double var8 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * evt.partialTicks;
/*  40 */       double var10 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * evt.partialTicks;
/*  41 */       double var12 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * evt.partialTicks;
/*     */ 
/*     */       
/*  44 */       AxisAlignedBB head = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.3D, evt.target.field_72312_c + 1.35D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.3D, evt.target.field_72311_b + 0.5D + 0.3D, evt.target.field_72312_c + 1.95D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.3D);
/*     */ 
/*     */ 
/*     */       
/*  48 */       AxisAlignedBB body = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.55D, evt.target.field_72312_c + 0.75D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.17D, evt.target.field_72311_b + 0.5D + 0.55D, evt.target.field_72312_c + 1.5D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.17D);
/*     */ 
/*     */       
/*  51 */       if (!ns) {
/*  52 */         body = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.17D, evt.target.field_72312_c + 0.75D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.55D, evt.target.field_72311_b + 0.5D + 0.17D, evt.target.field_72312_c + 1.5D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.55D);
/*     */       }
/*     */ 
/*     */       
/*  56 */       AxisAlignedBB legs = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.315D, evt.target.field_72312_c + 0.19999999999999996D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.1395D, evt.target.field_72311_b + 0.5D + 0.315D, evt.target.field_72312_c + 0.9D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.1395D);
/*     */ 
/*     */       
/*  59 */       if (!ns) {
/*  60 */         legs = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.1395D, evt.target.field_72312_c + 0.19999999999999996D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.315D, evt.target.field_72311_b + 0.5D + 0.1395D, evt.target.field_72312_c + 0.9D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.315D);
/*     */       }
/*     */ 
/*     */       
/*  64 */       AxisAlignedBB feet = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.35D, evt.target.field_72312_c + 0.15D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.17D, evt.target.field_72311_b + 0.5D + 0.35D, evt.target.field_72312_c + 0.40000000000000013D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.17D);
/*     */ 
/*     */       
/*  67 */       if (!ns) {
/*  68 */         feet = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.17D, evt.target.field_72312_c + 0.15D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.35D, evt.target.field_72311_b + 0.5D + 0.17D, evt.target.field_72312_c + 0.40000000000000013D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.35D);
/*     */       }
/*     */ 
/*     */       
/*  72 */       Vec3 unit = vectorTerm.func_72432_b();
/*  73 */       unit = player.func_70040_Z();
/*  74 */       if (isVecInsideBox(head, player, unit, var8, var10, var12) && stand.items[4] != null) {
/*     */         
/*  76 */         this.boxToRender = head;
/*     */       
/*     */       }
/*  79 */       else if (isVecInsideBox(body, player, unit, var8, var10, var12) && stand.items[3] != null) {
/*     */         
/*  81 */         this.boxToRender = body;
/*     */       
/*     */       }
/*  84 */       else if (isVecInsideBox(legs, player, unit, var8, var10, var12) && stand.items[2] != null) {
/*     */         
/*  86 */         this.boxToRender = legs;
/*     */       
/*     */       }
/*  89 */       else if (isVecInsideBox(feet, player, unit, var8, var10, var12) && stand.items[1] != null) {
/*     */         
/*  91 */         this.boxToRender = feet;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       if (this.boxToRender != null) {
/*     */ 
/*     */         
/* 102 */         GL11.glEnable(3042);
/* 103 */         GL11.glBlendFunc(1, 1);
/* 104 */         GL11.glDisable(3553);
/* 105 */         GL11.glDepthMask(false);
/*     */ 
/*     */         
/* 108 */         GL11.glBlendFunc(770, 771);
/* 109 */         GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.4F);
/* 110 */         GL11.glLineWidth(4.0F);
/* 111 */         GL11.glDepthMask(true);
/*     */         
/* 113 */         drawOutlinedBoundingBox(this.boxToRender.func_72314_b(0.019999999552965164D, 0.019999999552965164D, 0.019999999552965164D).func_72325_c(-var8, -var10, -var12));
/* 114 */         GL11.glDepthMask(true);
/* 115 */         GL11.glEnable(3553);
/* 116 */         GL11.glDisable(3042);
/*     */       } 
/* 118 */       this.boxToRender = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVecInsideBox(AxisAlignedBB aabb, EntityPlayer player, Vec3 unit, double xOffset, double yOffset, double zOffset) {
/* 124 */     unit = player.func_70040_Z();
/* 125 */     aabb.field_72338_b += 0.1D;
/* 126 */     aabb.field_72337_e += 0.1D;
/* 127 */     Vec3 playerVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.eyeHeight, player.field_70161_v);
/*     */ 
/*     */     
/* 130 */     Vec3 distBlockxyz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c)).func_72432_b();
/* 131 */     Vec3 distBlockXYZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)).func_72432_b();
/*     */     
/* 133 */     Vec3 distBlockxyZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f)).func_72432_b();
/* 134 */     Vec3 distBlockXYz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c)).func_72432_b();
/*     */     
/* 136 */     Vec3 distBlockxYZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f)).func_72432_b();
/* 137 */     Vec3 distBlockXyz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c)).func_72432_b();
/*     */     
/* 139 */     Vec3 distBlockxYz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c)).func_72432_b();
/* 140 */     Vec3 distBlockXyZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f)).func_72432_b();
/*     */     
/* 142 */     double currentLongestProj = 0.0D;
/* 143 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxyz));
/* 144 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXYZ));
/* 145 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXyz));
/* 146 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxYZ));
/* 147 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxYz));
/* 148 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXyZ));
/* 149 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxyZ));
/* 150 */     currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXYz));
/*     */     
/* 152 */     unit.field_72450_a *= currentLongestProj * 0.99D; unit.field_72448_b *= currentLongestProj * 0.99D; unit.field_72449_c *= currentLongestProj * 0.99D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     boolean insideBoxX = ((unit.field_72450_a >= distBlockxyz.field_72450_a && unit.field_72450_a <= distBlockXYZ.field_72450_a) || (unit.field_72450_a >= distBlockxyZ.field_72450_a && unit.field_72450_a <= distBlockXYz.field_72450_a) || (unit.field_72450_a >= distBlockxYZ.field_72450_a && unit.field_72450_a <= distBlockXyz.field_72450_a) || (unit.field_72450_a >= distBlockxYz.field_72450_a && unit.field_72450_a <= distBlockXyZ.field_72450_a));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     boolean insideBoxY = ((unit.field_72448_b >= distBlockxyz.field_72448_b && unit.field_72448_b <= distBlockXYZ.field_72448_b) || (unit.field_72448_b >= distBlockxyZ.field_72448_b && unit.field_72448_b <= distBlockXYz.field_72448_b) || (unit.field_72448_b >= distBlockXyz.field_72448_b && unit.field_72448_b <= distBlockxYZ.field_72448_b) || (unit.field_72448_b >= distBlockXyZ.field_72448_b && unit.field_72448_b <= distBlockxYz.field_72448_b));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     boolean insideBoxZ = ((unit.field_72449_c >= distBlockxyz.field_72449_c && unit.field_72449_c <= distBlockXYZ.field_72449_c) || (unit.field_72449_c >= distBlockXYz.field_72449_c && unit.field_72449_c <= distBlockxyZ.field_72449_c) || (unit.field_72449_c >= distBlockXyz.field_72449_c && unit.field_72449_c <= distBlockxYZ.field_72449_c) || (unit.field_72449_c >= distBlockxYz.field_72449_c && unit.field_72449_c <= distBlockXyZ.field_72449_c));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     aabb.field_72338_b -= 0.1D;
/* 207 */     aabb.field_72337_e -= 0.1D;
/* 208 */     return (insideBoxX && insideBoxY && insideBoxZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLine(Vec3 origin, Vec3 vector) {
/* 213 */     Tessellator var2 = Tessellator.field_78398_a;
/* 214 */     var2.func_78371_b(3);
/*     */ 
/*     */     
/* 217 */     var2.func_78377_a(origin.field_72450_a, origin.field_72448_b, origin.field_72449_c);
/* 218 */     var2.func_78377_a(vector.field_72450_a, vector.field_72448_b, vector.field_72449_c);
/* 219 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
/* 224 */     Tessellator var2 = Tessellator.field_78398_a;
/* 225 */     var2.func_78371_b(3);
/* 226 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 227 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 228 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 229 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 230 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 231 */     var2.func_78381_a();
/* 232 */     var2.func_78371_b(3);
/* 233 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 234 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 235 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 236 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 237 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 238 */     var2.func_78381_a();
/* 239 */     var2.func_78371_b(1);
/* 240 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 241 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 242 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 243 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 244 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 245 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 246 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 247 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 248 */     var2.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\ArmourStandHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */