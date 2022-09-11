package com.example.colors;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  // crear atributos sobre todos los elementos
  private TextView IblProposedColor;
  private TextView IblTargetColor;
  private SeekBar sbrRed;
  private SeekBar sbrGreen;
  private SeekBar sbrBlue;
  private TextView IbLRedTitle;
  private TextView IbLBlueTitle;
  private TextView IbLGreenTitle;
  private TextView IblRedValue;
  private TextView IblGreenValue;
  private TextView IblBlueValue;
  private Button btnGetScore;
  private Button btnNewColor;
  private ColorsGame colorsGame;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();

    colorsGame = new ColorsGame();

    //Cambia el color del texto y del label target
    colorsGame.setOnChangeTargetColorListener((backColor, textColor) ->{
      IblTargetColor.setBackgroundColor(backColor);
      IblTargetColor.setTextColor(textColor);
    });

    //Cambia el color del texto y el label de Popossed
    colorsGame.setOnChangePropossedColorListener(((backColor, textColor) -> {
      IblProposedColor.setBackgroundColor(backColor);
      IblProposedColor.setTextColor(textColor);

      sbrRed.setProgress(Color.red(backColor));
      IblRedValue.setText(String.valueOf(Color.red(backColor)));
      sbrGreen.setProgress(Color.green(backColor));
      IblGreenValue.setText(String.valueOf(Color.green(backColor)));
      sbrBlue.setProgress(Color.blue(backColor));
      IblBlueValue.setText(String.valueOf(Color.blue(backColor)));
    }));

    restartGame();
    initEvents();

  }

  public void initView() {
    /*
     * Captura todos los los elemento del view (del archivo xml)
     */
    //Conexion de los controles sobre la interfaz

    IblProposedColor = findViewById(R.id.IblProposedColor);
    IblTargetColor = findViewById(R.id.IblTargetColor);

    sbrRed = findViewById(R.id.sbrRed);
    sbrGreen = findViewById(R.id.sbrGreen);
    sbrBlue = findViewById(R.id.sbrBlue);

    btnGetScore = findViewById(R.id.btnGetScore);
    btnNewColor = findViewById(R.id.btnNewColor);

    IbLRedTitle = findViewById(R.id.IbLRedTitle);
    IbLBlueTitle = findViewById(R.id.IblBlueTitle);
    IbLGreenTitle = findViewById(R.id.IblGreenTitle);
    IblRedValue = findViewById(R.id.IblRedValue);
    IblGreenValue = findViewById(R.id.IblGreenValue);
    IblBlueValue = findViewById(R.id.IblBlueValue);
  }

  /*
    * Sirve para escuchar la modificacion de las Seek Bars que son las
    * que son las que modifican el valor de cada color (R, G, B)
   */
  public void initEvents(){
    SeekBar[] seekBars = {sbrRed, sbrGreen, sbrBlue};

    for (SeekBar seekBar: seekBars){
      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          updateValues();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });
    }

    btnGetScore.setOnClickListener(view -> showScore());
    btnNewColor.setOnClickListener(view -> restartGame());
  }

  public void updateValues(){
    int redValue = sbrRed.getProgress();
    int greenValue = sbrGreen.getProgress();
    int blueValue = sbrBlue.getProgress();

    int newBackColor = Color.rgb(redValue, greenValue, blueValue);

    colorsGame.setPropossedBackColor(newBackColor);
  }

  public void showScore(){
    final String RED = getString(R.string.Red);
    final String GREEN = getString(R.string.Green);
    final String BLUE = getString(R.string.Blue);
    final String VERY_LOW = getString(R.string.Very_low);
    final String LOW = getString(R.string.Low);
    final String VERY_HIGH = getString(R.string.Very_high);
    final String HIGH = getString(R.string.High);

    int targetColor = colorsGame.getTargetBackColor();
    int proposedColor = colorsGame.getPropossedBackColor();

    AlertDialog.Builder alert = new AlertDialog.Builder(this);
    StringBuilder text = new StringBuilder();
    StringBuilder tips = new StringBuilder();

    int redDiff = Color.red(targetColor) - Color.red(proposedColor);
    int greenDiff = Color.green(targetColor) - Color.green(proposedColor);
    int blueDiff = Color.blue(targetColor) - Color.blue(proposedColor);

    text.append(getString(R.string.Your_score_is, String.valueOf(colorsGame.getScore())));

    if(redDiff > 10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), VERY_LOW.toLowerCase()));
    } else if( redDiff > 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), LOW.toLowerCase()));
    } else if( redDiff < -10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), VERY_HIGH.toLowerCase()));
    } else if(redDiff < 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), HIGH.toLowerCase()));
    }

    if(greenDiff > 10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), VERY_LOW.toLowerCase()));
    } else if (greenDiff > 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), LOW.toLowerCase()));
    } else if(greenDiff < -10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), VERY_HIGH.toLowerCase()));
    } else if (greenDiff < 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), HIGH.toLowerCase()));
    }

    if(blueDiff > 10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), VERY_LOW.toLowerCase()));
    }else if(blueDiff > 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), LOW.toLowerCase()));
    } else if ( blueDiff < -10){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), VERY_HIGH.toLowerCase()));
    } else if(blueDiff < 0){
      tips.append("\n");
      tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), HIGH.toLowerCase()));
    }

    if(tips.length() > 0){
      text.append("\n\n");
      text.append(getString(R.string.Tips));
      text.append(": ");
      text.append(tips);
    }

    alert.setMessage(text);
    alert.setPositiveButton(getString(R.string.Close), null);

    alert.show();
  }

  public void restartGame(){
    colorsGame.restarGame();
  }
}
