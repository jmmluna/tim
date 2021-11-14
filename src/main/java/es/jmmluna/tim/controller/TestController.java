package es.jmmluna.tim.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/view1")
	public String hola(Model model) {
		DateFormat dateFormat = new SimpleDateFormat();
		log.info("Plantilla de vista 1");
		model.addAttribute("mensaje", "hola desde mi controlador");
		model.addAttribute("serverTime", dateFormat.format(new Date()));
		return "vistas/vista1";
	}
	
//	public void generatePdfFromHtml(String html) {
//	    String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//	    OutputStream outputStream = new FileOutputStream(outputFolder);
//
//	    ITextRenderer renderer = new ITextRenderer();
//	    renderer.setDocumentFromString(html);
//	    renderer.layout();
//	    renderer.createPDF(outputStream);
//
//	    outputStream.close();
//	}
}
