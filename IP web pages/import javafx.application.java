import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ElectricBillingSystem extends Application {

    // GUI components
    private TextField usernameTxt;
    private PasswordField passwordTxt;
    private Button loginBtn;
    private Label statusLbl;
    private Label customerNameLbl;
    private TextField customerNameTxt;
    private Label customerAddressLbl;
    private TextField customerAddressTxt;
    private Label customerPhoneLbl;
    private TextField customerPhoneTxt;
    private Label kwhLbl;
    private TextField kwhTxt;
    private Label rateLbl;
    private TextField rateTxt;
    private Button computeBtn;
    private Button clearBtn;
    private TextArea billingDetailsTxtArea;

    @Override
    public void start(Stage primaryStage) {
        // Set up login pane layout
        VBox loginPane = new VBox();
        loginPane.setSpacing(10);
        loginPane.setPadding(new Insets(10));
        Label titleLabel = new Label("Electric Billing System");
        titleLabel.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);
        Label usernameLbl = new Label("Username: ");
        usernameTxt = new TextField();
        Label passwordLbl = new Label("Password: ");
        passwordTxt = new PasswordField();
        loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> handleLogin());
        statusLbl = new Label();
        statusLbl.setStyle("-fx-text-fill: red;");
        loginPane.getChildren().addAll(titleLabel, usernameLbl, usernameTxt, passwordLbl, passwordTxt, loginBtn, statusLbl);

        // Set up billing input pane layout
        GridPane billingInputPane = new GridPane();
        billingInputPane.setHgap(10);
        billingInputPane.setVgap(10);
        billingInputPane.setPadding(new Insets(10));
        customerNameLbl = new Label("Customer Name: ");
        customerNameTxt = new TextField();
        customerAddressLbl = new Label("Customer Address: ");
        customerAddressTxt = new TextField();
        customerPhoneLbl = new Label("Customer Phone: ");
        customerPhoneTxt = new TextField();
        kwhLbl = new Label("KWH used: ");
        kwhTxt = new TextField();
        rateLbl = new Label("Rate per KWH: ");
        rateTxt = new TextField();
        computeBtn = new Button("Compute");
        computeBtn.setOnAction(e -> computeBillingDetails());
        clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> clearForm());
        billingDetailsTxtArea = new TextArea();
        billingDetailsTxtArea.setEditable(false);
        billingDetailsTxtArea.setPrefSize(300, 200);
        billingInputPane.addRow(0, customerNameLbl, customerNameTxt);
        billingInputPane.addRow(1, customerAddressLbl, customerAddressTxt);
        billingInputPane.addRow(2, customerPhoneLbl, customerPhoneTxt);
        billingInputPane.addRow(3, kwhLbl, kwhTxt);
        billingInputPane.addRow(4, rateLbl, rateTxt);
        billingInputPane.add(computeBtn, 0, 5);
        billingInputPane.add(clearBtn, 1, 5);
        billingInputPane.add(billingDetailsTxtArea, 0, 6, 2, 1);

        // Set up main layout with login pane initially visible
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(loginPane);

        // Set up scene and show window
        Scene scene = new Scene(mainPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electric Billing System");
        primaryStage.show();
    }

    private void handleLogin() {
        // TODO: Replace with actual database or authentication logic
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        if (username.equals("admin") && password.equals("pass")) {
            statusLbl.setText("");
            showBillingInputPane();
        } else {
            statusLbl.setText("Invalid username or password!");
        }
    }

    private void showBillingInputPane() {
        BorderPane mainPane = (BorderPane) usernameTxt.getScene().getRoot();
        mainPane.setCenter(null);
        mainPane.setCenter(billingInputPane);
    }

    private void computeBillingDetails() {
        // Get customer information
        String name = customerNameTxt.getText();
        String address = customerAddressTxt.getText();
        String phone = customerPhoneTxt.getText();

        // Get rate and KWH used
        double rate = Double.parseDouble(rateTxt.getText());
        double kwh = Double.parseDouble(kwhTxt.getText());

        // Calculate bill amount
        double billAmount = rate * kwh;

        // Display billing details
        String billingDetails = "Customer name: " + name + "\n" +
                "Customer address: " + address + "\n" +
                "Customer phone: " + phone + "\n" +
                "KWH used: " + kwh + "\n" +
                "Rate per KWH: " + rate + "\n" +
                "Total bill amount: $" + billAmount;
        billingDetailsTxtArea.setText(billingDetails);
    }

    private void clearForm() {
        customerNameTxt.clear();
        customerAddressTxt.clear();
        customerPhoneTxt.clear();
        kwhTxt.clear();
        rateTxt.clear();
        billingDetailsTxtArea.clear();
    }

    public static void main() {
        launch(args);
    }
}