package com.radu.util;

import com.radu.data.CustomItem;

import java.io.*;
import java.util.List;

public class SerializationDeserializationUtil {
    public static void serialize(List<CustomItem> obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/com/radu/storage/audit.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Audit items saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream("src/com/radu/storage/audit.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println("Audit items: \n" + in.readObject().toString());
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
