package com.lahrachtech.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textViewTitle);
        EditText postId = findViewById(R.id.etPostId);
        Button btn = findViewById(R.id.button);
        Button btnStorePost = findViewById(R.id.btnStorePost);

        EditText etUserId = findViewById(R.id.etUserId);
        EditText etTitle = findViewById(R.id.etTitle);
        EditText etBody = findViewById(R.id.etBody);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);


        btnStorePost.setOnClickListener(v -> {
            int userId = Integer.parseInt(etUserId.getText().toString());
            String title = etTitle.getText().toString();
            String body = etBody.getText().toString();

            if (!(title.isEmpty() && body.isEmpty() && userId <= 0)) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("title", title);
                map.put("body", body);
                Post post = new Post(userId, title, body);
                Call<Post> call = apiInterface.storePost(map);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.body() != null) {
                            text.setText(response.body().getBody() + " " + response.body().getId());
                        } else {
                            text.setText("There is no value in this id");
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        text.setText(t.getMessage());
                    }
                });
            }
        });
        btn.setOnClickListener(v -> {
            if (!postId.getText().toString().isEmpty()) {
                Integer id = Integer.parseInt(postId.getText().toString());
                Call<Post> call = apiInterface.getPost(id);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.body() != null) {
                            text.setText(response.body().getBody());
                        } else {
                            text.setText("There is no value in this id");
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        text.setText(t.getMessage());

                    }
                });
            }
        });


    }
}