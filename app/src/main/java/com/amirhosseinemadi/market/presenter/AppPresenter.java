package com.amirhosseinemadi.market.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import com.amirhosseinemadi.market.common.AppCls;
import com.amirhosseinemadi.market.model.entity.Comment;
import com.amirhosseinemadi.market.model.request.ResponseListener;
import com.amirhosseinemadi.market.view.AppView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AppPresenter {

    private static AppView appView;
    private List<Comment> comments;
    private String name;

    public AppPresenter(AppView appView, String slider, String name)
    {
        AppPresenter.appView = appView;
        this.name = name;

        List<String> list = new ArrayList<>();
        for (int i = 1; i<=Integer.valueOf(slider);i++)
        {
            list.add(String.valueOf(i));
        }


        AppCls.component.apiCaller().getComment(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                comments = (List<Comment>) response;
                appView.initLayout(list,comments);

            }

            @Override
            public void onFailed(String error) {


            }
        },name);


        AppCls.component.apiCaller().getStars(new ResponseListener() {
            @Override
            public void onResponse(Object response) {

                Float f = (Float) response;
                appView.initRate(f);

            }

            @Override
            public void onFailed(String error) {


            }
        },name);


    }



    public void addComment(String username,String comment,int stars)
    {
      appView.showProgress();
      AppCls.component.apiCaller().addComment(new ResponseListener() {
          @Override
          public void onResponse(Object response)
          {
              appView.hideProgress();
              appView.comment();
          }

          @Override
          public void onFailed(String error)
          {
              appView.hideProgress();
              appView.connectionError();
          }
      },name,username,stars,comment);
    }



    public void downloadApp (String name, String packageName, Context context)
    {
        String myUrl = "https://bermodaco.ir/market/app/download.php?name="+name;

        new Download(myUrl,packageName).execute();
    }



    static class Download extends AsyncTask<Void,Integer,Void>
    {
        String myUrl;
        String packageName;

        public Download(String myUrl,String packageName)
        {
            this.myUrl = myUrl;
            this.packageName = packageName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            appView.showProgressBar();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL(myUrl);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();

                File file = new File(AppCls.component.context().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),packageName);
                if (!file.exists())
                {
                    file.createNewFile();
                }
                int fileSize = urlConnection.getContentLength();
                InputStream inputStream = urlConnection.getInputStream();
                OutputStream outputStream = new FileOutputStream(file);

                byte[] fileReader = new byte[2048];
                int count = 0;
                int progress = 0;

                while ((count = inputStream.read(fileReader))!=-1)
                {
                    outputStream.write(fileReader,0,count);
                    progress += count;
                    publishProgress(progress*100/fileSize);
                }

                outputStream.flush();
                inputStream.close();
                outputStream.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            appView.hideProgressBar();
            appView.installApp();
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            appView.handleProgress(values[0]);
        }
    }

}
