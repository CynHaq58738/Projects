
import java.util.ArrayList;
import java.util.Scanner;

/************************************************************************************************
 * This program has the Main method to run the program, take user input and display the schedule.
 *
 * @members all
 * @date 11-26-2024
 * @version projectSubmission7 
 ********************************************************************************************** */
public class ScheduleMain 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        //used to store groups
        ArrayList<Group> groups = new ArrayList<>();
        String resPonse;

        do
        {
            // Client specifies the name for the group of courses
             System.out.print("Enter group name: ");
             String groupName = scanner.nextLine();

             Group group = new Group(groupName);
             String response;

           do 
           {
                System.out.print("\nDo you want to add a course or a non-course block? (course/non-course): ");
                String type = scanner.nextLine();

                if(type.equalsIgnoreCase("course"))
                {
                  //Count how many courses client input
                do
                {

                    // Gather course information
                    System.out.print("\nEnter course ID: ");
                    String courseID = scanner.nextLine();
                    System.out.print("Enter extra text (e.g., LEC, LAB1): ");
                    String extraText = scanner.nextLine();
                    System.out.print("Enter instructor's name: ");
                    String instructor = scanner.nextLine();
                    System.out.print("Enter course schedule seperated by a semi-colon(e.g., MW 2:00PM-3:50PM; F 10:00AM-12:00PM): ");
                    String courseSchedule = scanner.nextLine();

                    // Create a new course and add it to the group
                    Course newCourse = new Course(courseID, extraText, instructor, courseSchedule);
                    newCourse.parse();  // Parse the schedule into day, start time, and end time
                    group.addCourse(newCourse);

                    System.out.print("\nDo you want to add another course? (yes/no): ");
                    response = scanner.nextLine();

                }while(response.equalsIgnoreCase("yes")); 
                
                }
                else if(type.equalsIgnoreCase("non-course"))
                {
                    //Count how many courses client input
                    do
                    {
                        System.out.print("\nEnter non-course name: ");
                        String blockName = scanner.nextLine();
                        System.out.print("Enter block schedule seperated by a semi-colon(e.g., MW 2:00PM-3:50PM; F 10:00AM-12:00PM): ");
                        String blockSchedule = scanner.nextLine();

                        // Create a non course and add it to the group
                        Course nonCourse = new Course(blockName, blockSchedule, true);
                        nonCourse.parse();
                        group.addNonCourse(nonCourse);

                        System.out.print("\nDo you want to add another non-course? (yes/no): ");
                        response = scanner.nextLine();

                    }while(response.equalsIgnoreCase("yes"));
                    
                }

                System.out.print("Do want to add anything else? (yes/no): ");
                response = scanner.nextLine();
            }while (response.equalsIgnoreCase("yes"));
        
            //Adds created groups to group arraylist
            groups.add(group);
            System.out.print("Do you want to add another group? (yes/no):");
            resPonse = scanner.nextLine();
        }while (resPonse.equalsIgnoreCase("yes"));
        
        // Save groups to a file
        System.out.print("Do you want to save your work? (yes/no): ");
        String saveResponse = scanner.nextLine();
        if (saveResponse.equalsIgnoreCase("yes")) 
        {
            SaveRead.save(groups);
            System.out.println("Groups successfully saved.");
        }

        // Check for conflicts and display schedule
        System.out.println("\nAll Groups Created:");
            
        // Check for conflicts and display schedule
            System.out.println("\nConflict Checking Options:");
            System.out.println("1. Check conflict for a specific group");
            System.out.println("2. Check conflict for all groups");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) 
            {
                case 1:
                    // Check conflict for a specific group by name
                    System.out.println("\nGroups Available:");
                    for (int i = 0; i < groups.size(); i++) 
                    {
                        System.out.println("- " + groups.get(i).getName());
                    }
                    System.out.print("\nEnter the group name to check conflicts: ");
                    String groupName = scanner.nextLine();
            
                    // This loop finds the name of a specific group
                    Group selectedGroup = null;
                    for (int i = 0; i < groups.size(); i++) 
                    {
                        if (groups.get(i).getName().equalsIgnoreCase(groupName)) 
                        {
                            selectedGroup = groups.get(i);
                            
                        }
                    }
            
                    if (selectedGroup != null) 
                    {
                        System.out.println("Checking conflict for Group: " + selectedGroup.getName());
            
                        if (!Conflict.checkConflicts(selectedGroup)) 
                        {
                            System.out.println("\nNo conflicts found. Displaying schedule:\n");
                            Display.displaySchedule(selectedGroup);
                        } else 
                        {
                            System.out.println("\nPlease resolve conflicts before viewing the schedule.");
                        }
                    } 
                    else 
                    {
                        System.out.println("Group not found. ");
                    }
                    
                    break;
                case 2:
                    // Check conflict for all groups
                    System.out.println("\nChecking conflicts for all groups:");
                    for (int i = 0; i < groups.size(); i++) 
                    {
                        Group group = groups.get(i);
                        System.out.println("Checking conflict for Group: " + group.getName());
                        if (!Conflict.checkConflicts(group)) 
                        {
                            System.out.println("\nNo conflicts found. Displaying schedule:\n");
                            Display.displaySchedule(group);
                        }
                        else 
                        {
                            System.out.println("\nPlease resolve conflicts before viewing the schedule.");
                        }
                    }
                    break;
            
                default:
                    System.out.println(" Please restart and try again.");
                    break;
            }
    
        scanner.close();
    }
}
