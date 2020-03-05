//settings for canvas
size(640, 320);
background(255);

//control variables
int times = 10;
int maxnum = 10;
int unit = 25;
int xpos = 20;
int ypos = 300;
int sum = 0;
float avg = 0;

//generate random numbers and draw
for (int i = 1; i <= times; i++) {

  int temnum = round(random(maxnum)); 
  sum = sum +temnum;

  //asign colors  
  if (temnum % 2 == 0) {
    fill(0, 255-200/maxnum*temnum, 0);
  } else {
    fill(255-200/maxnum*temnum, 0, 0);
  }
  rect(xpos, ypos-unit*i, temnum*unit, unit);
  fill(66);
  text(temnum, xpos+temnum*unit+unit/2, ypos-unit*i+unit/2);
}
//caculate avg
avg = float(sum)/times;

//x,y axis
line(xpos, ypos, xpos, 0);
line(xpos, ypos, width, ypos);

//skecth avg line
fill(255, 0, 0);
stroke(255, 0, 0);
line(xpos+avg*unit, ypos-unit*times, xpos+avg*unit, ypos);
text("avg = " + avg, avg*unit-unit/2, ypos-unit*times-unit/2); 