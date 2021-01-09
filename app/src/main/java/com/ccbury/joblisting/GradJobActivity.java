package com.ccbury.joblisting;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GradJobActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView title, posted, role, responsabilities, requiredQualifications, desiredQualifications, perks, contactEmail;
    ImageView logo;
    jobs newJob;
    VideoView videoView;
    Boolean videoPlaying = false;
    DatabaseReference ref;
    GoogleMap mMap;
    File pic;
    Bitmap thumbnail;
    protected static final int CAMERA_PIC_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_job);

        String JobId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference(JobId);

        title = (TextView) findViewById(R.id.title);
        posted = (TextView) findViewById(R.id.posted);
        logo = (ImageView) findViewById(R.id.logo);
        role = (TextView) findViewById(R.id.role);
        responsabilities = (TextView) findViewById(R.id.responsabilities);
        requiredQualifications = (TextView) findViewById(R.id.requiredQualifications);
        desiredQualifications = (TextView) findViewById(R.id.desiredQualifications);
        perks = (TextView) findViewById(R.id.perks);
        contactEmail = (TextView) findViewById(R.id.contact_email);
        videoView = (VideoView) findViewById(R.id.video);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button homeBack = findViewById(R.id.home1);
        homeBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                startActivity(activity2Intent);
            }
        });
    }
    private void addValueEventListener(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                newJob = dataSnapshot.getValue(jobs.class);
                title.setText(newJob.getTitle());
                posted.setText("Posted: " + newJob.getPosted());
                role.setText(newJob.getRole());
                responsabilities.setText(newJob.getResponsabilities());
                requiredQualifications.setText(newJob.getRequiredQualifications());
                desiredQualifications.setText(newJob.getDesiredQualifications());
                perks.setText(newJob.getPerks());
                contactEmail.setText(newJob.getContactEmail());
                videoView.setVideoPath(newJob.getVideoUri());
                videoView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(!videoPlaying) {
                            videoView.start();
                            videoPlaying = !videoPlaying;
                        } else {
                            videoPlaying = !videoPlaying;
                            videoView.pause();
                        }
                    }
                });
                Glide.with(logo.getContext()).load(newJob.getCompanyImage()).into(logo);

                LatLng location = new LatLng(newJob.getLat(), newJob.getLongi());
                mMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title(newJob.getCompany()));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        Button applyNow = findViewById(R.id.apply_button);
        applyNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.putExtra(Intent.EXTRA_EMAIL, new String[]{newJob.getContactEmail()});
//                i.putExtra(Intent.EXTRA_SUBJECT,"Job Application");
//                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pic));
//
//                i.setType("image/png");
//                startActivity(Intent.createChooser(i,"Share you on the jobing"));
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 16.0f; //This goes up to 21
        addValueEventListener();
       // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NCI, zoomLevel));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            thumbnail = (Bitmap) data.getExtras().get("data");
            try {
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()) {
                    pic = new File(root, "pic.png");
                    FileOutputStream out = new FileOutputStream(pic);
                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                Log.e("BROKEN", "Could not write file " + e.getMessage());
            }

        }
    }
}
