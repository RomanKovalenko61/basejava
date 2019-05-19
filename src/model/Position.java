package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Position {

    private Link place;

    private List<NotePosition> listNotePosition = new ArrayList<>();

    public Position(String title, String url) {
        this.place = new Link(title, url);
    }

    public void addNoteToPosition(LocalDate startDate, LocalDate endDate, String description) {
        listNotePosition.add(new NotePosition(startDate, endDate, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!place.equals(position.place)) return false;
        return listNotePosition.equals(position.listNotePosition);

    }

    @Override
    public int hashCode() {
        int result = place.hashCode();
        result = 31 * result + listNotePosition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(place);
        sb.append("\n");
        for (NotePosition note : listNotePosition) {
            sb.append(note);
            sb.append("\n");
        }
        return sb.toString();
    }
}
