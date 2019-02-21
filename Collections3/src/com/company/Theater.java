package com.company;

import java.util.*;

public class Theater {

    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();

    //for more on comparators
    //notice that Comparator is an interface
    //https://www.geeksforgeeks.org/comparator-interface-java/
    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        //We can add as many comparators as we want
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    };
    //Not sure why we need a semi-colon above....???
    //Obviously, it's a part of the syntax
    //It's probably something minor that I'm going to understand
    //later on. It's also a simple fix that intelliJ finds
    //Also, I'm still not fully grasping what static means...
    //This is what Tim said:
    //We can use as many comparators as we want, so we could
    //for example use one to light the highest price seats first
    //so, the comparator doesn't have to be static, but it makes
    //it easy to use if we don't ned a class instnace in order
    //to use it
    //When he says it like this, it leads me to believe that
    //what static does is that it allows us to use comparator
    //for example, without the need to place it inside a class.
    //Again, I could be wrong, but hold onto that thought.
    //Tim also inserts a static initilization block as kind of
    // way to bring back something we learned. I still
    //don't understand what that is
    //here was the code:
    //
    //static {
       /* PRICE_ORDER = new Comparator<Seat>() {
            @Override
            public int compare(Seat seat1, Seat seat2) {
                if (seat1.getPrice() < seat2.getPrice()) {
                    return -1;
                } } else if (seat1.getPrice() > seat2.getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }
};*/



    public Theater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;


        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;
                //Adding unique pricing based on seats
                if((row < 'D' ) && (seatNum >= 4 && seatNum <=9)) {
                    price = 14.00;
                } else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }



    public boolean reserveSeat(String seatNumber) {

        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }


    // for testing
    public Collection<Seat> getSeats() {
        return seats;
    }

    //remember that this is an inner class
    public class Seat implements Comparable<Seat> {

        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            //should return a number greater than zero if
            //we should sort greater than the object
            //that's being compared to
            //if the two objects ar equal, the method needs
            //to return zero
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());

        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }




    }






}
