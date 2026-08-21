#include <bits/stdc++.h>
using namespace std;

class Product{
   public:
    string name;
    double price;
    
    Product(string name, double price){
        this->name = name;
        this->price = price;
    }
};

class Cart{
   vector<Product> products;  
   
   public:
   
   void addProduct(Product p){
       products.push_back(p);
   }
   
   void getProducts(){
       for(Product p : products){
           cout<<"Name:"<<p.name <<" , "<<"Price:"<<p.price<<endl;
       }
   }
   
   double totalPrice(){
       double total = 0;
       for(Product p : products){
           total += p.price;
       }
       return total;
   }
};

class SavetoDB{
   public:
   
   virtual void save() = 0;
   
};

class savetoPostgreSQL :public SavetoDB {
    
    public:
    
    void save() override{
        cout<<"Data saved to PostgreSQL"<<endl;
    }
};

class savetoMongoDB : public SavetoDB {
    public:
    
    void save() override{
        cout<<"Data saved to MongoDB"<<endl;
    }
};


int main() {

  Product p1("Laptop", 500000);
  Product p2("Mouse", 3000);
  
  Cart C1;
  C1.addProduct(p1);
  C1.addProduct(p2);
  
  C1.getProducts();
  
  cout<<"Total Price of cart :" << C1.totalPrice()<<endl;
  
  SavetoDB* s1 = new savetoPostgreSQL();
  SavetoDB* s2 = new savetoMongoDB();
  
  s1->save();
  s2->save();

  returnn 0;

}
