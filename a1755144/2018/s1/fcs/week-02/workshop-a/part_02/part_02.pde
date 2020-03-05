size(640,120);
background(255);
int ncol = 10;
int x = 10;
int y = 80;
int gcolor = 250;
float rcolor = gcolor / ncol;
float gap = 80/ncol;
int y2 = int(gap);
for(int i = 1; i <=ncol; i++){
  fill(gcolor);
  rect(x,y,20,y2);
  gcolor = gcolor -int(rcolor);
  x = x + 20;
  y = y - int(gap);
  y2 = y2 + int(gap);
}