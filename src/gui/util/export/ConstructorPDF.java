package gui.util.export;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.openpdf.text.Chunk;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import estadistica.interpretacion.InterpretacionRegresion;
import estadistica.interpretacion.PreguntaInterpretacion;

public final class ConstructorPDF {

	private final InterpretacionRegresion interpretacion;
	private final BufferedImage grafica;
	private final String nombreReporte;
	
	public ConstructorPDF(InterpretacionRegresion interpretacion, BufferedImage grafica, String nombreReporte) {

	    this.interpretacion = interpretacion;
	    this.grafica = grafica;
	    this.nombreReporte = nombreReporte;
	}
	
	private void agregarPregunta(Document documento, PreguntaInterpretacion pregunta) {

	    Font preguntaFont = new Font(Font.HELVETICA, 11, Font.BOLD);
	    Font respuestaFont = new Font(Font.HELVETICA, 10, Font.BOLD);
	    Font detalleFont = new Font(Font.HELVETICA, 10, Font.NORMAL);

	    Paragraph textoPregunta = new Paragraph(pregunta.getPregunta(), preguntaFont);
	    textoPregunta.setSpacingAfter(4);
	    documento.add(textoPregunta);

	    Paragraph respuesta = new Paragraph("Respuesta: " + pregunta.getRespuesta(), respuestaFont);
	    respuesta.setSpacingAfter(4);
	    documento.add(respuesta);

	    Paragraph detalle = new Paragraph(pregunta.getDetalle(), detalleFont);
	    detalle.setSpacingAfter(10);
	    documento.add(detalle);
	}

	public void construir(File archivo) {

		Document documento = new Document();

		try {

			PdfWriter.getInstance(documento, new FileOutputStream(archivo));

			documento.open();
			

			// =========================
			// Encabezado
			// =========================

			Font titulo = new Font(Font.HELVETICA, 22, Font.BOLD);

			Font subtitulo = new Font(Font.HELVETICA, 14, Font.NORMAL);

			Paragraph encabezado = new Paragraph(nombreReporte, titulo);
			
			encabezado.setAlignment(Paragraph.ALIGN_CENTER);

			documento.add(encabezado);

			Paragraph subtituloPDF = new Paragraph("Informe de Regresión Lineal", subtitulo);

			subtituloPDF.setAlignment(Paragraph.ALIGN_CENTER);

			documento.add(subtituloPDF);

			documento.add(new Paragraph(" "));
			

			// =========================
			// Fecha de generación
			// =========================

			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String fechaGeneracion = "Generado: " + LocalDateTime.now().format(formatoFecha);
			Font fechaFont = new Font(Font.HELVETICA, 9, Font.NORMAL);
			Paragraph fecha = new Paragraph(fechaGeneracion, fechaFont);
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			
			documento.add(fecha);

			
			// =========================
			// Separador
			// =========================

			documento.add(new Chunk(" ", new Font(Font.HELVETICA, 1)));

			Paragraph separador = new Paragraph();
			separador.setSpacingBefore(4);
			separador.setSpacingAfter(10);
			separador.add(new Chunk("______________________________________________________________________________"));

			documento.add(separador);

			
			// =========================
			// Resumen del modelo
			// =========================

			Font tituloSeccion = new Font(Font.HELVETICA, 14, Font.BOLD);
			Paragraph tituloResumen = new Paragraph("Resumen del modelo", tituloSeccion);
			tituloResumen.setSpacingAfter(8);

			documento.add(tituloResumen);

			PdfPTable tablaResumen = new PdfPTable(2);

			tablaResumen.setWidthPercentage(100);
			tablaResumen.setWidths(new float[] { 2f, 1f });

			// Encabezados

			PdfPCell encabezadoResultado = new PdfPCell(new Phrase("Resultado"));

			PdfPCell encabezadoValor = new PdfPCell(new Phrase("Valor"));

			encabezadoResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
			encabezadoValor.setHorizontalAlignment(Element.ALIGN_CENTER);

			tablaResumen.addCell(encabezadoResultado);
			tablaResumen.addCell(encabezadoValor);

			// Calidad

			tablaResumen.addCell("Calidad");
			tablaResumen.addCell(interpretacion.getCalidad());

			// R²

			tablaResumen.addCell("R²");
			tablaResumen.addCell(interpretacion.getR2Pct() + " %");

			// Error relativo

			tablaResumen.addCell("Error estándar relativo");
			tablaResumen.addCell(interpretacion.getSeRelPct() + " %");

			// Fuerza

			tablaResumen.addCell("Fuerza de la correlación");
			tablaResumen.addCell(interpretacion.getFuerza());

			// Dirección

			tablaResumen.addCell("Dirección");
			tablaResumen.addCell(interpretacion.getDireccion());

			// Semáforo

			tablaResumen.addCell("Semáforo");
			tablaResumen.addCell(interpretacion.getSemaforo().toString());

			documento.add(tablaResumen);

			documento.add(new Paragraph(" "));

			
			// =========================
			// Interpretación de la pendiente
			// =========================

			Paragraph tituloPendiente = new Paragraph("Interpretación de la pendiente", tituloSeccion);

			tituloPendiente.setSpacingBefore(6);
			tituloPendiente.setSpacingAfter(8);

			documento.add(tituloPendiente);

			String textoPendiente;

			if (interpretacion.getDireccion().equals("positiva")) {

			    textoPendiente = "Por cada unidad que " + interpretacion.getEjemploX()
			            + " aumenta, " + interpretacion.getEjemploY()
			            + " aumenta aproximadamente "
			            + interpretacion.getPendienteVal()
			            + " unidades.";

			} else {

			    textoPendiente =
			            "Por cada unidad que " + interpretacion.getEjemploX()
			            + " aumenta, " + interpretacion.getEjemploY()
			            + " disminuye aproximadamente "
			            + interpretacion.getPendienteVal()
			            + " unidades.";
			}

			Paragraph parrafoPendiente = new Paragraph(textoPendiente);

			parrafoPendiente.setSpacingAfter(10);
			documento.add(parrafoPendiente);
			
			
			// =========================
			// Preguntas de interpretación
			// =========================

			Paragraph tituloPreguntas = new Paragraph("Preguntas de interpretación", tituloSeccion);

			tituloPreguntas.setSpacingBefore(10);
			tituloPreguntas.setSpacingAfter(10);

			documento.add(tituloPreguntas);
			
			//Preguntas 1, 2 y 3
			agregarPregunta(documento, interpretacion.getPregunta1());
			agregarPregunta(documento, interpretacion.getPregunta2());
			agregarPregunta(documento, interpretacion.getPregunta3());
			
			
			// =========================
			// Conclusión
			// =========================

			Paragraph tituloConclusion = new Paragraph("Conclusión", tituloSeccion);

			tituloConclusion.setSpacingBefore(10);
			tituloConclusion.setSpacingAfter(8);
			
			documento.add(tituloConclusion);

			Font conclusionFont = new Font(Font.HELVETICA, 11, Font.NORMAL);

			Paragraph conclusion = new Paragraph(interpretacion.getConclusion(), conclusionFont);
			conclusion.setSpacingAfter(10);
			
			documento.add(conclusion);
			
			
			// =========================
			// Gráfica de regresión
			// =========================

			Paragraph tituloGrafica = new Paragraph("Gráfica de regresión", tituloSeccion);

			tituloGrafica.setSpacingBefore(10);
			tituloGrafica.setSpacingAfter(8);

			documento.add(tituloGrafica);

			if (grafica != null) {

			    try {

			        ByteArrayOutputStream salida = new ByteArrayOutputStream();
			        ImageIO.write(grafica, "png", salida);
			        
			        Image imagenPDF = Image.getInstance(salida.toByteArray());

			        // =========================
			        // Ajustar tamaño
			        // =========================

			        float anchoDisponible =
			                documento.getPageSize().getWidth()
			                - documento.leftMargin()
			                - documento.rightMargin();

			        float anchoOriginal = imagenPDF.getWidth();
			        float altoOriginal = imagenPDF.getHeight();

			        float escala = anchoDisponible / anchoOriginal;

			        if (escala > 1f) {
			            escala = 1f;
			        }

			        imagenPDF.scaleAbsolute(anchoOriginal * escala, altoOriginal * escala);
			        imagenPDF.setAlignment(Image.ALIGN_CENTER);

			        documento.add(imagenPDF);

			    } catch (Exception e) {

			        throw new RuntimeException("No se pudo agregar la gráfica al PDF.", e);

			    }

			}

		} catch (Exception e) {

			throw new RuntimeException("No se pudo construir el PDF.", e);

		} finally {

			documento.close();

		}

	}

	public InterpretacionRegresion getInterpretacion() {
		return interpretacion;
	}

	public BufferedImage getGrafica() {
		return grafica;
	}
}