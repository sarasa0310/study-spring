package com.jimmy.studyspring.caching;

public interface BookRepository {

    Book getByIsbn(String isbn);

}
