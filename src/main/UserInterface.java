package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import numberlist.InvalidIndexException;
import numberlist.objectlist.Money;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * This is a user interface class.This represent a user interface of expenditure
 * diary application.In this application, user can input strAmount, date,
 * category of his expenditures.The application shows the diary list of user's
 * expenditure by corresponding date, description of expenditure,reference
 * number, and strAmount of money.User can sort the list by date,description,
 * reference number, and the money strAmount.Also, user can edit the diary list
 * by reference number.This application also provides a Pie-Chart of the
 * expenditure to show the percentage of each expenditure.
 *
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @author Verena Girgis
 * @version 3/12/2018
 */
public class UserInterface extends Application {

    private BorderPane leftMainPane;
    private BorderPane borderPane;
    private VBox vBoxBottom;
    private GridPane gridPaneList = new GridPane();

    private double food;
    private double groceries;
    private double utilities;
    private double gas;
    private double other;

    ObservableList<PieChart.Data> pieChartData;
    private Expenditures expenditures = new Expenditures();

    private final String FOOD = "Food";
    private final String GAS = "Gas";
    private final String UTILITIES = "Utilities";
    private final String GROCERIES = "Groceries";
    private final String OTHER = "Other";

    /**
     * This is a start method which represent the stage of the GUI.
     *
     * @param primaryStage the primary Stage of the user interface.
     * @throws InvalidIndexException the exception which test if the index is
     * valid or not.
     */
    @Override
    public void start(Stage primaryStage) throws InvalidIndexException {
        expenditures.read();

        setExpenditureControls();

        //main pane
        HBox mainPane = new HBox(); //main pane

        //middle pane
        borderPane = new BorderPane();
        borderPane.setBottom(vBoxBottom);

        //middle pane for pie CHART
        StackPane middlePane = new StackPane();
        // add piechart
        makePieChart();
        final PieChart CHART = new PieChart(pieChartData);
        CHART.setTitle("Expenditure Expense");
        middlePane.getChildren().add(CHART);

        borderPane.setCenter(middlePane);

        //Adding left and center pane to main pane
        mainPane.getChildren().add(setLeftPane());
        mainPane.getChildren().add(borderPane);

        Scene scene = new Scene(mainPane, 1020, 500);
        primaryStage.setTitle("Expenditure Expense");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(1020);
        primaryStage.setOnHidden(e -> Platform.exit());

        primaryStage.show();

    }

    /**
     *
     * This is a helper method for the addData method. It provides a new data
     * for the pie chart!
     *
     * @param name
     * @param value
     */
    //adds new Data to the list
    private void naiveAddData(String name, double value) {
        pieChartData.add(new Data(name, value));
    }

    /**
     * This method updates existing Data-Object if name matches another name in
     * the pie chart.
     *
     * @param name
     * @param value
     */
    //
    private void addData(String name, double value) {
        for (Data d : pieChartData) {
            if (d.getName().equals(name)) {
                d.setPieValue(value);
                return;
            }
        }
        naiveAddData(name, value);
    }

    /**
     * This method make the pie chart on the application.
     */
    private void makePieChart() {
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Food", food),
                new PieChart.Data("Groceries", groceries),
                new PieChart.Data("Utilities", utilities),
                new PieChart.Data("Gas", gas),
                new PieChart.Data("Other", other));

    }

    /**
     * This method sets controls:label, textField, date picker, radio
     * buttons.And, set the functionalities for Add button, radio
     * button(radOthers).
     */
    private void setExpenditureControls() {
        GridPane gPaneInput = new GridPane();
        GridPane gPaneRadioButtons = new GridPane();

        gPaneInput.setPadding(new Insets(10));
        gPaneInput.setHgap(5);
        gPaneInput.setVgap(5);
        gPaneRadioButtons.setHgap(5);
        gPaneRadioButtons.setVgap(5);

        vBoxBottom = new VBox();
        Label lblAmount = new Label("Amount $");
        TextField txtAmount = new TextField();

        Label lblDate = new Label("Date:");
        // please note that we have allowed the user to make withdrawls they expect to make in the future. 
        //If they may like to omit this withdrawl, they may choose to edit it.
        DatePicker datePicker = new DatePicker();
        datePicker.getEditor().setDisable(true);
        //set date to today date
        datePicker.setValue(LocalDate.now());
        vBoxBottom.getChildren().addAll(gPaneInput, gPaneRadioButtons);

        vBoxBottom.setPadding(new Insets(0, 0, 10, 0));
        // Radio Button
        final ToggleGroup EXPENDITURE_T_GROUP = new ToggleGroup();
        RadioButton radFood = new RadioButton("Food");
        radFood.setUserData("Food");
        radFood.setToggleGroup(EXPENDITURE_T_GROUP);
        radFood.setSelected(true);
        RadioButton radGroceries = new RadioButton("Groceries");
        radGroceries.setUserData("Groceries");
        radGroceries.setToggleGroup(EXPENDITURE_T_GROUP);
        RadioButton radUtilities = new RadioButton("Utilities");
        radUtilities.setUserData("Utilities");
        radUtilities.setToggleGroup(EXPENDITURE_T_GROUP);
        RadioButton radGas = new RadioButton("Gas");
        radGas.setUserData("Gas");

        radGas.setToggleGroup(EXPENDITURE_T_GROUP);
        TextField txtOther = new TextField();
        txtOther.setDisable(true);
        RadioButton radOther = new RadioButton("Other");
        radOther.setUserData("Other");

        //radio button action
        radOther.setOnAction(e -> {
            txtOther.setDisable(false);
            txtOther.focusedProperty();
        });
        radOther.setToggleGroup(EXPENDITURE_T_GROUP);
        radGas.setOnAction(b -> txtOther.setDisable(true));
        radFood.setOnAction(b -> txtOther.setDisable(true));
        radUtilities.setOnAction(b -> txtOther.setDisable(true));

        Button btnAdd = new Button("Add");
        btnAdd.setDefaultButton(true);

        btnAdd.setOnAction((ActionEvent f) -> {
            try {
                String strDescription = EXPENDITURE_T_GROUP.getSelectedToggle().getUserData().toString();
                // we realize that if this is selected and the text feild has nothing, we will get an empty string.
                // we left it this way in case anyone does not want a description of their other factor.
                if (radOther.isSelected()) {
                    strDescription = txtOther.getText();
                }

                String strAmount = txtAmount.getText();
                if (strAmount.charAt(0) == '-' || strAmount.equals("")) {
                    warningScene();
                    return;
                }

                LocalDate date = datePicker.getValue();
                expenditures.add(strAmount, date, strDescription);

                populateGUI();

                expenditures.save();

                //clear the textfield after user click add button
                txtAmount.clear();
                txtOther.clear();
                datePicker.setValue(LocalDate.now());

            } catch (Exception nfe) {
                warningScene();
            }

        });

        gPaneInput.add(lblAmount, 0, 0);
        gPaneInput.add(txtAmount, 1, 0);
        gPaneInput.add(lblDate, 2, 0);
        gPaneInput.add(datePicker, 3, 0);
        gPaneInput.add(btnAdd, 4, 0);
        gPaneRadioButtons.add(radFood, 0, 1);
        gPaneRadioButtons.add(radGroceries, 1, 1);
        gPaneRadioButtons.add(radUtilities, 2, 1);
        gPaneRadioButtons.add(radGas, 3, 1);
        gPaneRadioButtons.add(radOther, 4, 1);
        gPaneRadioButtons.add(txtOther, 5, 1);
        txtAmount.requestFocus();
    }

    /**
     * This method set the left Main Pane.
     *
     * @return the set left main pane
     */
    private BorderPane setLeftPane() {
        //create main left pane
        leftMainPane = new BorderPane();

        leftMainPane.setPadding(new Insets(5));

        //top pane
        GridPane titlePane = new GridPane();
        Label lblDate = new Label("Date");
        Label lblAmount = new Label("Amount");
        Label lblDesc = new Label("Description");
        Label lblRef = new Label("Ref");
        titlePane.add(lblDate, 0, 0);
        titlePane.add(lblDesc, 1, 0);
        titlePane.add(lblRef, 2, 0);
        titlePane.add(lblAmount, 3, 0);
        GridPane.setHalignment(lblDate, HPos.CENTER);
        GridPane.setHalignment(lblDesc, HPos.CENTER);
        GridPane.setHalignment(lblAmount, HPos.CENTER);
        GridPane.setHalignment(lblRef, HPos.CENTER);
        titlePane.setGridLinesVisible(true);

        //colomn constraint fro gridpanes
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(18);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(55);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(9);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(18);
        titlePane.getColumnConstraints().addAll(col1, col2, col3, col4);
        gridPaneList.getColumnConstraints().addAll(col1, col2, col3, col4);

        populateGUI();

        setLeftButtons();

        //add scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPaneList);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxWidth(460);
        scrollPane.setMinWidth(460);

        //put everything in main left pane
        leftMainPane.setTop(titlePane);
        leftMainPane.setCenter(scrollPane);

        return leftMainPane;
    }

    /**
     * This method sets the toggle buttons place in the left bottom side of the
     * application which are used to sort the parallel list.And, set the
     * functionalities of each buttons.
     */
    private void setLeftButtons() {

        HBox hBoxBotom = new HBox();
        ToggleGroup tglGroup = new ToggleGroup();
        ToggleButton tglDescription = new ToggleButton("Description");
        tglDescription.setToggleGroup(tglGroup);
        ToggleButton tglDate = new ToggleButton("Date");
        tglDate.setToggleGroup(tglGroup);
        ToggleButton tglAmount = new ToggleButton("Amount");
        tglAmount.setToggleGroup(tglGroup);
        Button btnEdit = new Button("Edit");
        hBoxBotom.getChildren().addAll(tglDescription, tglDate, tglAmount, btnEdit);
        hBoxBotom.setPadding(new Insets(10));
        hBoxBotom.setAlignment(Pos.CENTER);
        hBoxBotom.setSpacing(5);
        leftMainPane.setBottom(hBoxBotom);

        btnEdit.setOnAction(e -> {
            stageEditPopUp();
        });

        tglAmount.setOnAction(f -> {
            expenditures.sortAmount();
            expenditures.save();
            populateGUI();
        });

        tglDate.setOnAction(d -> {
            expenditures.sortDate();
            expenditures.save();
            populateGUI();
        });

        tglDescription.setOnAction(v -> {
            expenditures.sortDescription();
            expenditures.save();
            populateGUI();
        });

    }

    /**
     * This method sets the Edit pop up window which appears when the user enter
     * the Edit button.User can edit his expenditure diary by changing the
     * values of strAmount money, dates,and the description.
     *
     */
    @SuppressWarnings({"unchecked"})
    private void stageEditPopUp() {
        Stage stgEdit = new Stage();
        VBox vBoxEdit = new VBox();
        vBoxEdit.setPadding(new Insets(5));
        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(5));
        HBox hBoxBtm = new HBox();
        hBoxBtm.setPadding(new Insets(5));
        hBoxTop.setSpacing(5);
        hBoxBtm.setSpacing(5);

        Label lblRef = new Label("Reference Number:");
        TextField txtRef = new TextField();
        hBoxTop.getChildren().addAll(lblRef, txtRef);
        hBoxTop.setAlignment(Pos.CENTER);

        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> {
            int indexRef = expenditures.getIndexRef(txtRef.getText());
            if (indexRef == -1) {
                showWarningDNE();
            } else {
                stgEdit.close();
                GridPane editPane = new GridPane();
                editPane.setPadding(new Insets(5));
                editPane.setHgap(5);
                editPane.setVgap(5);
                editPane.setAlignment(Pos.CENTER);
                Label lblDate = new Label("Date");
                Date date = new Date();
                date = new Date(expenditures.getDate(indexRef));

                SimpleDateFormat dfEdit = new SimpleDateFormat("MM/dd/YYYY");
                String dateText = dfEdit.format(date);
                DatePicker datePickerEdit = new DatePicker();
                datePickerEdit.getEditor().setText(dateText);
                datePickerEdit.getEditor().setDisable(true);
                Label lblDescEdit = new Label("Description");
                TextField txtOtherEdit = new TextField();
                txtOtherEdit.setText("Other");
                txtOtherEdit.setDisable(true);
                ObservableList<String> options = FXCollections.observableArrayList(
                        "Food", "Groceries", "Utilities", "Gas", "Other"
                );
                ComboBox<String> cbxDesc = new ComboBox(options);
                String descValue = expenditures.getDescription(indexRef);
                if (descValue.equalsIgnoreCase("Food")
                        || descValue.equalsIgnoreCase("Utilities")
                        || descValue.equalsIgnoreCase("gas")
                        || descValue.equalsIgnoreCase("Groceries")) {
                    cbxDesc.setValue(descValue);
                } else {
                    cbxDesc.setValue("Other");
                    txtOtherEdit.setText(expenditures.getDescription(indexRef));
                    txtOtherEdit.setDisable(false);
                }

                cbxDesc.setOnAction(g -> {
                    if (cbxDesc.getValue().equalsIgnoreCase("other")) {
                        txtOtherEdit.setDisable(false);
                    } else {
                        txtOtherEdit.setDisable(true);
                    }
                });

                Label lblAmountEdit = new Label("Amount $");
                TextField txtAmountEdit = new TextField();
                txtAmountEdit.setText(Long.toString(expenditures.getAmount(indexRef).getDollars())
                        + "." + Long.toString(expenditures.getAmount(indexRef).getCents()));

                editPane.add(lblDate, 0, 0);
                editPane.add(datePickerEdit, 1, 0);
                editPane.add(lblDescEdit, 0, 1);
                editPane.add(cbxDesc, 1, 1);
                editPane.add(txtOtherEdit, 1, 2);
                editPane.add(lblAmountEdit, 0, 3);
                editPane.add(txtAmountEdit, 1, 3);

                //create buttons
                Button btnOkEdit = new Button("Ok");
                Button btnCancelEdit = new Button("Cancel");
                Button btnDeleteEdit = new Button("Delete");

                HBox hBoxForButtonsEdit = new HBox();
                hBoxForButtonsEdit.setSpacing(5);
                hBoxForButtonsEdit.getChildren().addAll(btnOkEdit,
                        btnCancelEdit, btnDeleteEdit);
                hBoxForButtonsEdit.setAlignment(Pos.CENTER);

                //add to vBox
                VBox vBoxEditMain = new VBox();
                vBoxEditMain.setPadding(new Insets(5));
                vBoxEditMain.getChildren().addAll(editPane, hBoxForButtonsEdit);
                vBoxEditMain.setAlignment(Pos.CENTER);

                Scene sceneEdit = new Scene(vBoxEditMain, 300, 200);
                Stage stageEdit = new Stage();
                stageEdit.setResizable(false);

                stageEdit.setTitle("Edit Reference Number " + expenditures.getReference(indexRef));
                stageEdit.setScene(sceneEdit);
                stageEdit.initModality(Modality.APPLICATION_MODAL);
                stageEdit.show();

                //button handler
                btnCancelEdit.setOnAction(s -> stageEdit.close());
                btnOkEdit.setOnAction(l -> {
                    expenditures.setDate(indexRef, datePickerEdit.getValue());

                    String desc = "";
                    if (!(cbxDesc.getValue().equalsIgnoreCase("other"))) {
                        desc = cbxDesc.getValue();
                    } else {
                        desc = txtOtherEdit.getText();
                    }

                    try {

                        expenditures.setDescription(indexRef, desc);
                        expenditures.setMoney(indexRef, txtAmountEdit.getText());

                        populateGUI();
                        expenditures.save();
                        stageEdit.close();
                    } catch (Exception ieo) {
                        warningScene();
                    }
                });

                btnDeleteEdit.setOnAction(g -> {
                    //updating the piechart
                    double oldAmountFromList = expenditures.getAmount(indexRef).getDollars()
                            + expenditures.getAmount(indexRef).getCents();
                    String category = normalizeDescription(expenditures.getDescription(indexRef));

                    subtractItem(category, oldAmountFromList);
                    expenditures.removeAt(indexRef);
                    expenditures.save();
                    populateGUI();

                    stageEdit.close();

                });

            }

        });

        Button btnCancel = new Button("Cancel");
        hBoxBtm.getChildren().addAll(btnOk, btnCancel);
        hBoxBtm.setAlignment(Pos.CENTER);

        vBoxEdit.getChildren().addAll(hBoxTop, hBoxBtm);

        Scene scene2 = new Scene(vBoxEdit);

        stgEdit.setTitle("Edit");
        stgEdit.setScene(scene2);
        stgEdit.setResizable(false);
        stgEdit.initModality(Modality.APPLICATION_MODAL); //focus on this stage
        stgEdit.show();

        btnCancel.setOnAction(e -> stgEdit.close());

    }

    /**
     * This is a show warning method which shows the warning message when the
     * user input a wrong reference number.
     */
    private void showWarningDNE() {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5));
        VBox vbControl = new VBox();
        vbControl.setAlignment(Pos.CENTER);
        vbControl.setSpacing(5);
        Label lblWarn = new Label("The reference number doesn't exist");
        vbControl.getChildren().add(lblWarn);
        Button btnClose = new Button("close");
        vbControl.getChildren().add(btnClose);

        pane.getChildren().add(vbControl);

        Scene sceneDNE = new Scene(pane);
        Stage stgDNE = new Stage();
        stgDNE.setScene(sceneDNE);
        stgDNE.initModality(Modality.APPLICATION_MODAL);
        stgDNE.show();

        btnClose.setOnAction(k -> stgDNE.close());
    }

    /**
     * This is a warning method which shows the warning message to indicates
     * that the user entered the invalid number or didn't enter any number.
     */
    private void warningScene() {
        Label lblWarning = new Label("Please have a positive number");
        VBox vBoxWarning = new VBox();
        Button btnCloseWarning = new Button("Close");

        vBoxWarning.getChildren().addAll(lblWarning, btnCloseWarning);
        vBoxWarning.setPadding(new Insets(5));
        vBoxWarning.setSpacing(5);
        vBoxWarning.setAlignment(Pos.CENTER);

        Scene sceneWarning = new Scene(vBoxWarning);
        Stage stageWarning = new Stage();
        stageWarning.setScene(sceneWarning);
        stageWarning.initModality(Modality.APPLICATION_MODAL);
        stageWarning.show();

        btnCloseWarning.setOnAction(e -> stageWarning.close());
    }

    /**
     * This is a populate method which populate the data to the application when
     * it is called.
     */
    private void populateGUI() {
        gridPaneList.getChildren().clear();
        food = gas = utilities = other = groceries = 0;

        for (int i = 0; i < expenditures.size(); i++) {

            Date date = new Date(expenditures.getDate(i));
            SimpleDateFormat dfPopulate = new SimpleDateFormat("MM/dd/YYYY");
            String dateText = dfPopulate.format(date);
            Label lblCurrentDate = new Label(dateText);
            String category = expenditures.getDescription(i);
            Label lblDescription = new Label(category);

            category = normalizeDescription(category);
            Money amountMoney = expenditures.getAmount(i);
            addItem(category, amountMoney.getDollars() + ((double) amountMoney.getCents()) / 100);

            Label lblAmount = new Label(amountMoney.toString());
            Label lblRef = new Label(expenditures.getReference(i));

            gridPaneList.add(lblCurrentDate, 0, i);
            gridPaneList.add(lblDescription, 1, i);
            gridPaneList.add(lblRef, 2, i);
            gridPaneList.add(lblAmount, 3, i);
            GridPane.setHalignment(lblRef, HPos.CENTER);
            GridPane.setHalignment(lblAmount, HPos.RIGHT);
        }

    }

    /**
     * This method normalize the description.If the parameter matches one of
     * FOOD, GAS, GROCERIES, UTILITIES ignoring the case, then it returns the
     * corresponding normalized value.
     *
     * @param category the category wants to be normalized.
     * @return the normalized string value
     */
    private String normalizeDescription(String category) {
        if (FOOD.equalsIgnoreCase(category)) {
            return FOOD;
        }
        if (GAS.equalsIgnoreCase(category)) {
            return GAS;
        }
        if (GROCERIES.equalsIgnoreCase(category)) {
            return GROCERIES;
        }
        if (UTILITIES.equalsIgnoreCase(category)) {
            return UTILITIES;
        }
        return OTHER;
    }

    /**
     * This method updates the existing value by adding value if category
     * matches.
     *
     * @param category the category which the value is adding to.
     * @param addedValue the value adding to the category
     */
    private void addItem(String category, double addedValue) {
        double totalValue;
        switch (category) {
            case FOOD:
                food += addedValue;
                totalValue = food;
                break;
            case GAS:
                gas += addedValue;
                totalValue = gas;
                break;
            case GROCERIES:
                groceries += addedValue;
                totalValue = groceries;
                break;
            case UTILITIES:
                utilities += addedValue;
                totalValue = utilities;
                break;
            case OTHER:
            default:
                other += addedValue;
                totalValue = other;
                break;
        }
        addData(category, totalValue);
    }

    /**
     * This method updates the existing value by subtracting value if category
     * matches.
     *
     * @param category the category which the value is subtracting to.
     * @param subtractedValue the value subtracting to the category
     */
    private void subtractItem(String category, double subtractedValue) {
        double totalValue;
        switch (category) {
            case FOOD:
                food -= subtractedValue;
                totalValue = food;
                break;
            case GAS:
                gas -= subtractedValue;
                totalValue = gas;
                break;
            case GROCERIES:
                groceries -= subtractedValue;
                totalValue = groceries;
                break;
            case UTILITIES:
                utilities -= subtractedValue;
                totalValue = utilities;
                break;
            case OTHER:
            default:
                other -= subtractedValue;
                totalValue = other;
                break;
        }
        addData(category, totalValue);
    }
}
