package com.cos.viewtestmyself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cos.viewtestmyself.adapter.PersonAdapter;
import com.cos.viewtestmyself.provider.PersonProvider;

// 어댑터와 리사이클러뷰 연결 !!
public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPersons;
    //방향 설정
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initData();
        //notifyDataSetChanged();는 onCreate가 종료되지 않아서 달지 않았다.
    }

    //어댑터 관련
    private void initAdapter(){
        //MainActivity가 context이다.
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter();
        rvPersons.setAdapter(personAdapter);
    }
    //데이터 셋팅
    private void initData(){
        PersonProvider p = new PersonProvider();
        //캡슐화
        personAdapter.addItems(p.findAll());
    }
    //findViewBy 관련
    private void init(){
        rvPersons = findViewById(R.id.rvPersons);

    }
}