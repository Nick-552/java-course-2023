package edu.hw10.ex1;

import edu.hw10.ex1.annotations.Max;
import edu.hw10.ex1.annotations.Min;
import edu.hw10.ex1.annotations.NotNull;
import edu.hw10.ex1.paramGenerator.ParameterGenerator;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RandomObjectGeneratorTest {

    private final RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

    private final RandomObjectGenerator randomObjectGeneratorSeeded
        = new RandomObjectGenerator(ThreadLocalRandom.current());

    @Test
    @DisplayName("basic test")
    void basicTest() {
        var person1 = randomObjectGenerator.nextObject(Person.class);
        var person2 = randomObjectGenerator.nextObject(Person.class, "create");
        var pet = randomObjectGenerator.nextObject(Person.class, "createPet");
        assertThat(person1).isInstanceOf(Person.class);
        assertThat(person1.age).isBetween(0, 200);
        assertThat(person1.name).isEqualTo("");
        assertThat(person2).isInstanceOf(Person.class);
        assertThat(((Person) person2).name).isNull();
        assertThat(((Person) person2).age).isBetween(0, 200);
        assertThat(pet).isInstanceOf(Pet.class);
    }

    @Test
    @DisplayName("no constructors")
    void nextObject_shouldThrowRuntimeException_whenInterfaceGiven() {
        assertThatThrownBy(() -> randomObjectGenerator.nextObject(ParameterGenerator.class))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("clazz represents an interface, a primitive type, an array class, or void.");
    }

    @Test
    @DisplayName("no method")
    void nextObject_shouldThrowRuntimeException_whenNoMethodWithGivenMethodNameFound() {
        assertThatThrownBy(() -> randomObjectGenerator.nextObject(Person.class, "createPerson"))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("No method for this method name %s in class %s"
                .formatted("createPerson", Person.class.getName()));
    }
}

class Person {

    String name;
    int age;
    Pet pet;

    public Person(
        @NotNull @Min(minValue = 5) @Max(maxValue = 4) String name,
        @Min(minValue = 0) @Max(maxValue = 200) int age
    ) {
        this.name = name;
        this.age = age;
    }

    public static Person create(
        @Min(minValue = 5) @Max(maxValue = 4) String name,
        @Min(minValue = 0) @Max(maxValue = 200) int age
    ) {
        return new Person(name, age);
    }

    public Pet createPet(String petName) {
        this.pet = new Pet(petName);
        return pet;
    }
}

record Pet(String name) {
}
