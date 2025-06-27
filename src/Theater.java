import java.util.ArrayList;
import java.util.concurrent.locks.*;

class SeatAlreadyBookedException extends Exception{
    SeatAlreadyBookedException(){}

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return "This seat is already booked";
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}

public class Theater {
    private ArrayList<Seat> seats;
    private Lock seatLock;


    Theater(){
        seats = TheaterManagementSystem.loadSeatsFromFile();
        seatLock = new ReentrantLock();
    }


    public synchronized String bookSeat(String seatNumber){
        for(Seat seat : this.seats){
            if(!seat.isBooked() && seat.getSeatNumber().equals(seatNumber)){
                seatLock.lock();
                seat.setBooked(true);
                seatLock.unlock();
                System.out.println("Seat " + seatNumber + " has been booked for you");
                return seatNumber;
            }else if(seat.isBooked() && seat.getSeatNumber().equals(seatNumber)){
                try{
                    throw new SeatAlreadyBookedException();
                }catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;//Not able to book the seat
    }
}
