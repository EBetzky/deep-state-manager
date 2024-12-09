package deep.state;

import deep.state.repository.CsvPersonRepository;
import deep.state.service.MemberServiceImpl;

public class DeepStateMain {
    private static final String DEEP_STATE_MEMBER_DATA = "./src/deep/state/data/members.csv";

    public static void main(String[] args) {

        System.out.println(
                """
                Welcome to Deep State Manager Beta, an app to rule the world! Hello World!
                ---""");

        var memberRepository = new CsvPersonRepository(DEEP_STATE_MEMBER_DATA);
        var memberService = new MemberServiceImpl();

        // 1. Napisz metodę, która stworzy kolekcję 12 obiektów Person. 5 obiektów Person powinno mieć pustą wartość dla age.
        var members = memberRepository.acquireMembers();
        members.forEach(System.out::println);
        System.out.println("---");

        // 2. Napisz metodę, która wypisze listę 3 najstarszych osób jako pojedynczy string  title + name + age z wykorzystaniem Optional.
        var oldestMembers = memberService.processOldestMembers(members);
        System.out.println(oldestMembers.orElse("Deep State doesn't have enough members") + "\n---");

        // 3. Napisz metodę, która pogrupuje osoby po title, wypisze ilość osób w grupie a następnie dla title="Mr" doda przedrostek "pan" do imienia.
        var otherMembers = memberService.enhanceNamesWithPrefix(members);
        otherMembers.forEach(System.out::println);
        System.out.println("---");

        // 4. Napisz metodę, która połączy dwie kolekcje z Person, tak aby w wyniku nie było duplikatów.
        var mergedMembers = memberService.mergeMembers(members, otherMembers);
        mergedMembers.forEach(System.out::println);
        System.out.println("---");

        System.out.println(
                """
                Deep State is over...
                """);
    }
}
