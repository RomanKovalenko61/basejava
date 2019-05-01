package model;

import java.util.ArrayList;
import java.util.List;

public class ListSectionType {
    private SectionType title;

    private List<String> list = new ArrayList<>();

    public ListSectionType(SectionType title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSectionType that = (ListSectionType) o;

        if (title != that.title) return false;
        return list.equals(that.list);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ListSectionType{" +
                "title=" + title +
                ", list=" + list +
                '}';
    }

    public SectionType getTitle() {
        return title;
    }

    public void setTitle(SectionType title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void addString(String str) {
        list.add(str);
    }

    public void editString(int index, String str) {
        list.set(index, str);
    }

    public String getString(int index) {
        return list.get(index);
    }

    public void removeString(int index) {
        list.remove(index);
    }
}
