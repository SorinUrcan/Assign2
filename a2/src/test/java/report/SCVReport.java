package report;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import model.Book;
import model.BookList;

public class SCVReport implements Report {

	public void createReport(BookList books, String filePath) {
		String CSV_SEPARATOR = ",";
		try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            StringBuilder firstLine = new StringBuilder();
            firstLine.append("ID,Genre,Title,Author,Price,Quantity");
            bw.write(firstLine.toString());
			bw.newLine();
            for (Book book : books.getBooks())
            	if (book.getStock() <= 0) {
            		StringBuilder oneLine = new StringBuilder();
            		oneLine.append(book.getId());
            		oneLine.append(CSV_SEPARATOR);
            		oneLine.append(book.getGenre());
            		oneLine.append(CSV_SEPARATOR);
        			oneLine.append(book.getTitle());
        			oneLine.append(CSV_SEPARATOR);
        			oneLine.append(book.getAuthor());
        			oneLine.append(CSV_SEPARATOR);
        			oneLine.append(book.getPrice());
        			oneLine.append(CSV_SEPARATOR);
        			oneLine.append(book.getStock());
        		
        			bw.write(oneLine.toString());
        			bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
	}
}
