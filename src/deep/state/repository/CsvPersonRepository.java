package deep.state.repository;

import deep.state.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class CsvPersonRepository implements MemberRepository<Person> {
    private static final int DEEP_STATE_MEMBERS_LIMIT = 12;

    private final Path path;

    public CsvPersonRepository(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Person> acquireMembers() {
        try (var lines = Files.lines(path)) {
            var members = lines
                    .map(Person::createFromLine)
                    .limit(DEEP_STATE_MEMBERS_LIMIT)
                    .collect(toList());
            return unmodifiableList(makeSomeMembersImmortal(members));
        } catch (IOException e) {
            System.err.println("Deep State couldn't be established due to missing members: " + e.getMessage());
        }

        return emptyList();
    }

    private List<Person> makeSomeMembersImmortal(List<Person> members) {
        final int immortalsCounter = 5;
        int index = 0;
        Random random = new Random();
        for (int i = 0; i < immortalsCounter; i++) {
            members.set(index, Person.nullifyAge(members.get(index)));
            index += random.nextInt(0, 2) + 1;
        }

        return members;
    }
}
