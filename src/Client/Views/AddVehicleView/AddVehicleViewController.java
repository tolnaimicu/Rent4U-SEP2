package Client.Views.AddVehicleView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.AddVehicleViewModel;
import Client.Views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private boolean manager;

    @Override public void init(ViewHandler vh, ViewModelFactory vmf)
    {
        this.viewHandler = vh;
        this.addVehicleViewModel = vmf.getAddVehicleViewModel();

        types.getItems().addAll("Car", "Minibus", "Van", "Motorcycle");

    }

    public void checkRadioButton(RadioButton radioButton){
        if (radioButton.isSelected()){
            radioButton.setSelected(false);
        }
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

    public void selectAutomatic(){
        checkRadioButton(manualGearBoxButton);
    }

    public void selectManual(){
        checkRadioButton(automaticGearBoxButton);
    }

    public void selectPetrol(){
        checkRadioButton(dieselTypeButton);
        checkRadioButton(hybridTypeButton);
        checkRadioButton(electricTypeButton);
    }

    public void selectDiesel(){
        checkRadioButton(petrolTypeButton);
        checkRadioButton(hybridTypeButton);
        checkRadioButton(electricTypeButton);
    }

    public void selectHybrid(){
        checkRadioButton(petrolTypeButton);
        checkRadioButton(dieselTypeButton);
        checkRadioButton(electricTypeButton);
    }

    public void selectElectric(){
        checkRadioButton(petrolTypeButton);
        checkRadioButton(dieselTypeButton);
        checkRadioButton(hybridTypeButton);
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
                viewHandler.openListOfVehicleView(manager);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("The vehicle was added");
                alert.setContentText("The vehicle was created in the system!\nThank you!");
                alert.showAndWait();
            } catch (SQLException | RemoteException e) {
                e.printStackTrace();
            }
        });
  }

  public void onExitButton(){
    viewHandler.openMainMenu(manager);
  }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}