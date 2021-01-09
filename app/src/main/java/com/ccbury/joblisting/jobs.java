//Add the object to the package so that it can be accessed
package com.ccbury.joblisting;

//begin the jobs object
public class jobs {
    //Declare variables for a job object. These values are assigned by the Firebase Realtime database
    public String company, location, pay, posted, title, jobid, companyImage, role, responsability, requiredQualifications, desiredQualifications, perks, contactEmail, videoUri;
    public int lat, longi;

    //Constructor will all variables attached
    public jobs(String company, String location, String pay, String posted, String title, String jobid, String companyImage, String role, String responsability, String requiredQualifications, String desiredQualifications, String perks, String contactEmail, int lat, int longi, String videoUri){
        this.company = company;
        this.location = location;
        this.pay = pay;
        this.posted = posted;
        this.title = title;
        this.jobid = jobid;
        this.companyImage = companyImage;
        this.role = role;
        this.responsability = responsability;
        this.requiredQualifications = requiredQualifications;
        this.desiredQualifications = desiredQualifications;
        this.perks = perks;
        this.contactEmail = contactEmail;
        this.lat = lat;
        this.longi=longi;
        this.videoUri = videoUri;
    }//End constructor

    //Empty constructor
    public jobs(){ }//End constructor

    //Begin getters and setters section
    public String getVideoUri() { return videoUri; }
    public void setVideoUri(String videoUri) { this.videoUri = videoUri; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPay() { return pay; }
    public void setPay(String pay) { this.pay = pay; }

    public String getPosted() { return posted; }
    public void setPosted(String posted) { this.posted = posted; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getJobid() { return jobid; }
    public void setJobid(String jobid) { this.jobid = jobid; }

    public String getCompanyImage() { return companyImage; }
    public void setCompanyImage(String companyImage) { this.companyImage = companyImage; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getResponsabilities() { return responsability; }
    public void setResponsabilities(String responsabilities) { this.responsability = responsabilities; }

    public String getRequiredQualifications() { return requiredQualifications; }
    public void setRequiredQualifications(String requiredQualifications) { this.requiredQualifications = requiredQualifications; }

    public String getDesiredQualifications() { return desiredQualifications; }
    public void setDesiredQualifications(String desiredQualifications) { this.desiredQualifications = desiredQualifications; }

    public String getPerks() { return perks; }
    public void setPerks(String perks) { this.perks = perks; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public int getLat() { return lat; }
    public void setLat(int lat) { this.lat = lat; }

    public int getLongi() { return longi; }
    public void setLongi(int longi) { this.longi = longi; }
    //end getters and setters sections
}//End jobs object
