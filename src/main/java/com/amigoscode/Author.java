package com.amigoscode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Author(
        Integer id,
        String name
) {
    public static List<Author> authors = Arrays.asList(
            new Author(1, "J.K.R"),
            new Author(2, "H.G Wells"),
            new Author(3, "L.N")
    );

    public static Optional<Author> getAuthorById(Integer id) {
        System.out.println("Fetching Author with id: " + id);
        return authors.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }
}
