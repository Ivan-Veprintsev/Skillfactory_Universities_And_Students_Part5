import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import objects.Statistics;
import objects.Student;
import objects.University;
import util.ComparatorUtil;
import util.JsonUtil;
import util.StatisticsUtil;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        List<University> universities = XSSFReader.readUniversities();
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.ID);
        universities.sort(universityComparator);

        String universitiesJson = JsonUtil.universityListToJson(universities);
        System.out.println(universitiesJson);

        List<University> universityList = JsonUtil.jsonToUniversityList(universitiesJson);
        System.out.println(universities.size() == universityList.size());

        universities.forEach(university -> {
            String universityJson = JsonUtil.universityToJson(university);
            System.out.println(universityJson);

            University universityFromJson = JsonUtil.jsonToUniversity(universityJson);
            System.out.println(universityFromJson);
        });

        List<Student> students = XSSFReader.readStudents();
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.FULL_NAME);
        students.sort(studentComparator);

        String studentsJson = JsonUtil.studentListToJson(students);
        System.out.println(studentsJson);

        List<Student> studentList = JsonUtil.jsonToStudentList(studentsJson);
        System.out.println(students.size() == studentList.size());

        students.forEach(student -> {
            String studentJson = JsonUtil.studentToJson(student);
            System.out.println(studentJson);

            Student studentFromJson = JsonUtil.jsonToStudent(studentJson);
            System.out.println(studentFromJson);
        });

        List<Statistics> statisticsList = StatisticsUtil.createStatistics(students, universities);
        XSSFWriter.writeXlsStatistics(statisticsList, "src\\main\\resources\\statistics.xlsx");
    }

}
