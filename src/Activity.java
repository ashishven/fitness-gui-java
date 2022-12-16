public class Activity {
    private String type;

    private String date;
    private String duration;
    private String distance;
    private int caloriesBurnt;


    public Activity(String date, String type, String duration, String distance, int caloriesBurnt) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.caloriesBurnt = caloriesBurnt;
    }

    public String getType() {
        return type;
    }
    public String getDate() {return date;}
    public int getCaloriesBurnt() {return caloriesBurnt;}
    public String getDistance() {return distance;}
    public String getDuration() {return duration;}
}
