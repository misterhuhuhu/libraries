package com.example.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ItextApplication {
    
    public static void main(String[] args) throws DocumentException, IOException {
//        SpringApplication.run(ItextApplication.class, args);
        Map<String, Object> data = new HashMap<String, Object>();//要插入的数据
        data.put("fireLocation", "测试");
        data.put("simulateDuration", "测试2");
        //初始化itext
        //设置编码
        BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\MR.WHO\\Desktop\\火灾烟气2.pdf");) {
            PdfReader pdfReader = new PdfReader("C:\\Users\\MR.WHO\\Desktop\\火灾烟气.pdf");
            PdfStamper pdfStamper = new PdfStamper(pdfReader, fileOutputStream);
            AcroFields form = pdfStamper.getAcroFields();
            form.addSubstitutionFont(baseFont);
            
            //写入数据
            for (String key : data.keySet()) {
                String value = data.get(key).toString();
                //key对应模板数据域的名称
                form.setField(key, value, true);
            }
            
            
            Rectangle pageSize = pdfReader.getPageSize(1);
            Document document = new Document(pageSize);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            PdfContentByte directContent = writer.getDirectContent();
            PdfContentByte cbUnder = directContent;
            PdfImportedPage pageTemplate = writer.getImportedPage(pdfReader, 1);
            cbUnder.addTemplate(pageTemplate, 0, 0);
            
            /**/
            PdfPTable table = new PdfPTable(3);
//            table.setTotalWidth(new float[]{120, 120, 120});
            table.setLockedWidth(true);
//            table.setWidthPercentage(50);
//            table.setHorizontalAlignment(Element.ALIGN_CENTER);//居中
            table.setTotalWidth(document.right() - document.left());
//        datatable.setTotalWidth(document.right(document.rightMargin()) - document.left(document.leftMargin()));
            directContent.saveState();
            table.writeSelectedRows(0, -1, document.left(),
                    table.getTotalHeight() + document.bottom(), directContent);
            directContent.restoreState();
            Font textFont = new Font(baseFont, 6, Font.NORMAL);
            
            //表头模拟数据
            for (int i = 0; i < 3; i++) {
                PdfPCell heardCell = new PdfPCell();
                heardCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                heardCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                heardCell.setPhrase(new Phrase("value", textFont));
                table.addCell(heardCell);
            }
            //表格数据
            DecimalFormat df = new DecimalFormat("0.00");
            for (int i = 0; i < 70; i++) {
                PdfPCell value1 = new PdfPCell();
                value1.setPhrase(new Phrase("2022:" + i, textFont));
                table.addCell(value1);
                Double value = Math.random() * 10;
                PdfPCell value2 = new PdfPCell();
                value2.setPhrase(new Phrase(df.format(value), textFont));
                
            }
            
            document.add(table);
            
            document.close();
//            pdfStamper.close();
            
        }
        
    }
    
}
