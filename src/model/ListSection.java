package model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {

    private List<String> listNote = new ArrayList<>();

    public void addNoteToList(String note) {
        listNote.add(note);
    }

    public void updateNoteToList(int index, String note) {
        listNote.set(index, note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return listNote != null ? listNote.equals(that.listNote) : that.listNote == null;

    }

    @Override
    public int hashCode() {
        return listNote != null ? listNote.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : listNote) {
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }
}