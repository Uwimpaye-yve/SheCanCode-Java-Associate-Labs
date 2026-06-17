import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OrderBook centralExchange = new OrderBook();

        // Create a fixed thread pool of 5 workers
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit tasks to the executor instead of manual 'new Thread()'
        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                Order order = new Order("ID-" + System.currentTimeMillis(), "BK", Order.Type.BUY, 100, 150.00);
                centralExchange.addOrder(order);
                centralExchange.matchOrders();
            });
        }

        // Shut down the executor after tasks are done
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("All tasks finished via ExecutorService.");
    }
}