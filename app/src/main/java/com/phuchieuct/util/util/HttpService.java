package com.phuchieuct.util.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;
import com.koushikdutta.ion.builder.LoadBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;

@EBean(scope = EBean.Scope.Singleton)
public class HttpService {
    private final static int TIMEOUT = 2000;
    @RootContext
    Context context;
    LoadBuilder<Builders.Any.B> ionLoadUrl;
    Picasso picasso;


    @AfterInject
    public void initIon() {
        ionLoadUrl = Ion.with(context.getApplicationContext());
        picasso = Picasso.with(context.getApplicationContext());
    }


    public String readUrl(String path) throws ExecutionException, InterruptedException {
            String result = ionLoadUrl.load(path).setTimeout(TIMEOUT).asString().get();

            return result;



    }


    public void loadImage(String url, final ImageView imageView, final ProgressBar progressBar) {
        try {
            if (url.isEmpty() || url.length() == 0) {
                url = "http://etaal.gov.in/etaal/Image/news.png";

            }

            picasso.load(url).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    if (progressBar != null) {
                        progressBar.setVisibility(GONE);

                    }
                }

                @Override
                public void onError() {
                    imageView.setVisibility(GONE);
//                         imageView.setImageResource(R.drawable.news);
                    if (progressBar != null) {

                        progressBar.setVisibility(GONE);

                    }
                }
            });

        } catch (Exception e) {
        }


    }


    public void setRandomImage(final ImageView imageView) {
        try {

            String newString = "http://lorempixel.com/400/300/?time=" + new Date().toString();

            loadImage(newString, imageView, null);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}