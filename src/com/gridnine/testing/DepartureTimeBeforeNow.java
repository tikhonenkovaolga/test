package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class DepartureTimeBeforeNow implements FlightFilter {
    @Override
    public List<Segment> filter(List<Segment> segments) {
        if (segments.isEmpty()) {
            return segments;
        }

        LocalDateTime today = LocalDateTime.now();
        List<Segment> filteredList = new ArrayList<>();
        for (Segment s : segments) {
            if (!s.getDepartureDate().isBefore(today)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    @Test
    public void testFilter() {

        Segment segment1 = new Segment(LocalDateTime.of(2021, 10, 25, 22, 22), LocalDateTime.of(2021, 10, 30, 23, 22));
        Segment segment2 = new Segment(LocalDateTime.of(2021, 10, 25, 14, 22), LocalDateTime.of(2021, 10, 25, 17, 22));
        Segment segment3 = new Segment(LocalDateTime.of(2021, 10, 30, 20, 22), LocalDateTime.of(2021, 10, 30, 23, 22));

        List<Segment> segmentsUnFiltered = new ArrayList<>();
        segmentsUnFiltered.add(segment1);
        segmentsUnFiltered.add(segment2);
        segmentsUnFiltered.add(segment3);

        List<Segment> expected = new ArrayList<>();
        expected.add(segment3);

        assertEquals("my message about error", expected, new DepartureTimeBeforeNow().filter(segmentsUnFiltered));
    }
}







