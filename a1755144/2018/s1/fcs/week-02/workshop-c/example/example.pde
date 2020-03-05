size(240, 240);
strokeWeight(4);

for (int x=0; x<width; x=x+10) {
  for (int y=0; y<height; y=y+10) {
    stroke(x, y, 255-y);
    point(x, y);
  }
}