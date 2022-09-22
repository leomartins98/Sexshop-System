package serialization;

import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;

import java.util.ArrayList;

public final class SerializationManager<T extends SerializableID> {

    private ArrayList<T> objects;
    private String databaseName;
    private String filepath;

    public SerializationManager(String filepath, String databaseName) {
        this.objects = new ArrayList<T>();
        this.databaseName = databaseName;
        this.filepath = filepath;

        this.initialize();
    }

    public SerializationManager(String filepath) {
        this.objects = new ArrayList<T>();
        this.databaseName = "default";
        this.filepath = filepath;

        this.initialize();
    }

    // Initializers:
    private void initialize() {
        File fileCheck = new File(this.filepath);
        if(fileCheck.isFile())
            this.objects = read();
        else {
            try {
                if(fileCheck.createNewFile())
                    System.out.println("Initialized a new default object file for the " + databaseName + " database.");
            } catch (IOException e) {
                System.out.println("An error occurred while initializing the " + databaseName + " database.");
            }
        }
    }

    // Getters & Setters:
    public ArrayList<T> get() {
        return this.objects;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return this.filepath;
    }

    // Memory management methods:
    public void add(T object) {
        this.objects.add(object);
    }

    public T find(int ID) {
        for (var object : this.objects) {
            if (object.getID() == ID)
                return object;
        }

        return null;
    }

    public void update(int ID, T newObject) {
        int index = 0;
        for (var object : this.objects) {
            if (object.ID == ID) {
                this.objects.set(index, newObject);
                return;
            }
            index++;
        }

        System.out.println("Unable to update object in the " + databaseName + " database.");
    }

    // File management:
    public void save() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(this.filepath);
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);

            output.writeObject(this.objects);
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> read() {
        ArrayList<T> results = new ArrayList<T>();

        try {
            FileInputStream fileInput = new FileInputStream(this.filepath);
            ObjectInputStream input = new ObjectInputStream(fileInput);

            results = (ArrayList<T>) input.readObject();

            input.close();
            fileInput.close();
        }
        catch (EOFException e) {
            System.out.println("The " + databaseName + " database is empty.");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } 

        return results;
    }
}
