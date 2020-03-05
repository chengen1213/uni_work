size(170, 170);
int box_size=width/4;
int padding=width/10;
int number_padding = box_size/2 -padding;
textSize(25);
int multiarray[][]={{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
for (int row=0; row < multiarray.length; row++) {
  for (int col=0; col < multiarray[row].length; col++) {
    if (row==0) {
      fill(190,0,0);
    } else if (row==1) {
      fill(0,190,0);
    } else if (row==2) {
      fill(0,0,190);
    }
    rect(col*box_size+padding, row*box_size+padding, box_size, box_size);
    fill(0);
    text(multiarray[row][col],((col+1)*box_size+number_padding)-padding,((row+1)*box_size+number_padding));
  }
}