package part2.chapter5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadUserTest {
    @Test
    void renameUser() {
        var user = new BadUser("Doe jun");

        String newName = user.normalizeName(
                "His name length is over 50 characters, His name length is over 50 characters, His name length is over 50 characters"
        );

        user.modifyName(newName);
        assertThat(newName.length()).isEqualTo(50);
    }
}