package serialization;

import loja.Item;

public class ItemManager extends SerializationManager<Item> {

    private static Integer databaseAmount = 1;

    public ItemManager(String filepath) {
        super(filepath, "items-" + databaseAmount.toString());
        ItemManager.databaseAmount++;
    }
}
