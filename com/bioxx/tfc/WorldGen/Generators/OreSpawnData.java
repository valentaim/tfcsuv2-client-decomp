/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Enums.EnumOreGen;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ 
/*     */ public class OreSpawnData {
/*     */   public EnumOreGen type;
/*     */   public int size;
/*     */   public int meta;
/*     */   public int rarity;
/*  19 */   public int min = 5; public int max = 128; public int rnd; public int SphereXSize; public int SphereYSize;
/*     */   public int SphereZSize;
/*     */   public int VeinWidth;
/*     */   public int VeinBaseHeight;
/*     */   
/*     */   public OreSpawnData(String t, String s, String blockName, int m, int r, String[] baseRocks) {
/*  25 */     this.block = Block.func_149684_b(blockName);
/*     */     
/*  27 */     if (this.block == null) {
/*     */       
/*  29 */       TerraFirmaCraft.LOG.error(TFC_Core.translate("error.error") + " " + TFC_Core.translate("error.OreCFG") + " " + blockName);
/*  30 */       throw new NullPointerException(TFC_Core.translate("error.OreCFG") + " " + blockName);
/*     */     } 
/*     */     
/*  33 */     this.meta = m;
/*  34 */     this.rarity = r;
/*  35 */     if (EnumOreGen.Area.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Area; }
/*  36 */     else if (EnumOreGen.Vein.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Vein; }
/*  37 */     else if (EnumOreGen.Lens.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Lens; }
/*  38 */     else { this.type = EnumOreGen.Vein; }
/*     */     
/*  40 */     if ("small".equals(s)) { this.size = 0; }
/*  41 */     else if ("medium".equals(s)) { this.size = 1; }
/*  42 */     else { this.size = 2; }
/*     */     
/*  44 */     this.base = new HashMap<>();
/*  45 */     for (String name : baseRocks)
/*  46 */       getOre(name); 
/*     */   }
/*     */   public int VeinDownFactor; public int AreaNumber;
/*     */   public int AreaMaxDistance;
/*     */   
/*     */   public OreSpawnData(String t, String s, String blockName, int m, int r, String[] baseRocks, int minHeight, int maxHeight, int rnd, int sxs, int sys, int szs, int vw, int vbh, int vdf, int an, int amd, int cs) {
/*  52 */     this(t, s, blockName, m, r, baseRocks);
/*  53 */     this.min = minHeight;
/*  54 */     this.max = maxHeight;
/*  55 */     this.rnd = rnd;
/*  56 */     this.SphereXSize = sxs;
/*  57 */     this.SphereYSize = sys;
/*  58 */     this.SphereZSize = szs;
/*  59 */     this.VeinWidth = vw;
/*  60 */     this.VeinBaseHeight = vbh;
/*  61 */     this.VeinDownFactor = vdf;
/*  62 */     this.AreaNumber = an;
/*  63 */     this.AreaMaxDistance = amd;
/*  64 */     this.CellSize = cs;
/*     */   }
/*     */   public int CellSize; public Block block; public Map<Block, List<Integer>> base;
/*     */   private void getOre(String name) {
/*     */     int i;
/*  69 */     for (i = 0; i < Global.STONE_IGIN.length; i++) {
/*  70 */       if (name.equalsIgnoreCase(Global.STONE_IGIN[i])) {
/*     */         
/*  72 */         List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgIn) ? this.base.get(TFCBlocks.stoneIgIn) : new ArrayList<>();
/*  73 */         metadata.add(Integer.valueOf(i));
/*  74 */         this.base.put(TFCBlocks.stoneIgIn, metadata);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  79 */     for (i = 0; i < Global.STONE_IGEX.length; i++) {
/*     */       
/*  81 */       if (name.equalsIgnoreCase(Global.STONE_IGEX[i])) {
/*     */         
/*  83 */         List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgEx) ? this.base.get(TFCBlocks.stoneIgEx) : new ArrayList<>();
/*  84 */         metadata.add(Integer.valueOf(i));
/*  85 */         this.base.put(TFCBlocks.stoneIgEx, metadata);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  90 */     for (i = 0; i < Global.STONE_SED.length; i++) {
/*     */       
/*  92 */       if (name.equalsIgnoreCase(Global.STONE_SED[i])) {
/*     */         
/*  94 */         List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneSed) ? this.base.get(TFCBlocks.stoneSed) : new ArrayList<>();
/*  95 */         metadata.add(Integer.valueOf(i));
/*  96 */         this.base.put(TFCBlocks.stoneSed, metadata);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 101 */     for (i = 0; i < Global.STONE_MM.length; i++) {
/*     */       
/* 103 */       if (name.equalsIgnoreCase(Global.STONE_MM[i])) {
/*     */         
/* 105 */         List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneMM) ? this.base.get(TFCBlocks.stoneMM) : new ArrayList<>();
/* 106 */         metadata.add(Integer.valueOf(i));
/* 107 */         this.base.put(TFCBlocks.stoneMM, metadata);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 112 */     if ("igneous intrusive".equalsIgnoreCase(name)) {
/*     */       
/* 114 */       List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgIn) ? this.base.get(TFCBlocks.stoneIgIn) : new ArrayList<>();
/* 115 */       metadata.add(Integer.valueOf(-1));
/* 116 */       this.base.put(TFCBlocks.stoneIgIn, metadata);
/*     */       return;
/*     */     } 
/* 119 */     if ("igneous extrusive".equalsIgnoreCase(name)) {
/*     */       
/* 121 */       List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgEx) ? this.base.get(TFCBlocks.stoneIgEx) : new ArrayList<>();
/* 122 */       metadata.add(Integer.valueOf(-1));
/* 123 */       this.base.put(TFCBlocks.stoneIgEx, metadata);
/*     */       return;
/*     */     } 
/* 126 */     if ("sedimentary".equalsIgnoreCase(name)) {
/*     */       
/* 128 */       List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneSed) ? this.base.get(TFCBlocks.stoneSed) : new ArrayList<>();
/* 129 */       metadata.add(Integer.valueOf(-1));
/* 130 */       this.base.put(TFCBlocks.stoneSed, metadata);
/*     */       return;
/*     */     } 
/* 133 */     if ("metamorphic".equalsIgnoreCase(name)) {
/*     */       
/* 135 */       List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneMM) ? this.base.get(TFCBlocks.stoneMM) : new ArrayList<>();
/* 136 */       metadata.add(Integer.valueOf(-1));
/* 137 */       this.base.put(TFCBlocks.stoneMM, metadata);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\OreSpawnData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */