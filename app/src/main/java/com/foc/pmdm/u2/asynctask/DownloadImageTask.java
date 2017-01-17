package com.foc.pmdm.u2.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

// Code by javacasm 13-1-2017
// Referencias
// https://guides.codepath.com/android/Creating-and-Executing-Async-Tasks
// http://developer.android.com/reference/android/os/AsyncTask.html

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap>
{
    public ImageView imageView;
    public ProgressBar progressBar;
   //añado el textview
    public TextView textView;

    // Este es el único método obligatorio de implementar
    @Override
    protected Bitmap doInBackground(String... urls) {
               Bitmap bitmap=LoadImageFromWebOperations(urls[0]);
        return bitmap;
    }

    // Este método lo hacemos nosotros...
    public static Bitmap LoadImageFromWebOperations(String url) {
        Bitmap bitmap=null;
        try {
            InputStream is = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            bitmap=null;
        }
        return bitmap;
    }

    // Se llama automáticamente al método antes de empezar con doInBackground desde el Subproceso UI
    protected void onPreExecute() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        //Antes de ejecutar  salta el mensaje
        textView.setText("Realizando descarga en segundo plano");
        textView.setText("Realizando descarga en segundo plano");
        //Pongo una espera para que se vea porque carga muy rápido
        try {
            this.wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Se llama al método cada vez que llamamos a publishProgress
    protected void onProgressUpdate(Integer... values) {

        progressBar.setProgress(values[0]);

    }

    // Se llama automáticamente al método al terminar con doInBackground desde el Subproceso UI
    protected void onPostExecute(Bitmap result) {
        if(result!=null) {
            imageView.setImageBitmap(result);
            imageView.setVisibility(View.VISIBLE);
            // Hide the progress bar
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            textView.setText("Imagen correctamente descargada");
        }
        else        {
            Context context=imageView.getContext();
            textView.setText("Opss algo falló");
            Toast.makeText(context,context.getResources().getText(R.string.error_downloading_image),Toast.LENGTH_LONG).show();
        }
    }
}
