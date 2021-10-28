package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class DateArriveBeforeDateDepartureSegments implements FlightFilter {
    @Override
    public List<Segment> filter(List<Segment> segments) {
        if (segments.isEmpty()){
            return segments;
        }
        List<Segment> filteredList = new ArrayList<>();
        for (Segment s : segments) {
            if (!s.getArrivalDate().isBefore(s.getDepartureDate())) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    @Test
    public void testFilter(){

        Segment segment1 = new Segment(LocalDateTime.of(2021, 10,30, 22, 22), LocalDateTime.of(2021, 10, 30, 20,22));
        Segment segment2 = new Segment(LocalDateTime.of(2021, 10,25, 14, 22), LocalDateTime.of(2021, 10, 25, 17,22));
        Segment segment3 = new Segment(LocalDateTime.of(2021, 10,30, 20, 22), LocalDateTime.of(2021, 10, 30, 15,22));

        List<Segment> segmentsUnFiltered = new ArrayList<>();
        segmentsUnFiltered.add(segment1);
        segmentsUnFiltered.add(segment2);
        segmentsUnFiltered.add(segment3);

        List<Segment> expected = new ArrayList<>();
        expected.add(segment2);


        assertEquals("my message about error", expected, new DateArriveBeforeDateDepartureSegments().filter(segmentsUnFiltered));
    }
}
