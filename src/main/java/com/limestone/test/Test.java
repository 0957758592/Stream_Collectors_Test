package main.java.com.limestone.test;

// Please, do not use internet or any other sources of information during the test
// tast shouldn’t take more than 30 minutes
// There are 3 classes: Student, Subject and Mark
// - Fill the data according to the following conditions (use lists)
// 1. It is known that there are such students in the group: Valery Popov, Semyon Korzhev, Peter Ivanov, Maria Semenova and Kolya Nesterenko
// 2. Mathematics, Physics, Astronomy, History and Ethics are learned by this group and all subjects are mandatory excluding Ethics. It is optional.
// 3. Fill the data about marks if it is known that students have mark 3 for mandatory subjects and Maria has mark 5 for History and Ethics.
// 4. Please print results in a such way:
//   Popova Valeria Mathematics-1 Physics-2 Astronomy-0 History-1 Ethics-3 (do not display the subject info if there is no data about it)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        // 1
        List<Student> students = getStudentsList();

        // 2
        List<Subject> subjects = getSubjectList();

        // 3
        List<Mark> markList = getMarkList(students, subjects);

        // 4
        Map<Student, List<Mark>> result = markList
                .stream()
                .collect(Collectors.groupingBy(s -> s.getStudent(),
                        Collectors.mapping(s -> s, Collectors.toList())));

        result.forEach((student, mark) ->
                System.out.printf("%s %s: %s \n",
                        student.getFirstName(),
                        student.getLastName(),
                        mark.stream()
                                .flatMap((m) ->
                                        m.getSubject().isMandatory() ?
                                                Arrays.asList(m.getSubject().getName() + "-" + m.getRank()).stream() :
                                                Arrays.asList().stream())
                                .collect(Collectors.toList())
                                .toString().replaceAll("[\\[\\]\\,]", "")));
    }


    private static List<Mark> getMarkList(List<Student> students, List<Subject> subjects) {
        List<Mark> markList = new ArrayList<>();


        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            for (int j = 0; j < subjects.size(); j++) {
                Subject subject = subjects.get(i);
                Subject subjectCopy = new Subject();
                subjectCopy.setName(subject.getName());

                if (student.getFirstName().equals("Maria") || j < 3) {
                    subjectCopy.setMandatory(true);
                } else {
                    subjectCopy.setMandatory(false);
                }

                markList.add(new Mark(student, subjectCopy, j));
            }
        }
        return markList;
    }

    private static List<Student> getStudentsList() {

        Student[] students = {
                new Student("Valery", "Popov"),
                new Student("Semyon", "Korzhev"),
                new Student("Peter", "Ivanov"),
                new Student("Maria", "Semenova"),
                new Student("Kolya", "Nesterenko")
        };

        List<Student> studentsList = Arrays.asList(students);

        return studentsList;
    }

    private static List<Subject> getSubjectList() {

        List<Subject> subjectList = new ArrayList<>();

        subjectList.add(new Subject("Mathematics", true));
        subjectList.add(new Subject("Physics", true));
        subjectList.add(new Subject("Astronomy", true));
        subjectList.add(new Subject("History", true));
        subjectList.add(new Subject("Ethics", false));

        return subjectList;
    }
}
