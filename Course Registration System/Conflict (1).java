import java.util.ArrayList;
/****************************************************************************************************
 * This program has the ability to check for conflicts in a grouped manner which sorts the courses
 * and non-courses by day and then compare the StartTime and EndTime to check if the courses are 
 * conflicting.
 *
 * @members all
 * @date 11-26-2024
 * @version projectSubmission7 
 ************************************************************************************************** */
public class Conflict 
{
    // Method to check for scheduling conflicts within a group
    public static boolean checkConflicts(Group group) 
    {
        boolean conflict = false;
        ArrayList<Course> courseList = group.getCourses();

        // Compare each course with all other courses
        for (int i = 0; i < courseList.size(); i++) 
        {
            Course firstCourse = courseList.get(i);
            for (int j = i + 1; j < courseList.size(); j++) 
            {
                Course nextCourse = courseList.get(j);

                // Compare all timeslots of the two courses
                for (String[] firstSlot : firstCourse.getTimeslots()) 
                {
                    for (String[] nextSlot : nextCourse.getTimeslots()) 
                    {
                        // Check for overlapping days
                        if (hasCommonDays(firstSlot[0], nextSlot[0])) 
                        {
                            // Check for overlapping times
                            if (firstSlot[2].compareTo(nextSlot[1]) > 0 && firstSlot[1].compareTo(nextSlot[2]) < 0) 
                            {
                                System.out.println("\nConflict found between:");
                                System.out.println("  - " + firstCourse);
                                System.out.println("  - " + nextCourse);
                                conflict = true;
                            }
                        }
                    }
                }
            }
        }
        return conflict;
    }

    // Helper method to check if two day strings have common days
    private static boolean hasCommonDays(String days1, String days2) 
    {
        for (char day1 : days1.toCharArray()) 
        {
            for (char day2 : days2.toCharArray()) 
            {
                if (day1 == day2) return true;
            }
        }
        return false;
    }
}
