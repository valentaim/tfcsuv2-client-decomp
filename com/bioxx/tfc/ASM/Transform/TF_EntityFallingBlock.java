/*    */ package com.bioxx.tfc.ASM.Transform;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.ClassTransformer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.objectweb.asm.tree.AbstractInsnNode;
/*    */ import org.objectweb.asm.tree.IntInsnNode;
/*    */ import org.objectweb.asm.tree.LdcInsnNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TF_EntityFallingBlock
/*    */   extends ClassTransformer
/*    */ {
/*    */   public TF_EntityFallingBlock() {
/* 27 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
/* 28 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new IntInsnNode(17, 2000), 13, ClassTransformer.InstrOpType.Replace));
/* 29 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Float.valueOf(100.0F)), 18, ClassTransformer.InstrOpType.Replace));
/* 30 */     ClassTransformer.Patch patch = new ClassTransformer.Patch(this, nodes);
/* 31 */     this.mcpMethodNodes.put("<init> | (Lnet/minecraft/world/World;DDDLnet/minecraft/block/Block;I)V", patch);
/* 32 */     this.obfMethodNodes.put("<init> | (Lafn;DDDILahu;I)V", patch);
/*    */     
/* 34 */     nodes = new ArrayList<>();
/* 35 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new IntInsnNode(17, 2000), 13, ClassTransformer.InstrOpType.Replace));
/* 36 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Float.valueOf(100.0F)), 18, ClassTransformer.InstrOpType.Replace));
/* 37 */     patch = new ClassTransformer.Patch(this, nodes);
/* 38 */     this.mcpMethodNodes.put("<init> | (Lnet/minecraft/world/World;)V", patch);
/* 39 */     this.obfMethodNodes.put("<init> | (Lafn;)V", patch);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */