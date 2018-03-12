package report;

public class ReportFactory {

	public Report setReport(String type) {
		if (type.equals(".csv")) {
			return new SCVReport();
		} else if (type.equals(".pdf")) {
			return new PDFReport();
		}
		return null;
	}
}
