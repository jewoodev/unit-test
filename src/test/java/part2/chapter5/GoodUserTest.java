package part2.chapter5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GoodUserTest {
    @Test
    void test() {
        var user = new GoodUser("Doe jun");

        user.modifyName(
                "His name length is over 50 characters, His name length is over 50 characters, His name length is over 50 characters"
        );

        assertThat(user.getName().length()).isEqualTo(50);
    }
}