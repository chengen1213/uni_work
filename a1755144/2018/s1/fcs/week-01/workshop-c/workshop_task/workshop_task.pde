int rect_x = 50;
int rect_y = 50;
int rect_w = 50;
int rect_h = 30;
size(640,320);
point(4,5);
line(1,2,rect_x,rect_y);
rect(rect_x,rect_y,rect_w,rect_h);
ellipse(rect_x+rect_w/2,rect_y+rect_h/2,rect_w,rect_h);
point(rect_x+rect_w/2,rect_y+rect_h/2);

//int x1 = 1;
//int x2 = 0;
//int y1 = 30;
//int y2 = 25;
//line(1,0,4,5);
//line(1,0,20,20);
//line(x1*2,x2*2,y1*2,y2*2);
//int answer = (x1 + y2) * x2;
//text(answer,50,50);