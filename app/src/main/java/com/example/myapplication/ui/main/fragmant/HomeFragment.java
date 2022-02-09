package com.example.myapplication.ui.main.fragmant;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.ui.main.CivilizationActivity;
import com.example.myapplication.ui.main.DriverActivity;
import com.example.myapplication.ui.main.HomeAdapter;
import com.example.myapplication.ui.main.HotileActivity;
import com.example.myapplication.ui.main.TourGuideActivity;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeAdapter.OnClick  {
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;
    private HomeAdapter adapter;
    private List<Modaldata> list;


    public HomeFragment() {
        // Required empty public constructor
    }


    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);



}

    @Override
    public void onViewCreated(@NonNull @NotNull View view,  @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView findViewbyid
        recyclerView =view.findViewById(R.id.recyclerView) ;
        progressBar=view.findViewById(R.id.Progress);
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        list=new ArrayList<>();
        adapter=new HomeAdapter(getContext(),list,this);
        recyclerView.setAdapter(adapter);
        getData();

    }
    @Override
    public void onItemClick(String pos) {
        if(pos.equals("0"))
        {
            Toast.makeText(getContext(),pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), TourGuideActivity.class));
        }
        if(pos.equals("1"))
        {
            Toast.makeText(getContext(),pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), CivilizationActivity.class));
        }
        if(pos.equals("2"))
        {
            Toast.makeText(getContext(),pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), HotileActivity.class));
        }
        if(pos.equals("3"))
        {
            Toast.makeText(getContext(),pos,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), DriverActivity.class));
        }


    }
    private void getData(){
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore.collection("Home")
                .orderBy("time", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){
                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(),"value is null",Toast.LENGTH_SHORT).show();

                        }else {
                            for (DocumentChange documentChange: value.getDocumentChanges()){
                                Modaldata model=documentChange.getDocument().toObject(Modaldata.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();

                            }
                            progressBar.setVisibility(View.GONE);
                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });

}}