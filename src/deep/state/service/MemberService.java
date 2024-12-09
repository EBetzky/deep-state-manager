package deep.state.service;

import java.util.List;
import java.util.Optional;

public interface MemberService<Person> {
    int OLDEST_MEMBERS_COUNT = 3;

    Optional<String> processOldestMembers(List<Person> members);
    List<Person> enhanceNamesWithPrefix(List<Person> members);
    List<Person> mergeMembers(List<Person> members1, List<Person> members2);
}
