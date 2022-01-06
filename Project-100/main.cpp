#include <iostream>
#include <queue>

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include<ctype.h>
#include <graphics.h>
#include <conio.h>
#include<windows.h>
#include<MMSystem.h>

#define start_limit 15              ///maze size x*x
#define SZ 50
#define max_wall_len 6             ///it is added with 3 so max is 5+3 =8
#define amount_of_walls 7
#define num_surv 8
#define infinity 1000000
#define difficulty 2

using namespace std;

void wallgen(int strx, int stry, int len, int maxlen);
void draw_world(void);
void initsidescr();
void BFS(int x,int y);
void opening(void);
void introduction(void);

int xx[] = {0, -1, 0, 1, -1, -1, 1, 1}, yy[] = {-1, 0, 1, 0 , -1, 1 ,1 ,-1};


int survr[10][2];     ///the first survivor survr[0] is the start point
int maze_dist[SZ][SZ];
int dist[SZ][SZ],num_moves,var;
char maze[SZ][SZ];

int start_point[2];

bool colour_wall[SZ][SZ];
bool colour_paths[SZ][SZ];
bool no_same_row[SZ];
bool no_same_col[SZ];
bool loc_survivor[SZ][SZ];
bool for_bfs[SZ][SZ];


int nodes;
bool colour_bt[50];
char st_nodes[50];
int min_dist;


void gen_dist(void)
{
    int i,j;
    for(i=1;i<=num_surv;i++)
    {
        BFS(survr[i][0],survr[i][1]);
        for(j=i+1;j<=num_surv;j++)
        {
            dist[i][j]=maze_dist[survr[j][0]][survr[j][1]];
            dist[j][i]=maze_dist[survr[j][0]][survr[j][1]];
//            printf("dist from %d to %d is %d\n",i,j,dist[i][j]);
        }
    }
    printf("distances  are generated successfully\n");
    return ;
}
int get_dist(void)
{
    int i,len=strlen(st_nodes),d=0,p1,p2;

    for(i=0;i<len;i++)
    {
        if(i+1<len )
        {
            p1=st_nodes[i]-'0';p2=st_nodes[i+1]-'0';
//            printf("%d %d dist %d\n",p1,p2,dist[p1][p2]);
            d+=dist[p1][p2];
        }
    }
//    printf("total distance %d\n",d);
    if( min_dist>d ) min_dist=d;
    return 0;
}
void BT(int depth)
{
    int i;
    if(depth==nodes) {/*printf("%s\n",st_nodes);*/get_dist(); return ;}
    for(i=2;i<=nodes;i++)
    {
        if(!colour_bt[i])
        {
            st_nodes[depth]=i+'0';
            colour_bt[i]=true;
            BT(depth+1);
            colour_bt[i]=false;
        }
    }

    return ;
}
void BFS(int x,int y)
{
    memset(for_bfs,0,sizeof for_bfs );
    int row,col,p,q,i,j;

    for(i=0;i<SZ;i++)
        for(j=0;j<SZ;j++)
            maze_dist[i][j]=infinity;

    queue<int>Q;
    Q.push(x);Q.push(y);
    for_bfs[x][y]=true;
    maze_dist[x][y]=0;
    while(!Q.empty())
    {
        row=Q.front();Q.pop();
        col=Q.front();Q.pop();
        for(i=0;i<8;i++)
        {
            p=row+xx[i];q=col+yy[i];
            if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && colour_paths[p][q]==true && for_bfs[p][q]==false)
            {
                for_bfs[p][q]=true;
                Q.push(p);Q.push(q);
                maze_dist[p][q]=maze_dist[row][col]+1;
            }
        }
    }
    return ;
}
void path(void)
{
    memset( colour_paths,0,sizeof colour_paths );


    int row,col,p,q,i;
    queue<int>Q;
    Q.push(1);Q.push(1);
    colour_paths[1][1]=true;maze[1][1]='-';
    while(!Q.empty())
    {
        row=Q.front();Q.pop();
        col=Q.front();Q.pop();
        for(i=0;i<8;i++)
        {
            p=row+xx[i];q=col+yy[i];
            if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && colour_wall[p][q]==false && colour_paths[p][q]==false)
            {
                colour_paths[p][q]=true;
                Q.push(p);Q.push(q);
                maze[p][q]='-';
            }
        }
    }
    printf("path function ended successfully\n");

    return ;
}
void survivors(void)
{
    srand(time(NULL));
    memset(loc_survivor,0,sizeof loc_survivor );
    int val1,val2,pos=0;

    int num_survivor=num_surv ;
    while(num_survivor--)
    {
        val1=rand()%start_limit +1;val2=rand()%start_limit +1;
        while(!colour_paths[val1][val2] || loc_survivor[val1][val2]) {val1=rand()%start_limit +1;val2=rand()%start_limit +1;}

        survr[++pos][0]=val1;survr[pos][1]=val2;
        loc_survivor[val1][val2]=true;
//        printf("surv %d %d\n",val1,val2);
        maze[val1][val2]='+';
    }
    printf("all the survivors are created\n");

    return ;
}

void walls(void)
{
    srand(time(NULL));
    int times=amount_of_walls;

    printf("wall generation started\n");

    memset(maze, '.', sizeof maze);
    memset( colour_wall,0,sizeof colour_wall );
    memset( no_same_row,0,sizeof no_same_row );
    memset( no_same_col,0,sizeof no_same_col );
    while(times--)
    {


//        int strx=0, stry=0;
//        maze[45][45]='*';
        int strx = rand() % start_limit + 1;
        int stry = rand() % start_limit + 1;
        while(no_same_row[strx]==true || no_same_col[stry]==true || colour_wall[strx][stry]==true || strx>=start_limit || stry>=start_limit || strx<=1 || stry<=1)
        {
            strx = rand() % start_limit + 1,stry = rand() % start_limit + 1;
        }
//        printf("strx %d, stry %d\n", strx, stry);

        maze[strx][stry] = '*';
        colour_wall[strx][stry]=true;

        no_same_row[strx]=true;
        no_same_col[stry]=true;

        int len=1, maxlen;
        maxlen = rand()%max_wall_len + 3;
//        printf("maxlen %d\n", maxlen);
        wallgen(strx, stry, len, maxlen);




    }
    printf("walls are generated successfully\n");
    return ;

}
void shortest_path()
{
        nodes=num_surv;
        memset(colour_bt,0,sizeof(bool));
        memset( st_nodes,0,sizeof st_nodes );
        min_dist=infinity;
        st_nodes[0]='1';
        colour_bt[1]=true;
        BT(1);
//        printf("shortest path found -> dist %d\n",min_dist);
}


void wallgen(int strx, int stry, int len, int maxlen)
{
    if(len==maxlen)
        return;
    int x, y,dir=rand()%8;
    x = strx+xx[dir], y = stry+yy[dir];
    int count=0;bool flag=0;
//    while(maze[x][y]=='*' && ((x>=10 || y>=10) || (x<0 || y<0)))
    while(colour_wall[x][y]==true || x>=start_limit || y>=start_limit || x<=1 || y<=1)
    {
        dir = rand()%8;
        x = strx+xx[dir], y = stry+yy[dir];
        count++;
        if(count>20) {/*printf("\nstrx %d Stry %d x= %d y= %d\n",strx,stry,x,y);*/flag=1;break;}
    }
//    printf("x %d, y %d\n", x, y);
    if(flag==0)
    {
        maze[x][y] = '*';
        colour_wall[x][y]=true;
        wallgen(x, y, len+1, maxlen);
    }
    return ;

}

int main()
{
//    char *WAV="sound.wav";
//    sndPlaySound(WAV, SND_ASYNC);

    int i, j,p,q,total_surv;
    char move,str_mov[100],str_rem[100],val;
    initwindow(850,550,"Rescuing the Survivors");
    opening();
    settextstyle(SYSTEM_FONT,HORIZ_DIR,2);

    setbkcolor(GREEN);
    clearviewport();

    initsidescr();
    setviewport(0, 0, 550, 550, 1);
    rectangle(1,1,509,509);

//    setfillstyle(XHATCH_FILL, RED);
//    bar(1,1,100,100);
    while(1)
    {
        setbkcolor(WHITE);
        clearviewport();
        setcolor(BLACK);
        outtextxy(150,150,"Press 'Y' to continue and 'N' to exit");

        num_moves=0;
        walls();                  ///makes problem sometimes but not all times
        path();                  ///100% ok <verified>
        survivors();            ///100% ok <certified>

        printf("MAZE:\n\n");
        for(i=1;i<=start_limit;i++)
        {
            for(j=1;j<=start_limit;j++)  printf("%c", maze[i][j]);

            printf("\n");
        }
        gen_dist();             ///100% ok <No Problemo>
        shortest_path();        ///100% ok <AWSM>

        start_point[0]=survr[1][0];
        start_point[1]=survr[1][1];
        maze[start_point[0]][start_point[1]]='-';


        move=getch();
        if(move=='n') break;
        clearviewport();
        setcolor(BLACK);
        rectangle(1,1,509,509);
        draw_world();
        total_surv=num_surv-1;
        bool flag=false;
        int rr = (rand()%difficulty)+1;
        var=min_dist+rr;
        sprintf(str_rem,"You have %d moves to collect all the survivors\n",var);
        setcolor(BLACK);
        outtextxy(100,510,str_rem);
        while(1)
        {
            move=getch();

            if(move=='\r') break;
            if(var<=num_moves) {flag=true;break;}
            if(move=='w')
            {
                p=start_point[0]-1;
                q=start_point[1];

                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[1]=start_point[1] ;

            }
            else if(move=='s')
            {
                p=start_point[0]+1;
                q=start_point[1];
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
            }
            else if(move=='a')
            {

                p=start_point[0];
                q=start_point[1]-1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[1]=start_point[1]-1;
            }
            else if(move=='d')
            {
                p=start_point[0];
                q=start_point[1] + 1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[1]=start_point[1]+1;
            }

            else if(move=='q')
            {
                p=start_point[0]-1;
                q=start_point[1]-1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[0]=start_point[0]-1;
//                start_point[1]=start_point[1]-1;
            }
            else if(move=='e')
            {
                p=start_point[0]-1;
                q=start_point[1]+1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[0]=start_point[0]-1;
//                start_point[1]=start_point[1]+1;
            }
            else if(move=='z')
            {
                p=start_point[0]+1;
                q=start_point[1]-1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[0]=start_point[0]+1;
//                start_point[1]=start_point[1]-1;
            }
            else if(move=='c')
            {
                p=start_point[0]+1;
                q=start_point[1]+1;
                if(p>=1 && q>=1 && p<=start_limit && q<=start_limit && maze[p][q]!='*' )
                {
                    start_point[0]=p;
                    start_point[1]=q;
                    if(maze[p][q]=='+') {maze[p][q]='-';total_surv--;}
                    draw_world();
                }
//                start_point[0]=start_point[0]+1;
//                start_point[1]=start_point[1]+1;
            }
            if( !total_surv ) break;

            sprintf(str_rem,"You have %4d moves to collect all the survivors\n",var);
            outtextxy(100,510,str_rem);
        }

//        sprintf(str_mov,"number of moves took %d\n",num_moves);
//        outtextxy(200,500,str_mov);
        if(flag)
        {
            setcolor(RED);
            outtextxy(200,530,"You have been defeated");
        }
        else
        {
            setcolor(GREEN);
            outtextxy(200,530,"You have succeeded");
        }
//        printf("\nnumber of moves %d\n",num_moves);

        val=getch();
        if(val=='n') break;
//        gen_dist();             ///100% ok <No Problemo>
//        shortest_path();        ///100% ok <AWSM>

    }

}
void draw_world(void)
{
    var--;
    int i,j;
    for(i=1;i<=start_limit;i++)
        {
            for(j=1;j<=start_limit;j++)
            {
                if((i==start_point[0]) && (j==start_point[1]))
                {
                    setfillstyle(7, 14);
                    bar(j*30,i*30,j*30 + 25,i*30 +25);
                    continue;
                }
                if(maze[i][j]=='-')
                {
                    setfillstyle(1, GREEN);
                    bar(j*30,i*30,j*30 + 25,i*30 +25);
                }
                else if(maze[i][j]=='*')
                {
                    setfillstyle(9, RED);
                    bar(j*30,i*30,j*30 + 25,i*30 +25);

                }
                else if(maze[i][j]=='+')
                {
                    setfillstyle(1, 11);
                    bar(j*30,i*30,j*30 + 25,i*30 +25);
                }
                else if(maze[i][j]=='.')
                {
                    setfillstyle(9, RED);
                    bar(j*30,i*30,j*30 + 25,i*30 +25);
                }

            }
        }
}

void initsidescr()
{
    //For player icon
    setfillstyle(7, 14);
    bar(600, 50, 625, 75);
    setcolor(YELLOW);
    rectangle(597,47,627,78);
    setbkcolor(RED);
    setcolor(WHITE);
    outtextxy(632,52,"-> This is you!");
    setbkcolor(GREEN);
    //For wall icon
    setfillstyle(9, RED);
    bar(600,90,625,115);
    setcolor(RED);
    setbkcolor(RED);
    rectangle(597, 87, 627, 118);
    setcolor(WHITE);
    outtextxy(632, 92, "-> This is a wall!");

    //For survivor icon
    setfillstyle(1, 11);
    bar(600,130,625,155);
    setcolor(11);
    rectangle(597, 127, 627, 158);
    setcolor(WHITE);
    outtextxy(632, 132, "-> This is a survivor!");

    //Control
    //UP
    outtextxy(580, 170, "For");
    setcolor(RED);
    line(610, 170, 610, 185);
    line(610, 170, 606, 178);
    line(610, 170, 614, 178);
    setcolor(WHITE);
    outtextxy(620, 170, "Press 'W'");

    //DOWN
    outtextxy(580, 200, "For");
    setcolor(RED);
    line(610, 200, 610, 215);
    line(610, 215, 606, 208);
    line(610, 215, 614, 208);
    setcolor(WHITE);
    outtextxy(620, 200, "Press 'S'");

    //LEFT
    outtextxy(580, 230, "For");
    setcolor(RED);
    line(602, 240, 615, 240);
    line(602, 240, 610, 237);
    line(602, 240, 610, 243);
    setcolor(WHITE);
    outtextxy(620, 230, "Press 'A'");

    //RIGHT
    outtextxy(580, 260, "For");
    setcolor(RED);
    line(602, 270, 615, 270);
    line(615, 270, 607, 267);
    line(615, 270, 607, 273);
    setcolor(WHITE);
    outtextxy(620, 260, "Press 'D'");

    //UP-LEFT
    outtextxy(580, 290, "For");
    setcolor(RED);
    line(605, 292, 616, 300);
    line(605, 292, 607, 301);
    line(605, 292, 614, 292);
    setcolor(WHITE);
    outtextxy(620, 290, "Press 'Q'");

    //DOWN-RIGHT
    outtextxy(580, 320, "For");
    setcolor(RED);
    line(605, 322, 616, 330);
    line(616, 330, 607, 330);
    line(616, 330, 614, 322);
    setcolor(WHITE);
    outtextxy(620, 320, "Press 'C'");

    //UP-RIGHT
    outtextxy(580, 350, "For");
    setcolor(RED);
    line(616, 352, 605, 360);
    line(616, 352, 607, 353);
    line(616, 352, 614, 360);
    setcolor(WHITE);
    outtextxy(620, 350, "Press 'E'");

    //DOWN-LEFT
    outtextxy(580, 380, "For");
    setcolor(RED);
    line(616, 382, 605, 390);
    line(605, 390, 607, 383);
    line(605, 390, 612, 390);
    setcolor(WHITE);
    outtextxy(620, 380, "Press 'Z'");
}
void opening(void)
{
    clearviewport();
    setcolor(GREEN);
    settextstyle(4,HORIZ_DIR,4);
    setbkcolor(WHITE);
    setcolor(BLACK);
    clearviewport();
    int i,j;
    char str[100]="Gaming Project";
    char str2[100]="2011";
    char str3[]="Game Created By";
    char str4[]="Nafis & Shakil";

    for(i=10,j=100;i<270;i+=10)
    {
        setcolor(rand()%15);
        setbkcolor(rand()%15);
//        clearviewport();
        outtextxy(i,j,str);
        delay(70);
    }
    for(i=840,j=200;i>350;i-=10)
    {
        setcolor(rand()%15);
        setbkcolor(rand()%15);
//        clearviewport();
        outtextxy(i,j,str2);
        delay(70);
    }
    setbkcolor(WHITE);
    setcolor(BLACK);
    settextstyle(5,HORIZ_DIR,6);
    outtextxy(180,300,str3);
    outtextxy(150,380,str4);
    getch();
    introduction();
}
void introduction(void)
{
    setbkcolor(7);
    clearviewport();
    setcolor(4);

    settextstyle(10,HORIZ_DIR,2);
    char str[]="A plane has crashed in the sundarbans &";
    char str2[]="the survivors are scattered throughout";
    char str3[]="the forest. You, the rescue squad's captain,";
    char str4[]="have to rescue them all with your";
    char str5[]="limited amount of fuel.";
    char str6[]="[All the levels are generated randomly";
    char str7[]="So you'll never encounter a level twice]";

    outtextxy(50,50,str);
    outtextxy(50,100,str2);
    outtextxy(50,150,str3);
    outtextxy(50,200,str4);
    outtextxy(50,250,str5);
    outtextxy(50,350,str6);
    outtextxy(50,400,str7);

    getch();
//    outtextxy(50,50,str);
//    char str8[]="";
    return ;
}
//settextstyle(SYSTEM_FONT,HORIZ_DIR,2);


