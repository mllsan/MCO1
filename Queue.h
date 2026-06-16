#ifndef QUEUE_H
#define QUEUE_H

/* Preprocessor Directives */
#include <stdbool.h>

/* Definitions */
typedef struct node {
    int data;
    struct node *next;
} Node;

typedef struct {
    Node *nHead;
    Node *nTail;
} Queue;

/* Function Prototypes */
void CreateQueue(Queue *Q);
void Enqueue(Queue *Q, int x);
int Dequeue(Queue *Q);
int Head(Queue Q);
int Tail(Queue Q);
bool isEmptyQueue(Queue Q);
bool isFullQueue();

#endif