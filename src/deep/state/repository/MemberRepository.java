package deep.state.repository;

import java.util.List;

public interface MemberRepository<M> {
    List<M> acquireMembers();
}
