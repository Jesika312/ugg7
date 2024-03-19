package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.ClassRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassManager {
    private Connection connection;
    private List<ClassRoom> classRooms = new ArrayList<>();


    public ClassManager() {
        connection = DBConnectionManager.getConnection();
    }

    // Add methods for class management (schedule, cancel, update)
    public boolean scheduleClass(int courseId, String date, String time, String duration, String location) {
        ClassRoom newClassRoom = new ClassRoom(classRooms.size() + 1, courseId, date, time, duration, location);
        if (!classRooms.contains(newClassRoom)) {
            classRooms.add(newClassRoom);
        }
        return false;

    }

    public boolean cancelClass(int classId) {
        int indexToDelete = -1;
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getId() == classId) {
                indexToDelete = i;
            }
        }
        if (indexToDelete > 0) {
            classRooms.remove(indexToDelete);
            return true;
        }
        return false;

    }

    public boolean updateClass(int classId, int courseId, String newDate, String newTime, String newDuration, String newLocation) {
        ClassRoom newClassRoom = new ClassRoom(classId,courseId,newDate, newTime, newDuration, newLocation);
        for (int i = 0; i < classRooms.size(); i++) {
            if(classRooms.get(i).getId() == classId){
                classRooms.set(i, newClassRoom);
            }
        }
        return false;

    }

    public List<ClassRoom> getAllClasses() {
        List<ClassRoom> listosclass = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM classrooms");

            while (rs.next()) {
                int id= rs.getInt("id");
                int courseId= rs.getInt("course_id");
                String date = rs.getString("date");
                String time= rs.getString("time");
                String duration= rs.getString("duration");
                String location= rs.getString("location");
                ClassRoom classRoom= new ClassRoom(id,courseId,date,time,duration,location);
                listosclass.add(classRoom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return listosclass;
    }


    public  static void main(String[] args){
        ClassManager clss= new ClassManager();
        List<ClassRoom> lou =clss.getAllClasses();
        for (ClassRoom classRoom : lou){
            System.out.println(classRoom.getCourseId());
            System.out.println(classRoom.getClass());
            System.out.println(classRoom.getLocation());
        }
    }
}