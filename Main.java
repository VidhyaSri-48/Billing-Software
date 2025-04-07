import java.util.*;

class Customer{
    static int customer_id_counter = 0;
    int customer_id;
    String name;
    String mobile;
    

    Customer(String name, String mobile){
        this.customer_id = ++customer_id_counter;
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
        custMap.put(customer.getCustomerId(), customer);
    }
    static void display_customer(){
        System.out.println("Customer Details");
        for(Map.Entry<Integer, Customer> entry: custMap.entrySet()){
            
            Customer cust = entry.getValue();
            System.out.println("Customer ID:" + cust.getCustomerId());
            System.out.println("Customer Name:" + cust.getName());
            System.out.println("Mobile.NO:" + cust.getMobile());
            System.out.println("------------------------------------");
        }
    }

    public static Customer getCustomerById(int id){
        return custMap.get(id);
    }
    public int getCustomerId(){
        return customer_id;
    }
    public String getName(){
        return name;
    }
    public String getMobile(){
        return mobile;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMobile(String name){
        this.mobile = mobile;
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
        for(Map.Entry<Integer, Product> entry: Product.prodMap.entrySet()){
            int prodID = entry.getKey();
            Product prod = entry.getValue();
            System.out.println("Product ID:" + prodID);
            System.out.println("Product Name:" + prod.productname);
            System.out.println("Rate:" + prod.rate);
            System.out.println("Quantity:" + prod.quantity);
            System.out.println("------------------------------------");
        }
    } 
    
    public int getProductId() {
        return productid;
    }

    public String getProductName() {
        return productname;
    }

    public double getRate() {
        return rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
class Sales{
    static int salescounter = 0;
    int sales_id;
    Customer customer;
    Product product;
    double total_amount;
    double discount_amount;

    Sales(Customer customer, Product product, double total_amount, double discount_amount){
        salescounter +=1;
        this.sales_id = salescounter;
        this.customer = customer;
        this.product = product;
        this.total_amount = total_amount;
        this.discount_amount  = discount_amount; 
    }
    static HashMap<Product, Integer>cartMap = new HashMap<>();

    static void make_sale(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int customer_id = sc.nextInt();

        Customer customer = Customer.getCustomerById(customer_id);
        if(customer==null){
            System.out.println("Invalid Customer ID! Add customer to make sale");
        }

        while(true){
            System.out.println("Enter product id");
            int productid = sc.nextInt();
            System.out.println("Enter quantity");
            int quantity = sc.nextInt();
            
            Product selectedProduct = Product.prodMap.get(productid);
            if(quantity==0 ||selectedProduct==null || selectedProduct.getQuantity() < quantity){
                System.out.println("Invalid quantity");
            }
            else{
                cartMap.put(selectedProduct, quantity);
                selectedProduct.quantity -= quantity;
            }

            System.out.print("Wish to buy more products(yes/no)?");
            String ans = sc.next();
            if(ans.equalsIgnoreCase("no")) break;
            
        }

        Sales sale = new Sales(customer,null, 0, 0 );
        sale.Amount_calculation();
        sale.generate_Bill();
    }
    void Amount_calculation(){
        for(Map.Entry<Product, Integer> entry: cartMap.entrySet()){
            Product prod = entry.getKey();
            total_amount += prod.getQuantity() * prod.getRate();
             
        }

        discount_amount = discount_calculation(total_amount);
        
        if(discount_amount>0){
            total_amount -= discount_amount;
        }
        else if(discount_amount == 0.0){
            System.out.println("No discount, Buy more to avail discount");
        }
    }

    double discount_calculation(double amount){
        if(amount >= 1500){
            return (15.0/100.0) * amount;
        }
        else{
            return 0.0;
        }
    }
    void generate_Bill(){

        System.out.println("Invoice Generation");
        System.out.println("Customer Name: " + customer.name);
        System.out.println("Customer Mobile: " + customer.mobile);
        System.out.println("------------------------------------");
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + p.productname + ", Qty: " + quantity + ", Price: " + p.rate);
        }
        System.out.println("------------------------------------");

        System.out.println("Total Amount: "+ total_amount);   
        System.out.println("Discount Amount: "+ discount_amount);   
        System.out.println("You have saved Rs. "+ discount_amount);   
        
        
    }
}
public class Main {
    public static void display_options(){

        System.out.println("""
                1. Add Customer
                2. Add products
                3. Display products
                4. Display Customer
                5. Make sale
                6. Exit
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
            else if(choice==5){
                Sales.make_sale();
            } 
            else if(choice==6){
                System.out.println("Thank you! Visit Again");
                break;
            } 
            else{
                System.out.println("Invalid choice!");
            }
        }        
    }
}
