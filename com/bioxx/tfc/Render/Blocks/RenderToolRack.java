/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEToolRack;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderToolRack
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks) {
/*  17 */     renderblocks.field_147840_d = block.func_149691_a(0, metadata);
/*     */     
/*  19 */     renderblocks.func_147782_a(0.30000001192092896D, 0.3D, 0.949999988079071D, 1.7000000476837158D, 0.44999998807907104D, 1.100000023841858D);
/*  20 */     renderInvBlock(block, metadata, renderblocks);
/*     */     
/*  22 */     renderblocks.func_147782_a(0.30000001192092896D, 0.9D, 0.949999988079071D, 1.7000000476837158D, 1.0499999523162842D, 1.100000023841858D);
/*  23 */     renderInvBlock(block, metadata, renderblocks);
/*     */     
/*  25 */     renderblocks.func_147771_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
/*  31 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*  32 */     TEToolRack te = (TEToolRack)blockAccess.func_147438_o(i, j, k);
/*  33 */     int dir = blockAccess.func_72805_g(i, j, k);
/*  34 */     boolean breaking = (renderblocks.field_147840_d != null);
/*  35 */     if (te != null) {
/*     */       
/*  37 */       if (!breaking) {
/*  38 */         renderblocks.field_147840_d = block.func_149691_a(0, te.woodType);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  45 */       if (dir == 0) {
/*     */         
/*  47 */         renderRackDir0(block, i, j, k, renderblocks, 0.7F);
/*  48 */         renderRackDir0(block, i, j, k, renderblocks, 0.3F);
/*     */       }
/*  50 */       else if (dir == 1) {
/*     */         
/*  52 */         renderRackDir1(block, i, j, k, renderblocks, 0.7F);
/*  53 */         renderRackDir1(block, i, j, k, renderblocks, 0.3F);
/*     */       }
/*  55 */       else if (dir == 2) {
/*     */         
/*  57 */         renderRackDir2(block, i, j, k, renderblocks, 0.7F);
/*  58 */         renderRackDir2(block, i, j, k, renderblocks, 0.3F);
/*     */       }
/*  60 */       else if (dir == 3) {
/*     */         
/*  62 */         renderRackDir3(block, i, j, k, renderblocks, 0.7F);
/*  63 */         renderRackDir3(block, i, j, k, renderblocks, 0.3F);
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     if (!breaking) {
/*  68 */       renderblocks.func_147771_a();
/*     */     }
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/*  82 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderRackDir0(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
/*  87 */     renderblocks.func_147782_a(0.0D, yOffset, 0.949999988079071D, 1.0D, (yOffset + 0.05F), 1.0D);
/*  88 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/*  90 */     renderblocks.func_147782_a(0.20000000298023224D, yOffset, 0.8999999761581421D, 0.2199999988079071D, (yOffset + 0.05F), 0.949999988079071D);
/*  91 */     renderblocks.func_147784_q(block, i, j, k);
/*  92 */     renderblocks.func_147782_a(0.30000001192092896D, yOffset, 0.8999999761581421D, 0.3199999928474426D, (yOffset + 0.05F), 0.949999988079071D);
/*  93 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/*  95 */     renderblocks.func_147782_a(0.6800000071525574D, yOffset, 0.8999999761581421D, 0.699999988079071D, (yOffset + 0.05F), 0.949999988079071D);
/*  96 */     renderblocks.func_147784_q(block, i, j, k);
/*  97 */     renderblocks.func_147782_a(0.7799999713897705D, yOffset, 0.8999999761581421D, 0.800000011920929D, (yOffset + 0.05F), 0.949999988079071D);
/*  98 */     renderblocks.func_147784_q(block, i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderRackDir1(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
/* 103 */     renderblocks.func_147782_a(0.0D, yOffset, 0.0D, 0.05000000074505806D, (yOffset + 0.05F), 1.0D);
/* 104 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 106 */     renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.20000000298023224D, 0.10000000149011612D, (yOffset + 0.05F), 0.2199999988079071D);
/* 107 */     renderblocks.func_147784_q(block, i, j, k);
/* 108 */     renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.30000001192092896D, 0.10000000149011612D, (yOffset + 0.05F), 0.3199999928474426D);
/* 109 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 111 */     renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.6800000071525574D, 0.10000000149011612D, (yOffset + 0.05F), 0.699999988079071D);
/* 112 */     renderblocks.func_147784_q(block, i, j, k);
/* 113 */     renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.7799999713897705D, 0.10000000149011612D, (yOffset + 0.05F), 0.800000011920929D);
/* 114 */     renderblocks.func_147784_q(block, i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderRackDir2(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
/* 119 */     renderblocks.func_147782_a(0.0D, yOffset, 0.0D, 1.0D, (yOffset + 0.05F), 0.05000000074505806D);
/* 120 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 122 */     renderblocks.func_147782_a(0.20000000298023224D, yOffset, 0.05000000074505806D, 0.2199999988079071D, (yOffset + 0.05F), 0.10000000149011612D);
/* 123 */     renderblocks.func_147784_q(block, i, j, k);
/* 124 */     renderblocks.func_147782_a(0.30000001192092896D, yOffset, 0.05000000074505806D, 0.3199999928474426D, (yOffset + 0.05F), 0.10000000149011612D);
/* 125 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 127 */     renderblocks.func_147782_a(0.6800000071525574D, yOffset, 0.05000000074505806D, 0.699999988079071D, (yOffset + 0.05F), 0.10000000149011612D);
/* 128 */     renderblocks.func_147784_q(block, i, j, k);
/* 129 */     renderblocks.func_147782_a(0.7799999713897705D, yOffset, 0.05000000074505806D, 0.800000011920929D, (yOffset + 0.05F), 0.10000000149011612D);
/* 130 */     renderblocks.func_147784_q(block, i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderRackDir3(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
/* 135 */     renderblocks.func_147782_a(0.949999988079071D, yOffset, 0.0D, 1.0D, (yOffset + 0.05F), 1.0D);
/* 136 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 138 */     renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.20000000298023224D, 0.949999988079071D, (yOffset + 0.05F), 0.2199999988079071D);
/* 139 */     renderblocks.func_147784_q(block, i, j, k);
/* 140 */     renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.30000001192092896D, 0.949999988079071D, (yOffset + 0.05F), 0.3199999928474426D);
/* 141 */     renderblocks.func_147784_q(block, i, j, k);
/*     */     
/* 143 */     renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.6800000071525574D, 0.949999988079071D, (yOffset + 0.05F), 0.699999988079071D);
/* 144 */     renderblocks.func_147784_q(block, i, j, k);
/* 145 */     renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.7799999713897705D, 0.949999988079071D, (yOffset + 0.05F), 0.800000011920929D);
/* 146 */     renderblocks.func_147784_q(block, i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
/* 151 */     Tessellator var14 = Tessellator.field_78398_a;
/* 152 */     var14.func_78382_b();
/* 153 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 154 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 155 */     var14.func_78381_a();
/* 156 */     var14.func_78382_b();
/* 157 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 158 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 159 */     var14.func_78381_a();
/* 160 */     var14.func_78382_b();
/* 161 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 162 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 163 */     var14.func_78381_a();
/* 164 */     var14.func_78382_b();
/* 165 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 166 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 167 */     var14.func_78381_a();
/* 168 */     var14.func_78382_b();
/* 169 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 170 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 171 */     var14.func_78381_a();
/* 172 */     var14.func_78382_b();
/* 173 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 174 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 175 */     var14.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */