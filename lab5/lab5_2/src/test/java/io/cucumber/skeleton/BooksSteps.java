package io.cucumber.skeleton;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooksSteps {
    private Library library = new Library();
    private List<Book> result;
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }
    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void a_book_with_the_title_written_by_published_in(final String title, final String author,  final LocalDateTime published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }
    @Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void another_book_with_the_title_written_by_published_in(final String title, final String author,  final LocalDateTime published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }
    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void the_customer_searches_for_books_published_between_and(final LocalDateTime from, final LocalDateTime to) {
        result = library.findBooks(java.util.Date.from(from.atZone(ZoneId.systemDefault()).toInstant()), java.util.Date.from(to.atZone(ZoneId.systemDefault()).toInstant()));
    }
    @Then("{int} books should have been found")
    public void books_should_have_been_found(final int int1) {
        assertEquals(int1, result.size());
    }
    @Then("Book {int} should have the title {string}")
    public void book_should_have_the_title(final int int1, final String string) {
        assertEquals(string, result.get(int1-1).getTitle());
    }

}
