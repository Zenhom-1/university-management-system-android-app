package com.example.studentasu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));

        setContentView(R.layout.activity_guide);

        PDFView pdfView = findViewById(R.id.pdfview);

        pdfView.fromAsset("studentguide.pdf").load();
    }
}