package report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Book;
import model.BookList;

public class PDFReport implements Report {

	public void createReport(BookList books, String filePath) {
		try {
		    OutputStream file = new FileOutputStream(new File(filePath));
		    Document document = new Document();
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    
		    List<Book> bookList = books.getBooks();
	        StringBuilder output = new StringBuilder();
	        output.append("ID | Genre | Title | Author | Price | Quantity \n");
	        for (Book book : bookList) {
	        	if (book.getStock() <= 0) {
	        	output.append(book.getId());
	            output.append("     ");
	            output.append(book.getGenre());
	            output.append("  ");
	            output.append(book.getTitle());
	            output.append("  ");
	            output.append(book.getAuthor());
	            output.append("  ");
	            output.append(book.getPrice());
	            output.append("  ");
	            output.append(book.getStock());
	            output.append("\n");
	        	}
	        }
	        
		    document.add(new Paragraph(output.toString()));
		    document.close();
	         writer.close();
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
