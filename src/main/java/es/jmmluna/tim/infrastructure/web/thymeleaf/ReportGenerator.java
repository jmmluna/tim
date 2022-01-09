package es.jmmluna.tim.infrastructure.web.thymeleaf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import es.jmmluna.tim.application.service.DTO;

@Component
public class ReportGenerator {
	
	@Autowired
	private SpringTemplateEngine templateEngine;

//	private String parseThymeleafTemplate(DTO dto, String fieldName) {
//		Context context = new Context();		
////		context.setVariable("budget", dto);
//		context.setVariable(fieldName, dto);
//
//		return templateEngine.process("budget/budget-report", context);
//	}
	
	private String getParseTemplate(String template, DTO dto, String fieldName) {
		Context context = new Context();		
//		context.setVariable("budget", dto);
		context.setVariable(fieldName, dto);

//		return templateEngine.process("budget/budget-report", context);
		return templateEngine.process(template, context);
	}
	
	public ByteArrayOutputStream generatePdfFromHtml(String template, DTO dto, String fieldName) {
		String html = getParseTemplate(template, dto, fieldName);
		
		
		String urlBase = "/";// "http://localhost:9080";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html, urlBase);

			renderer.layout();
			renderer.createPDF(bos, false);
			renderer.finishPDF();
//			log.info("PDF created correctamente");

			return bos;
		} catch (DocumentException e) {

			throw new RuntimeException();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
//					log.error("Error creando pdf", e);
				}
			}
		}

	}
}
