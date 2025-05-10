import java.util.ArrayList;
import java.util.Collections;
/**********************************************************************************************
 * This program creates a simple user display to view the schedule entered by the user.
 *
 * @members Samridhi Girdhar
 * @date 11-26-2024
 * @version projectSubmission7 
 ******************************************************************************************** */
public class Display 
{
    public static void displaySchedule(Group group) 
    {
        String[] days = {"M", "T", "W", "Th", "F"};
        ArrayList<CourseWithTimeslot> courseWithTimeslots = new ArrayList<>();

        for (Course course : group.getCourses()) 
        {
            for (String[] timeslot : course.getTimeslots()) 
            {
                String dayString = timeslot[0]; 
                String startTime = timeslot[1];
                String endTime = timeslot[2];
                int i = 0;
                while (i < dayString.length())
                {
                    if (dayString.charAt(i) == 'T' && i + 1 < dayString.length() && dayString.charAt(i + 1) == 'h') {
                        courseWithTimeslots.add(new CourseWithTimeslot(
                            course.getCourseID(),
                            course.getExtraText(),
                            course.getInstructor(),
                            "Th", 
                            startTime,
                            endTime,
                            course.isNonCourse()
                        ));
                        i += 2; 
                    }
                    else 
                    {
                        courseWithTimeslots.add(new CourseWithTimeslot(
                            course.getCourseID(),
                            course.getExtraText(),
                            course.getInstructor(),
                            String.valueOf(dayString.charAt(i)), 
                            startTime,
                            endTime,
                            course.isNonCourse()
                        ));
                        i++;
                    }
                }
            }
        }

        Collections.sort(courseWithTimeslots);

        String currentDay = "";
        for (CourseWithTimeslot timeslot : courseWithTimeslots)
        {
            String fullDayName = getDayName(timeslot.getDay());
            if (!fullDayName.equals(currentDay)) 
            
            {
                currentDay = fullDayName;
                System.out.println("\n" + currentDay + ":");
            }
            System.out.println("  " + timeslot);
        }
    }

    private static String getDayName(String day) 
    {
        switch (day) {
            case "M": return "Monday";
            case "T": return "Tuesday";
            case "W": return "Wednesday";
            case "Th": return "Thursday";
            case "F": return "Friday";
            default: return "Unknown";
        }
    }

    private static class CourseWithTimeslot implements Comparable<CourseWithTimeslot> 
    {
        private String courseID;
        private String extraText;
        private String instructor;
        private String day;
        private String startTime;
        private String endTime;
        private boolean isNonCourse;

        public CourseWithTimeslot(String courseID, String extraText, String instructor,
                                  String day, String startTime, String endTime, boolean isNonCourse) 
        {
            this.courseID = courseID;
            this.extraText = extraText;
            this.instructor = instructor;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.isNonCourse = isNonCourse;
        }
        
        public String getDay() 
        {
            return day;
        }

        @Override
        public int compareTo(CourseWithTimeslot other) 
        {
            int dayComparison = Integer.compare(getDayIndex(this.day), getDayIndex(other.day));
            if (dayComparison != 0) 
            {
                return dayComparison;
            }
            return this.startTime.compareTo(other.startTime);
        }

        @Override
        public String toString()
        {
            if (!isNonCourse)
            {
                return courseID + ", " + extraText + ", " + instructor + ", " + day + " " + startTime + "-" + endTime;
            } 
            else 
            {
                return extraText + ", " + day + " " + startTime + "-" + endTime;
            }
        }

        private static int getDayIndex(String day) 
        {
            switch (day) 
            {
                case "M": return 0;
                case "T": return 1;
                case "W": return 2;
                case "Th": return 3;
                case "F": return 4;
                default: return 5; 
            }
        }
    }
}
