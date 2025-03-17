/*    */ package com.bioxx.tfc.ASM.Transform;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.ClassTransformer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.objectweb.asm.tree.AbstractInsnNode;
/*    */ import org.objectweb.asm.tree.InsnList;
/*    */ import org.objectweb.asm.tree.InsnNode;
/*    */ import org.objectweb.asm.tree.LabelNode;
/*    */ import org.objectweb.asm.tree.MethodInsnNode;
/*    */ import org.objectweb.asm.tree.VarInsnNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TF_EntityPlayer
/*    */   extends ClassTransformer
/*    */ {
/*    */   public TF_EntityPlayer() {
/* 23 */     List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
/* 24 */     InsnList list = new InsnList();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     LabelNode ln = new LabelNode();
/*    */     
/* 31 */     list.add((AbstractInsnNode)new VarInsnNode(25, 0));
/* 32 */     list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ServerOverrides", "canPlayerMove", "(Lnet/minecraft/entity/EntityLivingBase;)Z"));
/* 33 */     list.add((AbstractInsnNode)new ClassTransformer.JumpNode(this, 154, ln));
/*    */ 
/*    */     
/* 36 */     list.add((AbstractInsnNode)new InsnNode(11));
/* 37 */     list.add((AbstractInsnNode)new VarInsnNode(56, 1));
/*    */ 
/*    */     
/* 40 */     list.add((AbstractInsnNode)new InsnNode(11));
/* 41 */     list.add((AbstractInsnNode)new VarInsnNode(56, 2));
/*    */ 
/*    */     
/* 44 */     list.add((AbstractInsnNode)ln);
/*    */     
/* 46 */     nodes.add(new ClassTransformer.InstrSet(this, list, 0, ClassTransformer.InstrOpType.InsertBefore));
/*    */     
/* 48 */     this.mcpMethodNodes.put("moveEntityWithHeading | (FF)V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/* 49 */     this.obfMethodNodes.put("e | (FF)V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     list = new InsnList();
/* 55 */     nodes = new ArrayList<>();
/* 56 */     nodes.add(new ClassTransformer.InstrSet(this, list, 33, ClassTransformer.InstrOpType.Remove));
/* 57 */     nodes.add(new ClassTransformer.InstrSet(this, list, 34, ClassTransformer.InstrOpType.Remove));
/* 58 */     nodes.add(new ClassTransformer.InstrSet(this, list, 35, ClassTransformer.InstrOpType.Remove));
/* 59 */     nodes.add(new ClassTransformer.InstrSet(this, list, 36, ClassTransformer.InstrOpType.Remove));
/* 60 */     nodes.add(new ClassTransformer.InstrSet(this, list, 37, ClassTransformer.InstrOpType.Remove));
/* 61 */     nodes.add(new ClassTransformer.InstrSet(this, list, 38, ClassTransformer.InstrOpType.Remove));
/* 62 */     nodes.add(new ClassTransformer.InstrSet(this, list, 39, ClassTransformer.InstrOpType.Remove));
/* 63 */     nodes.add(new ClassTransformer.InstrSet(this, list, 40, ClassTransformer.InstrOpType.Remove));
/* 64 */     nodes.add(new ClassTransformer.InstrSet(this, list, 41, ClassTransformer.InstrOpType.Remove));
/* 65 */     nodes.add(new ClassTransformer.InstrSet(this, list, 42, ClassTransformer.InstrOpType.Remove));
/* 66 */     nodes.add(new ClassTransformer.InstrSet(this, list, 43, ClassTransformer.InstrOpType.Remove));
/* 67 */     nodes.add(new ClassTransformer.InstrSet(this, list, 44, ClassTransformer.InstrOpType.Remove));
/* 68 */     nodes.add(new ClassTransformer.InstrSet(this, list, 45, ClassTransformer.InstrOpType.Remove));
/* 69 */     nodes.add(new ClassTransformer.InstrSet(this, list, 46, ClassTransformer.InstrOpType.Remove));
/* 70 */     nodes.add(new ClassTransformer.InstrSet(this, list, 47, ClassTransformer.InstrOpType.Remove));
/* 71 */     nodes.add(new ClassTransformer.InstrSet(this, list, 48, ClassTransformer.InstrOpType.Remove));
/* 72 */     nodes.add(new ClassTransformer.InstrSet(this, list, 49, ClassTransformer.InstrOpType.Remove));
/* 73 */     nodes.add(new ClassTransformer.InstrSet(this, list, 50, ClassTransformer.InstrOpType.Remove));
/* 74 */     nodes.add(new ClassTransformer.InstrSet(this, list, 51, ClassTransformer.InstrOpType.Remove));
/* 75 */     nodes.add(new ClassTransformer.InstrSet(this, list, 52, ClassTransformer.InstrOpType.Remove));
/* 76 */     nodes.add(new ClassTransformer.InstrSet(this, list, 53, ClassTransformer.InstrOpType.Remove));
/* 77 */     nodes.add(new ClassTransformer.InstrSet(this, list, 54, ClassTransformer.InstrOpType.Remove));
/* 78 */     nodes.add(new ClassTransformer.InstrSet(this, list, 55, ClassTransformer.InstrOpType.Remove));
/* 79 */     nodes.add(new ClassTransformer.InstrSet(this, list, 56, ClassTransformer.InstrOpType.Remove));
/* 80 */     nodes.add(new ClassTransformer.InstrSet(this, list, 57, ClassTransformer.InstrOpType.Remove));
/* 81 */     nodes.add(new ClassTransformer.InstrSet(this, list, 58, ClassTransformer.InstrOpType.Remove));
/* 82 */     nodes.add(new ClassTransformer.InstrSet(this, list, 59, ClassTransformer.InstrOpType.Remove));
/* 83 */     nodes.add(new ClassTransformer.InstrSet(this, list, 60, ClassTransformer.InstrOpType.Remove));
/* 84 */     nodes.add(new ClassTransformer.InstrSet(this, list, 61, ClassTransformer.InstrOpType.Remove));
/* 85 */     nodes.add(new ClassTransformer.InstrSet(this, list, 62, ClassTransformer.InstrOpType.Remove));
/* 86 */     nodes.add(new ClassTransformer.InstrSet(this, list, 63, ClassTransformer.InstrOpType.Remove));
/* 87 */     nodes.add(new ClassTransformer.InstrSet(this, list, 64, ClassTransformer.InstrOpType.Remove));
/* 88 */     nodes.add(new ClassTransformer.InstrSet(this, list, 65, ClassTransformer.InstrOpType.Remove));
/* 89 */     nodes.add(new ClassTransformer.InstrSet(this, list, 66, ClassTransformer.InstrOpType.Remove));
/* 90 */     nodes.add(new ClassTransformer.InstrSet(this, list, 67, ClassTransformer.InstrOpType.Remove));
/* 91 */     nodes.add(new ClassTransformer.InstrSet(this, list, 68, ClassTransformer.InstrOpType.Remove));
/* 92 */     nodes.add(new ClassTransformer.InstrSet(this, list, 69, ClassTransformer.InstrOpType.Remove));
/* 93 */     nodes.add(new ClassTransformer.InstrSet(this, list, 70, ClassTransformer.InstrOpType.Remove));
/* 94 */     nodes.add(new ClassTransformer.InstrSet(this, list, 71, ClassTransformer.InstrOpType.Remove));
/* 95 */     nodes.add(new ClassTransformer.InstrSet(this, list, 72, ClassTransformer.InstrOpType.Remove));
/* 96 */     nodes.add(new ClassTransformer.InstrSet(this, list, 73, ClassTransformer.InstrOpType.Remove));
/*    */     
/* 98 */     this.mcpMethodNodes.put("getItemIcon | (Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/* 99 */     this.obfMethodNodes.put("b | (Ladd;I)Lrf;", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */