//canvas settings
void setup() {
  size(200, 200);
  background(255);
  fill(0);
}
//set variables
void draw() {
  noLoop();
  int numOfElement = 4;
  int[] numbers = new int[numOfElement];//set numbers to be calculated
  numbers[0]=0;
  numbers[1]=1;
  numbers[2]=3;
  numbers[3]=7;
  int ypos =200/(numOfElement+1);//calculate yposition of text
  text("Input  Factorial", 50, 10);
  for (int i=0; i<numbers.length; i++) {//process elements in the array
    factorial(numbers[i], ypos*(i+1));
  }
}
//calculate factorial and display result
void factorial(int n, int ypos) {
  int factorial = 1; 
  if (n>=0) {
    for (int i = 1; i <= n; i++) { 
      factorial = factorial * i;//calculate factorial
    }
    text(n+"        "+factorial, 50, ypos);//display result
  } else {
    text(n+"        "+"not available", 50, ypos);//negative numbers do not have factorial
  }
}