import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomerServiceLayout extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(getMainLayoutPane(), 600, 400);
        stage.setTitle("Customer Service System");
        stage.setScene(scene);
        stage.show();
    }

    protected BorderPane getMainLayoutPane(){
        BorderPane customerServiceLayout = new BorderPane();
        return customerServiceLayout;
    }
}
