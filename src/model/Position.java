package model;

import java.time.LocalDate;

public class Position {

    private String place;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    public Position(String place, String description) {
        this.place = place;
        this.description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(int year, int month, int day) {
        startDate = LocalDate.of(year, month, day);
    }

    public void setEndDatend(int year, int month, int day) {
        endDate = LocalDate.of(year, month, day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (place != null ? !place.equals(position.place) : position.place != null) return false;
        if (startDate != null ? !startDate.equals(position.startDate) : position.startDate != null) return false;
        if (endDate != null ? !endDate.equals(position.endDate) : position.endDate != null) return false;
        return description != null ? description.equals(position.description) : position.description == null;

    }

    @Override
    public int hashCode() {
        int result = place != null ? place.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(place);
        sb.append("\n");
        sb.append(startDate);
        sb.append(" - ");
        sb.append(endDate);
        sb.append("   ");
        sb.append(description);
        return sb.toString();
    }
}
