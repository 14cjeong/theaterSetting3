package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater("Olympian", 8, 12);

        if(theater.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Sorry, seat is taken");
        }

        if(theater.reserveSeat("B13")) {
            System.out.println("Please pay for B13");
        } else {
            System.out.println("Seat already reserved");
        }

        List<Theater.Seat> reverseSeats = new ArrayList<>(theater.getSeats());
        Collections.reverse(reverseSeats);
        printList(reverseSeats);

        //gets a copy of our elements
        List<Theater.Seat> priceSeats = new ArrayList<>(theater.getSeats());
        //another example of instantiating an inner class
        priceSeats.add(theater.new Seat("B00", 13.00));
        priceSeats.add(theater.new Seat("A00", 13.00));
        //This is sorting based on price
        Collections.sort(priceSeats, Theater.PRICE_ORDER);
        printList(priceSeats);


    }


public static void printList(List<Theater.Seat>list) {
        for(Theater.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
        }

    System.out.println();
    System.out.println("===================================");
}




}
