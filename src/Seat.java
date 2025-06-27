public class Seat {
    private String seatNumber;
    private boolean isBooked;


    public String getSeatNumber(){return this.seatNumber;}
    public boolean isBooked(){return this.isBooked;}
    public void setBooked(boolean booked){this.isBooked = booked;}


    Seat(String seatNumber, boolean isBooked){
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }


    //File I/O

    public String toFileString(){//seatNumber, isBooked
        return this.getSeatNumber() + ", " + this.isBooked;
    }

    public static Seat fromFileString(String string){
        String[] info = string.split(", ");
        String seatNum = info[0];
        boolean isAvai = Boolean.parseBoolean(info[1]);
        return new Seat(seatNum, isAvai);
    }
}
