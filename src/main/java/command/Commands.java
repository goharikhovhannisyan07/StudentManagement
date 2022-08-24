package command;

public interface Commands {
    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENTS = 2;
    int DELETE_STUDENT_BY_INDEX = 3;
    int PRINT_STUDENTS_BY_LESSON = 4;
    int PRINT_STUDENT_COUNT = 5;
    int CHANGE_STUDENT_LESSON = 6;
    int ADD_LESSON = 7;
    int PRINT_ALL_LESSON = 8;

    int EXIT=0;
    int LOGIN=1;
    int REGISTER=2;



    static void printCommands() {
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
        System.out.println("Please input " + ADD_STUDENT + " for add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " for print all students");
        System.out.println("Please input " + DELETE_STUDENT_BY_INDEX + " for delete student by index");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for print student by lesson");
        System.out.println("Please input " + PRINT_STUDENT_COUNT + " for print students count");
        System.out.println("Please input " + CHANGE_STUDENT_LESSON + " for change student's lesson");
        System.out.println("Please input " + ADD_LESSON + " for Add lesson");
        System.out.println("Please input " + PRINT_ALL_LESSON + " for print all lesson");
    }

    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " for Exit");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");
    }

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
        System.out.println("Please input " + ADD_STUDENT + " for add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " for print all students");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for print student by lesson");
        System.out.println("Please input " + PRINT_STUDENT_COUNT + " for print students count");
        System.out.println("Please input " + PRINT_ALL_LESSON + " for print all lesson");
    }
}
