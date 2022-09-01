package com.example.colors;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  // crear atributos sobre todos los elementos
  private TextView IblProposedColor;
  private TextView IblTargetColor;
  private SeekBar sbrRed;
  private SeekBar sbrGreen;
  private SeekBar sbrBlue;
  private Button btnGetScore;
  private Button btnNewColor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void initView() {
    /*
     * Captura todos los los elemento del view (del archivo xml)
     */

    IblProposedColor = findViewById(R.id.IblProposedColor);
    IblTargetColor = findViewById(R.id.IblTargetColor);
    sbrRed = findViewById(R.id.sbrRed);
    sbrGreen = findViewById(R.id.sbrGreen);
    sbrBlue = findViewById(R.id.sbrBlue);
    btnGetScore = findViewById(R.id.btnGetScore);
    btnNewColor = findViewById(R.id.btnNewColor);
  }
}
