interface PaymentMethod{
    public void payment(double amount);
}

interface NotificationMethod{
    public void sendNotification();
}

interface ReceiptGenerator{
    public void generate(double amount);
}

class UPIpayment implements PaymentMethod{
    public void payment(double amount){
       System.out.println("Payment of" + amount + " paid by the UPI");
    }
}

class CreditCardpayment implements PaymentMethod{
    public void payment(double amount){
       System.out.println("Payment of" + amount + " paid by the Credit Card");
    }
}

class EmailNotification implements NotificationMethod{
    public void sendNotification(){
        System.out.println("Notification send successfully via email.");
    }
}

class WhatsAppNotification implements NotificationMethod{
    public void sendNotification(){
        System.out.println("Notification send successfully via WhatsApp.");
    }
}

class SimpleReceipt implements ReceiptGenerator{
    public void generate(double amount){
        System.out.println("Amount paid :" + amount);
    }
}

class PaymentService{
    PaymentMethod payments;
    NotificationMethod notifications;
    ReceiptGenerator receipts;
    
    
    public PaymentService(PaymentMethod payments,NotificationMethod notifications,ReceiptGenerator receipts){
        this.payments = payments;
        this.notifications = notifications;
        this.receipts = receipts;
    }
    
    public void paymentProcessing(double amount){
        payments.payment(amount);
        notifications.sendNotification();
        receipts.generate(amount);
    }
}

class PaymentFactory {

    public static PaymentMethod create(String type) {

        if (type.equals("UPI")) {
            return new UPIpayment();
        }

        else if (type.equals("CreditCard")) {
            return new CreditCardpayment();
        }

        throw new IllegalArgumentException(
            "Invalid payment type"
        );
    }
}

class NotificationFactory {

    public static NotificationMethod create(String type) {

        if (type.equals("Email")) {
            return new EmailNotification();
        }

        else if (type.equals("WhatsApp")) {
            return new WhatsAppNotification();
        }

        throw new IllegalArgumentException(
            "Invalid notification type"
        );
    }
}

class ReceiptFactory {

    public static ReceiptGenerator create(String type) {

        if (type.equals("Simple")) {
            return new SimpleReceipt();
        }

        throw new IllegalArgumentException(
            "Invalid receipt type"
        );
    }
}

public class Main {

    public static void main(String[] args) {

        PaymentMethod payment =
            PaymentFactory.create("UPI");

        NotificationMethod notification =
            NotificationFactory.create("WhatsApp");

        ReceiptGenerator receipt =
            ReceiptFactory.create("Simple");

        PaymentService service =
            new PaymentService(
                payment,
                notification,
                receipt
            );

        service.paymentProcessing(5000);
    }
}

