package Client.ViewModel;

import Client.Model.ClientModel;
import Client.Model.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInCustomerViewModel
{
  private ClientModel userModel;

  public LogInCustomerViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public boolean checkForPassword(String emailAddress, String password) throws
      RemoteException, SQLException
  {
   return userModel.checkForPassword(emailAddress,password);
  }
}
