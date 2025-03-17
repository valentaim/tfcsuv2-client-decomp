/*    */ package com.bioxx.tfc.ASM.Transform;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.ClassTransformer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.objectweb.asm.tree.AbstractInsnNode;
/*    */ import org.objectweb.asm.tree.LabelNode;
/*    */ import org.objectweb.asm.tree.LineNumberNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TF_EntityPlayerMP
/*    */   extends ClassTransformer
/*    */ {
/*    */   public TF_EntityPlayerMP() {
/* 22 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
/* 23 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 59, ClassTransformer.InstrOpType.Remove));
/* 24 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 60, ClassTransformer.InstrOpType.Remove));
/* 25 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 61, ClassTransformer.InstrOpType.Remove));
/* 26 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 62, ClassTransformer.InstrOpType.Remove));
/* 27 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 63, ClassTransformer.InstrOpType.Remove));
/* 28 */     nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LineNumberNode(0, new LabelNode()), 64, ClassTransformer.InstrOpType.Remove));
/*    */     
/* 30 */     this.mcpMethodNodes.put("onUpdateEntity | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/* 31 */     this.obfMethodNodes.put("i | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */