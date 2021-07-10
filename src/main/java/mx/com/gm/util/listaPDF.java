package mx.com.gm.util;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.gm.domain.Postulacion;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

@Component("../adminListaAdoptados")
public class listaPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document dcmnt, PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        @SuppressWarnings("uncheked")
         List<Postulacion> listaAdoptados = (List<Postulacion>) map.get("listaAdoptados");
         
         PdfPTable tablaAdoptados = new PdfPTable(1);
         
         
         listaAdoptados.forEach(Postulacion -> {
         tablaAdoptados.addCell(Postulacion.getId_postulacion().toString());
         
         });
        
         dcmnt.add(tablaAdoptados);
    }
    
    
    
}
