package Client.Views.AddVehicleView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.AddVehicleViewModel;
import Client.Views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddVehicleViewController implements ViewController
{
    @FXML private TextField licensePlateField;
    @FXML private TextField enginePowerField;
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField yearField;
    @FXML private TextField nbOfSeatsField;
    @FXML private TextField priceField;

    @FXML private RadioButton automaticGearBoxButton;
    @FXML private RadioButton manualGearBoxButton;

    @FXML private RadioButton petrolTypeButton;
    @FXML private RadioButton dieselTypeButton;
    @FXML private RadioButton hybridTypeButton;
    @FXML private RadioButton electricTypeButton;

    @FXML private ComboBox<String> types;


    private ViewHandler viewHandler;
    private AddVehicleViewModel addVehicleViewModel;

    @Override public void init(ViewHandler vh, ViewModelFactory vmf)
    {
        this.viewHandler = vh;
        this.addVehicleViewModel = vmf.getAddVehicleViewModel();

        types.getItems().addAll("Car", "Minibus", "Bus", "Motorcycle");

    }

    private String getFuelType(){
        if (petrolTypeButton.isSelected()){
            return  "petrol";
        }
        if (dieselTypeButton.isSelected()){
            return "diesel";
        }
        if (hybridTypeButton.isSelected()){
            return "hybrid";
        }

        if (electricTypeButton.isSelected()){
            return "electric";
        }

        else return "Not selected";
    }

    private String getGearBoxType(){
        if (automaticGearBoxButton.isSelected()){
            return "automatic";
        }
        if (manualGearBoxButton.isSelected()){
            return "manual";
        }
        else return "Not selected";
    }


    public void onAddButton(){
        Platform.runLater(() -> {
            try {
                addVehicleViewModel.addVehicle(
                    licensePlateField.getText(),
                    parseInt(enginePowerField.getText()),
                    types.getSelectionModel().getSelectedItem(),
                    makeField.getText(),
                    modelField.getText(),
                    parseInt(yearField.getText()),
                    getGearBoxType(),
                    getFuelType(),
                    parseInt(nbOfSeatsField.getText()),
                    parseDouble(priceField.getText())
                    );
            } catch (SQLException | RemoteException e) {
                e.printStackTrace();
            }
        });
        viewHandler.openMainMenu();
  }

  public void onExitButton(){
    viewHandler.openMainMenu();
  }
}