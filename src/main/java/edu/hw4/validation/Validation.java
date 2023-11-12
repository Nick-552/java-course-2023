package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.Optional;

public interface Validation {
    Optional<ValidationError> perform(Animal animal);
}
