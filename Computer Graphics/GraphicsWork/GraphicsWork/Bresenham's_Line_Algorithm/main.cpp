#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>

using namespace std;

template<class T1> void deb(T1 e){cout<<e<<endl;}
template<class T1,class T2> void deb(T1 e1,T2 e2){cout<<e1<<" "<<e2<<endl;}
template<class T1,class T2,class T3> void deb(T1 e1,T2 e2,T3 e3){cout<<e1<<" "<<e2<<" "<<e3<<endl;}
template<class T1,class T2,class T3,class T4> void deb(T1 e1,T2 e2,T3 e3,T4 e4){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<endl;}
template<class T1,class T2,class T3,class T4,class T5> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<endl;}
template<class T1,class T2,class T3,class T4,class T5,class T6> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5,T6 e6){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<" "<<e6<<endl;}


int x1,x2,y1,y2;

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
static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);
    if(x2==x1) {draw_line(x1,y1,x2,y2);return ;}

    if(y2<y1) swap(x1,x2),swap(y1,y2);
    double m=(double)(y2-y1)/(double)(x2-x1);

    if(m>0.0)
     {
        int dx=x2-x1 , dy=y2-y1, dT=2*(dy-dx) , dS=2*dy;
        int d=2*dy-dx;
        draw_point(x1,y1);
        while(x1<x2) {
            x1++;
            if(d<0) d= d+dS;
            else y1++,d=d+dT;
            draw_point(x1,y1);
        }
    }else {

        int dx=x2-x1 , dy=y2-y1, dT=2*(-dy-dx) , dS=-2*dy;
        int d=-2*dy-dx;
        draw_point(x1,y1);
        while(x1>x2) {
            --x1;
            if(d>0) d= d+dS;
            else y1++,d=d+dT;
            draw_point(x1,y1);
        }
    }

    glutSwapBuffers();
}
int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640,480);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("Pre_Code");
    gluOrtho2D(-320,320,-240,240);

    cout<<"Enter Four Points x1 y1 And x2 y2"<<endl;
    cin>>x1>>y1>>x2>>y2;

    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}
