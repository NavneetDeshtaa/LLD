class Base {

    public void print() {
        System.out.println("This is base class printer");
    }

    public int adder(int a, int b) {
        System.out.println("Base adder");
        return a + b;
    }

    public int substractor(int a, int b) {
        System.out.println("Base substractor");
        return a - b;
    }
}


class Derived extends Base {

    @Override
    public void print() {
        System.out.println("This is Derived class printer");
    }

    @Override
    public int adder(int a, int b) {
        System.out.println("Derived adder");
        return a + b;
    }

    @Override
    public int substractor(int a, int b) {
        System.out.println("Derived substractor");
        return a - b;
    }

    public int multiplier(int a, int b) {
        return a * b;
    }
}

public class Main {
    
    
    public static void client( Base obj){
        obj.print();
        
        System.out.println("Addition:" + obj.adder(10,20));
        System.out.println("Substraction:" + obj.substractor(200, 100));
        
    }

    public static void main(String[] args) {
        
        Base b1  = new Base();
        Derived d1 = new Derived();
        client(d1);

    }
}