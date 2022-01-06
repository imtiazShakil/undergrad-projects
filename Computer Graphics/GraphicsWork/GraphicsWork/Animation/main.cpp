#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <time.h>
#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <string>
#include <vector>

#define SZ(x) (int)x.size()
#define fs      first
#define sc      second
#define pb push_back
#define mp make_pair
#define PI (2.0*acos(0))

using namespace std;

typedef pair<int,int> paii;
typedef pair<double,double> padd;

template<class T1> void deb(T1 e){cout<<e<<endl;}
template<class T1,class T2> void deb(T1 e1,T2 e2){cout<<e1<<" "<<e2<<endl;}
template<class T1,class T2,class T3> void deb(T1 e1,T2 e2,T3 e3){cout<<e1<<" "<<e2<<" "<<e3<<endl;}
template<class T1,class T2,class T3,class T4> void deb(T1 e1,T2 e2,T3 e3,T4 e4){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<endl;}
template<class T1,class T2,class T3,class T4,class T5> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<endl;}
template<class T1,class T2,class T3,class T4,class T5,class T6> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5,T6 e6){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<" "<<e6<<endl;}


int MAX_X=720, MAX_Y=640;

struct data {
    vector<padd>cord;
    padd center;
    data(vector<padd>cc, paii cntr)
    {
        cord=cc;center=cntr;
    }
};

vector<data>v;
void draw_line(double a,double b, double c,double d)
{

    glBegin( GL_LINES );
        glVertex2f(a,b);
        glVertex2f(c,d);
    glEnd();

//    glFlush();
//    glutSwapBuffers();
    return ;
}

void draw_polygon(int id)
{
    if(id==-1)
        for(int i=0;i<SZ(v);i++)
        {
            for(int j=0;j<SZ( v[i].cord )-1;j++)
                draw_line( v[i].cord[j].fs,v[i].cord[j].sc,v[i].cord[j+1].fs,v[i].cord[j+1].sc );
        }
    else
    {
        int i=id;
        for(int j=0;j<SZ( v[i].cord )-1;j++)
            draw_line( v[i].cord[j].fs,v[i].cord[j].sc,v[i].cord[j+1].fs,v[i].cord[j+1].sc );
    }
//    glutSwapBuffers();
}

void translation(int id, int upd_x, int upd_y)
{
    int flag=0;
    if(upd_x==-1) flag=1;

    if(upd_x==-1) upd_x=rand()%50;
    if(upd_y==-1) upd_y=rand()%50;

    if(flag) {
        if(rand()%2) upd_x*=-1;
        if(rand()%2) upd_y*=-1;
    }

    for(int i=0;i<SZ(v[id].cord);i++) v[id].cord[i].fs+=upd_x , v[id].cord[i].sc+=upd_y;
//    glutSwapBuffers();
    v[id].center.fs+=upd_x;
    v[id].center.sc+=upd_y;

//    draw_polygon(id);
    return ;
}

void scaling(int id)
{
    double zoom;
    int cnt=rand()%4;
    if(cnt==0) zoom=1;
    else if(cnt==1) zoom=1.2;
    else if(cnt==2) zoom=.8;
    else zoom=1.;

    double c1=v[id].center.fs;
    double c2=v[id].center.sc;

    translation(id,-c1,-c2);

    for(int i=0;i<SZ(v[id].cord);i++) v[id].cord[i].fs*=zoom,v[id].cord[i].sc*=zoom;
    translation(id,c1,c2);

//    glutSwapBuffers();
    return ;
}

void rotation(int id)
{
    double ang=(30.0/180.0)*PI;
    if(rand()%2) ang*=-1.0;

    double c1=v[id].center.fs;
    double c2=v[id].center.sc;
    translation(id,-c1,-c2);

    double x,y;
    for(int i=0;i<SZ(v[id].cord);i++){
        x=v[id].cord[i].fs;
        y=v[id].cord[i].sc;

        v[id].cord[i].fs=x*cos(ang) - y*sin(ang) ,v[id].cord[i].sc=x*sin(ang) +y*cos(ang);
    }
    translation(id,c1,c2);

//    glutSwapBuffers();
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


static void display(void)
{
    glLineWidth(2.0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//    glColor3d(1,0,0);

    double r1=rand()%30;
    double r2=rand()%30;
    double r3=rand()%30;

    glColor3d(cos(r1),sin(r2),sin(r3));

    draw_polygon(-1);

    int id=rand()%SZ(v);
    int typ=rand()%3;

    if(typ==0) translation(id,-1,-1);
    else if(typ==1) scaling(id);
    else if(typ==2) rotation(id);

    Sleep(180);
    glutPostRedisplay();

    string str="ANIMATION DONE BY SHAKIL";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+2);



    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(MAX_X,MAX_Y);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("ANIMATION DONE BY SHAKIL");
    gluOrtho2D(-MAX_X/2,MAX_X/2,-MAX_Y/2,MAX_Y/2);

    srand (time(NULL));
    vector<padd>crd;
    int cnt=10;

    while(cnt--)
    {
        crd.clear();

        double xxx=( rand()%(MAX_X/2) );
        double yyy=( rand()%(MAX_Y/2) );

        if(rand()%2) xxx*=-1;
        if(rand()%2) yyy*=-1;

        padd cc=mp(xxx,yyy);
        int poly=30+rand()%30;

        crd.pb( mp( xxx,yyy+poly ) );
        crd.pb( mp( -poly+xxx,-poly+yyy ) );
        crd.pb( mp(poly+15+xxx,yyy) );
        crd.pb( mp(-poly-15+xxx,yyy) );
        crd.pb( mp(poly+xxx,-poly+yyy) );
        crd.pb( mp(xxx,poly+yyy) );

        v.pb( data(crd,cc) );
    }


    glutDisplayFunc(display);
    glClearColor(0,0,0,0);


    glutMainLoop();

    return EXIT_SUCCESS;
}
