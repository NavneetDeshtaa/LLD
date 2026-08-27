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

public class Main{
    
    public static void main(String[] args){
        
       PaymentMethod Payment = new UPIpayment();
       NotificationMethod notification = new WhatsAppNotification();
       ReceiptGenerator receipt = new SimpleReceipt();
       
       PaymentService service = new PaymentService(Payment, notification, receipt);
       service.paymentProcessing(10000);
        
    }
}

