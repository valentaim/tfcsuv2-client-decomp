package com.bioxx.tfc.ASM.Transform;

import com.bioxx.tfc.ASM.ClassTransformer;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

















public class TF_RenderBlock
  extends ClassTransformer
{
  public TF_RenderBlock() {
    List<ClassTransformer.InstrSet> nodes = new ArrayList<>();

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7189, 2, ClassTransformer.InstrOpType.InsertAfter));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7189, 6, ClassTransformer.InstrOpType.InsertAfter));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7190, 1, ClassTransformer.InstrOpType.InsertAfter));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7190, 5, ClassTransformer.InstrOpType.InsertAfter));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 10), 7267, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 14), 7267, 6, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, 7268, 3, 7274, 3));
    nodes.add(new ClassTransformer.InstrSet(this, 7268, 5, 7274, 5));
    nodes.add(new ClassTransformer.InstrSet(this, 7268, 7, 7274, 7));
    nodes.add(new ClassTransformer.InstrSet(this, 7269, 3, 7275, 3));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7270, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7270, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 12), 7273, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 16), 7273, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7276, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7276, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 10), 7280, 5, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 14), 7280, 6, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7281, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7281, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 12), 7282, 5, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 16), 7282, 6, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7283, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7283, 6, ClassTransformer.InstrOpType.Replace));

    this.mcpMethodNodes.put("renderFaceZNeg | (Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V", new ClassTransformer.Patch(this, nodes));
    this.obfMethodNodes.put("c | (Lahu;DDDLps;)V", new ClassTransformer.Patch(this, nodes));




    nodes = new ArrayList<>();

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7519, 2, ClassTransformer.InstrOpType.InsertAfter));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7519, 6, ClassTransformer.InstrOpType.InsertAfter));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new LdcInsnNode(Double.valueOf(16.0D)), 7520, 1, ClassTransformer.InstrOpType.InsertAfter));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new InsnNode(103), 7520, 5, ClassTransformer.InstrOpType.InsertAfter));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 30), 7597, 3, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7597, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7597, 6, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, 7598, 3, 7604, 3));
    nodes.add(new ClassTransformer.InstrSet(this, 7598, 5, 7604, 5));
    nodes.add(new ClassTransformer.InstrSet(this, 7598, 7, 7604, 7));
    nodes.add(new ClassTransformer.InstrSet(this, 7599, 3, 7605, 3));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 34), 7600, 4, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 28), 7603, 3, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7603, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7603, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 32), 7606, 4, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 30), 7610, 3, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 18), 7610, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 22), 7610, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 34), 7611, 4, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 28), 7612, 3, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 20), 7612, 5, ClassTransformer.InstrOpType.Replace));
    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 24), 7612, 6, ClassTransformer.InstrOpType.Replace));

    nodes.add(new ClassTransformer.InstrSet(this, (AbstractInsnNode)new VarInsnNode(24, 32), 7613, 4, ClassTransformer.InstrOpType.Replace));

    this.mcpMethodNodes.put("renderFaceXPos | (Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V", new ClassTransformer.Patch(this, nodes));
    this.obfMethodNodes.put("f | (Lahu;DDDLps;)V", new ClassTransformer.Patch(this, nodes));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_RenderBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */