package com.spring.poc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class SVGTOPDFController {

	@RequestMapping(value = "/svgtopdf", method = RequestMethod.POST)
	public @ResponseBody
	String svgtopdfConverter( @RequestParam("file") MultipartFile file ,HttpServletResponse response) throws IOException {
		OutputStream out = null;
		try{
			//is = this.getClass().getClassLoader().getResourceAsStream("svg/chessboard.svg");
			out = response.getOutputStream();
			//Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		    String fileName = file.getName();
			InputStream fileContent = file.getInputStream();
			TranscoderInput input_svg_image = new TranscoderInput(fileContent);    
	        TranscoderOutput output_pdf_document = new TranscoderOutput(out);         
	        Transcoder transcoder = new PDFTranscoder();       
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	        transcoder.transcode(input_svg_image, output_pdf_document);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally {
            out.close();
    }
		return "File from SVG to PDF Converted Successfully";
		
		
	}
}
