public class applypso implements datas{
points[] p;
double gbest;
double fitnesslist[];
int gbestind;
public applypso()
{
    p=initialize();
    gbest=0.0;
    gbestind=-1;
}

private void runalgo()
{int iter=1;
  do 
  { getgbest();
    System.out.println("New gbest="+gbest);
    update();
    System.out.println("Pass:"+iter+": New gbest="+gbest);
  System.out.println("Px= "+p[gbestind].getpx()+", Py= "+p[gbestind].getpy()+"; Pbest="+p[gbestind].getpbest());
    iter++;
   }while(!(Math.abs(gbest-target)<=tolerance)&iter<Max_iter);
  System.out.println("New gbest="+gbest);
  System.out.println("Px= "+p[gbestind].getpx()+", Py= "+p[gbestind].getpy()+"; Pbest="+p[gbestind].getpbest());
 }
private points[] initialize()
{points local[]=new points[swarm];
java.util.Random gen=new java.util.Random();
    for(int i=0;i<swarm;i++)
    {double px=gen.nextDouble() * 3.0 + 1.0;
     double py=gen.nextDouble() * 2.0 - 1.0;
     double vx=gen.nextDouble() * 2.0 - 1.0;
     double vy=gen.nextDouble() * 2.0 - 1.0;
        local[i]=new points(px,py,vx,py);
        local[i].setpbest(f(px,py));
    }
return local;
}
private void getgbest()
{   gbestind=0;
    gbest=p[gbestind].getpbest();
    for(int i=1;i<swarm;i++)
    {
        if(p[i].getpbest()<gbest)
        {gbest=p[i].getpbest();gbestind=i;}
    }
}
private void update()
{
    java.util.Random gen=new java.util.Random();
    for(int i=0;i<swarm;i++)
    if(i!=gbestind)
    {double px=p[i].getpx();
     double py=p[i].getpy();
     double vx=p[i].getvx();
     double vy=p[i].getvy();
     double r1=gen.nextDouble();
     double r2=gen.nextDouble();
     vx=(wt*vx)+(r1*(gbest-vx))+(r2*(p[gbestind].getvx()-vx));
     vy=(wt*vy)+(r1*(gbest-vy))+(r2*(p[gbestind].getvx()-vy));
     px=px+vx;
     py=py+vy;
     p[i].setpbest(f(px,py));
    }
    
}
private double f(double x,double y)//returns values of the fuction f(x,y)
{double d=Math.pow((2.8125 - x + x * Math.pow(y, 4)), 2) +
 Math.pow((2.25 - x + x * Math.pow(y, 2)), 2) +
 Math.pow((1.5 - x + x * y), 2);//(2.8125-x^2y^4)^2+(2.25-x+xy^2)^2+(1.5-x+xy)
    return d;
}
public static void main(String args[])
{applypso ap=new applypso();
ap.runalgo();
}
}
