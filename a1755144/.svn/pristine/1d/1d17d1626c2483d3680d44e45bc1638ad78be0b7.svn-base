#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#define byteLength 8

//access information
typedef struct
{
	char action;
	char *address;
	int pageNum;
}accessInfo;

//frame structure
typedef struct
{
	int pageNum;
	int age;
	bool referenced;
	bool modified;
	bool additionalByte[byteLength];
}page;

//global variables
static int numPages;
static page pageList[128];
static int cursor = -1;
static int interval = 0;
static int intervalCounter = 0;

//counters for output 
static int total = 0;
static int read = 0;
static int write = 0;

//power
double power(double base, double power)
{
	if (power == 0)
	{
		return 1;
	}
	else
	{
		double result = 1;
		for (int i = 0; i < power; ++i)
		{
			result = result * base;
		}
		return result;
	}
}

//print frame information
void printPages()
{
	for (int i = 0; i < numPages; ++i)
	{
		int sum = 0;
		for (int j = 0; j < byteLength; ++j)
		{
			int digit = 0;
			if (pageList[i].additionalByte[j])
			{
				digit = 1;
			}
			sum += digit * power(10, 7 - j);
		}
		printf("%d\t%d\t%d\t%d\t%d\t%d\n", i, pageList[i].pageNum, pageList[i].referenced, pageList[i].modified, sum, pageList[i].age);
	}
	printf("===============\n");
}

//translate hex address to decimal address
double addressToPage(int pageSize, char* address)
{
	double value = 0;
	double digitValue = 0;
	for (int i = 0; i < 8; ++i)
	{
		char digit = address[i];
		switch (digit)
		{
		case 'a':
		case 'A':
			digitValue = 10;
			break;
		case 'b':
		case 'B':
			digitValue = 11;
			break;
		case 'c':
		case 'C':
			digitValue = 12;
			break;
		case 'd':
		case 'D':
			digitValue = 13;
			break;
		case 'e':
		case 'E':
			digitValue = 14;
			break;
		case 'f':
		case 'F':
			digitValue = 15;
			break;
		default:
			digitValue = digit - '0';
			break;
		}
		value = value + digitValue * power(16, 7 - i);
	}
	return value / pageSize;
}

//shift all reference bits
void rightShift()
{
	for (int i = 0; i < numPages; ++i)
	{
		for (int j = 7; j > 0; --j)
		{
			pageList[i].additionalByte[j] = pageList[i].additionalByte[j - 1];
		}
		pageList[i].additionalByte[0] = false;
	}
}

//check if their still empty frame available
int findEmptyPage(int pageNum)
{
	for (int i = 0; i < numPages; ++i)
	{
		if (pageList[i].pageNum == -1)
		{
			return i;
		}
	}
	return -1;
}

//check if the number is in the frame
int findPage(int pageNum)
{
	for (int i = 0; i < numPages; ++i)
	{
		if (pageList[i].pageNum == pageNum)
		{
			return i;
		}
	}
	return -1;
}

//set the frame and related information
void replacePage(int index, accessInfo access)
{
	pageList[index].pageNum = access.pageNum;
	pageList[index].referenced = true;
	pageList[index].additionalByte[0] = true;
	if (access.action == 'W')
	{
		pageList[index].modified = true;
	}
	else
	{
		pageList[index].modified = false;
	}
	pageList[index].age = 0;
}

//sc/esc would read the frame circularly
int circularRead(int index)
{
	if (index >= numPages)
		index -= numPages;
	return index;
}

//compare two frame content if lhs >= rhs return true otherwise return false
bool comparePage(int lhs, int rhs)
{
	//considering reference bits
	for (int i = 0; i < byteLength; ++i)
	{
		if (pageList[lhs].additionalByte[i] && !pageList[rhs].additionalByte[i])
		{
			return true;
		}
		if (!pageList[lhs].additionalByte[i] && pageList[rhs].additionalByte[i])
		{
			return false;
		}
	}
	//considering FIFO
	if (pageList[lhs].age > pageList[rhs].age)
	{
		return false;
	}
	return true;
}

int sc()
{
	cursor = circularRead(++cursor);
	while (pageList[cursor].referenced)
	{
		pageList[cursor].referenced = false;
		cursor = circularRead(++cursor);
	}
	return cursor;
}

int esc()
{
	for (int i = 0; i < numPages; ++i)
	{
		cursor = circularRead(++cursor);
		if (!pageList[cursor].referenced && !pageList[cursor].modified)
			return cursor;
	}

	for (int i = 0; i < numPages; ++i)
	{
		cursor = circularRead(++cursor);
		if (!pageList[cursor].referenced)
			return cursor;
		pageList[cursor].referenced = false;
	}

	for (int i = 0; i < numPages; ++i)
	{
		cursor = circularRead(++cursor);
		if (!pageList[cursor].referenced && !pageList[cursor].modified)
			return cursor;
	}

	return sc();
}

int arb()
{
	int index = 0;
	//fine the smallest page in the frame
	for (int i = 1; i < numPages; ++i)
	{
		if (!comparePage(i, index))
		{
			index = i;
		}
	}
	return index;
}

//calculate the difference between the msb of the two pages
int msbDiff(int lhs, int rhs)
{
	int lhsIndex = 8;
	for (int i = 0; i < byteLength; ++i)
	{
		if (pageList[lhs].additionalByte[i])
		{
			lhsIndex = i;
			break;
		}
	}
	int rhsIndex = 8;
	for (int i = 0; i < byteLength; ++i)
	{
		if (pageList[rhs].additionalByte[i])
		{
			rhsIndex = i;
			break;
		}
	}
	return lhsIndex - rhsIndex;
}

int earb()
{
	//find the smallest page without considering modified bit
	int index = arb();
	//if the found page is modified, find the potential condidate in the unmodified pages
	if (pageList[index].modified)
	{
		//find the smallest unmodified page
		int unmodifiedIndex = -1;
		for (int i = 0; i < numPages; ++i)
		{
			if (!pageList[i].modified)
			{
				unmodifiedIndex = i;
				break;
			}
		}
		if (unmodifiedIndex != -1)
		{
			for (int i = unmodifiedIndex + 1; i < numPages; ++i)
			{
				if (!pageList[i].modified && !comparePage(i, unmodifiedIndex))
				{
					unmodifiedIndex = i;
				}
			}
			//determine who is the victim
			int diff = msbDiff(index, unmodifiedIndex);
			if (diff <= 3)
			{
				index = unmodifiedIndex;
			}
		}
	}
	return index;
}

//handling all the aceessing processes
void access(int algorithm, accessInfo access)
{
	int index = -1;
	index = findPage(access.pageNum);
	//handling a hit condition
	if (index != -1)
	{
		//printf("HIT:    page %d\n", access.pageNum);
		pageList[index].referenced = true;
		pageList[index].additionalByte[0] = true;
		if (access.action == 'W')
		{
			pageList[index].modified = true;
		}
	}
	//shift the reference bits
	if ((algorithm == 3 || algorithm == 4) && intervalCounter == interval)
	{
		rightShift();
		intervalCounter = 0;
	}
	//if hit, end the process
	if (index != -1)
		return;
	++read;

	//printf("MISS:    page %d\n", access.pageNum);
	//find empty frame, if there is one, replace it and end the process
	index = findEmptyPage(access.pageNum);
	if (index != -1)
	{
		cursor = index;
		replacePage(index, access);
		return;
	}
	//find victim by using the designated algorithm
	switch (algorithm)
	{
	case 1:
		index = sc();
		break;
	case 2:
		index = esc();
		break;
	case 3:
		index = arb();
		break;
	case 4:
		index = earb();
		break;
	}
	//printf("REPLACE:    page %d", pageList[index].pageNum);
	//if the victim is modified, a write occurs
	if (pageList[index].modified)
	{
		//printf("(DIRTY)");
		++write;
	}
	//printf("\n");
	cursor = index;
	//initialize the refernce bits
	for (int i = 0; i < byteLength; ++i)
	{
		pageList[index].additionalByte[i] = false;
	}
	replacePage(index, access);
	return;
}

int main(int argc, char *argv[])
{
	//handling the arguments
	char *fileName = argv[1];
	int pageSize = atoi(argv[2]);
	numPages = atoi(argv[3]);
	char *algorithm = argv[4];
	int alg = 0;

	if (strcmp(algorithm, "SC") == 0)
	{
		alg = 1;
	}
	else if (strcmp(algorithm, "ESC") == 0)
	{
		alg = 2;
	}
	else if (strcmp(algorithm, "ARB") == 0)
	{
		interval = atoi(argv[5]);
		alg = 3;
	}
	else if (strcmp(algorithm, "EARB") == 0)
	{
		interval = atoi(argv[5]);
		alg = 4;
	}

	//initialize the frames
	for (int i = 0; i < numPages; ++i)
	{
		bool *additionalBytes = calloc(byteLength, sizeof(bool));
		page p = { -1, false, false, additionalBytes };
		pageList[i] = p;
		for (int j = 0; j < byteLength; ++j)
		{
			pageList[i].additionalByte[j] = false;
		}
		pageList[i].age = 0;
	}

	//read file line by line
	FILE *ptr_file;
	char buf[20];
	ptr_file = fopen(fileName, "r");
	if (!ptr_file)
		return 1;

	int bufLen = 20;
	int addressLen = 8;

	while (fgets(buf, bufLen, ptr_file) != NULL)
	{
		char *content = strtok(buf, " ");
		if (content[0] == 'R' || content[0] == 'W')
		{
			++total;
			//package all the data into the accessInfo structure
			char action;
			char address[9];
			int pageNum;
			action = content[0];
			content = strtok(NULL, " ");
			memcpy(address, content, addressLen);
			address[8] = '\0';
			//interpret the address to the page number
			pageNum = addressToPage(pageSize, address);
			accessInfo info = { action, address, pageNum };
			//increase the interval counter
			if (alg == 3 || alg == 4)
			{
				++intervalCounter;
				for (int i = 0; i < numPages; ++i)
				{
					pageList[i].age++;
				}
			}
			//process the memory access
			access(alg, info);
			//printf("access %d\n", info.pageNum);
			//printPages();
		}
	}
	fclose(ptr_file);
	printf("events in trace:    %d\n", total);
	printf("total disk reads:   %d\n", read);
	printf("total disk writes:  %d\n", write);
	return 0;
}