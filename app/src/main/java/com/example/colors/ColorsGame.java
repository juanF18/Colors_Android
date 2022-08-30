package com.example.colors;

import android.graphics.Color;
import java.util.Random;

public class ColorsGame {

  //Constructor
  public ColorsGame() {
    restarGame();
  }

  /*
   * Las librerias de android trabajan los colores
   * como enteros ( int )
   */

  private int targetBackColor = 0;
  private int targetTextColor = 0;
  private int propossedBackColor = 0;
  private int propossedTextColor = 0;

  private OnChangeTargetColorListener onChangeTargetColorListener;
  private OnChangePropossedColorListener onChangePropossedColorListener;

  /*
   * Crea numero aletorios con Random y nos retorna un nuevo
   * color Con el metodo propietario de Android Color
   */
  public static int randomColor() {
    Random rand = new Random();
    int redVal = rand.nextInt(256);
    int greenVal = rand.nextInt(256);
    int blueVal = rand.nextInt(256);

    return Color.rgb(redVal, greenVal, blueVal);
  }

  /*
   *   retorna el color sugerido para el fondo
   *  (blanco o negro)
   */
  public static int getSuggestedTextColor(int backColor) {
    int redVal = Color.red(backColor);
    int greenVal = Color.green(backColor);
    int blueVal = Color.blue(backColor);
    int grayVal = (int) (redVal + greenVal + blueVal) / 3;
    //int grayVal = (int)(redVal * 0.20 + greenVal * 0.75 + blueVal * 0.05);
    int textColor = Color.BLACK;

    if (255 - grayVal > grayVal) {
      textColor = Color.WHITE;
    }
    return textColor;
  }

  /*
   * Retorna la distancia entre 2 colores
   *
   */

  public static double distance(int color1, int color2) {
    int redVal1 = Color.red(color1);
    int greenVal1 = Color.green(color1);
    int blueVal1 = Color.blue(color1);

    int redVal2 = Color.red(color2);
    int greenVal2 = Color.green(color2);
    int blueVal2 = Color.blue(color2);

    //resultado parcial
    int resRedVal = (int) Math.pow((redVal1 - redVal2), 2);
    int resGreenVal = (int) Math.pow((greenVal1 - greenVal2), 2);
    int resBlueVal = (int) Math.pow((blueVal1 - blueVal2), 2);

    // Calcula la distancia entre 2 colores (puntos en 3D)
    return Math.sqrt(resRedVal + resGreenVal + resBlueVal);
  }

  /*
   *   obtener el puntaje
   */
  public int getScore() {
    double distance = distance(targetBackColor, propossedBackColor);

    // Retorna el puntaje.
    return (int) (100 - Math.min(distance, 100));
  }

  public void restarGame() {
    setTargetBackColor(randomColor());
  }

  /*
   * independizar el manejo de las funciones de captura de los colores
   *
   */
  public interface OnChangeTargetColorListener {
    void onChange(int backColor, int textColor);
  }

  public interface OnChangePropossedColorListener {
    void onChange(int backColor, int textColor);
  }

  /*
   * Getter and setters for Propossed
   * */
  public OnChangePropossedColorListener getOnChangePropossedColorListener() {
    return onChangePropossedColorListener;
  }

  public void setOnChangePropossedColorListener(
    OnChangePropossedColorListener onChangePropossedColorListener
  ) {
    this.onChangePropossedColorListener = onChangePropossedColorListener;
  }

  /*
   * Getters and setters for Target
   */

  public OnChangeTargetColorListener getOnChangeTargetColorListener() {
    return onChangeTargetColorListener;
  }

  public void setOnChangeTargetColorListener(
    OnChangeTargetColorListener onChangeTargetColorListener
  ) {
    this.onChangeTargetColorListener = onChangeTargetColorListener;
  }

  /*
   * Target set and get
   */

  public int getTargetBackColor() {
    return targetBackColor;
  }

  public void setTargetBackColor(int newBackColor) {
    targetBackColor = newBackColor;
    targetTextColor = getSuggestedTextColor(targetBackColor);
    do {
      propossedBackColor = randomColor();
    } while (getScore() > 0);

    propossedTextColor = getSuggestedTextColor(propossedBackColor);

    if (getOnChangeTargetColorListener() != null) {
      getOnChangeTargetColorListener()
        .onChange(targetBackColor, targetTextColor);
    }

    if (getOnChangePropossedColorListener() != null) {
      getOnChangePropossedColorListener()
        .onChange(propossedBackColor, propossedTextColor);
    }
  }

  public int getTargetTextColor() {
    return targetTextColor;
  }

  public void setTargetTextColor(int targetTextColor) {
    this.targetTextColor = targetTextColor;
  }

  /*
   * Propossed set an get
   *
   */

  public int getPropossedBackColor() {
    return propossedBackColor;
  }

  public void setPropossedBackColor(int newBackColor) {
    this.propossedBackColor = newBackColor;
    propossedTextColor = getSuggestedTextColor(propossedBackColor);

    if (getOnChangePropossedColorListener() != null) {
      getOnChangePropossedColorListener()
        .onChange(propossedBackColor, propossedTextColor);
    }
  }

  public int getPropossedTextColor() {
    return propossedTextColor;
  }

  public void setPropossedTextColor(int propossedTextColor) {
    this.propossedTextColor = propossedTextColor;
  }
}
