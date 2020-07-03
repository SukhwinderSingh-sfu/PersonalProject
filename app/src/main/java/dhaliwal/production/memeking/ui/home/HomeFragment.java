package dhaliwal.production.memeking.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

import dhaliwal.production.memeking.R;

public class HomeFragment extends Fragment implements jadapter.OnNoteListener, AdapterView.OnItemSelectedListener{

    private HomeViewModel homeViewModel;
    //Arraylist of StorageReference to pass to jadapter.
    private ArrayList<StorageReference> downloadImage;
    private jadapter Jadapter;
    private RecyclerView list;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment(){

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //viewmodel should be used later to load the data.
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        //root view should be used instead of getActivity() to find or set items.
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context=getContext();

        //Firebase setup getInstance().
        final FirebaseStorage storage=FirebaseStorage.getInstance();
        //set up recylcerview;
        list=root.findViewById(R.id.homerecylerview);
        list.setHasFixedSize(true);
        list.setItemViewCacheSize(20);
        list.setDrawingCacheEnabled(true);
        list.setNestedScrollingEnabled(false);
        list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference memes=database.getReference("memes");
        memes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                downloadImage=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String memereferid=ds.getValue(String.class);
                    StorageReference memesReference2=storage.getReference().child("Memes").child(memereferid);
                    downloadImage.add(memesReference2);
                }
                Collections.reverse(downloadImage);
                setRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return root;

    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){

        swipeRefreshLayout=view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //
                final FirebaseStorage storage=FirebaseStorage.getInstance();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference memes=database.getReference("memes");
                memes.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<StorageReference> downloadImage2=new ArrayList<>();
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            String memereferid=ds.getValue(String.class);
                            StorageReference memesReference2=storage.getReference().child("Memes").child(memereferid);
                            downloadImage2.add(memesReference2);
                        }
                        Collections.reverse(downloadImage2);

                        Jadapter.clear();
                        Jadapter.addAll(downloadImage2);
                        swipeRefreshLayout.setRefreshing(false);

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.darker_gray,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);




    }





    private void setRecyclerView(){
        Jadapter =new jadapter(downloadImage, this,context);
        list.setAdapter(Jadapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onNoteClick(int position) {

    }
}
