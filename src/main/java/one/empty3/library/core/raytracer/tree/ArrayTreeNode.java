package one.empty3.library.core.raytracer.tree;
import one.empty3.library.*;
public class ArrayTreeNodeType extends TreeNodeType {
    private StructureMatrix list;
// todo des que tu as un separateur tu dois recommener avant quil soit pas la
    public ArrayTreeNodeType(Class t) {
super();
list = new StructureMatrix(1, t) ;
} 
    public ArrayTreeNode() {
         this(Double.class);
} 
    public boolean add(Object o) {
list.add(1, o);
} 
} 
