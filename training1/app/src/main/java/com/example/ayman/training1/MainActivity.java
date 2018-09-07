package com.example.ayman.training1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
 @BindView(R.id.wordRecyclerViewID)RecyclerView wordRecycler;
 @BindView(R.id.responseTxtID)TextView responsetxt;
    wordAdapter word_Adapter;
    List <WordClass>wordClassList;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        wordClassList=new ArrayList<>();

        CallRetrofitToGetPosts();

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.adduserID:
             addUser();
                return true;
        }
        return true;
    }

    public void CallRetrofitToGetPosts(){

       try {
      Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofiteInterface.Base_URL)
                   .addConverterFactory(GsonConverterFactory.create()).build();

           RetrofiteInterface myservice = retrofit.create(RetrofiteInterface.class);
           myservice.getposts().enqueue(new Callback<List<Endi>>() {
               @Override
               public void onResponse(Call<List<Endi>> call, Response<List<Endi>> response) {
        //           Toast.makeText(getApplicationContext(),response.body().get(0).getPost_content().toString(),Toast.LENGTH_LONG).show();
                   for (int i=0;i<response.body().size();i++){
                       wordClassList.add(new WordClass(response.body().get(i).getPost_content().toString()));
                   }
                   try {
                       layoutManager = new LinearLayoutManager(getApplicationContext());
                       word_Adapter = new wordAdapter(wordClassList, getApplicationContext());
                       wordRecycler.setAdapter(word_Adapter);
                       wordRecycler.setLayoutManager(layoutManager);
                       wordRecycler.setHasFixedSize(true);
                   }catch (Exception ex){

                       Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                   }

               }

               @Override
               public void onFailure(Call<List<Endi>> call, Throwable t) {

               }
           });



       }catch (Exception ex){
           responsetxt.setText(ex.getMessage().toString());
       }
   }
    public void addUser(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofiteInterface.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofiteInterface myservice = retrofit.create(RetrofiteInterface.class);
        myservice.setPost("Ameer","123123","ameer@hendiware","Cairo")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


    }
}
