package service;


import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.*;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.*;

/**
 * Unit test for simple service.Service.
 */
public class ServiceTest extends TestCase{
    protected Validator<Student> studentValidator;
    protected Validator<Tema> temaValidator;
    protected Validator<Nota> notaValidator;

    protected StudentXMLRepository fileRepository1;
    protected TemaXMLRepository fileRepository2;
    protected NotaXMLRepository fileRepository3;

    protected Service service;

    //assigning the values
    protected void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    public void testSaveTemaTrue(){
        assertEquals(service.saveTema("3", "descriere", 10, 3), 0);
    }

    public void testSaveTemaFalse(){
        assertEquals(service.saveTema(null, "descriere2", 12, 1), 1);
    }

    public void testaddStudentValid(){
        service.saveStudent("2", "titus", 934);
    }

    public void testaddStudentNullId(){
        assertEquals(service.saveStudent( null, "titus", 934), 1);
    }

    public void testaddStudentEmptyId(){
        assertEquals(service.saveStudent( "", "titus", 934), 1);
    }

    public void testaddStudentNullName(){
        assertEquals(service.saveStudent( "12", null, 934), 1);
    }

    public void testaddStudentEmptyName(){
        assertEquals(service.saveStudent( "12", "", 934), 1);
    }

    public void testaddStudentBigGroup(){
        assertEquals(service.saveStudent( "15", "titus", 999), 1);
    }

    public void testaddStudentSmallGroup(){
        assertEquals(service.saveStudent( "15", "titus", 100), 1);
    }


    public void testaddStudentBVAGroupSmallValid(){
        assertEquals(service.saveStudent( "12", "titus", 111), 0);
    }

    public void testaddStudentBVAGroupSmallInvalid(){
        assertEquals(service.saveStudent( "15", "titus", 110), 1);
    }

    public void testaddStudentBVAGroupBigValid(){
        assertEquals(service.saveStudent( "16", "titus", 937), 0);
    }
}
