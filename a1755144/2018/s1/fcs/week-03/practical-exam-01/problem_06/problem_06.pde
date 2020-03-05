int subtraction(float a, float b) 
{
 float answer = a - b; //lost ";" , use variable without define
 return round(answer); //did not return the required type of data
}

void setup()
{
 size(300,300);
 background(subtraction(-1,55.0)); //lost ";"
}

void draw(){ 
//method should be put in a runable block(setup or draw)  
println("Your subtraction is <" + subtraction(-1,55.0) + ">"); //lost ";"
}