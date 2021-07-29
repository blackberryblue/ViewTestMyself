package com.cos.viewtestmyself.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtestmyself.MainActivity;
import com.cos.viewtestmyself.R;
import com.cos.viewtestmyself.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

//2.어댑터 만들기
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private  PersonAdapter personAdapter = this;
    private static final String TAG = "PersonAdapter";
    private MainActivity mContext;
    private int createCount = 1;
    private  int bindCount = 1;

    public PersonAdapter(MainActivity mContext) {
        this.mContext = mContext;
    }

    //3.컬렉션
    private List<Person> persons = new ArrayList<>();

    //4.컬렉션 데이터 세팅
    public void addItems(List<Person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }


    public void addItem(Person person) {
        this.persons = persons;
        notifyDataSetChanged();
        mContext.mRvScroll();
    }

    public List<Person> getItems() {
        return persons;
    }

    public void removeItem(int index){
        persons.remove(index);
        notifyDataSetChanged();
    }

    // ViewHolder 객체 만드는 친구(그림에 대한 객체)
    //Inflater 학습
    //그림만 그림다. onBindVie~로 리턴해서 바인딩을 해야한다.
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: 그림 만들어짐 "+createCount);
        createCount++;
        LayoutInflater layoutInflater =
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //linearlayout을 메모리에 띄움
        View view = layoutInflater.inflate(R.layout.person_item, parent, false);

        return new MyViewHolder(view);
    }
    // ViewHolder 데이터 갈아끼우는 친구
    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: 데이터 바인딩 "+bindCount);
        bindCount++;
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
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        private FloatingActionButton fadAdd;
        private TextView tvName, tvTel;

        // 뷰를 가져와서 데이터 갈아 끼우는 역
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);
            fadAdd = itemView.findViewById(R.id.fabAdd);

            initListener();
        }

        private void initListener() {
            // 다른 클래스여서 persons를 찾을 수 없다.
            itemView.setOnClickListener(v -> {
                Log.d(TAG, "onCreateViewHolder: "+getAdapterPosition());
                int index = getAdapterPosition();
                Log.d(TAG, "initListener: " + personAdapter.getItems().get(index).getName());
                personAdapter.removeItem(index);

              /*  TextView t = v.findViewById(R.id.tvName);
                Log.d(TAG, "initListener: "+ t.getText());*/
            });
        }
        //앱구동시 최초 이 후,스크롤할 때 발동
        //데이터 갈아 끼우기
        public void setItem(Person person) {
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }

    }
}
