public class MultiThreatLearning {
  // For thread main, "main start" will run first and "main end" will run later
  // For thread t, "main start..." will run first and "main end..." will run later
  // The main end is printed before the thread run, after the thread end, or in between, it is
  // impossible to determine
  // So I added Thread.sleep();
  public static void main(String[] args) {
    System.out.println("main start");
    Thread t =
        new Thread() {
          public void run() {
            System.out.println("thread run...");
            try {
              Thread.sleep(1);
              //                    Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("thread end...");
          }
        };
    t.start();
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
    }
    System.out.println("main end");
  }
}
