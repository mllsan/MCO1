/* Preprocessor Directives */
#include "Queue.h"
#include <stdio.h>
#include <stdlib.h>

/* Function Implementations */
void CreateQueue(Queue *Q) {
    Q->nHead = NULL;
    Q->nTail = NULL;
}

void Enqueue(Queue *Q, int x) {
    if (isFullQueue()) {
        printf("Queue Overflow\n");
        return;
    }

    Node *newNode = (Node *)malloc(sizeof(Node));

    newNode->data = x;
    newNode->next = NULL;

    if (isEmptyQueue(*Q)) {
        Q->nHead = newNode;
        Q->nTail = newNode;
    } 
    else {
        Q->nTail->next = newNode;
        Q->nTail = newNode;
    }
}

int Dequeue(Queue *Q) {
    if (isEmptyQueue(*Q)) {
        printf("Queue Underflow\n");
        return -1;
    }

    Node *temp = Q->nHead;
    int value = temp->data;

    Q->nHead = Q->nHead->next;

    if (Q->nHead == NULL)
        Q->nTail = NULL;

    free(temp);

    return value;
}

int Head(Queue Q) {
    if (isEmptyQueue(Q)) {
        printf("Queue Empty\n");
        return -1;
    }
    return Q.nHead->data;
}

int Tail(Queue Q) {
    if (isEmptyQueue(Q)) {
        printf("Queue Empty\n");
        return -1;
    }
    return Q.nTail->data;
}

bool isEmptyQueue(Queue Q) {
    return Q.nHead == NULL;
}

bool isFullQueue() {
    Node *temp = (Node *)malloc(sizeof(Node));

    if (temp == NULL)
        return true;

    free(temp);
    return false;
}
