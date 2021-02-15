package com.example.complaintms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewComplaint extends Fragment implements AdapterView.OnItemClickListener {

    DBHelper DB ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_new_complaint, container, false);


        Spinner location = (Spinner) v.findViewById(R.id.location);
        Spinner department = (Spinner) v.findViewById(R.id.department);
        Spinner category = (Spinner) v.findViewById(R.id.category);
        EditText Mrnum = (EditText) v.findViewById(R.id.MRnum);
        EditText Subject = (EditText) v.findViewById(R.id.subject);
        EditText comment = (EditText) v.findViewById(R.id.comment);
        Button post = (Button) v.findViewById(R.id.postComplaint);

//        location.setOnItemClickListener(this);
//        department.setOnItemClickListener(this);
//        category.setOnItemClickListener(this);
        DB = new DBHelper(getActivity());

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = location.getSelectedItem().toString();
                String dep = department.getSelectedItem().toString();
                String cat = category.getSelectedItem().toString();
                String mnum = Mrnum.getText().toString();
                String sub = Subject.getText().toString();
                String com = comment.getText().toString();

                if(loc.equals("")||dep.equals("")||cat.equals("")||sub.equals("")||com.equals("")) {
                    Toast.makeText( getActivity(), "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert;
                    insert = DB.insertComplaint(loc,dep,cat,mnum,sub,com);
                    if(insert==true){
                        Toast.makeText(getActivity() , "Complaint Launched Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Something Went Wrong",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}