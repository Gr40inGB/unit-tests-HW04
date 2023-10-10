package seminars.fourth.book;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BookServiceTest {

    @Test
    void findBookById() {
        final String search_id_ok = "1";
        final String search_id_bad = "#1j2k4lkj11409";
        Book looking_book = new Book("1", "Book1", "Author1");

        BookRepository bookRepositoryMock = mock(BookRepository.class);
        when(bookRepositoryMock.findById(search_id_ok)).thenReturn(looking_book);

        BookService bookService = new BookService(bookRepositoryMock);

        assertEquals(bookService.findBookById(search_id_ok), looking_book);
        assertNotEquals(bookService.findBookById(search_id_bad), looking_book);
    }

    @Test
    void findAllBooks() {
        List<Book> bookList = new ArrayList<>() {{
            new Book("1", "Book1", "Author1");
            new Book("2", "Book2", "Author2");
            new Book("3", "Book3", "Author3");
            new Book("4", "Book4", "Author4");
        }};

        BookRepository bookRepositoryMock = mock(BookRepository.class);
        when(bookRepositoryMock.findAll()).thenReturn(bookList);

        BookService bookService = new BookService(bookRepositoryMock);

        assertEquals(bookService.findAllBooks(), bookList);
        assertNotEquals(bookService.findAllBooks(), new ArrayList<>(Arrays.asList(new Book("3"))));

    }
}