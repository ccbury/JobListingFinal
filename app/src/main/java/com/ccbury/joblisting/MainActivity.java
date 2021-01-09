//Add mainactivity to the project package
package com.ccbury.joblisting;
//Import requirements for allowing application to function
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//Begin class
public class MainActivity extends AppCompatActivity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    //OnCreate. run when the MainActivity is launched
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
        }





        //Add logic for all of the buttons displayed on the card view.
        Button workFromHome = findViewById(R.id.workFromHome);
        workFromHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Work From Home");
                startActivity(activity2Intent);
            }
        });
        Button projectManager = findViewById(R.id.projectManager);
        projectManager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Project Manager");
                startActivity(activity2Intent);
            }
        });
        Button computing = findViewById(R.id.computing);
        computing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Computing");
                startActivity(activity2Intent);
            }
        });
        Button dataAnalytics = findViewById(R.id.dataAnalytics);
        dataAnalytics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Data Analytics");
                startActivity(activity2Intent);
            }
        });
        Button softwareEngineer = findViewById(R.id.softwareEngineer);
        softwareEngineer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Software Engineer");
                startActivity(activity2Intent);
            }
        });
        Button cryptoCurrencies = findViewById(R.id.cryptoCurrencies);
        cryptoCurrencies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Crypto Currencies");
                startActivity(activity2Intent);
            }
        });
        Button graduateJobs = findViewById(R.id.graduateJobs);
        graduateJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "Graduate Jobs");
                startActivity(activity2Intent);
            }
        });
        Button iTHelpdesk = findViewById(R.id.itHelpdesk);
        iTHelpdesk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent activity2Intent = new Intent(getApplicationContext(), WorkFromHomeActivity.class);
                activity2Intent.putExtra("EXTRA_SESSION_ID", "IT Helpdesk");
                startActivity(activity2Intent);
            }
        });
    }//End  onCreate
}//end class