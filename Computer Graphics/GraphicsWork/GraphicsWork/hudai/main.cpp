
#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>

using namespace std;


void draw()
{
    glBegin( GL_LINES );
        glVertex2f(0.,0.);
        glVertex2f(105.0,100.0);
    glEnd();

    glFlush();
    glutSwapBuffers();
    return ;

}
static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(0,0,1);


    draw();



    glFlush();
    glutSwapBuffers();
}



int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640,480);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    gluOrtho2D(-320,320,-240,240);
//    glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH);

    glutCreateWindow("GLUT Shapes");

    glutDisplayFunc(display);

    glClearColor(1,1,1,1);

    glutMainLoop();

    return EXIT_SUCCESS;
}
