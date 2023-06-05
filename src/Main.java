import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class Main {

  public static void main(String[] args) {
    int[] salesInCurrentMonth = new int[0];
    int[] salesInLastMonth = new int[16];
    Random random = new Random();
    Logger logger = Logger.getLogger("Sales Manager Homework");

    for(int n = 0; n < salesInLastMonth.length; n++) {
      salesInLastMonth[n] = random.nextInt(100);
    }
    SalesManager managerCurrentMonth = new SalesManager(salesInCurrentMonth);
    SalesManager managerLastMonth = new SalesManager(salesInLastMonth);

    logger.info("Продажи в данном месяце: " + Arrays.toString(salesInCurrentMonth));
    logger.info("Максимальная цена продажи в данном месяце была: " + managerCurrentMonth.max());

    logger.info("Продажи в последнем месяце: " + Arrays.toString(salesInLastMonth));
    logger.info("Максимальная цена продажи в последнем месяце была: " + managerLastMonth.max());
  }
}