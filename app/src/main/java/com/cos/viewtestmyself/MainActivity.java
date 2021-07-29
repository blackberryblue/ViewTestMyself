package com.cos.viewtestmyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.cos.viewtestmyself.adapter.PersonAdapter;
import com.cos.viewtestmyself.model.Person;
import com.cos.viewtestmyself.provider.PersonProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

// 어댑터와 리사이클러뷰 연결 !!
public class MainActivity extends AppCompatActivity {
    private MainActivity mContext = this;
    private RecyclerView rvPersons;
    //B1:전역변수 선언
    private FloatingActionButton fabAdd;
    //방향 설정
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //notifyDataSetChanged();는 onCreate가 종료되지 않아서 달지 않았다.

        init();
        initAdapter();
        initData();
        initListener();

    }

    //리사이클러뷰 swipe 기능
   private void initListener() {
        fabAdd.setOnClickListener(v -> {
            personAdapter.addItem(new Person("이름","0103224"));
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                personAdapter.removeItem(index);
            }
        }).attachToRecyclerView(rvPersons);
    }

    //어댑터 관련
    private void initAdapter(){
        //MainActivity가 context이다.
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(mContext);
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
        fabAdd = findViewById(R.id.fabAdd);

    }

    public void mRvScroll() {
        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
    }


}