void setup() {
  int numOfEle = 5;
  int[] savAry = new int[numOfEle];
  for (int i = 0; i < savAry.length; i++ ) {
    int randomNum = round(random(10));
    addValues(savAry, randomNum);
    println(randomNum);
  }
  println(sum(savAry));
}

void addValues(int[] savAry, int valuesToAdd) {
  for (int i = 0; i < savAry.length; i++) {
    if (savAry[i] == 0) {
      savAry[i] = valuesToAdd;
      return;
    }
  }
}
int sum(int[] tgtAry) {
  int sum = 0;
  for (int val : tgtAry) {
    sum += val;
  }
  return sum;
}