package com.example.complaintms;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TrackComp extends Fragment {
        ListView complist;
        DBHelper DB;
        ArrayList<String> listItem;
        ArrayAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.track_complaint , container , false);

        DB = new DBHelper(getActivity());
        listItem = new ArrayList<>();
        complist=v.findViewById(R.id.complistview);

        getComplaint();

        return v;
    }
    private void getComplaint(){
        Cursor cursor = DB.getComplaint();

        if (cursor.getCount()==0){
            Toast.makeText(getActivity(),"No Entries",Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(0));
            }

            adapter= new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listItem);
            complist.setAdapter(adapter);
        }
    }
}
