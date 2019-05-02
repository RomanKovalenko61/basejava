package model;

import java.util.ArrayList;
import java.util.List;

public class TableSectionType implements Printable {
    private SectionType title;

    private List<Table> tables = new ArrayList<>();

    public TableSectionType(SectionType title) {
        this.title = title;
    }

    public SectionType getTitle() {
        return title;
    }

    public List<Table> getList() {
        return tables;
    }

    public void addString(String place, String period, String description) {
        tables.add(new Table(place, period, description));
    }

    @Override
    public void print() {
        System.out.println(title.getTitle());
        for (Table table : tables) {
            System.out.println(table.place);
            System.out.println(table.period + ": " + table.description);
        }
    }

    private class Table {
        private String place;

        private String period;

        private String description;

        private Table(String place, String period, String description) {
            this.place = place;
            this.period = period;
            this.description = description;
        }
    }
}
