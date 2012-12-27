public class graph {
double x,y;
boolean status;
public graph(double a,double b){
    this.x=a;
    this.y=b;
    System.out.println("X="+a+"Y="+b);
}
void setstatus(boolean b){
    this.status=b;
}
boolean getstatus(){return this.status;}
double getx(){return this.x;}
double gety(){return this.y;}
}
