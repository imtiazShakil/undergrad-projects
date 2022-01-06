
#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>


static void display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(0,1,1);

    glBegin(GL_POLYGON);
        glVertex2d(0,0);
        glVertex2d(10,0);
        glVertex2d(10,10);
        glVertex2d(0,10);
    glEnd();

    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640,480);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    gluOrtho2D(-320,320,-240,240);

    glutCreateWindow("Creating Polygons");

    glutDisplayFunc(display);

    glutMainLoop();

    return EXIT_SUCCESS;
}
