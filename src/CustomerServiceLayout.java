import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class CustomerServiceLayout extends Application {
    private static final String[] PROVINCES = {
            "AB", "BC", "MB", "NB", "NL", "NT", "NS", "NU", "ON", "PE", "QC", "SK", "YT"
    };

    private static final String[] ORDERS = {};
    @Override
    public void start(Stage stage) throws Exception {
        showStage(stage);
    }

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
        //provinceList.setValue(ORDERS[0]);
        ordersLabelLayout.getChildren().add(ordersLabel);
        ordersLabelLayout.setAlignment(Pos.TOP_LEFT);
        ordersLabelLayout.setPadding(new Insets(0,0,0,75));
        ordersListLayout.getChildren().add(ordersList);
        ordersListLayout.setAlignment(Pos.TOP_LEFT);
        ordersListLayout.setPadding(new Insets(0,0,0,75));

        // Load Data and Save buttons
        HBox btnsLayout = new HBox();
        Button loadBtn = new Button("Load Data");
        loadBtn.setPrefWidth(100);
        Button prevBtn = new Button("<");
        Button nextBtn = new Button(">");
        Button saveBtn = new Button("Save");
        saveBtn.setPrefWidth(100);
        btnsLayout.getChildren().addAll(loadBtn,prevBtn,nextBtn,saveBtn);
        btnsLayout.setAlignment(Pos.CENTER);
        btnsLayout.setSpacing(20);
        btnsLayout.setPadding(new Insets(50,0,0,0));

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
                        btnsLayout);
        return customerServiceFormPane;
    }

    protected MenuBar getMainMenu(){
        //File menu bar
        MenuBar mainMenu = new MenuBar();
        Menu menuFile = new Menu("File");
        mainMenu.getMenus().addAll(menuFile);
        return mainMenu;
    }
    protected BorderPane getMainLayoutPane(){
        MenuBar mainMenu = getMainMenu();
        VBox formPane = getFormPane();
        BorderPane customerServiceLayout = new BorderPane();
        customerServiceLayout.setTop(mainMenu);
        customerServiceLayout.setCenter(formPane);
        return customerServiceLayout;
    }

    protected void showStage(Stage stage){
        Scene scene = new Scene(getMainLayoutPane(), 600, 400);
        stage.setTitle("Customer Service System");
        stage.setScene(scene);
        stage.show();
    }
}
