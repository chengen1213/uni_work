void setup() {
  size(320, 320);
}
void draw() {
  int first_number = 1;
  int second_number = 2;
  int sum_number = sum(first_number, second_number);
  textSize(26);
  text("sum: " + sum_number, 200, 200);
}