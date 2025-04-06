import java.util.*;


class Customer{
    static int customer_id=0;
    String name;
    String mobile;
    

    Customer(String name, String mobile){
        customer_id+=1;
        this. name = name;
        this.mobile = mobile;
    }


    void add_customer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = sc.next();
        System.out.println("Enter customer mobile.no: ");
        String mobile = sc.next();
        HashMap<int, Customer> custMap = new HashMap<>();

        Customer customer = new Customer(name, mobile);
        custMap.put(customer_id, customer);

    }
    void display_customer(){
        System.out.println("Customer Details");
        for(Map.Entry<int, Customer> entry: custMap.entrySet()){
            int custID = entry.getKey();
            Customer cust = entry.getValue();
            System.out.println("Customer ID:" + custID);
            System.out.println("Customer Name:" + customer.name);
            System.out.println("Mobile.NO:" + customer.mobile);
            System.out.println("-------------------------------");
        }
    }




}
class Shop{

    System.out.println("SRI'S MART");
    

    while(true){
        display_options();
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        if(choice==1){
            add_customer();
        }
        else if(choice==2){
            add_product();
        }
        else if(choice==3){
            display_product();
        }
        else if(choice==4){
            display_customer();
        }
        else if(choice==5){
            generate_bill();
        }



    }

void display_options(){

    System.out.println("""
            1. Add Customer
            2. Add products
            3. Display products
            4. Display Customer
            5. Generate Bill 
            """);


}

}
public class Main {
    public static void main(String[] args) {
        
    }

}
