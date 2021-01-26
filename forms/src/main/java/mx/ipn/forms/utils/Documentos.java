package mx.ipn.forms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Documentos {

  private static final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir");

  private static String getVariableName(String value) {
    int idx1 = value.indexOf('(');
    int idx2 = value.lastIndexOf(')');

    return value.substring(idx1 + 1, idx2).trim();
  }

  public static File transformTemplate(File template, Map<String, Object> variables) throws DocumentException {
    try {
      return transformTemplate(new FileInputStream(template), variables);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new DocumentException("El template no existe");
    }
  }

  public static File transformTemplate(InputStream template, Map<String, Object> variables) throws DocumentException {
    try {
      XWPFDocument doc = new XWPFDocument(template);

      for (XWPFParagraph para : doc.getParagraphs()) {
        List<XWPFRun> runs = para.getRuns();

        if (runs != null) {
          for (XWPFRun run : runs) {
            String text = run.getText(0);

            if (text == null)
              continue;

            if (text.startsWith("$(") && text.endsWith(")$")) {
              System.out.println(text);

              String varName = getVariableName(text);
              Object varValue = variables.get(varName);

              if (varValue == null) {
                run.setText(" ", 0);
              } else {
                System.out.println(varValue);
                System.out.println(varValue.getClass());

                if (varValue instanceof Image) {
                  Image image = (Image) varValue;

                  run.setText("", 0);
                  run.addPicture(image.getData(), image.getType().getValue(), image.getName(),
                      Units.toEMU(image.getWidth()), Units.toEMU(image.getHeight()));
                } else if (varValue instanceof List<?>) {
                  boolean setText = false;

                  for (Object value : (List<?>) varValue) {
                    if (value != null) {
                      if (value instanceof Image) {
                        Image image = (Image) value;

                        if (!setText) {
                          run.setText("", 0);
                          setText = true;
                        }

                        run.addPicture(image.getData(), image.getType().getValue(), image.getName(),
                            Units.toEMU(image.getWidth()), Units.toEMU(image.getHeight()));
                      }
                    } else {
                      // TODO: Implementar para lista de otross valores
                    }
                  }
                } else {
                  run.setText(varValue.toString() + " ", 0);
                }
              }
            }
          }
        }
      }

      if (variables != null && variables.size() > 0) {
        List<XWPFTable> tables = doc.getTables();

        for (XWPFTable table : tables) {
          List<XWPFTableRow> rows = table.getRows();

          for (XWPFTableRow row : rows) {
            for (int i = 0; i < row.getTableCells().size(); i++) {
              List<XWPFParagraph> paragraphs = row.getCell(i).getParagraphs();

              for (XWPFParagraph para : paragraphs) {
                List<XWPFRun> runs = para.getRuns();

                for (XWPFRun run : runs) {
                  String text = run.getText(0);

                  if (text == null)
                    continue;

                  if (text.startsWith("$(") && text.endsWith(")$")) {
                    System.out.println(text);

                    String varName = getVariableName(text);
                    Object varValue = variables.get(varName);

                    if (varValue == null) {
                      run.setText(" ", 0);
                    } else {
                      System.out.println(varValue);
                      System.out.println(varValue.getClass());

                      if (varValue instanceof Image) {
                        Image image = (Image) varValue;

                        run.setText("", 0);
                        run.addPicture(image.getData(), image.getType().getValue(), image.getName(),
                            Units.toEMU(image.getWidth()), Units.toEMU(image.getHeight()));
                      } else if (varValue instanceof List<?>) {
                        boolean setText = false;

                        for (Object value : (List<?>) varValue) {
                          if (value != null) {
                            if (value instanceof Image) {
                              Image image = (Image) value;

                              if (!setText) {
                                run.setText("", 0);
                                setText = true;
                              }

                              run.addPicture(image.getData(), image.getType().getValue(), image.getName(),
                                  Units.toEMU(image.getWidth()), Units.toEMU(image.getHeight()));
                            }
                          } else {
                            // TODO: Implementar para lista de otross valores
                          }
                        }
                      } else {
                        run.setText(varValue.toString() + " ", 0);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }

      File word = new File(TEMP_DIRECTORY + File.separator + System.currentTimeMillis() + ".docx");

      FileOutputStream out = new FileOutputStream(word);
      doc.write(out);
      out.close();
      try {
        doc.close();
      } catch (Throwable e) {
      }

      return word;
    } catch (IOException e) {
      e.printStackTrace();
      throw new DocumentException("No se pudo leer el template");
    } catch (InvalidFormatException e) {
      e.printStackTrace();
      throw new DocumentException("Error al agregar la imagen");
    }
  }

}
