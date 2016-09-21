package com.example.lenovo.talenthunt;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.ProgressDialog;
import java.net.URLEncoder;
/**
 * Created by Belal on 11/22/2015.
 */
public class Upload {

    public static final String UPLOAD_URL= "http://192.168.0.110/VideoUpload/uploads/upload.php";

    private int serverResponseCode;
    ProgressDialog dialog = null;

        public String uploadVideo(String file,Context context) throws IOException {

            String fileName = file;
            StringBuilder result;
            String upLoadServerUri = null;
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(file);

            if (!sourceFile.isFile()) {

                dialog.dismiss();

                Log.e("uploadFile", "Source File not exist :"
                        + fileName);

                Toast.makeText(context, "Source file does not exist ",
                        Toast.LENGTH_SHORT).show();
                return "failed";
            } else {
                try {
                    upLoadServerUri = "http://192.168.0.111/VideoUpload/uploads/upload.php";
                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(upLoadServerUri);

                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("uploaded_file", fileName);

                    dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=" + "uploaded_file" + ";filename=\""
                            + fileName + "\"" + lineEnd);
                    String s="Content-Disposition: form-data; name=" + "uploaded_file" + ";filename=\""
                            + fileName + "\"" + lineEnd;
                    dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {

                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    }

                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                    Log.i("uploadFile", "HTTP Response is : "
                            + serverResponseMessage + ": " + serverResponseCode);


                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();
                    try {
                        //Receive the response from the server
                        InputStream in = new BufferedInputStream(conn.getInputStream());

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        Log.d("JSON Parser", "result: " + result.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //return "successfull";
                } catch (MalformedURLException ex) {

                    dialog.dismiss();
                    ex.printStackTrace();

                    Toast.makeText(context, "MalformedURLException",
                            Toast.LENGTH_SHORT).show();


                    Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {

                    dialog.dismiss();
                    e.printStackTrace();

                    Toast.makeText(context, "Got Exception : see logcat ",
                            Toast.LENGTH_SHORT).show();

                    Log.e("Upload file ", "Exception : "
                            + e.getMessage(), e);
                }
                dialog.dismiss();


            } // End return "successfull"; block
            return "successfull";
        }

}