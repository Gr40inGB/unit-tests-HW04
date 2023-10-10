### Задание 1.

1) Почему использование тестовых заглушек может быть полезным при написании модульных тестов?

   `Простота и скорость написания тестов, экономия ресурсов (потому что вместо загрузки целых классов с методами, задаются только поведение или прямые данные), безопасность для реальных данных`

2) Какой тип тестовой заглушки следует использовать, если вам нужно проверить, что метод был вызван с определенными
   аргументами?

     `C такой задачей могут справится spy и mock `

3) Какой тип тестовой заглушки следует использовать, если вам просто нужно вернуть определенное значение или исключение
   в ответ на вызов метода?

    `для подстановки достаточно stub или как вариант более сложные - fake и mock`

4) Какой тип тестовой заглушки вы бы использовали для имитации взаимодействия с внешним API или базой данных?

    `fake и mock могут обеспечить имитацию поведений необходимых для тестов`

### Задание 2

```java
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
```