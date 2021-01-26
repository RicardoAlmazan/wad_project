package mx.ipn.forms.utils;

import java.io.InputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Image {
  public enum Type {
    PNG(XWPFDocument.PICTURE_TYPE_PNG), JPG(XWPFDocument.PICTURE_TYPE_JPEG), GIF(XWPFDocument.PICTURE_TYPE_GIF);

    private final int value;

    private Type(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  };

  private InputStream data;
  private String name;
  private Type type;
  private int width;
  private int height;

  public InputStream getData() {
    return data;
  }

  public void setData(InputStream data) {
    this.data = data;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}