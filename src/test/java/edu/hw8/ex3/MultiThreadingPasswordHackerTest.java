package edu.hw8.ex3;

import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MultiThreadingPasswordHackerTest {

    @Test
    void hackPasswordsFromHashes() {
        Set<UserCredentials> passwordDatabase = Set.of(
            new UserCredentials("aaaaa", "594f803b380a41396ed63dca39503542"),
            new UserCredentials("nick", "a384b6463fc216a5f8ecb6670f86456a"),
            new UserCredentials("nick552", "827ccb0eea8a706c4c34a16891f84e7b"),
            new UserCredentials("99999", "d3eb9a9233e52948740d7eb8c3062d14"),
            new UserCredentials("aaaaaa", "0b4e7a0e5fe84ad35fb5f95b9ceeac79")
        );
        Map<String, String> hackedUsers = new MultiThreadingPasswordHacker()
            .hackPasswordsFromHashes(passwordDatabase);
        assertThat(hackedUsers).containsExactlyInAnyOrderEntriesOf(Map.of(
            "aaaaaa", "aaaaaa",
            "qwert", "nick",
            "12345", "nick552",
            "99999", "99999",
            "aaaaa", "aaaaa"
        ));
    }
}
