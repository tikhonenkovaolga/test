package com.gridnine.testing;

import java.util.List;

interface FlightFilter {

    List<Segment> filter(List<Segment> segments);
}
