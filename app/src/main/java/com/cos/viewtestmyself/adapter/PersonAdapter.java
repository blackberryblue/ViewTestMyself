package com.cos.viewtestmyself.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtestmyself.R;
import com.cos.viewtestmyself.model.Person;

import java.util.ArrayList;
import java.util.List;

//2.어댑터 만들기
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    //3.컬렉션
    private List<Person> persons = new ArrayList<>();

    //4.컬렉션 데이터 세팅
    public void addItems(List<Person> persons){
        this.persons = persons;
    }


    // ViewHolder 객체 만드는 친구(그림에 대한 객체)
    //Inflater 학습
    //그림만 그림다. onBindVie~로 리턴해서 바인딩을 해야한다.
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //linearlayout을 메모리에 띄움
        View view = layoutInflater.inflate(R.layout.person_item, parent, false);
        return new MyViewHolder(view);
    }
    // ViewHolder 데이터 갈아끼우는 친구
    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.setItem(person);
    }

    // 어댑터가 알아서 호출해서 사이즈 10이네?
    // 화면크기가 600
    // 아이템 크기가 200
    @Override
    public int getItemCount() {
        return persons.size();
    }

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
