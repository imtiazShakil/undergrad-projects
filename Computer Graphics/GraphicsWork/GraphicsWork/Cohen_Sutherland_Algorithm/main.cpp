#include <windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <iostream>
#include <string>
#include <vector>

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

int MAX_X=640, MAX_Y=480;
int xmin=-100,xmax=100;
int ymin=-100,ymax=100;


struct line {
    int x1,y1,x2,y2;
    line(int a, int b, int c, int d) {
        x1=a;y1=b;x2=c;y2=d;
    }
};

vector< line >v;

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
    glutSwapBuffers();
    return ;
}

int sign(int val)
{
    if(val>0) return 1;
    return 0;
}
int check_clipping_candidate(int x1, int y1, int x2, int y2)
{
    int mask1=0;
    int bit1= sign(y1-ymax);
    int bit2=sign(ymin-y1);
    int bit3=sign(x1-xmax);
    int bit4=sign(xmin-x1);

    mask1= (bit1<<3) | (bit2<<2) | (bit3<<1) | (bit4<<0);

    int mask2=0;
    bit1=sign(y2-ymax);
    bit2=sign(ymin-y2);
    bit3=sign(x2-xmax);
    bit4=sign(xmin-x2);

    mask2= (bit1<<3) | (bit2<<2) | (bit3<<1) | (bit4<<0);

    if(mask1==0 && mask2==0) return 0;
    if(mask1&mask2) return 1;
    if( (mask1&mask2)==0) return 2;///clipping candidate
}
void draw_rectangle( paii a, paii b, paii c, paii d)
{
    draw_line(a.fs,a.sc,b.fs,b.sc);draw_line(b.fs,b.sc,c.fs,c.sc);
    draw_line(c.fs,c.sc,d.fs,d.sc);draw_line(d.fs,d.sc,a.fs,a.sc);

    return;
}

vector<paii>pt;
void get_intersection_points(int x1,int y1, int x2, int y2 , int cnt) ///intersection Point is stored in vector<paii>pt
{
//    deb("rec ",x1,y1,x2,y2);
//    if(abs(x1-x2)<=1 && abs(y1-y2)<=1 ) {pt.pb(mp(x1,y1) );return ;}
    if(check_clipping_candidate(x1,y1,x2,y2)!=2) return ;
    if(cnt>=10) { pt.pb(mp(x1,y1)); return ;}

    int xm=(x1+x2)/2;
    int ym=(y1+y2)/2;

    get_intersection_points(x1,y1,xm,ym,cnt+1);
    get_intersection_points(xm,ym,x2,y2,cnt+1);

    return ;
}


static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1,0,0);
    string str="COHEN SUTHERLAND ALGORITHM";
    printBitmap(str,-MAX_X/2+2,-MAX_Y/2+2);


    draw_rectangle( mp(xmin,ymin ) , mp(xmax,ymin ) , mp(xmax,ymax ) , mp(xmin,ymax ) );

    for(int i=0;i<SZ(v);i++)
    {
        int res=check_clipping_candidate(v[i].x1,v[i].y1,v[i].x2,v[i].y2);
        if(res==0) {    ///visible
            glColor3d(0,1,0);
//            glPointSize(10.0);
            glLineWidth(2.0);

            draw_line(v[i].x1,v[i].y1,v[i].x2,v[i].y2);
        }
        else if(res==1) {
            glColor3d(1,0,1);
            glLineWidth(2.0);
            draw_line(v[i].x1,v[i].y1,v[i].x2,v[i].y2);
        }else {         ///clipping candidate

            pt.clear();
            int a=v[i].x1;
            int b=v[i].y1;
            int c=v[i].x2;
            int d=v[i].y2;

            get_intersection_points (a,b,(a+c)/2,(b+d)/2 ,0) ;

            ///invalid line
            glColor3d(1,0,0);
            glLineWidth(2.0);
            draw_line(pt[0].fs,pt[0].sc,a,b);

            get_intersection_points ((a+c)/2,(b+d)/2 ,c,d,0) ;
            draw_line(pt[1].fs,pt[1].sc,c,d);


//            deb(pt[0].fs,pt[0].sc,pt[1].fs,pt[1].sc);
            ///valid line
            glColor3d(0,0,1);
            glLineWidth(2.0);
            draw_line(pt[0].fs,pt[0].sc,pt[1].fs,pt[1].sc);
        }
    }

    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(MAX_X,MAX_Y);
    glutInitWindowPosition(10,10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("COHEN SUTHERLAND ALGORITHM");
    gluOrtho2D(-MAX_X/2,MAX_X/2,-MAX_Y/2,MAX_Y/2);

    int cnt , x1,y1,x2,y2;
    cout<<"Enter number of Lines"<<endl;
    cin>>cnt;

    cout<<"Enter Their Coordinates"<<endl;
    while(cnt--) {
        cin>>x1>>y1>>x2>>y2;
        v.pb( line(x1,y1,x2,y2) );
    }


    glutDisplayFunc(display);
    glClearColor(1,1,1,1);


    glutMainLoop();

    return EXIT_SUCCESS;
}

/*
3
-70 07 30 23
120 120 145 210
-120 120 120 -120
*/
