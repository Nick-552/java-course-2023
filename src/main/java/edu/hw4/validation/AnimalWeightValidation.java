package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.Optional;

public class AnimalWeightValidation implements Validation {
    @Override
    public Optional<ValidationError> perform(Animal animal) {
        if (animal.weight() <= 0) {
            return Optional.of(new ValidationError("weight", "Weight should be greater than zero"));
        }
        return Optional.empty();
    }
}
