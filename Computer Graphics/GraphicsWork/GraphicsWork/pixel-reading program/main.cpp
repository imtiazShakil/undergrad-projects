// readpixel.cpp
// Glenn G. Chappell
// 21 Sep 2010
//
// For CS 381 Fall 2010
// Example pixel-reading program

#include <windows.h>
#include <iostream>
using std::cout;
using std::cerr;
using std::endl;
#include <string>
using std::string;
#include <cstdlib>       // Do this before GL/GLUT includes
using std::exit;
#ifndef __APPLE__
# include <GL/glut.h>    // GLUT stuff, includes OpenGL headers as well
#else
# include <GLUT/glut.h>  // Apple puts glut.h in a different place
#endif


// Global variables
// Keyboard
enum { ESCKEY = 27 };         // ASCII value of escape character

// Window/viewport
const int startwinsize = 400; // Starting window width & height (pixels)
int winw, winh;               // Current window width, height (pixels)

// Cursor
double cursx, cursy;           // x, y pos for printing (camunits)
double cursheight;             // Height of text line (camunits)

// Color
GLfloat thecolor[3] = { 0.3, 0.3, 0.3 };
                              // Holds color of pixel that is read
                              // Defaults to dark-ish gray


// readapixel
// Reads pixel at mouse position (given, GLUT format)
// Puts color in thecolor (global GLfloat [3]).
// Assumes global winh holds height of window/viewport, in pixels.
void readapixel(int x, int y)
{
    glReadPixels(x, winh-y,  // Read pixel at mouse position
                 1, 1,       // 1 x 1 block of pixels (single pixel)
                 GL_RGB,     // Get R, G, B
                 GL_FLOAT,   // Return GLfloat, value 0..1
                 thecolor);  // Place to put the result
}


// printBitmap
// Prints the given string at the raster position cursx, cursy
//  using GLUT bitmap fonts and the current transformation.
// Decreases cursy by cursheight.
//
// NOTE: You probably don't want any rotations in the model/view
//  transformation when calling this function.
void printBitmap(const string & msg)
{
    glRasterPos2d(cursx, cursy);
    for (string::const_iterator ii = msg.begin();
         ii != msg.end();
         ++ii)
    {
        glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *ii);
    }
    cursy -= cursheight;
}


// myDisplay
// The GLUT display function
void myDisplay()
{
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw a random-ish scene with lots of colors
    //  in the left half of the viewport
    glColor3d(0.3, 0.2, 0.3);
    glRectd(-1.0, -1.0, -0.2, 0.4);

    glColor3d(0.4, 0.6, 0.9);
    glRectd(-0.9, -0.7, -0.5, 0.2);

    glColor3d(0.9, 0.0, 0.1);
    glRectd(-0.6, -0.8, -0.1, -0.4);

    glColor3d(1.0, 0.8, 0.2);
    glRectd(-0.9, 0.5, -0.1, 0.7);

    glColor3d(0.4, 0.8, 0.4);
    glRectd(-0.7, -0.2, -0.4, 1.0);

    glColor3d(0.8, 0.6, 0.4);
    glRectd(-0.8, -0.9, -0.4, -0.5);

    // Draw a solid color in the right half of the viewport
    glColor3fv(thecolor);
    glRectd(0.0, -100.0, 100.0, 100.0);

    // Draw documentation
    glLoadIdentity();
    glMatrixMode(GL_PROJECTION);  // Set up simple ortho projection
    glPushMatrix();
    glLoadIdentity();
    gluOrtho2D(-1., 1., -1., 1.);
    glColor3d(0., 0., 0.);        // Black text
    cursx = -0.9;                 // Start of first text line
    cursy = 0.9;
    cursheight = 0.1;             // Height of each text line
    printBitmap("Pixel Reading");
    printBitmap("Click somewhere!");
    printBitmap("Esc       Quit");
    glPopMatrix();
    glMatrixMode(GL_MODELVIEW);

    glutSwapBuffers();
}


// myMouse
// The GLUT mouse function
void myMouse(int button, int state, int x, int y)
{
    readapixel(x, y);  // Read & store pixel color
    glutPostRedisplay();
}


// myMotion
// The GLUT motion function
void myMotion(int x, int y)
{
    readapixel(x, y);  // Read & store pixel color
    glutPostRedisplay();
}


// myReshape
// The GLUT reshape function
void myReshape(int w, int h)
{
    // Save window dimensions
    winw = w;
    winh = h;

    // Set viewport
    glViewport(0, 0, w, h);

    // Projection: orthogonal.
    // We can see -1 to 1 in both x & y, aspect ratio is correct,
    //  and this area is as large as possible in the viewport.
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    // We set up coordinate system so that aspect ratios are always correct,
    //  and the region from -1..1 in x & y always just fits in the viewport.
    if (w > h)
    {
        gluOrtho2D(-double(w)/h, double(w)/h, -1., 1.);
    }
    else
    {
        gluOrtho2D( -1., 1., -double(h)/w, double(h)/w);
    }

    glMatrixMode(GL_MODELVIEW);  // Always go back to model/view mode
}


// myKeyboard
// The GLUT keyboard function
void myKeyboard(unsigned char key, int x, int y)
{
    switch (key)
    {
    case ESCKEY:  // ESC: Quit
        exit(0);
        break;
    }
}


// myIdle
// The GLUT idle function
void myIdle()
{
    // Print OpenGL errors, if there are any (for debugging)
    if (GLenum err = glGetError())
    {
        cerr << "OpenGL ERROR: " << gluErrorString(err) << endl;
    }
}


// init
// Initializes GL states
// Called by main after window creation
void init()
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
}


int main(int argc, char ** argv)
{
    // Initialize OpenGL/GLUT
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);

    // Make a window
    glutInitWindowSize(startwinsize, startwinsize);
    glutInitWindowPosition(50, 50);
    glutCreateWindow("CS 381 - Pixel Reading");

    // Initialize GL states & register GLUT callbacks
    init();
    glutDisplayFunc(myDisplay);
    glutMouseFunc(myMouse);
    glutMotionFunc(myMotion);
    glutReshapeFunc(myReshape);
    glutKeyboardFunc(myKeyboard);
    glutIdleFunc(myIdle);

    // Do something
    glutMainLoop();

    return 0;
}
