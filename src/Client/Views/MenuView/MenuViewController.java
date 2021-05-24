package Client.Views.MenuView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.MenuViewModel;
import Client.Views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MenuViewController implements ViewController
{

  private ViewHandler viewHandler;
  private MenuViewModel menuViewModel;

  @FXML private Button openAddVehicleButton;
  @FXML private Button openLogOutButton;
  @FXML private Button listOfVehicleButton;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.menuViewModel = viewModelFactory.getMenuViewModel();
  }

  public void onAddVehicleButton(ActionEvent evt)
  {
    viewHandler.openAddVehicle();
  }

  public void onListOfVehicleButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openListOfVehicleView();
  }

  public void onLogOutButton(ActionEvent evt)
  {
    viewHandler.openLogInEmployee();
  }

  public void onAddBookingButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openAddBooking();
  }
}
