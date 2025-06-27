import java.io.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.util.*;

public class TheaterManagementSystem {


    public static ArrayList<Seat> loadSeatsFromFile(){
        ArrayList<Seat> seats = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/Seats.txt")))){
            String line;
            while((line = reader.readLine()) != null) seats.add(Seat.fromFileString(line));
        }catch(Exception e){e.printStackTrace();}
        return seats;
    }

    public static void saveSeatsToFile(String seatNumber){
        List<Seat> seats = loadSeatsFromFile();

        Random ran = new Random();
        boolean randomAvailability = ran.nextBoolean();
        String newLine = seatNumber + ", " + randomAvailability;

        boolean seatAlreadyTaken = false;
        List<String> updatedLines = new ArrayList<>();
        for(Seat seat : seats){
            if(seat.getSeatNumber().equals(seatNumber)){
                seatAlreadyTaken = true;
                //updatedLines.add(newLine);
            }else{
                updatedLines.add(seat.toFileString());
            }
        }

        if(!seatAlreadyTaken){updatedLines.add(newLine);}

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/Seats.txt")))){
            for(String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        }catch(Exception e){ e.printStackTrace();}
    }

    public static void main(String[] args){
        Theater theater = new Theater();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        /*We would submit 3 customer objects to executorService as: executorService.submit(customer);
        and then do executorService.shutdown();
         */

        //Usage
        theater.bookSeat("A");

        System.out.println("Seat statuses:");
        for(Seat seat : loadSeatsFromFile()) System.out.println(seat.toFileString());
        System.out.println();
        saveSeatsToFile("1000");
        System.out.println("Seat statuses:");
        for(Seat seat : loadSeatsFromFile()) System.out.println(seat.toFileString());
    }
}
