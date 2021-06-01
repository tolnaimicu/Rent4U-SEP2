package Client.Views.AddPersonalAccountView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.Model.Customer;
import Client.ViewModel.AddPersonalAccountViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class AddPersonalAccountViewController implements ViewController
{
  private ViewHandler viewHandler;
  private AddPersonalAccountViewModel addPersonalAccountViewModel;

  @FXML TextField firstNameField;
  @FXML TextField lastNameField;
  @FXML DatePicker dateOfBirthPicker;
  @FXML TextField eMailField;
  @FXML TextField drivingLicenseField;
  @FXML TextField cprFirstField;
  @FXML TextField cprSecondField;
  @FXML TextField passwordField;
  @FXML TextField rePasswordField;
  @FXML TextField phoneField;
  @FXML Label passwordCheckLabel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.addPersonalAccountViewModel = viewModelFactory.getAddPersonalAccountViewModelViewModel();
    passwordCheckLabel.setVisible(false);
  }

  private boolean checkPassword(){
    if (passwordField.getText().equals(rePasswordField.getText())){
      passwordCheckLabel.setVisible(false);
      return true;
    } else {
      passwordCheckLabel.setVisible(true);
      return false;
    }
  }

  private String getCpr(){
    return cprFirstField.getText()+cprSecondField.getText();
  }

  public GregorianCalendar getDateBirth(){
    GregorianCalendar now=new GregorianCalendar();
    LocalDate date = dateOfBirthPicker.getValue();
    GregorianCalendar dateOfBirth = new GregorianCalendar(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
    if(dateOfBirth.before(now))
      return null;
    return  dateOfBirth;
  }

  public void onCreateButton() throws RemoteException, SQLException {
    if (getDateBirth() != null)
    {
      if (checkPassword()){
        Customer customer =
            addPersonalAccountViewModel.createPersonalAccount(
                firstNameField.getText(),
                lastNameField.getText(),
                getDateBirth(),
                eMailField.getText(),
                passwordField.getText(),
                phoneField.getText(),
                drivingLicenseField.getText(),
                getCpr()
            );
        viewHandler.openMenuCustomerView(customer);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Your profile was created");
        alert.setContentText("Your personal profile is ready!\nThank you!");
        alert.showAndWait();
    }}
    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText(
          "Please enter a valid time of birth\nPlease try again!");
      alert.showAndWait();
    }


  }

  public void onMenuButton() throws SQLException, RemoteException {
    viewHandler.openLogInCustomer();
  }

}
