#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#define SZ(x) (int)x.size()
#define fs      first
#define sc      second
#define pb push_back
#define mp make_pair

using namespace std;

typedef pair<int,int> paii;

template<class T1> void deb(T1 e){cout<<e<<endl;}
template<class T1,class T2> void deb(T1 e1,T2 e2){cout<<e1<<" "<<e2<<endl;}
template<class T1,class T2,class T3> void deb(T1 e1,T2 e2,T3 e3){cout<<e1<<" "<<e2<<" "<<e3<<endl;}
template<class T1,class T2,class T3,class T4> void deb(T1 e1,T2 e2,T3 e3,T4 e4){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<endl;}
template<class T1,class T2,class T3,class T4,class T5> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<endl;}
template<class T1,class T2,class T3,class T4,class T5,class T6> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5,T6 e6){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<" "<<e6<<endl;}


int MAX_X=640, MAX_Y=480;
int poly_ymax=-1000,poly_ymin=1000;

struct data {
    int edge_id,ymin,ymax;
    int x_of_ymin;
    double slope;
    double now_x;

    data(int a, int b, int c, int d, double e, double f)
    {
        edge_id=a;ymin=b;ymax=c;
        x_of_ymin=d;
        slope=e;now_x=f;

    }
    data(double x)
    {
        now_x=x;
    }
};

vector<data>v;

bool com(data a, data b)
{
    return a.ymin<b.ymin;
}

void draw_point(double a, double b)
{
    glBegin(GL_POINTS);
        glVertex2f(a,b);
    glEnd();

    //Sleep(10);
    glFlush();
    glutSwapBuffers();
    return ;
}

void printBitmap(const string & msg, double x, double y)
{
    glRasterPos2d(x, y);
    for (string::const_iterator ii = msg.begin();
         ii != msg.end();
         ++ii)
    {
        glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *ii);
    }
}

void draw_line(double a,double b, double c,double d)
{
    glBegin( GL_LINES );
        glVertex2f(a,b);
        glVertex2f(c,d);
    glEnd();

    glFlush();
    glutSwapBuffers();
    return ;
}

void do_scan_line()
{
    int indx=0;
    for(int y=poly_ymin;y<=poly_ymax;y++)
    {
        vector<double>x;

        for(int i=indx;i<SZ(v);i++)
        {
            if(v[i].ymin>y) continue;

            if(v[i].ymin==y)
            {
                v[i]=data( v[i].x_of_ymin );
                x.pb( v[i].x_of_ymin );
//                v[i]=data(v[i].edge_id, v[i].ymin,v[i].ymax,v[i].x_of_ymin, v[i].slope, );
            }
            else if(v[i].ymin<=y && v[i].ymax>=y)
            {
                x.pb(v[i].now_x + 1.0/v[i].slope);
                v[i]=data( v[i].now_x + 1.0/v[i].slope );
            }
            if(v[i].ymax==y) indx++;
        }
        sort(x.begin(),x.end());

        for(int i=0;i<SZ(x);i+=2)
        {
            draw_line(x[i],y,x[i+1],y );
        }
        glFlush();
        glutSwapBuffers();
    }
    return ;
}

static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);
    string str="SCAN LINE ALGORITHM";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+2);


    do_scan_line();
    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(MAX_X,MAX_Y);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("SCAN LINE ALGORITHM");
    gluOrtho2D(-MAX_X/2,MAX_X/2,-MAX_Y/2,MAX_Y/2);

    int cnt;
    cout<<"Enter Number of Edges of the Polygon"<<endl;
    cin>>cnt;

    cout<<"Enter Their Coordinates x1,y1 and x2,y2"<<endl;

    int edge_id,ymin,ymax;
    int x_of_ymin;
    double slope;
    double now_x;

    int x1,y1,x2,y2;

    for(int i=0;i<cnt;i++)
    {
        cin>>x1>>y1>>x2>>y2;
        if(y1<y2) x_of_ymin=x1;
        else  x_of_ymin=x2;

        ymin=min(y1,y2);
        ymax=max(y1,y2);

        poly_ymin=min(poly_ymin,ymin);
        poly_ymax=max(poly_ymax,ymax);

        if(x1==x2) continue;
        slope=(y2-y1)/(x2-x1);

        v.pb( data(i,ymin,ymax,x_of_ymin,slope,0.0 ) );
    }


    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}

/*
4
-10 -10 10 -10
10 -10 10 10
10 10 -10 10
-10 10 -10 -10
*/
