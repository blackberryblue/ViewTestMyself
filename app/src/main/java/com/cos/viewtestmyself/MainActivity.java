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
    //다른 클래스에 메모리 전달
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
    //floatingActingButton으로 데이터 추가
   private void initListener() {
        fabAdd.setOnClickListener(v -> {
            //addItem의 하나의 함수가 모든 책임을 다 가지고 있어야한다.
            personAdapter.addItem(new Person("이름New","0103224"));

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
        //personAdapter로 매개변수 전달 -> addItem에 rvPersons사용을 위해서 메모리 띄울 수 있음
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

    //PersonAdapter에서 addItem에 함수로 접근하기 위해서 만든 함수
    public void mRvScroll() {
        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
    }


}