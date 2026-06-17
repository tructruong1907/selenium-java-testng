package javaSDET.javaBasic;

public class ConditionLoopStatement {
    static void main(String[] args) {
        int workingDay = 22;
        int salary = 10500000;

        //Condition Statement - biểu thức điều kiện
        //if
        if(workingDay > 22){
            salary += 1000000;
        }
        System.out.println("Thưởng: " + salary);
        //if-else
        if(workingDay > 20){
            salary += 500000;
        }else{
            salary += 0;
        }
        System.out.println("Thưởng: " + salary);
        //if-else-ìf
        if(workingDay > 20){
            salary += 500000;
        } else if(workingDay > 18){
            salary += 200000;
        }else{
            salary += 0;
        }
        System.out.println("Thưởng: " + salary);

        //switch-case
        String brownserName = "Chrome";
        switch(brownserName){
            case "Chrome":
                System.out.println("Khởi tạo Chrome");
                break;
            case  "Firefox":
                System.out.println("Firefox");
                break;
            case   "IE":
                System.out.println("IE");
                break;
            default:
                System.out.println("Safari");
                break;
        }

        //Loop Statement - biểu thức vòng lặp
        int studentNumber = 50;

        //for (classic)
        for(int i = 1; i <= studentNumber; i++){
            System.out.println("Tặng 1 vé xe cho sinh viên: " + i);
        }

        //for-each
        String[] studentName = {"Truơng Ngọc Thanh Trúc", "Trương Ngọc Mi", "Trương Ngọc Hoàng"};
        for(String name: studentName){
            System.out.println(name);
        }
        //while


        //do-while
    }
}
