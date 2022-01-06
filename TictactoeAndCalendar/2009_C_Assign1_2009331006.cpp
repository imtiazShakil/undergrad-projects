#include <stdio.h>
/*
	Name :   Imtiaz Shakil Siddique
	Roll:         2009331006
	Assignment:   Calendar
	Date of submission  : 03-May-2010
*/

int day_of_month(int yr,int mnth);
int  leap_year(int yr,char ch);
int draw_calender(int space,int month,int year);
int show_all_month(int year);

int main(void)
{
	int year,month,where_day_begins;
	printf("Enter the year followed by month(1-12)  like this  (xxxx  4)\n");
	while( (scanf("%d",&year) == 1)  &&  (scanf("%d",&month)==1))
	{
		if(year>0 && month>=0 && month<13)
		{	//printf("\n%d  %d",year,month);
			where_day_begins = day_of_month(year,month);
			//printf("\n%d\n",where_day_begins);
			draw_calender(where_day_begins,month,year);
		}
		else
		printf("\t\t\t\tINVALID INPUT\n\t\t\t\tTRY USING VALID INPUTS\n");
	}



}

int day_of_month(int yr,int mnth)
{
	int adder,result,prev_leap=0,mon_end,day_new_month;
	prev_leap=leap_year(yr,'l');  //   'l'  is used for looping i.e. which (nth) leap year it actually is//prev_leap  stores how many leap years have been passed
	adder=prev_leap%7;
	result=(yr%7) + adder ;
	result%=7;
	if (mnth>1)
	{
		for(int i=1;i<=mnth-1;i++)
		{
			//printf("\nresult  %d",result);
			switch(i)
			{
				case 1: case 3: case 5:case 7:case 8:case 10:case 12: mon_end=31;
				break;
				case 2:
				{
					if(leap_year(yr,'c')) mon_end=29;
					else mon_end=28;
				}
				break;
				default: mon_end=30;
				break;
			}
			int differe=7-result;
			day_new_month=(mon_end-differe)%7;
			result=day_new_month;
		}
	}
	return result;

}

int  leap_year(int yr,char ch)
{
	if(ch=='l')
	{
		int count=0;
		for(int i=1;i<yr;i++)
		{
			int num1=i%4,num2=i%400,num3=i%100;
			if(!num1 && (!num2  || num3)) ++count;
		}

		return count;                      //count stores the actual number of leap years before the given (yr)  year
	}
	else
	{
		if   ( !(yr%4)  && (!(yr%400)   || yr%100) )  return 1;
		else return 0;

	}
}



int draw_calender(int space,int month,int year)
{
	if (!month)    show_all_month(year);
	else
	{
		int i=0,mon_end,neo_line=space;
		switch(month)
		{
			case 1:case 3:case 5:case 7:case 8:case 10:case 12: mon_end=31;
			break;
			case 2:
			{
				if(leap_year(year,'c')) mon_end=29;
				else mon_end=28;
			}
			break;
			default:mon_end=30;
			break;
		}

		printf("Sun\t\tMon\t\tTue\t\tWed\t\tThurs\t\tFri\t\tSat\n\n");
		while (i<space)
		{
			printf("\t\t");
			i++;
		}

		for(int c=1;c<=mon_end;c++)
		{
			printf("%d\t\t",c);
			if(!((neo_line+1)%7)) printf("\n");             //there are seven spaces  so we mod by 7
			neo_line++;
		}
		printf("\n\n\n");
	}
}

int show_all_month(int year)
{
	int month=1;
	for(int i=1;i<13;i++,month++)
	{
		printf("MONTH  Number  =  %d\n",month);
		int  space=day_of_month(year,i);

		int i=0,mon_end,neo_line=space;
			switch(month)
			{
				case 1:case 3:case 5:case 7:case 8:case 10:case 12: mon_end=31;
				break;
				case 2:
				{
					if(leap_year(year,'c')) mon_end=29;
					else mon_end=28;
				}
				break;
				default:mon_end=30;
				break;
			}

			printf("Sun\t\tMon\t\tTue\t\tWed\t\tThurs\t\tFri\t\tSat\n\n");
			while (i<space)
			{
				printf("\t\t");
				i++;
			}

		for(int c=1;c<=mon_end;c++)
		{
			printf("%d\t\t",c);
			if(!((neo_line+1)%7)) printf("\n");
			neo_line++;

		}

		printf("\n\n");
	}
}
