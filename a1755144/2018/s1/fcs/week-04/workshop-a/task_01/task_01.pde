int input = 3;
int output;

if (input < 0) {
  output = input * -1;
} else if (input < 10) {
  output = input * -2;
} else {
  output = input + 1;
}
text(output, 40, 40);