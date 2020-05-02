package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.*;
import com.epam.izh.rd.online.repository.BookRepository;

import static java.util.Objects.isNull;

public class SimpleSchoolBookService implements BookService {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        Author author = authorService.findByFullName(((SchoolBook) book).getAuthorName(), ((SchoolBook) book).getAuthorLastName());
        if (isNull(author)) {
            return false;
        }
        schoolBookBookRepository.save((SchoolBook) book);
        return true;
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        if (schoolBookBookRepository.findByName(name).length == 0) {
            return null;
        }
        SchoolBook book = schoolBookBookRepository.findByName(name)[0];
        return authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
    }
}
