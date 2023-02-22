package edu.gatech.seclass.jobcompare6300;

public class Job {

    public String id;
    public String title;
    public String company;
    public String location;
    public Integer salary;
    public Integer bonus;
    public Integer leavetime;
    public Integer stock;
    public Integer homefund;
    public Integer wellnessfund;
    public Float jobscore;
    public String currentjob; // Value is true if this is current job; false if this is a job offer

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Integer leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getHomefund() {
        return homefund;
    }

    public void setHomefund(Integer homefund) {
        this.homefund = homefund;
    }

    public Integer getWellnessfund() {
        return wellnessfund;
    }

    public void setWellnessfund(Integer wellnessfund) {
        this.wellnessfund = wellnessfund;
    }

    public Float getJobscore() {
        return jobscore;
    }

    public void setJobscore(Float jobscore) {
        this.jobscore = jobscore;
    }
}
