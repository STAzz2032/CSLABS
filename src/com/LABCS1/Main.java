package com.radu;

import com.radu.data.CustomItem;
import com.radu.util.SerializationDeserializationUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "MSCT_Windows_10_20H2_v1.0.0.audit";
        String content = readContent(fileName);
        parseContent(content);

        List<String> elements = parseContent(content);

        List<CustomItem> items = new ArrayList<>();

        for (String element : elements) {
            items.add(convertToCustomItemObject(element));
        }

        SerializationDeserializationUtil.serialize(items);
        SerializationDeserializationUtil.deserialize();
    }

    public static String readContent(String fileName) throws IOException {
        Path filePath = Paths.get("src/com/radu/input/" + fileName);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }

    public static List<String> parseContent(String content) {
        List<String> customItemsContent = new ArrayList<>();

        String foundItem = "";

        for (String tag : content.split("\\s+")) {
            if (tag.equals("<custom_item>")) {
                foundItem = "";
            } else if (tag.equals("</custom_item>")) {
                customItemsContent.add(foundItem);
            } else {
                foundItem = foundItem.concat(" " + tag);
            }
        }

        return customItemsContent;
    }

    public static CustomItem convertToCustomItemObject(String element) {
        CustomItem item = new CustomItem();

        List<String> fields = Arrays.asList("type", "description", "info", "solution", "see_also", "value_type",
                "value_data", "reg_key", "reg_item", "reg_option", "reference", "PolicySettingName");

        String currentField = "";
        String currentValue = "";

        element = element.replaceFirst("Policy Setting Name", "PolicySettingName");

        boolean firstType = true;

        for (String word : element.split("\\s+")) {
            if (fields.contains(word)) {
                String info = currentValue.replaceFirst(":", "").replaceAll("\"", "")
                        .trim().replaceAll(" +", " ");
                switch (currentField) {
                    case "type": {
                        if(firstType){
                            item.setType(info);
                            firstType = false;
                        }
                    }
                    case "description": {
                        item.setDescription(info);
                    }
                    case "info": {
                        item.setInfo(info);
                    }
                    case "solution": {
                        item.setSolution(info);
                    }
                    case "see_also": {
                        item.setSeeAlso(info);
                    }
                    case "value_type": {
                        item.setValueType(info);
                    }
                    case "value_data": {
                        item.setValueData(info);
                    }
                    case "reg_key": {
                        item.setRegKey(info);
                    }
                    case "reg_item": {
                        item.setRegItem(info);
                    }
                    case "reg_option": {
                        item.setRegOption(info);
                    }
                    case "reference": {
                        item.setReference(info);
                    }
                    case "PolicySettingName": {
                        item.setPolicySettingName(info);
                    }
                }
                currentValue = "";
                currentField = word;
            } else {
                currentValue = currentValue.concat(" " + word);
            }
        }

        return item;
    }
}
