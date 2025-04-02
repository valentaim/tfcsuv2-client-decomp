package com.bioxx.tfc.ASM.Transform;

import com.bioxx.tfc.ASM.ClassTransformer;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;







public class TF_EntityPlayer
  extends ClassTransformer
{
  public TF_EntityPlayer() {
    List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
    InsnList list = new InsnList();




    LabelNode ln = new LabelNode();

    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
    list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ServerOverrides", "canPlayerMove", "(Lnet/minecraft/entity/EntityLivingBase;)Z"));
    list.add((AbstractInsnNode)new ClassTransformer.JumpNode(this, 154, ln));


    list.add((AbstractInsnNode)new InsnNode(11));
    list.add((AbstractInsnNode)new VarInsnNode(56, 1));


    list.add((AbstractInsnNode)new InsnNode(11));
    list.add((AbstractInsnNode)new VarInsnNode(56, 2));


    list.add((AbstractInsnNode)ln);

    nodes.add(new ClassTransformer.InstrSet(this, list, 0, ClassTransformer.InstrOpType.InsertBefore));

    this.mcpMethodNodes.put("moveEntityWithHeading | (FF)V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
    this.obfMethodNodes.put("e | (FF)V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));




    list = new InsnList();
    nodes = new ArrayList<>();
    nodes.add(new ClassTransformer.InstrSet(this, list, 33, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 34, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 35, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 36, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 37, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 38, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 39, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 40, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 41, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 42, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 43, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 44, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 45, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 46, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 47, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 48, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 49, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 50, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 51, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 52, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 53, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 54, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 55, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 56, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 57, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 58, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 59, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 60, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 61, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 62, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 63, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 64, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 65, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 66, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 67, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 68, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 69, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 70, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 71, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 72, ClassTransformer.InstrOpType.Remove));
    nodes.add(new ClassTransformer.InstrSet(this, list, 73, ClassTransformer.InstrOpType.Remove));

    this.mcpMethodNodes.put("getItemIcon | (Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
    this.obfMethodNodes.put("b | (Ladd;I)Lrf;", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */