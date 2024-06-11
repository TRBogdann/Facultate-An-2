#include "data.h"
#include <stdio.h>
#include <malloc.h>
#include <string.h>

typedef struct Node{
Item data;
struct Node* next;
}Node;

void insertFist(Node** first,Item item)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data.code = item.code;
    newNode->data.price = item.price;
    newNode->data.name = (char*)malloc(strlen(item.name)+1);
    strcpy(newNode->data.name,item.name);
    newNode->next = *first;
    *first = newNode;
}

void printList(Node* node)
{
    while(node!=NULL)
    {
        printItem(node->data);
        node = node->next;
    }
}

void insertLast(Node** first,Item item)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data.code = item.code;
    newNode->data.price = item.price;
    newNode->data.name = (char*)malloc(strlen(item.name)+1);
    strcpy(newNode->data.name,item.name);
    newNode->next=NULL;
    if(*first == NULL)
    {
        *first = newNode;
        return;
    }
    Node *temp = *first; 
    while(temp->next!=NULL)
    {
        temp = temp->next;
    }
    temp->next = newNode;
}

Node* genList(const char* filename)
{
    FILE* file = fopen(filename, "rt");
    Node* first = NULL;
    if(file != NULL && !feof(file))
    {
        insertLast(&first, newItemFromFile(file));
        Node *current = first;
        while(!feof(file))
        {
            insertLast(&current, newItemFromFile(file));
            current = current->next;
        }
    }
    fclose(file);
    return first;
}

void deleteList(Node** first)
{
    Node* begin = *first;
    Node* end = NULL;
    while(begin!=NULL)
    {
        end = begin->next;
        free(begin);
        begin = end;
    }
    *first = NULL;
}
int main()
{
    //FILE* input = fopen("data.txt","rt");
    /*
    Node* node = NULL;
    insertLast(&node,newItem(2,"Paine",2.7));
    insertLast(&node,newItem(1,"Paine Integrala",2.5));
    printList(node);
    */

    Node* first = genList("data.txt");
    printList(first);
    deleteList(&first);
    return 0;
}
