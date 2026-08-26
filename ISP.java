interface Shape2D{
    
    public int area(int a, int b);
}

interface Shape3D{
    
    public int volume(int a, int b, int c);
}

class Square implements Shape2D{
    
   @Override
   public int area(int a, int b){
       return a*b;
   }
}

class Cube implements Shape2D, Shape3D{
    
   @Override
   public int area(int a, int b){
       return a*b;
   }
   
   @Override
   public int volume(int a, int b, int c){
       return a*b*c;
   }
}


public class Main{
    
    public static void main(String[] args){
        System.out.println("Hello World");
    }
    
}