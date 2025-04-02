package com.bioxx.tfc.ASM.Transform;

import com.bioxx.tfc.ASM.ClassTransformer;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;











public class TF_EntityRenderer
  extends ClassTransformer
{
  public TF_EntityRenderer() {
    List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
    InsnList list = new InsnList();
























    list.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/renderer/EntityRenderer", "random", "Ljava/util/Random;"));
    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
    list.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/renderer/EntityRenderer", "rendererUpdateCount", "I"));
    list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ClientOverrides", "doRainClient", "(Ljava/util/Random;I)V"));
    nodes.add(new ClassTransformer.InstrSet(this, list, 208, ClassTransformer.InstrOpType.Replace));
    this.mcpMethodNodes.put("updateRenderer | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));

    nodes = new ArrayList<>();
    list = new InsnList();

    list.add((AbstractInsnNode)new FieldInsnNode(180, "blt", "al", "Ljava/util/Random;"));
    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
    list.add((AbstractInsnNode)new FieldInsnNode(180, "blt", "w", "I"));
    list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ClientOverrides", "doRainClient", "(Ljava/util/Random;I)V"));
    nodes.add(new ClassTransformer.InstrSet(this, list, 208, ClassTransformer.InstrOpType.Replace));
    this.obfMethodNodes.put("d | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_EntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */