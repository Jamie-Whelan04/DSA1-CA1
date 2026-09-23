package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;

import java.util.Optional;

public class MenuController {

    private Supermarket supermarket = new Supermarket();

    @FXML
    private ListView<String> floorAreaList;
    @FXML
    private ListView<String> aisleList;
    @FXML
    private ListView<String> shelfList;
    @FXML
    private ListView<String> goodsList;

    private String ask(String text) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(text);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void info(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.show();
    }



    @FXML
    private void initialize() {

        floorAreaList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateAisles(newVal));
        aisleList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateShelves(newVal));
        shelfList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateGoods(newVal));
    }

    private void refreshFloorAreas() {
        floorAreaList.getItems().clear();
        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            floorAreaList.getItems().add(supermarket.getFloorAreas().get(i).getFloorName());
        }
        aisleList.getItems().clear();
        shelfList.getItems().clear();
        goodsList.getItems().clear();
    }

    private void updateAisles(String floorName) {
        aisleList.getItems().clear();
        shelfList.getItems().clear();
        goodsList.getItems().clear();

        if (floorName == null) return;

        FloorArea fa = findFloorArea(floorName);
        if (fa != null) {
            for (int i = 0; i < fa.getAisles().size(); i++) {
                aisleList.getItems().add(fa.getAisles().get(i).getAisleName());
            }
        }
    }

    private void updateShelves(String aisleName) {
        shelfList.getItems().clear();
        goodsList.getItems().clear();

        if (aisleName == null) return;

        Aisle aisle = findAisle(aisleName);
        if (aisle != null) {
            for (int i = 0; i < aisle.getShelves().size(); i++) {
                shelfList.getItems().add(aisle.getShelves().get(i).getShelfName());
            }
        }
    }

    private void updateGoods(String shelfName) {
        goodsList.getItems().clear();
        if (shelfName == null) return;

        Shelf shelf = findShelf(shelfName);
        if (shelf != null) {
            for (int i = 0; i < shelf.getItems().size(); i++) {
                goodsList.getItems().add(shelf.getItems().get(i).getName());
            }
        }
    }

    private FloorArea findFloorArea(String name) {
        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            if (fa.getFloorName().equalsIgnoreCase(name)) return fa;
        }
        return null;
    }

    private Aisle findAisle(String name) {
        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            for (int j = 0; j < fa.getAisles().size(); j++) {
                Aisle a = fa.getAisles().get(j);
                if (a.getAisleName().equalsIgnoreCase(name)) return a;
            }
        }
        return null;
    }

    private Shelf findShelf(String name) {
        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            for (int j = 0; j < fa.getAisles().size(); j++) {
                Aisle a = fa.getAisles().get(j);
                for (int k = 0; k < a.getShelves().size(); k++) {
                    Shelf s = a.getShelves().get(k);
                    if (s.getShelfName().equalsIgnoreCase(name)) return s;
                }
            }
        }
        return null;
    }

    @FXML
    private void onAddFloorArea() {
        String name = ask("Enter Floor Area Name:");
        if (name == null) return;

        String level = ask("Enter Floor Level:");
        if (level == null) return;

        supermarket.addFloorArea(new FloorArea(name, level));
        info("Floor Area added.");
        refreshFloorAreas();
    }

    @FXML
    private void onAddAisle() {
        String floorName = floorAreaList.getSelectionModel().getSelectedItem();
        if (floorName == null) {
            info("Select a Floor Area first.");
            return;
        }

        FloorArea fa = findFloorArea(floorName);

        String aisleName = ask("Enter Aisle Name:");
        if (aisleName == null) return;

        if (supermarket.aisleExists(aisleName)) {
            info("Error: An aisle with that name already exists!");
            return;
        }

        fa.getAisles().add(new Aisle(aisleName));
        info("Aisle added.");
        updateAisles(floorName);
    }

    @FXML
    private void onAddShelf() {
        String aisleName = aisleList.getSelectionModel().getSelectedItem();
        if (aisleName == null) {
            info("Select an Aisle first.");
            return;
        }

        Aisle aisle = findAisle(aisleName);

        String shelfName = ask("Enter Shelf Name:");
        if (shelfName == null) return;

        aisle.getShelves().add(new Shelf(shelfName));
        info("Shelf added.");
        updateShelves(aisleName);
    }

    @FXML
    private void onAddGoods() {
        String shelfName = shelfList.getSelectionModel().getSelectedItem();
        if (shelfName == null) {
            info("Select a Shelf first.");
            return;
        }

        Shelf shelf = findShelf(shelfName);

        String name = ask("Enter Goods Name:");
        if (name == null) return;
        int qty = Integer.parseInt(ask("Enter Quantity:"));
        double price = Double.parseDouble(ask("Enter Unit Price:"));
        String temp = ask("Enter Temperature:");
        double size = Double.parseDouble(ask("Enter Size/Weight:"));
        String url = ask("Enter Photo URL:");

        Goods g = new Goods(name, qty, price, temp, size, url);
        shelf.getItems().add(g);
        info("Goods added successfully!");
        updateGoods(shelfName);
    }

    @FXML
    public void viewStock() {
        StringBuilder sb = new StringBuilder();
        double supermarketTotal = 0;

        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            double floorTotal = 0;
            sb.append("Floor Area: ").append(fa.getFloorName()).append("\n");

            for (int j = 0; j < fa.getAisles().size(); j++) {
                Aisle aisle = fa.getAisles().get(j);
                double aisleTotal = 0;
                sb.append("  Aisle: ").append(aisle.getAisleName()).append("\n");

                for (int k = 0; k < aisle.getShelves().size(); k++) {
                    Shelf shelf = aisle.getShelves().get(k);
                    double shelfTotal = 0;
                    sb.append("    Shelf: ").append(shelf.getShelfName()).append("\n");

                    for (int l = 0; l < shelf.getItems().size(); l++) {
                        Goods g = shelf.getItems().get(l);
                        double totalPrice = g.totalPrice();
                        sb.append("      • ").append(g.getName())
                                .append(" | Qty: ").append(g.getQuantity())
                                .append(" | Unit: €").append(String.format("%.2f", g.getPrice()))
                                .append(" | Total: €").append(String.format("%.2f", totalPrice))
                                .append("\n");
                        shelfTotal += totalPrice;
                    }

                    sb.append("    Shelf Total: €").append(String.format("%.2f", shelfTotal)).append("\n");
                    aisleTotal += shelfTotal;
                }

                sb.append("  Aisle Total: €").append(String.format("%.2f", aisleTotal)).append("\n");
                floorTotal += aisleTotal;
            }

            sb.append("Floor Area Total: €").append(String.format("%.2f", floorTotal)).append("\n\n");
            supermarketTotal += floorTotal;
        }

        sb.append("Supermarket Total: €").append(String.format("%.2f", supermarketTotal)).append("\n");


        TextArea area = new TextArea(sb.toString());
        area.setEditable(false);
        area.setWrapText(true);
        area.setPrefWidth(500);
        area.setPrefHeight(600);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Stock");
        alert.setHeaderText("Complete Supermarket Stock");
        alert.getDialogPane().setContent(area);
        alert.showAndWait();
    }

    @FXML
    private void onSearchGoods() {
        String keyword = ask("Enter search keyword:");
        if (keyword == null) return;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            for (int j = 0; j < fa.getAisles().size(); j++) {
                Aisle aisle = fa.getAisles().get(j);
                for (int k = 0; k < aisle.getShelves().size(); k++) {
                    Shelf shelf = aisle.getShelves().get(k);
                    for (int m = 0; m < shelf.getItems().size(); m++) {
                        Goods g = shelf.getItems().get(m);
                        if (g.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            result.append("Found: ")
                                    .append(g)
                                    .append("\nLocation → Floor: ")
                                    .append(fa.getFloorName())
                                    .append(" | Aisle: ")
                                    .append(aisle.getAisleName())
                                    .append(" | Shelf: ")
                                    .append(shelf.getShelfName())
                                    .append("\n\n");
                        }
                    }
                }
            }
        }

        info(result.length() == 0 ? "No goods found." : result.toString());
    }

    @FXML
    private void onTotalValue() {
        double total = 0;

        for (int i = 0; i < supermarket.getFloorAreas().size(); i++) {
            FloorArea fa = supermarket.getFloorAreas().get(i);
            for (int j = 0; j < fa.getAisles().size(); j++) {
                Aisle aisle = fa.getAisles().get(j);
                for (int k = 0; k < aisle.getShelves().size(); k++) {
                    Shelf shelf = aisle.getShelves().get(k);
                    for (int m = 0; m < shelf.getItems().size(); m++) {
                        total += shelf.getItems().get(m).totalPrice();
                    }
                }
            }
        }

        info("Total supermarket value: €" + total);
    }


    @FXML
    private void onRemoveItem() {
        String shelfName = shelfList.getSelectionModel().getSelectedItem();
        String goodsName = goodsList.getSelectionModel().getSelectedItem();
        if (goodsName != null && shelfName != null) {
            Shelf shelf = findShelf(shelfName);
            Goods toRemove = null;
            for (int i = 0; i < shelf.getItems().size(); i++) {
                Goods g = shelf.getItems().get(i);
                if (g.getName().equalsIgnoreCase(goodsName)) {
                    toRemove = g;
                    break;
                }
            }
            if (toRemove != null) {
                shelf.getItems().remove(toRemove);
                info("Goods removed.");
                updateGoods(shelfName);
                return;
            }
        }

        // Removes selected shelf
        if (shelfName != null) {
            Aisle aisle = findAisle(aisleList.getSelectionModel().getSelectedItem());
            Shelf toRemove = null;
            for (int i = 0; i < aisle.getShelves().size(); i++) {
                Shelf s = aisle.getShelves().get(i);
                if (s.getShelfName().equalsIgnoreCase(shelfName)) {
                    toRemove = s;
                    break;
                }
            }
            if (toRemove != null) {
                aisle.getShelves().remove(toRemove);
                info("Shelf removed.");
                updateShelves(aisle.getAisleName());
                return;
            }
        }

        // Removes selected aisle
        String aisleName = aisleList.getSelectionModel().getSelectedItem();
        if (aisleName != null) {
            FloorArea fa = findFloorArea(floorAreaList.getSelectionModel().getSelectedItem());
            Aisle toRemove = null;
            for (int i = 0; i < fa.getAisles().size(); i++) {
                Aisle a = fa.getAisles().get(i);
                if (a.getAisleName().equalsIgnoreCase(aisleName)) {
                    toRemove = a;
                    break;
                }
            }
            if (toRemove != null) {
                fa.getAisles().remove(toRemove);
                info("Aisle removed.");
                updateAisles(fa.getFloorName());
                return;
            }
        }

        // Removes selected floor area
        String floorName = floorAreaList.getSelectionModel().getSelectedItem();
        if (floorName != null) {
            FloorArea toRemove = findFloorArea(floorName);
            if (toRemove != null) {
                supermarket.getFloorAreas().remove(toRemove);
                info("Floor Area removed.");
                refreshFloorAreas();
                return;
            }
        }

        info("No item selected to remove.");
    }

    @FXML
    private void onReset() {
        supermarket.getFloorAreas().clear();
        refreshFloorAreas();
        info("Supermarket reset.");
    }

    @FXML
    private void onExit() {
        System.exit(0);
    }
}
