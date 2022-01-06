/*
ID: shakil1
PROG: transform
LANG: C++
*/

#include <map>
#include <queue>
#include <stack>
#include <cmath>
#include <cctype>
#include <set>
#include <bitset>
#include <algorithm>
#include <list>
#include <vector>
#include <sstream>
#include <iostream>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>


#define print1(a) cout<<a<<endl
#define print2(a,b) cout<<a<<" "<<b<<endl
#define print3(a,b,c) cout<<a<<" "<<b<<" "<<endl
#define PI (2*acos(0))
#define ERR 1e-5
#define ll long long
#define VI vector<int>
#define mem(a,b) memset(a,b,sizeof a)
#define pb push_back
#define popb pop_back
#define all(x) (x).begin(),(x).end()
#define mp make_pair
#define MAX 60
#define SZ(x) (int)x.size()
#define paii pair<int,int>
#define oo (1<<25)

///ll rrr[]={1,0,-1,0};ll ccc[]={0,1,0,-1}; //4 Direction
///int rrr[]={1,1,0,-1,-1,-1,0,1};int ccc[]={0,1,1,1,0,-1,-1,-1};//8 direction
///int rrr[]={2,1,-1,-2,-2,-1,1,2};int ccc[]={1,2,2,1,-1,-2,-2,-1};//Knight Direction
///int rrr[]={2,1,-1,-2,-1,1};int ccc[]={0,1,1,0,-1,-1}; //Hexagonal Direction
///ll month[]={31,28,31,30,31,30,31,31,30,31,30,31}; //month

using namespace std;

vector<string>inp;
vector<string>out;
vector<string>v;

bool mtch(vector<string> a ,vector<string> b)
{
    for(int i=0;i<SZ(a);i++)
        for(int j=0;j<SZ(a[i]);j++)
            if(a[i][j]!=b[i][j]) return false;

            return true;
}
void trans90(vector<string>in1 )
{
    v.clear();
    string str;str.clear();
    for(int i=0;i<SZ(in1);i++)
    {
        str.clear();
        for(int j=SZ(in1)-1;j>=0;j--)   str+=in1[j][i];

        v.pb(str);
    }
    return ;
}

void reflection(vector<string>in2)
{
    v.clear();
    string str;str.clear();
    for(int i=0;i<SZ(in2);i++)
    {
        str.clear();
        for(int j=SZ(in2[i])-1;j>=0;j--) str+=in2[i][j];

        v.pb(str);
    }
    return ;
}
int main(void)
{
    freopen("transform.in","r",stdin);
    freopen("transform.out","w+",stdout);

    vector<string>tmp,ref;
    int n,t;string str;
    inp.clear();out.clear();
    scanf("%d",&n);t=n;
    while(n--)
    {
        cin>>str;inp.pb(str);
    }
    while(t--)
    {
        cin>>str;out.pb(str);
    }



    trans90(inp);
    if(mtch(out,v)) {print1(1);return 0;}
    tmp.clear();tmp=v;

    trans90(tmp);
    if(mtch(out,v)) {print1(2);return 0;}
    tmp.clear();tmp=v;


    trans90(tmp);
    if(mtch(out,v)) {print1(3);return 0;}

    reflection(inp);
    if(mtch(out,v)) {print1(4);return 0;}
    ref.clear();ref=v;

    trans90(ref);
    if(mtch(out,v)) {print1(5);return 0;}
    tmp.clear();tmp=v;

    trans90(tmp);
    if(mtch(out,v)) {print1(5);return 0;}
    tmp.clear();tmp=v;

    trans90(tmp);
    if(mtch(out,v)) {print1(5);return 0;}

    if(mtch(inp,out)) {print1(6);return 0;}

    print1(7);


    return 0;
}
