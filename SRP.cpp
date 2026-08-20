#include <bits/stdc++.h>
using namespace std;

class Product{
    public:
    string name;
    int price;
    Product(string name, int price){
        this->name = name;
        this->price = price;
    }
};

class Cart {
    vector<Product> products;
    
    public:
    
    void addProducts(Product p){
        products.push_back(p);
    }
    
   void getProducts() {
    for (Product p : products) {
        cout << "Product: " << p.name << ", Price: " << p.price << endl;
    }
   }
    double CalculatePrice(){
        int total = 0;
        for( Product p : products){
             total += p.price;
        }
        return total;
    }
};


int main() {

   Cart C1;
   Product p1("laptop", 1200000);
   Product p2("Mouse", 15000);
   
   C1.addProducts(p1);
   C1.addProducts(p2);
   
   cout<<"Price : " << C1.CalculatePrice()<<endl;

}
