package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static junit.framework.TestCase.assertEquals;

public class Lab4 {
    protected Validator<Student> studentValidator;
    protected Validator<Tema> temaValidator;
    protected Validator<Nota> notaValidator;

    protected StudentXMLRepository fileRepository1;
    protected TemaXMLRepository fileRepository2;
    protected NotaXMLRepository fileRepository3;

    protected Service service;

    //assigning the values
    @Before
    public void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }
/*
    public void testaddGradeBigBang(){
        service.saveTema("2", "desc", 10, 1);
        service.saveStudent("2", "titus", 934);
        assertEquals(service.saveNota("2", "2", 8, 3, "alabalaportocala"), 1);
    }

    public void testaddStudentBigBang(){
        assertEquals(service.saveStudent("1", "titus", 934), 0);
    }

    public void testaddAssignmentBigBang(){
        assertEquals(service.saveTema("1", "desc", 10, 1), 0);
    }

    public void testintegrateBigBang(){
        testaddGradeBigBang();
        testaddAssignmentBigBang();
        testaddStudentBigBang();
    }*/

    @Test
    public void testAddStudentTopDown(){
        assertEquals(0, this.service.saveStudent("12", "TTTT", 934));
    }

    @Test
    public void testAddAssignmentTopDown(){
        this.testAddStudentTopDown();
        assertEquals(0, this.service.saveTema("12", "SSVV", 10, 8));
    }

    @Test
    public void testAddGradeTopDown(){
        this.testAddStudentTopDown();
        this.testAddAssignmentTopDown();
        assertEquals(0, this.service.saveNota("12", "12", 9.8, 10, "perfect"));
    }
}
