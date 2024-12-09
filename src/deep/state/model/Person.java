package deep.state.model;

public record Person(String title, String name, String surname, Integer age) {

    private static final int TITLE_CSV_INDEX = 0;
    private static final int NAME_CSV_INDEX = 1;
    private static final int SURNAME_CSV_INDEX = 2;
    private static final int AGE_CSV_INDEX = 4;

    public static Person createFromLine(String line) {
        String[] data = line.split(",");
        return new Person(
                data[TITLE_CSV_INDEX],
                data[NAME_CSV_INDEX],
                data[SURNAME_CSV_INDEX],
                Integer.parseInt(data[AGE_CSV_INDEX]));
    }

    public static Person nullifyAge(Person person) {
        return new Person(person.title(), person.name(), person.surname(), null);
    }
}
