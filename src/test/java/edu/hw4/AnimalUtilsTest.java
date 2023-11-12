package edu.hw4;

import edu.hw4.validation.ValidationError;
import edu.hw4.validator.DefaultAnimalValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class AnimalUtilsTest {
    private final List<Animal> animals = new ArrayList<>();

    @BeforeEach
    void clearAnimalList() {
        animals.clear();
    }

    @Test
    @DisplayName("getHeightSorted")
    void getHeightSorted_shouldReturnSortedListOfAnimalsByHeight() {
        animals.add(Animal.builder().height(10).build());
        animals.add(Animal.builder().height(19).build());
        animals.add(Animal.builder().height(12).build());
        animals.add(Animal.builder().height(9).build());
        animals.add(Animal.builder().height(1).build());

        assertThat(AnimalUtils.getHeightSorted(animals).stream().map(Animal::height).toList())
            .isEqualTo(List.of(1, 9, 10, 12, 19));
    }

    @Test
    @DisplayName("getHeaviestKAnimals")
    void getHeaviestKAnimals_shouldReturnSortedListOfHeaviestKAnimals() {
        animals.add(Animal.builder().weight(10).build());
        animals.add(Animal.builder().weight(19).build());
        animals.add(Animal.builder().weight(12).build());
        animals.add(Animal.builder().weight(9).build());
        animals.add(Animal.builder().weight(1).build());

        assertThat(AnimalUtils.getHeaviestKAnimals(animals, 3).stream().map(Animal::weight).toList())
            .isEqualTo(List.of(19, 12, 10));
    }

    @Test
    @DisplayName("countNumberOfAnimalsOfEachType")
    void countNumberOfAnimalsOfEachType_shouldReturnMapTypeAmount() {
        animals.add(Animal.builder().type(Animal.Type.DOG).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).build());
        animals.add(Animal.builder().type(Animal.Type.CAT).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).build());

        assertThat(AnimalUtils.countNumberOfAnimalsOfEachType(animals))
            .containsAllEntriesOf(
                Map.of(
                    Animal.Type.CAT, 2,
                    Animal.Type.DOG, 3,
                    Animal.Type.FISH, 1
                )
            )
        ;
    }

    @Test
    @DisplayName("getAnimalWithLongestName")
    void getAnimalWithLongestName_shouldReturnAnimalWithLongestName() {
        Animal longestNameAnimal = Animal.builder().name("longestNameEver").build();
        animals.add(Animal.builder().name("longever").build());
        animals.add(Animal.builder().name("longe").build());
        animals.add(longestNameAnimal);
        animals.add(Animal.builder().name("lon").build());

        assertThat(AnimalUtils.getAnimalWithLongestName(animals)).isEqualTo(longestNameAnimal);
    }

    @Test
    @DisplayName("animalsOfWhichSexAreMore")
    void animalsOfWhichSexAreMore_shouldReturnSexThatMostAnimalsHave() {
        animals.add(Animal.builder().sex(Animal.Sex.F).build());
        animals.add(Animal.builder().sex(Animal.Sex.F).build());
        animals.add(Animal.builder().sex(Animal.Sex.M).build());
        assertThat(AnimalUtils.animalsOfWhichSexAreMore(animals)).isEqualTo(Animal.Sex.F);

        animals.add(Animal.builder().sex(Animal.Sex.M).build());
        animals.add(Animal.builder().sex(Animal.Sex.M).build());
        assertThat(AnimalUtils.animalsOfWhichSexAreMore(animals)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("getHeaviestAnimalOfEachType")
    void getHeaviestAnimalOfEachType_shouldReturnMapTypeHeaviestAnimal() {
        animals.add(Animal.builder().weight(5).type(Animal.Type.DOG).build());
        Animal dogMax = Animal.builder().weight(15).type(Animal.Type.DOG).build();
        animals.add(dogMax);
        animals.add(Animal.builder().weight(0).type(Animal.Type.DOG).build());
        Animal fishMax = Animal.builder().weight(4).type(Animal.Type.FISH).build();
        animals.add(fishMax);
        Animal catMax = Animal.builder().weight(10).type(Animal.Type.CAT).build();
        animals.add(catMax);
        animals.add(Animal.builder().weight(7).type(Animal.Type.CAT).build());

        assertThat(AnimalUtils.getHeaviestAnimalOfEachType(animals))
            .containsAllEntriesOf(
                Map.of(
                    Animal.Type.CAT, catMax,
                    Animal.Type.DOG, dogMax,
                    Animal.Type.FISH, fishMax
                )
            )
        ;
    }

    @Test
    @DisplayName("getTheKthOfOldestAnimals")
    void getTheKthOfOldestAnimals_shouldReturnKOldestAnimal() {
        animals.add(Animal.builder().age(19).build());
        Animal expected = Animal.builder().age(10).build();
        animals.add(expected);
        animals.add(Animal.builder().age(12).build());
        animals.add(Animal.builder().age(1).build());
        animals.add(Animal.builder().age(9).build());

        assertThat(AnimalUtils.getTheKthOfOldestAnimals(animals, 3)).isEqualTo(expected);
    }

    @Test
    @DisplayName("getHeaviestAnimalBelowMaxHeightCm")
    void getHeaviestAnimalBelowMaxHeightCm_shouldReturnOptionalAnimal_NoAnimalWhenAllAnimalsAreHigherThanMaxHeight() {
        animals.add(Animal.builder().height(100).weight(100).build());
        Animal expected = Animal.builder().height(10).weight(17).build();
        animals.add(expected);
        animals.add(Animal.builder().height(12).weight(15).build());
        animals.add(Animal.builder().height(1).weight(3).build());
        animals.add(Animal.builder().height(9).weight(14).build());
        assertThat(AnimalUtils.getHeaviestAnimalBelowMaxHeightCm(animals, 99).orElse(null)).isEqualTo(expected);

        animals.clear();
        animals.add(Animal.builder().height(100).weight(5).build());
        animals.add(Animal.builder().height(120).weight(7).build());
        assertThat(AnimalUtils.getHeaviestAnimalBelowMaxHeightCm(animals, 99).orElse(null)).isEqualTo(null);
    }

    @Test
    @DisplayName("sum of paws")
    void getSumOfPaws_shouldReturnAmountOfPawsOfAllAnimalsInAnimalList() {
        animals.add(Animal.builder().type(Animal.Type.DOG).build()); // 4 +
        animals.add(Animal.builder().type(Animal.Type.FISH).build()); // 0 +
        animals.add(Animal.builder().type(Animal.Type.CAT).build()); // 4 +
        animals.add(Animal.builder().type(Animal.Type.SPIDER).build()); // 8 +
        animals.add(Animal.builder().type(Animal.Type.BIRD).build()); // 2 +
        animals.add(Animal.builder().type(Animal.Type.DOG).build()); // 4

        assertThat(AnimalUtils.getSumOfPaws(animals)).isEqualTo(22);
    }

    @Test
    @DisplayName("getAnimalsWithAgeNotEqualsAmountOfPaws")
    void getAnimalsWithAgeNotEqualsAmountOfPaws() {
        animals.add(Animal.builder().age(4).type(Animal.Type.DOG).build()); // 4 = 4
        animals.add(Animal.builder().age(5).type(Animal.Type.FISH).build()); // 5 != 0
        animals.add(Animal.builder().age(2).type(Animal.Type.CAT).build()); // 2 != 4
        animals.add(Animal.builder().age(3).type(Animal.Type.SPIDER).build()); // 3 != 8
        animals.add(Animal.builder().age(4).type(Animal.Type.CAT).build()); // 4 = 4
        animals.add(Animal.builder().age(2).type(Animal.Type.BIRD).build()); // 2 = 2
        assertThat(AnimalUtils.getAnimalsWithAgeNotEqualsAmountOfPaws(animals))
            .containsExactly(
                Animal.builder().age(5).type(Animal.Type.FISH).build(),
                Animal.builder().age(2).type(Animal.Type.CAT).build(),
                Animal.builder().age(3).type(Animal.Type.SPIDER).build()
            );
    }

    @Test
    @DisplayName("getAnimalsCanBiteAndHigherThan100")
    void getAnimalsCanBiteAndHigherThan100() {
        animals.add(Animal.builder().height(100).bites(true).build());
        animals.add(Animal.builder().height(101).bites(true).build());
        animals.add(Animal.builder().height(101).bites(false).build());

        assertThat(AnimalUtils.getAnimalsCanBiteAndHigherThan100(animals))
            .containsExactly(
                Animal.builder().height(101).bites(true).build()
            );
    }

    @Test
    @DisplayName("countAnimalsWithWeightGreaterThanHeight")
    void countAnimalsWithWeightGreaterThanHeight() {
        animals.add(Animal.builder().height(10).weight(10).build());
        animals.add(Animal.builder().height(19).weight(20).build()); // + 1
        animals.add(Animal.builder().height(12).weight(11).build());
        animals.add(Animal.builder().height(9).weight(10).build()); // + 1
        animals.add(Animal.builder().height(1).weight(2).build()); // + 1

        assertThat(AnimalUtils.countAnimalsWithWeightGreaterThanHeight(animals)).isEqualTo(3);
    }

    @Test
    @DisplayName("getAnimalWithComplexName")
    void getAnimalWithComplexName() {
        animals.add(Animal.builder().name("longest Name Ever").build());
        animals.add(Animal.builder().name("long ever").build());
        animals.add(Animal.builder().name("l on ge").build());
        animals.add(Animal.builder().name("lon").build());
        animals.add(Animal.builder().name("longestNameEveras").build());

        assertThat(AnimalUtils.getAnimalWithComplexName(animals))
            .containsExactly(
                Animal.builder().name("longest Name Ever").build(),
                Animal.builder().name("l on ge").build()
            );
    }

    @Test
    @DisplayName("ifThereIsDogTallerThanK")
    void ifThereIsDogTallerThanK() {
        animals.add(Animal.builder().height(4).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().height(10).type(Animal.Type.FISH).build());
        animals.add(Animal.builder().height(5).type(Animal.Type.DOG).build());
        assertThat(AnimalUtils.ifThereIsDogTallerThanK(animals, 5)).isFalse();

        animals.add(Animal.builder().height(10).type(Animal.Type.DOG).build());
        assertThat(AnimalUtils.ifThereIsDogTallerThanK(animals, 5)).isTrue();
    }

    @Test
    @DisplayName("getWeightSumOfEachTypeOfAnimalsWithAgeBetweenLAndK")
    void getWeightSumOfEachTypeOfAnimalsWithAgeBetweenLAndK() {
        animals.add(Animal.builder().weight(130).age(10).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().weight(120).age(8).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().weight(100).age(13).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().weight(301).age(11).type(Animal.Type.CAT).build());

        assertThat(AnimalUtils.getWeightSumOfEachTypeOfAnimalsWithAgeBetweenLAndK(animals, 7, 13))
            .containsAllEntriesOf(Map.of(
                Animal.Type.DOG, 250,
                Animal.Type.CAT, 301)
            );
    }

    @Test
    @DisplayName("sortedByTypeThenBySexThenByName")
    void sortedByTypeThenBySexThenByName() {
        animals.add(Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.F).name("catF").build());
        animals.add(Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("dogM").build());
        animals.add(Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("catMa").build());
        animals.add(Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("catMb").build());

        assertThat(AnimalUtils.sortedByTypeThenBySexThenByName(animals))
            .isEqualTo(
                List.of(
                    Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("catMa").build(),
                    Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("catMb").build(),
                    Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.F).name("catF").build(),
                    Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("dogM").build()
                )
            );
    }

    @Test
    @DisplayName("ifSpidersBiteMoreOftenThanDogs")
    void ifSpidersBiteMoreOftenThanDogs() {
        animals.add(Animal.builder().bites(false).type(Animal.Type.DOG).build());
        assertThat(AnimalUtils.ifSpidersBiteMoreOftenThanDogs(animals)).isFalse(); // can't say so false
        animals.add(Animal.builder().bites(true).type(Animal.Type.SPIDER).build());
        animals.add(Animal.builder().bites(true).type(Animal.Type.DOG).build());
        animals.add(Animal.builder().bites(true).type(Animal.Type.DOG).build());
        assertThat(AnimalUtils.ifSpidersBiteMoreOftenThanDogs(animals)).isTrue(); // SPIDER - 100%, DOG - 66%
        animals.add(Animal.builder().bites(false).type(Animal.Type.SPIDER).build());
        assertThat(AnimalUtils.ifSpidersBiteMoreOftenThanDogs(animals)).isFalse(); // SPIDER - 50%, DOG - 66%
    }

    @Test
    @DisplayName("heaviest fish")
    void getHeaviestFishFromSeveralLists() {
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(Animal.builder().type(Animal.Type.CAT).weight(200).build());
        List<Animal> animals2 = new ArrayList<>();
        animals2.add(Animal.builder().type(Animal.Type.FISH).weight(300).build());
        animals.add(Animal.builder().type(Animal.Type.FISH).weight(100).build());
        animals.add(Animal.builder().type(Animal.Type.DOG).weight(400).build());
        assertThat(AnimalUtils.getHeaviestFishFromSeveralLists(animals, animals2, animals1))
            .isEqualTo(animals2.getFirst());
    }

    @Test
    @DisplayName("animal errors")
    void getAnimalErrors() {
        animals.add(Animal.builder().weight(0).height(12).age(-5).name("Microchelik").build());
        animals.add(Animal.builder().weight(12).height(-5).age(15).name("Stepan").build());
        animals.add(Animal.builder().weight(12).height(13).age(10).name("Vlader0n").build());

        assertThat(AnimalUtils.getAnimalErrors(animals, new DefaultAnimalValidator()))
            .isEqualTo(Map.of(
                "Microchelik",
                Set.of(
                    new ValidationError("age", "Age should be positive"),
                    new ValidationError("weight", "Weight should be greater than zero")
                ),
                "Stepan",
                Set.of(
                    new ValidationError("height", "Height should be greater than zero")
                )
            ));
    }

    @Test
    @DisplayName("animal readable errors")
    void getAnimalErrorsMoreReadable() {
        animals.add(Animal.builder().weight(0).height(12).age(-5).name("Microchelik").build());
        animals.add(Animal.builder().weight(12).height(-5).age(15).name("Stepan").build());
        animals.add(Animal.builder().weight(12).height(13).age(10).name("Vlader0n").build());
        assertThat(AnimalUtils.getAnimalErrorsMoreReadable(animals, new DefaultAnimalValidator()))
            .isEqualTo(Map.of(
                "Microchelik",
                "age, weight",
                "Stepan",
                "height"
            ));
    }
}
