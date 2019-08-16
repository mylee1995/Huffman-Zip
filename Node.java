public class Node implements Comparable<Node>
{
    public char value;
    public int frequency;
    public Node left;
    public Node right;
    public Node parent;
        
    public Node()
    {
        parent = null;
    }
    
    public  Node(char value,int frequency, Node left, Node right, Node parent)
    {
        this.value = value;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.parent = right;
    }
    
    public int compareTo(Node node)
    {
        int result = this.frequency-node.getFrequency();
        if (result == 0)
        {
            result = this.value - node.getValue();
        }
        return result;
    }
    
    public int getFrequency()
    {
        return frequency;
    }
    
    public char getValue()
    {
        return value;
    }

    public Node(char value)
    {
        this.value = value;
    }
    
    public Node(int frequency)
    {
        this.frequency = frequency;
    }
}
