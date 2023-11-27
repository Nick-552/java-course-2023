package edu.hw6.ex3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter abstractFilter) {
        return entry -> AbstractFilter.this.accept(entry) && abstractFilter.accept(entry);
    }
}
