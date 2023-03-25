import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private String filename;

    public CustomerDAO(String filename){
        this.filename = filename;
    }

    public List<Customer> loadCustomers() throws IOException,ClassNotFoundException{
        FileInputStream fileIn =  new FileInputStream(filename);
        ObjectInputStream in  = new ObjectInputStream(fileIn);
        List<Customer> customers = new ArrayList<>();
        while (fileIn.available() > 0){
            Customer customer = (Customer) in.readObject();
            customers.add(customer);
        }
        in.close();
        fileIn.close();
        return customers;
    }
}
