package team1_finalproject;

/**
 * @Course: SDEV 450 ~ Java Enterprise
 * @Author: Jenney Chang & Jeremy Dehay
 * @Assignment Name: Team1_FinalProject
 * @Date: Dec 2, 2018
 * @Subclass CreateAccount Controller Description: Adds functionality for
 * elements
 */
//Imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import team1_finalproject.supporting_classes.*;

public class CreateAccountController implements Initializable {

    //Variables
    @FXML
    private TextField tfNewUserEmail;
    @FXML
    private PasswordField tfNewUserPassword;
    @FXML
    private PasswordField tfNewUserPassword2;
    @FXML
    private Text txtErrorMsg;

    /**
     * Method: Initialize
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initailize Error Message to Invisible
        txtErrorMsg.setVisible(false);
    }

    /**
     * Method: Creates account & validates input
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void CreateAccount(ActionEvent event) throws Exception {
        // Connect to the database
        DBInterface db = new DBInterface();

        //#1 Check if text fields are empty
        if (tfNewUserEmail.getText().equals("") || tfNewUserPassword.getText().equals("")
                || tfNewUserPassword2.getText().equals("")) {
            errorMessage("Invalid text entry: one or more fields are empty");
            return;
        }

        //#2 Check if email is valid & password length
        //if email is valid & password length is correct, then do passwords match?
        if (tfNewUserEmail.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
        }

        //Check password length
        if (tfNewUserPassword.getLength() == 6
                && tfNewUserPassword.getText().equals(tfNewUserPassword2.getText())) {

        }
        //Check if passwords match
        if (!tfNewUserPassword.getText().equals(tfNewUserPassword2.getText())) {
            System.out.println("Passwords don't match");
            //Display Error message
            errorMessage("Invalid Password: passwords don't match.");
            return;
        }

        if (tfNewUserEmail.getText().matches("^\\D+$") && tfNewUserPassword.getText().matches("^\\D+$")) {

            // send tfUserEmail to DBInterface
            DBInterface.setName(tfNewUserEmail.getText());
            DBInterface.setPassword(tfNewUserPassword.getText());
            if (!db.createDB()) {
                errorMessage("Email address unavailable.");
                return;
            }

            // Change scene after successful account creation
            Parent rootBP = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Scene sceneBP = new Scene(rootBP);

            Stage wSignIn = (Stage) ((Node) event.getSource()).getScene().getWindow();
            wSignIn.setScene(sceneBP);
            wSignIn.show();
        }

    }

    /**
     * Method: Error Message
     *
     * @param error
     */
    @FXML
    public void errorMessage(String error) {
        txtErrorMsg.setText(error);
        txtErrorMsg.setVisible(true);
    }

    /**
     * Method: Exit Create Account window
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void CancelAccountCreation(ActionEvent event) throws Exception {
        Parent rootBP = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene sceneBP = new Scene(rootBP);

        Stage wSignIn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wSignIn.setScene(sceneBP);
        wSignIn.show();
    }
}
