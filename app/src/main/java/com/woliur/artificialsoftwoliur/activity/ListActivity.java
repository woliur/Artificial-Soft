package com.woliur.artificialsoftwoliur.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.woliur.artificialsoftwoliur.R;
import com.woliur.artificialsoftwoliur.adapter.RecycleViewAdapter;
import com.woliur.artificialsoftwoliur.model.ProfileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {

    public static final String url = "https://api.myjson.com/bins/15baeq";

    OkHttpClient client = new OkHttpClient();

    ArrayList<ProfileModel> model = new ArrayList<>();
    RecycleViewAdapter adapter;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new RecycleViewAdapter(ListActivity.this,model);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        fetchUserList();

    }

    private void fetchUserList() {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        showResponseDialog("Failed to connect to the server!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        if (response.body() != null) {
                            String result = null;
                            try {
                                result = response.body().string();

                                JSONObject mainObject = new JSONObject(result);
                                JSONArray data = mainObject.getJSONArray("search_result");

                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject userJson = data.getJSONObject(i);

                                    String id = userJson.getString("id");
                                    String userNum = userJson.getString("User");
                                    String name = userJson.getString("name");
                                    String who = userJson.getString("who");
                                    String image = userJson.getString("image");

                                    ProfileModel singleProfile = new ProfileModel(id,userNum,name,who,image);

                                    model.add(singleProfile);
                                }

                                adapter.notifyDataSetChanged();

                            } catch (IOException e) {
                                showResponseDialog("Exception occurred");
                                e.printStackTrace();
                            } catch (JSONException e) {
                                showResponseDialog("Json Error");
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    private void showResponseDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

        builder.setTitle("Response");
        builder.setMessage(result);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
