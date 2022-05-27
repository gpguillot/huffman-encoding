package core;

public class Node 
{
   
   private int value; 
   private char character; 
   private Node left, right; 
  
   public Node(int value, char character)
   {
      this.value = value;
      this.character = character;
      this.right = null;
      this.left = null;
   }  
   
  @Override
  public String toString() 
  {
     return "Node [value=" + this.value + ", character=" + this.character + 
           ", " + "left=" + this.left + ", right=" + this.right + "]";
  }  

}
