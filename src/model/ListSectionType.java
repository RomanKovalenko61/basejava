package model;

import java.util.ArrayList;
import java.util.List;

public class ListSectionType {

    private List<String> list = new ArrayList<>();

    public void addNote(String str) {
        list.add(str);
    }

    public void updateNote(int index, String str) {
        list.set(index, str);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }
}
