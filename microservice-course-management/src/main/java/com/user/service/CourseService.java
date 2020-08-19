package com.user.service;

import java.util.List;
import com.user.model.Course;
import com.user.model.Transaction;

public interface CourseService {
	
    List<Course> allCourses();

    Course findCourseById(Long courseId);

    List<Transaction> findTransactionsOfUser(Long userId);

    List<Transaction> findTransactionsOfCourse(Long courseId);

    Transaction saveTransaction(Transaction transaction);
}
