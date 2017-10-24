package com.aakruth.dao;

import java.io.File;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface PdfService {

	File print(Integer billId);

}
	
	
