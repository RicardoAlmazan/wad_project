package mx.ipn.forms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
  private static final SimpleDateFormat DATE_FORMAT_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  static {
    DATE_FORMAT_ISO.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
  }

  public static String toISO(Date date) {
    return DATE_FORMAT_ISO.format(date);
  }
}
