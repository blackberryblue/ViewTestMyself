package com.cos.viewtestmyself.provider;

import com.cos.viewtestmyself.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonProvider {

    public  List<Person> findAll () {
        List<Person> persons = new ArrayList<>();
        for(int i = 1; i<21; i++){
            persons.add(new Person("이름"+i,"010222222"));
        }
        //통신할 때는 null이 return되서 future나 라이브러리 사용
        return persons;
    }


}
