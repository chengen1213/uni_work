class Main{ 

    public static double circleArea(double r){
        double area = 0, pi = 3.14;
        area = pi * r * r;
        return area;
    }

    public static void main(String args[]){
        double r = 10;
        double area = circleArea(r);
        System.out.println("The area for this circle is " + area);
    }
}