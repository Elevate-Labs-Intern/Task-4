import java.util.concurrent.locks.*;

public class Customer implements Runnable{
    String seatNumber;
    Lock lock;


    Customer(String seatNumber){
        this.seatNumber = seatNumber;
        this.lock = new ReentrantLock();
    }


    @Override
    public void run() {
        for(int i = 0; i < 10; i++){//Simulating 10 customers
            lock.lock();
            lock.unlock();
        }
    }
}
