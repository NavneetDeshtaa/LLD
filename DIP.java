
interface database{
    public void save(String user);
}

class MongoDB implements database{
    
    @Override
    public void save (String user){
        System.out.println( user + " saved to MongoDB");
    } 
}

class sqlDB implements database{

    @Override
    public void save (String user) {
        System.out.println( user +" saved to  SQl");
    }
}

class Client{
    public database db;
    
    public Client(database db){
        this.db = db;
    }

    public void storeUser(String user) {
        db.save(user);
    }
}

public class Main{

    public static void main(String[] args){

    MongoDB db1 = new MongoDB();
    sqlDB db2 = new sqlDB();

    Client c1 = new Client(db1);
    c1.storeUser("Navneet");

    Client c2 = new Client(db2);
    c2.storeUser("Rahul");

    }
}