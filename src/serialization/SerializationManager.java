package serialization;

import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;

import java.util.ArrayList;

public class SerializationManager<T extends Serializable> {

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

    public T find(String fieldName, String fieldValue) {
        for(var object : this.objects) {
            try {
                var field = object.getClass().getField(fieldName);

                if(field.get(object).toString().equals(fieldValue)) {
                    return object;
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    public void update(String fieldName, String fieldValue, T newObject) {
        var index = 0;
        for(var object : this.objects) {
            try {
                var field = object.getClass().getField(fieldName);
                
                if(field.get(object).equals(fieldValue)) {
                    this.objects.set(index, newObject);
                    return;
                }

                index++;

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
        
        System.out.println("Unable to update object in the " + databaseName + " database.");
    }

    public void update(Field fieldName, String fieldValue, T newObject) {
        var index = 0;
        for(var object : this.objects) {
            try {
                var field = object.getClass().getField(fieldName);
                
                if(field.get(object).equals(fieldValue)) {
                    this.objects.set(index, newObject);
                    return;
                }

                index++;

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
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
