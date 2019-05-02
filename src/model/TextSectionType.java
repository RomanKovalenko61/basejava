package model;

public class TextSectionType implements Printable {
    private String title;

    private String description;

    public TextSectionType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSectionType that = (TextSectionType) o;

        if (!title.equals(that.title)) return false;
        return description.equals(that.description);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public void print() {
        System.out.println(title + ": " + description);
    }
}
