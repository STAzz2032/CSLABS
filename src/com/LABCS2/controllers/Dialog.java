package com.LABCS2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.LABCS2.data.CustomItem;
import com.LABCS2.util.SerializationDeserializationUtil;

import java.util.List;

public class Dialog {
    @FXML
    TextField fileName;

    private static List<CustomItem> currentItems;

    public static void setItems(List<CustomItem> items){
        currentItems = items;
    }

    public void saveFile(){
        SerializationDeserializationUtil.serialize(currentItems, fileName.getText());
    }
}
