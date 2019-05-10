package model;

public class NoteAboutExperience {

    private String place;

    private String period;

    private String description;

    public NoteAboutExperience(String place, String period, String description) {
        this.place = place;
        this.period = period;
        this.description = description;
    }

    public void updateNote(String place, String period, String description) {
        this.place = place;
        this.period = period;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteAboutExperience that = (NoteAboutExperience) o;

        if (!place.equals(that.place)) return false;
        if (!period.equals(that.period)) return false;
        return description.equals(that.description);

    }

    @Override
    public int hashCode() {
        int result = place.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return place +
                "\n" +
                period +
                " : " +
                description;
    }
}
