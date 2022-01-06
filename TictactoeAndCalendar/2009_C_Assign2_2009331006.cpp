/*
Name:Imtiaz Shakil Siddique
Roll:2009331006
Assignment:Tic-Tac-Toe
Date of Submission: 6-May-2010
*/
#include <stdio.h>
#include <conio.h>

char ch[10],user='X',comp='O',blank=' ';
int blank_space=0;
void decession(void);
void draw_board(void);
void win_lose(void);

int main(void)
{
	char che='y';
	int choice=1;
	while(che!='n')
	{
		blank_space=0;
		for(int i=1;i<=9;i++) ch[i]=blank;
		draw_board();
		printf("\n\t\tThe Number increments from left box to right box\n\t\tPlease dont type anything other than numbers");
		while(blank_space<9)
		{
			printf("\n\t\tEnter ur choice(1 to 9)  =    ");
			scanf("%d",&choice);
			if(ch[choice]==blank)
			{
				ch[choice]=user;
				blank_space++;
				decession();
				draw_board();
				win_lose();
			}
			else printf("\n\t\t\tThat Slot is ALREADY OCCPIED");
		}
		che=getche();
		printf("\n\n");
	}
}

void draw_board(void)
{
	int p=1;
	printf("_____________\n");
	for(int i=1;i<=3;i++)
	{
		for(int c=1;c<=3;c++)
		{
			for(int k=1;k<=13;k++)
			{
				if(k==1 || k==5 ||k==9 ||k==13  )
				{
					printf("|");
					continue;
				}
				if(c==2 && (k==3 || k==7 ||k==11 ))
				{
					printf("%c",ch[p]);
					p++;
					continue;
				}
				if (c==3) printf("_");
				else printf(" ");
			}
			printf("\n");
		}
	}
}

void decession(void)
{
	if (blank_space==1)
	{
		for (int i=1;i<=9;i++)
		{
			if(ch[i]==user)
			{
				switch(i)
				{
					case 1:case 2:case 3:case 4:case 6:case 7:case 8:case 9: ch[5]=comp;
					blank_space++;
					return;
					case 5: ch[1]=comp;
					blank_space++;
					return;
				}
			}
		}
	}
	for(int i=1;i<=9;i++)
	{
		if(ch[i]==blank)
		{
			switch(i)
			{
				case 1:if(  ( (ch[4]==comp && ch[7]==comp)  ||(ch[2]==comp && ch[3]==comp) || (ch[5]==comp && ch[9]==comp)) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return;}
				break;
				case 2:if(  ( (ch[1]==comp && ch[3]==comp)  ||  (ch[5]==comp && ch[8]==comp) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return;}
				break;
				case 3:if(  ( (ch[1]==comp && ch[2]==comp)  ||(ch[6]==comp && ch[9]==comp) || (ch[7]==comp && ch[5]==comp) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 4:if(  ( (ch[1]==comp && ch[7]==comp)  ||  (ch[5]==comp && ch[6]==comp) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 5:if(  ( (ch[1]==comp && ch[9]==comp)  ||(ch[3]==comp && ch[7]==comp) || (ch[2]==comp && ch[8]==comp) || (ch[4]==comp && ch[6]==comp)) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 6:if(  ( (ch[4]==comp && ch[5]==comp)  ||  (ch[3]==comp && ch[9]==comp) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 7:if(  ( (ch[1]==comp && ch[4]==comp)  ||(ch[8]==comp && ch[9]==comp) || (ch[3]==comp && ch[5]==comp) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 8:if(  ( (ch[7]==comp && ch[9]==comp)  ||  (ch[2]==comp && ch[5]==comp) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return;  }
				break;
				case 9:if(  ( (ch[7]==comp && ch[8]==comp)  ||(ch[3]==comp && ch[6]==comp) || (ch[1]==comp && ch[5]==comp) && ch[i]==blank) ) { ch[i]=comp;
				blank_space++;
				return;}
				break;
			}
		}
	}


	for(int i=1;i<=9;i++)
	{
		if(ch[i]==blank)
		{
			switch(i)
			{
				case 1:if(  ( (ch[4]==user && ch[7]==user)  ||(ch[2]==user && ch[3]==user) || (ch[5]==user && ch[9]==user)) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 2:if(  ( (ch[1]==user && ch[3]==user)  ||  (ch[5]==user && ch[8]==user) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 3:if(  ( (ch[1]==user && ch[2]==user)  ||(ch[6]==user && ch[9]==user) || (ch[7]==user && ch[5]==user) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 4:if(  ( (ch[1]==user && ch[7]==user)  ||  (ch[5]==user && ch[6]==user) ) && ch[i]==blank  ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 5:if(  ( (ch[1]==user && ch[9]==user)  ||(ch[3]==user && ch[7]==user) || (ch[2]==user && ch[8]==user) || (ch[4]==user && ch[6]==user)) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 6:if(  ( (ch[4]==user && ch[5]==user)  ||  (ch[3]==user && ch[9]==user) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 7:if(  ( (ch[1]==user && ch[4]==user)  ||(ch[8]==user && ch[9]==user) || (ch[3]==user && ch[5]==user) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 8:if(  ( (ch[7]==user && ch[9]==user)  ||  (ch[2]==user && ch[5]==user) ) && ch[i]==blank ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 9:if(  ( (ch[7]==user && ch[8]==user)  ||(ch[3]==user && ch[6]==user) || (ch[1]==user && ch[5]==user) && ch[i]==blank) ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
			}
		}
	}
	for(int i=1;i<=9;i++)
	{
		if(ch[i]==blank)
		{
			switch(i)
			{
				case 1:if  ( (ch[4]==(blank||comp) && ch[7]==(blank||comp) )  ||(ch[2]==(blank||comp) && ch[3]==(blank||comp)) || (ch[5]==(blank||comp) && ch[9]==(blank||comp)))   { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 2:if  ( ( ch[1]==(blank||comp) && ch[3]==(blank||comp) )  ||  (ch[5]==(blank||comp) && ch[8]==(blank||comp) ) )   { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 3:if  ( (ch[1]==(blank||comp) && ch[2]==(blank||comp))  ||(ch[6]==(blank||comp) && ch[9]==(blank||comp)) || (ch[7]==(blank||comp) && ch[5]==(blank||comp)) )  { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 4:if  ( (ch[1]==(blank||comp) && ch[7]==(blank||comp))  ||  (ch[5]==(blank||comp) && ch[6]==(blank||comp)) ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 5:if  ( (ch[1]==(blank||comp) && ch[9]==(blank||comp))  ||(ch[3]==(blank||comp) && ch[7]==(blank||comp)) || (ch[2]==(blank||comp) && ch[8]==(blank||comp)) || (ch[4]==(blank||comp) && ch[6]==(blank||comp)))   { ch[i]=comp;
				blank_space++;
				return;  }
				break;
				case 6:if  ( (ch[4]==(blank||comp) && ch[5]==(blank||comp))  ||  (ch[3]==(blank||comp) && ch[9]==(blank||comp)) )  { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 7:if  ( (ch[1]==(blank||comp) && ch[4]==(blank||comp))  ||(ch[8]==(blank||comp) && ch[9]==(blank||comp)) || (ch[3]==(blank||comp) && ch[5]==(blank||comp)) ) { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 8:if  ( (ch[7]==(blank||comp) && ch[9]==(blank||comp))  ||  (ch[2]==(blank||comp) && ch[5]==(blank||comp)) )  { ch[i]=comp;
				blank_space++;
				return; }
				break;
				case 9:if  ( (ch[7]==(blank||comp) && ch[8]==(blank||comp))  ||(ch[3]==(blank||comp) && ch[6]==(blank||comp)) || (ch[1]==(blank||comp) && ch[5]==(blank||comp)) )  {ch[i]=comp;
				blank_space++;
				return; }
				break;
				}
		}
	}

	for(int i=1;i<=9;i++)
	{
		if(ch[i]==blank)
		{
			ch[i]=comp;
			blank_space++;
			return;
		}
	}

}

void win_lose(void)
{
	char che=comp;

	if   ( ( (ch[1]==che && ch[2]==che && ch[3]==che) ||(ch[4]==che && ch[5]==che && ch[6]==che) ||(ch[7]==che && ch[8]==che && ch[9]==che) ) || (  (ch[1]==che && ch[4]==che && ch[7]==che) ||(ch[2]==che && ch[5]==che && ch[8]==che) ||(ch[3]==che && ch[6]==che && ch[9]==che) ) ||  ( (ch[1]==che && ch[5]==che && ch[9]==che) ||(ch[3]==che && ch[5]==che && ch[7]==che)) )
	{
		 printf("\n\n\t\t\t\aTHE COMPUTER HAS WON THIS MATCH\n\n\t\t\tDo you want to try again(y/n)?\n");
		 blank_space=9;
	}
	else if(blank_space==9)
	{
		printf("\n\n\t\t\t\aIT IS A DRAW\n\n\t\t\tWant another time(y/n)?\n");
	}
}
