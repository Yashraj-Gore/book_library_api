package com.books.app.service;

import com.books.app.model.Book;
import com.books.app.repository.BookRepository;
import com.books.app.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMockTest {

    @Mock
    BookRepository bookRepositoryMock;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    List<Book> booksData;

    @Before
    public void setUp() {
        setupData();
    }

    @Test
    public void testGetAll() {

        Mockito.when(bookRepositoryMock.findAll()).thenReturn(booksData);

        List<Book> responseBooks = bookServiceImpl.getAll();
        Assert.assertEquals(3, responseBooks.size());
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).findAll();
    }

    @Test
    public void testCreate() {
        Book newBook = booksData.get(0);

        Mockito.when(bookRepositoryMock.save(Mockito.any(Book.class))).thenReturn(newBook);

        Book book = bookServiceImpl.create(newBook);
        Assert.assertNotNull(book);
        Assert.assertEquals("Test book1", book.getName());
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).save(newBook);
    }

    @Test
    public void testDelete() {
        Book bookToDelete = booksData.get(0);
        Mockito.when(bookRepositoryMock.getOne(1l)).thenReturn(bookToDelete);
        Mockito.doNothing().when(bookRepositoryMock).delete(Mockito.any(Book.class));

        bookServiceImpl.delete(1l);
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).getOne(1l);
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).delete(bookToDelete);
    }

    private void setupData() {
        booksData = Arrays.asList(
                new Book(1l, "Test book1", "Test author1"),
                new Book(2l, "Test book2", "Test author2"),
                new Book(3l, "Test book3", "Test author3")
        );
    }

}