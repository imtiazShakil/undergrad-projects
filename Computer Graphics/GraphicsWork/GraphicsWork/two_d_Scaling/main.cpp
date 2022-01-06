#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <string>
#include <iostream>
#include <vector>

#define SZ(x) (int)x.size()
#define fs      first
#define sc      second
#define pb push_back
#define mp make_pair

using namespace std;

typedef pair<int,int> paii;
typedef pair<double,double> padd;

int MAX_X=640,MAX_Y=480;
int poly=60;

vector<padd>v;

double zoom_x=1.0,zoom_y=1.0;

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
//    glutSwapBuffers();
    return ;
}

void scaling()
{
    for(int i=0;i<SZ(v);i++) v[i].fs*=zoom_x,v[i].sc*=zoom_y;
    glutSwapBuffers();
    return ;
}
void draw_polygon()
{
    for(int i=0;i<SZ(v)-1;i++)
    {
        draw_line( v[i].fs,v[i].sc,v[i+1].fs,v[i+1].sc );
    }
    glutSwapBuffers();
}
static void display(void)
{
    glLineWidth(2.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(.2,.7,.6);

//    transformation( (int)(a*10.0)%40);
    scaling();
    draw_polygon();


    string str="TWO DIMENSIONAL TRANSLATION";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+2);

    str="Press w/a/s/d to get the effects";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+20);


    glutSwapBuffers();
}


static void key(unsigned char key, int x, int y)
{
    switch (key)
    {
        case 'q':
            exit(0);
            break;

        case 'w':
            zoom_x=2.0;
            zoom_y=1.0;
            break;

        case 's':
            zoom_x=.5;
            zoom_y=1.0;
            break;
        case 'a':
            zoom_x=1.0;
            zoom_y=.5;
            break;
        case 'd' :
            zoom_x=1.0;
            zoom_y=2.0;
            break;
    }

    glutPostRedisplay();
}


/* Program entry point */

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(MAX_X,MAX_Y);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("TWO DIMENSIONAL Scaling");
    gluOrtho2D(-MAX_X/2,MAX_X/2,-MAX_Y/2,MAX_Y/2);

    v.pb( mp(0,poly) );
    v.pb( mp(-poly,-poly) );
    v.pb( mp(poly+15,0) );
    v.pb( mp(-poly-15,0) );
    v.pb( mp(poly,-poly) );
    v.pb( mp(0,poly) );

    glutDisplayFunc(display);
    glutKeyboardFunc(key);
//    glutIdleFunc(idle);

    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}
