package model;

import java.util.ArrayList;
import java.util.List;

public class ListSectionType implements Printable {
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

    public SectionType getTitle() {
        return title;
    }

    public List<String> getList() {
        return list;
    }

    public void addString(String str) {
        list.add(str);
    }

    @Override
    public void print() {
        System.out.println(title.getTitle());
        for (String str : list) {
            System.out.println(str);
        }
    }
}
