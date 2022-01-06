
#include<windows.h>
#include <GL/glut.h>
#include<iostream>
#include<stdio.h>
#include<time.h>

using namespace std;

int leftX,topY,rightX,bottomY;
int intsX,intsY;

typedef struct Color {
  int r;
  int g;
  int b;
};

void drawLine(int x1,int y1, int x2, int y2) {
    glBegin(GL_LINES);

    glVertex2f(x1,y1);
    glVertex2f(x2,y2);

    glEnd();
    glFlush();
}
void drawPolygon(int x[], int y[], int n,Color color) {
	glColor3f(color.r, color.g, color.b);

    int i;
    for(i=0;i<n-1;i++)   //n-1 edges.
        drawLine(x[i],y[i],x[i+1],y[i+1]);

   drawLine(x[i],y[i],x[0],y[0]);   //nth edge.
}

void makeViewPort() {
    drawLine(leftX,topY, leftX,bottomY);
    drawLine(leftX,bottomY, rightX,bottomY);
    drawLine(rightX,bottomY, rightX,topY);
    drawLine(rightX,topY, leftX,topY);
}

void getIntersect(int x1,int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
    intsX=0,intsY=0;

    intsX=( (x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4) ) / ( (x1-x2)*(y3-y4) -(y1-y2)*(x3-x4));
    intsY=( (x1*y2-y1*x2)*(y3-y4) - (y1-y2)*(x3*y4-y3*x4) ) / ( (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4) );
}

void clipPolygon(int xd[], int yd[], int n) {

    int x[n+1],y[n+1];      //copy data to new working array, ending with starting point.
    int id=0;
    for(id;id<n;id++) {
        x[id]=xd[id];
        y[id]=yd[id];
    }
    x[n]=xd[0],y[n]=yd[0];      //end of copying.
    int nst=0,nx[100], ny[100]; //pointer of new points, new point X, new point Y

    int c;  //determine left/right(In/Out) position of any point around any edge.
    int totalP=0;
    int i;
    for(i=0;i<n;i++) {

        bool firstIn=false,secondIn=false;      //position of 1st-point, 2nd-point
        bool isEdited=false;        //if any intersected value is accepted as new value.
        bool np1=false, np2=false;       //number of new points found in this loop


        c=(rightX-leftX)*(y[i]-bottomY)-(bottomY-bottomY)*(x[i]-leftX);
        if(c>=0)
            firstIn=true;
        c=(rightX-leftX)*(y[i+1]-bottomY)-(bottomY-bottomY)*(x[i+1]-leftX);
        if(c>=0)
            secondIn=true;


        if(firstIn && secondIn) {       //both are inside.
            np1=true;
            if(!isEdited) {
                nx[nst]=x[i],ny[nst]=y[i];
                isEdited=true;
            }
        }
        else if(firstIn) {      //1st->in, 2nd->out
            np2=true;
            isEdited=true;
            nx[nst]=x[i],ny[nst]=y[i];

            getIntersect(x[i],y[i], x[i+1],y[i+1], leftX,bottomY, rightX,bottomY);

            nx[nst+1]=intsX,ny[nst+1]=intsY;
        }
        else if(secondIn) {
            np1=true;
            isEdited=true;
            getIntersect(x[i],y[i], x[i+1],y[i+1], leftX,bottomY, rightX,bottomY);

            nx[nst]=intsX,ny[nst]=intsY;
        }



        firstIn=false,secondIn=false;
        c=(rightX-rightX)*(y[i]-bottomY)-(topY-bottomY)*(x[i]-rightX);
        if(c>=0)
            firstIn=true;

        c=(rightX-rightX)*(y[i+1]-bottomY)-(topY-bottomY)*(x[i+1]-rightX);
        if(c>=0)
            secondIn=true;


        if(firstIn && secondIn) {       //both are inside.
            np1=true;
            if(!isEdited) {
                nx[nst]=x[i],ny[nst]=y[i];
                isEdited=true;
            }
        }
        else if(firstIn) {      //1st->in, 2nd->out
            np2=true;
            isEdited=true;
            nx[nst]=x[i],ny[nst]=y[i];

            getIntersect(x[i],y[i], x[i+1],y[i+1], rightX,bottomY, rightX,topY);

            nx[nst+1]=intsX,ny[nst+1]=intsY;
        }
        else if(secondIn) {
            np1=true;
            isEdited=true;
            getIntersect(x[i],y[i], x[i+1],y[i+1], rightX,bottomY, rightX,topY);

            nx[nst]=intsX,ny[nst]=intsY;
        }

        firstIn=false,secondIn=false;
        c=(leftX-rightX)*(y[i]-topY)-(topY-topY)*(x[i]-rightX);
        if(c>=0)
            firstIn=true;

        c=(leftX-rightX)*(y[i+1]-topY)-(topY-topY)*(x[i+1]-rightX);
        if(c>=0)
            secondIn=true;


        if(firstIn && secondIn) {       //both are inside.
            np1=true;
            if(!isEdited) {
                nx[nst]=x[i],ny[nst]=y[i];
                isEdited=true;
            }
        }
        else if(firstIn) {      //1st->in, 2nd->out
            np2=true;
            isEdited=true;
            nx[nst]=x[i],ny[nst]=y[i];
            getIntersect(x[i],y[i], x[i+1],y[i+1], rightX,topY, leftX,topY);

            nx[nst+1]=intsX,ny[nst+1]=intsY;
        }
        else if(secondIn) {
            np1=true;
            isEdited=true;
            getIntersect(x[i],y[i], x[i+1],y[i+1], rightX,topY, leftX,topY);

            nx[nst]=intsX,ny[nst]=intsY;
        }


        firstIn=false,secondIn=false;
        c=(leftX-leftX)*(y[i]-topY)-(bottomY-topY)*(x[i]-leftX);
        if(c>=0)
            firstIn=true;

        c=(leftX-leftX)*(y[i+1]-topY)-(bottomY-topY)*(x[i+1]-leftX);
        if(c>=0)
            secondIn=true;


        if(firstIn && secondIn) {       //both are inside.
            np1=true;
            if(!isEdited) {
                nx[nst]=x[i],ny[nst]=y[i];
                isEdited=true;
            }
        }
        else if(firstIn) {      //1st->in, 2nd->out
            np2=true;
            isEdited=true;
            nx[nst]=x[i],ny[nst]=y[i];

            getIntersect(x[i],y[i], x[i+1],y[i+1], leftX,topY, leftX,bottomY);

            nx[nst+1]=intsX,ny[nst+1]=intsY;
        }
        else if(secondIn) {     //1st->out, 2nd->in
            np1=true;
            isEdited=true;
            getIntersect(x[i],y[i], x[i+1],y[i+1], leftX,topY, leftX,bottomY);

            nx[nst]=intsX,ny[nst]=intsY;
        }




        if(np2) {       //found new points
            nst+=2;        //next new point to start.
            totalP+=2;
        }
        else if(np1) {
            nst+=1;
            totalP+=1;
        }

    }

    printf("Total New Point=%d\n",totalP);
    cout<<endl<<"New Points"<<endl;

    for(i=0;i<nst;i++) {
        cout<<nx[i]<<" "<<ny[i]<<endl;

    }
    cout<<endl<<"--------------------"<<endl;

    Color color;
    color.r=0;
    color.g=0;
    color.b=1;
	drawPolygon(nx,ny,nst,color);
}


void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1, 0, 0);
    Color color;
    color.r=1;
    color.g=0;
    color.b=0;


	makeViewPort();



    int n=5;
	int x[n], y[n];
	x[0]=20,y[0]=40;
	x[1]=150,y[1]=50;
	x[2]=120,y[2]=100;
	x[3]=40,y[3]=140;
	x[4]=60,y[4]=90;

	drawPolygon(x,y,n,color);

	clipPolygon(x,y,n);     //total 5points.

	glutSwapBuffers();

}



int main(int argc,char *argv[])     //the main function, program starts from here.
{
    leftX=30,topY=130,rightX=130,bottomY=30;
	glutInit(&argc,argv);
	glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize (800, 500);                      //window size.
	glutInitWindowPosition (100, 100);              //window position
	glMatrixMode (GL_PROJECTION);

	glutCreateWindow ("Polygon Clipping");            //title

	glClearColor(0,0,0, 0.0);                        //clear with 0,0,0 (r,g,b)=black=background color
	gluOrtho2D (0, 200, 0, 150);


	glutDisplayFunc(display);           //-------the display function.contains what to display.



	glutMainLoop();                 //it means, keep active the graphics display untill cross(X) button
                                    //is pressed.

}
