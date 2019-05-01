package model;

import java.util.ArrayList;
import java.util.List;

public class TableSectionType {
    private SectionType title;

    private List<Table> cellTables = new ArrayList<>();

    public TableSectionType(SectionType title) {
        this.title = title;
    }

    public SectionType getTitle() {
        return title;
    }

    public List<Table> getList() {
        return cellTables;
    }

    public void addCell(Table table) {
        cellTables.add(table);
    }

    public void editString(int index, Table table) {
        cellTables.set(index, table);
    }

    public Table getString(int index) {
        return cellTables.get(index);
    }

    public void removeString(int index) {
        cellTables.remove(index);
    }

    public class Table {
        private String place;

        private String period;

        private String description;

        Table(String place, String period, String description) {
            this.place = place;
            this.period = period;
            this.description = description;
        }

        public String getPlace() {
            return place;
        }

        public String getPeriod() {
            return period;
        }

        public String getDescription() {
            return description;
        }
    }
}
