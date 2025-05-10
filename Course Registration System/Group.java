import java.util.ArrayList;
/**********************************************************************************************
 * This program creates an arrayList to add courses and group them. 
 *
 * @members all
 * @date 27-11-2024
 * @version 2024 
 ******************************************************************************************** */
public class Group 
{
    private String name;
    private ArrayList<Course> courseList;
    public Group(String name) 
    {
        this.name = name;
        this.courseList = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }
    
    public void addCourse(Course course) 
    {
        courseList.add(course);
    }

    public void addNonCourse(Course nonCourse) 
    {
        courseList.add(nonCourse);
    }
    
    public ArrayList<Course> getCourses() 
    {
        return courseList;
    }

}
