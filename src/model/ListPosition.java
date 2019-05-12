package model;

import java.util.ArrayList;
import java.util.List;

public class ListPosition {

    private List<Position> listPosition = new ArrayList<>();

    public void addNoteToListPosition(Position position) {
        listPosition.add(position);
    }

    public void updateNoteToListPosition(int index, Position position) {
        listPosition.set(index, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListPosition that = (ListPosition) o;

        return listPosition != null ? listPosition.equals(that.listPosition) : that.listPosition == null;

    }

    @Override
    public int hashCode() {
        return listPosition != null ? listPosition.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Position temp : listPosition) {
            sb.append(temp);
            sb.append("\n");
        }
        return sb.toString();
    }
}
