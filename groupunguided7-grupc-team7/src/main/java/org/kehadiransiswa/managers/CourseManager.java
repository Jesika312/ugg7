package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.Course;
import org.kehadiransiswa.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    List<Course> courses = new ArrayList<>();
    private Connection connection;

    public CourseManager() {
        connection =DBConnectionManager.getConnection();
        courses = new ArrayList<>();
    }

    public static void main(String[] args) {
//        UserManager usr = new UserManager();
//        for (User user : usr.getAllUsers()) {
//            System.out.println(user.getEmail());
//        }
        CourseManager crs = new CourseManager();
        for (Course courses : crs.getAllCourse()){
            System.out.println(courses.getTitle());
        }
    }
    public List<Course> getAllCourse(){
        List<Course>listOfCourse = new ArrayList<>();
        try {
            Statement statement =connection.createStatement();
            ResultSet rs =statement.executeQuery("SELECT * from courses");
            while (rs.next()){
                int id = rs.getInt("id");
                String title= rs.getString("title");
                String desc = rs.getString("description");
                listOfCourse.add(new Course(id,title,desc));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return listOfCourse;
    }



    // Add methods for course management (create, edit, delete)
    public boolean addCourse(String title, String description) {
        Course newCourse = new Course(courses.size() + 1, title, description);
        if (!courses.contains(newCourse)) {
            courses.add(newCourse);
            return true;
        }
        return false;
    }

    public boolean editCourse(int courseId, String newTitle, String newDescription) {
        Course newCourse = new Course(courseId, newTitle, newDescription);
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                courses.set(i, newCourse);
            }
        }
        return false;

    }

    public boolean deleteCourse(int courseId) {
        int indexToDelete = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                indexToDelete = i;
            }
        }
        if (indexToDelete > 0) {
            courses.remove(indexToDelete);
        }
        return false;

    }

    public List<Course> getAllCourses() {
        return courses;
    }
}
