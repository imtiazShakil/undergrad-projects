#include<windows.h>
#include<stdio.h>
#include<GL/glut.h>
#include<iostream>
#include<math.h>
#include<iostream>

using namespace std;

int D=50;
int V[4][8] = {{50,150,150,50,50,50,150,150},{50,50,150,150,150,50,50,150},{50,50,50,50,150,150,150,150},{1,1,1,1,1,1,1,1}};
int Per[4][4] = {{D,0,0,0},{0,D,0,0},{0,0,0,0},{0,0,1,D}};
int dot[4][8];


void Mat_Mul(int a[][4], int b[][8])
{
    int i = 0,j = 0, k = 0;
    memset(dot,0,sizeof dot);
    for(i=0; i<4; i++)
    for( j=0; j<8; j++)
    for( k=0; k<4; k++)
        dot[i][j] +=  a[i][k] * b[k][j];

    for ( i=0; i<8; i++ )
    for ( j=0; j<2; j++ )
        dot[j][i]/=dot[3][i];
}

void change_Perspective_Matrix()
{
    memset(Per,0,sizeof Per);
    Per[0][0]=D;
    Per[1][1]=D;

    Per[3][2]=1;
    Per[3][3]=D;
    return ;
}
void drawLine(int x1,int y1,int x2,int y2)
{
//    cout<<"in line"<<endl;
    double x = 640/(double)2.0;
    double y = 480/(double)2.0;
//    glColor3d(1,0,0);
    glBegin(GL_LINES);
    glVertex2f(x1/x,y1/y);
    glVertex2f(x2/x,y2/y);
    glEnd();
}


static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(0,0,1);
    drawLine(0,0,320,0);
    drawLine(0,0,0,320);
    drawLine(0,0,-320,-240);

//    Sleep(50);

    Mat_Mul(Per,V);
    glColor3d(1,0,0);
//    for(int i=0;i<8;i++) cout<<V[0][i]<<" "<<V[1][i]<<endl;
    drawLine( V[0][0], V[1][0], V[0][1], V[1][1] );
    drawLine( V[0][1], V[1][1], V[0][2], V[1][2] );
    drawLine( V[0][2], V[1][2], V[0][3], V[1][3] );
    drawLine( V[0][3], V[1][3], V[0][0], V[1][0] );

    int dis = (int)(sqrt((abs(V[2][0]-V[2][7])*abs(V[2][0]-V[2][7]))+(abs(V[2][0]-V[2][7])*abs(V[2][0]-V[2][7])))/2);
    drawLine( V[0][4]+dis, V[1][4]+dis, V[0][5]+dis, V[1][5]+dis);
    drawLine( V[0][5]+dis, V[1][5]+dis, V[0][6]+dis, V[1][6]+dis);
    drawLine( V[0][6]+dis, V[1][6]+dis, V[0][7]+dis, V[1][7]+dis );
    drawLine( V[0][7]+dis, V[1][7]+dis, V[0][4]+dis, V[1][4]+dis );

    drawLine( V[0][0], V[1][0], V[0][5]+dis, V[1][5]+dis);
    drawLine( V[0][1], V[1][1], V[0][6]+dis, V[1][6]+dis);
    drawLine( V[0][2], V[1][2], V[0][7]+dis, V[1][7]+dis);
    drawLine( V[0][3], V[1][3], V[0][4]+dis, V[1][4]+dis);

    glColor3f(0,1,0);

    drawLine( dot[0][0], dot[1][0], dot[0][1], dot[1][1] );
    drawLine( dot[0][1], dot[1][1], dot[0][2], dot[1][2] );
    drawLine( dot[0][2], dot[1][2], dot[0][3], dot[1][3] );
    drawLine( dot[0][3], dot[1][3], dot[0][0], dot[1][0] );

    dis = (int)(sqrt((abs(dot[2][0]-dot[2][7])*abs(dot[2][0]-dot[2][7]))+(abs(dot[2][0]-dot[2][7])*abs(dot[2][0]-dot[2][7])))/2);
    drawLine( dot[0][4]+dis, dot[1][4]+dis, dot[0][5]+dis, dot[1][5]+dis);
    drawLine( dot[0][5]+dis, dot[1][5]+dis, dot[0][6]+dis, dot[1][6]+dis);
    drawLine( dot[0][6]+dis, dot[1][6]+dis, dot[0][7]+dis, dot[1][7]+dis );
    drawLine( dot[0][7]+dis, dot[1][7]+dis, dot[0][4]+dis, dot[1][4]+dis );

    drawLine( dot[0][0], dot[1][0], dot[0][5]+dis, dot[1][5]+dis);
    drawLine( dot[0][1], dot[1][1], dot[0][6]+dis, dot[1][6]+dis);
    drawLine( dot[0][2], dot[1][2], dot[0][7]+dis, dot[1][7]+dis);
    drawLine( dot[0][3], dot[1][3], dot[0][4]+dis, dot[1][4]+dis);

    glFlush();
    glutSwapBuffers();
}

static void key(unsigned char key, int x, int y)
{
    switch (key)
    {
        case 'q':
            exit(0);
            break;

        case 'a':
            D+=30;
            break;

        case 'd':
            D-=30;
            break;
    }
    change_Perspective_Matrix();

    glutPostRedisplay();
}


int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_SINGLE | GLUT_DEPTH);
    glutInitWindowSize(640,480);
    glutInitWindowPosition(10,10);
    glutCreateWindow("Projection");
    glClearColor(0.0, 0.0, 0.0, 0);
    glutDisplayFunc(display);
    glutKeyboardFunc(key);
    glutMainLoop();
    return 0;
}
