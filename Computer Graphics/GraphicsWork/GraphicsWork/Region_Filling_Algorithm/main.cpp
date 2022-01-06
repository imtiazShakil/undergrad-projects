#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <queue>
#define SZ(x) (int)x.size()
#define fs      first
#define sc      second
#define pb push_back
#define mp make_pair

using namespace std;

typedef pair<int,int> paii;

template<class T1> void deb(T1 e){cout<<e<<endl;}
template<class T1,class T2> void deb(T1 e1,T2 e2){cout<<e1<<" "<<e2<<endl;}
template<class T1,class T2,class T3> void deb(T1 e1,T2 e2,T3 e3){cout<<e1<<" "<<e2<<" "<<e3<<endl;}
template<class T1,class T2,class T3,class T4> void deb(T1 e1,T2 e2,T3 e3,T4 e4){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<endl;}
template<class T1,class T2,class T3,class T4,class T5> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<endl;}
template<class T1,class T2,class T3,class T4,class T5,class T6> void deb(T1 e1,T2 e2,T3 e3,T4 e4,T5 e5,T6 e6){cout<<e1<<" "<<e2<<" "<<e3<<" "<<e4<<" "<<e5<<" "<<e6<<endl;}


int rrr[]={1,0,-1,0};int ccc[]={0,1,0,-1}; //4 Direction
int MAX_X=640, MAX_Y=480;
set<paii>S1;
set<paii>S2;

struct data {
    int a[4];
    data(int x, int y, int z, int zz)
    {
        a[0]=x;a[1]=y;a[2]=z;a[3]=zz;
    }
};

vector<data>v;
int seed_x, seed_y;

void draw_point(double a, double b , int track)
{

    int x1=a,y1=b;

    glBegin(GL_POINTS);
        glVertex2d(x1,y1);
    glEnd();

    if(track) {
        S1.insert( mp(x1,y1) );
    }
    if(!track) Sleep(1);
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

void draw_line(int x1, int y1, int x2, int y2)
{
    if(x1==x2)
    {
        for(int i=min(y1,y2);i<=max(y1,y2);i++) draw_point(x1,i,1);
        return ;
    }
    else if(y1==y2)
    {
        for(int i=min(x1,x2);i<=max(x1,x2);i++) draw_point(i,y1,1);
        return ;
    }

    draw_point(x1,y1,1);draw_point(x2,y2,1);

    double slope=(y2-y1)/(double)(x2-x1);
    double intercept=1.0*y1-slope*x1;

    if( abs(slope)>1.0 )
    {
        int mn=min(y1,y2);
        int mx=max(y1,y2);

        for(int i=mn;i<=mx;i++)
        {
            double now_x= (1.0*i-intercept)/slope;
            draw_point(now_x,i,1);
        }
    }
    else {
        int mn=min(x1,x2);
        int mx=max(x1,x2);

        for(int i=mn;i<=mx;i++)
        {
            double now_y=slope*i + intercept;
            draw_point(i,now_y,1);
        }
    }
}


void bfs(int x, int y)
{
    queue<int>Q;
    Q.push(x);Q.push(y);
    S2.insert( mp(x,y) );
    while(!Q.empty())
    {
        x=Q.front();Q.pop();
        y=Q.front();Q.pop();

        S2.insert( mp(x,y) );
        draw_point(x,y,0);
        for(int i=0;i<4;i++)
        {
            int p=x+rrr[i];
            int q=y+ccc[i];
            if(p>=MAX_X/2 || q>=MAX_Y/2 || p<= (-MAX_X/2) || q<= (-MAX_Y/2) ) continue;
            if(S2.find( mp(p,q) )!=S2.end() ) continue;
            if(S1.find(mp(p,q))!=S1.end() ) continue;

            Q.push(p);
            Q.push(q);
        }
    }
}
static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);
    string str="REGION FILLING ALGORITHM";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+2);


    for(int i=0;i<SZ(v);i++) draw_line( v[i].a[0] ,v[i].a[1] ,v[i].a[2] ,v[i].a[3] );

    bfs(seed_x,seed_y);

    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(MAX_X,MAX_Y);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("REGION FILLING ALGORITHM");
    gluOrtho2D(-MAX_X/2,MAX_X/2,-MAX_Y/2,MAX_Y/2);


    int cnt,a,b,c,d;
    cout<<"Enter No of Boundaries"<<endl;
    cin>>cnt;
    cout<<"Enter Their Coordinates x1,y1 and x2,y2"<<endl;
    while(cnt--)
    {
        cin>>a>>b>>c>>d;
        v.pb( data(a,b,c,d) );
    }

    cout<<"Enter Seed value x,y"<<endl;
    cin>>seed_x>>seed_y;

    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}

/*
4
-10 0 0 -30
0 -30 40 0
40 0 0 90
0 90 -10 0
0 0
*/
