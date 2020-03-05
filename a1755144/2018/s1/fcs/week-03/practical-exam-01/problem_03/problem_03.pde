size(640, 360);
background(0);
stroke(153);
strokeWeight(4);
strokeCap(SQUARE);

int a = 50;
int b = 120;
int c = 180;

line(a, b, a+c, b);
line(a, b+10, a+c, b+10);
line(a, b+20, a+c, b+20);
line(a, b+30, a+c, b+30);

line(a+c, b+b, a+2*c, b+b);
line(a+c, b+10+b, a+2*c, b+10+b);
line(a+c, b+20+b, a+2*c, b+20+b);
line(a+c, b+30+b, a+2*c, b+30+b);

line(a+2*c, b, a+3*c, b);
line(a+2*c, b+10, a+3*c, b+10);
line(a+2*c, b+20, a+3*c, b+20);
line(a+2*c, b+30, a+3*c, b+30);