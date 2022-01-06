#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>

using namespace std;

/* GLUT callback Handlers */

int x1,x2,y1,y2;

void draw_point(double x, double y)
{
    glBegin( GL_POINTS );
        glVertex2f(x,y);
    glEnd();

    Sleep(10);

    glutSwapBuffers();
    glFlush();
}

static void display_line_drawing_direct_method(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);

    draw_point(x1,y1);draw_point(x2,y2);

    double slope=(y2-y1)/(double)(x2-x1);
    double intercept=1.0*y1-slope*x1;

    if( abs(slope)>1.0 )
    {
        int mn=min(y1,y2);
        int mx=max(y1,y2);

        for(int i=mn;i<=mx;i++)
        {
            double now_x= (1.0*i-intercept)/slope;
            draw_point(now_x,i);
        }
    }
    else {
        int mn=min(x1,x2);
        int mx=max(x1,x2);

        for(int i=mn;i<=mx;i++)
        {
            double now_y=slope*i + intercept;
            draw_point(i,now_y);
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
    glutCreateWindow("Direct Method For Line Drawing");
    gluOrtho2D(-320,320,-240,240);

    cout<<"Enter Four Points x1 y1 And x2 y2"<<endl;
    cin>>x1>>y1>>x2>>y2;

    glClearColor(1,1,1,1);

    glutDisplayFunc(display_line_drawing_direct_method);


    glutMainLoop();

    return EXIT_SUCCESS;
}
