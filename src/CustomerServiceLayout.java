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
import java.io.IOException;
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
        loadBtn.setOnAction(actionEvent -> {
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
        });

        nextBtn.setOnAction(actionEvent -> {
            currentCustomerRecord++;
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
        });

        prevBtn.setOnAction(actionEvent -> {
            currentCustomerRecord--;
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
                System.out.println(customers.get(currentCustomerRecord).getFirstName());
            }
            if (currentCustomerRecord == 0){
                prevBtn.setDisable(true);
            }
        });

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
