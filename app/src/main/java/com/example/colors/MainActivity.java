package com.example.colors;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  // crear atributos sobre todos los elementos
  private TextView IblProposedColor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void initView() {
    IblProposedColor = findViewById(R.id.IblProposedColor);
  }
}
