#include <bits/stdc++.h>
using namespace std;


class Base{
  
  public:
  
  virtual void print() {
      cout<<"This is base class printer"<<endl;
  }
  
  virtual int adder( int a, int b){
      cout<<"Base adder"<<endl;
      return (a+b);
  }
  
  virtual int substractor(int a, int b){
      cout<<"Base substractor"<<endl;
       return (a-b);
  }
    
};

class Derived : public Base{
    
    public:
    
    void print() override{
        cout<<"This is Derived class printer"<<endl;
    }
    
    
    int adder(int a, int b) override{
        cout<<"Derived adder"<<endl;
        return (a+b);
    }
    
    int substractor (int a, int b) override {
        cout<<"Derived substractor"<<endl;
        return (a-b);
    }
    
    int multiplier(int a, int b){
         return (a*b);
    }
    
};

void client(Base* obj){
    
    obj->print();
    cout<<"Addition: "<< obj->adder(10,20)<<endl;
    cout<<"Substraction: "<< obj->substractor(20,10)<<endl;
}

int main() {
    
   Base b1;
   Derived d1;
   
   client(&b1);
   
   return 0;

}
