package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class WaitingTimeMoreTwoHours implements FlightFilter {
    @Override
    public List<Segment> filter(List<Segment> segments) {
        if (segments.isEmpty() || segments.size() == 1){
            return segments;
        }

        List<Segment> filteredList = new ArrayList<>();
        for (int i = 0; i < segments.size() - 1; i++) {

            LocalDateTime arrive = segments.get(i).getArrivalDate();
            LocalDateTime departure = segments.get(i + 1).getDepartureDate();

            long hours = ChronoUnit.HOURS.between(arrive, departure);
            if (hours <= 2) {
                filteredList.add(segments.get(i));
            }
        }
        filteredList.add(segments.get(segments.size() - 1));
        return filteredList;
    }

    @Test
    public void testFilter(){

        Segment segment1 = new Segment(LocalDateTime.of(2021, 10,30, 14, 22), LocalDateTime.of(2021, 10, 30, 15,22));
        Segment segment2 = new Segment(LocalDateTime.of(2021, 10,30, 18, 22), LocalDateTime.of(2021, 10, 30, 19,22));
        Segment segment3 = new Segment(LocalDateTime.of(2021, 10,30, 20, 22), LocalDateTime.of(2021, 10, 30, 22,22));

        List<Segment> segmentsUnFiltered = new ArrayList<>();
        segmentsUnFiltered.add(segment1);
        segmentsUnFiltered.add(segment2);
        segmentsUnFiltered.add(segment3);

        List<Segment> expected = new ArrayList<>();
        expected.add(segment2);
        expected.add(segment3);


        assertEquals("my message about error", expected, new WaitingTimeMoreTwoHours().filter(segmentsUnFiltered));
    }


}
