void draw() {
  noLoop();
  println(sum(1, 2));
  println(sum(2, 2));
  println(sum(22, 33));
  println(sum(55, 44));

  println(multiply(1, 2));
  println(multiply(2, 2));
  println(multiply(22, 33));
  println(multiply(55, 44));
}

int sum(int numA, int numB) {
  return numA + numB;
}

float multiply(float numA, float numB) {
  return numA * numB;
}