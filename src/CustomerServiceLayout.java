/*
    Assigment 1 - sdcCOSC603Assign1 - Class Name CustomerServiceLayout.java
    This class will contain the code to implement the ACME Customer Service JavaFX Application.
    Author: Rodrigo Farinango - SDC - ID#: 000482153
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerServiceLayout extends Application {
    private static final String[] PROVINCES = {
            "AB", "BC", "MB", "NB", "NL", "NT", "NS", "NU", "ON", "PE", "QC", "SK", "YT"
    };
    private List<Customer> customers = new ArrayList<>();
    private int currentCustomerRecord = 0;
    private boolean dataLoaded = false;
    private String[] ORDERS = {};
    @Override
    public void start(Stage stage) throws Exception {

        showStage(stage);
    }

    /***
     * Generates and initialize all the nodes that are part of the ACME Customer Service Form.
     * @return a VBox that represents the ACME Customer Service Form.
     */
    protected VBox getFormPane(){
        // ACME Customer Service Form
        VBox customerServiceFormPane = new VBox();
        customerServiceFormPane.setAlignment(Pos.CENTER);
        // ACME Customer Service title
        Label titleLabel = new Label("ACME Customer Service");
        titleLabel.setFont(Font.font("Arial", 32));

        // First Name and Last Name inputs
        HBox fullNameLayout = new HBox();
        Label fNLabel = new Label("First Name:");
        Label lNLabel = new Label("Last Name:");
        TextField fNTxtField = new TextField();
        TextField lNTxtField = new TextField();
        fullNameLayout.getChildren().addAll(fNLabel,fNTxtField, lNLabel, lNTxtField);
        fullNameLayout.setAlignment(Pos.CENTER);
        fullNameLayout.setSpacing(10);

        // Address input
        HBox addressLayout = new HBox();
        Label addressLabel = new Label("Address:");
        TextField addressTxtField = new TextField();
        addressTxtField.setPrefWidth(390);
        addressLayout.getChildren().addAll(addressLabel, addressTxtField);
        addressLayout.setAlignment(Pos.CENTER);
        addressLayout.setSpacing(10);

        // City, province and postal inputs
        HBox fullCityInfoLayout = new HBox();
        Label cityLabel = new Label("City:");
        Label provinceLabel = new Label("Province:");
        Label postalLabel = new Label("Postal:");
        TextField cityTxtField = new TextField();
        cityTxtField.setPrefWidth(160);
        ComboBox<String> provinceList = new ComboBox<>();
        provinceList.setItems(FXCollections.observableArrayList(PROVINCES));
        provinceList.setValue(PROVINCES[0]);
        TextField postalTxtField = new TextField();
        postalTxtField.setPrefWidth(70);
        fullCityInfoLayout.getChildren().addAll(cityLabel,cityTxtField,provinceLabel, provinceList,postalLabel,postalTxtField);
        fullCityInfoLayout.setAlignment(Pos.CENTER);
        fullCityInfoLayout.setSpacing(10);

        // Email and phone inputs
        HBox contactInfoLayout = new HBox();
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Phone:");
        TextField emailTxtField = new TextField();
        emailTxtField.setPrefWidth(175);
        TextField phoneTxtField = new TextField();
        phoneTxtField.setPrefWidth(175);
        contactInfoLayout.getChildren().addAll(emailLabel, emailTxtField, phoneLabel, phoneTxtField);
        contactInfoLayout.setAlignment(Pos.CENTER);
        contactInfoLayout.setSpacing(10);

        // Orders
        HBox ordersLabelLayout = new HBox();
        HBox ordersListLayout = new HBox();
        Label ordersLabel = new Label("Orders:");
        ordersLabel.setPadding(new Insets(0,0,-10,0));
        ComboBox<String> ordersList = new ComboBox<>();
        ordersList.setItems(FXCollections.observableArrayList(ORDERS));
        ordersList.setPrefWidth(140);
        ordersList.setPrefHeight(15);
        ordersLabelLayout.getChildren().add(ordersLabel);
        ordersLabelLayout.setAlignment(Pos.TOP_LEFT);
        ordersLabelLayout.setPadding(new Insets(0,0,0,75));
        ordersListLayout.getChildren().add(ordersList);
        ordersListLayout.setAlignment(Pos.TOP_LEFT);
        ordersListLayout.setPadding(new Insets(0,0,0,75));

        // Load Data and Save buttons
        HBox btnLayout = new HBox();
        Button loadBtn = new Button("Load Data");
        loadBtn.setPrefWidth(100);
        Button prevBtn = new Button("<");
        Button nextBtn = new Button(">");
        Button saveBtn = new Button("Save");
        saveBtn.setPrefWidth(100);
        prevBtn.setDisable(true);
        nextBtn.setDisable(true);
        btnLayout.getChildren().addAll(loadBtn,prevBtn,nextBtn,saveBtn);
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.setSpacing(20);
        btnLayout.setPadding(new Insets(50,0,0,0));

        // On load btn click
        loadBtn.setOnAction(actionEvent -> handleLoadData(nextBtn, prevBtn, fNTxtField, lNTxtField, addressTxtField, cityTxtField, provinceList, postalTxtField, emailTxtField, phoneTxtField, ordersList));
        // On nextBtn Click
        nextBtn.setOnAction(actionEvent -> handleNxtBtn(nextBtn, prevBtn, saveBtn, fNTxtField, lNTxtField, addressTxtField, cityTxtField, provinceList, postalTxtField, emailTxtField, phoneTxtField, ordersList));
        // On prevBtn Click
        prevBtn.setOnAction(actionEvent -> handlePrvBtn(nextBtn, prevBtn, saveBtn, fNTxtField, lNTxtField, addressTxtField, cityTxtField, provinceList, postalTxtField, emailTxtField, phoneTxtField, ordersList));
        //On saveBtn Click
        saveBtn.setOnAction(actionEvent -> handleSaveBtn(ordersList, saveBtn));

        // Add elements to the form pane
        customerServiceFormPane.setSpacing(10);
        customerServiceFormPane.getChildren()
                .addAll(titleLabel,
                        fullNameLayout,
                        addressLayout,
                        fullCityInfoLayout,
                        contactInfoLayout,
                        ordersLabelLayout,
                        ordersListLayout,
                        btnLayout);
        return customerServiceFormPane;
    }

    /***
     * Handle method to save current record into a RandomAccessFile.
     * @param ordersList ordersList that contains orders for each customer.
     * @param saveBtn saveBtn reference to be disabled if the record is saved.
     */
    protected void handleSaveBtn(ComboBox<String> ordersList,
                                 Button saveBtn){
        List<Order> orders = new ArrayList<>();
        // Get Array that contains orders from the combobox
        String[] ordersArray = ordersList.getItems().toArray(new String[0]);
        // Cast to List of orders
        for (String o: ordersArray){
            Order order = new Order(o);
            orders.add(order);
        }
        if(!customers.get(currentCustomerRecord).isRecordSaved()){
            // Random access file for writing
            try{
                RandomAccessFile customersFile = new RandomAccessFile("savedCustomers.dat", "rw");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);

                // Write the customer data to the DataOutputStream
                dos.writeUTF(customers.get(currentCustomerRecord).getFirstName());
                dos.writeUTF(customers.get(currentCustomerRecord).getLastName());
                dos.writeUTF(customers.get(currentCustomerRecord).getAddress());
                dos.writeUTF(customers.get(currentCustomerRecord).getCity());
                dos.writeUTF(customers.get(currentCustomerRecord).getProvince());
                dos.writeUTF(customers.get(currentCustomerRecord).getPostalCode());
                dos.writeUTF(customers.get(currentCustomerRecord).getEmail());
                dos.writeUTF(customers.get(currentCustomerRecord).getPhoneNumber());
                // Write the orders data to the DataOutputStream
                dos.writeInt(orders.size());
                for (Order order : orders){
                    dos.writeUTF(order.getOrderNumber());
                }
                byte[] data = baos.toByteArray();
                customersFile.seek(customersFile.length());
                customersFile.write(data);
                customersFile.close();
                customers.get(currentCustomerRecord).setRecordSaved(true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        saveBtn.setDisable(customers.get(currentCustomerRecord).isRecordSaved());
    }

    /***
     * Handle method to update info of previous record on prvBtn click.
     * @param nextBtn nextBtn button reference.
     * @param prevBtn prevBtn button reference.
     * @param saveBtn saveBtn button reference.
     * @param fNTxtField First Name TextField to update.
     * @param lNTxtField Last Name TextField to update.
     * @param addressTxtField Address TextField to update.
     * @param cityTxtField City TextField to update.
     * @param provinceList Provinces ComboBox to update their customer corresponding value.
     * @param postalTxtField Postal TextField to update.
     * @param emailTxtField Email TextField to update.
     * @param phoneTxtField Phone TextField to update.
     * @param ordersList Orders ComboBox to update to their customer corresponding orders values.
     */
    protected  void handlePrvBtn(Button nextBtn,
                                 Button prevBtn,
                                 Button saveBtn,
                                 TextField fNTxtField,
                                 TextField lNTxtField,
                                 TextField addressTxtField,
                                 TextField cityTxtField,
                                 ComboBox<String> provinceList,
                                 TextField postalTxtField,
                                 TextField emailTxtField,
                                 TextField phoneTxtField,
                                 ComboBox<String> ordersList){
        // Track the current customer record info to update the text fields
        currentCustomerRecord--;
        saveBtn.setDisable(customers.get(currentCustomerRecord).isRecordSaved());
        if (currentCustomerRecord!=customers.size()-1){
            nextBtn.setDisable(false);
            updateTextFields(
                    fNTxtField,
                    lNTxtField,
                    addressTxtField,
                    cityTxtField,
                    provinceList,
                    postalTxtField,
                    emailTxtField,
                    phoneTxtField,
                    ordersList,
                    customers.get(currentCustomerRecord));
        }
        if (currentCustomerRecord == 0){
            prevBtn.setDisable(true);

        }
    }
    /***
     * Handle method to update info of next record on nxtBtn click.
     * @param nextBtn nextBtn button reference.
     * @param prevBtn prevBtn button reference.
     * @param saveBtn saveBtn button reference.
     * @param fNTxtField First Name TextField to update.
     * @param lNTxtField Last Name TextField to update.
     * @param addressTxtField Address TextField to update.
     * @param cityTxtField City TextField to update.
     * @param provinceList Provinces ComboBox to update their customer corresponding value.
     * @param postalTxtField Postal TextField to update.
     * @param emailTxtField Email TextField to update.
     * @param phoneTxtField Phone TextField to update.
     * @param ordersList Orders ComboBox to update to their customer corresponding orders values.
     */
    protected void handleNxtBtn(Button nextBtn,
                                Button prevBtn,
                                Button saveBtn,
                                TextField fNTxtField,
                                TextField lNTxtField,
                                TextField addressTxtField,
                                TextField cityTxtField,
                                ComboBox<String> provinceList,
                                TextField postalTxtField,
                                TextField emailTxtField,
                                TextField phoneTxtField,
                                ComboBox<String> ordersList){
        // Track the current customer record info to update the text fields
        currentCustomerRecord++;
        saveBtn.setDisable(customers.get(currentCustomerRecord).isRecordSaved());

        if (currentCustomerRecord!=0){
            prevBtn.setDisable(false);
            updateTextFields(
                    fNTxtField,
                    lNTxtField,
                    addressTxtField,
                    cityTxtField,
                    provinceList,
                    postalTxtField,
                    emailTxtField,
                    phoneTxtField,
                    ordersList,
                    customers.get(currentCustomerRecord));
        }
        if (currentCustomerRecord==customers.size()-1){
            nextBtn.setDisable(true);
        }
    }

    /***
     * Handle method to update info of previous record on prvBtn click.
     * @param nextBtn nextBtn button reference.
     * @param prevBtn prevBtn button reference.
     * @param fNTxtField First Name TextField to load.
     * @param lNTxtField Last Name TextField to load.
     * @param addressTxtField Address TextField to load.
     * @param cityTxtField City TextField to load.
     * @param provinceList Provinces ComboBox to load their customer corresponding value.
     * @param postalTxtField Postal TextField to update.
     * @param emailTxtField Email TextField to load.
     * @param phoneTxtField Phone TextField to load.
     * @param ordersList Orders ComboBox to load to their customer corresponding orders values.
     */
    protected void handleLoadData(Button nextBtn,
                            Button prevBtn,
                            TextField fNTxtField,
                            TextField lNTxtField,
                            TextField addressTxtField,
                            TextField cityTxtField,
                            ComboBox<String> provinceList,
                            TextField postalTxtField,
                            TextField emailTxtField,
                            TextField phoneTxtField,
                            ComboBox<String> ordersList){
        if (!dataLoaded){
            String filename = "src/customers.dat";
            CustomerDAO customerDAO = new CustomerDAO(filename);
            nextBtn.setDisable(false);
            try{
                customers = customerDAO.loadCustomers();
                // Show data in fields for the first record
                updateTextFields(
                        fNTxtField,
                        lNTxtField,
                        addressTxtField,
                        cityTxtField,
                        provinceList,
                        postalTxtField,
                        emailTxtField,
                        phoneTxtField,
                        ordersList,
                        customers.get(0));
                // Disable prevBtn since we have the first record
                prevBtn.setDisable(true);
                dataLoaded = true;
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    /***
     * Updates the values of the input parameters in the ACME Customer Service form to show the current values in the form.
     * @param fName First Name TextField to update.
     * @param lName Last Name TextField to update.
     * @param address Address TextField to update.
     * @param city City TextField to update.
     * @param provincesList Provinces ComboBox to update their customer corresponding value.
     * @param postal Postal TextField to update.
     * @param email Email TextField to update.
     * @param phone Phone TextField to update.
     * @param ordersList Orders ComboBox to update to their customer corresponding orders values.
     * @param customer Customer that contains all the correct values to update in the TextFields.
     */
    protected void updateTextFields(TextField fName,
                                    TextField lName,
                                    TextField address,
                                    TextField city,
                                    ComboBox<String> provincesList,
                                    TextField postal,
                                    TextField email,
                                    TextField phone,
                                    ComboBox<String> ordersList,
                                    Customer customer){
        fName.setText(customer.getFirstName());
        lName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        city.setText(customer.getCity());
        postal.setText(customer.getPostalCode());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhoneNumber());
        String[] ordersStringArray = customer.getOrders().stream().map(order -> order.getOrderNumber()).toArray(String[]::new);
        ordersList.setItems(FXCollections.observableArrayList(ordersStringArray));
        if (ordersStringArray.length!=0){
            ordersList.setValue(ordersStringArray[0]);
        }
        updateProvincesComboBox(customer, provincesList);
    }

    /***
     * Updates the provinces ComboBox to set the value corresponding to the correct customer value.
     * @param customer Customer that contains the String of the province.
     * @param provincesList provinces ComboBox to be updated.
     */
    protected void updateProvincesComboBox(Customer customer, ComboBox<String> provincesList){
        switch (customer.getProvince()){
            case "Alberta":
                provincesList.setValue(PROVINCES[0]);
                break;
            case "British Columbia":
                provincesList.setValue(PROVINCES[1]);
                break;
            case "Manitoba":
                provincesList.setValue(PROVINCES[2]);
                break;
            case "New Brunswick":
                provincesList.setValue(PROVINCES[3]);
                break;
            case "Newfoundland and Labrador":
                provincesList.setValue(PROVINCES[4]);
                break;
            case "Northwest Territories":
                provincesList.setValue(PROVINCES[5]);
                break;
            case "Nova Scotia":
                provincesList.setValue(PROVINCES[6]);
                break;
            case "Nunavut":
                provincesList.setValue(PROVINCES[7]);
                break;
            case "Ontario":
                provincesList.setValue(PROVINCES[8]);
                break;
            case "Prince Edward Island":
                provincesList.setValue(PROVINCES[9]);
                break;
            case "Québec":
                provincesList.setValue(PROVINCES[10]);
                break;
            case "Saskatchewan":
                provincesList.setValue(PROVINCES[11]);
                break;
            case "Yukon":
                provincesList.setValue(PROVINCES[12]);
                break;


        }
    }

    /***
     * Generate the main bar menu that contains the File menu.
     * @return returns a menu bar object.
     */
    protected MenuBar getMainMenu(){
        //File menu bar
        MenuBar mainMenu = new MenuBar();
        Menu menuFile = new Menu("File");
        mainMenu.getMenus().addAll(menuFile);
        return mainMenu;
    }

    /***
     * Generates the main BorderPane layout that will contain all the customer service layout.
     * @return BorderPane representing the customer service main layout.
     */
    protected BorderPane getMainLayoutPane(){
        MenuBar mainMenu = getMainMenu();
        VBox formPane = getFormPane();
        BorderPane customerServiceLayout = new BorderPane();
        customerServiceLayout.setTop(mainMenu);
        customerServiceLayout.setCenter(formPane);
        return customerServiceLayout;
    }

    /***
     * Shows the stage
     * @param stage stage to show.
     */
    protected void showStage(Stage stage){
        Scene scene = new Scene(getMainLayoutPane(), 600, 400);
        stage.setTitle("Customer Service System");
        stage.setScene(scene);
        stage.show();
    }
}
