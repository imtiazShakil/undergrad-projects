#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>
#include <math.h>

using namespace std;

template<class T1> void deb(T1 e){cout<<e<<endl;}
template<class T1,class T2> void deb(T1 e1,T2 e2){cout<<e1<<" "<<e2<<endl;}
template<class T1,class T2,class T3> void deb(T1 e1,T2 e2,T3 e3){cout<<e1<<" "<<e2<<" "<<e3<<endl;}
template<class T1,class T2,class T3,class T4> void deb(T1 e1,T2 e2,T3 e3,T4 e4){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<endl;}
template<class T1,class T2,class T3,class T4,class T5> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<endl;}
template<class T1,class T2,class T3,class T4,class T5,class T6> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5,T6 e6){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<" "<<e6<<endl;}

template<class T> inline T sqr(T x){return x*x;}

double r;

void draw_point(double a, double b)
{
    glBegin(GL_POINTS);
        glVertex2f(a,b);
    glEnd();

//    Sleep(1);
    glFlush();
    glutSwapBuffers();
    return ;
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

void make_8_points( double x, double y)
{
    draw_point(x,y);draw_point(-x,-y);
    draw_point(y,x);draw_point(-y,-x);
    draw_point(-y,x);draw_point(y,-x);
    draw_point(-x,y);draw_point(x,-y);

    return ;
}
static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);


    int x=0,y=r,p=1-r;
    while(x<=y) {

        make_8_points(x,y);
        if(p<0) p=p +2*x+3;
        else p= p+ 2*(x-y) + 5,--y;
        ++x;
    }
    glutSwapBuffers();
}
int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640,480);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("Circle Drawing MIDPOINT METHOD");
    gluOrtho2D(-320,320,-240,240);

    cout<<"Enter the radius For the Circle(0,0)"<<endl;
    cin>>r;


    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}
