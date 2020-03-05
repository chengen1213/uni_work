#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#define queueLength 100

typedef struct Service
{
	char* id;
	int arrival;
	int priority;
	//q2,q3 7 -> priority++
	int age;
	//for calculating the remaining time
	int cpu_time;
	//const, original cpu time
	int oCpu_time;
	int enterTime;
	int timeUnitUsed;
	//q1 5 rounds priority--
	//q2 2 rounds priority--
	int round;
	//end - ready - cpu time
	int waiting;
	int ready;
	int end;
	bool aging;
	bool ageReset;
	struct Service *next;
}Service;

typedef struct queue
{
	int counter;
	int head;
	int tail;
	Service sq[queueLength];
}queue;

int circular(int number)
{
	if (number >= queueLength)
	{
		return number % queueLength;
	}
	else if (number < 0)
	{
		return (number + queueLength);
	}
	else
	{
		return number;
	}
}

void enTimeQ(Service service, queue *tq)
{
	int counter = tq->counter;

	if (counter > 0)
	{
		int index = tq->tail;
		while (counter > 0 && tq->sq[index].arrival > service.arrival)
		{
			tq->sq[index + 1] = tq->sq[index];
			--counter;
			index = --index;
			index = circular(index);
		}
		index += 1;
		index = circular(index);
		tq->counter++;
		tq->tail++;
		tq->tail = circular(tq->tail);
		tq->sq[index] = service;
	}
	else
	{
		tq->counter = 1;
		tq->head = 0;
		tq->tail = 0;
		tq->sq[0] = service;
	}
}

int reaDFile(char *fileName, queue* timeQ)
{
	FILE *ptr_file;
	char buf[20];
	ptr_file = fopen(fileName, "r");
	if (!ptr_file)
		return 1;

	int bufLen = 20;
	int idLen = 10;
	while (fgets(buf, bufLen, ptr_file) != NULL)
	{
		char *id = NULL;
		id = calloc(idLen, sizeof(char));
		int datum[4] = { 0,0,0,0 };
		char *content = strtok(buf, " ");
		int k = -1;
		do {
			if (k == -1)
				memcpy(id, content, idLen);
			else
				datum[k] = atoi(content);
			++k;
		} while (content = strtok(NULL, " "));
		struct Service customer = { id, datum[0], datum[1], datum[2], datum[3],datum[3], 0, 0, 0, 0, -1, -1, false,false, NULL };
		enTimeQ(customer, timeQ);
	}
	fclose(ptr_file);
	return 0;
}

typedef struct linkedList
{
	int counter;
	Service *head;
	Service *tail;
}linkedList;

void addHead(int time, linkedList *queue, Service *customer)
{
	customer->next = NULL;
	customer->enterTime = time;
	if (queue->counter == 0)
	{
		queue->head = customer;
		queue->tail = customer;
	}
	else
	{
		customer->next = queue->head;
		queue->head = customer;
	}
	queue->counter++;
}

void addTail(int time, linkedList *queue, Service *customer)
{
	if (customer == NULL)
	{
		return;
	}
	customer->next = NULL;
	customer->enterTime = time;
	if (queue->counter == 0)
	{
		queue->head = customer;
		queue->tail = customer;
	}
	else
	{
		queue->tail->next = customer;
		queue->tail = customer;
	}
	queue->counter++;
}

bool q1Compare(Service *lhs, Service *rhs) {
	if (lhs->timeUnitUsed > 0)
	{
		return true;
	}
	if (lhs->priority < rhs->priority)
	{
		return false;
	}
	if (lhs->enterTime > rhs->enterTime)
	{
		return false;
	}
	return true;
}

void insertByPriority(int time, linkedList *queue, Service *customer)
{
	customer->next = NULL;
	customer->enterTime = time;
	if (queue->counter == 0)
	{
		queue->head = customer;
		queue->tail = customer;
	}
	else
	{
		Service *prev = NULL;
		Service *current = queue->head;
		while (current != NULL && q1Compare(current, customer))
		{
			prev = current;
			current = current->next;
		}
		if (prev == NULL)
		{
			customer->next = current;
			queue->head = customer;
		}
		else if (current == NULL)
		{
			prev->next = customer;
			queue->tail = customer;
		}
		else
		{
			customer->next = current;
			prev->next = customer;
		}
	}
	queue->counter++;
}

Service* dequeue(linkedList *queue)
{
	if (queue->counter == 0)
	{
		return NULL;
	}
	Service *out = queue->head;

	queue->head = queue->head->next;
	if (queue->head == NULL)
	{
		queue->tail = NULL;
	}
	queue->counter--;
	return out;
}

void enqueue(int time, linkedList *q1, linkedList *q2, linkedList *q3, Service *customer)
{
	customer->next = NULL;
	if (customer->priority > 4)
	{
		insertByPriority(time, q1, customer);
	}
	else if (customer->priority > 2)
	{
		addTail(time, q2, customer);
	}
	else
	{
		addTail(time, q3, customer);
	}
}

void printQ(linkedList *queue)
{
	printf("Index\tArrival\tPriority\tage\tCPU_Time\n");
	Service *current = queue->head;
	while (current != NULL)
	{
		printf("%s\t", current->id);
		printf("%d\t", current->arrival);
		printf("%d\t", current->priority);
		printf("%d\t", current->age);
		printf("%d\n", current->oCpu_time);
		current = current->next;
	}
}

void outputFile(linkedList *queue)
{
	//printf("They are finished processes:\n");
	printf("Index\tPriority\tArrival\tEnd\tReady\tCPU_Time\tWaiting\n");
	//FILE *file = fopen("output.txt", "a+"); // apend file or create a file if it does not exist
	//fprintf(file, "Index\tPriority\tArrival\tEnd\tReady\tCPU_Time\tWaiting\n");
	Service *current = queue->head;
	while (current != NULL)
	{
		printf("%s\t", current->id);
		printf("%d\t", current->priority);
		printf("%d\t", current->arrival);
		printf("%d\t", current->end);
		printf("%d\t", current->ready);
		printf("%d\t", current->oCpu_time);
		printf("%d\n", current->waiting);
		//fprintf(file, "%s\t", current->id);
		//fprintf(file, "%d\t", current->priority);
		//fprintf(file, "%d\t", current->arrival);
		//fprintf(file, "%d\t", current->age);
		//fprintf(file, "%d\t", current->end);
		//fprintf(file, "%d\t", current->ready);
		//fprintf(file, "%d\t", current->oCpu_time);
		//fprintf(file, "%d\n", current->waiting);
		current = current->next;
	}
	//fclose(file); // close file
}

void aging(linkedList *q)
{
	Service *current = q->head;
	while (current != NULL)
	{
		if (current->ageReset) {
			current->age = 0;
			current->ageReset = false;
		}
		if (current->aging)
		{
			current->age++;
			current->aging = false;
		}
		if (current->age > 7)
		{
			current->age = 0;
			current->priority++;
		}
		current = current->next;
	}
}

void organise(int time, linkedList *q1, linkedList *q2, linkedList *q3, linkedList *q4)
{
	//aging
	aging(q1);
	aging(q2);
	aging(q3);

	bool agingFlag = false;
	//q1
	if (q1->counter > 0) {
		Service *head = q1->head;
		if (head->cpu_time == 0)
		{
			Service *result = dequeue(q1);
			result->end = time;
			result->waiting = time - result->oCpu_time - result->ready;
			addTail(time, q4, result);
			//finish to be put in the output queue
		}
		else if (head->timeUnitUsed == 5)
		{
			//to the end
			head->timeUnitUsed = 0;
			head->round++;
			if (head->round == 5)
			{
				head->round = 0;
				head->priority--;
			}
			Service *requeue = dequeue(q1);
			enqueue(time, q1, q2, q3, requeue);
		}
	}
	//q2
	if (q2->counter > 0) {
		Service *current = q2->head;
		if (current->cpu_time == 0)
		{
			Service *result = dequeue(q2);
			result->end = time;
			result->waiting = time - result->oCpu_time - result->ready;
			addTail(time, q4, result);
			//finish to be put in the output queue
		}
		else if (current->timeUnitUsed == 10)
		{
			//to the end
			current->timeUnitUsed = 0;
			current->round++;
			if (current->round == 2)
			{
				current->round = 0;
				current->priority--;
			}
			Service *requeue = dequeue(q2);
			enqueue(time, q1, q2, q3, requeue);
		}
		else if (current->timeUnitUsed > 0 && q1->counter > 0)
		{
			//preemtion
			if (q2->counter > 1)
			{
				Service *current = q2->head->next;
				while (current != NULL)
				{
					current->age++;
					current = current->next;
				}
			}
			if (q3->counter > 0)
			{
				Service *current = q3->head;
				while (current != NULL)
				{
					current->age++;
					current = current->next;
				}
			}
			Service *requeue = dequeue(q2);
			enqueue(time, q1, q2, q3, requeue);
		}
		//promotion
		current = q2->head;
		Service *prev = NULL;
		while (current != NULL)
		{
			bool delFirst = false;
			Service *temp = current->next;
			if (current->priority > 4)
			{
				if (prev == NULL)
				{
					delFirst = true;
					q2->head = current->next;
				}
				else
				{
					prev->next = current->next;
				}
				if (current->next == NULL)
				{
					if (prev == NULL)
					{
						q2->tail = NULL;
					}
					else
					{
						q2->tail = prev;
					}
				}
				q2->counter--;
				enqueue(time, q1, q2, q3, current);
			}
			if (!delFirst)
				prev = current;
			current = temp;
		}
	}
	//q3
	if (q3->counter > 0) {
		Service *current = q3->head;
		if (current->cpu_time == 0)
		{
			Service *result = dequeue(q3);
			result->end = time;
			result->waiting = time - result->oCpu_time - result->ready;
			addTail(time, q4, result);
			//finish to be put in the output queue
		}
		else if (current->timeUnitUsed == 20)
		{
			//to the end
			current->timeUnitUsed = 0;
			Service *requeue = dequeue(q3);
			enqueue(time, q1, q2, q3, requeue);
		}
		else if (current->timeUnitUsed > 0 && (q1->counter > 0 || q2->counter > 0))
		{
			//preemption
			if (q3->counter > 1)
			{
				Service *current = q3->head->next;
				while (current != NULL)
				{
					current->age++;
					current = current->next;
				}
			}
			Service *requeue = dequeue(q3);
			enqueue(time, q1, q2, q3, requeue);
		}
		//promotion
		current = q3->head;
		Service *prev = NULL;
		while (current != NULL)
		{
			bool delFirst = false;
			Service *temp = current->next;
			if (current->priority > 2)
			{
				if (prev == NULL)
				{
					delFirst = true;
					q3->head = current->next;
				}
				else
				{
					prev->next = current->next;
				}
				if (current->next == NULL)
				{
					if (prev == NULL)
					{
						q3->tail = NULL;
					}
					else
					{
						q3->tail = prev;
					}
				}
				q3->counter--;
				enqueue(time, q1, q2, q3, current);
			}
			if (!delFirst)
				prev = current;
			current = temp;
		}
	}
}

bool processQ1(int time, int agingCounter, linkedList *q1)
{
	Service* current = q1->head;
	if (current->ready == -1) {
		current->ready = time;
	}
	current->cpu_time--;
	current->timeUnitUsed++;
	if (agingCounter == 5 || current->cpu_time == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool processQ23(int time, int turn, int agingCounter, bool agingFlag, linkedList *q)
{
	int agingCircle = -1;

	if (turn == 1)
		agingCircle = 5;
	else if (turn == 2)
		agingCircle = 10;
	else if (turn == 3)
		agingCircle = 20;

	bool aging = agingFlag;

	Service *current = q->head;
	if (turn == 2 || turn == 3) {
		while (current != NULL)
		{
			//first element decide whether to add age or not
			if (current == q->head)
			{
				if (current->ready == -1) {
					current->ready = time;
				}
				current->ageReset = true;
				current->cpu_time--;
				current->timeUnitUsed++;
				if (agingCounter == agingCircle || current->cpu_time == 0)
				{
					aging = true;
				}
			}
			else
			{
				if (aging) {
					current->aging = true;
				}
			}
			current = current->next;
		}
	}
	else
	{
		if (aging) {
			while (current != NULL)
			{
				current->aging = true;
				current = current->next;
			}
		}
	}
	return aging;
}

bool process(int time, int agingCounter, linkedList *q1, linkedList *q2, linkedList *q3)
{
	bool agingFlag = false;
	if (q1->counter > 0)
	{
		agingFlag = processQ1(time, agingCounter, q1);
		processQ23(time, 0, agingCounter, agingFlag, q2);
		processQ23(time, 0, agingCounter, agingFlag, q3);
	}
	else if (q2->counter > 0)
	{
		agingFlag = processQ23(time, 2, agingCounter, agingFlag, q2);
		processQ23(time, 0, agingCounter, agingFlag, q3);
	}
	else if (q3->counter > 0)
	{
		agingFlag = processQ23(time, 3, agingCounter, agingFlag, q3);
	}
	return agingFlag;
}

int main(int argc, char *argv[])
{
	char *fileName = argv[1];
	queue timeQ;
	//read data from the input file and sort them by time in an array
	int status = reaDFile(fileName, &timeQ);
	if (status) {
		return 1;
	}
	//initialise the queues
	linkedList q1 = { 0,NULL,NULL };
	linkedList q2 = { 0,NULL,NULL };
	linkedList q3 = { 0,NULL,NULL };
	linkedList resultQ = { 0,NULL,NULL };

	int time = 0;
	int agingCounter = 0;
	//keep operating until all the processes are done
	while (timeQ.counter > 0 || q1.counter > 0 || q2.counter > 0 || q3.counter > 0)
	{
		int index = timeQ.head;
		//when arrival == time, push the service into one of the three queues
		while (timeQ.sq[index].arrival == time && timeQ.counter > 0)
		{
			timeQ.counter--;
			timeQ.head++;
			timeQ.head = circular(timeQ.head);
			enqueue(time, &q1, &q2, &q3, &timeQ.sq[index]);
			++index;
		}
		//organise the three queues: to the end, promotion, demotion, preemtion
		organise(time, &q1, &q2, &q3, &resultQ);
		bool aging = false;
		agingCounter++;
		//calculate the rest of the cpu time and aging
		aging = process(time, agingCounter, &q1, &q2, &q3);
		if (aging)
			agingCounter = 0;
		//printf("***************************\n");
		//printf("The last moment of Time(%d)\n", time);
		//printf("This is Queue 1:\n");
		//printQ(&q1);
		//printf("This is Queue 2:\n");
		//printQ(&q2);
		//printf("This is Queue 3:\n");
		//printQ(&q3);
		//printf("***************************\n");
		//printf("result\n");
		//printQ(&resultQ);
		time += 1;
	}
	//print result
	printf("\n");
	outputFile(&resultQ);
	return 0;
}
