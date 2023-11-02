package edu.hw4;

import edu.hw4.validation.ValidationError;
import edu.hw4.validator.AnimalValidator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public final class AnimalUtils {

    private static final ToIntFunction<Animal> BITES = animal -> (animal.bites() ? 1 : 0);

    private static final int HEIGHT_OF_BIG_ANIMAL = 100;

    private AnimalUtils() {}

    public static List<Animal> getHeightSorted(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> getHeaviestKAnimals(List<Animal> animals, Integer k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> countNumberOfAnimalsOfEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }


    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt((animal) -> animal.name().length()))
            .orElseThrow();
    }


    public static Animal.Sex animalsOfWhichSexAreMore(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .orElseThrow()
            .getKey();
    }

    // 5

    public static Map<Animal.Type, Animal> getHeaviestAnimalOfEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }


    public static Animal getTheKthOfOldestAnimals(List<Animal> animals, Integer k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow();
    }

    public static Optional<Animal> getHeaviestAnimalBelowMaxHeightCm(List<Animal> animals, Integer maxHeight) {
        return animals.stream()
            .filter(animal -> animal.height() < maxHeight)
            .max(Comparator.comparingInt(Animal::weight));
    }


    public static Integer getSumOfPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWithAgeNotEqualsAmountOfPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    // 10

    public static List<Animal> getAnimalsCanBiteAndHigherThan100(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > HEIGHT_OF_BIG_ANIMAL)
            .toList();
    }

    public static Long countAnimalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> getAnimalWithComplexName(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean ifThereIsDogTallerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> getWeightSumOfEachTypeOfAnimalsWithAgeBetweenLAndK(
        List<Animal> animals, int l, int k) {
        return animals.stream()
            .filter(animal -> animal.age() > l && animal.age() < k)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    // 15

    public static List<Animal> sortedByTypeThenBySexThenByName(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            ).toList();
    }

    public static Boolean ifSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.type() == Animal.Type.SPIDER || a.type() == Animal.Type.DOG)
            .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                Animal::type,
                Collectors.averagingInt(BITES)
            ), (map) -> {
                if (!map.containsKey(Animal.Type.SPIDER) || !map.containsKey(Animal.Type.DOG)) {
                    return false;
                }
                return map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG);
            }));
    }


    @SafeVarargs public static Animal getHeaviestFishFromSeveralLists(List<Animal>... animalLists) {
        return Arrays.stream(animalLists)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getAnimalErrors(
        List<Animal> animals, AnimalValidator animalValidator) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    Animal::name, animalValidator::validate
                ),
                stringSetMap -> {
                    stringSetMap.values().removeIf(Set::isEmpty);
                    return stringSetMap;
                }
            ));
    }

    public static Map<String, String> getAnimalErrorsMoreReadable(
        List<Animal> animals, AnimalValidator animalValidator) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    Animal::name,
                    animal -> animalValidator.validate(animal).stream().map(ValidationError::field)
                        .collect(Collectors.joining(", "))
                ),
                map -> {
                    map.values().removeIf(String::isEmpty);
                    return map;
                }
            ));
    }
}
