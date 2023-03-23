import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
    Assigment 1 - sdcCOSC603Assign1 - Class Name BinaryDataFileGenerator
    This class will contain the code for the Step 1: Create a data file for the assingment.
    Author: Rodrigo Farinango - SDC - ID#: 000482153
 */
public class BinaryDataFileGenerator {
    public static void main(String[] args) {
        String filename = "src/customers.dat";

        try{
            // Create Order Objects to add to a list for customers
            Order order1 = new Order("0N5B816B6J23A");
            Order order2 = new Order("2A5J539C7V50T");
            Order order3 = new Order("9U5Y350F8F65R");
            Order order4 = new Order("2R9Y079P2P80E");
            Order order5 = new Order("6H4F009E1Q94K");
            Order order6 = new Order("6P8U056K0L57E");
            Order order7 = new Order("7A0Z413L6S90H");
            Order order8 = new Order("9I0G682M2S53R");
            Order order9 = new Order("7D8W722G5Z46S");
            Order order10 = new Order("1Y4O601S4F37D");

            List<Order> orders = new ArrayList<>();
            orders.add(order1);
            orders.add(order2);
            orders.add(order3);
            orders.add(order4);
            orders.add(order5);
            orders.add(order6);
            orders.add(order7);
            orders.add(order8);
            orders.add(order9);
            orders.add(order10);
            // For example purposes, all 15 first customers will have the same orders
            // Create Customer objects and write it to the file
            // Customer with orders
            Customer customer1 = new Customer("Billi","Bendle","Suite 43","South River","Ontario","P3Y","bbendle0@ocn.ne.jp","373 814 1786", orders);
            Customer customer2 = new Customer("Alanna","Dami","Apt 769","Hudson","Québec","J0N","adami1@state.tx.us","588 476 8937", orders);
            Customer customer3 = new Customer("Bradney","Melland","Room 95","Carleton Place","Ontario","K7C","bmelland2@edublogs.org","605 355 6787", orders);
            Customer customer4 = new Customer("Marcy","Saxon","PO Box 27636","Antigonish","Nova Scotia","B2G","msaxon3@tinypic.com","788 336 3834", orders);
            Customer customer5 = new Customer("Gothart","Colcutt","Room 1373","Little Current","Ontario","J6A","gcolcutt4@youku.com","635 360 2445", orders);
            Customer customer6 = new Customer("Dyanna","Dann","Apt 53","Pincher Creek","Alberta","J0J","ddann5@ox.ac.uk","861 825 6154", orders);
            Customer customer7 = new Customer("Baily","Stegers","Room 1754","Newmarket","Ontario","L9N","bstegers6@google.cn","981 629 4998", orders);
            Customer customer8 = new Customer("Aloin","Meddows","PO Box 91250","Lloydminster","Saskatchewan","S9V","ameddows7@google.nl","493 800 7160", orders);
            Customer customer9 = new Customer("Omar","Gherardesci","Suite 83","Cookshire-Eaton","Québec","M4C","ogherardesci8@stumbleupon.com","704 801 4742", orders);
            Customer customer10 = new Customer("Averil","Bradfield","PO Box 76280","Windsor","Ontario","J1S","abradfield9@themeforest.net","463 693 3398", orders);
            Customer customer11 = new Customer("Francine","Kanwell","Room 1395","Saint John","New Brunswick","E2L","fkanwella@omniture.com","690 111 9261", orders);
            Customer customer12 = new Customer("Tailor","Kindell","Suite 98","Forestville","Québec","G2B","tkindellb@sun.com","298 212 0794", orders);
            Customer customer13 = new Customer("Hedvig","Huckin","Suite 60","Vaughan","Ontario","M3N","hhuckinc@gravatar.com","259 688 7068", orders);
            Customer customer14 = new Customer("Gilbertine","Colls","Apt 539","Melita","Manitoba","M5L","gcollsd@blogspot.com","876 180 0152", orders);
            Customer customer15 = new Customer("Appolonia","Falco","PO Box 86104","Lions Bay","British Columbia","K4K","afalcoe@skype.com","538 447 1361", orders);
            // Customer with no orders
            Customer customer16 = new Customer("Bobby","Buxton","PO Box 54658","Bancroft","Ontario","S4X","bbuxtonf@shutterfly.com","828 809 1076");
            Customer customer17 = new Customer("Meghan","Keppy","Suite 13","Kitchener","Ontario","N2R","mkeppyg@ibm.com","466 987 9751");
            Customer customer18 = new Customer("Bobette","Barnaby","1st Floor","Wolfville","Nova Scotia","B4P","bbarnabyh@sohu.com","253 840 7884");
            Customer customer19 = new Customer("Donella","Rosier","Room 996","Les Cèdres","Québec","J6S","drosieri@europa.eu","655 505 9488");
            Customer customer20 = new Customer("Selig","Mortlock","Suite 55","Dorval","Québec","H9S","smortlockj@elegantthemes.com","133 106 7846");

            // Write to file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(customer1);
            out.writeObject(customer2);
            out.writeObject(customer3);
            out.writeObject(customer4);
            out.writeObject(customer5);
            out.writeObject(customer6);
            out.writeObject(customer7);
            out.writeObject(customer8);
            out.writeObject(customer9);
            out.writeObject(customer10);
            out.writeObject(customer11);
            out.writeObject(customer12);
            out.writeObject(customer13);
            out.writeObject(customer14);
            out.writeObject(customer15);
            out.writeObject(customer16);
            out.writeObject(customer17);
            out.writeObject(customer18);
            out.writeObject(customer19);
            out.writeObject(customer20);
            out.close();

            // Read the Customer objects from file to check if the data was written correctly
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            System.out.println("Customers: ");
            while(true){
                try{
                    Customer customerFromFile = (Customer) in.readObject();
                    System.out.println("- " + customerFromFile.getFirstName() + " " + customerFromFile.getLastName() + " " + customerFromFile.getOrders());
                }catch (EOFException e){
                    break;
                }
            }
            in.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}


