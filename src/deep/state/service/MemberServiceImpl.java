package deep.state.service;

import deep.state.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class MemberServiceImpl implements MemberService<Person> {

    private static final String NAME_PREFIX_ENHANCEMENT = "Pan ";
    private static final String GROUP_FOR_ENHANCEMENT_TITLE = "Mr";

    @Override
    public Optional<String> processOldestMembers(List<Person> members) {
        return members.stream()
                .filter(person -> person.age() != null)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .limit(OLDEST_MEMBERS_COUNT)
                .map(this::getOldManDescription)
                .reduce((p1, p2) -> p1 + "; " + p2);
    }

    @Override
    public List<Person> enhanceNamesWithPrefix(List<Person> members) {
        Map<String, List<Person>> groups = members.stream()
                .collect(groupingBy(Person::title));

        groups.forEach(((title, group) -> System.out.println(title + ": " + group.size())));

        final var result = groups.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(GROUP_FOR_ENHANCEMENT_TITLE))
                .flatMap(entry -> entry.getValue().stream())
                .collect(toList());

        groups.get(GROUP_FOR_ENHANCEMENT_TITLE).stream()
                .map(this::enhanceNameWithPrefix)
                .forEach(result::add);

        return result;
    }

    @Override
    public List<Person> mergeMembers(List<Person> primaryMembers, List<Person> secondaryMembers) {
        final var result = new ArrayList<>(primaryMembers);

        secondaryMembers.stream()
                .filter(person -> !result.contains(person))
                .forEach(result::add);

        return result;
    }

    private String getOldManDescription(Person person) {
        return person.title() + " " + person.name() + ", " + person.age();
    }

    private Person enhanceNameWithPrefix(Person person) {
        return new Person(person.title(), NAME_PREFIX_ENHANCEMENT + person.name(), person.surname(), person.age());
    }
}
