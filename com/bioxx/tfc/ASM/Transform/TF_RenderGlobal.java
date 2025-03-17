/*    */ package com.bioxx.tfc.ASM.Transform;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.ClassTransformer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.objectweb.asm.tree.AbstractInsnNode;
/*    */ import org.objectweb.asm.tree.InsnList;
/*    */ import org.objectweb.asm.tree.LabelNode;
/*    */ import org.objectweb.asm.tree.LineNumberNode;
/*    */ import org.objectweb.asm.tree.MethodInsnNode;
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
/*    */ public class TF_RenderGlobal
/*    */   extends ClassTransformer
/*    */ {
/*    */   public TF_RenderGlobal() {
/* 26 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
/* 27 */     InsnList list = new InsnList();
/* 28 */     list.add((AbstractInsnNode)new LineNumberNode(445, new LabelNode()));
/* 29 */     list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ClientOverrides", "loadRenderers", "()V"));
/* 30 */     nodes.add(new ClassTransformer.InstrSet(this, list, 445, ClassTransformer.InstrOpType.InsertBefore));
/*    */     
/* 32 */     this.mcpMethodNodes.put("loadRenderers | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/* 33 */     this.obfMethodNodes.put("a | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_RenderGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */