import java.util.ArrayList;
/**********************************************************************************************
 * This program creates a course class with a course object and a 
 * non course object having all the details about course.
 *
 * @members all
 * @date 11-26-2024
 * @version projectSubmission7 
 ******************************************************************************************** */
public class Course 
{
    private String courseID;
    private String extraText;
    private String instructor;
    private String courseSchedule;
    private boolean isNonCourse;
    private ArrayList<String[]> timeslots; // List of [day(s), startTime, endTime]

    // Constructor for course
    public Course(String courseID, String extraText, String instructor, String courseSchedule) 
    {
        this.courseID = courseID;
        this.extraText = extraText;
        this.instructor = instructor;
        this.courseSchedule = courseSchedule;
        this.isNonCourse = false;
        this.timeslots = new ArrayList<>();
    }

    // Constructor for non-course block
    public Course(String extraText, String courseSchedule, boolean isNonCourse) 
    {
        this.extraText = extraText;
        this.courseSchedule = courseSchedule;
        this.isNonCourse = isNonCourse;
        this.timeslots = new ArrayList<>();
    }

    // Parse the schedule
    public void parse() 
    {
        String[] slots = courseSchedule.split(";"); // Split by semicolons for multiple timeslots
        for (String slot : slots) 
        {
            String[] parts = slot.trim().split(" ");
            if (parts.length == 2) 
            {
                String days = parts[0]; // Day(s)
                String[] times = parts[1].split("-");
                if (times.length == 2) 
                {
                    timeslots.add(new String[]{days, times[0], times[1]});
                }
            }
        }
    }

    // Getter for courseID
    public String getCourseID() 
    {
        return courseID;
    }

    // Getter for extraText
    public String getExtraText() 
    {
        return extraText;
    }

    // Getter for instructor
    public String getInstructor() 
    {
        return instructor;
    }

    // Getter for isNonCourse
    public boolean isNonCourse()
    {
        return isNonCourse;
    }

    // Getter for timeslots
    public ArrayList<String[]> getTimeslots() 
    {
        return timeslots;
    }

    // toString method
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        if (!isNonCourse) 
        {
            sb.append(courseID).append(", ").append(extraText).append(", ").append(instructor).append(", ");
        } 
        else
        {
            sb.append(extraText).append(", ");
        }

        for (String[] timeslot : timeslots) 
        {
            sb.append(timeslot[0]).append(" ").append(timeslot[1]).append("-").append(timeslot[2]).append("; ");
        }
        return sb.toString().trim();
    }
}
