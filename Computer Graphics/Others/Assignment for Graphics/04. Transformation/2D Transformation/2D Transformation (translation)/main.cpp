#include <graphics.h>
#include<windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif
#include <stdlib.h>
#include <iostream>
#include <stdio.h>
//#include<glut.h>

using namespace std;

POINT p[100];

void DrawLine(double x1, double y1, double x2, double y2)
{
    glBegin(GL_LINES);
        glColor3f(5, 200, 0);
        glVertex2f(x1,y1);
        glVertex2f(x2, y2);
    glEnd();

    glFlush();
    glutSwapBuffers();

    return;
}
void DrawMainObject(void)
{
    DrawLine(p[0].x,p[0].y,p[1].x,p[1].y);
    DrawLine(p[0].x,p[0].y,p[2].x,p[2].y);
    DrawLine(p[1].x,p[1].y,p[3].x,p[3].y);
    DrawLine(p[2].x,p[2].y,p[3].x,p[3].y);
    DrawLine(p[2].x,p[2].y,p[4].x,p[4].y);
    DrawLine(p[3].x,p[3].y,p[5].x,p[5].y);
    DrawLine(p[4].x,p[4].y,p[5].x,p[5].y);
    return;
}
void Initialize_MainObject(void)
{
    p[0].x=0; p[0].y=0;
    p[1].x=100; p[1].y=0;
    p[2].x=0; p[2].y=80;
    p[3].x=100; p[3].y=80;
    p[4].x=20; p[4].y=60;
    p[5].x=80; p[5].y=60;
    DrawLine(p[0].x,p[0].y,p[1].x,p[1].y);
    DrawLine(p[0].x,p[0].y,p[2].x,p[2].y);
    DrawLine(p[1].x,p[1].y,p[3].x,p[3].y);
    DrawLine(p[2].x,p[2].y,p[3].x,p[3].y);
    DrawLine(p[2].x,p[2].y,p[4].x,p[4].y);
    DrawLine(p[3].x,p[3].y,p[5].x,p[5].y);
    DrawLine(p[4].x,p[4].y,p[5].x,p[5].y);

    return;
}

void Start_translation(double tx, double ty)
{
    glClear(GL_COLOR_BUFFER_BIT);
    for(int i=0;i<6;i++)
    {
        p[i].x+=tx;
        p[i].y+=ty;
    }
    DrawMainObject();
    glFlush();


}

static void idle(void)
{
    glutPostRedisplay();
}

static void key(unsigned char key, int x, int y)
{
    glClear(GL_COLOR_BUFFER_BIT);
    switch (key)
    {
        case 27 :
        case 'q':
            exit(0);
            break;

        case 's':
            Start_translation(0,-50);
            glutSwapBuffers();
            break;

        case 'a':
            Start_translation(-50,0);
            glutSwapBuffers();
            break;
        case 'd':
            Start_translation(50,0);
            glutSwapBuffers();
            break;
        case 'w':
            Start_translation(0,50);
            glutSwapBuffers();
            break;

    }

    //glutPostRedisplay();
}


static void display(void)
{
    int ticket;
    double tx, ty;
    glClear(GL_COLOR_BUFFER_BIT);
    Initialize_MainObject();

    glutSwapBuffers();
    //glutDestroyWindow(1);
    return;
}




int main(int argc, char *argv[])
{
    //glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutInitWindowSize(1000,700);
    glutInitWindowPosition(900,100);
    glutCreateWindow("2d Transformation for Translation");
    glClearColor(0,0,0,0.0);
    gluOrtho2D(-500,500,-350,350);

    //glutReshapeFunc(resize);
    glutDisplayFunc(display);
    glutKeyboardFunc(key);
    //glutIdleFunc(idle);
    Sleep(50);
    glutMainLoop();
    return EXIT_SUCCESS;
}
