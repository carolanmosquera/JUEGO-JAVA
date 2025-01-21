package org.icesi.animation.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryManagerTool {

    private TableView<InventoryTool> inventoryTable;
    private double heigthTable;
    private ObservableList<InventoryTool> data;

    public InventoryManagerTool() {
        this.heigthTable = 100;
        inventoryTable = new TableView<>();

        //It is important to specify that the type of object corresponds to the one indicated and its format

        //create columns
        TableColumn<InventoryTool, String> nameColumn = new TableColumn<>("Item");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<InventoryTool, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<InventoryTool, Double> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<InventoryTool, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Agregar columnas a la tabla
        inventoryTable.getColumns().addAll(nameColumn, quantityColumn, durationColumn, descriptionColumn);


        // Crear datos iniciales
        this.data = FXCollections.observableArrayList(
                new InventoryTool("Magic_Weapon", 1, 0, "The great power inside you")

        );

        inventoryTable.setItems(data);
        inventoryTable.setPrefSize(400, heigthTable); // Tamaño de la tabla

    }

    public String selectItemIndex(int itemIndex) {
        // Selecciona el elemento en el índice dado
        inventoryTable.getSelectionModel().select(itemIndex);
        inventoryTable.scrollTo(itemIndex);

        // Obtener el elemento seleccionado
        InventoryTool selectedItem = inventoryTable.getSelectionModel().getSelectedItem();

        // Verificar si hay un elemento seleccionado y devolver el itemName
        if (selectedItem != null) {
            return selectedItem.getItemName();
        } else {
            return "No hay un elemento seleccionado";
        }

    }

    public int searchItemName(String itemName) {
        //a table is like a list
        int wantedIndex = 0;
        for (int i = 0; i < inventoryTable.getItems().size(); i++) {
            if (inventoryTable.getItems().get(i).getItemName().equals(itemName)) {
                wantedIndex = i;
            }
        }
        return wantedIndex;
    }

    public void updateInventory(InventoryTool inventoryTool) {
        //the information of a specific colum update by the searching method and the source that use to
        //update is the inventory Item that is given, each time that the inventory Iteam has a change, is
        //take it to this method.
        data.set(searchItemName(inventoryTool.getItemName()), inventoryTool);
    }

    public TableView<InventoryTool> getInventoryTable() {
        return inventoryTable;
    }

    public void addAxeInventory(InventoryTool herramienta){
        data.add(herramienta);
    }

    public void addPeakInventory(InventoryTool herramienta){
        data.add(herramienta);
    }

    public void addSickleInventory(InventoryTool herramienta){
        data.add(herramienta);

    }



}