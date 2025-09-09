package com.amigoscode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public record Book(
        Integer id,
        String name,
        Integer pageCount,
        Integer authorID
) {
    public static List<Book> books = Arrays.asList(
            new Book(1, "Harry Potter", 504, 1),
            new Book(2, "Time machine", 325, 2),
            new Book(3, "Halo: Combat Evolved", 236, 2),
            new Book(4, "Ring world", 550, 3)
    );

    public static Optional<Book> getBookById(Integer id) {
        System.out.println("Fetching Book with id: " + id);
        return books.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }
}
