package Client.Core;

import Client.ViewModel.AddVehicleViewModel;
import Client.ViewModel.LogInViewModel;
import Client.ViewModel.MenuViewModel;

public class ViewModelFactory
{
  private static ViewModelFactory instance = new ViewModelFactory();

  public static ViewModelFactory getInstance(){
    return instance;
  }

  private LogInViewModel logInViewModel;
  private AddVehicleViewModel addVehicleViewModel;
  private MenuViewModel menuViewModel;

  private ViewModelFactory() {
  }

  public LogInViewModel getLogInViewModel() {
    if (logInViewModel == null)
      logInViewModel = new LogInViewModel(ModelFactory.getInstance().getClientModel());
    return logInViewModel;
  }

  public AddVehicleViewModel getAddVehicleViewModel()
  {
    if(addVehicleViewModel==null)
      addVehicleViewModel=new AddVehicleViewModel(ModelFactory.getInstance().getClientModel());
    return addVehicleViewModel;
  }

  public MenuViewModel getMenuViewModel()
  {
    if(menuViewModel==null)
      menuViewModel=new MenuViewModel(ModelFactory.getInstance().getClientModel());
    return menuViewModel;
  }






}