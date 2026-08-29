interface ICoffee {
    double getPrice();
    void make();
}

class BasicCoffee implements ICoffee {

    @Override
    public double getPrice() {
        return 100;
    }

    @Override
    public void make() {
        System.out.println("Making Basic Coffee");
    }
}

abstract class CoffeeDecorator implements ICoffee {

    protected ICoffee coffee;

    public CoffeeDecorator(ICoffee coffee) {
        this.coffee = coffee;
    }
}

class CaramelDecorator extends CoffeeDecorator {

    public CaramelDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 30;
    }

    @Override
    public void make() {

        coffee.make();
        System.out.println("Adding Caramel");
    }
}

class ExtraShotDecorator extends CoffeeDecorator {

    public ExtraShotDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 50;
    }

    @Override
    public void make() {

        coffee.make();

        System.out.println("Adding Extra Shot");
    }
}

public class Main {

    public static void main(String[] args) {

        ICoffee coffee = new BasicCoffee();
        System.out.println("Basic Price: " + coffee.getPrice());

        coffee = new CaramelDecorator(coffee);
        System.out.println("After Caramel: " + coffee.getPrice());

        coffee = new ExtraShotDecorator(coffee);
        System.out.println("After Extra Shot: " + coffee.getPrice());

        coffee.make();
        System.out.println("Final Price: " + coffee.getPrice());
        
        
        // Passing Recursively.
        ICoffee coffee2 = new BasicCoffee();
        coffee2 = new CaramelDecorator( new ExtraShotDecorator(coffee2));
        System.out.println(coffee2.getPrice());
    }
}