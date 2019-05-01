package model;

public class TextSectionType {
    private String title;

    private String description;

    public TextSectionType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSectionType that = (TextSectionType) o;

        if (title != that.title) return false;
        return description.equals(that.description);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public String getText() {
        return (title + ": " + description);
    }
}
