package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.Optional;

public class AnimalAgeValidation implements Validation {
    @Override
    public Optional<ValidationError> perform(Animal animal) {
        if (animal.age() <= 0) {
            return Optional.of(new ValidationError("age", "Age should be positive"));
        }
        return Optional.empty();
    }
}
