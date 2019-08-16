public class Tree 
{
    public static  Node root;
    
    public Tree()
    {
        this.root = null;
    }
    
    public Tree(Tree tree1,Tree tree2)
    {
        root.left = tree1.root;
        root.right = tree2.root;
        root.frequency = tree1.root.frequency+tree2.root.frequency;
    }
    
    
}

