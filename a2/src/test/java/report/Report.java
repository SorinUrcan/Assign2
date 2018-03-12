package report;

import model.BookList;

public interface Report {
    public void createReport(BookList books, String filePath);
}
