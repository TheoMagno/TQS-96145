package io.cucumber.skeleton;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Book {
    private final String title;
    private final String author;
    private final LocalDateTime published;

    public Book(String title, String author, LocalDateTime published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Date getPublished() {
        return java.util.Date.from(published.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getTitle() {
        return title;
    }
}
