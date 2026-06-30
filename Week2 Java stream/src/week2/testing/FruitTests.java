package week2;

import week2.model.Fruit;
import week2.service.FruitStreamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FruitTests {
    public static void main(String[] args) {
        // 1. Instantiate our Stream Service factory
        FruitStreamService streamService = new FruitStreamService();

        // 2. Create a mock inventory list of Fruits
        List<Fruit> inventory = new ArrayList<>();
        inventory.add(new Fruit("Mango", "Yellow", 1800.0, 15));
        inventory.add(new Fruit("Apple", "Red", 1200.0, 25));
        inventory.add(new Fruit("Banana", "Yellow", 600.0, 40));
        inventory.add(new Fruit("Strawberry", "Red", 2500.0, 8));
        inventory.add(new Fruit("Avocado", "Green", 400.0, 50));
        inventory.add(new Fruit("Passion Fruit", "Purple", 1000.0, 30));

        System.out.println("====== 🌟 ORIGINAL INVENTORY 🌟 ======");
        inventory.forEach(fruit -> System.out.println("- " + fruit.getName() + " (" + fruit.getColor() + "): " + fruit.getPrice() + " RWF"));
        System.out.println();

        // 3. Test Pipeline 1: Filter Fruits Cheaper than 1,100 RWF
        double budget = 1100.0;
        List<Fruit> affordableFruits = streamService.getCheaperFruits(inventory, budget);

        System.out.println("====== 🛒 PIPELINE 1: FILTER (Cheaper than " + budget + " RWF) ======");
        affordableFruits.forEach(fruit -> System.out.println("👉 " + fruit.getName() + " - " + fruit.getPrice() + " RWF"));
        System.out.println();

        // 4. Test Pipeline 2: Map Fruits to get only their Names
        List<String> fruitNames = streamService.getFruitNames(inventory);

        System.out.println("====== 📦 PIPELINE 2: MAP (Extracting Names Only) ======");
        System.out.println("Fruit Names Dropdown List: " + fruitNames);
        System.out.println();

        // 5. Test Pipeline 3: Grouping Fruits by Color
        Map<String, List<Fruit>> fruitsByColor = streamService.groupFruitsByColor(inventory);

        System.out.println("====== 🎨 PIPELINE 3: COLLECT (Grouping By Color) ======");
        fruitsByColor.forEach((color, fruitList) -> {
            System.out.println("Color Category [" + color.toUpperCase() + "]:");
            fruitList.forEach(fruit -> System.out.println("  • " + fruit.getName()));
        });
    }
}