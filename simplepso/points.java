public class points {
double px,py;//(x,y) positions
double vx,vy;//(x,y) velocities
double pbest;
points(double p1,double p2,double v1,double v2)
{
    px=p1;py=p2;
    vx=v1;vy=v2;
    pbest=0;
}
void setpbest(double z){this.pbest=z;}
double getpbest()      {return pbest;}
double getpx(){return px;}
double getpy(){return py;}
double getvx(){return vx;}
double getvy(){return vy;}
}