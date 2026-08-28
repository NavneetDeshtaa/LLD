class Example {

    private static volatile Example instance = null;

    private Example() {
        System.out.println("Constructor Called");
    }

    public static Example getInstance() {

        if (instance == null) {

            synchronized (Example.class) {

                if (instance == null) {
                    instance = new Example();
                }
            }
        }

        return instance;
    }
}

public class Main{
    public static void main(String[] args){
        Example e1 = Example.getInstance();
        Example e2 = Example.getInstance();
        
        System.out.println(e1 == e2);
    }
}