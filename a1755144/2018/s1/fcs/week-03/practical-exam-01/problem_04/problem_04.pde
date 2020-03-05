int numOfElement = 10;
int[] randomNums =new int [numOfElement];

for (int i = 0; i < randomNums.length; i++) {
  randomNums[i] = round(random(0, 153));
  println(randomNums[i]);
}