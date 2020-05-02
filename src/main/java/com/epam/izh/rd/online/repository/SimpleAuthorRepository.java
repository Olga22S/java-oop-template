package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

import static java.util.Objects.isNull;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (!isNull(findByFullName(author.getName(), author.getLastName()))) {
            return false;
        }
        int newArraySize = authors.length + 1;
        Author[] updatedAuthors = Arrays.copyOf(authors, newArraySize);
        updatedAuthors[newArraySize - 1] = author;
        authors = updatedAuthors;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (isNull(findByFullName(author.getName(), author.getLastName()))) {
            return false;
        }
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                break;
            }
        }
        Author[] updatedAuthors = Arrays.copyOfRange(authors, 0, authors.length - 1);
        authors = updatedAuthors;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
