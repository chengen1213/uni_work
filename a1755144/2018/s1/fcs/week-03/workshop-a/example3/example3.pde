int multiarray[][] = new int[3][3];

println(multiarray.length);
println(multiarray[0].length);

for(int row=0; row<multiarray.length; row++){
  for(int col=0; col<multiarray[row].length; col++){
    multiarray[row][col] = row + col + (1*row);
    println("["+row+","+col+"]:"+multiarray[row][col]);
    text(multiarray[row][col],col*10+10,row*10+10);
  }
}