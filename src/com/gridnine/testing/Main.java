package com.gridnine.testing;

import java.util.List;

 class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        DepartureTimeBeforeNow departureTimeBeforeNow = new DepartureTimeBeforeNow();

        System.out.println("Filter by departure before now :");
        for (Flight f : flights) {
            System.out.println(departureTimeBeforeNow.filter(f.getSegments()));
        }

        DateArriveBeforeDateDepartureSegments dateArriveBeforeDateDepartureSegments = new DateArriveBeforeDateDepartureSegments();
        System.out.println("Filter by date arrive before date departure :");

        for (Flight f : flights) {
            System.out.println(dateArriveBeforeDateDepartureSegments.filter(f.getSegments()));
        }

        WaitingTimeMoreTwoHours waitingTimeMoreTwoHours = new WaitingTimeMoreTwoHours();
        System.out.println("Filter by waiting time more two hours :");

        for (Flight f : flights) {
            System.out.println(waitingTimeMoreTwoHours.filter(f.getSegments()));
        }


    }
}
