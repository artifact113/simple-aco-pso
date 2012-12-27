public class ACO implements datas{
    
    graph cities[];//all the points  
    double phero[][];//T
    double dist[][];//distance from one point to the other
    double invdist[][];//1/distance
    double totaldist=0.0;
    int tovisit,visited,dest,pos,r;
public ACO()
{cities=initcities();//set all points of the graph
     dist=calcdistbetweenpoints();//calculate distance between all points
     invdist=calcinvdist();//calculate the 1/distance values
     phero=initpheromones();//set initial pheromone levels in the pheromone matrix
     pos=getbeginningpos();//get a random beginning position
     r=0;//values in route array to keep a note of the route
     route[r]=pos;//set the randomly found beginning position as starting point
     cities[pos].setstatus(true);//set it as visited
     tovisit=city-1;//remainning no of cities to visit
     visited=1;//already visited
     System.out.println("The selected position to begin:"+pos);
    }
private int getbeginningpos()
{int d;
    do{d=(int)(10*Math.random());}while(d<0&&d>=city);
    return d;
    }
private graph[] initcities()
{graph local[]=new graph[city];
    for(int i=0;i<city;i++)
    {   local[i]=new graph(points[i][1],points[i][2]);
        local[i].setstatus(false);
     }
  return local;
}
private double[][] calcdistbetweenpoints()
{double local[][]=new double[city][city];
for(int i=0;i<city;i++)
for(int j=0;j<city;j++)
 local[i][j]=calceuclideandist(cities[i],cities[j]);
return local;
}
double calceuclideandist(graph a,graph b)
{
    return (Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)));
}
private double[][] calcinvdist()
{double local[][]=new double[city][city];
for(int i=0;i<city;i++)
for(int j=0;j<city;j++)
{if(i!=j)
    local[i][j]=1.0/dist[i][j];
else
    local[i][j]=0.0;
}
return local;
}
private double[][] initpheromones()
{   double local[][]=new double[city][city];
    for(int i=0;i<city;i++)
        for(int j=0;j<city;j++)
        local[i][j]=init_pheromone;
        return local;
}
private double calculateprob(int position,int dest)
{double p,sum=0.00;
for(int i=0;i<city;i++)
    sum+=((Math.pow(phero[position][i], alpha))*(Math.pow((invdist[position][i]),beta)));
p=(Math.pow(phero[position][dest], alpha))*(Math.pow((invdist[position][dest]),beta))/sum;
    return p;
}
private int getposwithbestprob(int position)
{int i,p=position; double hiprob=10e-10,prob;
for(i=0;i<city;i++)
{prob=calculateprob(position,i);
    if(prob>hiprob&&!cities[i].getstatus())
    {hiprob=prob;p=i;}
}return p; 
}
public void begin()
{int dest=-1;
    while(visited<city)
    {dest=getposwithbestprob(pos);
        System.out.println("Next City found:"+dest);
        moveant(dest);
        visited++;
        dest=-1;
 }
    displayresults();
}
private void moveant(int dest)
{
    route[r++]=dest;
    updatepheromone(pos,dest);
    totaldist+=calceuclideandist(cities[pos],cities[dest]);
    pos=dest;
    cities[pos].setstatus(true);
}
private void displayresults()
{
    System.out.println("The Final Route is:");
    for(int i=0;i<city;i++)
    {
        System.out.println((i+1)+"\t"+cities[route[i]].getx()+"\t"+cities[route[i]].gety());
    }
    System.out.println("Total Distance="+totaldist);
}
void updatepheromone(int p,int d)
{double delT=Q/calceuclideandist(cities[p],cities[d]);
        double calcphero=((1-rho)*phero[p][d])+delT;
        phero[p][d]=calcphero;
        phero[d][p]=calcphero;
}
public static void main(String args[])
{
    ACO obj=new ACO();
             obj.begin();
}
}
