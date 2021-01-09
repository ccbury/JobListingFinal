//make activity part of the applications package
package com.ccbury.joblisting;
//Imports used for the activity
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class WorkFromHomeActivity extends AppCompatActivity {
    //Declare class wide variables needed for this activity.
    //Recycler view will display all of the jobs for the category, jobsListAdapter populates the fields within the recyclerView and the DatabaseReference gets the information to populate the fields by.
    RecyclerView mResultList;
    jobsListAdapter adapter;
    DatabaseReference mjobsDatabase;

    //onCreate is the method called when the activity is called.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_from_home);
        //Get an instance of the database using the DataBaseReference
        mjobsDatabase = FirebaseDatabase.getInstance().getReference();
        //Decalre the view that will be used for the recyclerViews layout
        mResultList = findViewById(R.id.result_list);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        //Take in the category from intent. this will be used to name the page and also search for relevant jobs
        String category = getIntent().getStringExtra("EXTRA_SESSION_ID");
        //Set a custom query so that the application will only show the relevant jobs.
        Query firebaseSearchQuery = mjobsDatabase.orderByChild("title").startAt(category).endAt(category+"\uf8ff");
        //Use the jobs object to take information from the database
        FirebaseRecyclerOptions<jobs> options = new FirebaseRecyclerOptions.Builder<jobs>().setQuery(firebaseSearchQuery, jobs.class).build();

        //Set the title of the page equal to the value taken from intent
        TextView title = findViewById(R.id.title);
        title.setText(category);

        //Create the adapter for the recycler and apply it to the RecyclerView. this will display the list of entries.
        adapter = new jobsListAdapter(options);
        mResultList.setAdapter(adapter);

        //Create logic for the back button so that it will return a user to the main activity when pressed.
        Button homeBack = findViewById(R.id.home);
        homeBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2Intent);
            }
        });

        //Set an on item click listener. This is used to launch a new activity when the user wants to view more details on a job
        adapter.setOnItemClickListener(new jobsListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //Create Intent to leanch an activity. Add the position of the item clicked to he intent.
                //this is so that the launched activity can find the record that the user clicked and display information on it
                Intent activity2Intent = new Intent(getApplicationContext(), GradJobActivity.class);
                TextView idView = v.findViewById(R.id.id);
                String positionStr = idView.getText().toString();
                activity2Intent.putExtra("EXTRA_SESSION_ID", positionStr);
                startActivity(activity2Intent);
            }//End onItemClick
        });//End onItemClickListener
    }//End onCreate

    //These methods allow the application to see when it has stopped reading from the database, or when it has started.
    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }//End onStart
    @Override protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }//End onStop

}//End activity