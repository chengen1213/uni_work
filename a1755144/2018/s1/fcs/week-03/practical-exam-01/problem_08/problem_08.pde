void setup() {
  size(640, 160);
}
void draw() {
  noLoop();
  int numOfRec = 10;
  int numOfColor = 3;
  int xpos = 30;
  int ypos = 80;
  int [] frog_colors = {255, 200, 150, 100, 50};
  rectangles_background(numOfRec, numOfColor, frog_colors);
  draw_frog(xpos, ypos, frog_colors);
}

void rectangles_background(int x, int y, int [] frog_colors) {
  int wid = width/x;
  for (int i = 0; i < x; i++) {
    fill(frog_colors[i%frog_colors.length]);
    rect(wid*i, 0, wid, height);
  }
}
void draw_frog(int x, int y, int [] frog_colors) {
  translate(-50+x, -80+y);
  fill(255);
  ellipse(30, 60, 20, 20);
  ellipse(70, 60, 20, 20);
  fill(0);
  ellipse(30, 60, 10, 10);
  ellipse(70, 60, 10, 10);
  fill(0, frog_colors[1], 0);
  ellipse(50, 80, 70, 40);
  fill(0);
  ellipse(40, 75, 5, 5);
  ellipse(60, 75, 5, 5);
  line(35, 85, 50, 90);
  line(50, 90, 65, 85);
}