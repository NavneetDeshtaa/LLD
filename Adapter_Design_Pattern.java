interface PaymentProcessor{
    void pay(double amount);
}

class UPIPayment implements PaymentProcessor{
    
    @Override
    public void pay(double amount){
        System.out.println( amount + " Paid via UPI");
    }
}

class StripePayment{
    
    public double ProcessingFees = 10.0;
    
    public void makePayment(double amount){
        double PayableAmount = amount + ProcessingFees;
        System.out.println( PayableAmount + " Paid via Stripe");
    }
}

class StripeAdapter implements PaymentProcessor {

    private StripePayment stripe;

    public StripeAdapter(StripePayment stripe) {
        this.stripe = stripe;
    }

    @Override
    public void pay(double amount) {
        stripe.makePayment(amount);
    }
}

public class Main{
    
    public static void main(String[] args){
        
        PaymentProcessor Payment1 = new UPIPayment();
        Payment1.pay(1000);
        
        PaymentProcessor Payment2 = new StripeAdapter(new StripePayment());
        Payment2.pay(1000);
    }
}