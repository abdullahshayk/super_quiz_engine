package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "student_follow_teacher")
public class StudentFollowTeacher {
    @EmbeddedId
    private StudentFollowTeacherId id;

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("teacherTeacherId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_teacher_id", nullable = false)
    private Teacher teacherTeacher;

    public StudentFollowTeacherId getId() {
        return id;
    }

    public void setId(StudentFollowTeacherId id) {
        this.id = id;
    }

    public Student getStudentStudent() {
        return studentStudent;
    }

    public void setStudentStudent(Student studentStudent) {
        this.studentStudent = studentStudent;
    }

    public Teacher getTeacherTeacher() {
        return teacherTeacher;
    }

    public void setTeacherTeacher(Teacher teacherTeacher) {
        this.teacherTeacher = teacherTeacher;
    }

}