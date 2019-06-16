package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {

    private Link place;

    private List<Position> listPosition = new ArrayList<>();

    public Organization(String title, String url) {
        this.place = new Link(title, url);
    }

    public void addNoteToPosition(LocalDate startDate, LocalDate endDate, String description) {
        listPosition.add(new Position(startDate, endDate, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization organization = (Organization) o;

        if (!place.equals(organization.place)) return false;
        return listPosition.equals(organization.listPosition);

    }

    @Override
    public int hashCode() {
        int result = place.hashCode();
        result = 31 * result + listPosition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(place);
        sb.append("\n");
        for (Position note : listPosition) {
            sb.append(note);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class Position {
        private LocalDate startDate;

        private LocalDate endDate;

        private String description;

        public Position(LocalDate startDate, LocalDate endDate, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position that = (Position) o;

            if (!startDate.equals(that.startDate)) return false;
            if (!endDate.equals(that.endDate)) return false;
            return description != null ? description.equals(that.description) : that.description == null;

        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return startDate + " : " + endDate + " - " + description;
        }
    }
}
