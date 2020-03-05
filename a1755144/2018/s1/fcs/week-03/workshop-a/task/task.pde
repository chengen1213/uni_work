void setup() {

  size(400, 100);
  int unit = 20;

  int days = 31;
  float temperatures[] = new float[days];
  float range[][] = new float[5][days];
  int count[] = new int[5];
  temperatures[0] = 38.6;
  temperatures[1] = 36.2;
  temperatures[2] = 34.0;
  temperatures[3] = 32.5;
  temperatures[4] = 26.9;
  temperatures[5] = 28.8;
  temperatures[6] = 32.2;
  temperatures[7] = 33.0;
  temperatures[8] = 32.9;
  temperatures[9] = 31.5;
  temperatures[10] = 30.7;
  temperatures[11] = 21.7;
  temperatures[12] = 23.4;
  temperatures[13] = 30.2;
  temperatures[14] = 35.4;
  temperatures[15] = 23.3;
  temperatures[16] = 27.3;
  temperatures[17] = 33.5;
  temperatures[18] = 34.6;
  temperatures[19] = 34.4;
  temperatures[20] = 29.9;
  temperatures[21] = 22.6;
  temperatures[22] = 29.0;
  temperatures[23] = 32.5;
  temperatures[24] = 27.7;
  temperatures[25] = 33.3;
  temperatures[26] = 23.9;
  temperatures[27] = 24.6;
  temperatures[28] = 22.7;
  temperatures[29] = 19.7;
  temperatures[30] = 20.1;

  for (int i = 0; i < temperatures.length; i++) {
    if (temperatures[i]>=41 && temperatures[i] <= 50) {
      addval(range[0], temperatures[i]);
      count[0]++;
    } else if (temperatures[i]>=31 && temperatures[i] <= 40) {
      addval(range[1], temperatures[i]);
      count[1]++;
    } else if (temperatures[i]>=21 && temperatures[i] < 31) {
      addval(range[2], temperatures[i]);
      count[2]++;
    } else if (temperatures[i]>=11 && temperatures[i] < 21) {
      addval(range[3], temperatures[i]);
      count[3]++;
    } else if (temperatures[i]>=0 && temperatures[i] < 11) {
      addval(range[4], temperatures[i]);
      count[4]++;
    }
  }

  for (int i = 0; i <5; i++) {
    rect(0, i*unit, unit*count[i], unit);    
    text(count[i], unit*count[i]+unit/2, i*unit+unit/2);
  }
}

void addval(float[] range, float val) {
  for (int i = 0; i < range.length; i++) {
    if (range[i] == 0) {
      range[i] = val;
      return ;
    }
  }
}