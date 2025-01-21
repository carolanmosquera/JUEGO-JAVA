package org.icesi.animation.model;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryManagerResources {
    private TableView<ObservableList<String>> inventoryTable;
    private ObservableList<ObservableList<String>> data;
    private int maxItems = 6; // Maximum items in inventory.
    private int currentItemCount = 0; // the index of the actual position of the inventory

    public InventoryManagerResources() {
        inventoryTable = new TableView<>();
        data = FXCollections.observableArrayList();

        // it creates tow rows, one for the name and the other of the quantity
        ObservableList<String> namesRow = FXCollections.observableArrayList();
        ObservableList<String> quantitiesRow = FXCollections.observableArrayList();

        // it creates the 6 spaces, like the structures but is not visible
        for (int i = 0; i < maxItems; i++) {
            namesRow.add("");
            quantitiesRow.add("");
        }

        data.addAll(namesRow, quantitiesRow);
        inventoryTable.setItems(data);

        // it creates the 4 columns that are visibles
        for (int i = 0; i < maxItems; i++) {
            // name
            int columnIndex = i;
            TableColumn<ObservableList<String>, String> nameColumn = new TableColumn<>( "----- "+(i + 1)+" -----");
            nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(columnIndex)));
            inventoryTable.getColumns().add(nameColumn);


        }

        inventoryTable.setPrefSize(600, 100); // Ajusta el tamaño de la tabla.
    }

    public boolean addNewItem(String itemName, int quantity) {

        if (currentItemCount >= maxItems) {

            return false;
        }

        // Verifica si el item ya existe en el inventario.
        int existingIndex = searchItemName(itemName);

        if (existingIndex == -1) {
            // Busca una posición vacía en el inventario.
            int emptyIndex = searchItemName(""); // Busca el índice vacío.
            if (emptyIndex != -1) {
                System.out.println("empty index "+emptyIndex);
                // Agrega el nombre y la cantidad en la posición vacía.

                data.get(0).set(emptyIndex, itemName);
                data.get(1).set(emptyIndex, String.valueOf(quantity));
                currentItemCount++; // Incrementa el contador de items.
                inventoryTable.refresh();
                return true;
            } else {
                System.out.println("No hay espacio disponible en el inventario.");
                return false;
            }
        } else {
            // Si el item ya existe, actualiza la cantidad.
            String currentQuantityStr = data.get(1).get(existingIndex);
            if (!currentQuantityStr.isEmpty()) {
                int currentQuantity = Integer.parseInt(currentQuantityStr);
                data.get(1).set(existingIndex, String.valueOf(currentQuantity + quantity));
                inventoryTable.refresh();
            } else {
                System.out.println("Error: cantidad actual está vacía.");
            }
        }

        return true;
    }

    public int searchItemName(String itemName) {
        for (int i = 0; i < data.get(0).size(); i++) {
            String name = data.get(0).get(i); // Busca en la fila de nombres.
            if (name.equals(itemName)) {
                return i; // Retorna el índice del item.
            }
        }
        return -1; // Retorna -1 si no encuentra el item.
    }

    public String selectItemAtIndex(int index) {
        if (index < 0 || index >= maxItems) {
            System.out.println("Índice fuera de rango: " + index);
            return "no"; // Índice no válido, termina el método.
        }

        // Limpia cualquier selección previa.
        inventoryTable.getSelectionModel().clearSelection();

        // Obtiene la columna correspondiente.
        TableColumn<ObservableList<String>, String> column = (TableColumn<ObservableList<String>, String>) inventoryTable.getColumns().get(index);

        // Selecciona la fila 0 (nombres) y enfoca la celda.
        inventoryTable.getSelectionModel().select(0, column);
        inventoryTable.getFocusModel().focus(0, column);

        // Imprime el nombre y la cantidad en la posición seleccionada.
        String itemName = data.get(0).get(index);
        String quantity = data.get(1).get(index);
        System.out.println("Seleccionado: " + itemName + " (" + quantity + ")");

        return itemName;
    }

    public void useItem(String itemName, int usedQuantity){
        int index = searchItemName(itemName);
        if(index!=-1){
            int currentQuantity = Integer.parseInt(data.get(1).get(index)); // Obtener la cantidad actual.
            data.get(1).set(index, String.valueOf(currentQuantity - usedQuantity));
            inventoryTable.refresh();
            int updateQuantity = Integer.parseInt(data.get(1).get(index));
            if(updateQuantity<=0){
                data.get(0).set(index, "");
                currentItemCount--;
                inventoryTable.refresh();
            }
        }
    }

    public int getQuantityItem(String itemName){
        int index = searchItemName(itemName);
        if(index!=-1){
            return Integer.parseInt(data.get(1).get(index));
        }else{
            return 0;
        }
    }

    public TableView<ObservableList<String>> getInventoryTable() {
        return inventoryTable;
    }
}
