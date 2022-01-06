#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>

using namespace std;

int x1,y1,x2,y2;
void draw_point(double a, double b)
{
    glBegin(GL_POINTS);
        glVertex2f(a,b);
    glEnd();

    Sleep(10);
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

    draw_point(x1,y1);
    draw_point(x2,y2);

    double m=(double)(y2-y1)/(double)(x2-x1);
    if(abs(m)<1.0)
    {
        ///calculate y  using
        /// yi+1 = yi + m*DelataX
        ///setting DeltaX to 1

        int cnt=abs(x1-x2) + 1;
        double now_x=x1;
        double now_y=y1;

        for(int i=0;i<cnt;i++)
        {
            now_y=now_y + m;///delataX equals to 1
            now_x+=1.0;
            draw_point(now_x,now_y);
        }
    }
    else
    {
        ///calculate x using
        ///xi+1 = xi + DeltaY/m
        ///setting Deltay to 1

        int cnt=abs(y1-y2) + 1;
        double now_x=x1;
        double now_y=y1;

        for(int i=0;i<cnt;i++)
        {
            now_x=now_x+ 1.0/m;///Deltay equals 1
            now_y+=1.0;
            draw_point(now_x,now_y);
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

    cout<<"Enter Four Points x1 ,y1 and x2 ,y2 "<<endl;
    cin>>x1>>y1>>x2>>y2;

    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}
