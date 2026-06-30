package week2;

//import week2.testing.FruitTests;
import week2.testing.EcommerceTests;
import week2.testing.PatternTests;

public class Main {
    public static void main(String[] args) {

        PatternTests.runPatternTests();

        EcommerceTests.runPerformanceBenchmark();
        PatternTests.runPatternTests();
        // FruitTests.runAllFruitTests();

//        EcommerceTests.runAllEcommerceTests();
    }
}