import command.Commands;
import exeption.LessonNotFoundExeption;
import model.Lesson;
import model.Role;
import model.Student;
import model.User;
import storage.LessonStorage;
import storage.StudentStorage;
import storage.UserStorage;

import java.util.Scanner;

public class StudentDemo implements Commands {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentStorage studentStorage = new StudentStorage();
    private static LessonStorage lessonStorage = new LessonStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;


    public static void main(String[] args) {
        Lesson Java = new Lesson("Java", "Karen", 7, 35000);
        lessonStorage.add(Java);
        Lesson PHP = new Lesson("PHP", "Narek", 5, 25000);
        lessonStorage.add(PHP);
        Lesson English = new Lesson("English", "Mrs Zina", 6, 30000);
        lessonStorage.add(English);

        User admin = new User("Gog", "H-yan", "gog@mail.com", "gog2001", Role.ADMIN);
        userStorage.add(admin);
        studentStorage.add(new Student("Poxos", "Poxosyan", 25, "097558877", "Gyumri", Java, admin));
        studentStorage.add(new Student("Martiros", "Martirosyan", 23, "096557744", "Erevan", PHP, admin));
        studentStorage.add(new Student("Narek", "Boyajyan", 24, "098373781", "Gyumri", English, admin));
        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case 0:
                    run = false;
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("invalid command, please try again");

            }
        }


    }

    private static void login() {
        System.out.println("please input email, password");
        String emailPasswordSrt = scanner.nextLine();
        String[] emailPassword = emailPasswordSrt.split(",");
        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("user doesn't exists");
        } else {
            if (!user.getPassword().equals(emailPassword[1])) {
                System.out.println("password is wrong");
            } else {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();

                }
            }
        }
    }

    private static void userLogin() {
        System.out.println("Wlcome, " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser=null;
                    run = false;
                    System.out.println("Process Finished");
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentsByLessonName();
                    break;
                case PRINT_STUDENT_COUNT:
                    studentsCount();
                    break;
                case PRINT_ALL_LESSON:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("invalid command, please try again");
            }
        }

    }

    private static void register() {
        System.out.println("Please input name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct user data");
        } else {
            if (userStorage.getUserByEmail(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("User registred!");
            } else {
                System.out.println("User with " + userData[2] + "already exists");
            }

        }

    }

    private static void adminLogin() {
        boolean run = true;
        while (run) {
            Commands.printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser=null;
                    run = false;
                    System.out.println("Process Finished");
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteByIndex();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentsByLessonName();
                    break;
                case PRINT_STUDENT_COUNT:
                    studentsCount();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeStudentsLesson();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_ALL_LESSON:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("invalid command, please try again");
            }
        }
    }


    private static void changeStudentsLesson() {
        studentStorage.print();
        System.out.println("please choose student index");
        int index = Integer.parseInt(scanner.nextLine());
        Student student = studentStorage.getstudentByIndex(index);
        if (student == null) {
            System.out.println("Wrong Index");
            changeStudentsLesson();
        } else {
            if (lessonStorage.getSize() != 0) {
                lessonStorage.print();
                System.out.println("please choose lesson index");

                try {
                    int lessonIndex = Integer.parseInt(scanner.nextLine());
                    Lesson lesson = lessonStorage.getLessonByIndex(lessonIndex);
                    student.setLesson(lesson);
                    System.out.println("lesson changed!");
                } catch (LessonNotFoundExeption | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    changeStudentsLesson();
                }

            }
        }
    }


    private static void addLesson() {
        System.out.println("please input lesson name");
        String name = scanner.nextLine();

        System.out.println("please input lesson teacherName");
        String teacherName = scanner.nextLine();

        System.out.println("please input lesson diration by month");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.println("please input lesson price");
        double price = Double.parseDouble(scanner.nextLine());

        Lesson lesson = new Lesson(name, teacherName, duration, price);
        lessonStorage.add(lesson);
        System.out.println("lesson was make!");
    }


    private static void studentsCount() {
        System.out.println("students count");
        System.out.println(studentStorage.getSize());
    }

    private static void printStudentsByLessonName() {
        System.out.println("please input lesson name");
        String lessonName = scanner.nextLine();
        studentStorage.printStudentByLessonName(lessonName);
    }

    public static void deleteByIndex() {
        studentStorage.print();
        System.out.println("please choose student index");
        int index = Integer.parseInt(scanner.nextLine());
        studentStorage.deleteByIndex(index);
    }

    private static void addStudent() {
        if (lessonStorage.getSize() != 0) {
            lessonStorage.print();
            addLesson();
        } else {
            lessonStorage.print();
            System.out.println("please choos lesson index");

            Lesson lesson = null;
            try {
                int lessoIndex = Integer.parseInt(scanner.nextLine());
                lesson = lessonStorage.getLessonByIndex(lessoIndex);
                System.out.println("Please input Student's name");
                String name = scanner.nextLine();
                System.out.println("Please input Student's surname");
                String surname = scanner.nextLine();
                System.out.println("Please input Student's age");
                String agestr = scanner.nextLine();
                System.out.println("Please input Student's phoneNumber");
                String phoneNumber = scanner.nextLine();
                System.out.println("Please input Student's city");
                String city = scanner.nextLine();
                int age = Integer.parseInt(agestr);

                Student student = new Student(name, surname, age, phoneNumber, city, lesson, currentUser);
                studentStorage.add(student);
                System.out.println("Student created");
            } catch (LessonNotFoundExeption | NumberFormatException e) {
                System.out.println("please input coorect index or number");
                addStudent();
            }


        }


    }
}
