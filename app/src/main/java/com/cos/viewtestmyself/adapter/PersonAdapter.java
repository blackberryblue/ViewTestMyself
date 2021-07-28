package com.cos.viewtestmyself.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtestmyself.R;
import com.cos.viewtestmyself.model.Person;

public class PersonAdapter {

    // 1.뷰홀더 만들기 (뷰홀더는 데이터 갈아 끼워넣는 역)
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTel;

        // 뷰를 가져와서 데이터 갈아 끼우는 역
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);
        }
        //앱구동시 최초 이 후,스크롤할 때 발동
        //데이터 갈아 끼우기
        public void setItem(Person person) {
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }
    }
}
