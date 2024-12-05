package uk.co.diggidy.advent.of.code.day5.model;

import java.util.List;

public record Update(int value, List<Integer> ordering) implements Comparable<Update> {


    @Override
    public int compareTo(final Update o) {
        if(!this.ordering.contains(o.value) && !o.ordering.contains(this.value)) {
            return 0;
        }
        if(this.ordering.contains(o.value)) {
            return 1;
        }
        return -1;
    }
}
