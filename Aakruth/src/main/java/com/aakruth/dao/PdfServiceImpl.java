package com.aakruth.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakruth.model.BillTbl;
import com.aakruth.model.Dealer;
import com.aakruth.model.PrdTbl;
import com.aakruth.model.SalTbl;
import com.aakruth.model.UsrTbl;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("pdfService")
public class PdfServiceImpl implements PdfService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	static Font blueFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, BaseColor.BLUE);
	static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	static Font brandFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLUE);
	static Font whiteFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.WHITE);
	static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE);
	static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	static Font LargeBold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	static Font normal12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	static Font normal14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	static Font normal14i = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC);
	static Font normal12i = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
	static Font tiny10i = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);

	Integer PageNum = 1;
	@Autowired
	SaleRepository saleRepository;
	BillTbl bill;
	List<SalTbl> sale;

	@Autowired
	BillRepository billRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	DealerRepository dealerRepository;

	@Override
	public File print(Integer billId) {
		bill = billRepository.findOne(billId);
		sale = saleRepository.findByBillTbl(bill);
		File file = new File("Bill_number_000"+billId + ".pdf");

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			addMetaData(document);
			addTitlePage(document, bill);
			addContent(document, bill, PageNum);
			addCalc(document, bill, PageNum);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return file;
	}

	public static void addMetaData(Document document) {
		document.addTitle("Invoice");
		document.addSubject("Aakruth");
		document.addKeywords("Invoice");
		document.addAuthor("Aakruth Bio-Med");
		document.addCreator("Emmanual Solution");
	}

	public void addTitlePage(Document document, BillTbl bill) throws DocumentException {
		String Billnum = "000" + bill.getBillId();
		UsrTbl user = bill.getUsrTbl();
		Dealer customer = bill.getDealer();
		try {
			Paragraph p;
			p = new Paragraph("INVOICE", blueFontTitle);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			PdfPTable headtable = new PdfPTable(2);
			headtable.setWidthPercentage(100);
			headtable.setSpacingBefore(10);
			headtable.setSpacingAfter(10);
			headtable.setWidths(new int[] { 12, 4 });
			PdfPCell address = new PdfPCell();
			Phrase brand = new Phrase();
			brand.add(new Chunk("AAKRUTH BIO-MED", brandFont));
			address.addElement(brand);
			Phrase line1 = new Phrase();
			line1.add(new Chunk("44, Durga Parameswari Nilaya, 5th cross, Vinayakanagar", smallBold));
			address.addElement(line1);
			Phrase line2 = new Phrase();
			line2.add(new Chunk("Whitefield, Bangalore - 560066, Karnataka", smallBold));
			address.addElement(line2);
			Phrase line3 = new Phrase();
			line3.add(new Chunk("Ph No: +91 9916 57 2580, +91 9341 08 4730", smallBold));
			address.addElement(line3);
			headtable.addCell(address);

			PdfPCell invoice = new PdfPCell();
			Phrase invonbr = new Phrase();
			invonbr.add(new Chunk("Invoice    : ", smallBold));
			invonbr.add(new Chunk(Billnum, normal12i));
			invoice.addElement(invonbr);
			Phrase invodte = new Phrase();
			invodte.add(new Chunk("Date        : ", smallBold));
			invodte.add(new Chunk((convertDate(bill.getStrdte(), "dd,MMM,yyyy")), normal12i));
			invoice.addElement(invodte);
			Phrase invousr = new Phrase();
			invousr.add(new Chunk("Engineer  :  ", smallBold));
			invousr.add(new Chunk(user.getUsrnme(), normal12i));
			invoice.addElement(invousr);
			headtable.addCell(invoice);
			document.add(headtable);

			p = new Paragraph("Customer Details", subFont);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			PdfPTable clienttable = new PdfPTable(2);
			clienttable.setWidthPercentage(100);
			clienttable.setSpacingBefore(10);
			clienttable.setSpacingAfter(10);
			clienttable.setWidths(new int[] { 7, 7 });
			PdfPCell cellCust = new PdfPCell();
			Phrase custnme = new Phrase();
			custnme.add(new Chunk("Customer Name     :", smallBold));
			custnme.add(new Chunk(customer.getName(), normal12i));
			cellCust.addElement(custnme);
			Phrase custpoc = new Phrase();
			custpoc.add(new Chunk("Point Of Contact    :", smallBold));
			custpoc.add(new Chunk(customer.getPoc(), normal12i));
			cellCust.addElement(custpoc);
			Phrase custPo = new Phrase();
			custPo.add(new Chunk("Purchase Order No:", smallBold));
			custPo.add(new Chunk(bill.getPoNum(), normal12i));
			cellCust.addElement(custPo);
			cellCust.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
			clienttable.addCell(cellCust);
			document.add(clienttable);
			PdfPCell cellCustDet = new PdfPCell();
			Phrase custAdr = new Phrase();
			custAdr.add(new Chunk("Address                :", smallBold));
			custAdr.add(new Chunk(customer.getAdr(), normal12i));
			cellCustDet.addElement(custAdr);
			Phrase custPhn = new Phrase();
			custPhn.add(new Chunk("Contact Number :", smallBold));
			custPhn.add(new Chunk(customer.getPhnnbr(), normal12i));
			cellCustDet.addElement(custPhn);
			Phrase custEmail = new Phrase();
			custEmail.add(new Chunk("Email                    :", smallBold));
			custEmail.add(new Chunk(customer.getEmail(), normal12i));
			cellCustDet.addElement(custEmail);
			cellCustDet.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
			clienttable.addCell(cellCustDet);
			document.add(clienttable);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertDate(Date d, String newFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
		return sdf.format(d);
	}

	public void addContent(Document document, BillTbl bill, Integer PageNum) throws DocumentException {
		Integer sno = 1;
		Integer limit = 25;
		Integer last;
		PdfPTable conten = new PdfPTable(5);
		conten.setWidthPercentage(100);
		conten.setSpacingBefore(10);
		conten.setWidths(new int[] { 2, 7, 2, 3, 3 });
		conten.addCell(getCell("S.no:", Element.ALIGN_CENTER, whiteFont, "Header"));
		conten.addCell(getCell("Description:", Element.ALIGN_CENTER, whiteFont, "Header"));
		conten.addCell(getCell("Qty:", Element.ALIGN_CENTER, whiteFont, "Header"));
		conten.addCell(getCell("Unit Price:", Element.ALIGN_RIGHT, whiteFont, "Header"));
		conten.addCell(getCell("Total:", Element.ALIGN_RIGHT, whiteFont, "Header"));
		conten.setHeaderRows(1);
		
		for (SalTbl item : sale) {
			PrdTbl product = item.getPrdTbl();

			conten.addCell(getCell(Integer.toString(sno), Element.ALIGN_CENTER, normal12, "Item"));
			conten.addCell(getCell(product.getPrdnme(), Element.ALIGN_CENTER, normal12, "Item"));
			conten.addCell(getCell(Integer.toString(item.getCnt()), Element.ALIGN_CENTER, normal12, "Item"));
			conten.addCell(getCell(String.valueOf(item.getAmt().setScale(2, BigDecimal.ROUND_HALF_UP)),
					Element.ALIGN_RIGHT, normal12, "Item"));
			conten.addCell(getCell(String.valueOf(
					item.getAmt().multiply(new BigDecimal(item.getCnt())).setScale(2, BigDecimal.ROUND_HALF_UP)),
					Element.ALIGN_RIGHT, normal12, "Item"));

			if (sno % limit == 0) {

				String Ind = "Last";
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell("Page No : " + PageNum, Element.ALIGN_RIGHT, tiny10i, "page"));
				document.add(conten);
				conten.flushContent();
				document.newPage();
				addTitlePage(document, bill);
				PageNum++;
				// limit = limit + 5;
			}
			sno++;
		}
		if (sno % limit != 0) {
			for (; sno < limit; sno++) {
				String Ind = "Footer";
				if (sno % limit == 0) {
					Ind = "Last";
				}
				last = (sno % limit);
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
				conten.addCell(getCell(" ", normal12, Ind));
			}
		}

		document.add(conten);

	}

	public PdfPCell getCell(String value, int alignment, Font font, String indicator) {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		Paragraph p = new Paragraph(value, font);
		p.setAlignment(alignment);
		if (indicator == "Header") {
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setBorderWidth(1);
			cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
		}
		if (indicator == "Item") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setBorderWidth(1);
			cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		}
		if (indicator == "Calc") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setBorderWidth(1);
			cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
		}
		
		if (indicator == "page") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setColspan(5);
			cell.setBorder(Rectangle.NO_BORDER);
		}

		cell.addElement(p);
		return cell;
	}

	public PdfPCell getCell(String value, Font font, String indicator) {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		Paragraph p = new Paragraph(value, font);
		if (indicator == "Footer") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setBorderWidth(1);
			cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		}
		if (indicator == "Last") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setBorderWidth(1);
			cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
		}
		if (indicator == "blank") {
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
		}
		cell.addElement(p);
		return cell;
	}

	public void addCalc(Document document, BillTbl bill, Integer PageNum) throws DocumentException {
		BigDecimal saledet = saleRepository.findTotal(bill.getBillId());
		BigDecimal tax = bill.getTax().multiply(saledet).divide(new BigDecimal(100)).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		PdfPTable Calculat = new PdfPTable(3);
		Calculat.setWidthPercentage(100);
		Calculat.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Calculat.setSpacingAfter(10);
		Calculat.setWidths(new int[] { 9,5,3 });
		Phrase Tin = new Phrase();
		Tin.add(new Chunk("TIN  :  ", smallBold));
		Tin.add(new Chunk("29771263547 ", normal12i));
		Calculat.addCell(getCell(Tin, Element.ALIGN_LEFT));
		Calculat.addCell(getCell("Total               :", Element.ALIGN_LEFT, LargeBold, "Calc"));
		Calculat.addCell(getCell(String.valueOf((saledet).setScale(2, BigDecimal.ROUND_HALF_UP)), Element.ALIGN_RIGHT,
				LargeBold, "Calc"));
		Phrase CST = new Phrase();
		CST.add(new Chunk("CST  :  ", smallBold));
		CST.add(new Chunk("29771263547 ", normal12i));
		Calculat.addCell(getCell(CST, Element.ALIGN_LEFT));
		Calculat.addCell(getCell("VAT %           :", Element.ALIGN_LEFT, LargeBold, "Calc"));
		Calculat.addCell(getCell(String.valueOf(bill.getTax() + "%"), Element.ALIGN_RIGHT, LargeBold, "Calc"));
		Phrase PAN = new Phrase();
		PAN.add(new Chunk("PAN  :  ", smallBold));
		PAN.add(new Chunk("AANCA3946M ", normal12i));
		Calculat.addCell(getCell(PAN, Element.ALIGN_LEFT));
		Calculat.addCell(getCell("TAX               :", Element.ALIGN_LEFT, LargeBold, "Calc"));
		Calculat.addCell(getCell(String.valueOf(tax), Element.ALIGN_RIGHT, LargeBold, "Calc"));
		Calculat.addCell(getCell(" ", Element.ALIGN_LEFT, LargeBold, "blank"));
		Calculat.addCell(getCell("Grand Total  :", Element.ALIGN_LEFT, LargeBold, "Calc"));
		Calculat.addCell(getCell(String.valueOf(saledet.add(tax)), Element.ALIGN_RIGHT, LargeBold, "Calc"));
		
		document.add(Calculat);

		PdfPTable FootTable = new PdfPTable(2);
		FootTable.setWidthPercentage(100);
		FootTable.setSpacingBefore(10);
		FootTable.setSpacingAfter(10);
		FootTable.setWidths(new int[] { 7, 7 });
		PdfPCell declaration = new PdfPCell();
		Phrase declare = new Phrase();
		declare.add(new Chunk("DECLARATION", smallBold));
		declaration.addElement(declare);
		Phrase line1 = new Phrase();
		line1.add(new Chunk("1. Payments within 30 days from the Bill date ", smallBold));
		declaration.addElement(line1);
		Phrase line2 = new Phrase();
		line2.add(new Chunk("2. Interest will be charged at 24% after due date ", smallBold));
		declaration.addElement(line2);

		FootTable.addCell(declaration);

		PdfPCell Seal = new PdfPCell();
		Phrase companyseal = new Phrase();
		companyseal.add(new Chunk("Company Seal", smallBold));
		Seal.addElement(companyseal);
		Phrase seal1 = new Phrase();
		seal1.add(new Chunk(" ", smallBold));
		Seal.addElement(seal1);
		Phrase seal2 = new Phrase();
		seal2.add(new Chunk("", smallBold));
		Seal.addElement(seal2);
		FootTable.addCell(Seal);
		FootTable.addCell(getCell(" Thank You For Your Business ", Element.ALIGN_CENTER, normal14i, "page"));
		
		document.add(FootTable);

	}

	public PdfPCell getCell(Phrase tin, int align) {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setUseDescender(true);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorderWidth(1);
		cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
		cell.addElement(tin);
		return cell;
	}

}
