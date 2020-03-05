void setup() {
  size(640, 640);
  //mytext("my first function", 60, 60, 26, 100);
  //mytext("my first function", 60, 100, 28, 255);
  //mytext("my first function", 60, 150, 32, 0);
}
void mytext(String quote, int x, int y, int txt_size, int txt_color) {
  textSize(txt_size);
  fill(txt_color);
  text(quote, x, y);
}
void draw() {

  mytext("my first function", 60, 60, 26, 100);
  mytext("my first function", 60, 100, 28, 255);
  mytext("my first function", 60, 150, 32, 0);
  
}