//Add adapter to the projects package
package com.ccbury.joblisting;
//Import requirements to allow the adapted to function
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class jobsListAdapter extends FirebaseRecyclerAdapter<jobs, jobsListAdapter.jobsViewholder> {
    //ClickListener to allow a user to select an option
    private static ClickListener clickListener;

    //jobsListAdapter method to initialise the class
    public jobsListAdapter(@NonNull FirebaseRecyclerOptions<jobs> options) {
        super(options);
    }

    //onBindViewHolder sets the fields in the recycler template
    @Override
    protected void onBindViewHolder(@NonNull jobsViewholder holder, int position, @NonNull jobs model) {
        holder.posted.setText(model.getPosted());
        holder.title.setText(model.getTitle());
        holder.company.setText(model.getCompany());
        holder.location.setText(model.getLocation());
        holder.pay.setText(model.getPay());
        holder.jobid.setText(model.getJobid());
        Glide.with(holder.companyImage.getContext()).load(model.getCompanyImage()).into(holder.companyImage);
    }//End onBindViewHolder

    //OnCreateViewHolder adds the recycler template to the recycler
    @NonNull
    @Override
    public jobsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joblist, parent, false);
        return new jobsListAdapter.jobsViewholder(view);
    }//End onCreateViewHolder

    public static class jobsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //declare empty textView variables that will be used to edit template view later.
        TextView posted, title, company, location, pay, jobid;
        ImageView companyImage;
        public jobsViewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //initialise the textViews so that they point in the correct location
            posted = itemView.findViewById(R.id.posted);
            title = itemView.findViewById(R.id.title);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            pay = itemView.findViewById(R.id.pay);
            jobid = itemView.findViewById(R.id.id);
            companyImage = itemView.findViewById(R.id.company_image);
        }//End jobsViewholder
        //onClick that calls onItemClick from within clickListener. Allowing a user to select an option from the recycler
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }//End jobsViewholder

    public void setOnItemClickListener(ClickListener clickListener) {
        jobsListAdapter.clickListener = clickListener;
    }//End setOnItemClickListener

    public interface ClickListener {
        void onItemClick(int position, View v);
    }//End clickListener interface

}//End class
