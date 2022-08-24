package storage;

import model.Student;

public class StudentStorage {
    private Student[] array = new Student[10];
    private int size = 0;

    public void add(Student student) {
        if (array.length == size) {
            extend();
        }
        array[size++] = student;
    }

    private void increaseArray() {
        Student[] temp = new Student[array.length + 10];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    private void extend() {
        Student[] temp = new Student[array.length + 10];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("invalid index");
        } else {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }

    }

    public void printStudentByLessonName(String lessonName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getLesson().getName().equals(lessonName)) {
                System.out.println(array[i]);
            }


        }
    }

    public int getSize() {
        return size;
    }


    public Student getstudentByIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }
}
