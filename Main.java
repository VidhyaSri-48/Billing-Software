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
    static HashMap<Integer, Customer> custMap = new HashMap<>();


    static void add_customer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = sc.next();
        System.out.println("Enter customer mobile.no: ");
        String mobile = sc.next();
        
        Customer customer = new Customer(name, mobile);
        custMap.put(customer_id, customer);
    }
    static void display_customer(){
        System.out.println("Customer Details");
        for(Map.Entry<Integer, Customer> entry: custMap.entrySet()){
            int custID = entry.getKey();
            Customer cust = entry.getValue();
            System.out.println("Customer ID:" + custID);
            System.out.println("Customer Name:" + cust.name);
            System.out.println("Mobile.NO:" + cust.mobile);
            System.out.println("-------------------------------");
        }
    }

}


class Product{
    int productid;
    String productname;
    double rate;
    int quantity;

    Product(int productid, String productname, double rate, int quantity){
        this.productid = productid;
        this.productname = productname;
        this.rate = rate;
        this.quantity = quantity;
    }
    static HashMap<Integer, Product> prodMap = new HashMap<>();

    static void add_product(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productid = sc.nextInt();
        System.out.println("Enter product name: ");
        String productname = sc.next();
        System.out.println("Enter product rate: ");
        double rate = sc.nextDouble();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        if(quantity<=0){
            System.out.println("Invalid quantity! Enter correct quantity");
        }

        Product product = new Product(productid, productname, rate, quantity);
        prodMap.put(productid, product);    // productID, object
    }

    static void display_product(){
        System.out.println("Product Details");
        for(Map.Entry<Integer, Product> entry: prodMap.entrySet()){
            int prodID = entry.getKey();
            Product prod = entry.getValue();
            System.out.println("Product ID:" + prodID);
            System.out.println("Product Name:" + prod.productname);
            System.out.println("Rate:" + prod.rate);
            System.out.println("Quantity:" + prod.quantity);
            System.out.println("-------------------------------");
        }
    }  
}


class Sales{
    int sales_id = 0;
    Customer customer;
    Product product;
    double total_amt;
    double discount_amount;

    Sales(Customer customer, Product product, double total_amt, double discount_amount){
        this.sales_id +=1;
        this.customer = customer;
        this.product = product;
        this.total_amt = total_amt;
        this.discount_amount  = discount_amount; 
    }
    
    void make_sale(){

    }
    double Amount_calculation(){
        double total_amt = 0;
        for(Map.Entry<Integer, Product> entry: product.prodMap.entrySet()){
            Product prod = entry.getValue();
            if(prod.quantity==0){
                System.out.println("Stock unavailable");
            }
            else{
                double amt = prod.quantity * prod.rate;
                prod.quantity-=quantity;        
                total_amt += amt;
            }
            
        }

        double dicount_amount = discount_calculation(total_amt);
        if(discount_amount == 0.0){
            System.out.println("No discount, Buy more to avail discount");
        }
        else{
            total_amt = total_amt - discount_amount;
        }
    }

    double discount_calculation(double amount){
        if(amount>=1500){
            return (15/100)*amount;
        }
        else{
            return 0.0;
        }


    }
    void generate_Bill(){

        System.out.println("Invoice Generation");
        product.display_product();
        System.out.println("Total Amount: "+ total_amt);   
        
    }
}

public class Main {
    public static void display_options(){

        System.out.println("""
                1. Add Customer
                2. Add products
                3. Display products
                4. Display Customer
                5. Generate Bill 
                """);
    
    }

    public static void main(String[] args) {
        System.out.println("SRI'S MART");
        
        while(true){
            display_options();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your choice: ");
    
            int choice = sc.nextInt();
            if(choice==1){
                Customer.add_customer();
            }
            else if(choice==2){
                Product.add_product();
            }
            else if(choice==3){
                Product.display_product();
            }
            else if(choice==4){
                Customer.display_customer();
            }
            
    
        }
        
    }

}
