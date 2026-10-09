package Day6;


class Device{
    void call(){
        System.out.println("We can explore many things");
    }
}

class oldPhone extends Device{
    void walkytaly(){
        System.out.println("We can only talk");
    }
}

class SmartPhone extends oldPhone{
    void internet(){
        System.out.println("We can see eachother while talking");
    }
}

public class MultiLevelInherit {
    public static void main(String args[]){
        SmartPhone mySmartPhone = new SmartPhone();
        mySmartPhone.call();
        mySmartPhone.walkytaly();
        mySmartPhone.internet();
    }
    
}
