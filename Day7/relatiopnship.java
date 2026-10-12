package Day7;


class Engine{
    void start(){
        System.out.println("The engine has Started");

    }
}

class Car{
    private Engine engine;
    Car(){
        this.engine = new Engine();

    }
     void drive (){
        engine.start();
        System.out.println("Car is moving");
     }
    
}