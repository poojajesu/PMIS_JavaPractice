package Day6;


class Shape{
    void draw(){
        System.out.println("Draw a shape");
    }
}

class Triangle extends Shape{
    void drawTriangle() {
        System.out.println("Draw a Triangle");
    }
}

class Circle extends Shape{
    void drawCircle(){
        System.out.println("Draw a Circle ");
    }
}

class Rectangle extends Shape{
    void drawRectangle(){
        System.out.println("Draw a Rectangle");

    }
}
public class HierarchyInherit {
    public static void main(String args[]){
        Rectangle myRect = new Rectangle();
        Circle myCir = new Circle();
        Triangle myTri = new Triangle();


        myRect.draw();
        myTri.drawTriangle();
        myCir.drawCircle();
        myRect.drawRectangle();

        
    }
    
}
