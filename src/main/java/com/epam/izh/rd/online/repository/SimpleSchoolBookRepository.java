package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] updatedSchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        updatedSchoolBooks[updatedSchoolBooks.length - 1] = book;
        schoolBooks = updatedSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int bookNumber = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                bookNumber++;
            }
        }
        SchoolBook[] findedSchoolBooks = new SchoolBook[bookNumber];
        int index = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                findedSchoolBooks[index] = book;
                index++;
            }
        }
        return findedSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int bookNumber = findByName(name).length;
        if (bookNumber == 0) {
            return false;
        }
        SchoolBook[] updatedSchoolBooks = new SchoolBook[schoolBooks.length - bookNumber];
        int index = 0;
        for (SchoolBook book : schoolBooks) {
            if (!book.getName().equals(name)) {
                updatedSchoolBooks[index] = book;
                index++;
            }
        }
        schoolBooks = updatedSchoolBooks;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
