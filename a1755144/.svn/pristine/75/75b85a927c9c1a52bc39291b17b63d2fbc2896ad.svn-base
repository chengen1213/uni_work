//output settings
size(350, 100);
background(255);
fill(0);

//define varaibles
int times = 10;
int maxnum = 10;
int temnum = 0;
int sum = 0;
float avg = 0;

//generate random numbers
for (int i = 0; i < times; i++) {
  temnum = round(random(maxnum)); 
  println(temnum);
  text("The generated numbers are:", 10, 15);
  text("\""+temnum+"\"", 10+maxnum*i*3, 30);
  sum = sum +temnum;
}

//calculate avg
avg = float(sum)/times;
println("average = " + avg);
text("average = " + avg, 20, 60);