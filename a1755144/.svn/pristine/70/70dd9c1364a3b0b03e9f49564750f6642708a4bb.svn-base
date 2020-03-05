#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "emulator.h"
#include "sr.h"

/* ******************************************************************
   Go Back N protocol.  Adapted from
   ALTERNATING BIT AND GO-BACK-N NETWORK EMULATOR: VERSION 1.1  J.F.Kurose

   Network properties:
   - one way network delay averages five time units (longer if there
   are other messages in the channel for GBN), but can be larger
   - packets can be corrupted (either the header or the data portion)
   or lost, according to user-defined probabilities
   - packets will be delivered in the order in which they were sent
   (although some can be lost).

   Modifications (6/6/2008 - CLP):
   - removed bidirectional GBN code and other code not used by prac.
   - fixed C style to adhere to current programming style
   (24/3/2013 - CLP)
   - added GBN implementation
**********************************************************************/

#define RTT  15.0       /* round trip time.  MUST BE SET TO 15.0 when submitting assignment */
#define WINDOWSIZE 6    /* Maximum number of buffered unacked packet */
#define SEQSPACE 12     /* min sequence space for GBN must be at least windowsize + 1 */
#define NOTINUSE (-1)   /* used to fill header fields that are not being used */

/* generic procedure to compute the checksum of a packet.  Used by both sender and receiver
   the simulator will overwrite part of your packet with 'z's.  It will not overwrite your
   original checksum.  This procedure must generate a different checksum to the original if
   the packet is corrupted.
*/
int ComputeChecksum(struct pkt packet)
{
	int checksum = 0;
	int i;

	checksum = packet.seqnum;
	checksum += packet.acknum;
	for (i = 0; i < 20; i++)
		checksum += (int)(packet.payload[i]);

	return checksum;
}

bool IsCorrupted(struct pkt packet)
{
	if (packet.checksum == ComputeChecksum(packet))
		return (false);
	else
		return (true);
}


/********* Sender (A) variables and functions ************/

static struct pkt buffer[WINDOWSIZE];  /* array for storing packets waiting for ACK */
static int windowfirst, windowlast;    /* array indexes of the first/last packet awaiting ACK */
static int windowcount;                /* the number of packets currently awaiting an ACK */
static int A_nextseqnum;               /* the next sequence number to be used by the sender */

/* called from layer 5 (application layer), passed the message to be sent to other side */
void A_output(struct msg message)
{
	struct pkt sendpkt;
	int i;

	/* if not blocked waiting on ACK */
	if (windowcount < WINDOWSIZE) {
		if (TRACE > 1)
			printf("----A: New message arrives, send window is not full, send new messge to layer3!\n");

		/* create packet */
		sendpkt.seqnum = A_nextseqnum;
		sendpkt.acknum = NOTINUSE;
		for (i = 0; i < 20; i++)
			sendpkt.payload[i] = message.data[i];
		sendpkt.checksum = ComputeChecksum(sendpkt);

		/* put packet in window buffer */
		/* windowlast will always be 0 for alternating bit; but not for GoBackN */
		windowlast = (windowlast + 1) % WINDOWSIZE;
		buffer[windowlast] = sendpkt;
		windowcount++;

		/* send out packet */
		if (TRACE > 0)
			printf("Sending packet %d to layer 3\n", sendpkt.seqnum);
		tolayer3(A, sendpkt);
		/*start the timer when every packet was sent*/
		/*if (windowcount > 1)
			stoptimer(A);
		starttimer(A, RTT);*/
		/* start timer if first packet in window */
		if (windowcount == 1)
			starttimer(A, RTT);

		/* get next sequence number, wrap back to 0 */
		A_nextseqnum = (A_nextseqnum + 1) % SEQSPACE;
	}
	/* if blocked,  window is full */
	else {
		if (TRACE > 0)
			printf("----A: New message arrives, send window is full\n");
		window_full++;
	}
}


/* called from layer 3, when a packet arrives for layer 4
   In this practical this will always be an ACK as B never sends data.
*/
void A_input(struct pkt packet)
{
	int ackcount = 0;
	int i;
	int index;
	bool duplicated = true;

	/* if received ACK is not corrupted */
	if (!IsCorrupted(packet)) {
		if (TRACE > 0)
			printf("----A: uncorrupted ACK %d is received\n", packet.acknum);
		total_ACKs_received++;

		index = windowfirst;
		for (i = 0; i < windowcount; i++)
		{
			if (buffer[index].seqnum == packet.acknum && buffer[index].acknum == NOTINUSE)
			{
				duplicated = false;
				break;
			}
			index = (index + 1) % WINDOWSIZE;
		}

		/* check if new ACK or duplicate */
		if (!duplicated) {
			if (windowcount != 0)
			{
				int seqfirst = buffer[windowfirst].seqnum;
				int seqlast = buffer[windowlast].seqnum;
				/* check case when seqnum has and hasn't wrapped */
				if (((seqfirst <= seqlast) && (packet.acknum >= seqfirst && packet.acknum <= seqlast)) ||
					((seqfirst > seqlast) && (packet.acknum >= seqfirst || packet.acknum <= seqlast))) {

					/* packet is a new ACK */
					if (TRACE > 0)
						printf("----A: ACK %d is not a duplicate\n", packet.acknum);
					new_ACKs++;

					index = windowfirst;
					for (i = 0; i < windowcount; i++)
					{
						if (buffer[index].seqnum == packet.acknum)
						{
							buffer[index].acknum = packet.acknum;
							break;
						}
						index = (index + 1) % WINDOWSIZE;
					}
					if (packet.acknum == seqfirst)
					{
						index =windowfirst;
						for (i = 0; i < windowcount; ++i)
						{
							if (buffer[index].acknum == NOTINUSE)
								break;
							buffer[index].acknum = NOTINUSE;
							ackcount++;
							index = (index + 1) % WINDOWSIZE;
						}
						/* slide window by the number of packets ACKed */
						windowfirst = (windowfirst + ackcount) % WINDOWSIZE;
						/* delete the acked packets from window buffer */
						for (i = 0; i < ackcount; i++)
							windowcount--;
						/* stop the timer if no more packets left */
						/*if (windowcount == 0)
							stoptimer(A);*/
						/*reset timer if the oldest get acked*/
						stoptimer(A);
						if (windowcount != 0)
						starttimer(A, RTT);
					}
				}
			}
		}
		else
			if (TRACE > 0)
				printf("----A: duplicate ACK received, do nothing!\n");
	}
	else
		if (TRACE > 0)
			printf("----A: corrupted ACK is received, do nothing!\n");
}

/* called when A's timer goes off */
void A_timerinterrupt(void)
{
	/*int i;
	int index;*/

	if (TRACE > 0)
		printf("----A: time out,resend packets!\n");

	/*index = windowfirst;
	for (i = 0; i < windowcount; i++) {
		if (buffer[index].acknum == NOTINUSE)
		{
			if (TRACE > 0)
				printf("---A: resending packet %d\n", (buffer[(windowfirst + i) % WINDOWSIZE]).seqnum);

			tolayer3(A, buffer[(windowfirst + i) % WINDOWSIZE]);
			packets_resent++;
		}
		index = (index + 1) % WINDOWSIZE;
	}*/
	if (TRACE > 0)
		printf("---A: resending packet %d\n", (buffer[windowfirst]).seqnum);

	tolayer3(A, buffer[windowfirst]);
	packets_resent++;

	starttimer(A, RTT);
}



/* the following routine will be called once (only) before any other */
/* entity A routines are called. You can use it to do any initialization */
void A_init(void)
{
	/* initialise A's window, buffer and sequence number */
	A_nextseqnum = 0;  /* A starts with seq num 0, do not change this */
	windowfirst = 0;
	windowlast = -1;   /* windowlast is where the last packet sent is stored.
			   new packets are placed in winlast + 1
			   so initially this is set to -1
			 */
	windowcount = 0;
}



/********* Receiver (B)  variables and procedures ************/

static int B_nextseqnum;   /* the sequence number for the next packets sent by B */
static struct pkt bufferB[WINDOWSIZE];  /* array for storing packets waiting for ACK */
static int windowfirstB, windowlastB;    /* array indexes of the first/last packet awaiting ACK */             /* the number of packets currently awaiting an ACK */


/* called from layer 3, when a packet arrives for layer 4 at B*/
void B_input(struct pkt packet)
{
	struct pkt sendpkt;
	int i;

	/* if not corrupted and received packet is in order */
	if (!IsCorrupted(packet)) {
		int seqfirst = bufferB[windowfirstB].seqnum;
		int seqlast = bufferB[windowlastB].seqnum;
		if (TRACE > 0)
			printf("----B: packet %d is correctly received, send ACK!\n", packet.seqnum);
		packets_received++;

		if (((seqfirst <= seqlast) && (packet.seqnum >= seqfirst && packet.seqnum <= seqlast)) ||
				((seqfirst > seqlast) && (packet.seqnum >= seqfirst || packet.seqnum <= seqlast)))
		{
			for (i = 0; i < WINDOWSIZE; i++)
			{
				if (bufferB[i].seqnum == packet.seqnum)
				{
					bufferB[i] = packet;
					bufferB[i].acknum = packet.seqnum;
					break;
				}
			}
			if (packet.seqnum == bufferB[windowfirstB].seqnum)
			{
				while (bufferB[windowfirstB].acknum != NOTINUSE)
				{
					tolayer5(B, bufferB[windowfirstB].payload);
					windowfirstB = (windowfirstB + 1) % WINDOWSIZE;
					windowlastB = (windowlastB + 1) % WINDOWSIZE;
					bufferB[windowlastB].seqnum = B_nextseqnum;
					bufferB[windowlastB].acknum = NOTINUSE;
					B_nextseqnum = (B_nextseqnum + 1) % SEQSPACE;
				}
			}
		}

		/* send an ACK for the received packet */
		sendpkt.acknum = packet.seqnum;


		/* create packet */
		sendpkt.seqnum = sendpkt.acknum;

		/* we don't have any data to send.  fill payload with 0's */
		for (i = 0; i < 20; i++)
			sendpkt.payload[i] = '0';

		/* computer checksum */
		sendpkt.checksum = ComputeChecksum(sendpkt);

		/* send out packet */
		tolayer3(B, sendpkt);

	}
	/*else {
		// packet is corrupted or out of order resend last ACK 
		if (TRACE > 0)
			// printf("----B: packet corrupted or not expected sequence number, resend ACK!\n");
			printf("----B: packet corrupted, do nothing!\n");

	}*/
}

/* the following routine will be called once (only) before any other */
/* entity B routines are called. You can use it to do any initialization */
void B_init(void)
{
	int i;
	B_nextseqnum = WINDOWSIZE;
	/* initialise A's window, buffer and sequence number */
	windowfirstB = 0;
	windowlastB = WINDOWSIZE - 1;   /* windowlast is where the last packet sent is stored.
			   new packets are placed in winlast + 1
			   so initially this is set to -1
			 */
	for (i = 0; i < WINDOWSIZE; i++)
	{
		bufferB[i].seqnum = i;
		bufferB[i].acknum = NOTINUSE;
	}
}

/******************************************************************************
 * The following functions need be completed only for bi-directional messages *
 *****************************************************************************/

 /* Note that with simplex transfer from a-to-B, there is no B_output() */
void B_output(struct msg message)
{
}

/* called when B's timer goes off */
void B_timerinterrupt(void)
{
}
