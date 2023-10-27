package edu.hw3.ex5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw3.ex5.ContactListUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ContactListUtilsTest {

    private static Stream<Arguments> contactsSource() {
        return Stream.of(
            Arguments.of(
                new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                Order.ASC,
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                )
            ),
            Arguments.of(
                new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                Order.DESC,
                List.of(
                    new Contact("John", "Locke"),
                    new Contact("David", "Hume"),
                    new Contact("Rene", "Descartes"),
                    new Contact("Thomas", "Aquinas")
                )
            ),
            Arguments.of(
                new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                Order.DESC,
                List.of(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                )
            ),
            Arguments.of(
                null,
                Order.ASC,
                List.of()
            ),
            Arguments.of(
                new String[]{},
                Order.ASC,
                List.of()
            )
        );
    }

    private static Stream<Arguments> illegalContactsSource() {
        return Stream.of(
            Arguments.of(
                new String[]{null, null, null}, Order.ASC
            ),
            Arguments.of(
                new String[]{"", "", "", ""}, Order.ASC
            ),
            Arguments.of(
                new String[]{"Dsdfg", "Rsfdgs", "Agsfg"}, Order.ASC
            ),
            Arguments.of(
                new String[]{"Dfsdg Dfdfsg Dfgsddf", "Sdgsdf Kegsf, Ardsf"}, Order.ASC
            )
        );
    }

    private static Stream<Arguments> wrongFormatContactsSource() {
        return Stream.of(
            Arguments.of(
                new String[]{"fadfa fasdfa", "adfgsdfg gsdfgs", "dfgsg fdgsdfg"}, Order.ASC
            ),
            Arguments.of(
                new String[]{"Sfdsf3", "Dg3sfdg"}, Order.ASC
            ),
            Arguments.of(
                new String[]{"FDGSGSD", "DDgdfgs"}, Order.ASC
            ),
            Arguments.of(
                new String[]{"a s"}, Order.ASC
            )
        );
    }

    @ParameterizedTest
    @MethodSource("contactsSource")
    @DisplayName("Basic test")
    void parseContacts_shouldWorkCorrectly_whenValidInput(String[] contacts, Order order, List<Contact> expected) {
        assertThat(parseContacts(order, contacts)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("illegalContactsSource")
    @DisplayName("Can't parse contact")
    void parseContacts_shouldThrowIllegalArgumentException_whenInvalidInput(String[] contacts, Order order) {
        assertThatIllegalArgumentException().isThrownBy(() -> parseContacts(order, contacts));
    }

    @ParameterizedTest
    @MethodSource("wrongFormatContactsSource")
    @DisplayName("Wrong name and surname format")
    void parseContacts_shouldThrowIllegalArgumentException_whenWrongFormatContacts(String[] contacts, Order order) {
        assertThatIllegalArgumentException().isThrownBy(() -> parseContacts(order, contacts));
    }
}
