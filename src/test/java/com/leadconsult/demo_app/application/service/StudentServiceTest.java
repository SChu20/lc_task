package com.leadconsult.demo_app.application.service;

import com.leadconsult.demo_app.application.dto.StudentDTO;
import com.leadconsult.demo_app.application.exception.ResourceNotFoundException;
import com.leadconsult.demo_app.application.util.Mapper;
import com.leadconsult.demo_app.domain.model.Course;
import com.leadconsult.demo_app.domain.model.Group;
import com.leadconsult.demo_app.domain.model.Student;
import com.leadconsult.demo_app.domain.port.CourseRepositoryPort;
import com.leadconsult.demo_app.domain.port.GroupRepositoryPort;
import com.leadconsult.demo_app.domain.port.StudentRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepositoryPort studentRepository;

    @Mock
    private GroupRepositoryPort groupRepository;

    @Mock
    private CourseRepositoryPort courseRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentDTO studentDTO;
    private Group group;
    private List<Course> courses;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        group = new Group();
        group.setId(1L);

        Course course1 = new Course();
        course1.setId(1L);
        course1.setStudents(new ArrayList<>());

        Course course2 = new Course();
        course2.setId(2L);
        course2.setStudents(new ArrayList<>());

        courses = List.of(course1, course2);

        student = new Student();
        student.setName("Ivan Ivanov");
        student.setAge(20);
        student.setGroup(group);
        student.setCourses(new ArrayList<>());

        studentDTO = new StudentDTO("Ivan Ivanov", 20, 1L, List.of(1L, 2L));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(mapper.fromStudentList(any())).thenReturn(List.of(studentDTO));

        List<StudentDTO> result = studentService.getAllStudents();
        assertEquals(1, result.size());
        assertEquals("Ivan Ivanov", result.get(0).name());
    }

    @Test
    void testRemoveStudent() {
        studentService.remove(1L);
        verify(studentRepository).delete(1L);
    }

    @Test
    void testFindByCourse() throws ResourceNotFoundException {
        when(studentRepository.findByCourseId(1L)).thenReturn(List.of(student));
        when(mapper.fromStudentList(any())).thenReturn(List.of(studentDTO));

        List<StudentDTO> result = studentService.findByCourse(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testSaveStudent() throws ResourceNotFoundException {
        when(mapper.fromStudentDTO(any())).thenReturn(student);

        StudentDTO result = studentService.saveStudent(studentDTO);
        verify(studentRepository).save(any());
        assertEquals("Ivan Ivanov", result.name());
    }

    @Test
    void testUpdateStudent() throws ResourceNotFoundException {
        when(studentRepository.findById(1L)).thenReturn(student);
        when(groupRepository.findById(1L)).thenReturn(group);
        when(courseRepository.findAllById(List.of(1L, 2L))).thenReturn(courses);

        StudentDTO result = studentService.updateStudent(1L, studentDTO);

        verify(studentRepository).save(any(Student.class));
        assertEquals("Ivan Ivanov", result.name());
    }

    @Test
    void testUpdateStudent_InvalidCourse() throws ResourceNotFoundException {
        when(studentRepository.findById(1L)).thenReturn(student);
        when(groupRepository.findById(1L)).thenReturn(group);
        when(courseRepository.findAllById(List.of(1L, 2L))).thenReturn(List.of(new Course()));

        assertThrows(ResourceNotFoundException.class,
                () -> studentService.updateStudent(1L, studentDTO));
    }

    @Test
    void testGetStudent() throws ResourceNotFoundException {
        when(studentRepository.findById(1L)).thenReturn(student);
        when(mapper.fromStudent(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.getStudent(1L);
        assertEquals("Ivan Ivanov", result.name());
    }

    @Test
    void testFindByCourseAndAge() throws ResourceNotFoundException {
        when(studentRepository.findByCourseIdAndAgeGreaterThan(1L, 18)).thenReturn(List.of(student));
        when(mapper.fromStudentList(any())).thenReturn(List.of(studentDTO));

        List<StudentDTO> result = studentService.findByCourseAndAge(1L, 18);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByGroup() throws ResourceNotFoundException {
        when(studentRepository.findByGroupId(1L)).thenReturn(List.of(student));
        when(mapper.fromStudentList(any())).thenReturn(List.of(studentDTO));

        List<StudentDTO> result = studentService.findByGroup(1L);
        assertEquals(1, result.size());
    }
}
