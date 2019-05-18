package model;

import java.time.LocalDate;

public class Position {

    private Link place;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    public Position(String title, String url, String description) {
        this.place = new Link(title, url);
        this.description = description;
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

        if (!place.equals(position.place)) return false;
        if (!startDate.equals(position.startDate)) return false;
        if (!endDate.equals(position.endDate)) return false;
        return description != null ? description.equals(position.description) : position.description == null;

    }

    @Override
    public int hashCode() {
        int result = place.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
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
