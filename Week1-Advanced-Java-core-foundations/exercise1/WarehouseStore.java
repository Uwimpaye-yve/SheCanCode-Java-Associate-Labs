import java.util.ArrayList;
import java.util.List;

// The <T extends Product> ensures ONLY Product or its subclasses can be used here
public class WarehouseStore<T extends Product> {

    // An internal list to hold our generic items safely
    private List<T> storage = new ArrayList<>();

    // Method 1: Add an item to the warehouse
    public void addItem(T item) {
        storage.add(item);
        System.out.println("Successfully added: " + item.getName());
    }

    // Method 2: Remove an item using its unique ID
    // We use an Iterator here to safely remove while looping!
    public void removeItem(String id) {
        java.util.Iterator<T> iterator = storage.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                System.out.println("Successfully removed item with ID: " + id);
                found = true;
                break; // Item found and removed, we can stop looping
            }
        }
        if (!found) {
            System.out.println("Item with ID " + id + " not found in warehouse.");
        }
    }

    // Method 3: Find all items belonging to a specific category
    public List<T> findByCategory(String cat) {
        List<T> results = new ArrayList<>();
        for (T item : storage) {
            // Check if the item's category matches (ignoring capital letters case)
            if (item.getCategory().equalsIgnoreCase(cat)) {
                results.add(item);
            }
        }
        return results;
    }

    // Helper method to display everything inside the warehouse right now
    public void printInventory() {
        System.out.println("\n--- Current Warehouse Inventory List ---");
        for (T item : storage) {
            System.out.println(item);
        }
        System.out.println("----------------------------------------");
    }
}