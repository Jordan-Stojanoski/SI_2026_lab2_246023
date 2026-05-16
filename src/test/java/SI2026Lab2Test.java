import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {

        // Test case 1
        Library library1 = new Library();
        library1.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        List<Book> result1 = library1.searchBookByTitle("Clean Code");
        assertNotNull(result1);
        assertEquals(1, result1.size());


        // Test case 2
        Library library2 = new Library();
        library2.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        List<Book> result2 = library2.searchBookByTitle("Harry Potter");
        assertNull(result2);


        // Test case 3
        Library library3 = new Library();
        assertThrows(IllegalArgumentException.class, () -> {
            library3.searchBookByTitle("");
        });
    }


    @Test
    void borrowBookEveryBranchTest() {

        // Test case 1
        Library library1 = new Library();
        library1.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library1.borrowBook("The Hobbit", "J.R.R. Tolkien");
        assertEquals(0, library1.countAvailableBooks());


        // Test case 2
        Library library2 = new Library();
        assertThrows(IllegalArgumentException.class, () -> {
            library2.borrowBook("", "Author");
        });


        // Test case 3
        Library library3 = new Library();
        library3.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library3.borrowBook("The Hobbit", "J.R.R. Tolkien");
        assertThrows(RuntimeException.class, () -> {
            library3.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });


        // Test case 4
        Library library4 = new Library();
        library4.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        assertThrows(RuntimeException.class, () -> {
            library4.borrowBook("Unknown", "Unknown");
        });
    }

    @Test
    void searchBookMultipleConditionTest() {

        Library library = new Library();
        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        library.addBook(b1);

        assertNotNull(library.searchBookByTitle("Clean Code"));
        assertEquals(1, library.searchBookByTitle("Clean Code").size());

        assertNull(library.searchBookByTitle("Wrong Title"));

        b1.setBorrowed(true);
        assertNull(library.searchBookByTitle("Clean Code"));

        assertNull(library.searchBookByTitle("Something"));
    }

    @Test
    void borrowBookMultipleConditionTest() {

        Library library = new Library();
        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        library.addBook(b1);

        library.borrowBook("Clean Code", "Robert C. Martin");

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Robert C. Martin");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("Clean Code", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "");
        });
    }
}