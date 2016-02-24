package ua.nure.serdyuk.SummaryTask4.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public class ETicket {

	private static final Logger LOG = Logger.getLogger(ETicket.class);

	// private static final float[] COLUMNS_WIDTHS = { 1f, 2f };

	private TicketOrderBean bean;

	public ETicket(TicketOrderBean bean) {
		this.bean = bean;
	}

	public ByteArrayOutputStream createDocument(String path) {
		Document document = new Document();
		Font font = obtainFont();
		ByteArrayOutputStream baos = null;

		try {

			File file = new File(path);
			file.createNewFile();
			baos = new ByteArrayOutputStream();
			OutputStream fileOut = new FileOutputStream(path);
			
			PdfWriter.getInstance(document, baos);

			document.open();

			PdfPTable table = new PdfPTable(3);

			table.addCell(new Paragraph("�����: ", font));
			PdfPCell cell = new PdfPCell(
					new Paragraph(bean.getTrainBean().getTrainTag(), font));
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell(new Paragraph("�����: ", font));
			cell = new PdfPCell(
					new Paragraph(bean.getCarriage().getTag(), font));
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell(new Paragraph("�����: ", font));
			cell = new PdfPCell(
					new Paragraph(String.valueOf(bean.getSeatNum())));
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell(new Paragraph("���: ", font));
			cell = new PdfPCell(new Paragraph(String.format("%s %s",
					bean.getFirstName(), bean.getLastName()), font));
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell(new Paragraph("�����������: ", font));
			table.addCell(new Paragraph(bean.getStationFrom().getName(), font));
			table.addCell(new Paragraph(
					bean.getTrainBean().getDepDate().toLocaleString(), font));

			table.addCell(new Paragraph("��������: ", font));
			table.addCell(new Paragraph(bean.getStationTo().getName(), font));
			table.addCell(new Paragraph(
					bean.getTrainBean().getArrDate().toLocaleString(), font));

			table.addCell(new Paragraph("���������: ", font));
			cell = new PdfPCell(new Paragraph(
					String.valueOf(bean.getCarriage().getPrice())));
			cell.setColspan(2);
			table.addCell(cell);

			document.add(table);
			document.close();
			
			baos.writeTo(fileOut);

		} catch (DocumentException e) {
			LOG.error(String.format("Error while creating pdf: %s",
					e.getMessage()));
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

		return baos;
	}

	private Font obtainFont() {
		BaseFont bf = null;
		try {
			bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\ARIAL.TTF",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (DocumentException | IOException e1) {
			LOG.error("Error while obtaining font");
		}
		return new Font(bf);
	}

}
