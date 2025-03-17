/*     */ package com.bioxx.tfc.ASM.Transform;
/*     */ 
/*     */ import com.bioxx.tfc.ASM.ClassTransformer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.objectweb.asm.tree.InsnNode;
/*     */ import org.objectweb.asm.tree.LdcInsnNode;
/*     */ import org.objectweb.asm.tree.VarInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TF_RenderBlock
/*     */   extends ClassTransformer
/*     */ {
/*     */   public TF_RenderBlock() {
/*  31 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
/*     */     
/*  33 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7189, 2, ClassTransformer.InstrOpType.InsertAfter));
/*  34 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7189, 6, ClassTransformer.InstrOpType.InsertAfter));
/*     */     
/*  36 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7190, 1, ClassTransformer.InstrOpType.InsertAfter));
/*  37 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7190, 5, ClassTransformer.InstrOpType.InsertAfter));
/*     */     
/*  39 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 10), 7267, 5, ClassTransformer.InstrOpType.Replace));
/*  40 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 14), 7267, 6, ClassTransformer.InstrOpType.Replace));
/*  41 */     nodes.add(new ClassTransformer.InstrSet(this, 7268, 3, 7274, 3));
/*  42 */     nodes.add(new ClassTransformer.InstrSet(this, 7268, 5, 7274, 5));
/*  43 */     nodes.add(new ClassTransformer.InstrSet(this, 7268, 7, 7274, 7));
/*  44 */     nodes.add(new ClassTransformer.InstrSet(this, 7269, 3, 7275, 3));
/*  45 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7270, 5, ClassTransformer.InstrOpType.Replace));
/*  46 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7270, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  48 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 12), 7273, 5, ClassTransformer.InstrOpType.Replace));
/*  49 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 16), 7273, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  51 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7276, 5, ClassTransformer.InstrOpType.Replace));
/*  52 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7276, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  54 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 10), 7280, 5, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  56 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 14), 7280, 6, ClassTransformer.InstrOpType.Replace));
/*  57 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7281, 5, ClassTransformer.InstrOpType.Replace));
/*  58 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7281, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  60 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 12), 7282, 5, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  62 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 16), 7282, 6, ClassTransformer.InstrOpType.Replace));
/*  63 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7283, 5, ClassTransformer.InstrOpType.Replace));
/*  64 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7283, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  66 */     this.mcpMethodNodes.put("renderFaceZNeg | (Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V", new ClassTransformer.Patch(this, nodes));
/*  67 */     this.obfMethodNodes.put("c | (Lahu;DDDLps;)V", new ClassTransformer.Patch(this, nodes));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     nodes = new ArrayList<>();
/*     */     
/*  74 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7519, 2, ClassTransformer.InstrOpType.InsertAfter));
/*  75 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7519, 6, ClassTransformer.InstrOpType.InsertAfter));
/*  76 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7520, 1, ClassTransformer.InstrOpType.InsertAfter));
/*  77 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7520, 5, ClassTransformer.InstrOpType.InsertAfter));
/*     */     
/*  79 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 30), 7597, 3, ClassTransformer.InstrOpType.Replace));
/*  80 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7597, 5, ClassTransformer.InstrOpType.Replace));
/*  81 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7597, 6, ClassTransformer.InstrOpType.Replace));
/*  82 */     nodes.add(new ClassTransformer.InstrSet(this, 7598, 3, 7604, 3));
/*  83 */     nodes.add(new ClassTransformer.InstrSet(this, 7598, 5, 7604, 5));
/*  84 */     nodes.add(new ClassTransformer.InstrSet(this, 7598, 7, 7604, 7));
/*  85 */     nodes.add(new ClassTransformer.InstrSet(this, 7599, 3, 7605, 3));
/*  86 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 34), 7600, 4, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  88 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 28), 7603, 3, ClassTransformer.InstrOpType.Replace));
/*  89 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7603, 5, ClassTransformer.InstrOpType.Replace));
/*  90 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7603, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  92 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 32), 7606, 4, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  94 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 30), 7610, 3, ClassTransformer.InstrOpType.Replace));
/*  95 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7610, 5, ClassTransformer.InstrOpType.Replace));
/*  96 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7610, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/*  98 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 34), 7611, 4, ClassTransformer.InstrOpType.Replace));
/*     */     
/* 100 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 28), 7612, 3, ClassTransformer.InstrOpType.Replace));
/* 101 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7612, 5, ClassTransformer.InstrOpType.Replace));
/* 102 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7612, 6, ClassTransformer.InstrOpType.Replace));
/*     */     
/* 104 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 32), 7613, 4, ClassTransformer.InstrOpType.Replace));
/*     */     
/* 106 */     this.mcpMethodNodes.put("renderFaceXPos | (Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V", new ClassTransformer.Patch(this, nodes));
/* 107 */     this.obfMethodNodes.put("f | (Lahu;DDDLps;)V", new ClassTransformer.Patch(this, nodes));
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_RenderBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */