//solving backend problem filtering based on condition, we use stream pipeline instead of writing for loops
package week2.service;

import week2.model.Fruit;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class FruitStreamService{
    public List<Fruit> getCheaperFruits(List<Fruit> fruits, double maxBudget){
        return fruits.stream()
                .filter(fruit -> fruit.getPrice() < maxBudget)
                .collect(Collectors.toList());

    }

    public List<String> getFruitNames(List<Fruit> fruits){
        return fruits.stream()
                .map(fruit -> fruit.getName())
                .collect(Collectors.toList());
    }

    public Map<String, List<Fruit>> groupFruitsByColor(List<Fruit> fruits){
        return fruits.stream()
                .collect(Collectors.groupingBy(Fruit::getColor));
    }



}